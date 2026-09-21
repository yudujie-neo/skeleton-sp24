package solutions;

import java.util.Arrays;

/** Official solutions for Discussion 09 Regular Question 1. */
public class Dis09RegularQ1ShortestPathsSolution {

    /*
     * 1a - Official solution / 官方答案
     * B = 1 ; D = 5 ; F = 9 ; E = 10 ; C = 12
     *
     * For the best explanation, it is recommended to check the slideshow
     * linked on the website or watch the walkthrough video, as the text
     * explanation is verbose.
     *
     * We will maintain a priority queue and a table of distances found so far,
     * as suggested in the problem and pseudocode. We will use {} to represent
     * the PQ, and (()) to represent the distTo array.
     *
     * {A:0, B:inf, C:inf, D:inf, E:inf, F:inf}. (()).
     *
     * Pop A.
     * {B:inf, C:inf, D:inf, E:inf, F:inf}. ((A: 0)).
     * changePriority(B, 1). changePriority(D, 5).
     * {B:1, D:5, C:inf, E:inf, F:inf}. ((A: 0)).
     *
     * Pop B.
     * {D:5, C:inf, E:inf, F:inf}. ((A: 0, B: 1)).
     * changePriority(C, 16).
     * {D:5, C:16, E:inf, F:inf}. ((A: 0, B: 1)).
     *
     * Pop D.
     * {C:16, E:inf, F:inf}. ((A: 0, B: 1, D: 5)).
     * changePriority(F, 9).
     * {F: 9, C:16, E:inf}. ((A: 0, B: 1, D: 5)).
     *
     * Pop F.
     * {C:16, E:inf}. ((A: 0, B: 1, D: 5, F: 9)).
     * changePriority(E, 10).
     * {E:10, C:16}. ((A: 0, B: 1, D: 5, F: 9)).
     *
     * Pop E.
     * {C:16}. ((A: 0, B: 1, D: 5, F: 9, E: 10)).
     * changePriority(C, 12).
     * {C:12}. ((A: 0, B: 1, D: 5, F: 9, E: 10)).
     *
     * Pop C.
     * {}. ((A: 0, B: 1, D: 5, F: 9, E: 10, C: 12)).
     *
     * At the end, our table looks like this:
     * DistTo: A=0, B=1, C=12, D=5, E=10, F=9
     * EdgeTo: A=-, B=A, C=E, D=A, E=F, F=D
     *
     * 中文解析
     * Dijkstra 的出队顺序为 A, B, D, F, E, C；每次用出队顶点的出边
     * 松弛。最终前驱边构成 A->B、A->D、D->F、F->E、E->C。
     */
    public static final int[] DIJKSTRA_DIST_TO = {0, 1, 12, 5, 10, 9};
    public static final char[] DIJKSTRA_EDGE_TO = {'-', 'A', 'E', 'A', 'F', 'D'};

    /*
     * 1b - Official solution / 官方答案
     * A* would return A-D-F. The cost here is 9.
     *
     * DistTo: A=0, B=1, C=infinity, D=5, E=infinity, F=9
     * EdgeTo: A=-, B=A, C=-, D=A, E=-, F=D
     *
     * A* runs in a very similar fashion to Dijkstra's. We got the same answer
     * for the shortest path to F, though we actually explored less unnecessary
     * nodes in the process (we never popped B, C, or E off the queue). The main
     * difference is the priority in the priority queue. For A*, whenever
     * computing the priority (for the purposes of the priority queue) of a
     * particular node n, always add h(n) to whatever you would use with
     * Dijkstra's. Additionally, note that A* will be run to find the shortest
     * path to a particular goal node (as our heuristic is calculated as our
     * estimate to our specific goal node), whereas Dijkstra's may be run with
     * a specific goal, or it may be run to find the shortest paths to ALL
     * nodes. In the solutions above, we found the shortest paths to all nodes,
     * but if we only needed to know the shortest path to E, for example, we
     * could have stopped after visiting E.
     *
     * 中文解析
     * A* 用 g+h 排序。A 出队后，B 的优先级是 17，D 是 9；D 出队后 F 的
     * 优先级为 9，随后目标 F 出队并终止。
     */
    public static final String[] A_STAR_PATH = {"A", "D", "F"};
    public static final int A_STAR_COST = 9;

    /*
     * 1c - Official solution / 官方答案
     * The heuristic is admissible: for every node, the heuristic value is less
     * than or equal to the shortest distance path from that node to the target
     * node. It is also consistent: each estimate is less than or equal to the
     * estimated distance from any neighboring vertex to the goal, plus the
     * cost of reaching that neighbor. Because it is both admissible and
     * consistent, we can say that the heuristic is good. If we changed the
     * heuristic from D to F to be 6 (ie. h(D, F) = 6), then the overall
     * heuristic for the graph would no longer be admissible.
     *
     * 中文解析
     * 各启发值均不超过到 F 的真实剩余距离，且每条有向边 u->v 都满足
     * h(u,F) <= g(u,v)+h(v,F)。D 到 F 的真实距离是 4；把 h(D,F) 改成 6
     * 会造成高估。
     */

    public static void main(String[] args) {
        require(Arrays.equals(DIJKSTRA_DIST_TO, new int[]{0, 1, 12, 5, 10, 9}),
                "Dijkstra distances");
        require(Arrays.equals(DIJKSTRA_EDGE_TO, new char[]{'-', 'A', 'E', 'A', 'F', 'D'}),
                "Dijkstra predecessors");
        require(Arrays.equals(A_STAR_PATH, new String[]{"A", "D", "F"}), "A* path");
        require(A_STAR_COST == 9, "A* cost");
    }

    private static void require(boolean condition, String label) {
        if (!condition) {
            throw new AssertionError(label);
        }
    }

    private Dis09RegularQ1ShortestPathsSolution() {
    }
}
