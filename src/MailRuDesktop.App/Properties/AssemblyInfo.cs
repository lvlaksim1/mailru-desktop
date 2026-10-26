using System.Runtime.CompilerServices;

// Permit the local Windows UI regression runner to exercise the manual
// nineteen-mailbox selector without changing runtime visibility or making
// any network requests.
[assembly: InternalsVisibleTo("WindowsUiSmoke")]
