package questions;

/**
 * CS 61B Spring 2024 - Discussion 12, Regular Question 1.
 * Quicksort / 快速排序
 *
 * <p>Write conceptual answers in the TODO areas. The arrays below reproduce
 * the official worksheet input, but this Question file intentionally contains
 * no official answers or precomputed final states.</p>
 */
public class Dis12RegularQ1Quicksort {

    /*
     * 1a - English - Official question
     * Sort the following unordered list using Quicksort. Assume that we always
     * choose first element as the pivot and that we use the 3-way merge
     * partitioning process described in lecture. Show the steps taken at each
     * partitioning step.
     *
     * 18, 7, 22, 34, 99, 18, 11, 4
     *
     * 1a - 中文翻译
     * 使用快速排序对上述无序列表排序。假设始终选择第一个元素作为枢轴，
     * 并使用课堂所述的三路合并分区过程。展示每次分区步骤后的结果。
     *
     * Your answer / 你的答案：
     * TODO: Record every partitioning step here.
     * TODO：在这里记录每一步分区结果。
     */
    public static final int[] QUESTION_1A_INPUT = {18, 7, 22, 34, 99, 18, 11, 4};

    /*
     * 1b - English - Official question
     * What is the best and worst case running time of Quicksort with Hoare
     * Partitioning on N elements? Given the two lists [4, 4, 4, 4, 4] and
     * [1, 2, 3, 4, 5], assuming we pick the first element as the pivot every
     * time, which list would result in better runtime?
     *
     * 1b - 中文翻译
     * 对 N 个元素使用 Hoare 分区的快速排序，其最好和最坏运行时间分别是
     * 多少？对下面两个列表，若每次都选择第一个元素作为枢轴，哪个列表的
     * 运行时间更好？
     *
     * Your answer / 你的答案：
     * Best case / 最好情况：TODO
     * Worst case / 最坏情况：TODO
     * Better list and reasoning / 更快的列表与理由：TODO
     */
    public static final int[] ALL_EQUAL = {4, 4, 4, 4, 4};
    public static final int[] ALREADY_SORTED = {1, 2, 3, 4, 5};

    /*
     * 1c - English - Official question
     * What are two techniques that can be used to reduce the probability of
     * Quicksort taking the worst case running time?
     *
     * 1c - 中文翻译
     * 哪两种技术可以降低快速排序出现最坏运行时间的概率？
     *
     * Your answer / 你的答案：TODO
     */

    private Dis12RegularQ1Quicksort() {
        // Utility class.
    }
}
