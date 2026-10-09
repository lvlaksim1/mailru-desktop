#ifndef AppVersion
  #define AppVersion "0.0.0-dev"
#endif
#ifndef PublishDir
  #define PublishDir "..\\publish"
#endif
#ifndef OutputDir
  #define OutputDir "..\\dist"
#endif

#define AppName "MailRu Desktop"
#define AppExeName "MailRuDesktop.App.exe"
#define AppId "{{6F2B6B69-2F23-4A35-9A57-5D1A730FDFA3}"

[Setup]
AppId={#AppId}
AppName={#AppName}
AppVersion={#AppVersion}
AppPublisher=lvlaksim1
AppPublisherURL=https://github.com/lvlaksim1/mailru-desktop
AppSupportURL=https://github.com/lvlaksim1/mailru-desktop/issues
DefaultDirName={localappdata}\\Programs\\MailRuDesktop
DisableDirPage=yes
DisableProgramGroupPage=yes
DisableWelcomePage=yes
DisableReadyPage=yes
DisableFinishedPage=yes
DirExistsWarning=no
UsePreviousAppDir=no
OutputDir={#OutputDir}
OutputBaseFilename=MailRuDesktop_Update_v{#AppVersion}
Compression=lzma2/max
SolidCompression=yes
WizardStyle=modern
SetupIconFile=..\\src\\MailRuDesktop.App\\app.ico
PrivilegesRequired=lowest
ArchitecturesAllowed=x64compatible
ArchitecturesInstallIn64BitMode=x64compatible
UninstallDisplayIcon={app}\\{#AppExeName}
CloseApplications=yes
RestartApplications=no
SetupLogging=no
VersionInfoVersion={#AppVersion}
VersionInfoProductName={#AppName}
VersionInfoDescription=MailRu Desktop update installer

[Files]
Source: "{#PublishDir}\\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

; Updater never deletes or recreates shortcuts. Existing Explorer desktop
; icon placement is preserved. Full installer creates icons on first install.

[Registry]
Root: HKCU; Subkey: "Software\\MailRuDesktop"; Flags: uninsdeletekey

[UninstallDelete]
Type: filesandordirs; Name: "{localappdata}\\MailRuDesktop"
Type: filesandordirs; Name: "{userappdata}\\MailRuDesktop"
Type: files; Name: "{autoprograms}\\MailRu Desktop.lnk"
Type: files; Name: "{userdesktop}\\MailRu Desktop.lnk"

[Run]
Filename: "{app}\\{#AppExeName}"; Flags: nowait

[Code]
function InitializeSetup(): Boolean;
var
  InstalledExe: String;
begin
  InstalledExe := ExpandConstant('{localappdata}\\Programs\\MailRuDesktop\\{#AppExeName}');
  if not FileExists(InstalledExe) then
  begin
    MsgBox('MailRu Desktop не найден.' + #13#10 +
      'Для первой установки используйте полный установщик MailRuDesktop_Setup.',
      mbError, MB_OK);
    Result := False;
    exit;
  end;
  Result := True;
end;
