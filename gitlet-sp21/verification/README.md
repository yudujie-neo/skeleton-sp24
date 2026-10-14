# Gitlet 学习资料核验记录

首次核验：2026 年 10 月 12 日；资料与工具复核：2026 年 10 月 13 日。修正内容与复核范围见 [审计记录](audit-2026-10-13.md)。

## 来源与文档

- 官方骨架与测试资源共 41 个文件，下载时逐个与固定版本的 Git blob SHA-1 核对，并记录 SHA-256。
- 中文文档覆盖环境、模型、分阶段路线、13 个必做命令及 checkout 三种形式、merge、测试验收、设计模板，并为加分功能提供独立入口。
- 本地链接、官方规范锚点、文档格式和核心网址的实际检查数量与结果见 [materials-audit.json](materials-audit.json)。

## 编译与测试入口

使用本机 `C:\Users\18335\.jdks\ms-17.0.18` 的 JDK 17 和 `C:\Python314\python.exe`，执行了：

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\gitlet-sp21\tools\check.ps1 -Mode Samples
```

Gitlet 的全部官方 Java 源文件编译成功。测试器实际启动了 4 个样例测试，结果为 **4 个已运行，0 个通过**。原因是收集的骨架保持未实现：init 尚不创建仓库，文件恢复与 log 也尚未实现。该结果证明编译与测试入口能够工作，不证明 Gitlet 功能完成。

核对了官方 tester.py 的命令说明与比较函数。辅助脚本将编辑距离容差设为 0，但官方比较仍会归一化部分空白和换行；这不是逐字节检查。相关回归测试已验证这一行为。

核验工具的 11 项回归检查通过。辅助启动脚本的 8 个实际调用场景通过预期结果核对，包括无 JDK 的 Help、无 Python 的 Compile、错误 JDK、无匹配或错误测试输入，以及未实现骨架的预期失败。详细调用记录见 [launcher-audit.json](launcher-audit.json)。这些是资料工具检查，不是 Gitlet 实现的 11 个或 8 个功能测试。

## 验证范围

没有实现 Gitlet 解答，没有进行官方隐藏评测、Gradescope 提交或 staff 参考实现操作。tester.py 的 `--debug` 输入 n 路径已实际运行；连接 IntelliJ 并输入 s 的 Remote JVM Debug、runner.py 的交互调试流程、IntelliJ 图形界面配置和视频播放未实际操作。

本次新增内容都在 `gitlet-sp21` 目录。工作区中原有 Discussion、lab05、lab06、lab07 等未提交内容未修改，也未执行 commit 或 push。
