package questions;

/** Discussion 09 Regular Question 2: Minimalist Moles / 极简鼹鼠隧道. */
public class Dis09RegularQ2MinimumSpanningTrees {

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    public record Edge(char first, char second, int weight) {
    }

    /** Java representation of assets/regular-q2-weighted-graph.png. */
    public static final Edge[] GRAPH = {
        new Edge('A', 'B', 4), new Edge('B', 'C', 4),
        new Edge('A', 'C', 1), new Edge('A', 'D', 5),
        new Edge('C', 'D', 3), new Edge('C', 'E', 2),
        new Edge('D', 'E', 3)
    };

    /*
     * English - Official question preamble
     * Circle the mole wants to dig a network of tunnels connecting all of his
     * secret hideouts. There are a few set paths between the secret hideouts
     * that Circle can choose to possibly include in his tunnel system, shown
     * below. However, some portions of the ground are harder to dig
     * than others, and Circle wants to do as little work as possible. In the
     * diagram, the numbers next to the paths correspond to how hard that path
     * is to dig for Circle. Lucky for us, he knows how to use MSTs to optimize
     * the tunnel paths!
     *
     * 中文翻译
     * Circle 想挖一套连接所有秘密藏身处的隧道，不同路径的挖掘难度由图中
     * 数字表示。他希望总工作量最小，因此使用最小生成树优化隧道网络。
     *
     * Kruskal's pseudocode - Official reference
     * while there are still nodes not in the MST:
     *     Add the lightest edge that does not create a cycle.
     *     Add the new node to the set of nodes in the MST.
     *
     * Prim's pseudocode - Official reference
     * Start with any node.
     * Add that node to the set of nodes in the MST.
     * While there are still nodes not in the MST:
     *     Add the lightest edge from a node in the MST
     *         that leads to a new node that is unvisited.
     *     Add the new node to the set of nodes in the MST.
     */

    /*
     * 2a - English - Official question
     * Find a valid MST for the graph above using Kruskal's algorithm, then
     * Prim's. For Prim's algorithm, take A as the start node. In both cases,
     * if there is ever a tie, choose the edge that connects two nodes with
     * lower alphabetical order.
     *
     * 2a - 中文翻译
     * 先用 Kruskal、再用 Prim 求一棵有效 MST；Prim 从 A 开始，平局时选择
     * 连接字母序较小顶点的边。
     *
     * Your answer / 你的答案
     * Kruskal edge order / Kruskal 加边顺序：TODO
     * Prim edge order / Prim 加边顺序：TODO
     */

    /*
     * 2b - English - Official question
     * Are the above MSTs different or the same? If different, describe a
     * tie-breaking scheme that would make them the same. If the same, describe
     * a tie-breaking scheme that would make them different.
     *
     * 2b - 中文翻译
     * 上述两棵 MST 相同还是不同？若不同，描述使它们相同的平局规则；若
     * 相同，描述使它们不同的平局规则。
     *
     * Your answer / 你的答案：TODO
     */

    private Dis09RegularQ2MinimumSpanningTrees() {
    }
}
