using System.Globalization;
using System.Text;
using System.Text.RegularExpressions;

namespace MailRuDesktop.App;

/// <summary>
/// Presentation only: the original bounded technical records remain unchanged.
/// Never guess an account from the currently selected mailbox or an old
/// ACCOUNT_NNN label: their historical ordering is not guaranteed.
/// </summary>
internal static class HumanDiagnostics
{
    private static readonly Regex Email = new(
        @"(?i)\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}\b",
        RegexOptions.Compiled | RegexOptions.CultureInvariant);
    private static readonly Regex Secret = new(
        @"(?i)\b(access_token|refresh_token|securitytoken|token|password|cookie|Mpop|ssdc|sdcs)\s*[:=]\s*[^\s&;,]+",
        RegexOptions.Compiled | RegexOptions.CultureInvariant);
    private static readonly Regex PushLine = new(
        @"^(?<date>\d{4}-\d\d-\d\d \d\d:\d\d:\d\d(?:\.\d+)?\s+[+-]\d\d:\d\d)\s+#\d+\s+\[(?<stage>[A-Z_]+)\]\s+(?<code>[A-Z0-9_]+)(?<extra>.*)$",
        RegexOptions.Compiled | RegexOptions.CultureInvariant);
    private static readonly Regex Attribute = new(
        @"(?i)(?<key>[a-z][a-z0-9_-]*)=(?<value>[^;\s]+)",
        RegexOptions.Compiled | RegexOptions.CultureInvariant);

    private static readonly Dictionary<string, string> AppActions = new(StringComparer.OrdinalIgnoreCase)
    {
        ["auth_token_flow"] = "Проверка авторизации",
        ["auth_exception"] = "Вход в почту",
        ["auth_failure"] = "Дополнительная проверка входа",
        ["account_switch"] = "Переключение аккаунта",
        ["folder_load"] = "Загрузка папки писем",
        ["full_message"] = "Загрузка полного письма",
        ["message_window_full"] = "Открытие письма в отдельном окне",
        ["mail_render_timing"] = "Отображение письма",
        ["reader_visual_ready"] = "Отображение письма в предпросмотре",
        ["mail_image_proxy"] = "Загрузка изображения из письма через прокси",
        ["mail_image_inline"] = "Загрузка встроенного изображения",
        ["message_window_send"] = "Отправка письма из отдельного окна",
        ["message_window_move"] = "Перемещение письма из отдельного окна",
        ["message_window_attachment"] = "Скачивание вложения",
        ["incoming_attachment"] = "Скачивание вложения",
        ["incoming_attachment_open"] = "Открытие вложения",
        ["send"] = "Отправка письма",
        ["schedule"] = "Отложенная отправка письма",
        ["send_rejected"] = "Отправка письма",
        ["schedule_rejected"] = "Постановка письма на отложенную отправку",
        ["reply_archive"] = "Архивирование письма после ответа",
        ["outbox_resend"] = "Повторная отправка письма из исходящих",
        ["draft_save"] = "Сохранение черновика",
        ["preview_send"] = "Отправка письма из предпросмотра",
        ["message_move"] = "Перемещение письма",
        ["message_remove"] = "Удаление письма",
        ["message_marks"] = "Изменение отметок письма",
        ["folder_create"] = "Создание папки",
        ["folder_rename"] = "Переименование папки",
        ["folder_delete"] = "Удаление папки",
        ["folder_clear"] = "Очистка папки",
        ["server_search"] = "Поиск писем на сервере",
        ["contacts_load"] = "Загрузка адресной книги",
        ["contacts_fast"] = "Поиск адресатов",
        ["thread_detail"] = "Загрузка состава переписки",
        ["conversation_body"] = "Загрузка письма внутри переписки",
        ["detached_thread_detail"] = "Загрузка состава переписки в отдельном окне",
        ["detached_conversation"] = "Загрузка письма переписки в отдельном окне",
        ["full_open_read"] = "Отметка открытого письма прочитанным",
        ["push_notification"] = "Обработка уведомления о письме",
        ["push_event_folder_refresh"] = "Обновление папки после уведомления",
        ["reader_init"] = "Инициализация просмотра письма",
        ["reader_shell_stage"] = "Подготовка отображения письма",
        ["reader_shell_begin"] = "Начало отображения письма",
        ["reader_shell_error"] = "Ошибка отображения письма",
        ["message_window_attachment_open"] = "Открытие вложения в отдельном окне",
        ["account_copy_address"] = "Копирование адреса аккаунта"
    };

