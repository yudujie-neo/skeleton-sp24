package questions;

/**
 * CS 61B Spring 2024 - Discussion 12, Regular Question 2.
 * Radix Sorts / 基数排序
 */
public class Dis12RegularQ2RadixSorts {

    /*
     * 2a - English - Official question
     * Sort the following list using LSD Radix Sort with counting sort. Show the
     * steps taken after each round of counting sort. The first row is the
     * original list and the last two rounds are already filled for you.
     *
     * Original: 30395 30326 43092 30315
     * Round 1: TODO
     * Round 2: TODO
     * Round 3: TODO
     * Brackets below mark the underlined digits in the official PDF.
     * Round 4: [3031]5 [3032]6 [3039]5 [4309]2
     * Round 5: [30315] [30326] [30395] [43092]
     *
     * 2a - 中文翻译
     * 使用以计数排序为子程序的 LSD 基数排序处理下列列表。展示每轮计数
     * 排序后的结果。第一行是原始列表，最后两轮已给出。
     *
     * Your answer / 你的答案：填写第 1、2、3 轮。TODO
     */
    public static final int[] LSD_INPUT = {30395, 30326, 43092, 30315};
    public static final int[] LSD_GIVEN_ROUND_4 = {30315, 30326, 30395, 43092};
    public static final int[] LSD_GIVEN_ROUND_5 = {30315, 30326, 30395, 43092};

    /*
     * 2b - English - Official question
     * Sort the following list using MSD Radix Sort with counting sort. Show the
     * steps taken after each round of counting sort. The first row is the
     * original list and the first round is already filled for you.
     *
     * Original: 21295 22316 30753 21248 30751
     * Brackets below mark the underlined digits in the official PDF.
     * Round 1: [2]1295 [2]2316 [2]1248 | [3]0753 [3]0751
     * Rounds 2-5: TODO
     *
     * 2b - 中文翻译
     * 使用以计数排序为子程序的 MSD 基数排序处理下列列表。展示每轮计数
     * 排序后的结果。第一行是原始列表，第一轮已给出。
     *
     * Your answer / 你的答案：填写第 2、3、4、5 轮。TODO
     */
    public static final int[] MSD_INPUT = {21295, 22316, 30753, 21248, 30751};
    public static final int[][] MSD_GIVEN_ROUND_1_BUCKETS = {
        {21295, 22316, 21248},
        {30753, 30751}
    };

    /*
     * 2c - English - Official question
     * Give the best case runtime, worst case runtime, and whether or not the
     * sort is stable for both LSD and MSD radix sort. Assume we have N
     * elements, a radix R, and a maximum number of digits in an element W.
     *
     * 2c - 中文翻译
     * 对 LSD 和 MSD 基数排序，分别给出最好运行时间、最坏运行时间以及是否
     * 稳定。假设有 N 个元素，基数为 R，元素的最大位数为 W。
     *
     * Your answer / 你的答案：
     * LSD best / 最好：TODO; LSD worst / 最坏：TODO; stable / 稳定：TODO
     * MSD best / 最好：TODO; MSD worst / 最坏：TODO; stable / 稳定：TODO
     */

    /*
     * 2d - English - Official question
     * We saw in part (c) that radix sort has good runtime with respect to the
     * number of elements in the list. Given this fact, can we say that radix
     * sort is the best sort to use?
     *
     * 2d - 中文翻译
     * 从 2c 可见，基数排序相对于列表元素数量具有良好运行时间。能否据此
     * 断定基数排序是最好的排序方法？
     *
     * Your answer / 你的答案：TODO
     */

    private Dis12RegularQ2RadixSorts() {
        // Utility class.
    }
}
