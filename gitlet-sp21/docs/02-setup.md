# Gitlet Windows 环境与启动

本地项目目录是 `E:\cs61b\skeleton-sp24\gitlet-sp21\proj2`。这里的 Java 源码采用 `gitlet` 包，源码路径是 `proj2/gitlet/*.java`。先让命令行工作，再用 IntelliJ 调试。

## 当前环境

资料整理时发现本机有 `C:\Users\18335\.jdks\ms-17.0.18` 和 `C:\Users\18335\.jdks\openjdk-25.0.1`。当前终端 PATH 可以找到 Python 3，但不能直接找到 `java`、`javac`、`make`。因此以下步骤使用已有 JDK 17，并提供不依赖 make 的路径。这是本机的检查结果，换电脑后需要调整路径。

官方 Makefile 和 pom.xml 按 `library-sp21/javalib` 布局寻找课程依赖；本目录只收集 proj2 与 lab6，没有收集整套 library-sp21。现有 Gitlet 骨架仅依赖 Java 标准库，可以直接 javac 编译。以后自写 JUnit 测试时，再单独配置 JUnit 依赖。

## 用辅助脚本编译与测试

打开 PowerShell，在仓库根目录执行：

```powershell
Set-Location 'E:\cs61b\skeleton-sp24'
powershell -NoProfile -ExecutionPolicy Bypass -File .\gitlet-sp21\tools\check.ps1 -Mode Compile
```

这里的执行策略参数只用于本次子进程，不修改电脑的全局策略。若换了 JDK，给脚本传入 `-Jdk '你的JDK目录'`。

脚本编译前会清理 `proj2/out/classes` 内旧的 `.class` 生成物，避免删除或改名的源文件仍靠旧类型通过编译；源码不会被清理。下文的手动 javac 命令不会自动清理旧输出，删除或重命名 Java 类型后优先使用脚本编译。

```powershell
# 每次都先重新编译，再运行 4 个官方样例
powershell -NoProfile -ExecutionPolicy Bypass -File .\gitlet-sp21\tools\check.ps1 -Mode Samples

# 官方样例加 testing/student_tests 下你自己写的测试
powershell -NoProfile -ExecutionPolicy Bypass -File .\gitlet-sp21\tools\check.ps1 -Mode All

# 只运行一个测试并保留测试目录
powershell -NoProfile -ExecutionPolicy Bypass -File .\gitlet-sp21\tools\check.ps1 -Mode Samples -Tests 'samples/test02-basic-checkout.in' -Keep

# 查看官方测试语言说明
powershell -NoProfile -ExecutionPolicy Bypass -File .\gitlet-sp21\tools\check.ps1 -Mode Help
```

`-Tests` 路径相对于 `proj2/testing`，也可用通配符；每个模式都必须匹配到 `.in` 文件。`-Tests` 和 `-Keep` 只用于 Samples 或 All。Compile 只需 JDK；Help 只需 Python，不会编译源码。

脚本用 `--tolerance=0` 关闭普通输出比较的编辑距离容差，但官方测试器仍会归一化部分空白与换行，不能用它证明逐字符或逐字节完全一致。核对方法见 [测试调试指南](07-testing.md)。骨架未完成时样例测试失败是预期结果。

## 手动编译

以下环境变量只影响当前 PowerShell 会话。不要把输出目录当作 Gitlet 的工作目录。

```powershell
$gitletJdk = 'C:\Users\18335\.jdks\ms-17.0.18'
$env:PATH = "$gitletJdk\bin;$env:PATH"
java -version
javac -version
python --version

Set-Location 'E:\cs61b\skeleton-sp24\gitlet-sp21\proj2'
New-Item -ItemType Directory -Force .\out\classes | Out-Null
$gitletSources = @(Get-ChildItem .\gitlet\*.java | ForEach-Object { $_.FullName })
javac -encoding UTF-8 -proc:none -g -d .\out\classes @gitletSources
```

看到编译失败后先修复，不要继续运行旧 `.class`。

## 在单独目录手动练习

