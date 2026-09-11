# Discussion 12 — More Sorting / 更多排序

> UC Berkeley CS 61B, Spring 2024
>
> Discussion date: April 15, 2024

## Regular

### Question 1 — Quicksort / 快速排序

#### 1a

Sort the following unordered list using Quicksort. Assume that we always choose first element as the pivot and that we use the 3-way merge partitioning process described in lecture. Show the steps taken at each partitioning step.

`18, 7, 22, 34, 99, 18, 11, 4`

> **中文翻译：** 使用快速排序对上述无序列表排序。假设始终选择第一个元素作为枢轴，并使用课堂所述的三路合并分区过程。展示每次分区步骤后的结果。

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

```text
-18-, 7, 22, 34, 99, 18, 11, 4
-7-, 11, 4 | 18, 18 | 22, 34, 99
4, 7, 11, 18, 18 | -22-, 34, 99
4, 7, 11, 18, 18, 22 | -34-, 99
4, 7, 11, 18, 18, 22, 34, 99
```

#### 解析

第一轮以 `18` 为枢轴，把元素分成小于、等于和大于三组；两个 `18` 会一起进入中间组，之后不再递归。左组以 `7`、右组依次以 `22` 和 `34` 继续分区。三路分区对重复键尤其有效，因为所有等于枢轴的元素可以一次固定。

若每次分区平衡，递归深度为 $\Theta(\log N)$，总时间为 $\Theta(N\log N)$；持续选到极值则可能退化为 $\Theta(N^2)$。

#### 考点

- 三路快速排序
- 枢轴与分区
- 重复键处理

</details>

#### 1b

What is the best and worst case running time of Quicksort with Hoare Partitioning on $N$ elements? Given the two lists `[4, 4, 4, 4, 4]` and `[1, 2, 3, 4, 5]`, assuming we pick the first element as the pivot every time, which list would result in better runtime?

> **中文翻译：** 对 $N$ 个元素使用 Hoare 分区的快速排序，其最好和最坏运行时间分别是多少？对于 `[4, 4, 4, 4, 4]` 与 `[1, 2, 3, 4, 5]`，若每次都选择第一个元素作为枢轴，哪个列表的运行时间更好？

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

Best: $\Theta(N\log N)$. Running Quicksort on a list that has a pivot splits the partition exactly in half will result in $\Theta(\log N)$ levels, with the same amount work as above (i.e. $\Theta(N)$ at each level). For example, `[3, 1, 2, 5, 4]`. An alternative case is when we have all of the same element in the array (i.e. `[4, 4, 4, 4, 4]`), since the two pointers in Hoare partitioning always end up in the middle.

Worst: $\Theta(N^2)$. In general, the worst case is such that the partioning scheme repeatedly partions an array into one element and the rest.

Running Quicksort on a sorted list will take $\Theta(N^2)$ if the pivot chosen is always the first or last in the subarray: `[1, 2, 3, 4, 5]`. At each level of recursion, you will need to do $\Theta(N)$ work, and there will be $\Theta(N)$ levels of recursion. This sums up to $1 + 2 + \cdots + N$.

#### 解析

Hoare 分区中的双指针从两端向中间移动。对全相等数组，课程采用的实现会得到接近中点的分割，因此递归较平衡；升序数组配合首元素枢轴则不断产生极不平衡的子问题。因此 `[4, 4, 4, 4, 4]` 更快。

#### 考点

- Hoare 分区
- 递归树高度
- 输入次序与枢轴选择

</details>

#### 1c

What are two techniques that can be used to reduce the probability of Quicksort taking the worst case running time?

> **中文翻译：** 哪两种技术可以降低快速排序出现最坏运行时间的概率？

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

1. Randomly choose pivots.
2. Shuffle the list before running Quicksort.

#### 解析

两种方法都让枢轴相对于输入排列具有随机性，避免对手输入或已有顺序持续触发极端分区。它们不能消除理论上的 $\Theta(N^2)$ 最坏情况，但可使其概率很低，并带来 $\Theta(N\log N)$ 的期望时间。

#### 考点

- 随机化算法
- 随机枢轴
- 预洗牌

</details>

### Question 2 — Radix Sorts / 基数排序

#### 2a

Sort the following list using LSD Radix Sort with counting sort. Show the steps taken after each round of counting sort. The first row is the original list and the last two rounds are already filled for you.

> **中文翻译：** 使用以计数排序为子程序的 LSD 基数排序处理下列列表。展示每轮计数排序后的结果。第一行是原始列表，最后两轮已给出。

