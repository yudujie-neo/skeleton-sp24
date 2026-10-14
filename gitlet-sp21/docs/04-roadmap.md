# Gitlet 分阶段实现路线

这是一条建议的实现顺序。每阶段先读对应官方段落，再更新设计文档，再写实现和测试。阶段验收说明你可以继续下一步，并不代表通过官方隐藏评测。

## 阶段一 参数与持久化准备

阅读 [Overall Spec](https://sp21.datastructur.es/materials/proj/proj2/proj2#overall-spec)、[Lab 6](https://sp21.datastructur.es/materials/lab/lab6/lab6) 和 `Utils.java`。建立命令分派，识别无参数、未知命令、参数格式错误和仓库未初始化的情况。

写清 Commit、Stage、HEAD、分支引用的内存与磁盘表示。参数校验要处理 checkout 的三种形式，不能只按命令名做统一参数数量判断。

**验收：** 能说出每种状态下一次进程如何读取；错误输入不产生额外文件或调试输出。

## 阶段二 初始化与线性历史

实现 `init`、`log`，先不处理普通文件内容。核对默认分支、初始提交消息、epoch 时间、初始化重复调用，以及 log 的排序方向和日期格式。

**验收：** 在一个练习目录运行 init，结束进程后再运行 log，能看到初始提交；重复 init 不覆盖现有状态。日期可以显示本地时区，但必须表示同一初始时刻。

## 阶段三 暂存 提交 文件恢复

实现 `add`、`commit`、`checkout -- file`、`checkout ID -- file`。先支持完整 ID，再支持短 ID。

至少推演：新增文件、修改文件、不变文件重复 add、add 后继续编辑、add 后删除工作文件，以及恢复旧提交中的单个文件。

**验收：** [四个官方样例](07-testing.md)均通过；自写测试证明 commit 使用暂存时的内容。这个阶段对应 [2021 checkpoint 的功能范围](https://sp21.datastructur.es/materials/proj/proj2/proj2#checkpoint-grader)。

## 阶段四 删除 全局查询 状态

实现 `rm`、`global-log`、`find`、基础 `status`。区分解除暂存与把已追踪文件标记移除；区分当前分支历史和全部提交对象。

先完成 status 的五个区块和基础三栏，后两栏可先保留空内容。按 Spring 2021 规范，完整计算后两栏是加分部分。[status 规范](https://sp21.datastructur.es/materials/proj/proj2/proj2#status)

**验收：** 删除一个追踪文件后，新提交不再追踪它，旧提交仍能恢复；相同消息的多个提交都能被 find 找到；status 顺序与空行正确。

## 阶段五 分支和整个快照的恢复

实现 `branch`、`rm-branch`、`checkout branch`、`reset ID`。把“恢复目标快照”设计成可复用的操作，同时明确命令之间的指针差别。

先检查阻挡的未追踪文件，再写入目标文件、移除应删除的已追踪文件、更新引用并清空 Stage。不要边写文件边发现后续冲突。

**验收：** 在两个分支分别提交不同内容，反复切换后文件正确；reset 后分支名字不变，指向目标提交；失败时没有部分恢复。

## 阶段六 合并

阅读 [merge 规范](https://sp21.datastructur.es/materials/proj/proj2/proj2#merge) 和 [本地 merge 推理](06-merge.md)。先画提交图，再推导单个文件的三方状态，再组合多个文件。

拆出祖先识别、特殊情况、文件决策、覆盖检查、冲突内容和双亲提交。先跑普通分叉图，再跑已经包含 merge commit 的图。

**验收：** 祖先与 fast-forward 情况、仅一侧变化、同样变化、不同变化、修改对删除、独立新增、无结尾换行都经过测试；第一父与第二父顺序正确。

## 阶段七 完整核对

用 [命令清单](05-command-checklist.md) 和 [验收表](07-testing.md)逐项检查必做部分。特别检查错误的英文输出、失败后的状态、短 ID、排序、空行和性能。已经通过的基本测试不需要一遍遍重复代替缺失的边界测试。

**验收：** 必做命令均有成功、失败和跨进程测试；设计文档能解释当前实现；没有额外输出；同一文件内容不会被每次 commit 重复复制。

## 阶段八 可选扩展

完整 `status` 的后两栏和 remote 五条命令放在必做功能稳定后完成。Spring 2021 将它们列为加分，不要把它们当作开始 merge 之前的前置工作。[加分范围](https://sp21.datastructur.es/materials/proj/proj2/proj2#extra-credit)

Remote 在这个作业中可以是另一个本地 Gitlet 仓库路径，不需要实现真实网络协议。具体行为继续按 [Going Remote](https://sp21.datastructur.es/materials/proj/proj2/proj2#going-remote-extra-credit) 阅读。

## 进度记录

每完成一个阶段，记下对应测试路径、测试命令、运行结果以及还没覆盖的情况。建议按“功能已经实现”“本地测试已经验证”“官方评分已经验证”分别记录，不让一次编译成功变成全部完成的判断。
