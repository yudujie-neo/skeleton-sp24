package solutions;

import java.util.Arrays;

/** Official solutions for Discussion 09 Regular Question 2. */
public class Dis09RegularQ2MinimumSpanningTreesSolution {

    /*
     * 2a - Official solution / 官方答案
     * Both Prim's and Kruskal's give the MST below.
     *
     * The official drawing remains in
     * ../../assets/regular-q2a-official-mst.png.
     *
     * 中文解析
     * 按题目平局规则，边集为 AC(1), CE(2), CD(3), AB(4)，总权重 10。
     *
     * Supplemental analysis / 补充分析（非官方答案）
     * 官方 Kruskal 伪代码用“仍有结点不在 MST 中”简化循环，并说每次加入
     * 一个 new node。标准 Kruskal 按全局边权选择不成环的边，直到选满
     * V-1 条；被选边不一定每次只引入一个此前未出现的顶点。
     */
    public static final String[] MST_EDGES = {"AC", "CE", "CD", "AB"};
    public static final int MST_WEIGHT = 10;

    /*
     * 2b - Official solution / 官方答案
     * In this particular case, the trees for Prim's and Kruskal's are the
     * same. However, because our graph has edges with duplicate weights, then
     * it would be possible for Prim's and Kruskal's to give different answers
     * with a different tiebreaking scheme. For example, if in the graph above,
     * depending on which node we start Prim's from, as well as which
     * tiebreaking scheme we use (ie. instead of lower alphabetical order, use
     * higher alphabetical order, or randomness, or by most recently added
     * node(s)), we could get other perfectly valid MSTs, like:
     */

    /*
     * Official drawing reference / 官方图示引用（非官方说明）
     * The three official drawings remain in
     * ../../assets/regular-q2b-alternative-msts.png.
     *
     * Chinese explanation / 中文解析（非官方）
     * AB/BC 都为 4，CD/DE 都为 3，所以 MST 不唯一。改变 Prim 起点或等权
     * 边顺序可用 BC 替代 AB，或用 DE 替代 CD，总权重仍为 10。
     */

    public static void main(String[] args) {
        if (!Arrays.equals(MST_EDGES, new String[]{"AC", "CE", "CD", "AB"})) {
            throw new AssertionError("official MST edge order");
        }
        if (MST_WEIGHT != 10) {
            throw new AssertionError("official MST weight");
        }
    }

    private Dis09RegularQ2MinimumSpanningTreesSolution() {
    }
}
