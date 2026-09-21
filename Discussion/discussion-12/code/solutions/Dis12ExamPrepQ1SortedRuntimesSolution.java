package solutions;

import java.util.List;

/** Official solutions and Chinese explanation for Discussion 12 Exam Prep Q1. */
public class Dis12ExamPrepQ1SortedRuntimesSolution {

    /*
     * 1a - Official solution / 官方答案
     * Best Case: Theta(N), Worst Case: Theta(N^2)
     *
     * Once we have 100 runs of size N/100, insertion sort will take best case
     * Theta(N) and worst case Theta(N^2) time. Note that the number of merging
     * operations is actually constant (in particular, it takes about 7 splits
     * and merges to get to an array of size N/2^7 = N / 128).
     *
     * 中文解析：阈值是输入规模的固定比例，因此每个 run 仍有 Theta(N)
     * 个元素，而 run 的数量只是常数。之后常数层合并不改变主导项。
     *
     * Supplemental analysis / 补充分析（非官方答案）：
     * 官方先说有 “100 runs of size N/100”，随后说 7 次二分得到大小 N/128
     * 的 run。严格按二分阈值 <= N/100，会得到 128 个大小约 N/128 的 run，
     * 而不是恰好 100 个；这不影响渐近结论。
     */

    /*
     * 1b - Official solution / 官方答案
     * Best Case: Theta(N log(N)), Worst Case: Theta(N log(N))
     *
     * Doing an extra N work each iteration of quicksort doesn't asymptotically
     * change the best case runtime, since we have to do N work to partition the
     * array. However, it improves the worst case runtime, since we avoid the
     * ”bad” case where the pivot is on the extreme end(s) of the partition.
     *
     * 中文解析：精确中位数保证每次分区平衡，递归树有 Theta(log N) 层；
     * 每层的中位数选择与分区总工作均为 Theta(N)。
     */

    /*
     * 1c - Official solution / 官方答案
     * Best Case: Theta(N log(N)), Worst Case: Theta(N log(N))
     *
     * While a max-heap is better, we can make do with a min-heap by placing the
     * smallest element at the right end of the list until the list is sorted in
     * descending order. Once the list is in descending order, it can be sorted
     * in ascending order with a simple linear time pass.
     *
     * 中文解析：反复把最小值放到右端会先得到降序数组，再原地反转成
     * 升序。额外空间仍为 O(1)。
     */

    /*
     * 1d.i - Official solution / 官方答案
     * There are at most N inversions.
     * Best Case: Theta(N), Worst Case: Theta(N)
     *
     * Recall that insertion sort takes Theta(N + K) time, where K is the number
     * of inversions. Thus, the optimal sorting algorithm would be insertion
     * sort. If K < N, then, insertion sort has the best and worst case runtime
     * of Theta(N).
     *
     * 1d.ii - Official solution / 官方答案
     * There is exactly 1 inversion.
     * Best Case: Theta(1), Worst Case: Theta(N)
     *
     * First, we notice that if there is only 1 inversion, it could only involve
     * 2 adjacent elements. Intuitively, if two elements that are apart form an
     * inversion, than some element between these two would also form an
     * inversion with one of the elements.
     *
     * (Optional) A formal argument is as follows: suppose the only inversion
     * involves 2 elements that are not adjacent. Let's call their indices i and
     * j, where i < j, and a[i] > a[j] (definition of inversion). Because they
     * are not adjacent, there exist some index k, such that i < k < j. In case
     * 1, assume a[k] > a[i]. Then it follows that a[k] > a[i] > a[j]. Because
     * k < j but a[k] > a[j], (k,j) forms an inversion, so we have a
     * contradiction (we assumed ony 1 inversion). In case 2, assume
     * a[k] < a[i], but then we have k > i, so (k,i) also form an inversion,
     * which is also a contradiction.
     *
     * Using this, we can just compare neighboring elements to find that exact
     * inversion, and swap the 2 elements. If the inversion involves the first
     * two elements, constant time is needed. If the inversion involves elements
     * at the end, N time is needed.
     *
     * Supplemental analysis / 补充分析（非官方答案）：
     * 官方第二种情况写成 “(k,i) also form an inversion”。按逆序对以较小
     * 下标在前的记法，已知 i < k 且 a[i] > a[k]，应写为 (i,k)。这是索引
     * 顺序笔误，不影响结论。
     *
     * 1d.iii - Official solution / 官方答案
     * There are exactly N(N-1)/2 inversions.
     * Best Case: Theta(N), Worst Case: Theta(N)
     *
     * If a list has N(N-1)/2 inversions, it means it is sorted in descending
     * order. This is because every possible pair is an inversion (The total
     * number of unordered pairs from N elements is n choose 2, or N(N-1)/2).
     * So, it can be sorted in ascending order with a simple linear time pass.
     * We know that reversing any array is a linear time operation, so the
     * optimal runtime of any sorting algorithm is Theta(N).
     *
     * 中文解析：逆序对至多为 N 时使用插入排序；恰好一个逆序对时扫描相邻
     * 对；最大逆序对数意味着严格降序，原地反转即可。
     */

    public static final List<String> BEST_CASES = List.of(
        "Theta(N)",
        "Theta(N log N)",
        "Theta(N log N)",
        "Theta(N)",
        "Theta(1)",
        "Theta(N)"
    );

    public static final List<String> WORST_CASES = List.of(
        "Theta(N^2)",
        "Theta(N log N)",
        "Theta(N log N)",
        "Theta(N)",
        "Theta(N)",
        "Theta(N)"
    );

    public static void main(String[] args) {
        if (BEST_CASES.size() != 6 || WORST_CASES.size() != 6) {
            throw new AssertionError("Expected six runtime cases");
        }
    }
}
