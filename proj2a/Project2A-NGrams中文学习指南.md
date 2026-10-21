# Project 2A：NGrams 中文学习指南（CS61B Spring 2024）

拆分及分册审计日期：2026-10-20。内容来自 2026-10-14 审计修订后的综合指南；本轮核对分册、官方正文/FAQ 与当前本地接口，只维护文档，保留综合原版、代码、测试、数据和配置。

[2A 分册](Project2A-NGrams中文学习指南.md) · [2B 分册](../proj2b/Project2B-WordNet中文学习指南.md) · [2C 分册](../proj2c/Project2C-热门词与共同祖先中文学习指南.md) · [综合原版](../proj2c/Project2-Ngordnet中文学习与实现指南.md)

本册用于在当前项目目录中边读边做，不提供整个项目的完整解答。原文中的官方核对、独立计算、库行为探针属于原审计记录，本次未重新运行项目测试、网页、性能或正式评测。现有实现进度以你当前代码为准，不沿用原版“骨架尚未完成”的状态快照。

文件链接使用相对路径，方便在 IDEA 的项目中跳转。若预览没有显示 Mermaid 图，配套表格和状态推演仍包含完整关系；不需要依赖图形渲染才能继续实现。

## 阅读导航

- [1. 整体目标与路线](#s1)

- [2. 准备：版本、数据与入口](#s2)

- [3. TimeSeries：先把年份运算做正确](#s3)

- [4. NGramMap：文件、计数与相对频率](#s4)

- [5. History：文字、图像与 Main 接线](#s5)

- [6. 测试、运行、性能与排查](#s6)

- [7. 交付范围与正式提交](#s7)

- [8. 本阶段完成清单](#s8)

- [9. 来源与后续学习](#s9)

<a id="s1"></a>

## 1. 整体目标与路线

最终做出能返回单词相对频率文本和曲线的后端。输入是单词列表与闭区间年份；History (Text) 返回逐词文本，History 返回 PNG 的 Base64 字符串。网页、服务器、查询解析与 Plotter 已提供；你补全 TimeSeries、NGramMap，并新建真实的两个 History Handler。

| 顺序 | 要解决的问题 | 主要文件 | 完成标志 |
| --- | --- | --- | --- |
| 准备 | 数据、依赖、运行目录对齐 | Main、Utils、模块配置 | 小文件路径明确 |
| 年份基础 | 复制、相加、相除 | TimeSeries | 独立运算和缺年规则正确 |
| 数据资料库 | 一次读入，反复 count/weight 查询 | NGramMap、FileReadDemo | 副本、区间与未知词正确 |
| 网页接线 | 将正确数据交给两种输出 | 两个真实 Handler、Main、Plotter | 文字格式与图像分别验收 |
| 交付 | 补测、计时、核对文件 | tests 与本册清单 | 记录真实证据 |

请求经过：浏览器 → 已有查询解析 → 你的 History Handler → NGramMap → TimeSeries；图像还经 Plotter。2B 可以独立完成 k=0 的词网查询，2C 才会把两种数据接在一起。

<a id="s2"></a>

## 2. 准备：版本、数据与入口

### 2.1 文件与运行目录

先看本项目的 [Main](src/main/Main.java)、[模块配置](proj2a.iml)，以及 tests 中的路径常量。数据入口：[2A 官方数据包](https://drive.google.com/file/d/1xGTZqCo5maiZjA307OPocmKDOTYlJXnz/view?usp=sharing)。2026-10-20 核对时，本地已有 `data/ngrams` 和 `data/wordnet`；不要把原综合指南的“缺少 data”历史快照当成当前状态。本次没有重新下载或改动数据。

| 位置 | 应有内容 |
| --- | --- |
| `proj2a/src` | 本阶段 Java 源码 |
| `proj2a/tests` | 本阶段 JUnit 测试 |
| `proj2a/static` | 已提供的网页与脚本 |
| `proj2a/data/ngrams`、`proj2a/data/wordnet` | 数据文件；与 src、tests、static 平级 |

本阶段先核对：`data/ngrams/very_short.csv`、`data/ngrams/total_counts.csv`、`data/ngrams/top_14377_words.csv`，本轮已确认这三条路径存在；其他词表按后续检查选用。文件存在不表示所有数据已完整验证。

`./data/...` 和服务器的 `static` 都按进程 working directory 解释，不按 Java 文件所在位置解释。运行本阶段 Main 或 JUnit 时，将 working directory 设为 `E:/cs61b/skeleton-sp24/proj2a`。Main class 为 `main.Main`，使用目标项目模块的 classpath；三个目录有同名类，一次只启动一个占用 4567 的服务器。

沿用综合指南的 JDK 17 或更高版本建议；本地模块引用 `E:/cs61b/library-sp24`，不要仅因 FAQ 的旧库名改成其他学期依赖。文件存在不等于 IDEA 已正确导入。数据解压后核对最终路径，避免多套一层目录；保留仓库原有 `.gitignore`，不要强行上传大数据包。

本册的请求使用四参数 `NgordnetQuery(words,startYear,endYear,k)`，2A 忽略 k。后续迁入 2C 时保留 2C 自己的五参数 query，不能整包覆盖。

### 2.2 容易与原说明混淆的地方

| 项目 | 本册采用的规则 |
| --- | --- |
| 除法缺年 | FAQ 的笼统回答与本地注释不同；分母缺少分子年份应抛 `IllegalArgumentException`，仅分母多出的年份忽略 |
| 演示类/绘图库 | 本地是 `main.FileReadDemo` 与 `plotting.Plotter`，原说明部分地方用了旧名字 |
| 大词表常量 | `TOP_14337_WORDS_FILE` 实际指向 `top_14377_words.csv`，不要据常量拼写猜数据文件名 |
| 库目录 | FAQ 部分文字写 `library-fa23`；本地配置使用 `E:/cs61b/library-sp24` |
| Dummy 构造函数 | 本地 Dummy 无参；你创建的真实 History Handler 按说明接收实例 NGramMap |

### 2.3 要求标签与检查边界

- **【官方必做】**：正文明确的功能、限制或交付物。
- **【本地契约】**：当前签名、方法注释和提供测试确定的行为。
- **【建议】**：便于学习、实现、排错的顺序与组织方式；不是额外评分规则。
- **【自选】**：类名、内部结构、DFS/BFS、同频裁决等允许自行设计的部分。
- **【可选】**：完成主线之后的扩展，不计入必做完成标志。

每阶段的“完成标志”是供你实施和自查的目标，不是本次已通过的测试结果。文件准备、类能编译、单元测试、网页操作、性能与正式评测分别提供不同证据。

<a id="s3"></a>

## 3. TimeSeries：先把年份运算做正确

先打开 [TimeSeries.java](src/ngrams/TimeSeries.java) 和 [TimeSeriesTest.java](tests/TimeSeriesTest.java)。这一层不读文件，因此可以在没有官方数据包时先实现和验证。后面的计数、频率、绘图都使用它。

### 3.1 类型声明到底说明什么

`TimeSeries extends TreeMap<Integer, Double>` 表示它本身就是一个按年份排序的映射：键是年份，值是该年的数值。数值可能是次数，也可能是比例，由使用者赋予含义。

例如 `put(2001, 8.0)` 保存一条记录；`get(2001)` 查回 `8.0`；没有 `2002` 时，`containsKey(2002)` 为假，`get(2002)` 为 `null`。**没有记录**和**有记录且值为 0.0**不同。前者不要补成一条零值记录。

【官方必做】不添加额外 public 方法；可以加 private 辅助方法。【官方设计提示】`TimeSeries` 应不含实例变量——数据已经保存在父类映射中，不必再维护一份年份数组和数值数组。正文把这项放在 Tips 中；不要把“再维护一份数组”当成本项目的实现步骤。[TreeMap 官方 API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/TreeMap.html)

### 3.2 建议按以下四步做

| 步骤 | 具体动作 | 为什么先做它 | 此步检查 |
| --- | --- | --- | --- |
| 1 | 实现 `years()` 与 `data()`；从映射中取得列表 | 后面绘图和已有测试要把年份与数值一一对应 | 两个列表长度相等，相同下标属于同一条记录 |
| 2 | 实现区间复制构造函数 `TimeSeries(ts,start,end)` | NGramMap 区间查询与防御性复制要复用它 | 包含两端；不创造缺失年份；改副本不改原本 |
| 3 | 实现 `plus(ts)`，返回新对象 | 汇总多个词的频率需要按年合并 | 并集年份保留，只在共同年份相加 |
| 4 | 实现 `dividedBy(ts)`，返回新对象 | count 除以年度总数才能变成 weight | 分子年份都必须有分母，缺少就抛异常 |

【本地契约】`years()` 允许任意顺序，但 `data()` 必须与之对应；当前提供测试期待升序，所以利用 TreeMap 自然顺序最直接。`keySet()`、`values()` 是视图；返回类型是 List 时，要构造相应列表。区间副本也不能只是把 `subMap` 视图当成新数据。

【建议】若使用 TreeMap 的区间方法，注意两参数 `subMap(start,end)` 的右端不包含 end；四参数版本可以分别指定两端是否包含。无论选哪种写法，本项目的区间必须是闭区间，并将取得的记录复制到独立 TimeSeries，不能直接返回与原映射联动的视图。

### 3.3 手算一次相加与区间复制

下面是**人工数据**，不代表实际语料：

| 年份 | A | B | `A.plus(B)` 的判断 | 结果 |
| --- | --- | --- | --- | --- |
| 2000 | 0.0 | 无记录 | A 独有，保留已有的零值 | 0.0 |
| 2001 | 8.0 | 无记录 | A 独有，保留 | 8.0 |
| 2002 | 无记录 | 3.0 | B 独有，保留 | 3.0 |
| 2003 | 4.0 | 6.0 | 两边都有，相加 | 10.0 |
| 2004 | 无记录 | 无记录 | 两边都没有，不生成新年 | 无记录 |

操作前 A 与 B 各自不变；操作后新对象为 `{2000=0.0, 2001=8.0, 2002=3.0, 2003=10.0}`。复制区间 `[2001,2002]` 得到 `{2001=8.0, 2002=3.0}`。修改副本中的 2001 后，原结果仍为 8.0。

这里“缺失一边相当于加零”只是计算上的说法，不是要求给输入补零。如果遍历所有 1400—2100 年并填零，就会把缺数据改成有数据，破坏后续结果和测试。

### 3.4 手算一次除法：方向不能混淆

人工数据：分子 N=`{2001=8.0, 2003=4.0}`，分母 D=`{2001=40.0, 2002=50.0, 2003=20.0}`。

| 当前年份 | 判断 | 处理 |
| --- | --- | --- |
| 2001，来自 N | D 有这个年 | 写入 `8/40=0.2` |
| 2003，来自 N | D 有这个年 | 写入 `4/20=0.2` |
| 2002，仅存在于 D | N 没有这个年 | 不加入结果 |

得到 `{2001=0.2, 2003=0.2}`。若删除 D 的 2003，操作应抛 `IllegalArgumentException`，不能返回只有 2001 的部分结果。空分子可以得到空结果。官方允许假设分母不会是零，不必为本任务发明另一套零分母规则。

### 3.5 在这一层怎么验证

提供的 `testFromSpec` 检查年份并集与相加，`testEmptyBasic` 检查空列表和空加法。**它们没有测试除法，也没有测试复制隔离。**

【建议补测】单边空、共同年、双方不相交、已有零值、区间两端、区间没有记录、加法/除法不修改双方、分母缺年异常、分母多余年忽略、修改副本不影响原对象。Double 运算比较用误差范围，例如 Truth 的 `isWithin(...).of(...)`，避免把浮点表示误差当成逻辑错误。

性能上，一次遍历映射比每次调用 `years()` 生成新列表再从中查找更清楚。若在一个循环的每次迭代重新生成长度为 n 的列表，看起来只有一个循环也可能做了平方级工作。

**完成标志：** 手算表能逐项对应实际对象；已有两个测试和补充边界测试有真实运行记录；`plus`、`dividedBy`、区间复制均保持输入不变。此时无需启动服务器。

<a id="s4"></a>

## 4. NGramMap：文件、计数与相对频率

先看 [NGramMap.java](src/ngrams/NGramMap.java) 的所有方法，再看 [FileReadDemo.java](src/main/FileReadDemo.java) 和 [NGramMapTest.java](tests/NGramMapTest.java)。这一层依赖 TimeSeries；Handler 只应该使用它，不应该再负责解析原始文件。

### 4.1 两种文件，两个不同的数

| 文件 | 分隔符 | 需要使用的列 | 在程序中的含义 |
| --- | --- | --- | --- |
| words file，例如 `very_short.csv` | 制表符 `\t` | 单词、年份、出现次数 | 某词在某年的原始计数 |
| counts file，例如 `total_counts.csv` | 逗号 `,` | 年份、该年总单词数 | 全语料该年的分母 |

words file 的第四列是来源/卷数，本项目忽略；counts file 的第三、第四列也忽略。扩展名都是 `.csv`，不能据此给两种文件都用逗号分割。

以下是**人为构造的小文件**。代码块中的 `\t` 表示实际制表符，若你自行建立文件，应写入真正的 tab，不能写字面量反斜杠和 t：

```text
words file（示意）
cat\t2001\t8\t2
cat\t2003\t4\t1
dog\t2002\t3\t1
dog\t2003\t6\t2

counts file
2001,40,5,2
2002,60,7,3
2003,20,4,1
```

### 4.2 先选择能回答问题的存储形式

【建议】把它理解为一座资料库，内部有两份内容：

| 建议的表示 | 存什么 | 谁用它 |
| --- | --- | --- |
| `Map<String, TimeSeries>` | 每个单词 → 该词的年份计数 | `countHistory`、`weightHistory`、2C 热门筛选 |
| 一个 `TimeSeries` | 每个年份 → 该年全部单词总数 | `totalCountHistory` 和相对频率分母 |

`Map<String, TimeSeries>` 比 `Map<String, Map<Integer,Double>>` 更能表达“每个词有一段历史”，也可以复用已经验证的加法、除法和区间复制。你可以自选其他合理内部结构；【官方必做】`NGramMap` 不继承其他类、不添加额外 public 方法，允许 private 辅助方法。

读入人工例子后的状态应为：

| 索引入口 | 对应数据 |
| --- | --- |
| `cat` | `{2001=8.0, 2003=4.0}` |
| `dog` | `{2002=3.0, 2003=6.0}` |
| 年度总数 | `{2001=40.0, 2002=60.0, 2003=20.0}` |

构造函数逐行取字段，把同一词不同年份积累到同一个 TimeSeries 中。不要遇到同一词的新行就重建空对象，覆盖先前的年份。年份可用 int；计数与年度总数采用 Double/可容纳大数的数值解析，不要先 `Integer.parseInt` 再转 Double——若原文已经超出 int，错误发生在转换前。

### 4.3 读文件只做一次，先核对输入再写查询

【官方要求与建议】大量工作应在构造函数完成，查询复用已读入的结构。使用 `In` 时不要调用 `readAllLines` 或 `readAllStrings` 一次装入全文。文件读法可以自选；本地演示展示了 `In` 的逐行读取。

按行读取时，`hasNextLine()` 回答还有没有下一行，`isEmpty()` 的判断关注是否还有非空白 token；在普通数据行中它们常常都可工作，但对尾部空白行不完全等价。正文和 FAQ 的提示互相有差异，所以检查应落到实际结果：第一行和最后一条有效行都读到，空文件不误读，尾部空行不拿去解析数字。处理空白行是建议的健壮性措施，不需要扩展成支持所有通用 CSV 转义格式。

审计中用本地 `algs4.jar` 的 `In` 配合 `readLine()` 验证了下面的行为：

| 输入情形 | `while (in.hasNextLine())` | `while (!in.isEmpty())` |
| --- | --- | --- |
| 空输入 | 不进入循环 | 不进入循环 |
| 最后一条记录没有换行 | 仍能读到最后记录 | 仍能读到最后记录 |
| 有效记录后仅剩空白行 | 会继续读出空白行 | 会停止，不读纯尾部空白 |
| 两条有效记录之间有空行 | 会读出中间空行 | **同样会读出中间空行**，因为后面仍有有效 token |

因此换成 `!isEmpty()` 并不会自动跳过文件中所有空行。若你选择容忍空白行，应在读取后判断，再决定是否解析字段；这属于实现健壮性建议，不是新增的数据文件交付要求。

【建议动作】先用小文件确认：读入的词有哪些；指定词有几个年份；第一年与最后一年是什么；年度总数是否读对。此时只实现最简单的 `countHistory` 和 `totalCountHistory`，不要同时调试网页。

### 4.4 所有查询方法怎样逐层复用

| API | 具体动作 | 缺数据时 | 与前一步的连接 |
| --- | --- | --- | --- |
| `countHistory(word,start,end)` | 找词的内部历史，取闭区间并复制 | 新的空 TimeSeries | 复用区间复制 |
| `countHistory(word)` | 返回该词全部历史的副本 | 新的空 TimeSeries | 与区间版规则保持一致 |
| `totalCountHistory()` | 返回年度总数的防御性副本 | 按实际数据返回 | 不能把内部分母暴露出去 |
| `weightHistory(word,start,end)` | 区间计数除以对应年份的全语料总数 | 空 TimeSeries | 复用 `dividedBy` |
| `weightHistory(word)` | 返回全部有记录年份的相对频率 | 空 TimeSeries | 同上 |
| `summedWeightHistory(words,start,end)` | 各词的区间相对频率按年相加 | 未知词贡献空序列，不抛异常 | 复用 `weightHistory` 与 `plus` |
| `summedWeightHistory(words)` | 各词全部相对频率按年相加 | 同上 | 与区间版保持一致 |

`MIN_YEAR=1400`、`MAX_YEAR=2100` 是骨架给出的可用范围常量；它们可以帮助组织全范围查询，但不意味着需要填满每一个年份。

**防御性复制为什么必须做？** 假设外部拿到 `countHistory("cat")` 后执行 `clear()`。如果返回内部对象，下一次查询就丢失 cat；同样，暴露年度总数可能让一次外部修改破坏所有 weight。测试很容易用“先查询、改返回值、再查询”发现它。不要仅复制最外层 Map 而仍共享可变 TimeSeries。

### 4.5 手推一次从输入到输出

继续使用上面的人工资料：`weightHistory("cat",2001,2003)`。

1. 词索引找到 cat：`{2001=8.0,2003=4.0}`。
2. 区间过滤保留这两年；2002 没记录，不补零。
3. 同年除以年度总数：2001 是 `8/40=0.2`，2003 是 `4/20=0.2`。
4. 得到新对象 `{2001=0.2,2003=0.2}`，资料库不变。

dog 的 weight 为 `{2002=0.05,2003=0.3}`。对 `[cat,dog]` 求 summed weight 得到 `{2001=0.2,2002=0.05,2003=0.5}`；2003 是 `0.2+0.3`。未知词 `ghost` 返回 `{}`，加入 summed 查询不会增加年份。

这两个量很容易混淆：

| 名称 | 2003 的 cat | 含义 | 后面在哪里用 |
| --- | --- | --- | --- |
| count | 4.0 | 出现四次 | 2C 区间热门排名 |
| weight | 0.2 | 占该年总语料的 20% | History 文本与曲线 |

### 4.6 测试安排与完成标志

本地 `testCountHistory` 用 `very_short.csv` 检查 request 的完整年份/次数以及 `[2006,2007]` 区间；`testOnLargeFile` 用较大词表检查 fish 的计数、年度总数、weight，并检查 fish+dog 的 summed weight。它们只覆盖部分方法和数据点，不覆盖所有未知词、重载、复制隔离。

先给小文件写自检，再跑已有测试。补充：未知词、已知词但区间无记录、单年区间、两端年份、没有词的集合、多个词年份不重合、重载一致、返回值修改后再查询、两份使用不同文件的 NGramMap 相互独立。重复词是否按集合去重不是这里明确规定的额外规则，不要为了自己设想的行为改接口。

【官方性能目标】`top_14377_words.csv` 的构造应少于约 60 秒，旧电脑可能稍长；内存允许时还可尝试 `top_49887_words.csv`。这是需要实际测量的目标，不是本文已验证结果。

**完成标志：** 小文件中的字段都能查回；所有 API 返回符合语义的对象而不是 `null`；查询不重读文件；复制隔离检查正确；有本地测试记录。大文件性能在第 6 节单独记录。

<a id="s5"></a>

## 5. History：文字、图像与 Main 接线

先看 [DummyHistoryTextHandler](src/main/DummyHistoryTextHandler.java)、[DummyHistoryHandler](src/main/DummyHistoryHandler.java)、[PlotDemo](src/main/PlotDemo.java)、[Plotter](src/plotting/Plotter.java) 和 [2A Main](src/main/Main.java)。这一步依赖 NGramMap 正确，否则前端会把底层数值错误伪装成显示问题。

### 5.1 Handler 与 query 分别负责什么

2A 的查询声明是 `NgordnetQuery(List<String> words, int startYear, int endYear, int k)`。它是一份请求数据，不是你要实现的资料库。用 `q.words()` 等访问 record 分量，2A 忽略 k。

两个新类都继承 `NgordnetQueryHandler`，覆盖 `String handle(NgordnetQuery q)`。构造函数分别按官方形式接收 `NGramMap map`。由 Main 构造资料库，再传给 Handler 实例保存引用；不要做 static 全局 NGramMap，也不要在每次 handle 里重新读文件。

【设计原因】这样可以让测试分别装配小数据与大数据，让多个 Handler 共享一个已读入的资料库，而不会互相覆盖全局对象。构造函数传入依赖并非语法装饰，它决定每次请求到底使用哪一份资料。

### 5.2 先完成文字输出，再接图像

【官方必做】`HistoryTextHandler` 对每个请求单词取得**区间 weightHistory**，按请求列表顺序输出，每行结构为 `word: {年份=数值, ...}\n`。最后一行也必须换行。未知词仍按行显示空序列，例如 `ghost: {}\n`。使用 TimeSeries 继承的 `toString()`，不手写映射中的逗号、空格或数字格式。

用第 4 节人工资料，查询 `[cat,dog]`、2001—2003 时，应该构成以下文本（末尾有换行）：

```text
cat: {2001=0.2, 2003=0.2}
dog: {2002=0.05, 2003=0.3}
```

不要输出原始次数，不要把两个词合成一个 summed 序列，不要把词标签自动按字母重排。这三种行为都与本按钮的目标不同。

[HistoryTextHandlerTest](tests/HistoryTextHandlerTest.java) 当前被注释。等你的 Handler 创建后再自行启用它；它检查 request、airport 在 2006—2007 的完整字符串，包括顺序、数字、标点、换行。**看到绿色空测试类不能证明这项通过。**

### 5.3 图像链条：数值、曲线、字符串是三个层次

`HistoryHandler` 的输入与文字 Handler 相同，但返回图像编码。建议顺序如下：

| 步骤 | 创建或使用的内容 | 必须保持的对应关系 |
| --- | --- | --- |
| 1 | 请求词的标签列表 | 第 i 个标签是第 i 个词 |
| 2 | 每词的区间 weightHistory 列表 | 第 i 个 TimeSeries 属于第 i 个标签 |
| 3 | `Plotter.generateTimeSeriesChart(labels,series)` | 两列表长度相同，否则本地工具抛异常 |
| 4 | `Plotter.encodeChartAsString(chart)` | 返回裸 Base64 字符串给浏览器 |

已有 JavaScript 会加上 `data:image/png;base64,` 前缀；Handler 不要重复添加，也不必自己把结果再 JSON 编码。`NgordnetQueryHandler` 的 HTTP 重载已经用 Gson 编码字符串，直接单元测试的 `handle(q)` 返回普通字符串。

可以先运行 `PlotDemo` 看懂“图就是一段编码”，也可以使用它展示的 `Plotter.displayChart(chart)` 在本机查看。测试图表时先选确定有数据的不同单词，再检查空数据与重复输入。

**本地库边界，已独立验证：** 当前 `xchart-3.8.2.jar` 的 `XYChart.addSeries` 不接受空的 Y 数据，会抛 `IllegalArgumentException`；同一图中的两个 series 也不能使用相同名称。`Plotter` 逐项把单词作为 series 名、把 `years()/data()` 作为数据传进去，没有自动处理这两种情形。因此“未知词的 NGramMap 查询正确返回空序列”不代表“直接把这个空序列交给 Plotter 也能成功”。

【建议的界面健壮性处理】在调用绘图工具前检查有效数据，重复的同一词可以只画一次；筛掉空序列时，要同时筛掉它的标签，使两列表仍一一对应。全部词都无区间数据时，需要明确采用空图或其他无数据展示方案；本地库可把零条 series 的图编码成图像，但本文没有验证你的 HistoryHandler 已处理此情形。正文没有明确规定空图的外观或重复词的展示规则，所以这些处理方案属于建议，不能当成官方唯一答案。也不要给 NGramMap 补伪造零记录来绕开绘图库限制。

### 5.4 两个入口如何验收

【你实现时需要的接线】在 2A Main 中创建 NGramMap，将 `historytext` 注册到真实文字 Handler，将 `history` 注册到真实图像 Handler。保留 Main 开头标注不要删除/修改的静态初始化块。

运行目标模块的 Main，打开 [2A 网页文件](static/ngordnet_2a.html)，或访问程序输出的 [2A 本机地址](http://localhost:4567/ngordnet_2a.html)。输入有数据的词与区间，先检查文本，再点击 History 看曲线。换一个词或区间后，结果应相应变化；若始终显示抛物线与正弦波，仍注册着 Dummy。

**完成标志：** 字符串级测试真实运行；两按钮分别返回正确类型；曲线标签与数据对应；Main 使用实例依赖。文字测试不依赖服务器，网页检查则依赖正确启动和静态目录。至此 2A 四个类的工作才算齐全。

<a id="s6"></a>

## 6. 测试、运行、性能与排查

### 6.1 提供测试与缺口

| 测试文件 | 已有检查 | 主要缺口 |
| --- | --- | --- |
| 2A `TimeSeriesTest` | 相加并集、已有零值、空序列 | dividedBy、区间复制、修改隔离 |
| 2A `NGramMapTest` | request 全历史/区间；大文件 fish/dog 的部分 count、total、weight、summed | 所有重载/空结果/复制行为、全部年份 |
| 2A `HistoryTextHandlerTest` | request/airport 的精确文本 | 图像 Handler、未知词、多种格式输入 |

当前 `TimeSeriesTest` 有两个启用的测试方法，`NGramMapTest` 也有两个，共四项；这里说的是测试方法数，不是这两个实现类只需完成两个方法。`HistoryTextHandlerTest` 整段被注释，需等类存在后自行启用。不要把空测试类当成测试通过。先纯内存运算，再小文件查询、Handler 字符串，最后网页和大数据。

### 6.2 运行与界面检查

用目标模块启动 Main，访问 [2A 本机页面](http://localhost:4567/ngordnet_2a.html)。先用有数据的词和区间核对 History (Text)，再核对 History 曲线和标签。当前页面初始年份是 2000—2020；清空/不能解析某一项时，后端对该项分别回退到 1900/2020，直接构造 query 不应用这项回退。

先让内存单元测试、读文件检查、Handler 检查分别成立，再做浏览器检查。空序列与重复词的图像边界按第 5 节处理；启动日志或某个文本用例通过，都不代表全部要求通过。

### 6.3 性能自查

本项目没有要求提交实验数据表或性能分析报告。下面是【建议】的工程检查，用于确认加载目标与超时风险，不增加课程交付物，也不编造结果。

计时分别包围“构造数据对象”和“已构造对象上的查询”；不要把下载数据、打开 IDE 或用户思考输入的时间算进去。相同 JDK、内存设置、文件组合与查询才适合比较。首次运行与 JVM 预热后的运行应区分，不能把缓存后的单次最快值当普遍性能。

| 项目 | 输入/操作流程 | 记录项（待填） | 主要观察 |
| --- | --- | --- | --- |
| NGramMap 加载 | 构造时给 `top_14377_words.csv` 与 `total_counts.csv`；构造结束后停止计时 | JDK：___；文件：___；耗时：___；内存设置：___ | 对照官方约 60 秒目标 |

先核对 top_14377_words.csv 的构造时间，再测同一对象上的重复查询；原指南给出的加载目标约为 60 秒，旧电脑可能略长。

### 6.4 排查表

| 失败表现 | 优先检查 |
| --- | --- |
| 找不到 `data/...` 或静态页面 404 | 目标模块、working directory、是否套了多层解压目录 |
| `record` 语法错误 | JDK 与 IDE language level |
| 缺 spark/Gson/XChart/JUnit/Truth | `.iml` 指向的 `library-sp24` 与 IDE 实际库导入 |
| 点击按钮没响应/连接拒绝 | 服务器是否启动，页面是否对应项目，4567 是否已有旧进程 |
| `Failed to bind ...4567`、Spark 启动失败 | 停止自己先前启动的 Main；避免并行占用同端口；必要时再重启 IDE |
| 服务日志很多但没结果 | 看是否有真正异常、对应请求是否进入 Handler；启动提示只确认连接层 |
| 文字结果数值对、测试仍失败 | 单词顺序、冒号空格、标点、最后一行 `\n` |
| History 永远是抛物线/正弦波 | Main 仍注册 DummyHistoryHandler |
| History 抛 `Y-Axis data cannot be empty` | 某词在区间内无记录，空 TimeSeries 被直接传给 Plotter；不要补伪数据 |
| History 抛 series 名重复 | 重复单词作为同名曲线加入同一个图；核对你选择的重复输入处理 |
| 未改输入框却按 1900 开始统计 | 混淆页面初始的 2000 与解析器失败回退的 1900 |
| 年度总数 `NumberFormatException` 或原始计数异常 | 是否用 int 解析/累加过大计数 |
| 加法多出许多零年份 | 是否补满所有年份，混淆缺记录与真实零 |
| 除法缺年却悄悄丢年 | 是否误按 FAQ 简化说法取年份交集，未遵守本地异常契约 |
| 修改返回值后再次查询数据丢失 | 是否返回内部 TimeSeries/视图，而非防御性副本 |
| 线上示例与本地预期不同 | 本阶段的词频文件与年度总数文件是否匹配；起止年份和单词是否相同；不要直接套用其他数据的期望值 |
| 超时或内存错误 | 分开测加载与查询；检查重读/重建/重复列表；有证据后再判断平台问题 |

<a id="s7"></a>

## 7. 交付范围与正式提交

| 范围 | 必做实现/交付 | 官方评分说明 |
| --- | --- | --- |
| 2A | `TimeSeries`、`NGramMap`、`HistoryTextHandler`、`HistoryHandler`，以及可用的 Main 接线 | TimeSeries 30%；count/total 20%；weight/summed 30%；文字 10%；图像 10% |

2A 本身不需要提交 2B/C 的设计文档或 Checkpoint；到下一阶段再按要求完成。

综合指南记录的归档评测额度为初始八个 token、每个 24 小时恢复；当前账户和作业是否仍可用，应以课程/Gradescope 实际状态为准，本次没有核对。没有新增性能报告、TA 现场演示或录像交付要求。测试和计时记录是自查证据。

实现后处理本阶段 TODO，保留既有 public API。将来正式提交时，先确认目标阶段与文件范围，再按课程要求保存、add/commit、push，并在对应作业提交；本次只写文档，未执行这些操作。

<a id="s8"></a>

## 8. 本阶段完成清单

这些框是你实现后的验收目标。本次拆分没有将任何一项标为“已通过”。

### 8.1 必做与本地接口

- [ ] 使用 Spring 2024 资料；分清 2A/B/C，未混用 Gitlet 或其他学期 API。
- [ ] 目标目录的 data 与 src/static 平级；文件组合和 working directory 正确。
- [ ] TimeSeries 继承 `TreeMap<Integer,Double>`；未加额外 public API 或冗余实例存储。
- [ ] years/data 一一对应；区间包含两端；缺失年份不补记录。
- [ ] plus 返回新对象，处理年份并集；dividedBy 返回新对象，分母缺年抛异常，分母多余年忽略。
- [ ] NGramMap 不继承别的类；不加额外 public 方法；构造读取两种格式和正确列。
- [ ] 所有 count/total 返回防御性副本；未知词/无区间数据返回空序列。
- [ ] weight 与 summedWeight 的所有重载正确；无效词在 summed 中安全忽略。
- [ ] 两个 History Handler 接收实例 NGramMap，无 static 全局库。
- [ ] HistoryText 使用区间相对频率，保留请求顺序、标点与最后换行。
- [ ] History 返回 Base64 图像，标签与序列对应；Main 注册真实 Handler。
- [ ] 大词表加载满足本阶段要求；查询不重复读数据。
- [ ] 未把 data 压缩包/大文件强行纳入 Git；必要业务依赖齐全；实现后处理 TODO。
- [ ] 有正式课程提交权限时，完成每个要求的阶段与文档提交；记录真实 Gradescope 结果。

### 8.2 验证建议

- [ ] 参考官方测试段扩充提供测试，并新建对应场景的测试文件；被注释的测试实际启用后才计入证据。
- [ ] 小规模、接口装配、网页与大文件检查分别记录；不把局部通过当正式评测结论。
- [ ] 用实际计时核对大词表加载目标；将性能自查记录与课程交付物区分。
- [ ] 检查空序列与重复词的绘图行为；选定展示方案，不用补伪造零值规避库限制。
- [ ] 分清页面初始年份、解析器失败回退和直接构造 query 的参数。
- [ ] 如需容忍空白行，检查文件中间和末尾的空白，不能假设 `!isEmpty()` 会跳过全部空行。

### 8.3 可选与自选

- [ ] 【可选】内存允许时检查更大词表；额外可视化不要改变规定 API。

<a id="s9"></a>

## 9. 来源与后续学习

- [Spring 2024 Project 2A 正文](https://sp24.datastructur.es/projects/proj2a/)
- [Project 2A FAQ](https://sp24.datastructur.es/projects/proj2a/faq/)
- [TreeMap Java 17 API](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/TreeMap.html)
- [保留的综合原版](../proj2c/Project2-Ngordnet中文学习与实现指南.md)

本册的实现步骤与人工小例子继承综合原版。本轮重新取得 2A/B/C 正文与 FAQ，其正文文本与原审计快照一致，并核对当前数据目录、接口、提供测试、章节引用和文件链接。使用现有小数据独立复核了相关示例；没有调用你的待完成实现，也没有运行项目 JUnit、网页、性能或 Gradescope。原版库探针结论保留为历史依据，本轮未重复执行。综合原版保持不变。
