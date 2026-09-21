package solutions;

/** Official solutions and executable counterexample checks for Regular Q2. */
public class Dis10RegularQ2ATreeTakesOnGraphsSolution {

    public record WeightedEdge(char from, char to, int weight) {
    }

    /*
     * 2a - Official solution / 官方答案
     * False: Consider a tree (N nodes and N - 1 edges, no cycles, connected) -
     * there is only one way to connect a tree, so it will be its own MST (so
     * there is only one MST for a tree).
     *
     * 中文解释：相同边权只表示所有生成树的总权重相同。若底层图本身就是
     * 一棵树，它只有自身这一棵生成树，因此 MST 唯一。
     */

    /*
     * 2b - Official solution / 官方答案
     * False: Here, A* would incorrectly return A-B-D as the shortest path from
     * A to D. Starting at A, we would add B to the queue with priority 1+1
     * (the known distance to B, as well as our estimated distance from B to
     * the goal), and we would add C to the queue with priority 1+100 (the
     * known distance to C, as well as our estimated distance from C to the
     * goal). We then pop B off the queue, and add D to the queue with priority
     * 11+0 (the known distance to D, as well as the estimated distance from D
     * to the goal). Our queue now contains C with priority 101, and D with
     * priority 11, so we pop D off the queue and complete our search,
     * returning A-B-D as the shortest path instead of the correct answer:
     * A-C-D.
     *
     * In general, A* is only guaranteed to be correct if the heuristic is good
     * - specifically, it should be both admissible and consistent (note that
     * applying admissibility and consistency are out of scope for this class,
     * you only need to know the definition). In the example given, our
     * heuristic is neither admissible nor consistent.
     *
     * 中文解释：真实最短路 A-C-D 的成本为 2，但 h(C)=100 使 C 的优先级
     * 为 101；D 经由 B 以优先级 11 先出队，算法过早返回成本 11 的路径。
     * 官方图位于 ../../assets/regular-q2b-a-star-counterexample.png。
     */
    private static final WeightedEdge[] A_STAR_WRONG_PATH = {
        new WeightedEdge('A', 'B', 1), new WeightedEdge('B', 'D', 10)
    };
    private static final WeightedEdge[] A_STAR_SHORTEST_PATH = {
        new WeightedEdge('A', 'C', 1), new WeightedEdge('C', 'D', 1)
    };

    /*
     * 2c - Official solution / 官方答案
     * False: this can be disproved with the example below, where we add a
     * constant c=2 to every edge. Adding a constant factor per edge will
     * disadvantage paths with more edges. In our example, though A-B-C had
     * more edges, in our original graph it still has shorter total path cost,
     * at 1+1=2. On the other hand, A-C had fewer edges but larger total path
     * cost, 3. After adding a constant factor, however, the A-B-C path cost
     * was (1+2)+(1+2)=6 and the A-C had a path cost of (2+3)=5. So before the
     * addition, Dijkstra's shortest path tree would have said the shortest
     * path from A to C was A-B-C, but afterwards it would say A-C.
     *
     * 中文解释：含 m 条边的路径会额外增加 m*c；不同路径边数不同，因此
     * 统一加法可能改变相对次序。
     *
     * Supplemental analysis / 补充分析（非官方答案）
     * The worksheet says "constant factor," but both the operation and the
     * official example add a constant rather than multiply by one.
     * 题目虽写 constant factor，实际操作和官方示例都是加常数而非乘法。
     */
    private static final WeightedEdge[] ORIGINAL_TWO_EDGE_PATH = {
        new WeightedEdge('A', 'B', 1), new WeightedEdge('B', 'C', 1)
    };
    private static final WeightedEdge[] ORIGINAL_DIRECT_PATH = {
        new WeightedEdge('A', 'C', 3)
    };

    public static int pathCost(WeightedEdge[] path, int amountAddedPerEdge) {
        int total = 0;
        for (WeightedEdge edge : path) {
            total += edge.weight() + amountAddedPerEdge;
        }
        return total;
    }

    public static void main(String[] args) {
        require(pathCost(A_STAR_WRONG_PATH, 0) == 11);
        require(pathCost(A_STAR_SHORTEST_PATH, 0) == 2);
        require(pathCost(ORIGINAL_TWO_EDGE_PATH, 0) < pathCost(ORIGINAL_DIRECT_PATH, 0));
        require(pathCost(ORIGINAL_TWO_EDGE_PATH, 2) > pathCost(ORIGINAL_DIRECT_PATH, 2));
    }

    private static void require(boolean condition) {
        if (!condition) {
            throw new AssertionError("Discussion 10 Regular Q2 smoke test failed");
        }
    }
}
