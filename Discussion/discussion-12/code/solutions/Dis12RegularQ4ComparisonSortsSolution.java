package solutions;

import java.util.Arrays;
import java.util.List;

/** Official solutions and Chinese explanation for Discussion 12 Regular Q4. */
public class Dis12RegularQ4ComparisonSortsSolution {

    /*
     * 4a - Official solution / 官方答案
     * A simple example is: 5 4 3 2 1. Any 5 integer array in descending order
     * would work.
     *
     * 中文解析：降序输入具有最多的逆序对，每个新元素都要越过此前全部
     * 元素，因此插入排序耗时 Theta(N^2)。
     */
    public static final int[] QUESTION_4A_EXAMPLE = {5, 4, 3, 2, 1};

    /*
     * 4b - Official solution / 官方答案
     * Some possible answers: mergesort has Theta(N log N) worst case runtime
     * versus quicksort's Theta(N^2). Mergesort is stable, whereas quicksort
     * typically isn't. Mergesort can be highly parallelized because as we saw
     * in the first problem the left and right sides don't interact until the
     * end. Mergesort is also preferred for sorting a linked list.
     *
     * 中文解析：归并排序有确定的最坏时间保证、易于稳定实现、左右递归
     * 可并行，并且链表合并可通过重连指针高效完成。
     */

    /*
     * 4c.1 - Official solution / 官方答案
     * A, B, C. Bounded by Omega(N log N) lower bound. Both insertion sort and
     * heapsort both have a lower bound of Omega(N).
     *
     * 4c.2 - Official solution / 官方答案
     * B, E. Worst case runtime that is asymptotically better than Quicksort's
     * worst case runtime. Quicksort has a worst case runtime of O(N^2), while
     * both mergesort and heapsort have a worst-case runtime of O(N log N).
     *
     * 4c.3 - Official solution / 官方答案
     * C. In the worst case, performs Theta(N) pairwise swaps of elements. When
     * thinking of pairwise swaps, both selection and insertion sort come to
     * mind. Selection sort does at most Theta(N) swaps, while it is possible for
     * insertion sort to need Theta(N^2) swaps (for example, a reverse sorted
     * array).
     *
     * 4c.4 - Official solution / 官方答案
     * A, B, D. Never compares the same two elements twice. Notice for QuickSort
     * and MergeSort that once we do a comparison between two elements (the pivot
     * for QuickSort and elements within a recursive subarray in MergeSort), we
     * will never compare those two elements again. For example, we won't compare
     * the pivot against any other element again, and a sorted subarray will never
     * have elements within it compared against one another. Insertion sort is
     * much the same, as we bubble down elements into their respective places,
     * comparing only against elements to the ”left” of them.
     *
     * 4c.5 - Official solution / 官方答案
     * F. Runs in best case Theta(log N) time for certain inputs. The best case
     * runtime for a sorting algorithm cannot be faster than Theta(N). This is
     * because at the very least, we need to check if all elements are sorted,
     * and since there are N elements, we can't have an algorithm that sorts
     * faster than Theta(N).
     *
     * 中文解析：第 1 项中的选择排序为 Theta(N^2)，因此也属于
     * Omega(N log N)。第 3 项只计算成对交换，不计算比较或移动。第 5 项
     * 使用读取或确认 N 个元素所需的线性下界。
     */
    public static final List<String> QUESTION_4C_ANSWERS = List.of(
        "A, B, C",
        "B, E",
        "C",
        "A, B, D",
        "F"
    );

    public static void main(String[] args) {
        if (!Arrays.equals(QUESTION_4A_EXAMPLE, new int[]{5, 4, 3, 2, 1})) {
            throw new AssertionError("Unexpected insertion-sort example");
        }
        if (QUESTION_4C_ANSWERS.size() != 5) {
            throw new AssertionError("Expected five answers for 4c");
        }
    }
}
