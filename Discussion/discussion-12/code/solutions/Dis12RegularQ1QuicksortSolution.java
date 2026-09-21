package solutions;

import java.util.Arrays;

/** Official solutions and Chinese explanation for Discussion 12 Regular Q1. */
public class Dis12RegularQ1QuicksortSolution {

    /*
     * 1a - Official solution / 官方答案
     * -18-, 7, 22, 34, 99, 18, 11, 4
     * -7-, 11, 4 | 18, 18 | 22, 34, 99
     * 4, 7, 11, 18, 18 | -22-, 34, 99
     * 4, 7, 11, 18, 18, 22 | -34-, 99
     * 4, 7, 11, 18, 18, 22, 34, 99
     *
     * 中文解析：第一轮以 18 为枢轴，把元素分成小于、等于和大于三组；
     * 两个 18 一起进入中间组。左组以 7、右组依次以 22 和 34 继续分区。
     */
    public static final int[][] QUESTION_1A_STEPS = {
        {18, 7, 22, 34, 99, 18, 11, 4},
        {7, 11, 4, 18, 18, 22, 34, 99},
        {4, 7, 11, 18, 18, 22, 34, 99},
        {4, 7, 11, 18, 18, 22, 34, 99},
        {4, 7, 11, 18, 18, 22, 34, 99}
    };

    /*
     * 1b - Official solution / 官方答案
     * Best: Theta(N log N). Running Quicksort on a list that has a pivot splits
     * the partition exactly in half will result in Theta(log N) levels, with
     * the same amount work as above (i.e. Theta(N) at each level). For example,
     * [3, 1, 2, 5, 4]. An alternative case is when we have all of the same
     * element in the array (i.e. [4, 4, 4, 4, 4]), since the two pointers in
     * Hoare partitioning always end up in the middle.
     *
     * Worst: Theta(N^2). In general, the worst case is such that the partioning
     * scheme repeatedly partions an array into one element and the rest.
     *
     * Running Quicksort on a sorted list will take Theta(N^2) if the pivot
     * chosen is always the first or last in the subarray: [1, 2, 3, 4, 5]. At
     * each level of recursion, you will need to do Theta(N) work, and there will
     * be Theta(N) levels of recursion. This sums up to 1 + 2 + ... + N.
     *
     * 中文解析：全相等数组在课程采用的 Hoare 分区中会在中点附近分割，
     * 而升序数组配合首元素枢轴会持续产生极不平衡的子问题。因此全相等
     * 列表运行得更好。
     */

    /*
     * 1c - Official solution / 官方答案
     * 1. Randomly choose pivots.
     * 2. Shuffle the list before running Quicksort.
     *
     * 中文解析：两种方法都使枢轴相对于输入排列具有随机性。它们不消除
     * 理论上的 Theta(N^2) 最坏情况，但会显著降低持续极端分区的概率。
     */

    public static void main(String[] args) {
        int[] expected = {4, 7, 11, 18, 18, 22, 34, 99};
        int[] finalStep = QUESTION_1A_STEPS[QUESTION_1A_STEPS.length - 1];
        if (!Arrays.equals(expected, finalStep)) {
            throw new AssertionError("Unexpected final quicksort state");
        }
    }
}
