import java.util.Arrays;

/**
 * Discussion 11, Regular, Question 1 - Solution.
 *
 * Official solution / 官方答案（英文原文）
 *
 * 1d:
 * First, we need to heapify our array. We convert the current array to a max heap.
 * Recall that to heapify our array, we bubble down in reverse level order
 * (bottom to top, right to left). This means we start by bubbling down 4, which
 * in this case gives us the same heap structure. Bubbling down 7, and then 2,
 * leaves the heap unchanged as well. Bubbling down 6 (swapping 6 and 7) then
 * gives us the following. Bubbling down 0 gives us our final heap.
 *
 * Note that as we heapify, we also modify the underlying array representation as
 * well. This means that our final array looks like [7, 6, 2, 0, 4]. We then begin
 * popping off the max value from the heap, placing it at the back of the array.
 * Note that our underlying array representation doesn't consider the popped value
 * as part of the heap any more. We start by popping off 7 and bubbling down.
 * Our array now looks like [6, 4, 2, 0, 7], where the bolded section is considered
 * sorted and not part of the heap. We then continue by popping off 6, producing
 * [4, 0, 2, 6, 7]. We then pop off 4, producing [2, 0, 4, 6, 7]. In a similar
 * fashion, we pop off 2 and 0, resulting in [0, 2, 4, 6, 7] and finally the
 * sorted array [0, 2, 4, 6, 7].
 *
 * 中文说明（非官方答案）
 * 下列常量逐项保存官方答案中的数组状态。Q1d 的树形堆图仍以 Markdown 中引用的
 * 官方图片为准；Java 数据只用于检查底层数组表示。
 */
public class Dis11RegularQ1SortingTracesSolution {
    public static final String[] INSERTION_STEPS = {
        "0 | 4 2 7 6 1 3 5",
        "0 4 | 2 7 6 1 3 5",
        "0 2 4 | 7 6 1 3 5",
        "0 2 4 7 | 6 1 3 5",
        "0 2 4 6 7 | 1 3 5",
        "0 1 2 4 6 7 | 3 5",
        "0 1 2 3 4 6 7 | 5",
        "0 1 2 3 4 5 6 7 |"
    };

    public static final String[] SELECTION_STEPS = {
        "0 | 4 2 7 6 1 3 5",
        "0 1 | 2 7 6 4 3 5",
        "0 1 2 | 7 6 4 3 5",
        "0 1 2 3 | 6 4 7 5",
        "0 1 2 3 4 | 6 7 5",
        "0 1 2 3 4 5 | 7 6",
        "0 1 2 3 4 5 6 | 7",
        "0 1 2 3 4 5 6 7 |"
    };

    public static final String[] MERGE_STEPS = {
        "[0 4 2 7 6 1 3 5]",
        "[0 4 2 7] [6 1 3 5]",
        "[0 4] [2 7] [6 1] [3 5]",
        "[0] [4] [2] [7] [6] [1] [3] [5]",
        "[0 4] [2 7] [1 6] [3 5]",
        "[0 2 4 7] [1 3 5 6]",
        "[0 1 2 3 4 5 6 7]"
    };

    public static final int[][] HEAPSORT_ARRAY_STEPS = {
        {0, 6, 2, 7, 4},
        {7, 6, 2, 0, 4},
        {6, 4, 2, 0, 7},
        {4, 0, 2, 6, 7},
        {2, 0, 4, 6, 7},
        {0, 2, 4, 6, 7}
    };

    /** Small deterministic smoke test / 小型确定性验证. */
    public static void main(String[] args) {
        int[] expected = {0, 2, 4, 6, 7};
        int[] actual = HEAPSORT_ARRAY_STEPS[HEAPSORT_ARRAY_STEPS.length - 1];
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("Unexpected final heapsort array");
        }
        if (INSERTION_STEPS.length != 8 || SELECTION_STEPS.length != 8
                || MERGE_STEPS.length != 7) {
            throw new AssertionError("A sorting trace is incomplete");
        }
    }
}
