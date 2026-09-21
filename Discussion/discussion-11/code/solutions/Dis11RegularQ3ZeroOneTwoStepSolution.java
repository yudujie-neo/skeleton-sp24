import java.util.Arrays;

/**
 * Discussion 11, Regular, Question 3 - Solution.
 *
 * Solution overview / 答案概述（非官方摘要）
 * 3a. The solution below is designed in the style of comparison swaps we have
 * seen thus far. It has flavor similar to quicksort with 1 as the pivot. There
 * is also a valid counting-sort approach, but counting sorts are not the focus
 * of this discussion.
 *
 * 3b. We were only able to write this algorithm because we knew there were
 * exactly three possible values in the array. In the general case, when the
 * collection has much more variety, we cannot make these guarantees.
 *
 * 3c. In general, a sorting operation is in place if it does not require
 * significant extra space. "Significant" is often linear or greater in the
 * number of elements. Making a whole new array is not in place. Selection sort,
 * insertion sort, and heap sort can be implemented in place. Mergesort can
 * technically be implemented in place, but it is rather complex. In-place
 * operations are beneficial because we want to use as little memory/computer
 * resources as possible; space efficiency matters in the real world.
 *
 * 中文解释
 * 循环维护 [0, front) 为 0、[front, curr) 为 1、(back, end] 为 2。
 * curr 遇到 2 时只缩小 back，因为交换回 curr 的值尚未检查。每个元素只被处理
 * 常数次，所以时间为 Theta(N)，额外空间为 O(1)。
 *
 * Supplemental analysis / 补充分析（非官方答案）
 * The Solutions PDF starts declaring swap immediately after the while loop and
 * omits the brace that closes specialSort. This file adds that required brace;
 * the omission is preserved here as an explicit source note rather than being
 * silently attributed to the official answer.
 */
/*
 * Complete official solution text / 完整官方答案英文
 *
 * 3b. While our algorithm is super cool, we were only able to write it because
 * we knew there were exactly 3 possible values that could be in our array! For
 * the general case, when the collections we're sorting have much more variety,
 * we can't make these kinds of guarantees.
 *
 * 3c. In general, we consider a sorting operation to be done in place if it does
 * not require significant extra space. "Significant extra space" is often
 * defined as linear or greater, with respect to the number of elements we are
 * sorting. For example, if our sorting algorithm requires making a whole new
 * array and copying elements over to it, then it is NOT in place because we had
 * to allocate significant space for this new array. Some algorithms that can be
 * implemented in place are selection sort, insertion sort, and heap sort.
 * Mergesort technically can be implemented in place, but it's rather complex.
 * Doing operations in place is beneficial because we typically want to use as
 * little memory/computer resources as possible. Though in this class we focus on
 * time efficiency, space efficiency is important too in the real world!
 */
public class Dis11RegularQ3ZeroOneTwoStepSolution {
    public static void specialSort(int[] arr) {
        int front = 0;
        int back = arr.length - 1;
        int curr = 0;

        while (curr <= back) {
            if (arr[curr] < 1) {
                swap(arr, curr, front);
                front += 1;
                curr += 1;
            } else if (arr[curr] > 1) {
                swap(arr, curr, back);
                back -= 1;
            } else {
                curr += 1;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[][] cases = {
            {}, {0}, {1}, {2}, {2, 0, 1, 2, 1, 0}, {2, 2, 0, 0, 1, 1}
        };
        for (int[] input : cases) {
            assertSorts(input);
        }
        for (int length = 0; length <= 8; length += 1) {
            int combinations = (int) Math.pow(3, length);
            for (int encoded = 0; encoded < combinations; encoded += 1) {
                int[] input = new int[length];
                int value = encoded;
                for (int i = 0; i < length; i += 1) {
                    input[i] = value % 3;
                    value /= 3;
                }
                assertSorts(input);
            }
        }
    }

    private static void assertSorts(int[] input) {
        int[] expected = Arrays.copyOf(input, input.length);
        Arrays.sort(expected);
        specialSort(input);
        if (!Arrays.equals(expected, input)) {
            throw new AssertionError("specialSort failed: " + Arrays.toString(input));
        }
    }
}
