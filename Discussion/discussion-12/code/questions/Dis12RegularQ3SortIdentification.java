package questions;

/**
 * CS 61B Spring 2024 - Discussion 12, Regular Question 3.
 * Sort Identification / 排序识别
 */
public class Dis12RegularQ3SortIdentification {

    /*
     * English - Official question
     * Match the sorting algorithms to the sequences, each of which represents
     * several intermediate steps in the sorting of an array of integers.
     * Assume that for quicksort, the pivot is always the first item in the
     * sublist being sorted. Note that these steps are not necessarily the first
     * few intermediate steps and there may be steps which are skipped.
     *
     * Algorithms: Quicksort, Merge Sort, Heapsort, MSD Radix Sort,
     * Insertion Sort
     *
     * 中文翻译
     * 将排序算法与各组整数数组的若干中间状态匹配。快速排序始终选择当前
     * 子列表的第一个元素作为枢轴。这些状态不一定是最初几步，并且可能
     * 跳过了一些步骤。候选算法为快速排序、归并排序、堆排序、MSD 基数
     * 排序和插入排序。
     */

    /** 3a official sequence / 官方序列。 */
    public static final int[][] SEQUENCE_A = {
        {12, 7, 8, 4, 10, 2, 5, 34, 14},
        {7, 8, 4, 10, 2, 5, 12, 34, 14},
        {4, 2, 5, 7, 8, 10, 12, 14, 34}
    };

    /** 3b official sequence / 官方序列。 */
    public static final int[][] SEQUENCE_B = {
        {23, 45, 12, 4, 65, 34, 20, 43},
        {4, 12, 23, 45, 65, 34, 20, 43}
    };

    /** 3c official sequence / 官方序列。 */
    public static final int[][] SEQUENCE_C = {
        {12, 32, 14, 11, 17, 38, 23, 34},
        {12, 14, 11, 17, 23, 32, 38, 34}
    };

    /** 3d official sequence / 官方序列。 */
    public static final int[][] SEQUENCE_D = {
        {45, 23, 5, 65, 34, 3, 76, 25},
        {23, 45, 5, 65, 3, 34, 25, 76},
        {5, 23, 45, 65, 3, 25, 34, 76}
    };

    /** 3e official sequence / 官方序列。 */
    public static final int[][] SEQUENCE_E = {
        {23, 44, 12, 11, 54, 33, 1, 41},
        {54, 44, 33, 41, 23, 12, 1, 11},
        {44, 41, 33, 11, 23, 12, 1, 54}
    };

    /*
     * Your answer / 你的答案：
     * 3a: TODO - algorithm and evidence / 算法与证据
     * 3b: TODO - algorithm(s) and evidence / 算法（可能不止一个）与证据
     * 3c: TODO - algorithm and evidence / 算法与证据
     * 3d: TODO - algorithm and evidence / 算法与证据
     * 3e: TODO - algorithm and evidence / 算法与证据
     */

    private Dis12RegularQ3SortIdentification() {
        // Utility class.
    }
}