| Round | 1 | 2 | 3 | 4 |
|---:|---:|---:|---:|---:|
| Original | 30395 | 30326 | 43092 | 30315 |
| 1 |  |  |  |  |
| 2 |  |  |  |  |
| 3 |  |  |  |  |
| 4 | <u>3031</u>5 | <u>3032</u>6 | <u>3039</u>5 | <u>4309</u>2 |
| 5 | <u>30315</u> | <u>30326</u> | <u>30395</u> | <u>43092</u> |

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

The underlined sections denote the digits that have already been sorted.

| Round | 1 | 2 | 3 | 4 |
|---:|---:|---:|---:|---:|
| Original | 30395 | 30326 | 43092 | 30315 |
| 1 | 4309<u>2</u> | 3039<u>5</u> | 3031<u>5</u> | 3032<u>6</u> |
| 2 | 303<u>15</u> | 303<u>26</u> | 430<u>92</u> | 303<u>95</u> |
| 3 | 43<u>092</u> | 30<u>315</u> | 30<u>326</u> | 30<u>395</u> |
| 4 | 3<u>0315</u> | 3<u>0326</u> | 3<u>0395</u> | 4<u>3092</u> |
| 5 | <u>30315</u> | <u>30326</u> | <u>30395</u> | <u>43092</u> |

#### 解析

LSD 从个位开始，依次按十位、百位、千位、万位执行稳定的计数排序。稳定性保证上一轮已经确定的低位顺序在下一轮按更高位分组时仍被保留。处理完 $W=5$ 位后得到完整数值顺序。

#### 考点

- LSD 基数排序
- 稳定计数排序
- 从低位到高位

</details>

#### 2b

Sort the following list using MSD Radix Sort with counting sort. Show the steps taken after each round of counting sort. The first row is the original list and the first round is already filled for you.

> **中文翻译：** 使用以计数排序为子程序的 MSD 基数排序处理下列列表。展示每轮计数排序后的结果。第一行是原始列表，第一轮已给出。

| Round | Result |
|---:|---|
| Original | `21295 22316 30753 21248 30751` |
| 1 | <u>2</u>1295 <u>2</u>2316 <u>2</u>1248 &vert; <u>3</u>0753 <u>3</u>0751 |
| 2 |  |
| 3 |  |
| 4 |  |
| 5 |  |

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

| Round | Result |
|---:|---|
| Original | `21295 22316 30753 21248 30751` |
| 1 | <u>2</u>1295 <u>2</u>2316 <u>2</u>1248 &vert; <u>3</u>0753 <u>3</u>0751 |
| 2 | <u>21</u>295 <u>21</u>248 &vert; <u>22</u>316 &vert; <u>30</u>753 <u>30</u>751 |
| 3 | <u>212</u>95 <u>212</u>48 &vert; <u>223</u>16 &vert; <u>307</u>53 <u>307</u>51 |
| 4 | <u>2124</u>8 &vert; <u>2129</u>5 &vert; <u>2231</u>6 &vert; <u>3075</u>3 <u>3075</u>1 |
| 5 | <u>21248</u> &vert; <u>21295</u> &vert; <u>22316</u> &vert; <u>30751</u> &vert; <u>30753</u> |

#### 解析

MSD 先按最高位分组，然后只在各组内部递归处理下一位。竖线表示已经分开的递归桶，不同桶之后不会再混合。单元素桶可以立即停止；有共同前缀的桶继续向右比较字符。

#### 考点

- MSD 基数排序
- 前缀分桶
- 递归子问题

</details>

#### 2c

Give the best case runtime, worst case runtime, and whether or not the sort is stable for both LSD and MSD radix sort. Assume we have $N$ elements, a radix $R$, and a maximum number of digits in an element $W$.

> **中文翻译：** 对 LSD 和 MSD 基数排序，分别给出最好运行时间、最坏运行时间以及是否稳定。假设有 $N$ 个元素，基数为 $R$，元素的最大位数为 $W$。

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

| Sort | Time Complexity (Best) | Time Complexity (Worst) | Stability |
|---|---:|---:|:---:|
| LSD Radix Sort | $\Theta(W(N + R))$ | $\Theta(W(N + R))$ | Yes |
| MSD Radix Sort | $\Theta(N + R)$ | $\Theta(W(N + R))$ | Yes |

#### 解析