    private static readonly Dictionary<string, string> PushActions = new(StringComparer.Ordinal)
    {
        ["UI_INITIALIZED"] = "Окно приложения инициализировано",
        ["EXIT_REQUESTED"] = "Запрошено закрытие приложения",
        ["PERSISTENT_GROUPS_ONLY"] = "Уведомления работают только через сохранённые группы",
        ["AUTO_NOTIFICATIONS_ENABLED"] = "Включены автоматические уведомления",
        ["AUTO_NOTIFICATIONS_DISABLED"] = "Выключены автоматические уведомления",
        ["REPLAY_SUPPRESSION_ENABLED"] = "Включена защита от повторных уведомлений",
        ["REPLAY_SUPPRESSION_DISABLED"] = "Выключена защита от повторных уведомлений",
        ["MCS_RECEIVE_ENABLED"] = "Включено получение уведомлений Google",
        ["MCS_RECEIVE_DISABLED_ONLY"] = "Получение уведомлений Google выключено",
        ["STAGE1_PROGRESS"] = "Регистрация устройства Google продолжается",
        ["STAGE1_COMPLETE"] = "Регистрация устройства Google завершена",
        ["ASSIGNED_IDENTITY_MISSING"] = "Сохранённый получатель Google не найден",
        ["EXPLICIT_IDENTITY_CREATE"] = "Создание регистрации Google",
        ["SAVED_IDENTITY_REUSED"] = "Используется сохранённая регистрация Google",
        ["GROUP_SUBSCRIBE_START"] = "Начата регистрация группы аккаунтов в PushMe",
        ["GROUP_SUBSCRIBE_CONFIRMED"] = "Группа аккаунтов зарегистрирована в PushMe",
        ["GROUP_UNSUBSCRIBE_START"] = "Начато удаление группы из PushMe",
        ["GROUP_UNSUBSCRIBE_CONFIRMED"] = "Удаление аккаунтов из PushMe подтверждено",
        ["GROUP_UNSUBSCRIBE_REMAINING"] = "Аккаунтов осталось в группе",
        ["ACCOUNT_SUBSCRIBE_QUEUED"] = "Аккаунт поставлен в очередь регистрации PushMe",
        ["ACCOUNT_SUBSCRIBE_ACCEPTED"] = "Регистрация аккаунта в PushMe подтверждена",
        ["ACCOUNT_SUBSCRIBE_REJECTED"] = "PushMe отклонил регистрацию аккаунта",
        ["ACCOUNT_SUBSCRIBE_UNCONFIRMED"] = "Результат регистрации аккаунта не подтверждён",
        ["GROUP_MEMBER_BEFORE_REMOVAL"] = "Начато удаление аккаунта из группы",
        ["GROUP_MEMBER_REMOVED"] = "Аккаунт удалён из группы",
        ["GROUP_MEMBER_REMOVE_DEFERRED"] = "Удаление аккаунта отложено",
        ["GROUP_MEMBER_REMOVE_UNCONFIRMED"] = "Удаление аккаунта не подтверждено",
        ["GROUP_DELIVERY_OBSERVED"] = "Уведомление для аккаунта доставлено",
        ["GROUP_BATCH"] = "Обработка группы аккаунтов",
        ["PUSHME_HTTP_SEND"] = "Отправка запроса в PushMe",
        ["PUSHME_READ_RESPONSE"] = "Чтение ответа PushMe",
        ["PUSHME_ACCEPTED"] = "PushMe подтвердил регистрацию",
        ["PUSHME_RESPONSE_REJECTED"] = "PushMe отклонил ответ о регистрации",
        ["PUSHME_ACCEPTED_COUNT"] = "Количество принятых PushMe аккаунтов",
        ["REJECTED_COUNT"] = "Количество отклонённых PushMe аккаунтов",
        ["HTTP_STATUS"] = "Ответ сервера PushMe",
        ["CONNECT_ATTEMPT"] = "Попытка подключения к серверу уведомлений",
        ["MCS_TCP_CONNECT"] = "Установление соединения с Google",
        ["MCS_TCP_CONNECTED"] = "Сетевое соединение с Google установлено",
        ["MCS_TLS_HANDSHAKE"] = "Защищённое соединение с Google: согласование",
        ["MCS_TLS_OK"] = "Защищённое соединение с Google установлено",
        ["MCS_LOGIN_SEND"] = "Передача запроса входа в Google",
        ["MCS_LOGIN_PARSE"] = "Проверка ответа Google на вход",
        ["MCS_LOGIN_OK"] = "Авторизация в Google прошла",
        ["MCS_SERVER_LOGIN_ERROR"] = "Сервер Google отклонил авторизацию",
        ["MCS_LOGIN_INVALID_RESPONSE"] = "Некорректный ответ Google на вход",
        ["MCS_KEEPALIVE_TIMEOUT"] = "Превышено время ожидания ответа Google",
        ["MCS_SERVER_CLOSE"] = "Сервер Google закрыл соединение",
        ["MCS_ONLY_STOP_REQUESTED"] = "Остановлено получение уведомлений Google",
        ["NEW_MAIL_EVENT"] = "Получено уведомление о новом письме",
        ["KEEPALIVE_SENT"] = "Поддержание соединения с Google",
        ["PING_FROM_SERVER"] = "Получена проверка соединения Google",
        ["REPLAY_DISK_SUPPRESSED"] = "Повторное уведомление пропущено",
        ["EVENT_DEDUP_PERSISTED"] = "Уведомление отмечено обработанным",
        ["SELECTIVE_ACK_WRITTEN"] = "Отправлено подтверждение получения уведомления",
        ["SELECTIVE_ACK_ID_MISSING"] = "В уведомлении отсутствует идентификатор подтверждения",
        ["MAILBOX_REMOVED_WITHOUT_MCS_RESTART"] = "Удалён аккаунт без перезапуска соединения Google",
        ["RECONCILE_ENABLED"] = "Обновление состава подключённых аккаунтов",
        ["RECONCILE_DISABLED"] = "Отключение аккаунтов от уведомлений",
        ["RECEIVER_RESTART_REQUIRED"] = "Запрошено переподключение к Google",
        ["GROUP_ACCOUNT_REMOVE"] = "Удаление аккаунта из группы",
        ["GOOGLE_IDENTITY_READ"] = "Чтение регистрации устройства Google",
        ["PUSH_GROUP_STARTUP"] = "Восстановление сохранённых групп уведомлений",
        ["GROUP_DELIVERY_RECORD"] = "Сохранение сведений о доставке",
        ["LOGIN_PENDING_RECEIPTS"] = "Проверка неподтверждённых уведомлений",
        ["LOGIN_RECEIPTS_CONFIRMED"] = "Накопленные уведомления подтверждены",
        ["LOGIN_RECEIPTS_UNCONFIRMED"] = "Подтверждение накопленных уведомлений не получено",
        ["USER_CANCELLED"] = "Операция отменена пользователем"
    };

