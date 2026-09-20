/**
 * Discussion 06, Exam Prep Question 3: Is This a BST?
 *
 * English — Official setup
 * A BST has an int key and pointers to child BSTs left and right.
 *
 * 3a. brokenIsBST should check whether a binary tree is a BST, but it returns the
 * wrong answer for some trees. Give a counterexample.
 * 3b. Write isBST that fixes the error. Integer.MIN_VALUE and Integer.MAX_VALUE may
 * be helpful. Store information from previous layers, not only direct relatives.
 *
 * 中文翻译
 * 3a. 给出一个使 brokenIsBST 错判的二叉树。
 * 3b. 编写正确的 isBST。需要携带祖先层级的键信息；提示可使用整数上下界。
 *
 * My answer / 我的答案
 * 3a counterexample: TODO (official drawing remains in Markdown/assets)
 * 3b implementation: TODO below
 */
public class Dis06ExamPrepQ3IsThisABST {
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

    public static boolean isBST(BST T) {
        return todoIsBST(T); // TODO: call your helper with suitable bounds.
    }

    public static boolean isBSTHelper(BST T, int min, int max) {
        return todoIsBST(T); // TODO: implement the recursive bound checks.
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    private static boolean todoIsBST(BST T) {
        throw new UnsupportedOperationException("TODO: implement isBST using the official blanks");
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    static final class BST {
        int key;
        BST left;
        BST right;

        BST(int key) {
            this.key = key;
        }
    }
}
