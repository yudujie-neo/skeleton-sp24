# Gitlet 测试调试与完成验收

Gitlet 的核心测试单位是一串独立命令和命令之间的文件变化。每次 Java 启动都会重新读取仓库，因此只测试内存中的方法调用不足以证明持久化正确。[官方 Testing](https://sp21.datastructur.es/materials/proj/proj2/proj2#testing)

## 官方样例分别证明什么

| 文件 | 主要场景 |
| --- | --- |
| [test01-init.in](../proj2/testing/samples/test01-init.in) | init 创建 .gitlet |
| [test02-basic-checkout.in](../proj2/testing/samples/test02-basic-checkout.in) | 添加并提交，然后从 HEAD 恢复文件 |
| [test03-basic-log.in](../proj2/testing/samples/test03-basic-log.in) | 两个提交的 log 输出 |
| [test04-prev-checkout.in](../proj2/testing/samples/test04-prev-checkout.in) | 捕获历史 ID，恢复两个提交中的不同版本 |

四个样例通过后，还不能认为删除、分支、reset、merge、错误处理都正确。自行编写的测试放在 `proj2/testing/student_tests`，文件内容样本放在 `proj2/testing/src`，与官方样例区分。

## 测试语言入门

查看 [tester.py 自带说明](../proj2/testing/tester.py)和 [官方集成测试讲解](https://sp21.datastructur.es/materials/proj/proj2/proj2#understanding-integration-tests)。常用操作如下：

| 测试语法 | 作用 |
| --- | --- |
| `> command arguments` 到 `<<<` | 运行一次 Gitlet 命令，并检查这两行之间的预期输出 |
| `<<<*` | 使用正则匹配输出，适合动态 ID 和日期 |
| `+ target source` | 把 src 中的样本内容复制到测试工作目录的 target |
| `- name` | 直接删除工作文件，模拟用户在 Gitlet 之外删除 |
| `= target source` | 按文本读取目标文件与 src 样本，归一化换行后比较 |
| `E name` | 检查文件或目录存在 |
| `* name` | 检查文件或目录不存在 |
| `I file.inc` | 包含相对于当前测试文件的公共准备步骤 |
| `D NAME "value"` | 定义后续测试可引用的变量 |

下面是自行编写的入门例子，使用已经收集的样本文件。可保存为 `student_tests/my01-restore.in`，等对应功能实现后运行：

```text
# Save one version, overwrite it externally, then restore HEAD.
> init
<<<
+ note.txt wug.txt
> add note.txt
<<<
> commit "save note"
<<<
+ note.txt notwug.txt
> checkout -- note.txt
<<<
= note.txt wug.txt
```

这里 `> add` 的空预期输出是检查点，`= note.txt wug.txt` 检查文件恢复。别只比较终端输出而漏掉磁盘文件变化。

## 动态 ID 和正则

参考官方 [test04](../proj2/testing/samples/test04-prev-checkout.in) 的捕获方式：包含 definitions.inc，运行 log，使用 COMMIT_HEAD 匹配动态 ID，再用 D 定义变量供后续 checkout 或 reset 使用。你自己的 tests 目录也有 [definitions.inc](../proj2/testing/student_tests/definitions.inc)。

正则预期输出中，status 的字面星号需要转义；普通匹配则不需要。默认测试器有编辑距离容差，本目录辅助脚本显式设为 0，关闭普通输出的近似匹配。正则应该只放宽动态信息，不要把需要检查的文件名、消息或状态整段匹配为任意内容。

官方 `tester.py` 的 `correctProgramOutput` 还会处理行尾空白、行首空白以及整体末尾空白；正则匹配也允许忽略整体末尾空白。设为 0 并不会取消这些处理。`correctFileOutput` 使用文本模式读取，并去掉 `\r`；LF 与 CRLF 的字节不同可能仍然通过 `=` 检查。因此输出空行继续按规范人工核对，文件精确保真使用额外的字节比较。

例如，给两个真实文件路径运行下面的检查；退出码 0 表示字节相同，1 表示不同：

```powershell
python -c "from pathlib import Path; import sys; sys.exit(0 if Path(sys.argv[1]).read_bytes() == Path(sys.argv[2]).read_bytes() else 1)" '实际文件路径' '预期文件路径'
if ($LASTEXITCODE -ne 0) { throw 'File bytes differ.' }
```

空文件、无结尾换行、CRLF 和非 ASCII 内容等自测尤其需要这样的检查。不要把文本测试通过描述成逐字节验证通过。

## 调试失败的步骤

1. 用 `check.ps1 -Mode Samples -Tests '具体测试' -Keep` 重新编译并保留失败目录。
2. 从测试最开始逐条看每次命令的输出和状态；确定首次偏离发生在哪里。
3. 用下面的 `tester.py --debug` 在新的测试目录从头执行，走到需要调试的命令时输入 `s`，连接 IntelliJ 的 Remote JVM Debug。
4. 观察读取后的 Stage、HEAD、Commit 和要写回的数据；必要时在序列化前检查对象字段。
5. 修复后重跑原失败测试，再跑受到同一逻辑影响的测试。

status 结果错误可能来自上一次 add 没写回 Stage。checkout 内容错误可能来自更早的 commit 保存错版本。失败测试的最后一个命令不一定是错误起点。

`--keep` 保留的是测试停止后的状态。直接在这个目录再次运行 commit、reset 或 merge，可能已经无法复现第一次的输入状态。手动用 Application 配置复现时，应在新目录执行测试前缀，准备好错误命令运行前的状态。

```powershell
# 先按环境指南编译，并让当前终端能调用 java
Set-Location 'E:\cs61b\skeleton-sp24\gitlet-sp21\proj2\testing'
$gitletClasses = (Resolve-Path '..\out\classes').Path
python .\tester.py "--progdir=$gitletClasses" --tolerance=0 --debug --verbose --keep samples/test02-basic-checkout.in
```

提示出现时，`n` 执行下一条命令，`s` 让该次 Java 启动等待调试器。IntelliJ 中使用 Remote JVM Debug，主机 localhost，端口 5005，并在与编译结果对应的源码处设置断点；连接后恢复执行。一次测试中的不同命令是不同 Java 进程，后续输入 `s` 时需重新连接。这条入口无需 `library-sp21`。

序列化对象不适合直接用文本编辑器读。骨架中 [DumpObj.java](../proj2/gitlet/DumpObj.java) 和 [Dumpable.java](../proj2/gitlet/Dumpable.java) 提供可选调试接口；先读 Javadoc，再决定是否让自己的类实现该接口。[官方 Serialization Details](https://sp21.datastructur.es/materials/proj/proj2/proj2#serialization-details)

## 官方 runner 调试入口

`runner.py` 用于逐条运行和调试集成测试，会自行编译，并寻找课程库。其默认布局包含 `library-sp21/javalib` 和 REPO_DIR。当前目录未收集这套库，不能把默认入口当成已经配置好的工具。

如果你在本机使用已有 `E:\cs61b\library-sp24`，可显式提供库目录，例如在 `proj2/testing` 中执行：

```powershell
# 先按环境指南让当前终端能调用 java 和 javac
python .\runner.py --lib='E:\cs61b\library-sp24' --debug --keep --verbose samples/test02-basic-checkout.in
```

`--debug` 才启用逐条交互；仅 `--keep --verbose` 不会停下来等待输入。这里只借用现有库目录供调试器定位，不改变 Spring 2021 的项目要求。runner 的交互调试流程没有在本次实际操作，使用时继续参考 [Lab 6 调试说明](https://sp21.datastructur.es/materials/lab/lab6/lab6)。优先使用上面的 tester.py 调试入口，它直接使用已经编译的 classes。

## 必做功能验收矩阵

每一行至少记录一个你自己的测试，尤其要包含失败后状态没有变化的断言。下面是需要设计的场景，不是已经通过的结果。

| 类别 | 建议场景 | 状态 |
| --- | --- | --- |
| 参数校验 | 无命令、未知命令、数量错误、checkout 缺少正确分隔符 | 未验证 |
| 初始化 | 首次、重复、两个新仓库初始 ID 相同 | 未验证 |
| add | 新文件、不变文件、覆盖暂存、恢复原内容、缺失文件 | 未验证 |
| commit | 空消息、无变化、暂存后修改、暂存后删除、继承父快照 | 未验证 |
| rm | 仅暂存、仅追踪、同时追踪暂存、外部已删除、无删除理由 | 未验证 |
| 查询 | log 第一父、global-log 全部对象、find 多匹配与无匹配 | 未验证 |
| status | 空状态、排序、当前分支标识、增加和移除列表、五栏与空行 | 未验证 |
| 单文件 checkout | HEAD、完整 ID、短 ID、无 ID、快照无文件、Stage 不变 | 未验证 |
| 分支 | 创建不切换、重名、删除不存在或当前分支、历史仍存在 | 未验证 |
| 分支 checkout | 快照新增与移除、Stage 清空、当前分支、未追踪阻挡 | 未验证 |
| reset | 快照恢复、引用移动、Stage 清空、短 ID、覆盖阻挡 | 未验证 |
| merge 特殊情况 | Stage 非空、无分支、自身、祖先、fast-forward | 未验证 |
| merge 文件 | 单侧改动、同样改动、双删、独立新增、修改对删除、不同修改 | 未验证 |
| merge 历史 | 双亲、第一父 log、再次合并、第二父参与祖先搜索 | 未验证 |
| 字节与换行 | 空文件、无末尾换行、CRLF、非 ASCII 文件内容 | 未验证 |
| 失败原子性 | checkout reset merge 的阻挡发生时未部分改写文件 | 未验证 |
| 存储与性能 | blob 复用、旧快照不变、没有序列化整个历史 | 未验证 |

## 最后交付给自己的材料

- [ ] 最新 Java 源码和完整设计文档。
- [ ] 覆盖所有必做命令的 .in 测试与必要 src 样本。
- [ ] 编译命令、测试命令、实际测试数量和结果。
- [ ] 必做部分与加分部分的实现范围。
- [ ] 未完成或未验证的边界情况。

若你没有可用的 Gradescope 或教学账户，把验收描述为“本地测试通过”。不要把历史评分规则、自写测试数量或四个样例通过写成官方满分。
