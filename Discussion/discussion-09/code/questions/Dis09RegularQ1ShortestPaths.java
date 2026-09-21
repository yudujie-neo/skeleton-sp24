package questions;

/**
 * CS 61B Spring 2024 - Discussion 09, Regular Question 1.
 * The Shortest Path To Your Heart / 通往你心中的最短路径
 *
 * <p>The official directed graph remains in
 * {@code ../../assets/regular-q1-directed-graph.png}. This file represents the
 * same graph as Java data so the tracing exercise can be completed in IntelliJ.</p>
 */
public class Dis09RegularQ1ShortestPaths {

    /*
     * English - Official question preamble
     * For the graph below, let g(u, v) be the weight of the edge between any
     * nodes u and v. Let h(u, v) be the value returned by the heuristic for
     * any nodes u and v.
     *
     * Below, the pseudocode for Dijkstra's and A* are both shown for your
     * reference throughout the problem.
     *
     * 中文翻译
     * 对于讲义中的图，令 g(u, v) 表示结点 u 和 v 之间边的权重，令
     * h(u, v) 表示启发式函数对结点 u 和 v 返回的值。讲义同时给出
     * Dijkstra 与 A* 伪代码供本题参考。
     *
     * Compilation scaffold / 编译脚手架（非官方题目内容）
     * Edge 和下方常量只把官方图片、边权与启发值转成可检查的 Java 数据；
     * 它们不实现 Dijkstra 或 A*，也不包含最终答案。
     */
    public record Edge(char from, char to, int weight) {
    }

    public static final Edge[] DIRECTED_EDGES = {
        new Edge('A', 'B', 1), new Edge('A', 'D', 5),
        new Edge('B', 'C', 15), new Edge('C', 'D', 1),
        new Edge('D', 'F', 4), new Edge('F', 'E', 1),
        new Edge('E', 'C', 2)
    };

    /** h(A,F), h(B,F), h(C,F), h(D,F), h(E,F), h(F,F). */
    public static final int[] HEURISTIC_TO_F = {8, 16, 4, 4, 5, 0};

    /*
     * Dijkstra's pseudocode - Official reference
     * PQ = new PriorityQueue()
     * PQ.add(A, 0)
     * PQ.add(v, infinity) # (all nodes except A).
     *
     * distTo = {} # map
     * edgeTo = {} # map
     * distTo[A] = 0
     * distTo[v] = infinity # (all nodes except A).
     *
     * while (not PQ.isEmpty()):
     *     poppedNode, poppedPriority = PQ.pop()
     *     for child in poppedNode.children:
     *         potentialDist = distTo[poppedNode] +
     *             edgeWeight(poppedNode, child)
     *         if potentialDist < distTo[child]:
     *             distTo.put(child, potentialDist)
     *             PQ.changePriority(child, potentialDist)
     *             edgeTo[child] = poppedNode
     *
     * A* pseudocode - Official reference
     * PQ = new PriorityQueue()
     * PQ.add(A, h(A, goal))
     * PQ.add(v, infinity) # (all nodes except A).
     *
     * distTo = {} # map
     * distTo[A] = 0
     * distTo[v] = infinity # (all nodes except A).
     *
     * while (not PQ.isEmpty()):
     *     poppedNode, poppedPriority = PQ.pop()
     *     if (poppedNode == goal): terminate
     *     for child in poppedNode.children:
     *         potentialDist = distTo[poppedNode] +
     *             edgeWeight(poppedNode, child)
     *         if potentialDist < distTo[child]:
     *             distTo.put(child, potentialDist)
     *             PQ.changePriority(child, potentialDist + h(child, goal))
     *             edgeTo[child] = poppedNode
     */

    /*
     * 1a - English - Official question
     * Run Dijkstra's algorithm to find the shortest paths from A to every
     * other vertex. You may find it helpful to keep track of the priority
     * queue. We have provided a table to keep track of best distances, and the
     * adjacent vertex that has an edge going to the target vertex in the
     * current shortest paths tree so far.
     *
     * 1a - 中文翻译
     * 运行 Dijkstra 算法，求从 A 到其他所有顶点的最短路径。可记录优先
     * 队列，并填写目前最优距离及当前最短路径树中的前驱顶点。
     *
     * Your answer / 你的答案
     * Pop order / 出队顺序：TODO
     * DistTo for A..F：TODO
     * EdgeTo for A..F：TODO
     */

    /*
     * 1b - English - Official question
     * Given the weights and heuristic values for the graph above, what path
     * would A* search return, starting from A and with F as a goal?
     *
     * 1b - 中文翻译
     * 给定上图的边权与启发值，从 A 开始、以 F 为目标运行 A* 会返回哪条
     * 路径？
     *
     * Your answer / 你的答案
     * Returned path / 返回路径：TODO
     * Cost / 成本：TODO
     * DistTo and EdgeTo at termination / 终止时的表：TODO
     */

    /*
     * 1c - English - Official question
     * Based on the heuristics for part b, is the A* heuristic for this graph
     * good? In other words, will it always give us the actual shortest path
     * from A to F? If it is good, give an example of a change you would make
     * to the heuristic so that it is no longer good. If it is not, correct it.
     *
     * 1c - 中文翻译
     * 1b 的启发式是否良好，即它是否总能给出 A 到 F 的真实最短路径？若
     * 良好，举例修改一个启发值使其不再良好；否则请修正它。
     *
     * Your answer / 你的答案：TODO
     */

    private Dis09RegularQ1ShortestPaths() {
    }
}
