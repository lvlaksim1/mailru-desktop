param(
    [Parameter(Mandatory = $true)] [string]$GatewayRequestPath,
    [Parameter(Mandatory = $true)] [string]$GatewayResultPath
)

$ErrorActionPreference = 'Stop'
Set-StrictMode -Version 2.0

function Write-Result {
    param([string]$Status, [string]$ErrorText = '', [hashtable]$Extra = @{})
    $payload = [ordered]@{ status = $Status; error = $ErrorText; repository = 'lvlaksim1/mailru-desktop' }
    foreach ($key in $Extra.Keys) { $payload[$key] = $Extra[$key] }
    $payload | ConvertTo-Json -Depth 20 | Set-Content -LiteralPath $GatewayResultPath -Encoding UTF8
}

try {
    $request = Get-Content -LiteralPath $GatewayRequestPath -Raw -Encoding UTF8 | ConvertFrom-Json
    $version = [string]$request.args.version
    if ($version -notmatch '^\d+\.\d+\.\d+$') { throw 'args.version must be semantic X.Y.Z' }

    $expectedRepo = 'lvlaksim1/mailru-desktop'
    if ([string]$request.source_repository -ne $expectedRepo) { throw "Unexpected source repository: $($request.source_repository)" }

    $root = (Get-Location).Path
    $project = Join-Path $root 'src\MailRuDesktop.App\MailRuDesktop.App.csproj'
    $fullIss = Join-Path $root 'installer\full.iss'
    $updateIss = Join-Path $root 'installer\update.iss'
    foreach ($required in @($project, $fullIss, $updateIss)) {
        if (-not (Test-Path -LiteralPath $required -PathType Leaf)) { throw "Required project file not found: $required" }
    }

    $dotnet = Get-Command dotnet.exe -ErrorAction SilentlyContinue
    if ($null -eq $dotnet) { $dotnet = Get-Command dotnet -ErrorAction SilentlyContinue }
    if ($null -eq $dotnet) { throw '.NET SDK is not installed on the Windows runner.' }

    $gh = Get-Command gh.exe -ErrorAction SilentlyContinue
    if ($null -eq $gh) { $gh = Get-Command gh -ErrorAction SilentlyContinue }
    if ($null -eq $gh) { throw 'GitHub CLI (gh) is not installed on the Windows runner.' }

    & $gh.Source auth status -h github.com 2>&1 | Write-Host
    if ($LASTEXITCODE -ne 0) { throw 'GitHub CLI is not authenticated on the Windows runner.' }

    $programFilesX86 = [Environment]::GetFolderPath('ProgramFilesX86')
    $programFiles = [Environment]::GetFolderPath('ProgramFiles')
    $isccCandidates = @(
        (Join-Path $programFilesX86 'Inno Setup 6\ISCC.exe'),
        (Join-Path $programFiles 'Inno Setup 6\ISCC.exe'),
        (Join-Path $env:LOCALAPPDATA 'Programs\Inno Setup 6\ISCC.exe')
    ) | Where-Object { $_ -and (Test-Path -LiteralPath $_ -PathType Leaf) }

    if ($isccCandidates.Count -eq 0) {
        $choco = Get-Command choco.exe -ErrorAction SilentlyContinue
        if ($null -eq $choco) { throw 'Inno Setup 6 is not installed and Chocolatey is unavailable.' }
        Write-Host 'Inno Setup 6 not found; installing with Chocolatey...'
        & $choco.Source install innosetup -y --no-progress
        if ($LASTEXITCODE -ne 0) { throw "Chocolatey failed to install Inno Setup (exit $LASTEXITCODE)." }
        $isccCandidates = @(
            (Join-Path $programFilesX86 'Inno Setup 6\ISCC.exe'),
            (Join-Path $programFiles 'Inno Setup 6\ISCC.exe')
        ) | Where-Object { $_ -and (Test-Path -LiteralPath $_ -PathType Leaf) }
    }

    if ($isccCandidates.Count -eq 0) { throw 'Inno Setup 6 compiler ISCC.exe was not found after prerequisite check.' }
    $iscc = [string]$isccCandidates[0]

    $publish = Join-Path $root 'publish'
    $dist = Join-Path $root 'dist'
    if (Test-Path -LiteralPath $publish) { Remove-Item -LiteralPath $publish -Recurse -Force }
    if (Test-Path -LiteralPath $dist) { Remove-Item -LiteralPath $dist -Recurse -Force }
    New-Item -ItemType Directory -Force -Path $publish, $dist | Out-Null

    Write-Host "Publishing MailRu Desktop v$version..."
    $publishArgs = @('publish', $project, '-c', 'Release', '-r', 'win-x64', '--self-contained', 'true', '-p:PublishSingleFile=false', '-p:DebugType=None', '-p:DebugSymbols=false', "-p:Version=$version", '-o', $publish)
    & $dotnet.Source @publishArgs
    if ($LASTEXITCODE -ne 0) { throw "dotnet publish failed with exit code $LASTEXITCODE." }

    Write-Host 'Building full installer...'
    $fullArgs = @($fullIss, "/DAppVersion=$version", "/DPublishDir=$publish", "/DOutputDir=$dist")
    & $iscc @fullArgs
    if ($LASTEXITCODE -ne 0) { throw "Full installer build failed with exit code $LASTEXITCODE." }

    Write-Host 'Building update installer...'
    $updateArgs = @($updateIss, "/DAppVersion=$version", "/DPublishDir=$publish", "/DOutputDir=$dist")
    & $iscc @updateArgs
    if ($LASTEXITCODE -ne 0) { throw "Update installer build failed with exit code $LASTEXITCODE." }

    $setup = Join-Path $dist "MailRuDesktop_Setup_v$version.exe"
    $update = Join-Path $dist "MailRuDesktop_Update_v$version.exe"
    foreach ($binary in @($setup, $update)) {
        if (-not (Test-Path -LiteralPath $binary -PathType Leaf)) { throw "Installer was not produced: $binary" }
        if ((Get-Item -LiteralPath $binary).Length -lt 1MB) { throw "Installer is unexpectedly small: $binary" }
    }

    $setupHash = (Get-FileHash -LiteralPath $setup -Algorithm SHA256).Hash.ToLowerInvariant()
    $updateHash = (Get-FileHash -LiteralPath $update -Algorithm SHA256).Hash.ToLowerInvariant()

    $tag = "v$version"
    $existing = & $gh.Source release view $tag --repo $expectedRepo --json tagName 2>$null
    if ($LASTEXITCODE -eq 0 -and -not [string]::IsNullOrWhiteSpace([string]$existing)) {
        Write-Host "Removing an existing incomplete release $tag before publishing..."
        & $gh.Source release delete $tag --repo $expectedRepo --yes
        if ($LASTEXITCODE -ne 0) { throw "Could not delete existing release $tag." }
    }

    Write-Host "Publishing GitHub release $tag..."
    $notes = "MailRu Desktop $tag. Use Setup for first installation and Update for an existing installation."
    $releaseArgs = @('release','create',$tag,$setup,$update,'--repo',$expectedRepo,'--target',[string]$request.source_ref,'--title',"MailRu Desktop $tag",'--notes',$notes)
    & $gh.Source @releaseArgs
    if ($LASTEXITCODE -ne 0) { throw "gh release create failed with exit code $LASTEXITCODE." }

    $releaseJson = & $gh.Source release list --repo $expectedRepo --limit 100 --json tagName
    if ($LASTEXITCODE -ne 0) { throw 'Could not enumerate releases for retention cleanup.' }
    $releases = $releaseJson | ConvertFrom-Json
    foreach ($release in $releases) {
        $oldTag = [string]$release.tagName
        if ($oldTag -and $oldTag -ne $tag) {
            Write-Host "Deleting old binary release $oldTag..."
            & $gh.Source release delete $oldTag --repo $expectedRepo --yes
            if ($LASTEXITCODE -ne 0) { throw "Failed to prune old release $oldTag." }
        }
    }

    $base = "https://github.com/$expectedRepo/releases/download/$tag"
    Write-Result -Status 'ok' -Extra @{
        version = $version
        source_ref = [string]$request.source_ref
        setup_url = "$base/MailRuDesktop_Setup_v$version.exe"
        update_url = "$base/MailRuDesktop_Update_v$version.exe"
        setup_sha256 = $setupHash
        update_sha256 = $updateHash
        setup_bytes = (Get-Item -LiteralPath $setup).Length
        update_bytes = (Get-Item -LiteralPath $update).Length
    }

    Write-Host "RELEASED $tag"
    Write-Host "UPDATE: $base/MailRuDesktop_Update_v$version.exe"
    Write-Host "SETUP:  $base/MailRuDesktop_Setup_v$version.exe"
    exit 0
}
catch {
    $message = $_.Exception.Message
    Write-Error $message
    try { Write-Result -Status 'error' -ErrorText $message } catch {}
    exit 1
}
