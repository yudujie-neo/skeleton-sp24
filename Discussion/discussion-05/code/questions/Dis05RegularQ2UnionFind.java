package questions;

import java.util.Arrays;

/**
 * CS 61B Spring 2024 - Discussion 05, Regular Question 2.
 * Disjoint Sets / 不相交集合（并查集）
 */
public class Dis05RegularQ2UnionFind {

    /** Minimal API used only to make the official operation sequences compilable. */
    public interface UnionFind {
        void connect(int a, int b);

        int find(int value);

        int[] snapshot();
    }

    /*
     * 2a - English (official question)
     * Assume we have nine items, represented by integers 0 through 8. All items
     * are initially unconnected to each other. Draw the union find tree, draw
     * its array representation after the series of connect() and find()
     * operations, and write down the result of find() operations using
     * WeightedQuickUnion without path compression. Break ties by choosing the
     * smaller integer to be the root.
     *
     * 2a - 中文翻译
     * 从互不连通的 0 到 8 开始，使用不带路径压缩的 WeightedQuickUnion
     * 执行下列操作。画出树和数组，记录 find 结果；大小相同时选较小根。
     *
     * My answer / 我的答案：
     * find results / find 结果：TODO
     * final array / 最终数组：TODO
     * tree drawing / 树形图：TODO（可在 Markdown/纸上完成）
     */
    public static int[] question2a(UnionFind unionFind) {
        unionFind.connect(2, 3);
        unionFind.connect(1, 2);
        unionFind.connect(5, 7);
        unionFind.connect(8, 4);
        unionFind.connect(7, 2);
        int first = unionFind.find(3);
        unionFind.connect(0, 6);
        unionFind.connect(6, 4);
        unionFind.connect(6, 3);
        int second = unionFind.find(8);
        int third = unionFind.find(6);
        return new int[]{first, second, third};
    }

    /*
     * 2b - English (official question)
     * If N is the number of nodes in the set, what is the runtime of find in
     * the worst case? Draw out the structure of the Disjoint Set representation
     * for this worst case.
     *
     * 2b - 中文翻译
     * 对下面普通 QuickUnion 的递归 find，N 个节点时最坏运行时间是多少？
     * 画出产生最坏情况的结构。
     *
     * My answer / 我的答案：TODO
     */
    public static class QuickUnionFor2b {
        private final int[] parents;

        public QuickUnionFor2b(int[] parents) {
            this.parents = Arrays.copyOf(parents, parents.length);
        }

        public int parent(int val) {
            return parents[val];
        }

        public int find(int val) {
            int p = parent(val);
            if (p == -1) {
                return val;
            } else {
                int root = find(p);
                return root;
            }
        }
    }

    /*
     * 2c - English (official question)
     * Using a function setParent(int val, int newParent), which updates the
     * value of val's parent to newParent, modify find to achieve a faster
     * runtime using path compression. You may add at most one line to the
     * provided implementation.
     *
     * 2c - 中文翻译
     * 使用 setParent(int val, int newParent) 修改 find 以实现路径压缩；
     * 最多只能增加一行。
     *
     * Add your one line at TODO / 在 TODO 处增加一行。
     */
    public static class QuickUnionFor2c {
        private final int[] parents;

        public QuickUnionFor2c(int[] parents) {
            this.parents = Arrays.copyOf(parents, parents.length);
        }

        public int parent(int val) {
            return parents[val];
        }

        public void setParent(int val, int newParent) {
            parents[val] = newParent;
        }

        public int find(int val) {
            int p = parent(val);
            if (p == -1) {
                return val;
            } else {
                int root = find(p);
                // TODO: Add at most one line / 最多增加一行，实现路径压缩。
                return root;
            }
        }
    }

    /*
     * 2d - English (official extra practice)
     * Extra Practice: Draw out the tree and array representation for the
     * following WeightedQuickUnion with path compression that has 9 elements
     * from 0 to 8. Break ties by choosing the smaller integer to be root.
     *
     * 2d - 中文翻译
     * 对 0 到 8 的带路径压缩 WeightedQuickUnion 执行下列操作，画出最终树
     * 和数组。大小相同时选择较小根。
     *
     * My answer / 我的答案：TODO
     */
    public static int[] question2d(UnionFind unionFind) {
        unionFind.connect(2, 3);
        unionFind.connect(1, 2);
        unionFind.connect(5, 7);
        unionFind.connect(8, 4);
        unionFind.connect(7, 2);
        int first = unionFind.find(3);
        unionFind.connect(0, 6);
        unionFind.connect(7, 4);
        unionFind.connect(6, 3);
        int second = unionFind.find(8);
        int third = unionFind.find(6);
        return new int[]{first, second, third};
    }
}
