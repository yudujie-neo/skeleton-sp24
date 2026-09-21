package questions;

/**
 * CS 61B Spring 2024 - Discussion 12, Exam Prep Question 1.
 * Sorted Runtimes / 排序运行时间
 */
public class Dis12ExamPrepQ1SortedRuntimes {

    /*
     * English - Official preamble
     * We want to sort an array of N unique numbers in ascending order.
     * Determine the best case and worst case runtimes of the following sorts.
     *
     * 中文翻译
     * 我们要把一个含 N 个互不相同数字的数组按升序排序。确定下列排序
     * 方法的最好和最坏运行时间。
     */

    /*
     * 1a - English - Official question
     * Once the runs in merge sort are of size <= N / 100, we perform insertion
     * sort on them.
     * Best Case: Theta(____), Worst Case: Theta(____)
     *
     * 1a - 中文翻译
     * 当归并排序中的各段大小不超过 N/100 时，对这些段执行插入排序。
     * 填写最好与最坏运行时间。
     *
     * Your answer / 你的答案：Best TODO; Worst TODO; Reasoning TODO
     */

    /*
     * 1b - English - Official question
     * We use a linear time median finding algorithm to select the pivot in
     * quicksort.
     * Best Case: Theta(____), Worst Case: Theta(____)
     *
     * 1b - 中文翻译
     * 使用线性时间的中位数查找算法为快速排序选择枢轴。填写最好与最坏
     * 运行时间。
     *
     * Your answer / 你的答案：Best TODO; Worst TODO; Reasoning TODO
     */

    /*
     * 1c - English - Official question
     * We implement heapsort with a min-heap instead of a max-heap. You may
     * modify heapsort but must maintain constant space complexity.
     * Best Case: Theta(____), Worst Case: Theta(____)
     *
     * 1c - 中文翻译
     * 用最小堆而不是最大堆实现堆排序。可以修改算法，但必须保持常数额外
     * 空间。填写最好与最坏运行时间。
     *
     * Your answer / 你的答案：Best TODO; Worst TODO; Reasoning TODO
     */

    /*
     * 1d - English - Official question
     * We run an optimal sorting algorithm of our choosing knowing:
     * - There are at most N inversions.
     * - There is exactly 1 inversion.
     * - There are exactly N(N - 1) / 2 inversions.
     * Give the best and worst case for each condition.
     *
     * 1d - 中文翻译
     * 已知输入依次满足以下条件时，可以自由选择最优排序算法：逆序对至多
     * 为 N；恰好有 1 个逆序对；恰好有 N(N-1)/2 个逆序对。分别填写最好
     * 与最坏运行时间。
     *
     * Your answer / 你的答案：
     * At most N inversions / 至多 N 个：Best TODO; Worst TODO; Algorithm TODO
     * Exactly 1 / 恰好 1 个：Best TODO; Worst TODO; Algorithm TODO
     * Maximum / 最大数量：Best TODO; Worst TODO; Algorithm TODO
     */

    private Dis12ExamPrepQ1SortedRuntimes() {
        // Utility class.
    }
}
