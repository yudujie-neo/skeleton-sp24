package questions;

import java.util.ArrayList;
import java.util.List;

/** CS 61B Spring 2024 - Discussion 10, Exam-Level Question 2. */
public class Dis10ExamPrepQ2TopologicalSortingForCats {

    /*
     * English - Official preamble
     * The big brain cat, Duncan, is currently studying topological sorts! He
     * has a variety of curiosities that he wishes to satisfy.
     *
     * 中文翻译
     * 聪明猫 Duncan 正在研究拓扑排序，并有一系列问题想弄明白。
     */

    /*
     * 2a - English - Official question
     * Describe at a high level in plain English how to perform a topological
     * sort using an algorithm we already know (hint: it involves DFS), and
     * provide the time complexity.
     *
     * 2a - 中文翻译
     * 用自然语言概述如何利用 DFS 完成拓扑排序，并给出时间复杂度。
     *
     * Your answer / 你的答案：TODO
     */

    /*
     * 2b - English - Official question preamble
     * Duncan came up with another way to possibly do topological sorts, and he
     * wants you to check him on its correctness and tell him if it is more
     * efficient than our current way! Let's derive the algorithm.
     *
     * 2b - Part 1 - English - Official question
     * First, provide a logical reasoning for the following claim (or a
     * proof!): Every DAG has at least one source node, and at least one sink
     * node.
     *
     * 2b - 第 1 小问 - 中文翻译
     * 论证每个 DAG 至少有一个源点和一个汇点。
     *
     * Your answer / 你的答案：TODO
     */

    /*
     * Official Graph API reference / 官方 Graph API 参考
     *
     * public class Graph {
     *     public Graph(int V) // Create empty graph with v vertices,
     *                         // numbered 0 to V - 1
     *     public void addEdge(int v, int w) // Adds edge from v to w
     *     Iterable<Integer> adj(int v) // Gets vertices adjacent to v
     *     int V() // Number of vertices
     *     int E() // Number of edges
     * }
     */

    /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
    public static class Graph {
        private final List<List<Integer>> adjacency;
        private int edgeCount;

        public Graph(int vertexCount) {
            adjacency = new ArrayList<>();
            for (int v = 0; v < vertexCount; v += 1) {
                adjacency.add(new ArrayList<>());
            }
        }

        public void addEdge(int from, int to) {
            adjacency.get(from).add(to);
            edgeCount += 1;
        }

        public Iterable<Integer> adj(int vertex) {
            return adjacency.get(vertex);
        }

        public int V() {
            return adjacency.size();
        }

        public int E() {
            return edgeCount;
        }
    }

    public static class DAG extends Graph {
        public DAG(int vertexCount) {
            super(vertexCount);
        }

        /*
         * 2b - Part 2 - English - Official question
         * Duncan wishes to extend from the Graph class to create a DAG class.
         * He wants to eventually add a method that enables topological
         * sorting, but needs to write some helper methods first! Complete the
         * following instance methods computeInDegrees and
         * findAllSourceNodes().
         *
         * 2b - 第 2 小问 - 中文翻译
         * 补全计算每个顶点入度的方法和收集全部零入度源点的方法，并给出
         * 各自运行时间。
         *
         * Your answer / 你的答案：TODO
         *
         * Official method template / 官方方法模板
         * public int[] computeInDegrees() {
         *     int[] indegree = ______________________;
         *     for (_________________________________) {
         *         for (__________________________) {
         *             ______________________________
         *         }
         *     }
         *     return indegree;
         * }
         *
         * public List<Integer> findAllSourceNodes(int[] indegree) {
         *     List<Integer> sources = new ArrayList<>();
         *     for (_________________________________) {
         *         if (__________________________) {
         *             __________________________
         *         }
         *     }
         *     return ___________________;
         * }
         * Runtime of computeInDegrees is: TODO
         * Runtime of findAllSourceNodes is: TODO
         */
        public int[] computeInDegrees() {
            throw new UnsupportedOperationException("TODO: computeInDegrees");
        }

        public List<Integer> findAllSourceNodes(int[] indegree) {
            throw new UnsupportedOperationException("TODO: findAllSourceNodes");
        }

        /*
         * 2b - Part 3 - English - Official question
         * Now, make the following observation: If we remove all of the source
         * nodes from a DAG, we are guaranteed to have at least one new source
         * node. Inspired by this fact, and using the previous parts, complete
         * the topologicalSort() method. What is its runtime?
         * Hint: initialize a queue from another iterable.
         *
         * 2b - 第 3 小问 - 中文翻译
         * 利用“移除全部源点后会出现新源点”的观察和前面的方法，补全
         * topologicalSort() 并给出运行时间。
         *
         * Your answer / 你的答案：TODO
         *
         * Official method template / 官方方法模板
         * public List<Integer> topologicalSort() {
         *     List<Integer> sorted = new ArrayList<>();
         *     ____________________________________________
         *     // Hint: add elements from another iterable here
         *     Queue<Integer> sources =
         *         new ArrayDeque<>(_______________________);
         *     while (________________________________) {
         *         int source = sources.poll();
         *         ______________________________________
         *         for (______________________________________) {
         *             ______________________________________
         *             if (________________________) {
         *                 ________________________
         *             }
         *         }
         *     }
         *     return sorted;
         * }
         * Runtime of topologicalSort is: TODO
         */
        public List<Integer> topologicalSort() {
            throw new UnsupportedOperationException("TODO: topologicalSort");
        }

        /*
         * 2b - Part 4 - English - Official question
         * Venti, the bard from Mondstadt is allergic to cats. He wanted to
         * trick Duncan and created a DAG object, but it actually represents a
         * graph with a cycle! How can you modify the method topologicalSort()
         * above to detect whether the graph has a cycle?
         *
         * 2b - 第 4 小问 - 中文翻译
         * 若 DAG 对象实际含环，应如何修改 topologicalSort() 检测它？
         *
         * Your answer / 你的答案：TODO
         */
    }

    private Dis10ExamPrepQ2TopologicalSortingForCats() {
    }
}
