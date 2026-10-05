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
OutputDir={#OutputDir}
OutputBaseFilename=MailRuDesktop_Update_v{#AppVersion}
Compression=lzma2/max
SolidCompression=yes
WizardStyle=modern
PrivilegesRequired=lowest
ArchitecturesAllowed=x64compatible
ArchitecturesInstallIn64BitMode=x64compatible
UninstallDisplayIcon={app}\\{#AppExeName}
CloseApplications=yes
RestartApplications=no
SetupLogging=yes
VersionInfoVersion={#AppVersion}
VersionInfoProductName={#AppName}
VersionInfoDescription=MailRu Desktop update installer

[Files]
Source: "{#PublishDir}\\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[Run]
Filename: "{app}\\{#AppExeName}"; Description: "Запустить MailRu Desktop"; Flags: nowait postinstall skipifsilent

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
