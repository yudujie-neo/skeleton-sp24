package questions;

/**
 * CS 61B Spring 2024 - Discussion 05, Regular Question 1.
 * Asymptotics / 渐近分析
 *
 * <p>Write the complexity answers in the bilingual TODO blocks below. The code
 * supplied by the worksheet is kept in executable Java form. Helpers marked as
 * compilation scaffolding are not part of the official question.</p>
 */
public class Dis05RegularQ1Asymptotics {

    /*
     * 1a - English (official question)
     * Say we have a function findMax that iterates through an unsorted int
     * array one time and returns the maximum element found in that array. Give
     * the tightest lower and upper bounds (Omega and O) of findMax in terms of
     * N, the length of the array. Is it possible to define a Theta bound for
     * findMax?
     *
     * 1a - 中文翻译
     * 假设 findMax 完整遍历一次未排序的 int 数组并返回最大值。请用数组
     * 长度 N 给出最紧的下界和上界，并判断能否给出 Theta 界。
     *
     * My answer / 我的答案：
     * Lower bound / 下界：TODO
     * Upper bound / 上界：TODO
     * Theta bound / Theta 界：TODO
     * Reasoning / 推导：TODO
     */

    /** Compilation scaffold / 编译脚手架（非官方给定代码）。 */
    public static int findMax(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("array must not be empty");
        }
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /*
     * 1b - English (official question)
     * Give the worst-case and best-case runtime in terms of M and N. Assume
     * ping is in Theta(1) and returns an int.
     *
     * 1b - 中文翻译
     * 用 M 和 N 表示下面代码的最坏和最好运行时间。假设 ping 的运行时间
     * 为 Theta(1)，并返回 int。
     *
     * My answer / 我的答案：
     * Best case / 最好情况：TODO
     * Worst case / 最坏情况：TODO
     * Reasoning / 推导：TODO
     */
    public static void question1b(int N, int M) {
        for (int i = N; i > 0; i--) {
            for (int j = 0; j <= M; j++) {
                if (ping(i, j) > 64) {
                    break;
                }
            }
        }
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
    private static int ping(int i, int j) {
        throw new UnsupportedOperationException("Provide a Theta(1) ping implementation to run 1b");
    }

    /*
     * 1c - English (official question)
     * Below we have a function that returns true if every int has a duplicate
     * in the array, and false if there is any unique int in the array. Assume
     * sort(array) is in Theta(N log N) and returns array sorted. Give the worst
     * case and best case runtime where N = array.length.
     *
     * 1c - 中文翻译
     * noUniques 在每个整数都有重复项时返回 true，只要存在唯一整数就
     * 返回 false。假设 sort(array) 为 Theta(N log N) 并返回排序后的数组。
     * 当 N = array.length 时，给出最好和最坏运行时间。
     *
     * My answer / 我的答案：
     * Best case / 最好情况：TODO
     * Worst case / 最坏情况：TODO
     * Reasoning / 推导：TODO
     */
    public static boolean noUniques(int[] array) {
        array = sort(array);
        int N = array.length;
        for (int i = 0; i < N; i += 1) {
            boolean hasDuplicate = false;
            for (int j = 0; j < N; j += 1) {
                if (i != j && array[i] == array[j]) {
                    hasDuplicate = true;
                }
            }
            if (!hasDuplicate) {
                return false;
            }
        }
        return true;
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
    private static int[] sort(int[] array) {
        throw new UnsupportedOperationException("Assume this operation is Theta(N log N)");
    }
}
