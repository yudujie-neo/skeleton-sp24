package questions;

/** CS 61B Spring 2024 - Discussion 08, Regular Question 3. */
public class Dis08RegularQ3TrinarySearchTree {

    /*
     * English - Official question
     * We'd like a data structure that acts like a BST (Binary Search Tree) in
     * terms of operation runtimes but allows duplicate values. Therefore, we
     * decide to create a new data structure called a TST (Trinary Search Tree),
     * which can have up to three children, which we'll refer to as left, middle,
     * and right. In this setup, we have the following invariants, which are very
     * similar to the BST invariants:
     *
     * 1. Each node in a TST is a root of a smaller TST
     * 2. Every node to the left of a root has a value "lesser than" that of the root
     * 3. Every node to the right of a root has a value "greater than" that of the root
     * 4. Every node to the middle of a root has a value equal to that of the root
     *
     * Below is an example TST to help with visualization.
     * Describe an algorithm that will print the elements in a TST in descending
     * order. (Hint: recall that an in-order traversal for a BST gives elements
     * in increasing order.)
     *
     * 中文翻译
     * 我们要设计一种操作复杂度类似 BST、但允许重复值的三叉搜索树 TST。
     * 每个结点最多有 left、middle、right 三个孩子：左侧值小于根，中间值
     * 等于根，右侧值大于根，并且每个结点自身又是一棵 TST 的根。请描述
     * 一个按降序打印全部元素的算法。提示：BST 的中序遍历按升序输出。
     * 官方示例图保留在 ../../assets/question-03-tst.png。
     *
     * Your answer / 你的答案：TODO
     * Runtime / 时间复杂度：TODO
     * Auxiliary space / 辅助空间：TODO
     */

    /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
    public static final class TstNode {
        public final int value;
        public TstNode left;
        public TstNode middle;
        public TstNode right;

        public TstNode(int value) {
            this.value = value;
        }
    }

    public static void printDescending(TstNode root) {
        // TODO: Implement the requested traversal. Keep the method compilable.
    }
}
