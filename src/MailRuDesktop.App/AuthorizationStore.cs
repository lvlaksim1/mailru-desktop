using System.IO;
using System.Runtime.InteropServices;
using System.Text;
using System.Text.Json;

namespace MailRuDesktop.App;

public sealed record RestoredAuthorization(
    string Login,
    string AccessToken,
    string? RefreshToken);

internal sealed class AuthorizationStore
{
    private static readonly JsonSerializerOptions JsonOptions = new()
    {
        WriteIndented = true
    };

    private readonly string _directoryPath;
    private readonly string _filePath;
    private AuthorizationState _state;

    public AuthorizationStore()
    {
        _directoryPath = Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop");
        _filePath = Path.Combine(_directoryPath, "auth.json");
        _state = LoadState();
    }

    public IReadOnlyList<string> Logins =>
        _state.Accounts
            .Select(account => account.Login)
            .OrderBy(login => login, StringComparer.CurrentCultureIgnoreCase)
            .ToArray();

    public string? LastLogin => _state.LastLogin;

    public bool TryRestore(string login, out RestoredAuthorization? authorization)
    {
        authorization = null;

        var record = _state.Accounts.FirstOrDefault(account =>
            string.Equals(account.Login, login, StringComparison.OrdinalIgnoreCase));

        if (record is null)
            return false;

        try
        {
            var accessToken = Dpapi.Unprotect(record.AccessToken);
            var refreshToken = string.IsNullOrWhiteSpace(record.RefreshToken)
                ? null
                : Dpapi.Unprotect(record.RefreshToken);

            if (string.IsNullOrWhiteSpace(accessToken))
                return false;

            authorization = new RestoredAuthorization(
                record.Login,
                accessToken,
                string.IsNullOrWhiteSpace(refreshToken) ? null : refreshToken);
            return true;
        }
        catch
        {
            return false;
        }
    }

    public void Save(string login, string accessToken, string? refreshToken)
    {
        ArgumentException.ThrowIfNullOrWhiteSpace(login);
        ArgumentException.ThrowIfNullOrWhiteSpace(accessToken);

        var protectedAccess = Dpapi.Protect(accessToken);
        var protectedRefresh = string.IsNullOrWhiteSpace(refreshToken)
            ? null
            : Dpapi.Protect(refreshToken);

        var existing = _state.Accounts.FindIndex(account =>
            string.Equals(account.Login, login, StringComparison.OrdinalIgnoreCase));

        var replacement = new AuthorizationRecord
        {
            Login = login,
            AccessToken = protectedAccess,
            RefreshToken = protectedRefresh,
            SavedAtUtc = DateTimeOffset.UtcNow
        };

        if (existing >= 0)
            _state.Accounts[existing] = replacement;
        else
            _state.Accounts.Add(replacement);

        _state.LastLogin = login;
        Persist();
    }

    public void MarkLastUsed(string login)
    {
        if (_state.Accounts.Any(account =>
                string.Equals(account.Login, login, StringComparison.OrdinalIgnoreCase)))
        {
            _state.LastLogin = login;
            Persist();
        }
    }

    private AuthorizationState LoadState()
    {
        try
        {
            if (!File.Exists(_filePath))
                return new AuthorizationState();

            var json = File.ReadAllText(_filePath, Encoding.UTF8);
            return JsonSerializer.Deserialize<AuthorizationState>(json, JsonOptions)
                   ?? new AuthorizationState();
        }
        catch
        {
            return new AuthorizationState();
        }
    }

    private void Persist()
    {
        Directory.CreateDirectory(_directoryPath);

        var json = JsonSerializer.Serialize(_state, JsonOptions);
        var temp = _filePath + ".tmp";
        File.WriteAllText(temp, json, new UTF8Encoding(false));
        File.Move(temp, _filePath, true);
    }

    private sealed class AuthorizationState
    {
        public string? LastLogin { get; set; }
        public List<AuthorizationRecord> Accounts { get; set; } = [];
    }

    private sealed class AuthorizationRecord
    {
        public string Login { get; set; } = string.Empty;
        public string AccessToken { get; set; } = string.Empty;
        public string? RefreshToken { get; set; }
        public DateTimeOffset SavedAtUtc { get; set; }
    }

    private static class Dpapi
    {
        private const uint CryptProtectUiForbidden = 0x1;

        [StructLayout(LayoutKind.Sequential)]
        private struct DataBlob
        {
            public int Size;
            public IntPtr Data;
        }

        [DllImport("crypt32.dll", CharSet = CharSet.Unicode, SetLastError = true)]
        [return: MarshalAs(UnmanagedType.Bool)]
        private static extern bool CryptProtectData(
            ref DataBlob dataIn,
            string? description,
            IntPtr optionalEntropy,
            IntPtr reserved,
            IntPtr promptStruct,
            uint flags,
            out DataBlob dataOut);

        [DllImport("crypt32.dll", CharSet = CharSet.Unicode, SetLastError = true)]
        [return: MarshalAs(UnmanagedType.Bool)]
        private static extern bool CryptUnprotectData(
            ref DataBlob dataIn,
            IntPtr description,
            IntPtr optionalEntropy,
            IntPtr reserved,
            IntPtr promptStruct,
            uint flags,
            out DataBlob dataOut);

        [DllImport("kernel32.dll", SetLastError = true)]
        private static extern IntPtr LocalFree(IntPtr memory);

        public static string Protect(string value)
        {
            var bytes = Encoding.UTF8.GetBytes(value);
            var protectedBytes = Transform(bytes, protect: true);
            return Convert.ToBase64String(protectedBytes);
        }

        public static string Unprotect(string value)
        {
            var bytes = Convert.FromBase64String(value);
            var plainBytes = Transform(bytes, protect: false);
            return Encoding.UTF8.GetString(plainBytes);
        }

        private static byte[] Transform(byte[] input, bool protect)
        {
            var inputPointer = Marshal.AllocHGlobal(input.Length);
            try
            {
                Marshal.Copy(input, 0, inputPointer, input.Length);
                var inputBlob = new DataBlob
                {
                    Size = input.Length,
                    Data = inputPointer
                };

                DataBlob outputBlob;
                var ok = protect
                    ? CryptProtectData(
                        ref inputBlob,
                        "MailRu Desktop authorization",
                        IntPtr.Zero,
                        IntPtr.Zero,
                        IntPtr.Zero,
                        CryptProtectUiForbidden,
                        out outputBlob)
                    : CryptUnprotectData(
                        ref inputBlob,
                        IntPtr.Zero,
                        IntPtr.Zero,
                        IntPtr.Zero,
                        IntPtr.Zero,
                        CryptProtectUiForbidden,
                        out outputBlob);

                if (!ok)
                    throw new InvalidOperationException(
                        $"Windows DPAPI operation failed with error {Marshal.GetLastWin32Error()}.");

                try
                {
                    var output = new byte[outputBlob.Size];
                    Marshal.Copy(outputBlob.Data, output, 0, outputBlob.Size);
                    return output;
                }
                finally
                {
                    if (outputBlob.Data != IntPtr.Zero)
                        LocalFree(outputBlob.Data);
                }
            }
            finally
            {
                Marshal.FreeHGlobal(inputPointer);
            }
        }
    }
}
