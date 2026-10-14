# CS61B Spring 2021 Gitlet 学习资料

你要完成的是 Spring 2021 的 **Project 2 Gitlet**：用 Java 实现一个简化的版本控制系统。Lab 6 Canine Capers 是它的文件操作与持久化预备实验。本目录提供官方骨架、公开测试、中文学习路线和资源索引，供你自行完成项目。

整理日期：2026 年 10 月 12 日；审计修订：2026 年 10 月 13 日。项目行为以 [Spring 2021 官方规范](https://sp21.datastructur.es/materials/proj/proj2/proj2)为准。中文文档中的实现顺序、目录设计和自测方法是学习建议，并非指定答案。修正项目与复核结果见 [本次审计记录](verification/audit-2026-10-13.md)。

## 建议阅读顺序

| 顺序 | 文件 | 阅读后要完成的事 |
| --- | --- | --- |
| 1 | [官方资源索引](docs/01-resources.md) | 找到规范、视频、设计说明和测试入口 |
| 2 | [Windows 环境与启动](docs/02-setup.md) | 编译骨架，认识测试入口和工作目录 |
| 3 | [数据模型与持久化](docs/03-model.md) | 画出 commit、blob、branch、HEAD、stage 的关系 |
| 4 | [分阶段实现路线](docs/04-roadmap.md) | 逐步完成基础命令、分支、恢复和 merge |
| 5 | [命令规范阅读清单](docs/05-command-checklist.md) | 逐条核对参数、状态变化、失败条件与复杂度 |
| 6 | [Merge 推理与检查](docs/06-merge.md) | 推导每一种文件状态，处理双亲提交 |
| 7 | [测试调试与完成验收](docs/07-testing.md) | 补写自己的集成测试并检查完成情况 |
| 配套 | [设计文档填写模板](docs/08-design-template.md) | 在编码前和每次设计变动后更新设计 |

## 目录用途

```text
gitlet-sp21/
  README.md
  docs/                       中文指南
  proj2/                      官方 Gitlet 骨架，可在这里完成项目
    gitlet/                   Java 源码
    testing/samples/          4 个官方样例测试
    testing/student_tests/    放你自己写的 .in 文件
    testing/src/              测试用文件内容
    gitlet-design.md           官方设计文档骨架
  lab6/                       官方 Capers 预备实验骨架及 7 个公开测试
  tools/                      资料下载核验与 Windows 编译测试辅助脚本
  sources/                    来源链接、版本和文件哈希
  verification/               本次资料核验记录
```

当前仓库中的 `proj2a`、`proj2b`、`proj2c` 和 `lab06` 属于其他学期的任务。Gitlet 的代码入口是 **本目录的 `proj2/gitlet/Main.java`**；Capers 的入口是本目录的 `lab6/capers/Main.java`。

## 今天开始的三步

1. 阅读官方规范的 Overview、Internal Structures 和 Overall Spec，再浏览中文数据模型说明。
2. 按环境指南编译骨架；查看 `Utils.java` 的读写、序列化和 SHA-1 工具方法。
3. 填写设计文档的类与持久化部分，然后先做 `init` 和 `log`。每个阶段都用新的 Java 进程验证数据能够保留下来。

四个样例测试对应基础阶段。完整项目还需要自行测试删除、状态、分支、恢复、合并和错误场景。官方骨架保留 TODO，初始样例测试失败是正常现象。

## 资源和验证边界

官方 Java 源码、Python 测试器、Makefile 和测试输入来自 [Berkeley-CS61B/skeleton-sp21](https://github.com/Berkeley-CS61B/skeleton-sp21)，固定版本 `160747451c147c59d8e3cbf70a7afee2b73bebdb`。完整路径和 SHA-256 见 [starter-manifest.json](sources/starter-manifest.json)。

本目录的 [.gitattributes](.gitattributes) 为 `lab6/` 和 `proj2/` 中由 Git 识别为文本的文件固定 LF 换行，避免 Windows 检出时自动转换换行导致官方原始字节哈希失配。Git 识别为二进制的文件保持原始字节。

网页、视频、在线测验和 PDF 保留官方链接。视频没有逐段观看；链接列在官方规范中不代表当前一定可播放。2021 年的 Gradescope、教学服务器、snaps 和截止日期是历史课程安排，自学完成情况以你的代码和本地验证为依据。

本次只准备学习资料与未实现骨架。具体检查结果见 [资料核验记录](verification/README.md)。
