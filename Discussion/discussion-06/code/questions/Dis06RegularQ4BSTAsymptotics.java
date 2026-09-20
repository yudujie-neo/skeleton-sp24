import java.util.List;

/**
 * Discussion 06, Regular Question 4: BST Asymptotics.
 *
 * English — Official setup
 * find returns the BST rooted at the node with key sk. A BST has a key and pointers
 * to two child BSTs, left and right.
 *
 * 4a. Assume the BST is perfectly bushy. What is the runtime of a single find in
 * terms of N, the number of nodes? Can we generalize find to a tight bound?
 * 4b. Starting with an empty BST, insert [6, 2, 5, 9, 0, -3] in an order such that
 * a single find after all insertions has O(N) runtime. Draw the resulting BST.
 *
 * 中文翻译
 * 4a. 假设 BST 完美平衡，求一次 find 关于节点数 N 的运行时间，并判断能否
 * 给出统一紧确界。
 * 4b. 从空树开始，以某种顺序插入 [6, 2, 5, 9, 0, -3]，使插入完成后
 * 一次 find 为 O(N)，并画出结果。
 *
 * My answer / 我的答案
 * 4a: TODO
 * 4b insertion order: TODO
 * The drawing remains in discussion-06.md/assets; do not replace it with ASCII art.
 */
public class Dis06RegularQ4BSTAsymptotics {
    public static BST find(BST tree, Key sk) {
        if (tree == null) {
            return null;
        }
        if (sk.compareTo(tree.key) == 0) {
            return tree;
        } else if (sk.compareTo(tree.key) < 0) {
            return find(tree.left, sk);
        } else {
            return find(tree.right, sk);
        }
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    static final class BST {
        Key key;
        BST left;
        BST right;

        BST(Key key) {
            this.key = key;
        }
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    static final class Key implements Comparable<Key> {
        final int value;

        Key(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(Key other) {
            return Integer.compare(value, other.value);
        }
    }

    /** Optional place to record 4b as Java data without exposing an answer. */
    static List<Integer> myInsertionOrder() {
        return List.of(); // TODO: return your insertion order.
    }
}
