package solutions;

import java.util.Arrays;

/** Official solutions and executable checks for Discussion 05 Regular Q2. */
public class Dis05RegularQ2UnionFindSolution {

    /*
     * 2a - Official solution / 官方答案
     * find returns 2, 2, 2. Final array: [2, 2, -9, 2, 0, 2, 0, 5, 4].
     * The official tree remains in ../../assets/regular-q2a-final-tree.png.
     *
     * 中文解析：按大小合并；大小相同时选较小根。根保存集合大小的负值，
     * 非根保存父节点。不使用路径压缩，因此 8 和 6 仍保留中间父节点。
     */

    /*
     * 2b - Official solution / 官方答案
     * Theta(N). A chain 0 -> 1 -> 2 -> ... -> N - 1 makes find(0) traverse
     * N - 1 links. The official diagram is regular-q2b-worst-case.png.
     *
     * 中文解析：普通 QuickUnion 不限制高度，所以最坏可形成长度为 N 的链；
     * 递归时间和调用栈空间都是 Theta(N)。
     */

    /** 2c official code / 2c 官方代码。 */
    public static class QuickUnionWithPathCompression {
        private final int[] parents;

        public QuickUnionWithPathCompression(int[] parents) {
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
                setParent(val, root); // Sets val's parent to the set root.
                return root;
            }
        }

        public int[] snapshot() {
            return Arrays.copyOf(parents, parents.length);
        }
    }

    /*
     * 2c - Supplemental analysis / 补充分析（非官方答案）
     * The Solutions PDF states amortized O(log* N) after compression. Path
     * compression alone does not automatically inherit the classic near-
     * constant bound for weighted/ranked union plus path compression; the
     * precise bound depends on the union strategy and operation sequence.
     */

    /*
     * 2d - Official solution / 官方答案
     * Final array: [2, 2, -9, 2, 2, 2, 2, 2, 2].
     * The official image remains in regular-q2d-final-tree.png.
     */

    /** Runnable support implementation; not part of the official wording. */
    private static final class WeightedQuickUnion {
        private final int[] parents;
        private final boolean pathCompression;

        private WeightedQuickUnion(int size, boolean pathCompression) {
            parents = new int[size];
            Arrays.fill(parents, -1);
            this.pathCompression = pathCompression;
        }

        private int find(int value) {
            if (parents[value] < 0) {
                return value;
            }
            int root = find(parents[value]);
            if (pathCompression) {
                parents[value] = root;
            }
            return root;
        }

        private void connect(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return;
            }
            int sizeA = -parents[rootA];
            int sizeB = -parents[rootB];
            int newRoot;
            int child;
            if (sizeA > sizeB || sizeA == sizeB && rootA < rootB) {
                newRoot = rootA;
                child = rootB;
            } else {
                newRoot = rootB;
                child = rootA;
            }
            parents[newRoot] -= -parents[child];
            parents[child] = newRoot;
        }

        private int[] snapshot() {
            return Arrays.copyOf(parents, parents.length);
        }
    }

    private static int[] run2a(WeightedQuickUnion uf) {
        uf.connect(2, 3);
        uf.connect(1, 2);
        uf.connect(5, 7);
        uf.connect(8, 4);
        uf.connect(7, 2);
        int first = uf.find(3);
        uf.connect(0, 6);
        uf.connect(6, 4);
        uf.connect(6, 3);
        int second = uf.find(8);
        int third = uf.find(6);
        return new int[]{first, second, third};
    }

    private static int[] run2d(WeightedQuickUnion uf) {
        uf.connect(2, 3);
        uf.connect(1, 2);
        uf.connect(5, 7);
        uf.connect(8, 4);
        uf.connect(7, 2);
        int first = uf.find(3);
        uf.connect(0, 6);
        uf.connect(7, 4);
        uf.connect(6, 3);
        int second = uf.find(8);
        int third = uf.find(6);
        return new int[]{first, second, third};
    }

    public static void main(String[] args) {
        WeightedQuickUnion q2a = new WeightedQuickUnion(9, false);
        requireArray(new int[]{2, 2, 2}, run2a(q2a), "2a find results");
        requireArray(new int[]{2, 2, -9, 2, 0, 2, 0, 5, 4}, q2a.snapshot(), "2a array");

        WeightedQuickUnion q2d = new WeightedQuickUnion(9, true);
        requireArray(new int[]{2, 2, 2}, run2d(q2d), "2d find results");
        requireArray(new int[]{2, 2, -9, 2, 2, 2, 2, 2, 2}, q2d.snapshot(), "2d array");

        QuickUnionWithPathCompression q2c =
                new QuickUnionWithPathCompression(new int[]{1, 2, 3, -1});
        if (q2c.find(0) != 3) {
            throw new AssertionError("2c root");
        }
        requireArray(new int[]{3, 3, 3, -1}, q2c.snapshot(), "2c compressed path");
    }

    private static void requireArray(int[] expected, int[] actual, String label) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError(label + ": expected " + Arrays.toString(expected)
                    + ", got " + Arrays.toString(actual));
        }
    }
}
