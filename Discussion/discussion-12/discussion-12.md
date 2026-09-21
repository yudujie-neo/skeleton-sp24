# Discussion 12 — More Sorting / 更多排序

> UC Berkeley CS 61B, Spring 2024
>
> Discussion date: April 15, 2024

## 官方资料

- [Regular PDF](https://sp24.datastructur.es/assets/discussions/regular12.pdf)
- [Regular Solutions PDF](https://sp24.datastructur.es/assets/discussions/regular12sol.pdf)
- [Exam Prep PDF](https://sp24.datastructur.es/assets/discussions/examlevel12.pdf)
- [Exam Prep Solutions PDF](https://sp24.datastructur.es/assets/discussions/examlevel12sol.pdf)

## IntelliJ 使用说明

代码题已经迁移到 `code/questions/` 与 `code/solutions/`。Java 文件保留完整英文原题或官方答案，并单独提供中文翻译或解析；它们是代码题的权威版本，本文不再维护重复的大段代码与答案。

如 IntelliJ 尚未识别这些目录，可分别右键 `code/questions` 和 `code/solutions`，选择 **Mark Directory as → Sources Root**。所有类名均包含 Discussion 编号、Regular/ExamPrep、题号和主题，因此也可以直接与其他 Discussion 的文件一同编译。

## Regular

### Question 1 — Quicksort / 快速排序

- 1a：用首元素枢轴和三路分区追踪快速排序。
- 1b：分析 Hoare 分区的最好/最坏运行时间，并比较全相等与已排序输入。
- 1c：给出降低最坏情况概率的两种技术。
- [Question Java](code/questions/Dis12RegularQ1Quicksort.java)
- [Solution Java](code/solutions/Dis12RegularQ1QuicksortSolution.java)

### Question 2 — Radix Sorts / 基数排序

- 2a：记录 LSD 基数排序每一轮的数组状态。
- 2b：记录 MSD 基数排序的递归分桶状态。
- 2c：比较 LSD/MSD 的最好、最坏复杂度与稳定性。
- 2d：讨论基数排序的适用边界。
- [Question Java](code/questions/Dis12RegularQ2RadixSorts.java)
- [Solution Java](code/solutions/Dis12RegularQ2RadixSortsSolution.java)

### Question 3 — Sort Identification / 排序识别

- 3a–3e：根据数组的若干中间状态识别 Quicksort、Merge Sort、Heapsort、MSD Radix Sort 或 Insertion Sort。
- [Question Java](code/questions/Dis12RegularQ3SortIdentification.java)
- [Solution Java](code/solutions/Dis12RegularQ3SortIdentificationSolution.java)

### Question 4 — Conceptual Comparison Sorts Extra / 比较排序概念补充

- 4a：构造插入排序的 5 元素最坏输入。
- 4b：比较选择归并排序与快速排序的理由。
- 4c：按复杂度、交换次数和比较行为匹配排序算法。
- [Question Java](code/questions/Dis12RegularQ4ComparisonSorts.java)
- [Solution Java](code/solutions/Dis12RegularQ4ComparisonSortsSolution.java)

## Exam Prep

### Question 1 — Sorted Runtimes / 排序运行时间

- 1a：归并排序在比例阈值处切换到插入排序。
- 1b：用线性时间中位数算法选择快速排序枢轴。
- 1c：使用最小堆、常数额外空间完成堆排序。
- 1d：利用已知逆序对数量选择最优排序算法。
- [Question Java](code/questions/Dis12ExamPrepQ1SortedRuntimes.java)
- [Solution Java](code/solutions/Dis12ExamPrepQ1SortedRuntimesSolution.java)

### Question 2 — MSD Radix Sort / MSD 基数排序

递归实现等长字符串列表的 MSD 基数排序；题目文件以中性、可编译脚手架代替 PDF 中无法编译的填空横线。

- [Question Java](code/questions/Dis12ExamPrepQ2MSDRadixSort.java)
- [Solution Java](code/solutions/Dis12ExamPrepQ2MSDRadixSortSolution.java)

### Question 3 — Shuffled Exams / 被打乱的试卷

使用 `Exam`、`Student`、原下标包装器和固定长度十进制键，设计线性时间的学生数组恢复算法，同时保持试卷数组不变。

- [Question Java](code/questions/Dis12ExamPrepQ3ShuffledExams.java)
- [Solution Java](code/solutions/Dis12ExamPrepQ3ShuffledExamsSolution.java)

## 保留在 Markdown 的内容

本 Discussion 的四份官方 PDF 共 18 页，没有必须依赖树形图、手绘图、内存图或其他不可忠实代码化的图示。原 PDF 中的表格、数组状态和代码填空都已迁移到对应 Java 文件；`assets/` 无需新增图片，继续保留 `.gitkeep`。

## 完整性检查

- Regular：保留 Question 1–4 及全部小问 1a–1c、2a–2d、3a–3e、4a–4c。
- Exam Prep：保留 Question 1–3 及全部小问 1a–1d、Question 2、Question 3。
- 每个顶层 Question 都有一份可独立编译的 Question 文件和一份 Solution 文件。
- Question 文件不包含官方答案；Solution 文件完整保留官方英文答案。
- 官方原文中的堆排序 “bubbling up”、归并 run 数量、逆序对证明索引顺序和 Shuffled Exams 回填表述均原样保留；说明只出现在 `Supplemental analysis / 补充分析（非官方答案）` 区域。
- `assets/.gitkeep` 保留；本次没有需要新增的图片资源。
