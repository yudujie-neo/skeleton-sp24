package questions;

import java.util.ArrayList;
import java.util.List;

/** CS 61B Spring 2024 - Discussion 08, Exam-Level Question 1. */
public class Dis08ExamPrepQ1GraphConceptuals {

    /*
     * 1a - English - Official question
     * Answer the following questions as either True or False and provide a
     * brief explanation:
     *
     * 1. If a graph with n vertices has n - 1 edges, it must be a tree.
     * 2. Every edge is looked at exactly twice in each full run of DFS on a
     *    connected, undirected graph.
     * 3. In BFS, let d(v) be the minimum number of edges between a vertex v and
     *    the start vertex. For any two vertices u, v in the fringe (recall that
     *    the fringe in BFS is a queue), |d(u) - d(v)| is always less than 2.
     *
     * 1a - 中文翻译
     * 判断下列陈述为真或假并简要解释：
     * 1. 有 n 个顶点、n - 1 条边的图必然是一棵树。
     * 2. 在连通无向图上完整运行一次 DFS 时，每条边恰好被查看两次。
     * 3. BFS 中 d(v) 表示 v 到起点的最少边数；队列 fringe 中任意两点
     *    u、v 始终满足 |d(u) - d(v)| < 2。
     *
     * My answer / 我的答案：
     * Statement 1 / 陈述 1：TODO
     * Statement 2 / 陈述 2：TODO
     * Statement 3 / 陈述 3：TODO
     */

    /*
     * 1b - English - Official question
     * Given an undirected graph, provide an algorithm that returns true if a
     * cycle exists in the graph, and false otherwise. Also, provide a Theta
     * bound for the worst case runtime of your algorithm.
     *
     * 1b - 中文翻译
     * 给定无向图，设计一个算法：存在环时返回 true，否则返回 false；并给出
     * 该算法最坏运行时间的 Theta 界。
     *
     * Constraints / 限制
     * The scaffold uses integer vertices 0 through vertexCount - 1 and an
     * undirected adjacency list. Consider disconnected graphs as well.
     * 脚手架使用 0 到 vertexCount - 1 的整数顶点和无向邻接表；还要考虑
     * 非连通图。
     *
     * Your answer / 你的答案：TODO
     * Worst-case runtime / 最坏运行时间：TODO
     */
    public static boolean hasCycle(UndirectedGraph graph) {
        // TODO: Implement cycle detection without treating the parent edge as a cycle.
        throw new UnsupportedOperationException("TODO: undirected cycle detection");
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
    public static final class UndirectedGraph {
        private final List<List<Integer>> neighbors;

        public UndirectedGraph(int vertexCount) {
            if (vertexCount < 0) {
                throw new IllegalArgumentException("vertexCount must be nonnegative");
            }
            neighbors = new ArrayList<>();
            for (int i = 0; i < vertexCount; i += 1) {
                neighbors.add(new ArrayList<>());
            }
        }

        public void addEdge(int u, int v) {
            neighbors.get(u).add(v);
            neighbors.get(v).add(u);
        }

        public int vertexCount() {
            return neighbors.size();
        }

        public List<Integer> neighbors(int vertex) {
            return List.copyOf(neighbors.get(vertex));
        }
    }
}
