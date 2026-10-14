# Gitlet 资料来源与版本

官方课程规范是项目行为的依据，官方骨架仓库是本地源文件的依据。中文指南提供实施建议、推演和验收方法；不把建议布局或类划分视为规范要求。

## 原始文件

[starter-manifest.json](starter-manifest.json) 记录 41 个官方文件的路径、固定版本下载 URL、字节数、Git blob SHA-1 和 SHA-256。文件来自 `Berkeley-CS61B/skeleton-sp21` 的版本 `160747451c147c59d8e3cbf70a7afee2b73bebdb`，只包含 `proj2` 与 `lab6`。

资料整理时保留原始文件字节和 TODO。开始实现后这些文件自然会改变；这时它们与原始哈希不相同并不表示实现出错。清单用于证明起点和查找上游原文件。

[collect_official.py](../tools/collect_official.py) 可重新获取这个固定版本，下载时检查 Git blob ID，遇到已修改文件会停止并拒绝覆盖。不要把重新下载骨架作为清空自己实现的方法。

## 网页与资源入口

[source-index.json](source-index.json) 记录本次访问的核心官方网址、HTTP 状态、内容类型、字节数和网页哈希，以及主规范中的标题锚点和链接列表。它保存来源元数据，网页正文和 PDF 仍使用 [资源索引](../docs/01-resources.md) 中的官方入口。

HTTP 成功只表示本次能够读取该资源。视频播放、在线表单、教学服务器账户和 Gradescope 提交未在本次验证范围内。链接清单可以用来确认资源出处，不能据此判断第三方平台账户权限。

## 资料核验

在仓库根目录运行：

```powershell
python .\gitlet-sp21\tools\audit_materials.py
```

这个脚本核对原始文件哈希、本地文档链接、代码块完整性、官方规范锚点和核心资源地址，并写入验证记录。网址访问失败、规范锚点不可验证、PDF 地址返回非 PDF 内容或文件不匹配，都会记录失败并返回非零退出码；不会把跳过的检查计入通过数量。开始修改官方骨架后，原始哈希校验会失败；那时用你自己的代码测试验证实现即可。

核验工具的回归检查保存在 [test_material_tools.py](../tools/test_material_tools.py)，可运行：

```powershell
python .\gitlet-sp21\tools\test_material_tools.py
```

它使用临时目录模拟来源不可访问、错误链接、骨架变动和旧编译类型等情况，并验证官方测试器的换行与空白处理。检查不会改写你的 Gitlet 源码。