LSD 必须处理全部 $W$ 位，每轮稳定计数排序花费 $\Theta(N+R)$。MSD 若第一轮就把元素分成无需继续处理的桶，最好只需一轮；最坏情况下许多元素共享长前缀，需要处理全部 $W$ 位。这里的稳定性建立在每轮子排序稳定且递归拼接保持桶内顺序的前提上。

#### 考点

- $N、R、W$ 参数化复杂度
- LSD 与 MSD 的提前停止差异
- 稳定性

</details>

#### 2d

We saw in part (c) that radix sort has good runtime with respect to the number of elements in the list. Given this fact, can we say that radix sort is the best sort to use?

> **中文翻译：** 从 2c 可见，基数排序相对于列表元素数量具有良好运行时间。能否据此断定基数排序是最好的排序方法？

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

No. Though radix sort runs linear with respect to the number of elements in the list, the runtime also depends on the size of the radix $R$ and the length of the longest ”word” $W$ (or the number of digits in a number). Additionally, it is not always possible to use radix sort, because not all objects can be split up into digits. However, comparison sorts can be used on any object that defines a `compareTo` method, and would work well with `compareTo` methods that are fast.

#### 解析

“对 $N$ 线性”并不代表总成本总是小：$R$ 很大或键很长时，$W(N+R)$ 仍可能昂贵。基数排序还要求键能拆成有限位置和基数，而比较排序只需定义一致的比较关系。

#### 考点

- 非比较排序的适用条件
- 隐藏参数 $R$ 与 $W$
- 算法选择的工程权衡

</details>

### Question 3 — Sort Identification / 排序识别

Match the sorting algorithms to the sequences, each of which represents several intermediate steps in the sorting of an array of integers. Assume that for quicksort, the pivot is always the first item in the sublist being sorted. Note that these steps are not necessarily the first few intermediate steps and there may be steps which are skipped.

Algorithms: Quicksort, Merge Sort, Heapsort, MSD Radix Sort, Insertion Sort

> **中文翻译：** 将排序算法与各组整数数组的若干中间状态匹配。快速排序始终选择当前子列表的第一个元素作为枢轴。这些状态不一定是最初几步，并且可能跳过了一些步骤。候选算法为快速排序、归并排序、堆排序、MSD 基数排序和插入排序。

#### 3a

```text
12, 7, 8, 4, 10, 2, 5, 34, 14
7, 8, 4, 10, 2, 5, 12, 34, 14
4, 2, 5, 7, 8, 10, 12, 14, 34
```

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

Quicksort. A pattern we can see is that most of the elements remain in the same order relative to one another, but the first element keeps moving around. Taking a closer look at this element, we note that everything to the left is less than it, and everything to the right is greater than, indicating QuickSort.

#### 解析

首元素 `12` 分区后左侧都较小、右侧都较大；随后左分区继续以其首元素递归。枢轴逐步进入最终位置，而其余元素的相对次序大体保留。

#### 考点

- 快速排序状态识别
- 枢轴最终位置

</details>

#### 3b

```text
23, 45, 12, 4, 65, 34, 20, 43
4, 12, 23, 45, 65, 34, 20, 43
```

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

Insertion Sort. A surefire way of identifying insertion sort is the fact it slowly builds a partially sorted array on the left hand side, which is exactly what occurs here.

Another solution is merge sort (with a recursive implementation), where the left half has already been fully merge-sorted.

#### 解析

左侧五个元素已经有序、右侧保持原顺序，符合插入排序扩大有序前缀的状态；若递归归并排序恰好先完成左半部分，也能出现同一状态。因此两种答案都必须保留。

#### 考点

- 插入排序有序前缀
- 中间状态的不唯一性
- 官方备用答案

</details>

#### 3c

```text
12, 32, 14, 11, 17, 38, 23, 34
12, 14, 11, 17, 23, 32, 38, 34
```

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

MSD Radix Sort. We first see that the numbers have been moved all over the place, which is odd, but taking a closer look at how the numbers are oriented, we see that all the tens digits are ordered in ascending order (10s, then 20s and 30s). This is immediately indicative of MSD Radix Sort, sorting from the leftmost digit first.

#### 解析

十位已经按 1、2、3 分桶，而相同十位内部尚未全部按个位排好，说明算法先处理最高有效位。

#### 考点

- MSD 的高位优先特征
- 数字前缀分桶

</details>

#### 3d

```text
45, 23, 5, 65, 34, 3, 76, 25
23, 45, 5, 65, 3, 34, 25, 76
5, 23, 45, 65, 3, 25, 34, 76
```

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

