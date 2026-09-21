package solutions;

import java.util.Map;

/** Official solutions and Chinese explanation for Discussion 12 Regular Q3. */
public class Dis12RegularQ3SortIdentificationSolution {

    /*
     * 3a - Official solution / 官方答案
     * Quicksort. A pattern we can see is that most of the elements remain in
     * the same order relative to one another, but the first element keeps
     * moving around. Taking a closer look at this element, we note that
     * everything to the left is less than it, and everything to the right is
     * greater than, indicating QuickSort.
     *
     * 中文解析：首元素 12 分区后左侧都较小、右侧都较大，枢轴逐步进入
     * 最终位置。
     */

    /*
     * 3b - Official solution / 官方答案
     * Insertion Sort. A surefire way of identifying insertion sort is the fact
     * it slowly builds a partially sorted array on the left hand side, which is
     * exactly what occurs here.
     *
     * Another solution is merge sort (with a recursive implementation), where
     * the left half has already been fully merge-sorted.
     *
     * 中文解析：左侧有序前缀符合插入排序；递归归并排序若先完成左半部分，
     * 也能出现同一状态，因此官方保留两种答案。
     */

    /*
     * 3c - Official solution / 官方答案
     * MSD Radix Sort. We first see that the numbers have been moved all over
     * the place, which is odd, but taking a closer look at how the numbers are
     * oriented, we see that all the tens digits are ordered in ascending order
     * (10s, then 20s and 30s). This is immediately indicative of MSD Radix
     * Sort, sorting from the leftmost digit first.
     *
     * 中文解析：十位已经按 1、2、3 分桶，而相同十位内部尚未全部按个位
     * 排好，说明算法先处理最高有效位。
     */

    /*
     * 3d - Official solution / 官方答案
     * Merge Sort. We notice that the numbers stay relatively close to where
     * they begin, but we also see little sorted runs inside the array, like
     * (23, 45) and (5, 65) for example. These small sorted subarrays and the
     * merging of the subarrays in halves of the array is indicative of Merge
     * Sort.
     *
     * 中文解析：先出现长度为 2 的有序小段，再合并成更长的有序半区。
     */

    /*
     * 3e - Official solution / 官方答案
     * Heapsort. Heapsort always heapifies the array first, which on the second
     * line we see the maximum element, 54, at the start of the array. Heapifying
     * usually shuffles around the array, which we also see. Placing the maximum
     * element at the back of the array and bubbling up the 44 seals the deal
     * that this is heapsort.
     *
     * 中文解析：第二行是最大堆的数组表示，根为最大值 54；第三行把 54
     * 固定到末尾，并恢复剩余堆。
     *
     * Supplemental analysis / 补充分析（非官方答案）：
     * 官方最后一句写的是 “bubbling up the 44”。最大值移到末尾后，44 被
     * 交换到堆顶；恢复最大堆通常应称为让新根向下调整（bubble down / sink），
     * 而不是向上调整。官方原文保留在上方。
     */

    public static final Map<String, String> OFFICIAL_IDENTIFICATIONS = Map.of(
        "3a", "Quicksort",
        "3b", "Insertion Sort; alternatively recursive Merge Sort",
        "3c", "MSD Radix Sort",
        "3d", "Merge Sort",
        "3e", "Heapsort"
    );

    public static void main(String[] args) {
        if (OFFICIAL_IDENTIFICATIONS.size() != 5
                || !"Heapsort".equals(OFFICIAL_IDENTIFICATIONS.get("3e"))) {
            throw new AssertionError("Missing sort identification");
        }
    }
}