    internal static string Application(string report, bool includeAccounts, bool technical = false)
    {
        var aliases = new Dictionary<string, int>(StringComparer.OrdinalIgnoreCase);
        if (technical) return Protect(report, includeAccounts, aliases);
        var result = new StringBuilder("Журнал приложения — действия и результаты\n");
        result.AppendLine("Указан только достоверно установленный аккаунт. Старые записи могут не содержать адрес.");
        result.AppendLine();
        var shown = 0;
        foreach (var line in report.Split('\n'))
        {
            var fields = line.TrimEnd('\r').Split('\t', 3);
            if (fields.Length != 3) continue;
            var when = Time(fields[0]);
            var category = fields[1];
            var reason = fields[2];
            var values = Attributes(reason);
            var account = ResolveAccount(Get(values, "account"),
                includeAccounts, aliases);
            if (!AppActions.TryGetValue(category, out var action))
            {
                if (category.StartsWith("account_switch_", StringComparison.OrdinalIgnoreCase))
                    action = "Переключение почтового аккаунта";
                else action = "Событие приложения (" + category + ")";
            }
            string status;
            string explanation;
            if (category == "auth_token_flow")
            {
                var flow = Get(values, "flow");
                var stage = Get(values, "stage");
                var outcome = Get(values, "result");
                action = "Авторизация — " + AuthStep(flow, stage);
                (status, explanation) = AuthOutcome(outcome);
                var http = Get(values, "http");
                if (http.Length > 0 && http != "not_available")
                    explanation += "; ответ сервера HTTP " + http;
            }
            else if (category == "mail_render_timing")
            {
                status = "УСПЕШНО";
                explanation = "Письмо загружено и передано в просмотр";
                var elapsed = Get(values, "fetch-ms");
                if (elapsed.Length > 0) explanation += "; получение " + elapsed + " мс";
                var attachments = Get(values, "attachment-count");
                if (attachments.Length > 0) explanation += "; вложений " + attachments;
            }
            else if (category == "reader_visual_ready")
            {
                status = "УСПЕШНО";
                explanation = "Текст и доступные изображения показаны";
                var elapsed = Get(values, "elapsed-ms");
                if (elapsed.Length > 0) explanation += " (" + elapsed + " мс)";
            }
            else if (category == "mail_image_inline")
            {
                var loaded = reason.Contains("loaded", StringComparison.OrdinalIgnoreCase);
                status = loaded ? "УСПЕШНО" : "ОШИБКА";
                explanation = loaded ? "Изображение загружено" : "Изображение не удалось распознать";
                var type = Get(values, "type");
                if (type.Length > 0) explanation += "; формат " + type;
            }
            else if (category == "mail_image_proxy")
            {
                var http = Get(values, "http");
                status = http.StartsWith('2') ? "УСПЕШНО" :
                    http.Length > 0 ? "ОШИБКА" : "СВЕДЕНИЕ";
                explanation = http.Length > 0 ? "Ответ сервера HTTP " + http :
                    "Обращение к серверу изображений";
                var host = Get(values, "final-host");
                if (host.Length > 0) explanation += "; сервер " + host;
            }
            else
            {
                var success = reason.Contains("accepted_and_archived", StringComparison.OrdinalIgnoreCase) ||
                    reason.Contains("result=success", StringComparison.OrdinalIgnoreCase) ||
                    reason.Contains("completed", StringComparison.OrdinalIgnoreCase) ||
                    reason.StartsWith("success", StringComparison.OrdinalIgnoreCase);
                var pending = reason.StartsWith("started", StringComparison.OrdinalIgnoreCase);
                status = success ? "УСПЕШНО" : pending ? "НАЧАТО" :
                    "ОШИБКА";
                explanation = success ? "Выполнено" : pending ? "Операция выполняется" :
                    ErrorReason(reason);
            }
            shown++;
            result.Append(when).Append(" | ").Append(status).Append(" | ")
                .Append(account).Append(" | ").Append(action)
                .Append(" — ").AppendLine(explanation);
        }
        if (shown == 0)
            result.AppendLine("Записей пока нет.");
        return result.ToString();
    }

