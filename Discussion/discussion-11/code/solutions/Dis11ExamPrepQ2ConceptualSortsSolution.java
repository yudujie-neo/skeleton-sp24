/**
 * Discussion 11, Exam Prep, Question 2 - Solution.
 *
 * Solution overview / 答案概述（非官方摘要）
 *
 * 2a. The array is nearly sorted. The time complexity of insertion sort is
 * Theta(N + K), where K is the number of inversions. When K is small,
 * insertion sort runs fast.
 *
 * 2b. A simple example is 5 4 3 2 1. Any five-integer array in descending
 * order would work.
 *
 * 2c. False. Stability means equal elements retain their relative ordering.
 * Heap operations may disturb that ordering. A concrete max-heap example is
 * 21 20a 20b 12 11 8 7.
 *
 * 2d. (1) Mergesort has Theta(N log N) worst-case runtime versus quicksort's
 * Theta(N^2). (2) Mergesort is stable, whereas quicksort typically is not.
 * (3) Mergesort is more memory efficient for a linked list because no auxiliary
 * array is needed; one can modify node pointers to "snakeweave" nodes in order.
 *
 * 2e(1). A, B, C. All these sorts take at least Omega(N log N). In a sorted
 * list, insertion sort has linear runtime. Similarly, heapsort has linear
 * runtime on a heap of equal items.
 *
 * 2e(2). B, E. Only these two sorts are guaranteed to be better than Theta(N^2).
 *
 * 2e(3). A, B, D. Quicksort never compares the same elements after a pivot is
 * finished. Mergesort comparisons happen while merging different recursive
 * halves. Selection sort can compare the same items again while finding the
 * maximum. Insertion sort never compares the same elements twice. Heapsort may
 * compare the same items during heapification and later bubbling down.
 *
 * 2e(4). N. Sorting is theoretically lower-bounded by Theta(N): any sorting
 * algorithm must examine every element at least once.
 *
 * 中文解释
 * 插入排序的工作量与逆序对数有关；降序五元素给出最大逆序对。普通堆排序不稳定。
 * 链表归并可通过重连指针完成，并提供稳定的最坏 Theta(N log N) 保证。
 *
 * Supplemental analysis / 补充分析（非官方答案）
 * The official 2e(1) explanation says heapsort can be linear on a heap of equal
 * items. That depends on an implementation whose sink stops immediately when
 * parent and child are equal; it is not a universal statement about every
 * possible heapsort implementation. The official choices are preserved above.
 */
/*
 * Complete official 2e explanation / 完整官方答案英文
 *
 * Bounded by Omega(N log N) lower bound: A, B, C. All these sorts take at least
 * Omega(N log N). In a sorted list, insertion sort has linear runtime. Similarly,
 * heapsort has linear runtime on a heap of equal items.
 *
 * Has a worst case runtime asymptotically better than Quicksort's worst case:
 * B, E. Only these two sorts are guaranteed to be better than Theta(N^2).
 *
 * Never compares the same two elements twice: A, B, D. Quicksort never compares
 * the same two elements, since after partitioning around a pivot that pivot is
 * never used again for comparison. Mergesort also never compares two elements
 * twice: comparisons happen in the merge stage and we only compare items from
 * different halves of the recursive call. Selection sort can compare the same
 * items twice, since it requires finding the maximum on each iteration. Insertion
 * sort never compares the same elements twice; comparisons happen when swapping
 * to the front and once an item is at its correct location it is never compared
 * or swapped again. Heapsort may compare the same items multiple times during
 * heapification and bubbling down after a removal.
 *
 * Runs in best case Theta(log N) time for certain inputs: N. Sorting is
 * theoretically lower-bounded by Theta(N) - any sorting algorithm must examine
 * each element at least once.
 */
public class Dis11ExamPrepQ2ConceptualSortsSolution {
    public static final int[] WORST_CASE_INSERTION = {5, 4, 3, 2, 1};
    public static final String[] ANSWER_BANK_RESULTS = {"ABC", "BE", "ABD", "N"};

    public static void main(String[] args) {
        if (WORST_CASE_INSERTION.length != 5 || ANSWER_BANK_RESULTS.length != 4) {
            throw new AssertionError("Conceptual-sorts solution data is incomplete");
        }
        for (int i = 1; i < WORST_CASE_INSERTION.length; i += 1) {
            if (WORST_CASE_INSERTION[i - 1] <= WORST_CASE_INSERTION[i]) {
                throw new AssertionError("Expected a descending example");
            }
        }
    }
}
