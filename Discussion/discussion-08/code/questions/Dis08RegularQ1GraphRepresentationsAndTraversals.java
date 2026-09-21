package questions;

import java.util.List;

/** CS 61B Spring 2024 - Discussion 08, Regular Question 1. */
public class Dis08RegularQ1GraphRepresentationsAndTraversals {

    /*
     * 1a - English - Official question
     * Write the following traversals of the BST below.
     *
     * Pre-order:
     * In-order:
     * Post-order:
     * Level-order (BFS):
     *
     * 1a - 中文翻译
     * 写出题图中二叉搜索树的前序、中序、后序和层序（BFS）遍历。
     * The official tree image remains in ../../assets/question-01a-bst.png.
     * 官方树形图继续保留在上述 assets 路径中。
     *
     * Your answer / 你的答案：
     * Pre-order / 前序：TODO
     * In-order / 中序：TODO
     * Post-order / 后序：TODO
     * Level-order / 层序：TODO
     */

    /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
    public static final class TreeNode {
        public final int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    /**
     * Compilation scaffold / 编译脚手架（非官方题目内容）。
     * Builds the exact BST shown in the official 1a image so the four
     * traversals can be implemented and run directly in IntelliJ.
     */
    public static TreeNode officialTree() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(12);
        root.right.left = new TreeNode(11);
        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);
        root.right.right.right = new TreeNode(15);
        return root;
    }

    public static List<Integer> preOrder(TreeNode root) {
        // TODO: Return the root-left-right traversal for 1a.
        throw new UnsupportedOperationException("TODO: pre-order traversal");
    }

    public static List<Integer> inOrder(TreeNode root) {
        // TODO: Return the left-root-right traversal for 1a.
        throw new UnsupportedOperationException("TODO: in-order traversal");
    }

    public static List<Integer> postOrder(TreeNode root) {
        // TODO: Return the left-right-root traversal for 1a.
        throw new UnsupportedOperationException("TODO: post-order traversal");
    }

    public static List<Integer> levelOrder(TreeNode root) {
        // TODO: Return the breadth-first traversal for 1a.
        throw new UnsupportedOperationException("TODO: level-order traversal");
    }

    /*
     * 1b - English - Official question
     * Write the graph below as an adjacency matrix, then as an adjacency list.
     * What would be different if the graph were undirected instead?
     *
     * 1b - 中文翻译
     * 先把题图写成邻接矩阵，再写成邻接表。如果图改为无向图，表示会有
     * 什么不同？官方图保留在 ../../assets/question-01b-directed-graph.png。
     *
     * Constraints / 限制
     * Vertices are A through G. Matrix rows are start nodes and columns are end
     * nodes. Preserve alphabetical vertex order.
     * 顶点为 A 到 G；矩阵的行是起点、列是终点，并按字母顺序排列。
     *
     * Your answer / 你的答案：TODO
     */
    public static final char[] VERTICES = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

    /**
     * Compilation scaffold / 编译脚手架（非官方题目内容）。
     * Transcription of the arrows shown in the official question image; this is
     * input data, not the requested matrix/list answer.
     */
    public static final char[][] DIRECTED_EDGES = {
        {'A', 'B'}, {'A', 'D'}, {'B', 'C'}, {'C', 'F'}, {'D', 'B'},
        {'D', 'E'}, {'D', 'F'}, {'E', 'F'}, {'G', 'F'}
    };

    public static int[][] adjacencyMatrix() {
        // TODO: Build the directed adjacency matrix for 1b.
        throw new UnsupportedOperationException("TODO: adjacency matrix");
    }

    public static List<List<Character>> adjacencyList() {
        // TODO: Build the directed adjacency list for 1b.
        throw new UnsupportedOperationException("TODO: adjacency list");
    }

    /*
     * 1c - English - Official question
     * Write the order in which (1) DFS pre-order, (2) DFS post-order, and
     * (3) BFS would visit nodes in the same directed graph above, starting from
     * vertex A. Break ties alphabetically.
     *
     * 1c - 中文翻译
     * 对 1b 的同一幅有向图，从 A 开始写出 DFS 前序、DFS 后序和 BFS
     * 的访问顺序；有多个选择时按字母顺序打破平局。
     *
     * Your answer / 你的答案：
     * DFS pre-order / DFS 前序：TODO
     * DFS post-order / DFS 后序：TODO
     * BFS：TODO
     * Does the traversal restart at G? / 是否在 G 处重新开始：TODO
     */

    public static List<Character> dfsPreOrder(boolean restart) {
        // TODO: Return the traversal order; restart controls disconnected restart.
        throw new UnsupportedOperationException("TODO: DFS pre-order");
    }

    public static List<Character> dfsPostOrder(boolean restart) {
        // TODO: Return the traversal order; restart controls disconnected restart.
        throw new UnsupportedOperationException("TODO: DFS post-order");
    }

    public static List<Character> bfs(boolean restart) {
        // TODO: Return the traversal order; restart controls disconnected restart.
        throw new UnsupportedOperationException("TODO: BFS");
    }
}
