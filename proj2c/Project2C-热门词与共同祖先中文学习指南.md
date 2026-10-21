# Project 2C：热门词与共同祖先中文学习指南（CS61B Spring 2024）

拆分及分册审计日期：2026-10-20。内容来自 2026-10-14 审计修订后的综合指南；本轮核对分册、官方正文/FAQ 与当前本地接口，只维护文档，保留综合原版、代码、测试、数据和配置。

[2A 分册](../proj2a/Project2A-NGrams中文学习指南.md) · [2B 分册](../proj2b/Project2B-WordNet中文学习指南.md) · [2C 分册](Project2C-热门词与共同祖先中文学习指南.md) · [综合原版](Project2-Ngordnet中文学习与实现指南.md)

本册用于在当前项目目录中边读边做，不提供整个项目的完整解答。原文中的官方核对、独立计算、库行为探针属于原审计记录，本次未重新运行项目测试、网页、性能或正式评测。现有实现进度以你当前代码为准，不沿用原版“骨架尚未完成”的状态快照。

文件链接使用相对路径，方便在 IDEA 的项目中跳转。若预览没有显示 Mermaid 图，配套表格和状态推演仍包含完整关系；不需要依赖图形渲染才能继续实现。

## 阅读导航

- [1. 整体目标与路线](#s1)

- [2. 准备与前置契约](#s2)

- [3. 迁移 2A/B 并装配 2C](#s3)

- [4. 热门词：k、区间计数与排序](#s4)

- [5. 共同祖先：反向遍历与查询类型](#s5)

- [6. 测试、综合运行、性能与排查](#s6)

- [7. 交付范围与正式提交](#s7)

- [8. 可选整合：Hypohist 等](#s8)

- [9. 本阶段完成清单](#s9)

- [10. 来源与原版导航](#s10)

<a id="s1"></a>

## 1. 整体目标与路线

本阶段把已完成的 2A 统计层与 2B 词网连接起来。输入为单词列表、闭区间年份、k、查询类型；输出是字母排序的下位词或共同祖先词列表。k=0 返回全部候选，不按词频删词；k>0 在共同候选中按区间原始出现次数选满 min(k,正频候选数)。

| 顺序 | 解决的问题 | 主要对象 | 完成标志 |
| --- | --- | --- | --- |
| 前置核对 | 两种数据与共用设计已就绪 | 2A 的 NGramMap/TimeSeries，2B 的图/词网 | 能独立解释基础契约 |
| 迁移与装配 | 让 2C 有自己的完整依赖 | src/ngrams、2B 辅助类、Main、工厂 | 不靠其他模块隐式补缺类 |
| 热门筛选 | 先交集，再按 count 排名，最后字母排序 | Handler 与统计层 | 零频率、同频、数量和区间正确 |
| 共同祖先 | 按类型改用反向边，复用交集与筛选 | parents 索引、query type | 单词/多词及祖先 k>0 均验证 |
| 综合验收 | 旧 k=0 回归、新行为与入口 | tests、网页、计时记录 | 证据分别记录 |

2C 必做包含共同祖先；Hypohist 属于可选整合。不要将两者混为一谈。

<a id="s2"></a>

## 2. 准备与前置契约

### 2.1 文件与运行目录

先看本项目的 [Main](src/main/Main.java)、[模块配置](proj2c.iml)，以及 tests 中的路径常量。数据入口：[2B/C 官方数据包](https://drive.google.com/file/d/160iHOqwR4FAghGshbnSNMwd0idrjZxTR/view?usp=sharing)。2026-10-20 核对时，`proj2c` 仍没有自己的 data，`proj2a/data` 则已经存在；不会因依赖 2A 模块就自动改变本阶段相对路径的查找位置。本次没有下载、复制或改动数据。

| 位置 | 应有内容 |
| --- | --- |
| `proj2c/src` | 本阶段 Java 源码 |
| `proj2c/tests` | 本阶段 JUnit 测试 |
| `proj2c/static` | 已提供的网页与脚本 |
| `proj2c/data/ngrams`、`proj2c/data/wordnet` | 数据文件；与 src、tests、static 平级 |

本阶段先核对：`data/ngrams/` 下的词频与年度总数，`data/wordnet/` 下成对的 synsets/hyponyms。EECS 测试另用 `data/ngrams/frequency-EECS.csv`、`data/wordnet/synsets-EECS.txt`、`data/wordnet/hyponyms-EECS.txt`；本轮核对的 `proj2a/data` 也不包含这三个 EECS 文件，所以不能把已有的 2A 数据当作完整的 2C 测试资料。

`./data/...` 和服务器的 `static` 都按进程 working directory 解释，不按 Java 文件所在位置解释。运行本阶段 Main 或 JUnit 时，将 working directory 设为 `E:/cs61b/skeleton-sp24/proj2c`。Main class 为 `main.Main`，使用目标项目模块的 classpath；三个目录有同名类，一次只启动一个占用 4567 的服务器。

沿用综合指南的 JDK 17 或更高版本建议；本地模块引用 `E:/cs61b/library-sp24`，不要仅因 FAQ 的旧库名改成其他学期依赖。文件存在不等于 IDEA 已正确导入。数据解压后核对最终路径，避免多套一层目录；保留仓库原有 `.gitignore`，不要强行上传大数据包。

本册使用五参数 `NgordnetQuery(words,startYear,endYear,k,ngordnetQueryType)`；类型为 `NgordnetQueryType.HYPONYMS` 或 `ANCESTORS`。保持本地 `browser` 包。

### 2.2 容易与原说明混淆的地方

| 项目 | 本册采用的规则 |
| --- | --- |
| 工厂包名 | 本地测试 import `main.AutograderBuddy`；不能照正文的旧包名改成 `proj2b.src.main`、`proj2c_testing` 等 |
| EECS 文件名 | 2B 评分段写过 `synonyms-EECS.txt`；本地 2C 测试用 `synsets-EECS.txt`，下载后核对实际文件 |
| 模块依赖 | 2026-10-20 的 `proj2c.iml` 只声明依赖 `proj2a`，已不依赖 `proj2b`；没有原版记录的 2B↔2C 循环。开发时可能借到 2A 的统计类，但按官方交付结构仍需迁入必要实现，不能把跨模块可见当成 2C 已有相应源码 |
| 路由 | 注册的是 `"hyponyms"`，不是 `"hyponym"`；2C 的祖先按钮也走这个路由并携带类型 |

### 2.3 要求标签与检查边界

- **【官方必做】**：正文明确的功能、限制或交付物。
- **【本地契约】**：当前签名、方法注释和提供测试确定的行为。
- **【建议】**：便于学习、实现、排错的顺序与组织方式；不是额外评分规则。
- **【自选】**：类名、内部结构、DFS/BFS、同频裁决等允许自行设计的部分。
- **【可选】**：完成主线之后的扩展，不计入必做完成标志。

每阶段的“完成标志”是供你实施和自查的目标，不是本次已通过的测试结果。文件准备、类能编译、单元测试、网页操作、性能与正式评测分别提供不同证据。

### 2.4 前置契约复习与后文共享图例

| 来自哪部分 | 2C 立即用到的契约 |
| --- | --- |
| 2A TimeSeries | 年份与数据对应、闭区间副本、缺失年不补零、运算不改输入 |
| 2A NGramMap | count 是原始次数，weight 是相对频率；未知词/无数据返回空序列；count/total 不暴露内部可变对象 |
| 2B WordNet | 输入词的全部词义 ID 作起点；沿真实边遍历并展开词，不从结果词跳入其他词义 |
| 2B 多词查询 | 交集发生在单词字符串层，不能在 ID 层先交集；包含起点不保证多词结果保留每个输入词 |
| 装配 | Handler 接收实例依赖，测试工厂使用传入路径，不启动服务器，不使用 static 全局 NGramMap |

后文使用与 2B 分册相同的人工图：正向边为 0→1、0→2、1→3、2→3，4 为孤立点。其直接父关系是 1→{0}、2→{0}、3→{1,2}；反向遍历从 3 能到 {3,1,2,0}，从 4 只能到 {4}。这不是官方 WordNet 原始数据。

若这些基础尚不明确，先回看导航中的 2A/2B 分册。2B/C 共享 Checkpoint 与设计文档需要在编码前处理；模板包含单词/多词下位词、单词/多词祖先和 k>0 的算法及结构说明。仅在有正式课程权限时进行相应提交；本次没有替你提交。

<a id="s3"></a>

## 3. 迁移 2A/B 并装配 2C

先看 [2C Main](src/main/Main.java)、[2C AutograderBuddy](src/main/AutograderBuddy.java)、[2C NgordnetQuery](src/browser/NgordnetQuery.java)。本阶段连接两个已完成的部分，先保持旧行为正确，再加新功能。

【官方必做】2C 需要把 2A 的 `TimeSeries`、`NGramMap` 放到自己的 `src/ngrams`，并把 2B 的实现与辅助类迁入。当前 2C 没有这些本地实现；IDE 因模块依赖能够找到别处类，不等于满足了交付结构。

本地工厂的固定入口是 `main.AutograderBuddy.getHyponymsHandler(String wordFile, String countFile, String synsetFile, String hyponymFile)`，返回类型为 `NgordnetQueryHandler`。参数顺序依次是词频文件、年度总数文件、词义集合文件、下位词边文件；不要只在 Main 选好文件，却让工厂继续使用硬编码路径或占位异常。

迁移时建议逐步做：

1. 保留 2A、2B 的原实现，复制必要业务类到 2C 对应源码包。
2. 保留 2C 已有 `browser` 包和五参数查询类型；不要整目录覆盖。
3. 给 2C 准备自己的数据目录，使四个数据路径各指向正确文件。
4. 在 2C 工厂中装配 WordNet、NGramMap 和 Handler；其参数顺序保持不变。
5. 在 2C Main 中装配相同职责的对象并注册 `hyponyms`。
6. 把 2B 单词/多词 k=0 用例带到 2C，作为回归检查；将工作目录改为 2C。

【建议】Handler 持有 WordNet 与 NGramMap 的实例引用；Main 和测试工厂负责选择文件。你可以将热门筛选放在另一个业务辅助类，让 WordNet 继续只负责语义查询。不要求把 NGramMap 塞进 Graph。

2C 骨架已有 Plotter、PlotDemo，但必做评分入口仍是 HyponymsHandler 及其依赖。把真实 History Handler 也迁入、恢复历史按钮是整合界面的选择，不要误以为 2C 要额外交一套 Hypohist 才能完成。

**完成标志：** 2C 的本地源码包含必要业务实现；目标模块使用自己的 query；工厂能装配对象；2C 中 k=0 的既有行为正确。检查 classpath 时防止碰巧载入 2A/B 的同名类，不能用跨模块成功掩盖缺文件。

<a id="s4"></a>

## 4. 热门词：k、区间计数与排序

### 4.1 热门筛选要接在哪一层

先看 [TestOneWordKNot0Hyponyms](tests/TestOneWordKNot0Hyponyms.java) 与 [官方 k 说明](https://sp24.datastructur.es/projects/proj2c/#handling-k--0)。本阶段依赖已正确计算的候选集合，以及 NGramMap 的区间原始计数。

完整行为按下表分支：

| 条件 | 使用哪些数据 | 应做什么 |
| --- | --- | --- |
| `k=0` | WordNet | 返回全部共同候选词；忽略年份，不要求词有 NGrams 记录 |
| `k>0` | WordNet + NGrams | 在共同候选词中按区间原始计数和筛选，剔除总数为零的词，至多取 k 个，最后字母排序 |

`k=0` 表示“不限数量”，不是“返回零个”。正文围绕非负 k 说明行为；负数 k、倒置年份、null query 等并未给出统一必做契约。可以自行设计健壮性处理，但不要把它写成课程已规定的行为，也不要让它干扰正常输入。

### 4.2 小例子：先求交集，再统计，再选，再显示

下面全是**人工数据**。假设两个输入词的下位词集合为：

| 查询词 | 完整下位词集合 |
| --- | --- |
| food | `{food,cake,cookie,snap,wafer,bread}` |
| cake | `{cake,cookie,snap,wafer,icing}` |
| 交集 | `{cake,cookie,snap,wafer}` |

bread 即使有十万次，也不是共同候选词，不能进入排名。指定区间 `[2000,2001]`，四个候选词的计数如下：

| 词 | 2000 原始计数 | 2001 原始计数 | 区间总数 | 是否进入热门候选 |
| --- | --- | --- | --- | --- |
| cake | 20 | 30 | 50 | 是 |
| cookie | 40 | 20 | 60 | 是 |
| snap | 0 | 无记录 | 0 | 否 |
| wafer | 无记录 | 50 | 50 | 是 |

food 已在它自己的完整集合中；它不在 cake 的集合中，所以多词交集不包含 food。“无记录”在**求和**时没有可加项，不需要为 TimeSeries 补零。若 `k=2`，cookie 必选，cake 与 wafer 同频占剩余名额。可以选择 cake，最终显示 `[cake, cookie]`；也可以选择 wafer，显示 `[cookie, wafer]`。两者都选两个，最后都按字母排列。

若 `k=10`，只有三个非零词，结果为 `[cake, cookie, wafer]`；若区间里全部为零，返回 `[]`。若 `k=0`，结果包括 snap，返回 `[cake, cookie, snap, wafer]`，完全不使用上表的计数来筛除它。

**为什么不能先各自选 top-k 再交集？** 人工例子：输入 a、b，完整集合 `H(a)={a,x,c}`、`H(b)={b,y,c}`，保留了查询词自身。区间次数 x=100、y=100、c=90，a 和 b 的次数均为零，k=1。正确共同候选只有 c，结果 `[c]`；若各自取 top-1 后得到 `{x}`、`{y}`，交集反而为空。

返回数量也不能只检查“没有超过 k”：当正频候选不少于 k 时，应选满 k 个；只有候选不足时才全部返回。因此数量应为 `min(k, 正频候选数)`，不能在有可用词时任意少返回。

### 4.3 为什么必须用 count，不能用 weight

人工数据：2000 年语料总量为 100，2001 年为 10000。词 a 只在 2000 年出现 10 次，词 b 只在 2001 年出现 20 次。

| 比较量 | a | b | 哪个更大 |
| --- | --- | --- | --- |
| 区间原始计数和 | 10 | 20 | b |
| 每年相对频率的和 | 0.1 | 0.002 | a |

本项目热门度采用第一行，所以 k=1 应选 b。不要复用 History 的 weight 作为排名输入；相同底层数据用于不同功能时，指标不同。

### 4.4 同频、排序与数值类型分别处理

【官方必做】计数总和不能因 int 溢出变成负数；复用 TimeSeries 的 Double 数据，累加器用 double 等合适类型。候选词在统计之前先去重，避免同一字符串出现于多个 synset 时被重复计算。

【官方 FAQ 自选】同频裁决不限，可以按字母、随机或其他方式决定；【建议】按字母裁决能让本地测试可重复。**最终显示的字母排序是必做，不因裁决自由而变为自由。**[2C FAQ](https://sp24.datastructur.es/projects/proj2c/faq/)

不要用 `Map<Double,String>` 或让排序集合只比较频次：两个词同频可能覆盖/合并成一个词。保存“词+总数”的记录，或让比较规则在频次相同时仍区分不同词。

【自选】在每词频次已算好的前提下，可以将 M 个正频候选排序后取前 k 个，排序约 `O(M log M)`。也可以用堆保存至多 `K=min(k,M)` 个词，筛选约 `O(M log(K+1))`，最后还需对入选词做约 `O(K log K)` 的字母排序；这避免将 k=1 错写成零成本。图遍历和频次求和的成本另外计算。先用易验证的结构完成正确性，再根据实际瓶颈决定是否优化。不要比较器里每比较一次就重算整段历史；先为每个词算一次总数。

### 4.5 热门查询的测试与完成标志

已有 `testActKNot0` 的方法名虽然含 act，实际输入是 **CS61A**，2010—2020，k=4，使用 `frequency-EECS.csv`、`synsets-EECS.txt`、`hyponyms-EECS.txt`，期待 `[CS170, CS61A, CS61B, CS61C]`。不能依据方法名猜测试内容。

【建议补测】多个词、三词交集、k=1、k 大于候选数、WordNet 有但 NGrams 没有的词、区间外才出现的词、记录存在但总数为零、单年/两端年份、大计数、同频词不丢失。构造同频测试时，若你没有选择固定裁决，就允许边界同频项多种合法选择，同时检查结果数量、候选资格、频次次序边界和最终字母顺序。

EECS 是人工课程依赖资料，名称大小写要保留。官方说明它不由线上 staff 网页提供；不能输入 CS61A 查不到，就认为本地实现一定有错。官方 food/cake 在指定完整词网和 `top_14377_words.csv`、1950—1990、k=5 时给出的期望是 `[cake, cookie, kiss, snap, wafer]`，这不是任何数据包都通用的答案。

**完成标志：** 单词和多词热门查询正确；k=0 回归正确；计数求和按闭区间；零频率词剔除；同频不丢词；先按热门度选，再按字母显示。此时只扩展正向下位词查询，下一步再换方向。

<a id="s5"></a>

## 5. 共同祖先：反向遍历与查询类型

### 5.1 共同祖先：沿逆向边使用同样的流程

先看 [NgordnetQueryType](src/browser/NgordnetQueryType.java)、[TestCommonAncestors](tests/TestCommonAncestors.java) 与 [官方共同祖先说明](https://sp24.datastructur.es/projects/proj2c/#finding-common-ancestors)。这个功能是 2C 必做，不是 Optional Features。

正向边 `p→c` 表示 c 是 p 的下位词。祖先查询从 c 向 p 走，读取反向邻接表。若加载时存 `children[p].add(c)`，对应就可以同时存 `parents[c].add(p)`；两个方向是否用一个图类或两个图实例可自选。

| 查询步骤 | 下位词 | 祖先 |
| --- | --- | --- |
| 找起点 | 输入词的全部词义 ID | 相同 |
| 沿邻接关系遍历 | 子节点方向 | 父节点方向 |
| 起点是否计入 | 是 | 是 |
| 将节点变成词集合 | 展开可达节点全部词 | 相同 |
| 多个输入词 | 词集合交集 | 相同 |
| k>0 | 对交集按区间 count 筛选 | 相同 |
| 最终输出 | 去重、字母排序 | 相同 |

在第 2.4 节人工图中，从 3 找祖先：逆向直接邻居是 `{1,2}`，它们再指向 0，得到 `{3,1,2,0}`；从 4 找祖先只有 `{4}`。如果遗漏反向边或仍用 children，3 会错误地只返回自身。

**共同祖先仍取词交集。** 人工例子：ID0 含 subject 并指向 ID1 math；ID2 也含 subject 并指向 ID3 test_subject。math 的祖先词含 subject，test_subject 的祖先词也含 subject，虽然两条分支的祖先 ID 不同，词交集仍有 subject。输出应为 `[subject]`。这正是 2B 单词层交集规则的再次应用。

也不要为了找共同祖先逐个扫描词典中每个词，再查询它是否包含所有输入词的下位词；完整词网中这种反复遍历会非常慢。逆向索引让查询只走相关可达区域。它找的是**全部共同祖先词**，不只是最近祖先，不是最短祖先路径题。

### 5.2 Handler 怎么知道用户想往哪个方向查

本地 query 提供 `q.ngordnetQueryType()`，值为 `HYPONYMS` 或 `ANCESTORS`。同一个 Handler 据此选择方向，然后复用“候选交集→可选热门筛选→排序输出”。不能靠是否填写年份、是否输入多个词或按钮文字猜类型。

本地 JavaScript 的 Common Ancestors 按钮设置类型为 `ANCESTORS`，**实际仍请求 `/hyponyms`**。虽然文件里声明过 `/ancestors` 常量，但该按钮没有使用它。将同一个完整 Handler 注册到 `hyponyms` 才与现有页面一致；无需为必做功能改 JS 或另造一个祖先路由。

这里有两层不同的默认值，运行时要分清：

| 入口或状态 | 实际年份/参数来源 |
| --- | --- |
| 当前 2B/C HTML 初次打开，年份输入框未改 | 页面已填入 `2000`、`2020`，会发送这些有效数值 |
| 年份输入框被清空，或请求参数缺失/不能解析为整数 | Java 解析器对 start/end 分别回退到 `1900`、`2020`；只替换解析失败的那一项 |
| k 为空或解析失败 | 解析器回退到 `0` |
| 查询类型缺失或无效 | 解析器回退到 `HYPONYMS` |
| 直接 JUnit `new NgordnetQuery(...)` | 使用测试传入的值，不执行网页参数解析或默认回退 |

所以“没有改页面初始年份”和“没有提供有效年份”不同。直接测试必须明确传入年份、k、类型；本地解析器也不会自动修正可以成功解析但顺序倒置的年份或负数 k。

### 5.3 祖先查询的检查

已有测试仅检查 16 节点词网中 adjustment 的单词祖先、k=0，期待：

```text
[adjustment, alteration, event, happening, modification, natural_event, occurrence, occurrent]
```

官方另给 change 的祖先和 `[change,adjustment]` 的共同祖先例子，可补成精确匹配测试；后者应为 `[alteration, event, happening, modification, natural_event, occurrence, occurrent]`。它不是简单把单词查询自身全部加入多词结果。

【建议补测】多义词、两个/三个输入词、不同节点共享同名祖先、没有共同祖先、未知词、叶子、根节点、祖先 k>0、零频率、同频、同一 Handler 交替正向/反向请求。每次请求的 visited、候选集、频次应独立，不能保留上次查询的残留状态。

**完成标志：** 正反方向均正确；类型分支有效；祖先 k=0 和 k>0 都有运行证据；测试工厂四路径生效；正向查询无回归。到这里才实现了 Spring 2024 Project 2 的全部主要功能。

<a id="s6"></a>

## 6. 测试、综合运行、性能与排查

### 6.1 提供测试与缺口

| 测试文件 | 已有检查 | 主要缺口 |
| --- | --- | --- |
| 2C `TestOneWordKNot0Hyponyms` | EECS 的 CS61A、2010—2020、k=4 | 多词热门、零频率、较大 k、其他同频边界 |
| 2C `TestCommonAncestors` | adjustment 的祖先、k=0 | 多词祖先与全部祖先 k>0 |

两个文件各一个 sanity check，祖先测试仅覆盖单词 k=0。补测多词热门、祖先 k>0、零频率、同频、区间端点与交替类型；也把 2B 的 k=0 用例迁入 2C 做回归。具体新增测试类名自选。

### 6.2 两个入口的综合检查

先检查直接调用入口：用本地工厂和四个明确的路径得到 Handler，再像提供测试那样构造 `new NgordnetQuery(words,startYear,endYear,k,NgordnetQueryType.ANCESTORS)` 或 HYPONYMS 查询并调用 `handle(q)`。单元测试不用启动服务器；核对返回的普通字符串和预期结果。使用两套不同文件创建两个 Handler，再交替查询，可检查路径参数是否生效、数据是否串用。

再检查浏览器入口。两条入口要使用同样的业务逻辑，不能以网页成功代替工厂测试：

用准备好的四个文件启动对应 Main，打开 [2C 页面文件](static/ngordnet.html) 或访问 [2C 本机地址](http://localhost:4567/ngordnet.html)：

| 顺序 | 操作 | 应观察什么 |
| --- | --- | --- |
| 1 | Hyponyms，k=0 | 与同数据的 2B 结果一致 |
| 2 | Hyponyms，k>0，固定区间 | 返回 `min(k,正频候选数)` 个词，按区间次数选取、按字母显示 |
| 3 | 改区间，保持词与 k | 用独立计数核对排名是否应变化；结果不一定必然变化 |
| 4 | Common Ancestors，k=0 | 走父方向，不继续使用上次下位词候选 |
| 5 | Common Ancestors，k>0 | 对祖先候选采用相同热门规则 |
| 6 | 交替点击两种查询 | 类型与查询状态正确切换，无残留 |

网页检查是学习路线中的手动功能验证，**当前 2A/B/C 说明没有规定必须交 TA 现场演示或录屏**。本次也没有实际操作这些按钮。

### 6.3 性能自查

本项目没有要求提交实验数据表或性能分析报告。下面是【建议】的工程检查，用于确认加载目标与超时风险，不增加课程交付物，也不编造结果。

计时分别包围“构造数据对象”和“已构造对象上的查询”；不要把下载数据、打开 IDE 或用户思考输入的时间算进去。相同 JDK、内存设置、文件组合与查询才适合比较。首次运行与 JVM 预热后的运行应区分，不能把缓存后的单次最快值当普遍性能。

| 项目 | 输入/操作流程 | 记录项（待填） | 主要观察 |
| --- | --- | --- | --- |
| NGramMap 加载 | 构造时给 `top_14377_words.csv` 与 `total_counts.csv`；构造结束后停止计时 | JDK：___；文件：___；耗时：___；内存设置：___ | 对照官方约 60 秒目标 |
| WordNet 加载 | 给成对的完整 synsets/hyponyms，建立索引与正反边 | 文件：___；耗时：___ | 是否只初始化一次 |
| k=0 重复查询 | 在同一个 Handler 上多次查询同一/不同词 | 查询：___；次数：___；每次耗时：___ | 是否每次重读文件或重建图 |
| k>0 查询 | 固定四文件、词、区间和 k | 参数：___；候选数：___；耗时：___ | 是否反复求同一词历史、比较器中重算 |
| 祖先查询 | 同数据先单词后多词，重复查询 | 参数：___；耗时：___ | 是否用逆向可达区域，而非全词典试探 |

图遍历约为相关可达区域的 O(Vr+Er)，词集合展开和交集另计。FAQ 提及的 60000ms 超时不能当成“单次小于一分钟即可通过”的保证；避免重读文件、重建图、重复展开或全词典祖先扫描。

### 6.4 排查表

| 失败表现 | 优先检查 |
| --- | --- |
| 找不到 `data/...` 或静态页面 404 | 目标模块、working directory、是否套了多层解压目录 |
| `record` 语法错误 | JDK 与 IDE language level |
| 缺 spark/Gson/XChart/JUnit/Truth | `.iml` 指向的 `library-sp24` 与 IDE 实际库导入 |
| 2C 缺 NGramMap 或加载错 Main | 迁入的源码、当前 2C→2A 依赖、同名类及实际 classpath；不要沿用旧版循环依赖诊断 |
| 点击按钮没响应/连接拒绝 | 服务器是否启动，页面是否对应项目，4567 是否已有旧进程 |
| `Failed to bind ...4567`、Spark 启动失败 | 停止自己先前启动的 Main；避免并行占用同端口；必要时再重启 IDE |
| 服务日志很多但没结果 | 看是否有真正异常、对应请求是否进入 Handler；启动提示只确认连接层 |
| 未改输入框却按 1900 开始统计 | 混淆页面初始的 2000 与解析器失败回退的 1900 |
| 年度总数 `NumberFormatException` 或热门计数变负 | 是否用 int 解析/累加过大计数 |
| 下位词丢掉一个含义 | `word→id` 是否只保存一个 ID |
| 下位词多出不相关词 | 是否从输出词再次跳到所有同名 synsets |
| 重复父行的某个分支不见 | 加边是否覆盖先前邻接集合 |
| 到叶子/未知词抛 NullPointerException | 词索引或邻接 `get` 是否返回 null 后仍迭代 |
| 多词错误返回空 | 是否对 ID 取交集、以空集开始 retainAll、使用一个 `"a,b"` 列表元素 |
| k=0 也丢失没词频的词 | 是否无条件执行 NGrams 过滤 |
| top-k 不合理但 weight 看着正常 | 是否用 weight 排名、先各词 top-k 再交集、区间端点不含 |
| 同频词数量少于应有数量 | 是否以频次作唯一 Map 键或比较器只比较频次 |
| 排名选对但字符串顺序不对 | 是否忘了对入选词再次按字母排序 |
| 祖先永远只有输入词 | 是否仍遍历 children，或 reverse adjacency 未加载 |
| 连续查询互相污染 | visited/结果是否错误地保存在共享状态而未重置 |
| 线上示例与本地预期不同 | 四个文件是否相同；线上是否有该人工数据；不要先推断代码错 |
| 超时或内存错误 | 分开测加载与查询；检查重读/重建/重复列表；有证据后再判断平台问题 |

<a id="s7"></a>

## 7. 交付范围与正式提交

| 范围 | 必做实现/交付 | 官方评分说明 |
| --- | --- | --- |
| 2C | 迁入的 2A/B 业务类、增强 Handler 与辅助类；有效工厂 | 编码 25 分：固定热门 20%，随机热门 30%，共同祖先 50% |
| 2B/C 共同准备 | Checkpoint；按官方模板提交约 1—2 页设计文档 | Checkpoint 5 分，设计文档 5 分；不要按 B/C 重复计算 |

Handler 是评测入口，不是只交一个孤立文件；它依赖的辅助类与统计类也必须齐全。2B/C 共用的 Checkpoint 与设计文档不要重复计算。历史截止日为 2024-03-15（准备材料）、2024-04-01（编码），不是现在的截止日。

综合指南记录的归档评测额度为初始八个 token、每个 24 小时恢复；当前账户和作业是否仍可用，应以课程/Gradescope 实际状态为准，本次没有核对。没有新增性能报告、TA 现场演示或录像交付要求。测试和计时记录是自查证据。

实现后处理本阶段 TODO，保留既有 public API。将来正式提交时，先确认目标阶段与文件范围，再按课程要求保存、add/commit、push，并在对应作业提交；本次只写文档，未执行这些操作。

<a id="s8"></a>

## 8. 可选整合：Hypohist 等

依据 [Spring 2024 Optional Features](https://sp24.datastructur.es/projects/proj2c/optional_features/)，可选内容包括整合原来的 History 功能、添加 Hypohist 图像/文字、其他查询或可视化。不要把已属必做的 Common Ancestors 混进可选清单。

Hypohist 的合理顺序是：先求共同下位词 → 按区间 **count** 选热门词 → 再用这些词的 **weightHistory** 画曲线。这两个指标各有用途，不能因为曲线是相对频率就把筛选也换成相对频率。

可选按钮涉及三层：HTML 有按钮、JavaScript 绑定点击并请求路径、Main 注册相应 Handler。只改其中一层不会自动连通。当前 2B/C 页面已有 Hypohist 按钮/JS，仍不意味着已有对应 Java 功能。官方对 Hypohist 的 k=0 留给学生自选合理行为。

其他可选想法有限制距离 d、词的排除操作、语义图展示、路径查询等。它们需要单独定义行为和检查，不应改变必做入口的既定结果。

<a id="s9"></a>

## 9. 本阶段完成清单

这些框是你实现后的验收目标。本次拆分没有将任何一项标为“已通过”。

### 9.1 必做与本地接口

- [ ] 使用 Spring 2024 资料；分清 2A/B/C，未混用 Gitlet 或其他学期 API。
- [ ] 目标目录的 data 与 src/static 平级；文件组合和 working directory 正确。
- [ ] 复核共享 Checkpoint 与设计文档已按 2B/C 要求处理；没有重复计算两份成绩。
- [ ] 2C 有自己的必要 2A/B 实现，保留五参数 query 和本地包名。
- [ ] 2C 工厂使用四个传入路径；不同 Handler 不共享错误的全局数据。
- [ ] k>0 先交集，再按闭区间 count 求和；零频率词剔除；返回数量为 `min(k,正频候选数)`。
- [ ] 同频词不会被结构覆盖；最终显示仍按字母排序；计数不发生 int 溢出。
- [ ] ANCESTORS 走逆向边，包含起点，返回全部共同祖先词而非最近祖先。
- [ ] 单词/多词祖先、祖先 k>0 与下位词 k=0/k>0 都正确。
- [ ] 文件和图仅初始化一次，重复查询不重建；祖先查询不扫描全词典试探。
- [ ] 未把 data 压缩包/大文件强行纳入 Git；必要业务依赖齐全；实现后处理 TODO。
- [ ] 有正式课程提交权限时，完成每个要求的阶段与文档提交；记录真实 Gradescope 结果。

### 9.2 验证建议

- [ ] 参考官方测试段扩充提供测试，并新建对应场景的测试文件；被注释的测试实际启用后才计入证据。
- [ ] 小规模、接口装配、网页与大文件检查分别记录；不把局部通过当正式评测结论。
- [ ] 用实际计时核对大词表加载目标；将性能自查记录与课程交付物区分。
- [ ] 分清页面初始年份、解析器失败回退和直接构造 query 的参数。
- [ ] 如需容忍空白行，检查文件中间和末尾的空白，不能假设 `!isEmpty()` 会跳过全部空行。

### 9.3 可选与自选

- [ ] 【自选】同频裁决方式已确定，仍按字母输出。
- [ ] 【可选】Hypohist、距离/排除/路径等扩展有独立定义与测试。

<a id="s10"></a>

## 10. 来源与原版导航

- [Spring 2024 Project 2C 正文](https://sp24.datastructur.es/projects/proj2c/)
- [Project 2C FAQ](https://sp24.datastructur.es/projects/proj2c/faq/)
- [统计层依据：Project 2A 正文](https://sp24.datastructur.es/projects/proj2a/)
- [关联 Project 2B 正文](https://sp24.datastructur.es/projects/proj2b/)
- [官方设计模板](https://docs.google.com/document/d/1Vx7QAz4HFN0rEFFEt5rocY2X5AWVcIFFpRmD8vhegOM/edit?usp=sharing)
- [Checkpoint 入口](https://www.gradescope.com/courses/708063/assignments/4133684)
- [设计文档提交入口](https://www.gradescope.com/courses/708063/assignments/4187810)
- [WordNet Visualizer](https://www.qxbytes.com/wordnet/)
- [Staff Solution](https://ngordnet.datastructur.es/)
- [官方可选扩展](https://sp24.datastructur.es/projects/proj2c/optional_features/)
- [保留的综合原版](Project2-Ngordnet中文学习与实现指南.md)

本册的实现步骤与人工小例子继承综合原版。本轮重新取得 2A/B/C 正文与 FAQ，其正文文本与原审计快照一致；并核对当前模块依赖、接口、提供测试、章节引用及文件链接。使用 2A 已有的 16 节点词网独立复核了 adjustment 和多词共同祖先示例；没有运行你的 Handler、项目 JUnit、完整网页、性能或 Gradescope。EECS 数据仍未准备，未将其示例标为本地通过。Checkpoint/设计提交需要自己的课程账户；未读取登录后的题目，也未执行线上查询。综合原版保持不变。
