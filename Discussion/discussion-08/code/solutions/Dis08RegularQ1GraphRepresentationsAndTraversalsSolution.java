package solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/** Official solutions and executable checks for Discussion 08 Regular Q1. */
public class Dis08RegularQ1GraphRepresentationsAndTraversalsSolution {
    private static final int N = 7;
    private static final int[][] DIRECTED_EDGES = {
        {0, 1}, {0, 3}, {1, 2}, {2, 5}, {3, 1},
        {3, 4}, {3, 5}, {4, 5}, {6, 5}
    };

    /*
     * 1a - Official solution / 官方答案
     * Pre-order: 10 3 1 7 12 11 14 13 15
     * In-order: 1 3 7 10 11 12 13 14 15
     * Post-order: 1 7 3 11 13 15 14 12 10
     * Level-order (BFS): 10 3 12 1 7 11 14 13 15
     *
     * 中文解释：前序按根-左-右，中序按左-根-右，后序按左-右-根；
     * 层序遍历用队列逐层访问。官方树图仍保留在 Markdown/assets 中。
     */
    public static final int[] PRE_ORDER = {10, 3, 1, 7, 12, 11, 14, 13, 15};
    public static final int[] IN_ORDER = {1, 3, 7, 10, 11, 12, 13, 14, 15};
    public static final int[] POST_ORDER = {1, 7, 3, 11, 13, 15, 14, 12, 10};
    public static final int[] LEVEL_ORDER = {10, 3, 12, 1, 7, 11, 14, 13, 15};

    public static final class TreeNode {
        public final int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

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
        List<Integer> order = new ArrayList<>();
        collectPreOrder(root, order);
        return order;
    }

    private static void collectPreOrder(TreeNode root, List<Integer> order) {
        if (root == null) {
            return;
        }
        order.add(root.value);
        collectPreOrder(root.left, order);
        collectPreOrder(root.right, order);
    }

    public static List<Integer> inOrder(TreeNode root) {
        List<Integer> order = new ArrayList<>();
        collectInOrder(root, order);
        return order;
    }

    private static void collectInOrder(TreeNode root, List<Integer> order) {
        if (root == null) {
            return;
        }
        collectInOrder(root.left, order);
        order.add(root.value);
        collectInOrder(root.right, order);
    }

    public static List<Integer> postOrder(TreeNode root) {
        List<Integer> order = new ArrayList<>();
        collectPostOrder(root, order);
        return order;
    }

    private static void collectPostOrder(TreeNode root, List<Integer> order) {
        if (root == null) {
            return;
        }
        collectPostOrder(root.left, order);
        collectPostOrder(root.right, order);
        order.add(root.value);
    }

    public static List<Integer> levelOrder(TreeNode root) {
        List<Integer> order = new ArrayList<>();
        if (root == null) {
            return order;
        }
        Queue<TreeNode> fringe = new ArrayDeque<>();
        fringe.add(root);
        while (!fringe.isEmpty()) {
            TreeNode node = fringe.remove();
            order.add(node.value);
            if (node.left != null) {
                fringe.add(node.left);
            }
            if (node.right != null) {
                fringe.add(node.right);
            }
        }
        return order;
    }

    /*
     * 1b - Official solution / 官方答案
     * For the directed graph, rows are start nodes and columns are end nodes.
     * Matrix:
     *     A B C D E F G
     * A   0 1 0 1 0 0 0       A: {B, D}
     * B   0 0 1 0 0 0 0       B: {C}
     * C   0 0 0 0 0 1 0       C: {F}
     * D   0 1 0 0 1 1 0       D: {B, E, F}
     * E   0 0 0 0 0 1 0       E: {F}
     * F   0 0 0 0 0 0 0       F: {}
     * G   0 0 0 0 0 1 0       G: {F}
     *
     * For the undirected version of the graph, the representations look a bit
     * more symmetric. For your reference, the representations are included
     * below:
     * Matrix:
     *     A B C D E F G
     * A   0 1 0 1 0 0 0
     * B   1 0 1 1 0 0 0
     * C   0 1 0 0 0 1 0
     * D   1 1 0 0 1 1 0
     * E   0 0 0 1 0 1 0
     * F   0 0 1 1 1 0 1
     * G   0 0 0 0 0 1 0
     * List:
     * A: {B, D}; B: {A, C, D}; C: {B, F}; D: {A, B, E, F};
     * E: {D, F}; F: {C, D, E, G}; G: {F}.
     *
     * 中文解释：无向图要把每条 u-v 边同时记入 u 和 v 的邻接表，因此
     * 矩阵关于主对角线对称。
     */
    public static int[][] adjacencyMatrix(boolean undirected) {
        int[][] matrix = new int[N][N];
        for (int[] edge : DIRECTED_EDGES) {
            matrix[edge[0]][edge[1]] = 1;
            if (undirected) {
                matrix[edge[1]][edge[0]] = 1;
            }
        }
        return matrix;
    }