Merge Sort. We notice that the numbers stay relatively close to where they begin, but we also see little sorted runs inside the array, like `(23, 45)` and `(5, 65)` for example. These small sorted subarrays and the merging of the subarrays in halves of the array is indicative of Merge Sort.

#### 解析

先出现长度为 2 的有序小段，再合并成更长的有序半区；各元素主要在所属子数组附近移动，这是归并排序的典型中间状态。

#### 考点

- 有序 runs
- 自底向上合并

</details>

#### 3e

```text
23, 44, 12, 11, 54, 33, 1, 41
54, 44, 33, 41, 23, 12, 1, 11
44, 41, 33, 11, 23, 12, 1, 54
```

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

Heapsort. Heapsort always heapifies the array first, which on the second line we see the maximum element, 54, at the start of the array. Heapifying usually shuffles around the array, which we also see. Placing the maximum element at the back of the array and bubbling up the 44 seals the deal that this is heapsort.

#### 解析

第二行是最大堆的数组表示，根为全局最大值 `54`；第三行把 `54` 固定到末尾，并恢复剩余堆，因此可识别为堆排序。

**补充分析（非官方答案）：** 官方最后一句写的是 “bubbling up the 44”。最大值移到末尾后，`44` 被交换到堆顶；恢复最大堆通常应称为让新根向下调整（bubble down / sink），而不是向上调整。原文已保留，此处只说明术语问题。

#### 考点

- 最大堆数组表示
- 删除堆顶与下沉
- 官方术语辨析

</details>

### Question 4 — Conceptual Comparison Sorts Extra / 比较排序概念补充

Answer the following questions regarding various sorting algorithms that we’ve discussed in class. If the question is T/F and the statement is true, provide an explanation. If the statement is false, provide a counterexample.

> **中文翻译：** 回答关于课堂所讨论排序算法的问题。若题目为判断题且陈述为真，请解释；若为假，请给出反例。

#### 4a

Give a 5 integer array that elicits the worst case runtime for insertion sort.

> **中文翻译：** 给出一个含 5 个整数、能触发插入排序最坏运行时间的数组。

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

A simple example is: `5 4 3 2 1`. Any 5 integer array in descending order would work.

#### 解析

降序输入具有最多的逆序对，每个新元素都要越过此前全部元素，因此插入排序耗时 $\Theta(N^2)$。

#### 考点

- 插入排序最坏输入
- 逆序对

</details>

#### 4b

Give some reasons as to why someone would use mergesort over quicksort.

> **中文翻译：** 给出一些选择归并排序而不是快速排序的理由。

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

Some possible answers: mergesort has $\Theta(N\log N)$ worst case runtime versus quicksort’s $\Theta(N^2)$. Mergesort is stable, whereas quicksort typically isn’t. Mergesort can be highly parallelized because as we saw in the first problem the left and right sides don’t interact until the end. Mergesort is also preferred for sorting a linked list.

#### 解析

归并排序提供确定的最坏时间保证、易于稳定实现，左右递归可并行，链表合并又能通过重连指针高效完成。代价是数组版本通常需要 $\Theta(N)$ 辅助空间。

#### 考点

- 最坏时间保证
- 稳定性
- 并行性与链表排序

</details>

#### 4c

You are given the following options:

```text
(A) Quicksort (in-place using Hoare partitioning and choose the leftmost item as the pivot)
(B) Mergesort
(C) Selection Sort
(D) Insertion Sort
(E) Heapsort
(F) (None of the above)
```

For each of the statements below, list all letters that apply. Each option may be used multiple times or not at all. Note that all answers refer to the entire sorting process, not a single step of the sorting process, and assume that $N$ indicates the number of elements being sorted.

1. `_______________` Bounded by $\Omega(N\log N)$ lower bound.
2. `_______________` Worst case runtime that is asymptotically better than Quicksort’s worst case runtime.
3. `_______________` In the worst case, performs $\Theta(N)$ pairwise swaps of elements.
4. `_______________` Never compares the same two elements twice.
5. `_______________` Runs in best case $\Theta(\log N)$ time for certain inputs.

> **中文翻译：** 对每条陈述列出所有适用字母；选项可重复使用或不用。答案针对完整排序过程，$N$ 为元素数量。五条陈述依次询问：受 $\Omega(N\log N)$ 下界约束；最坏时间渐近优于快速排序的最坏时间；最坏情况下执行 $\Theta(N)$ 次成对交换；从不重复比较同一对元素；某些输入下最好时间为 $\Theta(\log N)$。

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

