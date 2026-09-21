import java.util.Arrays;

/**
 * Discussion 11, Exam Prep, Question 1 - Solution.
 *
 * Official solution / 官方答案（英文原文）
 * 1a. Mergesort. One identifying feature is that the left and right halves do
 * not interact until the very end. The first line has had several steps applied
 * yet is unchanged, reflecting how mergesort first partitions without sorting.
 *
 * 1b. Quicksort. The first item was chosen as pivot, so the first pivot is 1429,
 * and the first iteration breaks the array into < 1429, = 1429, and > 1429.
 *
 * 1c. Insertion Sort. It starts at the front and moves each item toward the
 * front as far as possible. These are the first few iterations, so the right
 * side remains unchanged.
 *
 * 1d. Heapsort. The second line is in the middle of heapifying the list into a
 * maxheap. Then we continually remove the max and place it at the end.
 *
 * 中文解释
 * A 的左右半区独立演化；B 由首枢轴 1429 分区；C 的有序前缀逐渐增长；
 * D 的最大值逐个固定到末尾。
 */
public class Dis11ExamPrepQ1IdentifyingSortsSolution {
    public static final String[] ANSWERS = {
        "Mergesort", "Quicksort", "Insertion Sort", "Heapsort"
    };

    public static final int[] FINAL_STEP = {
        129, 192, 594, 1000, 1337, 1429, 3291, 4242, 4392, 7683, 9001
    };

    public static void main(String[] args) {
        int[] copy = Arrays.copyOf(FINAL_STEP, FINAL_STEP.length);
        Arrays.sort(copy);
        if (!Arrays.equals(copy, FINAL_STEP) || ANSWERS.length != 4) {
            throw new AssertionError("Incomplete or invalid identifying-sorts solution");
        }
    }
}