Gitlet 使用 `System.getProperty("user.dir")` 判断工作目录。你从哪里启动 Java，哪里就是本次命令读写的工作目录，而不是源码所在的位置。

```powershell
$gitletClasses = 'E:\cs61b\skeleton-sp24\gitlet-sp21\proj2\out\classes'
New-Item -ItemType Directory -Force 'E:\cs61b\skeleton-sp24\gitlet-sp21\practice\first' | Out-Null
Set-Location 'E:\cs61b\skeleton-sp24\gitlet-sp21\practice\first'
java -cp $gitletClasses gitlet.Main init
java -cp $gitletClasses gitlet.Main log
Get-ChildItem -Force
```

这些命令要等你实现相应功能后才有预期行为。恢复与删除命令的练习也放在 `practice` 中，只操作临时练习文件。反复试验时新建 `practice/second` 等目录即可。

## 手动运行官方测试器

先按上面的步骤编译，再执行：

```powershell
Set-Location 'E:\cs61b\skeleton-sp24\gitlet-sp21\proj2\testing'
$gitletClasses = (Resolve-Path '..\out\classes').Path
python .\tester.py "--progdir=$gitletClasses" --tolerance=0 --verbose --keep samples/test02-basic-checkout.in

# PowerShell 不保证给 Python 自动展开通配符，显式收集测试文件
$gitletTests = @(Get-ChildItem .\samples\*.in | ForEach-Object { $_.FullName })
python .\tester.py "--progdir=$gitletClasses" --tolerance=0 --verbose @gitletTests
```

`tester.py` 不会替你重新编译。它启动多次 Java 进程，检查输出和文件内容；这个特点恰好能验证持久化。[官方测试说明](https://sp21.datastructur.es/materials/proj/proj2/proj2#testing)

## IntelliJ 使用方法

可以用 IntelliJ 单独打开 `gitlet-sp21/proj2`，或把它加入现有工程作为模块。为该模块选择 JDK 17，并把 `proj2` 设为 Sources Root，使 `gitlet.Main` 对应 `gitlet/Main.java`。项目只使用标准库时，先按普通 Java 模块配置；官方 pom 引用未收集的父 pom，直接导入 Maven 可能提示缺少 `library-sp21`。

创建 Application 配置：Main class 填 `gitlet.Main`，Program arguments 填 `init`、`log` 等一次命令，Working directory 填某个 `practice` 子目录。下一次命令仍选择同一工作目录。

`--keep` 留下的是测试停止后的目录，不一定是错误命令运行前的状态。要调试那一次命令，优先重新运行 `tester.py --debug`，在新测试目录中执行准备步骤后连接 Remote JVM Debug；或在新的练习目录中重新执行该命令之前的测试步骤。不要直接在失败后的目录里重复 commit、reset 或 merge，把第二次运行当成第一次的复现。[具体步骤](07-testing.md)

IntelliJ 的输出目录和 `check.ps1` 的输出目录可以不同。命令行测试始终使用脚本编译的 `proj2/out/classes`，修改后要重新编译。

## 常见环境问题

| 现象 | 优先检查 |
| --- | --- |
| 找不到 javac | JDK 路径是否存在，是否包含 bin/javac.exe |
| 找不到 gitlet.Main | `-cp` 指向的是包含 `gitlet/Main.class` 的父目录 |
| 骨架调用时崩溃或没有输出 | Main.java 仍有 TODO；先实现参数校验和命令 |
| make 不存在 | 使用本目录的 PowerShell 脚本 |
| Maven 找不到父项目 | 原始 pom 期待 library-sp21；先按普通 Java 模块学习 |
| Python 显示运行 0 个测试 | 路径、工作目录或通配符没有匹配；不能当作通过 |
| 改完代码结果没变化 | 重新编译，检查测试使用的 classes 路径 |
| Windows 成功，其他系统路径失败 | 用 Utils.join、File 构造器或 Path，不硬编码分隔符 |

官方调试器 `runner.py` 还需要课程库布局，且会自行编译。它与 `tester.py` 的参数不同。具体用法见 [测试调试指南](07-testing.md)。