1. **A, B, C.** Bounded by $\Omega(N\log N)$ lower bound. Both insertion sort and heapsort both have a lower bound of $\Omega(N)$.
2. **B, E.** Worst case runtime that is asymptotically better than Quicksort’s worst case runtime. Quicksort has a worst case runtime of $O(N^2)$, while both mergesort and heapsort have a worst-case runtime of $O(N\log N)$.
3. **C.** In the worst case, performs $\Theta(N)$ pairwise swaps of elements. When thinking of pairwise swaps, both selection and insertion sort come to mind. Selection sort does at most $\Theta(N)$ swaps, while it is possible for insertion sort to need $\Theta(N^2)$ swaps (for example, a reverse sorted array).
4. **A, B, D.** Never compares the same two elements twice. Notice for QuickSort and MergeSort that once we do a comparison between two elements (the pivot for QuickSort and elements within a recursive subarray in MergeSort), we will never compare those two elements again. For example, we won’t compare the pivot against any other element again, and a sorted subarray will never have elements within it compared against one another. Insertion sort is much the same, as we bubble down elements into their respective places, comparing only against elements to the ”left” of them.
5. **F.** Runs in best case $\Theta(\log N)$ time for certain inputs. The best case runtime for a sorting algorithm cannot be faster than $\Theta(N)$. This is because at the very least, we need to check if all elements are sorted, and since there are $N$ elements, we can’t have an algorithm that sorts faster than $\Theta(N)$.

#### 解析

第 1 项中，$\Theta(N^2)$ 的选择排序当然也属于 $\Omega(N\log N)$；插入排序与课程所采用的堆排序实现存在 $\Theta(N)$ 最好输入。第 2 项比较最坏保证。第 3 项只计算成对交换而非比较或移动。第 4 项追踪同一对具体元素是否会再次被比较。第 5 项利用读取或确认 $N$ 个元素所需的线性下界。

#### 考点

- 渐近上下界
- 比较次数与交换次数
- 最好和最坏情况
- 排序的线性输入读取下界

</details>

# Exam Prep

### Question 1 — Sorted Runtimes / 排序运行时间

We want to sort an array of $N$ **unique** numbers in ascending order. Determine the best case and worst case runtimes of the following sorts:

> **中文翻译：** 我们要把一个含 $N$ 个互不相同数字的数组按升序排序。确定下列排序方法的最好和最坏运行时间。

#### 1a

Once the runs in merge sort are of size $\leq \frac{N}{100}$, we perform insertion sort on them.

Best Case: $\Theta(\hspace{1cm})$, Worst Case: $\Theta(\hspace{1cm})$

> **中文翻译：** 当归并排序中的各段大小不超过 $N/100$ 时，对这些段执行插入排序。填写最好与最坏运行时间。

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

Best Case: $\Theta(N)$, Worst Case: $\Theta(N^2)$

Once we have 100 runs of size $N/100$, insertion sort will take best case $\Theta(N)$ and worst case $\Theta(N^2)$ time. Note that the number of merging operations is actually constant (in particular, it takes about 7 splits and merges to get to an array of size $N/2^7 = N/128$).

#### 解析

阈值是输入规模的固定比例，因此每个 run 仍有 $\Theta(N)$ 个元素，而 run 的数量只是常数。若各 run 已近乎有序，所有插入排序的总成本为 $\Theta(N)$；若每个 run 都是最坏输入，总成本仍为 $\Theta(N^2)$。之后常数层合并各花 $\Theta(N)$，不改变主导项。

**补充分析（非官方答案）：** 官方先说有 “100 runs of size $N/100$”，随后又说 7 次二分得到大小 $N/128$ 的 run；严格按二分阈值 $\le N/100$，会得到 128 个大小约 $N/128$ 的 run，而不是恰好 100 个。两者都是常数个 $\Theta(N)$ 大小的 run，因此不影响官方给出的渐近结论。

#### 考点

- 混合归并/插入排序
- 常数阈值比例
- 渐近量级忽略常数

</details>

#### 1b

We use a linear time median finding algorithm to select the pivot in quicksort.

Best Case: $\Theta(\hspace{1cm})$, Worst Case: $\Theta(\hspace{1cm})$

> **中文翻译：** 使用线性时间的中位数查找算法为快速排序选择枢轴。填写最好与最坏运行时间。

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

Best Case: $\Theta(N\log(N))$, Worst Case: $\Theta(N\log(N))$

Doing an extra $N$ work each iteration of quicksort doesn’t asymptotically change the best case runtime, since we have to do $N$ work to partition the array. However, it improves the worst case runtime, since we avoid the ”bad” case where the pivot is on the extreme end(s) of the partition.

