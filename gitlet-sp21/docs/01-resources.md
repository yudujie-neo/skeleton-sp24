# Gitlet 官方资源索引

先把官方规范作为手边的查阅页，再按实现阶段选视频。不要把真实 Git 的全部行为照搬到 Gitlet；项目的命令边界由 Spring 2021 规范决定。

## 核心文字资料

| 资料 | 用途 | 建议阅读时机 |
| --- | --- | --- |
| [Project 2 Gitlet 完整规范](https://sp21.datastructur.es/materials/proj/proj2/proj2) | 所有必做命令、错误情况、输出、性能和测试说明 | 从开始到验收持续查阅 |
| [Lab 6 Canine Capers](https://sp21.datastructur.es/materials/lab/lab6/lab6) | Java 文件操作、对象序列化、跨进程状态、测试调试 | 开始持久化之前 |
| [设计文档要求](https://sp21.datastructur.es/materials/proj/proj2/design.html) | Classes and Data Structures、Algorithms、Persistence | 写代码之前 |
| [Capers 设计文档示例](https://sp21.datastructur.es/materials/proj/proj2/capers-example) | 观察设计文档需要解释到什么程度 | 填写自己的设计文档时 |
| [Gitlet 设计笔记 PDF](https://sp21.datastructur.es/materials/proj/proj2/gitlet-design-notes.pdf) | 官方规范引用的 Designing Gitlet 配套笔记 | 设计对象与目录时 |
| [Gitlet 入门幻灯片 PDF](https://cdn-uploads.piazza.com/attach/k5eevxebzpj25b/jqr7jm9igtc7l5/k97ipfmgmb3n/Gitlet_Slides.pdf) | 官方入门视频配套幻灯片 | 看入门视频时 |
| [持久化笔记](https://paper.dropbox.com/doc/Gitlet-Persistence--AyM0lOEaezWrTi7gG_Pt~bXcAg-zEnTGJhtUMtGr8ILYhoab) | 官方引用的 Designing Persistence | 思考磁盘状态时 |
| [Staff Gitlet 使用指南](https://sp21.datastructur.es/materials/guides/staff-gitlet) | 在教学账户上观察参考实现与验证自写测试 | 有可用教学账户时；自学可跳过 |

[设计文档要求页](https://sp21.datastructur.es/materials/proj/proj2/design.html#3-persistence)的 Persistence 例子使用了 `commit -m`，与主规范的 Usage 不一致。实际命令应按主规范写成 `java gitlet.Main commit "message"`。

## 官方代码与公开测试

| 上游入口 | 本地路径 | 内容 |
| --- | --- | --- |
| [Spring 2021 官方骨架仓库](https://github.com/Berkeley-CS61B/skeleton-sp21) | [文件版本清单](../sources/starter-manifest.json) | 固定版本和逐文件来源 |
| [proj2](https://github.com/Berkeley-CS61B/skeleton-sp21/tree/master/proj2) | [Gitlet 入口](../proj2/gitlet/Main.java) | 待实现 Java 类、Utils、设计文档、构建文件、测试器 |
| [Gitlet 样例测试](https://github.com/Berkeley-CS61B/skeleton-sp21/tree/master/proj2/testing/samples) | [test01](../proj2/testing/samples/test01-init.in)、[test02](../proj2/testing/samples/test02-basic-checkout.in)、[test03](../proj2/testing/samples/test03-basic-log.in)、[test04](../proj2/testing/samples/test04-prev-checkout.in) | 初始化、恢复文件、历史输出与旧提交恢复 |
| [lab6](https://github.com/Berkeley-CS61B/skeleton-sp21/tree/master/lab6) | [Capers 入口](../lab6/capers/Main.java) | 故事文本、Dog 对象、持久化和预备测试 |

## 视频学习顺序

这些入口均来自 [Gitlet 官方规范的资源列表](https://sp21.datastructur.es/materials/proj/proj2/proj2#a-note-on-this-spec)。部分录于 2020 年，旧视频中的 Project 3、Lab 12、`shared`、`repo` 等名称不能当作你当前目录配置。

| 阶段 | 视频入口 | 看完后要能做的事 |
| --- | --- | --- |
| Git 基础 | [Git intro 1](https://www.youtube.com/watch?v=yWBzCAY_5UI)、[Git intro 2](https://www.youtube.com/watch?v=CnMpARAOhFg) | 区分工作文件、暂存和提交 |
| 整体模型 | [Lecture 12](https://youtu.be/fvhqn5PeU_Q) | 解释版本历史和分支关系 |
| Gitlet 入门 | [Part 1](https://www.youtube.com/watch?v=-1gE2cNFhPA)、[Part 2](https://www.youtube.com/watch?v=GfmH9_8tM5w)、[Part 3](https://www.youtube.com/watch?v=dv5VdbIZKF8)、[Part 4](https://www.youtube.com/watch?v=k8jwbG8bE7Y) | 明确命令改变哪些数据 |
| 2021 入门答疑 | [Getting started 1](https://youtu.be/6JVdbNZm0cM)、[Getting started 2](https://youtu.be/1d1yOSoTVAM) | 把模型转成实现计划 |
| 2021 设计答疑 | [Designing Gitlet](https://youtu.be/G3YU9oY8PcU) | 为对象与持久化作出选择 |
| 分支 | [Branching overview](https://youtu.be/desB3AS6aZg) | 手画两个分支分叉后的指针变化 |
| 合并 | [Merge overview](https://www.youtube.com/watch?v=JR3OYCMv9b4&t=929s)、[2021 Merge](https://youtu.be/l0X5NgzAWYQ) | 找共同祖先并逐文件比较三份状态 |
| 测试 | [Testing](https://www.youtube.com/watch?v=uMYpuQuHGu0&t=752s) | 写 .in 测试和定位失败步骤 |

## 概念自测

| 主题 | 官方链接 |
| --- | --- |
| status 无分支 | [概念测验](https://forms.gle/LSgBK5RAdRwhAqKK8) |
| status 有分支 | [概念测验](https://forms.gle/RHUiRkSrtgysC6En8) |
| checkout 无分支 | [概念测验](https://forms.gle/mfHLnrU9VX349jnr7) |
| checkout 有分支 | [概念测验](https://forms.gle/tbZuqDz7x3u41JhM6) |
| merge | [概念测验](https://forms.gle/Gu4FcFf1kfC7HYBa6) |

这些是官方当年的理解练习。若在线表单不可用，可用本目录的状态推演表和验收清单完成自测。

## Java 和 Git 补充参考

| 官方参考 | 应解决的问题 |
| --- | --- |
| [Java 17 File](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/File.html) | File 是路径表示；构造对象不会自动创建文件 |
| [Java 17 Serializable](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/Serializable.html) | 哪些对象字段会被持久化 |
| [Java 17 SimpleDateFormat](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/text/SimpleDateFormat.html) | 英文日期格式与时区 |
| [Java 17 TreeMap](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/TreeMap.html) | 排序和稳定的键遍历顺序 |
| [Pro Git Git Objects](https://git-scm.com/book/en/v2/Git-Internals-Git-Objects) | 理解内容寻址、blob 和 commit 的关系 |
| [Pro Git Branches in a Nutshell](https://git-scm.com/book/en/v2/Git-Branching-Branches-in-a-Nutshell) | 理解分支指针与 HEAD |

补充资料用于理解概念。Gitlet 的数据格式由你设计，命令要求继续以项目规范为准。
