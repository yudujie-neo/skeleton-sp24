package questions;

import java.util.List;

/** CS 61B Spring 2024 - Discussion 10, Exam-Level Question 1. */
public class Dis10ExamPrepQ1MultipleMSTs {

    /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
    public record Edge(char from, char to, int weight) {
    }

    /*
     * English - Official preamble
     * Recall a graph can have multiple MSTs if there are multiple spanning
     * trees of minimum weight.
     *
     * 中文翻译
     * 回顾：若存在多棵总权重最小的生成树，则图有多棵 MST。
     */

    /*
     * 1a - English - Official question
     * For each subpart below, select the correct option and justify your
     * answer. If you select "never" or "always," provide a short explanation.
     * If you select "sometimes", provide two graphs that fulfill the given
     * properties - one with multiple MSTs and one without. Assume G is an
     * undirected, connected graph with at least 3 vertices.
     *
     * 1. If some of the edge weights are identical, there will
     *    [never / sometimes / always] be multiple MSTs in G.
     *    Justification:
     * 2. If all of the edge weights are identical, there will
     *    [never / sometimes / always] be multiple MSTs in G.
     *    Justification:
     *
     * 1a - 中文翻译
     * 每小题从“从不/有时/总是”中选择并论证。选“有时”时，分别给出一个
     * 有多棵 MST 和一个没有多棵 MST 的图。G 为至少含三个顶点的连通
     * 无向图。第 1 小题只有部分边权相同；第 2 小题所有边权相同。
     *
     * Your answer / 你的答案
     * 1a - Part 1 choice and graphs / 第 1 小问选择与图：TODO
     * 1a - Part 2 choice and graphs / 第 2 小问选择与图：TODO
     * Official example images remain in ../../assets/exam-q1a1-mst-examples.png
     * and ../../assets/exam-q1a2-mst-examples.png.
     */

    /*
     * 1b - English - Official question
     * Suppose we have a connected, undirected graph G with N vertices and N
     * edges, where all the edge weights are identical. Find the maximum and
     * minimum number of MSTs in G and explain your reasoning.
     *
     * 1b - 中文翻译
     * 连通无向图 G 有 N 个顶点、N 条边，且边权全部相同。求 MST 数量的
     * 最大值和最小值并解释。
     *
     * Your answer / 你的答案
     * Minimum / 最小值：TODO
     * Maximum / 最大值：TODO
     * Justification / 理由：TODO
     */

    /*
     * 1c - English - Official question
     * It is possible that Prim's and Kruskal's find different MSTs on the same
     * graph G (as an added exercise, construct a graph where this is the
     * case!). Given any graph G with integer edge weights, modify the edge
     * weights of G to ensure that (1) Prim's and Kruskal's will output the same
     * results, and (2) the output edges still form a MST correctly in the
     * original graph. You may not modify Prim's or Kruskal's, and you may not
     * add or remove any nodes/edges.
     * Hint: Look at subpart 1 of part a.
     *
     * 1c - 中文翻译
     * 对任意整数边权图修改边权，使 Prim 与 Kruskal 输出同一结果，且该
     * 结果在原图中仍是 MST。不得修改算法或增删顶点/边。
     *
     * Your answer / 你的答案：TODO
     * The added-exercise graph remains in
     * ../../assets/exam-q1c-different-msts.png.
     */
    public static List<Edge> modifyWeights(List<Edge> graph) {
        // TODO: Return a modified copy; do not add or remove edges.
        throw new UnsupportedOperationException("TODO: modifyWeights");
    }

    private Dis10ExamPrepQ1MultipleMSTs() {
    }
}
