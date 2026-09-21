package solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Official solutions and checks for Discussion 10 Exam-Level Q1. */
public class Dis10ExamPrepQ1MultipleMSTsSolution {
    public record Edge(char from, char to, double weight) {
    }

    /*
     * 1a - Part 1 - Official solution / 官方答案
     * Sometimes be multiple MSTs in G.
     *
     * In the graph on the left, the only MST is [AB, BC]. In the graph on the
     * right, two MSTs exist - [AB, BC] and [AC, BC].
     * See ../../assets/exam-q1a1-mst-examples.png.
     *
     * 1a - Part 2 - Official solution / 官方答案
     * Sometimes be multiple MSTs in G.
     *
     * In the graph on the left, the only MST is [AB, AC]. Note that for any
     * tree, we only have one MST, since the tree itself is the MST! In the
     * graph on the right, three MSTs exist - [AB, BC], [AC, BC], and [AB, AC].
     * See ../../assets/exam-q1a2-mst-examples.png.
     *
     * 中文解释：重复边权使多棵 MST 成为可能，但不保证发生；图本身若是树，
     * 仍只有一棵生成树。
     */

    /*
     * 1b - Official solution / 官方答案
     * Minimum: 3. Maximum: N.
     * Notice that if all the edge weights are the same, an MST is just a
     * spanning tree. Let's begin by creating a tree, i.e. a connected graph
     * with N - 1 edges. Now, notice that there is only one spanning tree, since
     * the graph is itself a tree.
     *
     * As such, the problem reduces to: how many spanning trees can the
     * insertion of one edge create? If we add an edge to a tree, it will create
     * a cycle that can be of length at minimum 3 and at maximum N. Then, notice
     * that we can only remove any edge from a cycle to create a spanning tree,
     * so we have at minimum 3 and at maximum N possible MSTs in G.
     *
     * 中文解释：N 个顶点 N 条边的连通无向图是单环图。环长即生成树数量，
     * 环长范围为 3 到 N。
     */
    public static int minimumMstCount() {
        return 3;
    }

    public static int maximumMstCount(int vertexCount) {
        return vertexCount;
    }

    /*
     * 1c - Official solution / 官方答案
     * To ensure that Prim's and Kruskal's will always produce the same MST,
     * notice that if G has unique edges, only one MST can exist, and Prim's and
     * Kruskal's will always find that MST! So, what if we modify G to ensure
     * that all the edge weights are unique?
     *
     * To achieve this, let's strategically add a small, unique offset between
     * 0 and 1, exclusive, to each edge. It is important that we choose an
     * offset between 0 and 1. This is to ensure that the edges picked in the
     * modified graph is still a correct MST in the original graph, since all
     * the edge weights are integers. It is also important that the offset is
     * unique for each edge, because then we ensure each weight is distinct.
     *
     * Official pseudocode / 官方伪代码
     * E = number of edges in the graph
     * offset = 0
     * for edge in graph:
     *     edge.weight += offset
     *     offset += 1 / E
     *
     * In regard to the added exercise, here is a simple graph G where Prim's
     * and Kruskal's produce different MSTs. Prim's starting from A will select
     * AD, BD, and CD, whereas Kruskal's will select AD, BC, and BD. The graph
     * is at ../../assets/exam-q1c-different-msts.png.
     *
     * Supplemental analysis / 补充分析（非官方答案）
     * The prose requires offsets strictly in (0,1), but the official
     * pseudocode starts at 0. Offsets 0, 1/E, ..., (E-1)/E are still unique
     * and below 1, so the method remains correct. In Java, use 1.0/E; integer
     * division in 1/E would otherwise produce zero when E>1.
     */
    public static List<Edge> modifyWeights(List<Edge> graph) {
        List<Edge> modified = new ArrayList<>();
        double step = 1.0 / graph.size();
        double offset = 0.0;
        for (Edge edge : graph) {
            modified.add(new Edge(edge.from(), edge.to(), edge.weight() + offset));
            offset += step;
        }
        return modified;
    }

    public static void main(String[] args) {
        require(minimumMstCount() == 3);
        require(maximumMstCount(8) == 8);
        List<Edge> original = List.of(
                new Edge('A', 'B', 1), new Edge('B', 'C', 1),
                new Edge('A', 'C', 2), new Edge('C', 'D', 2)
        );
        List<Edge> modified = modifyWeights(original);
        Set<Double> weights = new HashSet<>();
        for (int i = 0; i < modified.size(); i += 1) {
            require(Math.floor(modified.get(i).weight()) == original.get(i).weight());
            require(weights.add(modified.get(i).weight()));
        }
    }

    private static void require(boolean condition) {
        if (!condition) {
            throw new AssertionError("Discussion 10 Exam-Level Q1 smoke test failed");
        }
    }
}
