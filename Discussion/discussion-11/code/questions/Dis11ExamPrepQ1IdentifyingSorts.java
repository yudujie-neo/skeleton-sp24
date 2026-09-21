import java.util.Arrays;

/**
 * Discussion 11, Exam Prep, Question 1 - Identifying Sorts.
 *
 * Question overview / 题目概述（非官方摘要）
 * Below are intermediate steps of sorting algorithms on the same input list.
 * The steps need not be consecutive, but are in the correct sequence. For each,
 * select insertion sort, selection sort, mergesort, quicksort (first element as
 * pivot), or heapsort. For an odd-length mergesort split, the larger half is on
 * the right.
 *
 * English - Official question / 英文原题
 * Below you will find intermediate steps in performing various sorting
 * algorithms on the same input list. The steps do not necessarily represent
 * consecutive steps in the algorithm (that is, many steps are missing), but
 * they are in the correct sequence. For each of them, select the algorithm it
 * illustrates from among the following choices: insertion sort, selection sort,
 * mergesort, quicksort (first element of sequence as pivot), and heapsort. When
 * we split an odd length array in half in mergesort, assume the larger half is
 * on the right.
 *
 * Input list:
 * 1429, 3291, 7683, 1337, 192, 594, 4242, 9001, 4392, 129, 1000
 *
 * 中文翻译
 * 下列各组是不同排序算法处理同一输入时的中间步骤；步骤可能不连续但顺序正确。
 * 对 1a-1d 分别从插入、选择、归并、快速（首元素为枢轴）和堆排序中识别算法。
 * 归并排序拆分奇数长度数组时，较大的一半在右侧。
 */
public class Dis11ExamPrepQ1IdentifyingSorts {
    public static final int[] INPUT = {
        1429, 3291, 7683, 1337, 192, 594, 4242, 9001, 4392, 129, 1000
    };

    public static final int[][] TRACE_A = {
        {1429, 3291, 7683, 1337, 192, 594, 4242, 9001, 4392, 129, 1000},
        {1429, 3291, 192, 1337, 7683, 594, 4242, 9001, 129, 1000, 4392},
        {192, 1337, 1429, 3291, 7683, 129, 594, 1000, 4242, 4392, 9001}
    };

    public static final int[][] TRACE_B = {
        {1337, 192, 594, 129, 1000, 1429, 3291, 7683, 4242, 9001, 4392},
        {192, 594, 129, 1000, 1337, 1429, 3291, 7683, 4242, 9001, 4392},
        {129, 192, 594, 1000, 1337, 1429, 3291, 4242, 4392, 7683, 9001}
    };

    public static final int[][] TRACE_C = {
        {1337, 1429, 3291, 7683, 192, 594, 4242, 9001, 4392, 129, 1000},
        {192, 1337, 1429, 3291, 7683, 594, 4242, 9001, 4392, 129, 1000},
        {192, 594, 1337, 1429, 3291, 7683, 4242, 9001, 4392, 129, 1000}
    };

    public static final int[][] TRACE_D = {
        {1429, 3291, 7683, 9001, 1000, 594, 4242, 1337, 4392, 129, 192},
        {7683, 4392, 4242, 3291, 1000, 594, 192, 1337, 1429, 129, 9001},
        {129, 4392, 4242, 3291, 1000, 594, 192, 1337, 1429, 7683, 9001}
    };

    private Dis11ExamPrepQ1IdentifyingSorts() {
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    public static int[] copyInput() {
        return Arrays.copyOf(INPUT, INPUT.length);
    }

    /*
     * My answer / 我的答案
     * TODO 1a:
     * TODO 1b:
     * TODO 1c:
     * TODO 1d:
     */
}