    internal static string Push(string report, bool includeAccounts, bool technical = false)
    {
        var aliases = new Dictionary<string, int>(StringComparer.OrdinalIgnoreCase);
        if (technical) return Protect(report, includeAccounts, aliases);
        var result = new StringBuilder("Google / PushMe — история подключений и уведомлений\n");
        result.AppendLine("События без аккаунта относятся к общему соединению или группе.");
        result.AppendLine();
        var shown = 0;
        foreach (var line in report.Split('\n'))
        {
            var match = PushLine.Match(line.TrimEnd('\r'));
            if (!match.Success) continue;
            var code = match.Groups["code"].Value;
            var stage = match.Groups["stage"].Value;
            var extra = match.Groups["extra"].Value;
            var properties = Attributes(extra);
            var account = ResolveAccount(Get(properties, "account"),
                includeAccounts, aliases, "Общий сервис");
            var http = Get(properties, "count_or_code");
            var status = PushStatus(stage, code, http);
            var action = PushActions.TryGetValue(code, out var label)
                ? label
                : stage == "ERROR"
                    ? FailureStep(code)
                    : "Служебное событие (" + code + ")";
            var detail = new List<string>();
            if (stage == "ERROR")
                detail.Add("сбой на этом этапе; " + FailureKind(code));
            else if (code == "HTTP_STATUS" && http.Length > 0)
                detail.Add("HTTP " + http);
            else if (http.Length > 0)
                detail.Add("значение " + http);
            var item = Get(properties, "item");
            if (item.Length > 0) detail.Add("аккаунт " + item + " в группе");
            shown++;
            result.Append(Time(match.Groups["date"].Value))
                .Append(" | ").Append(status).Append(" | ")
                .Append(account).Append(" | ").Append(action);
            if (detail.Count > 0) result.Append(" — ").Append(string.Join("; ", detail));
            result.AppendLine();
        }
        if (shown == 0)
            result.AppendLine("Записей пока нет.");
        return result.ToString();
    }

