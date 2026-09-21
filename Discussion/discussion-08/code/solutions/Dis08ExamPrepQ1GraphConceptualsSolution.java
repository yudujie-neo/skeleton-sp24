package solutions;

import java.util.ArrayList;
import java.util.List;

/** Official solutions and executable checks for Discussion 08 Exam-Level Q1. */
public class Dis08ExamPrepQ1GraphConceptualsSolution {

    /*
     * 1a - Official solution / 官方答案
     * 1. False. The graph must be connected.
     * 2. True. Say an edge connects u and v. Both u and v will look at the
     *    other one through this edge when it's their turn.
     * 3. True. Suppose this was not the case. Then, we could have a vertex 2
     *    edges away and a vertex 4 edges away in the fringe at the same time.
     *    But, the only way to have a vertex 4 edges away is if a vertex 3 edges
     *    away was removed from the fringe. We see this could never occur
     *    because the vertex 2 edges away would be removed before the vertex 3
     *    edges away!
     *
     * 中文解释：边数 n-1 还不足以保证连通；无向边在两个端点的邻接表中
     * 各出现一次；BFS 队列只会同时保存当前层和下一层的结点。
     */

    /*
     * 1b - Official solution / 官方答案
     * We do a depth first search traversal through the graph. While we recurse,
     * if we visit a node that we visited already, then we've found a cycle.
     * Assuming integer labels, we can use something like a visited boolean array
     * to keep track of the elements that we've seen, and while looking through
     * a node's neighbors, if visited gives true, then that indicates a cycle.
     *
     * However, since the graph is undirected, if an edge connects vertices u
     * and v, then u is a neighbor of v, and v is a neighbor of u. As such, if we
     * visit v after u, our algorithm will claim that there is a cycle since u is
     * a visited neighbor of v. To address this case, when we visit the neighbors
     * of v, we should ignore u. To implement this in code, we could add the
     * parent as another parameter in the method call.
     *
     * In the worst case, we have to explore at most V edges before finding a
     * cycle (number of edges doesn't matter). So, this runs in Theta(V).
     *
     * Pseudocode is provided below (for a disconnected graph, we should call
     * find_cycle on each component).
     *
     * find_cycle(v, parent=-1):
     *     visited[v] = true
     *     for (v, w) in G:
     *         if !visited[w]:
     *             if find_cycle(w, v):
     *                 return True
     *         else if w != parent:
     *             return True
     *     return False
     *
     * Supplemental analysis / 补充分析（非官方答案）：
     * 对一般邻接表图，完整 DFS 的最坏时间是 Theta(V + E)，不是官方所写
     * 的 Theta(V)。在连通无向图中因 E >= V - 1，也可简写为 Theta(E)。
     * 官方复杂度表述已原样保留在上方，没有静默修改。
     *
     * 此处实现与官方伪代码一样假设简单图。若允许平行边，只用 w != parent
     * 会把所有指向父结点的边都跳过，从而漏掉两条平行边组成的环。
     */
    public static boolean hasCycle(UndirectedGraph graph) {
        boolean[] visited = new boolean[graph.vertexCount()];
        for (int v = 0; v < graph.vertexCount(); v += 1) {
            if (!visited[v] && findCycle(graph, v, -1, visited)) {
                return true;
            }
        }
        return false;
    }

    private static boolean findCycle(UndirectedGraph graph, int vertex, int parent,
                                     boolean[] visited) {
        visited[vertex] = true;
        for (int neighbor : graph.neighbors(vertex)) {
            if (!visited[neighbor]) {
                if (findCycle(graph, neighbor, vertex, visited)) {
                    return true;
                }
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }

    /** Independent compilation scaffold matching the question representation. */
    public static final class UndirectedGraph {
        private final List<List<Integer>> neighbors;

        public UndirectedGraph(int vertexCount) {
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
            return neighbors.get(vertex);
        }
    }

    public static void main(String[] args) {
        UndirectedGraph treeAndIsolatedVertex = new UndirectedGraph(5);
        treeAndIsolatedVertex.addEdge(0, 1);
        treeAndIsolatedVertex.addEdge(1, 2);
        treeAndIsolatedVertex.addEdge(1, 3);
        if (hasCycle(treeAndIsolatedVertex)) {
            throw new AssertionError("A forest should not contain a cycle");
        }

        UndirectedGraph cycle = new UndirectedGraph(4);
        cycle.addEdge(0, 1);
        cycle.addEdge(1, 2);
        cycle.addEdge(2, 0);
        if (!hasCycle(cycle)) {
            throw new AssertionError("Triangle should contain a cycle");
        }
    }
}
