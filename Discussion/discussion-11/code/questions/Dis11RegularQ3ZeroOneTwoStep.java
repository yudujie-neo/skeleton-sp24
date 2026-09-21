/**
 * Discussion 11, Regular, Question 3 - Zero One Two-Step.
 *
 * English - Official question
 * 3a. Given an array that only contains 0's, 1's and 2's, write an
 * algorithm to sort it in linear time without creating a new array. You may
 * want to use the provided helper method, swap.
 * Hint: Consider how Hoare partitioning rearranges elements in an array.
 *
 * 3b. We just wrote a linear time sort. Why can't we always use this sort,
 * even though it has better runtime than Mergesort or Quicksort?
 *
 * 3c. The sort is also "in place." What does it mean to sort in place, and
 * why would we want this?
 *
 * 中文翻译
 * 3a 给定只含 0、1、2 的数组，不创建新数组，在线性时间内排序；可使用 swap。
 * 提示：思考 Hoare 分区如何重排数组。3b 解释为何该线性排序不能用于所有输入。
 * 3c 解释“原地排序”的含义及其价值。
 *
 * Constraints / 限制
 * 输入值域仅为 {0, 1, 2}；要求 Theta(N) 时间，并且不能创建新数组。
 */
/*
 * Official given code / 官方给定代码（原样保留在注释中）
 *
 * public static void specialSort(int[] arr) {
 *     int front = 0;
 *     int back = arr.length - 1;
 *     int curr = 0;
 *
 *     while (______________________________) {
 *         if (arr[curr] < 1) {
 *             _____________________________;
 *             _____________________________;
 *             _____________________________;
 *         } else if (arr[curr] > 1) {
 *             _____________________________;
 *             _____________________________;
 *         } else {
 *             _____________________________;
 *         }
 *     }
 * }
 *
 * private static void swap(int[] arr, int i, int j) {
 *     int temp = arr[i];
 *     arr[i] = arr[j];
 *     arr[j] = temp;
 * }
 */
public class Dis11RegularQ3ZeroOneTwoStep {
    /**
     * Compilation scaffold / 编译脚手架（非官方题目内容）.
     * The official PDF uses blank underscores inside a method. A throwing stub
     * preserves the TODO while keeping the untouched question compilable.
     */
    public static void specialSort(int[] arr) {
        // TODO 3a / 你的答案：用 front、back、curr 完成三向原地分区。
        throw new UnsupportedOperationException("TODO: implement specialSort");
    }

    /** Official provided helper / 官方给定辅助方法。 */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
     * My answer / 我的答案
     * TODO 3b:
     * TODO 3c:
     */
}
