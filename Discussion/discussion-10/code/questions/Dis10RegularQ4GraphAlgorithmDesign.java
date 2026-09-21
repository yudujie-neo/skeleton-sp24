package questions;

import java.util.ArrayList;
import java.util.List;

/** CS 61B Spring 2024 - Discussion 10, Regular Question 4. */
public class Dis10RegularQ4GraphAlgorithmDesign {

    /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
    public static final class Graph {
        private final List<List<Integer>> adjacency;

        public Graph(int vertexCount) {
            adjacency = new ArrayList<>();
            for (int v = 0; v < vertexCount; v += 1) {
                adjacency.add(new ArrayList<>());
            }
        }

        public void addDirectedEdge(int from, int to) {
            adjacency.get(from).add(to);
        }

        public void addUndirectedEdge(int a, int b) {
            addDirectedEdge(a, b);
            addDirectedEdge(b, a);
        }

        public int vertexCount() {
            return adjacency.size();
        }

        public List<Integer> neighbors(int vertex) {
            return List.copyOf(adjacency.get(vertex));
        }
    }

    /*
     * 4a - English - Official question
     * An undirected graph is said to be bipartite if all of its vertices can
     * be divided into two disjoint sets U and V such that every edge connects
     * an item in U to an item in V. For example below, the graph on the left
     * is bipartite, whereas on the graph on the right is not. Provide an
     * algorithm which determines whether or not a graph is bipartite. What is
     * the runtime of your algorithm?
     * Hint: Can you modify an algorithm we already know (ie. graph traversal)?
     *
     * 4a - 中文翻译
     * 若无向图顶点可分为两个不相交集合，且每条边跨越两个集合，则图为
     * 二分图。设计判断算法并给出运行时间。提示：修改已知图遍历。
     * 官方示例图保留在 ../../assets/regular-q4a-bipartite-examples.png。
     *
     * Your answer / 你的答案：TODO
     */
    public static boolean isBipartite(Graph graph) {
        // TODO: Implement the traversal and coloring rule.
        throw new UnsupportedOperationException("TODO: isBipartite");
    }

    /*
     * 4b - English - Official question
     * Consider this DFS implementation, which contains a crucial error:
     *
     * create the fringe, which is an empty Stack
     * push the start vertex onto the fringe and mark it
     * while the fringe is not empty:
     *     pop a vertex off the fringe and visit it
     *     for each neighbor of the vertex:
     *         if neighbor not marked:
     *             push neighbor onto the fringe
     *             mark neighbor
     *
     * First, identify the bug in this implementation. Then, give an example of
     * a graph where this algorithm may not traverse in DFS order.
     * Hint: When should we be marking vertices?
     *
     * 4b - 中文翻译
     * 找出上述迭代 DFS 的关键错误，并给出它可能不按 DFS 顺序遍历的图。
     * 提示：顶点应在什么时候标记？官方反例保留在
     * ../../assets/regular-q4b-dfs-counterexample.png。
     *
     * Your answer / 你的答案：TODO
     */

    /*
     * 4c - English - Official question
     * Extra: Provide an algorithm that finds the shortest cycle (in terms of
     * the number of edges used) in a directed graph in O(EV) time and O(E)
     * space, assuming E > V.
     *
     * 4c - 中文翻译
     * 额外题：假设 E > V，设计 O(EV) 时间、O(E) 空间算法，寻找有向图中
     * 按边数计的最短环。
     *
     * Your answer / 你的答案：TODO
     */
    public static int shortestDirectedCycleLength(Graph graph) {
        // TODO: Return -1 if there is no directed cycle.
        throw new UnsupportedOperationException("TODO: shortest directed cycle");
    }

    private Dis10RegularQ4GraphAlgorithmDesign() {
    }
}
