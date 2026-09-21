package solutions;

import java.util.Arrays;

/** Official solutions for Discussion 09 Exam Prep Question 4. */
public class Dis09ExamPrepQ4MinimumSpanningTreesSolution {

    /*
     * 4a - Official solution / 官方答案
     * Prim's algorithm order: AB, BC, BE, EF, BG, CD
     * Kruskal's algorithm order: EF, BC, BE, BG, AB, CD
     *
     * 中文解析
     * 两种算法最终得到相同边集，但加入顺序不同；总权重为 28。
     */
    public static final String[] PRIM_ORDER = {"AB", "BC", "BE", "EF", "BG", "CD"};
    public static final String[] KRUSKAL_ORDER = {"EF", "BC", "BE", "BG", "AB", "CD"};
    public static final int MST_WEIGHT = 28;

    /*
     * 4b - Official solution / 官方答案
     * True, either this smallest edge (now with weight +1) is included, or
     * this smallest edge is not included and some larger edge takes its place
     * since there was no other edge of equal weight. Either way, the total
     * weight increases.
     *
     * 中文解析
     * 在题目假设下，全局唯一最小边属于 MST；加 1 后无论保留还是换边，新
     * MST 总权重都会增加。
     */
    public static final boolean ANSWER_4B = true;

    /*
     * 4c - Official solution / 官方答案
     * True, the cut property states that the minimum weight edge in a cut must
     * be in the MST. Since all weights are unique, the minimum weight edge is
     * always unique, so there is only one possible MST.
     *
     * 中文解析
     * 官方答案按“整幅图所有边权唯一”的常见定理作答。
     *
     * Supplemental analysis / 补充分析（非官方答案）
     * 原题严格写的是“all the weights in an MST are unique”，只保证某一棵
     * MST 内部边权互异，这不足以推出 MST 唯一。例如三角形边权 1,2,2 有
     * 两棵总权重 3 的 MST，而每棵内部权重仍为 {1,2}。只有整幅图所有边权
     * 唯一时，官方结论才成立。
     */
    public static final boolean OFFICIAL_ANSWER_4C = true;

    /*
     * 4d - Official solution / 官方答案
     * False, consider vertices C and E in the graph above. The shortest path
     * between C and E uses the edge CE, but it is not part of the MST of the
     * graph.
     *
     * 中文解析
     * CE 直接边权为 5，而官方 MST 中 C-B-E 的树路径成本为 3+4=7。MST
     * 最小化连接全部顶点的总权重，不保证每一对顶点的树路径最短。
     */
    public static final boolean ANSWER_4D = false;

    public static void main(String[] args) {
        if (!Arrays.equals(PRIM_ORDER, new String[]{"AB", "BC", "BE", "EF", "BG", "CD"})) {
            throw new AssertionError("Prim order");
        }
        if (!Arrays.equals(KRUSKAL_ORDER, new String[]{"EF", "BC", "BE", "BG", "AB", "CD"})) {
            throw new AssertionError("Kruskal order");
        }
        if (MST_WEIGHT != 28 || !ANSWER_4B || !OFFICIAL_ANSWER_4C || ANSWER_4D) {
            throw new AssertionError("official MST answers");
        }
    }

    private Dis09ExamPrepQ4MinimumSpanningTreesSolution() {
    }
}
