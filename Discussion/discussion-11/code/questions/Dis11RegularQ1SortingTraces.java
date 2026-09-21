import java.util.Arrays;

/**
 * Discussion 11, Regular, Question 1 - All Sorts Of Sorts.
 *
 * English - Official question
 * Show the steps taken by each sort on the following unordered list:
 * 0, 4, 2, 7, 6, 1, 3, 5
 *
 * 1a. Insertion sort.
 * 1b. Selection sort.
 * 1c. Merge sort.
 * 1d. Use heapsort to sort the following array (hint: draw out the heap).
 *     Draw out the array at each step: 0, 6, 2, 7, 4.
 *
 * 中文翻译
 * 展示各排序算法处理数组 0, 4, 2, 7, 6, 1, 3, 5 时的步骤：
 * 1a 插入排序；1b 选择排序；1c 归并排序。
 * 1d 使用堆排序处理 0, 6, 2, 7, 4（提示：画出堆），并画出每一步的数组。
 *
 * Your answer / 你的答案
 * 在下面的 TODO 区域记录每种算法的中间状态。Q1d 的树形图可继续在
 * discussion-11.md 中作答；不要用失真的 ASCII 图替代官方堆图。
 */
public class Dis11RegularQ1SortingTraces {
    private static final int[] SORT_INPUT = {0, 4, 2, 7, 6, 1, 3, 5};
    private static final int[] HEAPSORT_INPUT = {0, 6, 2, 7, 4};

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    public static int[] sortingInput() {
        return Arrays.copyOf(SORT_INPUT, SORT_INPUT.length);
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    public static int[] heapsortInput() {
        return Arrays.copyOf(HEAPSORT_INPUT, HEAPSORT_INPUT.length);
    }

    /*
     * TODO 1a / 你的答案：记录插入排序的每一步。
     * TODO 1b / 你的答案：记录选择排序的每一步。
     * TODO 1c / 你的答案：记录归并排序的拆分与合并步骤。
     * TODO 1d / 你的答案：记录建堆和逐次删除最大值后的数组；在 Markdown 中画堆。
     */
}
