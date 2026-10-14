param(
    [ValidateSet('Compile', 'Samples', 'All', 'Help')]
    [string]$Mode = 'Compile',
    [string]$Jdk = '',
    [string[]]$Tests = @(),
    [switch]$Keep
)
$ErrorActionPreference = 'Stop'
$taskRoot = Split-Path -Parent $PSScriptRoot
$projectRoot = Join-Path $taskRoot 'proj2'
$classes = Join-Path $projectRoot 'out\classes'
$originalPath = $env:PATH
$originalClasspath = $env:CLASSPATH
$resultCode = 0
try {
    if (($Tests.Count -gt 0 -or $Keep) -and $Mode -in @('Compile', 'Help')) {
        throw '-Tests and -Keep are only valid with Samples or All.'
    }
    if ($Mode -ne 'Compile' -and -not (Get-Command python -ErrorAction SilentlyContinue)) {
        throw 'Python 3 not found on PATH.'
    }
    if ($Mode -eq 'Help') {
        & python (Join-Path $projectRoot 'testing\tester.py') "--progdir=$classes"
        $resultCode = $LASTEXITCODE
    } else {
    if ($Jdk) {
        foreach ($executable in @('java.exe', 'javac.exe')) {
            if (-not (Test-Path -LiteralPath (Join-Path $Jdk "bin\$executable"))) {
                throw "Invalid -Jdk directory: missing bin\$executable"
            }
        }
        $env:PATH = (Join-Path $Jdk 'bin') + ';' + $originalPath
    } elseif (-not (Get-Command javac -ErrorAction SilentlyContinue)) {
        $candidate = Join-Path $env:USERPROFILE '.jdks\ms-17.0.18'
        if (Test-Path -LiteralPath (Join-Path $candidate 'bin\javac.exe')) {
            $env:PATH = (Join-Path $candidate 'bin') + ';' + $originalPath
        }
    }
    if (-not (Get-Command javac -ErrorAction SilentlyContinue)) {
        throw 'JDK not found. Use -Jdk with the directory containing bin/java.exe and bin/javac.exe.'
    }
    if (-not (Get-Command java -ErrorAction SilentlyContinue)) {
        throw 'Java launcher not found on PATH.'
    }
    New-Item -ItemType Directory -Force -Path $classes | Out-Null
    # Never let removed sources be satisfied by stale compiled classes.
    $resolvedClasses = (Resolve-Path -LiteralPath $classes).Path
    $classesPrefix = $resolvedClasses.TrimEnd('\') + '\'
    $oldClassPaths = @()
    foreach ($oldClass in @(Get-ChildItem -LiteralPath $classes -Recurse -File -Filter '*.class')) {
        $oldPath = (Resolve-Path -LiteralPath $oldClass.FullName).Path
        if (-not $oldPath.StartsWith($classesPrefix, [StringComparison]::OrdinalIgnoreCase)) {
            throw "Refusing to remove an output file outside the classes directory: $oldPath"
        }
        $oldClassPaths += $oldPath
    }
    foreach ($oldPath in $oldClassPaths) {
        Remove-Item -LiteralPath $oldPath -Force
    }
    $javaFiles = @(Get-ChildItem -LiteralPath (Join-Path $projectRoot 'gitlet') -Filter '*.java' | ForEach-Object { $_.FullName })
    # This starter uses only the Java standard library. Compile all sources each time.
    & javac -encoding UTF-8 -proc:none -g -cp $classes -d $classes @javaFiles
    if ($LASTEXITCODE -ne 0) { throw 'Compilation failed; tests were not run.' }
    Write-Host "Compiled Gitlet to $classes"
    if ($Mode -ne 'Compile') {
        Push-Location (Join-Path $projectRoot 'testing')
        try {
            $env:CLASSPATH = $classes
            $testerArgs = @('tester.py', "--progdir=$classes", '--tolerance=0')
            if ($Mode -ne 'Help') {
                $testerArgs += '--verbose'
                if ($Keep) { $testerArgs += '--keep' }
                $testFiles = @()
                if ($Tests.Count -gt 0) {
                    foreach ($pattern in $Tests) {
                        $matches = @(Get-ChildItem -Path $pattern -File -ErrorAction Stop)
                        if ($matches.Count -eq 0) { throw "Test pattern matched no files: $pattern" }
                        if (@($matches | Where-Object { $_.Extension -ne '.in' }).Count -gt 0) {
                            throw "Only .in test files may be selected: $pattern"
                        }
                        $testFiles += @($matches | ForEach-Object { $_.FullName })
                    }
                } else {
                    $testFiles += @(Get-ChildItem -LiteralPath 'samples' -Filter '*.in' | ForEach-Object { $_.FullName })
                    if ($Mode -eq 'All') {
                        $testFiles += @(Get-ChildItem -LiteralPath 'student_tests' -Filter '*.in' | ForEach-Object { $_.FullName })
                    }
                }
                if ($testFiles.Count -eq 0) { throw 'No .in tests selected.' }
                $testerArgs += @($testFiles | Sort-Object -Unique)
            }
            & python @testerArgs
            $resultCode = $LASTEXITCODE
        } finally { Pop-Location }
    }
    }
} finally {
    $env:PATH = $originalPath
    $env:CLASSPATH = $originalClasspath
}
exit $resultCode