#### 解析

精确中位数保证每次分区平衡，递归树有 $\Theta(\log N)$ 层；每层的中位数选择与分区总工作均为 $\Theta(N)$。因此最好和最坏都是 $\Theta(N\log N)$。

#### 考点

- 线性时间选择
- 平衡快速排序
- 递归树分析

</details>

#### 1c

We implement heapsort with a min-heap instead of a max-heap. You may modify heapsort but must maintain constant space complexity.

Best Case: $\Theta(\hspace{1cm})$, Worst Case: $\Theta(\hspace{1cm})$

> **中文翻译：** 用最小堆而不是最大堆实现堆排序。可以修改算法，但必须保持常数额外空间。填写最好与最坏运行时间。

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

Best Case: $\Theta(N\log(N))$, Worst Case: $\Theta(N\log(N))$

While a max-heap is better, we can make do with a min-heap by placing the smallest element at the right end of the list until the list is sorted in descending order. Once the list is in descending order, it can be sorted in ascending order with a simple linear time pass.

#### 解析

反复把最小值放到右端会先得到降序数组，再原地反转成升序。建堆为 $\Theta(N)$，$N$ 次删除/恢复堆共 $\Theta(N\log N)$，最后反转为 $\Theta(N)$；额外空间仍为 $O(1)$。

#### 考点

- 最小堆堆排序
- 原地反转
- 时间与空间复杂度

</details>

#### 1d

We run an optimal sorting algorithm of our choosing knowing:

- There are at most $N$ inversions.
  - Best Case: $\Theta(\hspace{1cm})$, Worst Case: $\Theta(\hspace{1cm})$
- There is exactly 1 inversion.
  - Best Case: $\Theta(\hspace{1cm})$, Worst Case: $\Theta(\hspace{1cm})$
- There are exactly $\frac{N(N-1)}{2}$ inversions.
  - Best Case: $\Theta(\hspace{1cm})$, Worst Case: $\Theta(\hspace{1cm})$

> **中文翻译：** 已知输入依次满足以下条件时，可以自由选择最优排序算法：逆序对至多为 $N$；恰好有 1 个逆序对；恰好有 $N(N-1)/2$ 个逆序对。分别填写最好与最坏运行时间。

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

**There are at most $N$ inversions.**

Best Case: $\Theta(N)$, Worst Case: $\Theta(N)$

Recall that insertion sort takes $\Theta(N + K)$ time, where $K$ is the number of inversions. Thus, the optimal sorting algorithm would be insertion sort. If $K < N$, then, insertion sort has the best and worst case runtime of $\Theta(N)$.

**There is exactly 1 inversion.**

Best Case: $\Theta(1)$, Worst Case: $\Theta(N)$

First, we notice that if there is only 1 inversion, it could only involve 2 adjacent elements. Intuitively, if two elements that are apart form an inversion, than some element between these two would also form an inversion with one of the elements.

(Optional) A formal argument is as follows: suppose the only inversion involves 2 elements that are not adjacent. Let’s call their indices $i$ and $j$, where $i < j$, and $a[i] > a[j]$ (definition of inversion). Because they are not adjacent, there exist some index $k$, such that $i < k < j$. In case 1, assume $a[k] > a[i]$. Then it follows that $a[k] > a[i] > a[j]$. Because $k < j$ but $a[k] > a[j]$, $(k,j)$ forms an inversion, so we have a contradiction (we assumed ony 1 inversion). In case 2, assume $a[k] < a[i]$, but then we have $k > i$, so $(k,i)$ also form an inversion, which is also a contradiction.

Using this, we can just compare neighboring elements to find that exact inversion, and swap the 2 elements. If the inversion involves the first two elements, constant time is needed. If the inversion involves elements at the end, $N$ time is needed.

**There are exactly $\frac{N(N-1)}{2}$ inversions.**

Best Case: $\Theta(N)$, Worst Case: $\Theta(N)$

If a list has $\frac{N(N-1)}{2}$ inversions, it means it is sorted in descending order. This is because every possible pair is an inversion (The total number of unordered pairs from $N$ elements is $\binom{N}{2}$, or $\frac{N(N-1)}{2}$). So, it can be sorted in ascending order with a simple linear time pass. We know that reversing any array is a linear time operation, so the optimal runtime of any sorting algorithm is $\Theta(N)$.

#### 解析

