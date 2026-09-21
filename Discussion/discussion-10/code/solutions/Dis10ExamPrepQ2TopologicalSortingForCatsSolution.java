package solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/** Official solutions and executable Kahn algorithm for Exam-Level Q2. */
public class Dis10ExamPrepQ2TopologicalSortingForCatsSolution {

    /*
     * 2a - Official solution / 官方答案
     * Reverse the edges of the graph, then perform a postorder traversal from
     * any unvisited vertices until you visit all of them. The runtime is linear
     * with respect to the number of vertices and edges.
     *
     * 中文解释：在反图上取 DFS 后序，等价于在原图上取逆后序，时间为
     * O(V+E)，包括构造反图和遍历。
     */

    /*
     * 2b - Part 1 - Official solution / 官方答案
     * Suppose a DAG had no sinks. Then every node has a outgoing edge. If we
     * traversed the graph, then given that there are no sinks, there is always
     * another vertex we can go to from the current one, and therefore after V
     * steps we must be repeating vertices. Therefore, the graph has a cycle,
     * and is not a DAG. Suppose a DAG had no sources. Then every node has at
     * least one edge going into it. Note that then, if we reverse the graph,
     * this graph has no sinks, but should still be a DAG. However, we proved in
     * the previous part this cannot be the case, so this too cannot be a DAG.
     *
     * 中文解释：有限图中不断沿出边前进会由鸽巢原理重复顶点；反图把源点
     * 与汇点互换。
     */

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
         * 2b - Part 2 - Official solution / 官方答案
         *
         * public int[] computeInDegrees() {
         *     int[] indegree = new int[V()];
         *     for (int i = 0; i < V(); i++) {
         *         for (int w : adj(i)) {
         *             indegree[w]++;
         *         }
         *     }
         *     return indegree;
         * }
         *
         * public List<Integer> findAllSourceNodes(int[] indegree) {
         *     List<Integer> sources = new ArrayList<>();
         *     for (int i = 0; i < V(); i++) {
         *         if (indegree[i] == 0) {
         *             sources.add(i);
         *         }
         *     }
         *     return sources;
         * }
         *
         * The idea behind computeInDegrees() is to create an array of size V,
         * where indegree[i] represents the number of incoming edges to vertex
         * i. Then, we consider each edge coming out of each vertex. Each time,
         * we increment the indegree of the destination vertex by one.
         *
         * The idea behind findAllSourceNodes() is to iterate through the
         * indegree array, and if the indegree of a vertex is 0, add it to the
         * list of sources.
         *
         * The runtime of computeInDegrees is O(V+E), and the runtime of
         * findAllSourceNodes is O(V). This is because we iterate through all
         * the vertices and edges in the graph in the first method, and only the
         * vertices in the second method.
         *
         * 中文解释：枚举邻接表中的每条出边并累计终点入度；源点就是入度为
         * 零的顶点。
         */
        public int[] computeInDegrees() {
            int[] indegree = new int[V()];
            for (int i = 0; i < V(); i += 1) {
                for (int w : adj(i)) {
                    indegree[w] += 1;
                }
            }
            return indegree;
        }

        public List<Integer> findAllSourceNodes(int[] indegree) {
            List<Integer> sources = new ArrayList<>();
            for (int i = 0; i < V(); i += 1) {
                if (indegree[i] == 0) {
                    sources.add(i);
                }
            }
            return sources;
        }

        /*
         * 2b - Part 3 - Official solution / 官方答案
         *
         * public List<Integer> topologicalSort() {
         *     List<Integer> sorted = new ArrayList<>();
         *     int[] indegree = computeInDegrees();
         *     Queue<Integer> sources =
         *         new ArrayDeque<>(findAllSourceNodes(indegree));
         *     while (!sources.isEmpty()) {
         *         int source = sources.poll();
         *         sorted.add(source);
         *         for (int w : adj(source)) {
         *             indegree[w]--;
         *             if (indegree[w] == 0) {
         *                 sources.add(w);
         *             }
         *         }
         *     }
         * }
         *
         * The algorithm is as follows. Create the indegree array described in
         * the previous part. Get all of the source nodes from it, which are
         * guaranteed to exist by part a. Add them to a queue. Then, until the
         * set is queue, remove any vertex from the queue, output it, and
         * "removes" it from the graph (we are not really removing the edge and
         * vertex here, just decrementing the array indegree): For each edge
         * that that vertex has outgoing, decrement the indegree of the
         * destination vertex by one. If that vertex now has 0 indegree in the
         * array, add it to the queue.
         *
         * The runtime of topologicalSort is O(V+E). We made one call to
         * computeInDegrees, which is O(V+E), and findAllSourceNodes, which is
         * O(V). The while loop attempts to "remove" all vertices and its
         * outgoing edges from the graph when it becomes a source, and will run
         * in O(V+E) time.
         *
         * Wow, it actually works! Nice work Duncan! However, this algorithm has
         * already been discovered and is known as Kahn's algorithm, and so we
         * cannot call it Duncan's algorithm :(
         *
         * Official PDF code ends after the while loop without returning the
         * List<Integer> named sorted.
         *
         * Supplemental analysis / 补充分析（非官方答案）
         * The question template contains "return sorted;" but the official
         * solution PDF omits it. That verbatim code does not compile because a
         * List<Integer>-returning method can reach its end. The executable
         * implementation below retains return sorted; solely as the required
         * Java correction. The official prose also says "until the set is
         * queue"; context and code mean "while the queue is not empty."
         */
        public List<Integer> topologicalSort() {
            int[] indegree = computeInDegrees();
            Queue<Integer> sources = new ArrayDeque<>(findAllSourceNodes(indegree));
            List<Integer> sorted = new ArrayList<>();
            while (!sources.isEmpty()) {
                int source = sources.poll();
                sorted.add(source);
                for (int w : adj(source)) {
                    indegree[w] -= 1;
                    if (indegree[w] == 0) {
                        sources.add(w);
                    }
                }
            }
            return sorted;
        }

        /*
         * 2b - Part 4 - Official solution / 官方答案
         * If the graph has a cycle, at some point we will not be able to find
         * any more sources, but there will still be things that we have not
         * "removed" from the graph. Therefore, we can check if there are any
         * non-zero elements in the indegree array after the while loop. If
         * there are, then the graph has a cycle.
         *
         * 中文解释：环内顶点始终保留环内入边，永远无法入队。等价检查是
         * topologicalSort() 的结果大小是否小于 V。
         */
        public boolean hasCycle() {
            return topologicalSort().size() != V();
        }
    }

    public static void main(String[] args) {
        DAG dag = new DAG(6);
        dag.addEdge(0, 1);
        dag.addEdge(1, 2);
        dag.addEdge(1, 4);
        dag.addEdge(3, 4);
        dag.addEdge(2, 5);
        dag.addEdge(3, 5);
        require(dag.computeInDegrees()[5] == 2);
        require(dag.topologicalSort().size() == 6);
        require(!dag.hasCycle());

        DAG cyclic = new DAG(3);
        cyclic.addEdge(0, 1);
        cyclic.addEdge(1, 2);
        cyclic.addEdge(2, 0);
        require(cyclic.hasCycle());
    }

    private static void require(boolean condition) {
        if (!condition) {
            throw new AssertionError("Discussion 10 Exam-Level Q2 smoke test failed");
        }
    }
}
