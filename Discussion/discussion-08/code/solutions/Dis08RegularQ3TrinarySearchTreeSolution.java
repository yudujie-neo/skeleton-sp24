package solutions;

import java.util.ArrayList;
import java.util.List;

/** Official solution and executable check for Discussion 08 Regular Q3. */
public class Dis08RegularQ3TrinarySearchTreeSolution {

    /*
     * Official solution / 官方答案
     * Inorder traversal on a BST yields the sorted elements in the BST in
     * ascending order. Therefore, the core of the algorithm we'd like here is
     * going to be quite similar to inorder traversal, but reversed (visit the
     * right child before the left child) and with the added caveat that we also
     * must traverse through the middle children.
     *
     * In essence, given the root of some TST, we reverse onto the right child
     * subtree, then print the root's value, then reverse onto the middle child
     * subtree, then finally reverse onto the left subtree. The print root value
     * and reverse onto the middle child steps can be swapped, because overall
     * the order of the printed values should be the same.
     *
     * Pseudocode:
     * reverse(tst):
     *     if tst is null:
     *         return
     *     reverse(tst.right)
     *     print(tst.value)
     *     reverse(tst.middle)
     *     reverse(tst.left)
     *
     * 中文解释：先访问所有更大值所在的右子树，再访问根及相等值的中间
     * 分支，最后访问更小值的左子树。每个结点访问一次，时间为 Theta(N)，
     * 递归栈为 Theta(H)。
     */
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
        if (root == null) {
            return;
        }
        printDescending(root.right);
        System.out.println(root.value);
        printDescending(root.middle);
        printDescending(root.left);
    }

    /** Same traversal collected into a list to support a deterministic test. */
    public static List<Integer> descendingValues(TstNode root) {
        List<Integer> result = new ArrayList<>();
        collectDescending(root, result);
        return result;
    }

    private static void collectDescending(TstNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        collectDescending(root.right, result);
        result.add(root.value);
        collectDescending(root.middle, result);
        collectDescending(root.left, result);
    }

    private static TstNode exampleTree() {
        TstNode root = new TstNode(10);
        root.left = new TstNode(3);
        root.left.left = new TstNode(1);
        root.left.right = new TstNode(7);
        root.left.right.middle = new TstNode(7);
        root.middle = new TstNode(10);
        root.middle.middle = new TstNode(10);
        root.right = new TstNode(12);
        root.right.left = new TstNode(11);
        root.right.middle = new TstNode(12);
        root.right.right = new TstNode(14);
        root.right.right.left = new TstNode(13);
        root.right.right.right = new TstNode(15);
        return root;
    }

    public static void main(String[] args) {
        List<Integer> expected = List.of(15, 14, 13, 12, 12, 11, 10, 10, 10, 7, 7, 3, 1);
        List<Integer> actual = descendingValues(exampleTree());
        if (!actual.equals(expected)) {
            throw new AssertionError("Unexpected descending traversal: " + actual);
        }
    }
}