逆序对至多为 $N$ 时，插入排序的 $\Theta(N+K)$ 直接化为 $\Theta(N)$。恰好一个逆序对时，它必然来自一对相邻元素；从左扫描相邻对，找到后交换，位置靠前时最好 $\Theta(1)$、靠后时最坏 $\Theta(N)$。最大逆序对数意味着严格降序，原地反转即可在线性时间完成。

**补充分析（非官方答案）：** 官方可选证明的第二种情况写成了 “$(k,i)$ also form an inversion”。按逆序对通常以较小下标在前的记法，已知 $i<k$ 且 $a[i]>a[k]$，应写为 $(i,k)$。这是索引顺序笔误，不影响官方结论；官方原文已保留。

#### 考点

- 逆序对数量
- 插入排序的 $\Theta(N+K)$
- 相邻逆序与最大逆序
- 最优算法选择

</details>

### Question 2 — MSD Radix Sort / MSD 基数排序

Recursively implement the method `msd` below, which runs MSD radix sort on a `List` of `Strings` and returns a sorted `List` of `Strings`. For simplicity, assume that each string is of the same length. You may not need all of the lines below.

In lecture, recall that we used counting sort as the subroutine for MSD radix sort, but any stable sort works! For the subroutine here, you may use the `stableSort` method, which sorts the given list of strings in place, comparing two strings by the given index. Finally, you may find following methods of the `List` class helpful:

1. `List<E> subList(int fromIndex, int toIndex)`. Returns the portion of this list between the specified `fromIndex`, inclusive, and `toIndex`, exclusive.
2. `addAll(Collection<? extends E> c)`. Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection’s iterator.

> **中文翻译：** 递归实现下面的 `msd` 方法，对字符串列表执行 MSD 基数排序并返回有序字符串列表。假设所有字符串等长，且不一定需要使用所有给出的代码行。课堂中使用计数排序作为子程序，但任何稳定排序都可以。这里可调用 `stableSort`，它按指定字符索引原地稳定排序。还可使用 `subList` 取得左闭右开的列表视图，并用 `addAll` 按迭代顺序追加集合元素。

```java
public static List<String> msd(List<String> items) {

    return __________________________________________________________________;
}

private static List<String> msd(List<String> items, int index) {

    if (_____________________________________________________________________) {
        return items;
    }
    List<String> answer = new ArrayList<>();
    int start = 0;

    _________________________________________________________________________;
    for (int end = 1; end <= items.size(); end += 1) {

        if (_________________________________________________________________) {

            _________________________________________________________________;

            _________________________________________________________________;

            _________________________________________________________________;
        }
    }
    return answer;
}

/* Sorts the strings in `items` by their character at the `index` index alphabetically. */
private static void stableSort(List<String> items, int index) {
    // Implementation not shown
}
```

> **代码注释翻译：** `stableSort` 按 `index` 所指位置的字符，以字母顺序对 `items` 中的字符串排序；具体实现未给出。

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

```java
public static List<String> msd(List<String> items) {
    return msd(items, 0);
}

private static List<String> msd(List<String> items, int index) {
    if (items.size() <= 1 || index >= items.get(0).length()) {
        return items;
    }
    List<String> answer = new ArrayList<>();
    int start = 0;
    stableSort(items, index);
    for (int end = 1; end <= items.size(); end += 1) {
        if (end == items.size() || items.get(start).charAt(index) != items.get(end).charAt(index)) {
            List<String> subList = items.subList(start, end);
            answer.addAll(msd(subList, index + 1));
            start = end;
        }
    }
    return answer;
}

/* Sorts the strings in `items` by their character at the `index` index alphabetically. */
private static void stableSort(List<String> items, int index) {
    // Implementation not shown
}
```

#### 解析

入口从字符索引 0 开始。递归在桶大小至多 1 或已处理完整个字符串时停止。每层先按当前字符稳定排序，再用 `start` 和 `end` 找出相同字符的连续区间；每个区间递归处理下一字符，并按桶顺序追加到 `answer`。

条件中把 `end == items.size()` 放在 `||` 左侧十分重要：Java 短路求值可避免在末尾访问 `items.get(end)` 越界。若最长字符串长度为 $W$，每层稳定排序成本取决于 `stableSort`；使用计数排序时，最坏总成本为 $\Theta(W(N+R))$。

#### 考点

- MSD 递归分桶
- 稳定排序子程序
- `subList` 的左闭右开区间
- Java 短路求值与越界保护

</details>

### Question 3 — Shuffled Exams / 被打乱的试卷

For this problem, we will be working with `Exam` and `Student` objects, both of which have only one attribute: `sid`, which is a integer like any student ID.

