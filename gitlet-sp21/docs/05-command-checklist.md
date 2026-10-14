# Gitlet 命令规范阅读清单

本表帮助你定位规范并检查实现，不能替代原文中的精确英文输出与全部细节。每条命令完成后，打开对应官方段落核对 Usage、Description、Runtime、Failure cases 和输出样例。[官方完整规范](https://sp21.datastructur.es/materials/proj/proj2/proj2)

下列命令统一以 `java gitlet.Main` 为前缀。方括号表示参数占位，实际运行不带方括号。message 有空格时用引号包住，不使用 `-m`。

## 全局规则

- [ ] 保留 `gitlet.Main` 和 main 入口，用 Java 与允许的库实现。
- [ ] 版本状态保存于工作目录的 `.gitlet`，每次命令启动都能恢复。
- [ ] 无参数、未知命令、错误参数、仓库未初始化均按 [Overall Spec](https://sp21.datastructur.es/materials/proj/proj2/proj2#overall-spec)处理。
- [ ] 英文错误内容、句末标点、大小写逐项核对；不输出调试日志。
- [ ] 规范规定的失败不改变其他状态。
- [ ] 处理规范规定的错误时正常结束；如果使用 System.exit，按原文传入 0。不要把预期错误作为 Java 异常堆栈输出。
- [ ] 文件路径兼容不同平台，文件内容按字节保存与恢复。
- [ ] 日志格式、status 排序、空行和英文日期按样例检查。

## 必做命令

| 命令与规范 | 行为检查 | 必须设计的自测 |
| --- | --- | --- |
| [init](https://sp21.datastructur.es/materials/proj/proj2/proj2#init) | 空快照、无父提交、epoch 时间、`initial commit` 消息、master 初始分支；各仓库同一初始提交 ID | 新目录；重复初始化；两个新仓库的初始 ID |
| [add file](https://sp21.datastructur.es/materials/proj/proj2/proj2#add) | 暂存当前内容；重复暂存替换版本；等于 HEAD 时取消增加暂存；取消同名移除暂存 | 新增、更新、不变、改回 HEAD、缺失文件、先 rm 再 add |
| [commit message](https://sp21.datastructur.es/materials/proj/proj2/proj2#commit) | 在父快照上应用 Stage；保存消息时间与父 ID；移动当前分支；清空 Stage；不修改工作文件 | 空消息、无变化、暂存后再编辑、暂存后删除、复用 blob |
| [rm file](https://sp21.datastructur.es/materials/proj/proj2/proj2#rm) | 取消增加暂存；若 HEAD 追踪则暂存移除并删除工作文件；不随意删除未追踪文件 | 仅暂存新增、已追踪、同时暂存和追踪、文件已删、既不追踪也未暂存 |
| [log](https://sp21.datastructur.es/materials/proj/proj2/proj2#log) | 从 HEAD 沿第一父回到初始提交；显示正确格式；merge 提交附双亲 ID 摘要 | 空历史、一条链、已 merge 的链、无额外输出 |
| [global-log](https://sp21.datastructur.es/materials/proj/proj2/proj2#global-log) | 遍历所有已保存提交，不局限当前分支；不要求指定遍历顺序 | 分支上的提交、reset 后不再从 HEAD 可达的提交 |
| [find message](https://sp21.datastructur.es/materials/proj/proj2/proj2#find) | 精确匹配消息；匹配的每个提交 ID 单独一行 | 同消息多提交、含空格消息、完全无匹配 |
| [status](https://sp21.datastructur.es/materials/proj/proj2/proj2#status) | 五个区块；当前分支标记；按名字排序；必做三栏正确；保留后两栏标题 | 空状态、多分支、暂存增加、暂存移除、排序与空行 |
| [checkout -- file](https://sp21.datastructur.es/materials/proj/proj2/proj2#checkout) | 从 HEAD 恢复单个文件；不改引用和 Stage | 修改后恢复、工作文件缺失、HEAD 中没有该文件 |
| [checkout ID -- file](https://sp21.datastructur.es/materials/proj/proj2/proj2#checkout) | 从指定 Commit 恢复单个文件；支持有效唯一短 ID；不改 Stage | 老版本、短 ID、无此 ID、提交中无此文件、参数分隔符错误 |
| [checkout branch](https://sp21.datastructur.es/materials/proj/proj2/proj2#checkout) | 恢复分支目标快照；移除当前追踪且目标无的文件；切换当前分支；成功后清空 Stage；checkout 当前分支失败时保留 Stage | 不存在分支、当前分支、目标增加与删除文件、未追踪覆盖阻挡 |
| [branch name](https://sp21.datastructur.es/materials/proj/proj2/proj2#branch) | 新名字指向当前提交；不切换分支 | 重名、创建后当前分支不变、两分支独立提交 |
| [rm-branch name](https://sp21.datastructur.es/materials/proj/proj2/proj2#rm-branch) | 删除分支引用；保留历史对象 | 名字不存在、删除当前分支、删除其他分支 |
| [reset ID](https://sp21.datastructur.es/materials/proj/proj2/proj2#reset) | 恢复目标快照；移动当前分支引用；清空 Stage；支持短 ID | 老提交、删除多余追踪文件、未追踪阻挡、当前分支名不变 |
| [merge branch](https://sp21.datastructur.es/materials/proj/proj2/proj2#merge) | 最新共同祖先、两种特殊情况、三方比较、冲突、双亲提交 | 见 [Merge 检查](06-merge.md) |

上表中 checkout 的三行是同一命令的三种参数形式。包含 merge 在内，共有 13 个必做命令名。

## 各命令改变的数据

这张表用于检查职责边界。读取但不写入的数据没有列在“变化”中；失败情况应另外验证。

| 操作 | 工作文件 | Stage | 分支与 HEAD | 历史对象 |
| --- | --- | --- | --- | --- |
| init | 不改已有工作文件 | 创建空状态 | 建立初始引用 | 建立初始提交 |
| add | 不改文件内容 | 更新增加或取消移除等 | 不变 | 可以保存新的 blob |
| commit | 不变 | 清空 | 移动当前分支的引用 | 新 Commit 与必要 blob |
| rm | 仅适用条件下删除文件 | 取消增加或登记移除 | 不变 | 不删除旧历史 |
| checkout 单文件 | 恢复那个文件 | 不变 | 不变 | 不变 |
| checkout 分支 | 恢复目标快照 | 清空 | 改当前分支选择 | 不变 |
| branch | 不变 | 不变 | 增加引用 | 不变 |
| rm-branch | 不变 | 不变 | 移除非当前分支引用 | 不变 |
| reset | 恢复目标快照 | 清空 | 移动当前分支引用 | 不删除旧历史 |
| merge | 按文件规则处理 | 正常合并会暂存后提交 | 依特殊情况或合并更新 | 正常合并新增双亲 Commit |
| log global-log find status | 不变 | 不变 | 不变 | 不变 |

## 性能与存储检查

下面是阅读性能要求时的提醒。精确复杂度继续以每条命令原文为准，尤其不要把短 ID 的查找成本与完整 ID 混淆。

| 命令组 | 要避免的问题 |
| --- | --- |
| init branch rm-branch | 为一次引用变更扫描所有提交、所有文件或所有分支 |
| add | 为暂存一个文件读取全部历史；没有按文件大小分析成本 |
| commit | 重新保存所有父历史；把未变化 blob 重复复制；处理时间随提交总数增长 |
| rm | 为移除一个文件遍历全部提交 |
| log | 用目录枚举顺序代替父链；每读一个祖先都重新扫描全部提交 |
| global-log find | 未考虑需要遍历所有提交；漏掉不可从当前 HEAD 到达的对象 |
| status | 扫描与当前状态无关的全部历史 |
| checkout reset | 为完整 ID 查找扫描全部历史；没分析要恢复的目标文件大小 |
| merge | 只分析文件比较，忘了祖先图遍历成本；只沿第一父找共同祖先 |

规范允许在一定条件下忽略序列化时间，但不允许一次序列化的规模依赖历史中全部文件内容。按 blob 内容复用存储可以满足提交的增量存储约束。[Overall Spec](https://sp21.datastructur.es/materials/proj/proj2/proj2#overall-spec)、[commit Runtime](https://sp21.datastructur.es/materials/proj/proj2/proj2#commit)

## 可选完整 status

后两栏的计算在 Spring 2021 是加分。若实现，区分 HEAD、Stage、工作文件三种版本：已追踪未暂存的改变、暂存后又改变、暂存后删除、追踪但未标记移除却缺失，都可能属于未暂存修改。未追踪还包括暂存移除后又重新出现的文件。只考虑工作目录的普通文件，不递归列出子目录。[status 原文](https://sp21.datastructur.es/materials/proj/proj2/proj2#status)

## 可选远程命令

| 命令 | 阅读目标 |
| --- | --- |
| [add-remote name path/.gitlet](https://sp21.datastructur.es/materials/proj/proj2/proj2#add-remote) | 保存名字与路径，处理重名和路径分隔符 |
| [rm-remote name](https://sp21.datastructur.es/materials/proj/proj2/proj2#rm-remote) | 删除连接配置，处理名字不存在 |
| [push remote branch](https://sp21.datastructur.es/materials/proj/proj2/proj2#push) | 判断能否推进远端分支，传递缺少的提交和 blob |
| [fetch remote branch](https://sp21.datastructur.es/materials/proj/proj2/proj2#fetch) | 复制对象并更新本地 remote/branch 引用，不直接替换当前工作文件 |
| [pull remote branch](https://sp21.datastructur.es/materials/proj/proj2/proj2#pull) | 组合 fetch 和 merge，并保留两者的错误情况 |

远程目录不存在、远端分支不存在、远端历史不能直接推进都应有自测。先完成必做功能再决定是否扩展。
