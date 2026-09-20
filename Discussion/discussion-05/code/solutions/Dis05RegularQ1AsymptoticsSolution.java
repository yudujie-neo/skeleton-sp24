package solutions;

import java.util.Arrays;

/** Official solutions and Chinese explanation for Discussion 05 Regular Q1. */
public class Dis05RegularQ1AsymptoticsSolution {

    /*
     * 1a - Official solution / 官方答案
     * Lower bound: Omega(N). Upper bound: O(N). Therefore Theta(N).
     *
     * 中文解析：未排序数组的最大值可能出现在任何位置。为确认候选值确实
     * 最大，必须检查全部 N 个元素；给定实现又只遍历一次，所以运行时间为
     * Theta(N)。
     */

    /*
     * 1b - Official solution / 官方答案
     * Worst: Theta(MN). Best: Theta(N).
     *
     * 中文解析：外层固定执行 N 次。最好情况下每次首次调用 ping 就 break；
     * 最坏情况下内层完成 M + 1 次迭代，渐近上等价于 M 次。
     *
     * Supplemental analysis / 补充分析（非官方答案）：
     * 官方解释使用了 returns true/always false，但 ping 实际返回 int；应理解为
     * 返回值是否满足 ping(i, j) > 64。
     */

    /*
     * 1c - Official solution / 官方答案
     * Best: Theta(N log N). Worst: Theta(N^2).
     *
     * 中文解析：排序始终花费 Theta(N log N)。最好情况下第一次内层扫描后
     * 提前返回；最坏情况下两个循环完成 N^2 次比较，平方项占主导。
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

    /** Runnable scaffold matching the question's stated sort contract. */
    private static int[] sort(int[] array) {
        Arrays.sort(array);
        return array;
    }

    public static void main(String[] args) {
        if (!noUniques(new int[]{4, 4, 2, 2})) {
            throw new AssertionError("Every value has a duplicate");
        }
        if (noUniques(new int[]{4, 4, 2})) {
            throw new AssertionError("2 is unique");
        }
    }
}