PrairieLearn thought it was ready for the final. It had meticulously created two arrays, one of `Exams` and the other of `Students`, and ordered both on `sid` such that the ith `Exam` in the `Exams` array has the same `sid` as the ith `Student` in the `Students` array. Note the arrays are not necessarily sorted by `sid`. However, PrairieLearn crashed, and the `Students` array was shuffled, but the `Exams` array somehow remained untouched.

Time is precious, so you must design a $O(N)$ time algorithm to reorder the `Students` array appropriately **without** changing the `Exams` array!

Hint: While you cannot modify the `Exams` array, you can sort a copy of the `Exams` array with some added information. Think about what information would be useful to put back the `Students` array in the same order as the exams.

> **中文翻译：** 本题使用 `Exam` 和 `Student` 对象，它们都只有一个整数属性 `sid`。PrairieLearn 原先创建了试卷数组和学生数组，使相同下标的试卷与学生具有相同 `sid`；两个数组本身不一定按 `sid` 排序。系统崩溃后学生数组被打乱，而试卷数组保持不变。请设计 $O(N)$ 时间算法恢复学生数组顺序，同时不能修改试卷数组。提示：可以对带有额外信息的试卷数组副本排序，思考恢复原下标需要保存什么信息。

#### ✍️ My Answer

> 在这里作答

<details>
<summary>✅ 查看答案与解析</summary>

#### 官方答案

Let’s begin by creating an `ExamWrapper` class that contains two attributes — an `Exam` instance and the index of the corresponding `Exam` in the `Exams` array. Next, for each `Exam`, create the corresponding `ExamWrapper` instance.

Run radix sort on the `ExamWrappers`, sorting them on the `sid` of the `Exam` instances. Similarly run radix sort on the list of `Students`, sorting them on `sid` as well. Note that both iterations of radix sort take linear time since the `sid` is of fixed length and of base 10.

At this point in the algorithm, we have ”completed” the hint, but we still need move the ith `Student` to its proper place relative to the original `Exams` array. To acheive this, for the i-th `Student`, we will access the ith `ExamWrapper`, and set the index of the i-th `Student` as the `ExamWrapper`'s index attribute.

#### 解析

包装器把每份试卷的 `sid` 与原始数组下标绑定。按 `sid` 对包装器和学生分别执行固定长度、十进制的基数排序后，第 $i$ 个学生与第 $i$ 个包装器对应同一 `sid`。随后把该学生放到包装器记录的原下标，即可恢复与未修改 `Exams` 数组一致的顺序。两次基数排序和一次线性回填均为 $O(N)$；需要 $O(N)$ 辅助空间保存副本、包装器和回填结果。

**补充分析（非官方答案）：** 官方末句写成 “set the index of the i-th Student as the ExamWrapper's index attribute”。但题目已说明 `Student` 只有 `sid` 属性，因此这里更合理的算法含义是“把第 $i$ 个已排序学生写入 `Students[wrapper.index]`”，而不是给 `Student` 设置一个不存在的 `index` 字段。原文已保留，该具体回填表达属于补充澄清。

#### 考点

- 固定长度整数的基数排序
- 装饰/包装对象保留原下标
- 排序后按键对齐
- 线性时间回填

</details>

## 完整性检查

- Regular：题目 1–4 与 Regular Solutions 的题号一一对应；完整保留 1a–1c、2a–2d、3a–3e、4a–4c。
- Exam Prep：题目 1–3 与 Exam Prep Solutions 的题号一一对应；完整保留 1a–1d、Question 2 和 Question 3。
- 图片提取情况：18 页均已渲染并逐页视觉检查；没有必须保存为图片的树、图、内存图或复杂空间结构，表格均已转换为 Markdown Table，因此 `assets/` 仅保留 `.gitkeep`。
- 官方答案和补充分析的分区情况：官方原文与备用答案均保留；堆排序 “bubble up” 术语、归并 run 数量不一致、逆序对证明索引笔误及 Shuffled Exams 回填表述歧义只在中文解析中标为补充分析，没有冒充官方答案。

## 官方资料

- [Regular PDF](https://sp24.datastructur.es/assets/discussions/regular12.pdf)
- [Regular Solutions PDF](https://sp24.datastructur.es/assets/discussions/regular12sol.pdf)
- [Exam Prep PDF](https://sp24.datastructur.es/assets/discussions/examlevel12.pdf)
- [Exam Prep Solutions PDF](https://sp24.datastructur.es/assets/discussions/examlevel12sol.pdf)