    public static List<List<Integer>> adjacencyList(boolean undirected) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i += 1) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : DIRECTED_EDGES) {
            graph.get(edge[0]).add(edge[1]);
            if (undirected) {
                graph.get(edge[1]).add(edge[0]);
            }
        }
        for (List<Integer> neighbors : graph) {
            neighbors.sort(Integer::compareTo);
        }
        return graph;
    }

    /*
     * 1c - Official solution / 官方答案
     * Pre-order: ABCFDE (G)
     * Post-order: FCBEDA (G)
     * BFS: ABDCEF (G)
     *
     * Official explanation:
     * To compute DFS, we maintain a stack of nodes, and a visited set. As soon
     * as we add something to our stack, we note it down for preorder. The top
     * node in our stack represents the node we are currently on, and the marked
     * set represents nodes that have been visited. After we add a node to the
     * stack, we visit its lexicographically next unmarked child. If there is
     * none, we pop the topmost node from the stack and note it down for
     * postorder. Note that there are two ways DFS could run: with restart or
     * without; DFS with restart is the version where if we have exhausted our
     * stack, and still have unmarked nodes left, we restart on the next unmarked
     * node.
     *
     * Stack (bottom-top) | Visited Set | Preorder | Postorder
     * A                  | A           | A        | -
     * AB                 | AB          | AB       | -
     * ABC                | ABC         | ABC      | -
     * ABCF               | ABCF        | ABCF     | -
     * ABC                | ABCF        | ABCF     | F
     * AB                 | ABCF        | ABCF     | FC
     * A                  | ABCF        | ABCF     | FCB
     * AD                 | ABCFD       | ABCFD    | FCB
     * ADE                | ABCFDE      | ABCFDE   | FCB
     * AD                 | ABCFDE      | ABCFDE   | FCBE
     * A                  | ABCFDE      | ABCFDE   | FCBED
     * -                  | ABCFDE      | ABCFDE   | FCBEDA
     *
     * If DFS restarts on unmarked nodes, the following happens in the last
     * line. Otherwise, we do not proceed further.
     *
     * Stack (bottom-top) | Visited Set | Preorder | Postorder
     * G                  | ABCFDEG     | ABCFDEG  | FCBEDAG
     *
     * For BFS, we use a queue instead of a stack. BFS does not have the notion
     * of in-order and post-order, so we only visit it when we remove it from the
     * queue.
     *
     * 中文解释：从 A 沿有向边只能到达 A-F。括号中的 G 仅在遍历实现会
     * 对未访问顶点重新启动时出现。
     */
    public static List<Character> dfsPreOrder(boolean restart) {
        List<List<Integer>> graph = adjacencyList(false);
        boolean[] visited = new boolean[N];
        List<Character> order = new ArrayList<>();
        dfsPre(0, graph, visited, order);
        if (restart) {
            restartDfsPre(graph, visited, order);
        }
        return order;
    }

    private static void restartDfsPre(List<List<Integer>> graph, boolean[] visited,
                                      List<Character> order) {
        for (int v = 0; v < N; v += 1) {
            if (!visited[v]) {
                dfsPre(v, graph, visited, order);
            }
        }
    }

    private static void dfsPre(int v, List<List<Integer>> graph, boolean[] visited,
                               List<Character> order) {
        visited[v] = true;
        order.add((char) ('A' + v));
        for (int w : graph.get(v)) {
            if (!visited[w]) {
                dfsPre(w, graph, visited, order);
            }
        }
    }

    public static List<Character> dfsPostOrder(boolean restart) {
        List<List<Integer>> graph = adjacencyList(false);
        boolean[] visited = new boolean[N];
        List<Character> order = new ArrayList<>();
        dfsPost(0, graph, visited, order);
        if (restart) {
            for (int v = 0; v < N; v += 1) {
                if (!visited[v]) {
                    dfsPost(v, graph, visited, order);
                }
            }
        }
        return order;
    }

    private static void dfsPost(int v, List<List<Integer>> graph, boolean[] visited,
                                List<Character> order) {
        visited[v] = true;
        for (int w : graph.get(v)) {
            if (!visited[w]) {
                dfsPost(w, graph, visited, order);
            }
        }
        order.add((char) ('A' + v));
    }

    public static List<Character> bfs(boolean restart) {
        List<List<Integer>> graph = adjacencyList(false);
        boolean[] visited = new boolean[N];
        List<Character> order = new ArrayList<>();
        bfsFrom(0, graph, visited, order);
        if (restart) {
            for (int v = 0; v < N; v += 1) {
                if (!visited[v]) {
                    bfsFrom(v, graph, visited, order);
                }
            }
        }
        return order;
    }

    private static void bfsFrom(int start, List<List<Integer>> graph,
                                boolean[] visited, List<Character> order) {
        Queue<Integer> fringe = new ArrayDeque<>();
        visited[start] = true;
        fringe.add(start);
        while (!fringe.isEmpty()) {
            int v = fringe.remove();
            order.add((char) ('A' + v));
            for (int w : graph.get(v)) {
                if (!visited[w]) {
                    visited[w] = true;
                    fringe.add(w);
                }
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = officialTree();
        require(preOrder(root).equals(Arrays.stream(PRE_ORDER).boxed().toList()));
        require(inOrder(root).equals(Arrays.stream(IN_ORDER).boxed().toList()));
        require(postOrder(root).equals(Arrays.stream(POST_ORDER).boxed().toList()));
        require(levelOrder(root).equals(Arrays.stream(LEVEL_ORDER).boxed().toList()));
        int[][] directedMatrix = {
            {0, 1, 0, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0}
        };
        int[][] undirectedMatrix = {
            {0, 1, 0, 1, 0, 0, 0},
            {1, 0, 1, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0},
            {1, 1, 0, 0, 1, 1, 0},
            {0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 1, 1, 0, 1},
            {0, 0, 0, 0, 0, 1, 0}
        };
        require(Arrays.deepEquals(adjacencyMatrix(false), directedMatrix));
        require(Arrays.deepEquals(adjacencyMatrix(true), undirectedMatrix));
        require(adjacencyList(false).equals(List.of(
            List.of(1, 3), List.of(2), List.of(5), List.of(1, 4, 5),
            List.of(5), List.of(), List.of(5)
        )));
        require(adjacencyList(true).equals(List.of(
            List.of(1, 3), List.of(0, 2, 3), List.of(1, 5),
            List.of(0, 1, 4, 5), List.of(3, 5), List.of(2, 3, 4, 6),
            List.of(5)
        )));
        require(dfsPreOrder(false).equals(List.of('A', 'B', 'C', 'F', 'D', 'E')));
        require(dfsPreOrder(true).equals(List.of('A', 'B', 'C', 'F', 'D', 'E', 'G')));
        require(dfsPostOrder(false).equals(List.of('F', 'C', 'B', 'E', 'D', 'A')));
        require(dfsPostOrder(true).equals(List.of('F', 'C', 'B', 'E', 'D', 'A', 'G')));
        require(bfs(false).equals(List.of('A', 'B', 'D', 'C', 'E', 'F')));
        require(bfs(true).equals(List.of('A', 'B', 'D', 'C', 'E', 'F', 'G')));
    }

    private static void require(boolean condition) {
        if (!condition) {
            throw new AssertionError("Discussion 08 Regular Q1 smoke test failed");
        }
    }
}
