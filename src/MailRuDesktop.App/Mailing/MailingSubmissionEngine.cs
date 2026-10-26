using System.Text.Json;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App.Mailing;

/// <summary>
/// Serial submission of one durable run. A confirmed POST is never retried
/// because an Excel write failed; uncertain POST outcomes are not retried.
/// The delegate boundaries allow offline fault-injection without live email.
/// </summary>
internal sealed class MailingSubmissionEngine(MailingRunStore store)
{
    internal async Task ExecuteAsync(
        MailingRunRecord run,
        Func<MailingJobRecord, Task<MailRuCommandResult>> submit,
        Func<MailingJobRecord, Task> writeExcel,
        Func<bool> stopRequested,
        Action<MailingJobRecord>? onChanged = null,
        bool persist = true)
    {
        ArgumentNullException.ThrowIfNull(run);
        ArgumentNullException.ThrowIfNull(submit);
        ArgumentNullException.ThrowIfNull(writeExcel);
        ArgumentNullException.ThrowIfNull(stopRequested);

        foreach (var job in run.Jobs)
        {
            if (stopRequested()) break;

            if (job.Status == MailingJobStatus.Prepared)
            {
                if (job.ScheduledAt <= DateTimeOffset.Now.AddMinutes(2))
                {
                    job.Status = MailingJobStatus.Rejected;
                    job.ErrorCode = "scheduled_time_too_near";
                    if (persist) store.Save(run);
                    onChanged?.Invoke(job);
                    continue;
                }

                job.Status = MailingJobStatus.Submitting;
                job.ErrorCode = "";
                if (persist) store.Save(run); // durable BEFORE any network traffic
                onChanged?.Invoke(job);

                MailRuCommandResult result;
                try
                {
                    result = await submit(job);
                }
                catch (Exception ex)
                {
                    job.Status = MailingJobStatus.Indeterminate;
                    job.ErrorCode = "no_confirmed_response_" + ex.GetType().Name;
                    if (persist) store.Save(run);
                    onChanged?.Invoke(job);
                    break; // avoid a cascade of uncertain network outcomes
                }

                if (!result.Success)
                {
                    // Failure with no recognizable application-level refusal may
                    // still mean the server accepted the operation.
                    var explicitRefusal = IsExplicitRejection(result.RawResponse);
                    job.Status = explicitRefusal
                        ? MailingJobStatus.Rejected : MailingJobStatus.Indeterminate;
                    job.ErrorCode = explicitRefusal
                        ? ExplainRefusal(result.RawResponse) : "unconfirmed_server_reply";
                    if (persist) store.Save(run);
                    onChanged?.Invoke(job);
                    if (!explicitRefusal) break;
                    continue;
                }

                job.Status = MailingJobStatus.ExcelWritePending;
                job.ConfirmedAtUtc = DateTimeOffset.UtcNow;
                if (persist) store.Save(run); // server acceptance recorded BEFORE Excel write
                onChanged?.Invoke(job);
            }

            // Reconciliation after a crash or loss of Excel access never POSTs.
            if (job.Status != MailingJobStatus.ExcelWritePending)
                continue;

            try
            {
                await writeExcel(job);
                job.ExcelWritten = true;
                job.Status = MailingJobStatus.CompletedRecord;
                job.ErrorCode = "";
                if (persist) store.Save(run);
                onChanged?.Invoke(job);
            }
            catch (Exception ex)
            {
                job.ErrorCode = "excel_write_pending_" + ex.GetType().Name;
                if (persist) store.Save(run);
                onChanged?.Invoke(job);
                break; // pause new POSTs while the workbook is unavailable
            }
        }
    }

    private static string ExplainRefusal(string response)
    {
        try
        {
            using var document = JsonDocument.Parse(response);
            var root = document.RootElement;
            if (root.TryGetProperty("body", out var body) &&
                body.ValueKind == JsonValueKind.Object &&
                body.TryGetProperty("correspondents.to", out var toErrors) &&
                toErrors.ValueKind == JsonValueKind.Array)
            {
                foreach (var item in toErrors.EnumerateArray())
                {
                    if (item.ValueKind != JsonValueKind.Object ||
                        !item.TryGetProperty("error", out var code) ||
                        code.ValueKind != JsonValueKind.String)
                        continue;
                    var error = code.GetString() ?? "";
                    if (error.Length is < 1 or > 48 ||
                        !error.All(ch => char.IsAsciiLetterOrDigit(ch) || ch == '_'))
                        continue;
                    var address = item.TryGetProperty("value", out var value) &&
                        value.ValueKind == JsonValueKind.String
                        ? value.GetString() ?? "" : "";
                    if (address.Length > 254 || address.Any(char.IsControl))
                        address = "";
                    return "recipient_" + error + (address.Length > 0 ? ": " + address : "");
                }
            }
        }
        catch (JsonException) { }
        return "server_rejected";
    }

    internal static bool IsExplicitRejection(string response)
    {
        try
        {
            using var parsed = JsonDocument.Parse(response);
            if (!parsed.RootElement.TryGetProperty("status", out var status))
                return false;
            if (!int.TryParse(status.ToString(), out var number))
                return false;
            // 429 and 449 are retry/rate-limit responses, not proof that an
            // already-issued scheduling operation was not accepted.
            return number is >= 400 and < 500 and not (429 or 449);
        }
        catch (JsonException) { return false; }
    }

}
