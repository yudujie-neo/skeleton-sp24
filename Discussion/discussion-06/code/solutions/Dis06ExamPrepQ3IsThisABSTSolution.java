/** Discussion 06, Exam Prep Question 3 — Solution. */
public class Dis06ExamPrepQ3IsThisABSTSolution {
    public static boolean brokenIsBST(BST tree) {
        if (tree == null) {
            return true;
        } else if (tree.left != null && tree.left.key > tree.key) {
            return false;
        } else if (tree.right != null && tree.right.key < tree.key) {
            return false;
        } else {
            return brokenIsBST(tree.left) && brokenIsBST(tree.right);
        }
    }

    // Official solution code.
    public static boolean isBST(BST T) {
        return isBSTHelper(T, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBSTHelper(BST T, int min, int max) {
        if (T == null) {
            return true;
        } else if (T.key <= min || T.key >= max) {
            return false;
        } else {
            return isBSTHelper(T.left, min, T.key)
                    && isBSTHelper(T.right, T.key, max);
        }
    }

    /**
     * Official solution (English)
     * 3a: A counterexample has root 10, left child 5, right child 15, and node 12
     * as the right child of 5. brokenIsBST only checks direct parent-child order;
     * 12 is locally valid relative to 5 but violates the bound imposed by ancestor
     * 10. The method does return true for every tree that actually is a BST.
     * The official drawing remains in discussion-06.md/assets.
     *
     * 3b: Traverse recursively while carrying the minimum and maximum valid keys.
     * An empty tree is valid. A key outside the precomputed range is invalid. For
     * the left subtree, the current key becomes the new maximum; for the right,
     * it becomes the new minimum.
     *
     * 中文解释
     * 仅比较父子关系不能保证整棵子树满足祖先的范围。辅助函数携带当前节点
     * 允许的开区间，并在向左、向右递归时更新上界或下界。
     *
     * Supplemental analysis / 补充分析（非官方答案）
     * 官方 int 开区间实现会拒绝键 Integer.MIN_VALUE 或 Integer.MAX_VALUE。
     * 可改用 long 边界与 Long.MIN_VALUE/Long.MAX_VALUE。官方说明中还写左侧
     * “less than or equal to”，但代码实际要求严格小于；若 BST 不允许重复键，
     * 代码的严格比较才与该策略一致。
     */
    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    static final class BST {
        int key;
        BST left;
        BST right;

        BST(int key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {
        BST root = new BST(10);
        root.left = new BST(5);
        root.right = new BST(15);
        root.left.right = new BST(12);
        if (!brokenIsBST(root) || isBST(root)) {
            throw new AssertionError("BST counterexample smoke test failed");
        }
    }
}
/* Official solution transcript (English; mathematical typography normalized for Java comments): The method fails for some binary trees that are not BSTs because it only checks that the value at a node is greater than its left child and less than its right child, not that its value is greater than every node in the left subtree and less than every node in the right subtree. It is important to note that the method does indeed return true for every binary tree that actually is a BST (it correctly identifies proper BSTs). A BST is a naturally recursive structure, so it makes sense to use a recursive helper to go through the BST and ensure it is valid. Specifically, our recursive helper will traverse the BST while tracking the minimum and maximum valid values for subsequent nodes along our current path. We can get these minimum and maximum values by remembering the key property of BSTs: nodes to the left of our current node are always less than the current value, and nodes to the right of our current node are always greater than our current value. So for example, if we encounter a node with value 5, anything to the left must be < 5. In our base case, an empty BST is always valid. Otherwise, we can check the current node. If it doesn't fall within our precomputed min/max, we know this is invalid, and return immediately. Otherwise, we use the properties of BSTs to bound our subsequent min and max values. If we traverse to the left, everything must be less than or equal to the current value, so the value of our current node becomes the new max for the tree at T.left. Similar logic applies to the right. */
