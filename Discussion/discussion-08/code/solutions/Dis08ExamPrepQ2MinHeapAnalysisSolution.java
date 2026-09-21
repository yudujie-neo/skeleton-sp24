package solutions;

/** Official solution for Discussion 08 Exam-Level Question 2. */
public class Dis08ExamPrepQ2MinHeapAnalysisSolution {

    /*
     * Official solution / 官方答案
     * 1. removeMin has a best case runtime of Theta(1) and a worst case runtime
     *    of Theta(log N).
     * 2. insert has a best case runtime of Theta(1) and a worst case runtime of
     *    Theta(log N).
     * 3. A pre order or level order traversal on a min-heap can output the
     *    elements in sorted order.
     *
     *    Explanation: The smallest item of a min heap is at the top, so whatever
     *    traversal we choose must output the top element first in a complete
     *    binary tree. Only preorder and level-order have this property.
     *
     * 4. The fourth smallest element in a min-heap with 1000 distinct elements
     *    can appear in 14 places in the heap.
     *
     *    Explanation: The 4th smallest item can be on the 2nd, 3rd, or 4th
     *    level of the heap.
     *
     * 5. Given a min-heap with 2^N - 1 distinct elements, for an element:
     *    - to be on the second level it must be less than 2^(N-1) - 2
     *      element(s) and greater than 1 element(s).
     *    - to be on the bottommost level it must be less than 0 element(s) and
     *      greater than N - 1 element(s). (must be greater than the elements on
     *      its branch)
     *
     *    Explanation: An element on the second level must be larger than the
     *    root and less than the elements in its subtree. There are 2^(N-1) - 2
     *    elements in the subtree of an element on the second level: half the
     *    elements in the tree minus the root, then subtracting off the node
     *    itself.
     *
     *    An element on the bottom level must be greater than all elements on
     *    the path from itself to the root. A min heap with 2^N - 1 elements has
     *    N levels, so there are N - 1 items above it on a path to the root.
     *
     * 中文解释
     * removeMin/insert 最好无需继续下沉或上浮，最坏移动一整条树高。题目
     * 说的是 may output，而不是所有堆都保证按序。第四小元素最多有三个
     * 更小祖先，所以只能在第 2、3、4 层，共 2 + 4 + 8 = 14 个位置。
     *
     * Supplemental analysis / 补充分析（非官方答案）：
     * 题目开头把 N 定义为堆中元素个数，但第 5 题和 Hint 又用 N 表示层数。
     * 这是官方题面的符号复用不一致；上面的官方答案按 PDF 原文保留。第
     * 1、2 题将 N 解释为元素数，第 5 题按 Hint 将 N 解释为层数。
     */

    /** Checkable forms of the official numeric blanks. */
    public static int possiblePositionsForFourthSmallest() {
        return 2 + 4 + 8;
    }

    public static long guaranteedLargerBelowSecondLevel(int levels) {
        if (levels < 2 || levels >= Long.SIZE) {
            throw new IllegalArgumentException("levels must be in [2, 64)");
        }
        return (1L << (levels - 1)) - 2;
    }

    public static int guaranteedSmallerAboveBottomLevel(int levels) {
        if (levels < 1) {
            throw new IllegalArgumentException("levels must be positive");
        }
        return levels - 1;
    }

    public static void main(String[] args) {
        if (possiblePositionsForFourthSmallest() != 14
                || guaranteedLargerBelowSecondLevel(5) != 14
                || guaranteedSmallerAboveBottomLevel(5) != 4) {
            throw new AssertionError("Discussion 08 Exam-Level Q2 checks failed");
        }
    }
}
