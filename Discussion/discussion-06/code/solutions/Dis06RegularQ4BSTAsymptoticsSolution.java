import java.util.List;

/** Discussion 06, Regular Question 4 — Solution. */
public class Dis06RegularQ4BSTAsymptoticsSolution {
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

    /**
     * Official solution (English)
     * 4a: Find on a perfectly bushy BST takes O(log(N)) time because its height is
     * log(N). There is no single tight bound for an unspecified find: finding the
     * root is Omega(1), while a root-to-leaf path is O(log(N)).
     *
     * 4b: Insert the keys in ascending sorted order [-3, 0, 2, 5, 6, 9]. This
     * produces a perfectly linear BST, so the longest find path visits every node.
     * Descending sorted order also produces a linear BST, chained to the left.
     * The official drawing remains in discussion-06.md/assets.
     *
     * 中文解释
     * 平衡树高度为 log N；具体一次查询可从常数到对数。升序或降序插入会
     * 生成高度线性的退化 BST。
     *
     * Supplemental analysis / 补充分析（非官方答案）
     * 题干只要求 O(N)，而 O(log N) 也属于 O(N)。官方意图显然是构造最坏
     * 查找为 Theta(N) 的线性链。
     */
    static List<Integer> insertionOrder() {
        return List.of(-3, 0, 2, 5, 6, 9);
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    static final class BST {
        Key key;
        BST left;
        BST right;

        BST(Key key) {
            this.key = key;
        }

        void insert(Key newKey) {
            int comparison = newKey.compareTo(key);
            if (comparison < 0) {
                if (left == null) {
                    left = new BST(newKey);
                } else {
                    left.insert(newKey);
                }
            } else if (comparison > 0) {
                if (right == null) {
                    right = new BST(newKey);
                } else {
                    right.insert(newKey);
                }
            }
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

    public static void main(String[] args) {
        BST root = null;
        for (int key : insertionOrder()) {
            if (root == null) {
                root = new BST(new Key(key));
            } else {
                root.insert(new Key(key));
            }
        }
        if (find(root, new Key(9)) == null || find(root, new Key(7)) != null) {
            throw new AssertionError("BST find smoke test failed");
        }
    }
}
/* Official solution transcript (English; mathematical typography normalized for Java comments): Find operations on a perfectly bushy BST take O(log(N)) time, as the height of a perfectly bushy BST is log(N). In the worst case scenario, the key we're looking at is all the way at a leaf, so we have to traverse a path from root to leaf of length log(N). We cannot generalize the runtime of find to a tight bound because the lower and upper bounds are different. It is lower-bounded by Omega(1) (the case where the key we are looking for is at the root of the BST provided) and upper-bounded by O(log(N)) (mentioned above as the path from root to leaf). Therefore, there is no tight bound for find. We should insert the keys in ascending sorted order: [-3, 0, 2, 5, 6, 9]. This results in a perfectly linear BST, which means that the longest path for find (from root to leaf) traverses every single node in the BST. Alternatively, we could insert the keys in descending sorted order, which also results in a perfectly linear BST (but the keys would chain left from 9 to 6 to 5 to 2 to 0 to -3). */
