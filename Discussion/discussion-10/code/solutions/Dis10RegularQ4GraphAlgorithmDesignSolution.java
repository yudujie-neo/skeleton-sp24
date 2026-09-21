package solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/** Official solutions and executable checks for Discussion 10 Regular Q4. */
public class Dis10RegularQ4GraphAlgorithmDesignSolution {
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
            return adjacency.get(vertex);
        }
    }

    /*
     * 4a - Official solution / 官方答案
     * To solve this problem, we run a special version of a traversal from any
     * vertex. This can be implemented using either DFS and BFS as the
     * underlying traversal that we will modify. Our special version marks the
     * start vertex with a u, then each of its neighbors with a v, and each of
     * their neighbors with a u, and so forth. If at any point in the traversal
     * we want to mark a node with u but it is already marked with a v (or vice
     * versa), then the graph is not bipartite.
     *
     * If the graph is not connected, we repeat this process for each connected
     * component.
     *
     * If the algorithm completes, successfully marking every vertex in the
     * graph, then it is bipartite.
     *
     * The runtime of the algorithm is the same whether you use BFS or DFS:
     * Theta(E+V).
     *
     * 中文解释：这就是二染色。遍历边时若两端已有相同颜色，则发现奇环。
     * 每个顶点和每条边只处理常数次，时间 Theta(V+E)，空间 O(V)。
     */
    public static boolean isBipartite(Graph graph) {
        int[] color = new int[graph.vertexCount()];
        Arrays.fill(color, -1);
        Queue<Integer> fringe = new ArrayDeque<>();
        for (int start = 0; start < graph.vertexCount(); start += 1) {
            if (color[start] != -1) {
                continue;
            }
            color[start] = 0;
            fringe.add(start);
            while (!fringe.isEmpty()) {
                int vertex = fringe.remove();
                for (int neighbor : graph.neighbors(vertex)) {
                    if (color[neighbor] == -1) {
                        color[neighbor] = 1 - color[vertex];
                        fringe.add(neighbor);
                    } else if (color[neighbor] == color[vertex]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /*
     * 4b - Official solution / 官方答案
     * For the graph above, it's possible to visit in the order A-B-C-D (which
     * is not depth-first) because D won't be put into the fringe after
     * visiting B, since it's already been marked after visiting A. One should
     * only mark nodes when they have actually been visited, but in this buggy
     * implementation, we mistakenly mark them before we visit them, as we're
     * putting them into the fringe.
     *
     * 中文解释：A 的邻居可能先全部入栈并被标记；访问 B 时，边 B->D 会因
     * D 已标记而被跳过，从而破坏题目期望的递归 DFS 顺序。官方反例图保留
     * 在 ../../assets/regular-q4b-dfs-counterexample.png。
     *
     * Supplemental analysis / 补充分析（非官方答案）
     * Mark-on-push can be used in a standard iterative DFS if stack order is
     * controlled appropriately. It is not equivalent to the worksheet's
     * intended recursive DFS behavior for this implementation.
     */
    public static List<Integer> depthFirstVisitOnPop(Graph graph, int start) {
        boolean[] visited = new boolean[graph.vertexCount()];
        List<Integer> order = new ArrayList<>();
        Deque<Integer> fringe = new ArrayDeque<>();
        fringe.push(start);
        while (!fringe.isEmpty()) {
            int vertex = fringe.pop();
            if (visited[vertex]) {
                continue;
            }
            visited[vertex] = true;
            order.add(vertex);
            List<Integer> neighbors = graph.neighbors(vertex);
            for (int i = neighbors.size() - 1; i >= 0; i -= 1) {
                if (!visited[neighbors.get(i)]) {
                    fringe.push(neighbors.get(i));
                }
            }
        }
        return order;
    }

    /*
     * 4c - Official solution / 官方答案
     * The key realization here is that the shortest directed cycle involving
     * a particular source vertex s is just the shortest path to a vertex v
     * that has an edge to s, along with that edge. Using this knowledge, we
     * create a shortestCycleFromSource(s) subroutine. This subroutine runs BFS
     * on s to find the shortest path to every vertex in the graph. Afterwards,
     * it iterates through all the vertices to find the shortest cycle
     * involving s: if a vertex v has an edge back to s, the length of the
     * cycle involving s and v is one plus distTo(v) (which was computed by
     * BFS).
     *
     * An alternative approach to the above subroutine (that is slightly more
     * optimized) actually modifies BFS to short circuit if it's visiting a
     * node v and sees it has an edge v -> s. Because BFS visits in order of
     * distance from S, we can know that the first vertex v we see with an edge
     * back to s will be the shortest cycle.
     *
     * Regardless of which approach you take, asymptotically our subroutine
     * takes O(E+V) time because it uses BFS and a linear pass through the
     * vertices. To find the shortest cycle in an entire graph, we simply call
     * the subroutine on each vertex, resulting in an V * O(E+V) =
     * O(EV+V^2) runtime. Since E>V, this is still O(EV), since
     * O(EV+V^2) is in O(EV+EV), which is in O(EV).
     *
     * 中文解释：对每个源点做 BFS，并检查回到源点的入边；对所有源点取最小
     * 候选环长。父指针还可用于恢复实际环。
     */
    public static int shortestDirectedCycleLength(Graph graph) {
        int best = Integer.MAX_VALUE;
        for (int source = 0; source < graph.vertexCount(); source += 1) {
            int[] distance = bfsDistances(graph, source);
            for (int from = 0; from < graph.vertexCount(); from += 1) {
                if (distance[from] < 0) {
                    continue;
                }
                for (int to : graph.neighbors(from)) {
                    if (to == source) {
                        best = Math.min(best, distance[from] + 1);
                    }
                }
            }
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }

    private static int[] bfsDistances(Graph graph, int source) {
        int[] distance = new int[graph.vertexCount()];
        Arrays.fill(distance, -1);
        distance[source] = 0;
        Queue<Integer> fringe = new ArrayDeque<>();
        fringe.add(source);
        while (!fringe.isEmpty()) {
            int vertex = fringe.remove();
            for (int neighbor : graph.neighbors(vertex)) {
                if (distance[neighbor] == -1) {
                    distance[neighbor] = distance[vertex] + 1;
                    fringe.add(neighbor);
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        Graph evenCycle = new Graph(4);
        evenCycle.addUndirectedEdge(0, 1);
        evenCycle.addUndirectedEdge(1, 2);
        evenCycle.addUndirectedEdge(2, 3);
        evenCycle.addUndirectedEdge(3, 0);
        require(isBipartite(evenCycle));

        Graph oddCycle = new Graph(3);
        oddCycle.addUndirectedEdge(0, 1);
        oddCycle.addUndirectedEdge(1, 2);
        oddCycle.addUndirectedEdge(2, 0);
        require(!isBipartite(oddCycle));

        Graph directed = new Graph(4);
        directed.addDirectedEdge(0, 1);
        directed.addDirectedEdge(1, 2);
        directed.addDirectedEdge(2, 0);
        directed.addDirectedEdge(2, 3);
        require(shortestDirectedCycleLength(directed) == 3);
    }

    private static void require(boolean condition) {
        if (!condition) {
            throw new AssertionError("Discussion 10 Regular Q4 smoke test failed");
        }
    }
}