    private static string Time(string value) =>
        DateTimeOffset.TryParse(value, CultureInfo.InvariantCulture,
            DateTimeStyles.None, out var date)
            ? date.ToString("dd.MM HH:mm:ss", CultureInfo.InvariantCulture)
            : "Время неизвестно";

    private static Dictionary<string, string> Attributes(string input)
    {
        var data = new Dictionary<string, string>(StringComparer.OrdinalIgnoreCase);
        foreach (Match m in Attribute.Matches(input))
            data[m.Groups["key"].Value] = m.Groups["value"].Value;
        return data;
    }

    private static string Get(Dictionary<string, string> attributes, string key) =>
        attributes.TryGetValue(key, out var value) ? value : "";

    private static string ResolveAccount(string value, bool includeAccounts,
        Dictionary<string, int> aliases, string unknown = "Аккаунт не указан")
    {
        if (string.IsNullOrWhiteSpace(value)) return unknown;
        if (value.StartsWith("ACCOUNT_", StringComparison.OrdinalIgnoreCase))
            return value == "ACCOUNT_UNKNOWN" ? "Аккаунт не определён" :
                "Аккаунт " + value["ACCOUNT_".Length..] + " (старый журнал)";
        if (!Email.IsMatch(value)) return "Аккаунт не определён";
        if (includeAccounts) return value;
        if (!aliases.TryGetValue(value, out var number))
        {
            number = aliases.Count + 1;
            aliases[value] = number;
        }
        return "Аккаунт №" + number.ToString("D3", CultureInfo.InvariantCulture);
    }

    private static string AuthStep(string flow, string stage)
    {
        var operation = flow switch
        {
            "background_counter" => "обновление счётчиков",
            "active_folder" => "загрузка выбранной папки",
            "account_switch" => "переключение аккаунта",
            _ => "проверка доступа к почте"
        };
        var phase = stage switch
        {
            "initial_access" => "проверка действующего ключа доступа",
            "refresh_request" => "запрос нового ключа доступа",
            "refresh_response" => "ответ на запрос обновления ключа",
            "verify_new_access" => "проверка обновлённого ключа",
            "result" => "итог проверки",
            _ => "этап " + stage
        };
        return operation + ", " + phase;
    }

    private static (string Status, string Explanation) AuthOutcome(string result) =>
        result switch
        {
            "accepted" => ("УСПЕШНО", "Сервер принял ключ доступа на этом этапе"),
            "new_access_received" => ("УСПЕШНО", "Новый ключ получен, требуется проверка"),
            "verified" => ("УСПЕШНО", "Обновлённый ключ проверен сервером"),
            "rejected" => ("ОШИБКА", "Сервер отказал в доступе на этом этапе"),
            "not_refreshed" => ("ОШИБКА", "Не удалось обновить ключ доступа"),
            "unverified" => ("ПРЕДУПРЕЖДЕНИЕ", "Доступ не подтверждён"),
            "exception" => ("ОШИБКА", "Исключение при запросе"),
            _ => ("СВЕДЕНИЕ", "Результат: " + result)
        };

