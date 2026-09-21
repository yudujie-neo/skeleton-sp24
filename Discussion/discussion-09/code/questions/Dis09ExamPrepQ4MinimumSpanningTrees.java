package questions;

/** Discussion 09 Exam Prep Question 4: Introduction to MSTs. */
public class Dis09ExamPrepQ4MinimumSpanningTrees {

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    public record Edge(char first, char second, int weight) {
    }

    /** Java representation of assets/exam-q4-weighted-graph.png. */
    public static final Edge[] GRAPH = {
        new Edge('A', 'B', 6), new Edge('A', 'C', 12),
        new Edge('B', 'C', 3), new Edge('B', 'E', 4),
        new Edge('B', 'F', 7), new Edge('B', 'G', 4),
        new Edge('C', 'D', 9), new Edge('C', 'E', 5),
        new Edge('E', 'F', 2)
    };

    /*
     * 4a - English - Official question
     * For the graph above, list the edges in the order they're added to the
     * MST by Kruskal's and Prim's algorithm. Assume Prim's algorithm starts at
     * vertex A. Assume ties are broken in alphabetical order. Denote each edge
     * as a pair of vertices (e.g. AB is the edge from A to B).
     *
     * 4a - 中文翻译
     * 对图分别列出 Kruskal 和 Prim 加边顺序。Prim 从 A 开始，平局按字母
     * 序处理；边用顶点对表示，如 AB。
     *
     * Prim's algorithm order：TODO
     * Kruskal's algorithm order：TODO
     */

    /*
     * 4b - English - Official question
     * True/False: Adding 1 to the smallest edge of a graph G with unique edge
     * weights must change the total weight of its MST.
     *
     * 4b - 中文翻译
     * 判断：在边权唯一的图 G 中，把最小边的权重加 1，必然改变 MST 总权重。
     *
     * My answer / 我的答案：TODO
     */

    /*
     * 4c - English - Official question
     * True/False: If all the weights in an MST are unique, there is only one
     * possible MST.
     *
     * 4c - 中文翻译
     * 判断：如果一棵 MST 中所有边的权重互不相同，则可能的 MST 只有一棵。
     *
     * My answer / 我的答案：TODO
     */

    /*
     * 4d - English - Official question
     * True/False: The shortest path from vertex u to vertex v in a graph G is
     * the same as the shortest path from u to v using only edges in T, where T
     * is the MST of G.
     *
     * 4d - 中文翻译
     * 判断：图 G 中 u 到 v 的最短路径等于仅使用 G 的 MST T 中边所得的
     * u 到 v 路径。
     *
     * My answer / 我的答案：TODO
     */

    private Dis09ExamPrepQ4MinimumSpanningTrees() {
    }
}