    private static string PushStatus(string stage, string code, string count)
    {
        if (stage == "ERROR" ||
            code.EndsWith("_REJECTED", StringComparison.Ordinal) ||
            code.EndsWith("_UNCONFIRMED", StringComparison.Ordinal) ||
            code is "MCS_SERVER_LOGIN_ERROR" or "MCS_KEEPALIVE_TIMEOUT" or
                "MCS_LOGIN_INVALID_RESPONSE" ||
            (code == "HTTP_STATUS" && int.TryParse(count, out var http) && http >= 400))
            return "ОШИБКА";
        if (code.Contains("DEFERRED", StringComparison.Ordinal) ||
            code.Contains("MISSING", StringComparison.Ordinal) ||
            code.Contains("DISABLED", StringComparison.Ordinal))
            return "ПРЕДУПРЕЖДЕНИЕ";
        if (code.Contains("START", StringComparison.Ordinal) ||
            code.Contains("CONNECT_ATTEMPT", StringComparison.Ordinal) ||
            code.Contains("QUEUED", StringComparison.Ordinal) ||
            code is "MCS_TCP_CONNECT" or "MCS_TLS_HANDSHAKE" or "PUSHME_HTTP_SEND")
            return "НАЧАТО";
        if (code.Contains("ACCEPTED", StringComparison.Ordinal) ||
            code.Contains("CONFIRMED", StringComparison.Ordinal) ||
            code.Contains("COMPLETE", StringComparison.Ordinal) ||
            code.EndsWith("_OK", StringComparison.Ordinal) ||
            code.Contains("CONNECTED", StringComparison.Ordinal) ||
            code == "NEW_MAIL_EVENT" || code == "GROUP_DELIVERY_OBSERVED")
            return "УСПЕШНО";
        return "СВЕДЕНИЕ";
    }

    private static string ErrorReason(string source)
    {
        var exception = Regex.Match(source, @"\b[A-Za-z][A-Za-z0-9]*(?:Exception|Error)\b");
        var http = Regex.Match(source, @"(?i)(?:HTTP\s*|код\s*|http=)(\d{3})");
        var info = exception.Success ? exception.Value switch
        {
            "MailRuAuthorizationException" => "Отказ в авторизации",
            "HttpRequestException" => "Ошибка сетевого запроса",
            "TaskCanceledException" => "Запрос отменён или истекло время ожидания",
            "OperationCanceledException" => "Операция отменена",
            "IOException" => "Ошибка чтения или записи",
            "UnauthorizedAccessException" => "Нет разрешения на выполнение операции",
            "JsonException" => "Не удалось разобрать ответ сервера",
            _ => "Ошибка: " + exception.Value
        } : source.Contains("SERVER_REJECTED", StringComparison.OrdinalIgnoreCase) ||
            source.Contains("archive_rejected", StringComparison.OrdinalIgnoreCase)
            ? "Сервер отклонил операцию"
            : source.Contains("ID_MISMATCH", StringComparison.OrdinalIgnoreCase)
            ? "Идентификатор письма не совпал"
            : "Результат не подтверждён; см. технические записи";
        return info + (http.Success ? "; HTTP " + http.Groups[1].Value : "");
    }

    private static string FailureStep(string code)
    {
        foreach (var known in PushActions.Keys.OrderByDescending(x => x.Length))
            if (code.StartsWith(known + "_", StringComparison.Ordinal))
                return PushActions[known];
        var pos = code.IndexOf("_HTTP_", StringComparison.Ordinal);
        if (pos < 0) pos = code.IndexOf("_EXCEPTION_", StringComparison.Ordinal);
        return pos > 0 && PushActions.TryGetValue(code[..pos], out var action)
            ? action : "Сбой этапа " + code;
    }

    private static string FailureKind(string code)
    {
        if (code.Contains("TIMEOUT", StringComparison.Ordinal)) return "превышено время ожидания";
        if (code.Contains("TLS_AUTH", StringComparison.Ordinal)) return "не установлено защищённое соединение";
        if (code.Contains("SOCKET_", StringComparison.Ordinal)) return "ошибка соединения";
        if (code.Contains("HTTP_", StringComparison.Ordinal)) return "сервер вернул ошибку HTTP";
        if (code.Contains("JSON", StringComparison.Ordinal)) return "некорректный ответ сервера";
        if (code.Contains("CANCELLED", StringComparison.Ordinal)) return "операция отменена";
        return "категория " + code;
    }

    private static string Protect(string report, bool includeAccounts,
        Dictionary<string, int> aliases)
    {
        var safe = Secret.Replace(report, "$1=<скрыто>");
        if (includeAccounts) return safe;
        return Email.Replace(safe, match =>
            ResolveAccount(match.Value, false, aliases));
    }
}
