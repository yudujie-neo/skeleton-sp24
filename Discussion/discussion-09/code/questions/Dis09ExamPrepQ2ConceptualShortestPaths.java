package questions;

/** Discussion 09 Exam Prep Question 2: Conceptual Shortest Paths. */
public class Dis09ExamPrepQ2ConceptualShortestPaths {

    /*
     * English - Official instructions
     * Answer the following questions regarding shortest path algorithms for a
     * weighted, undirected graph. If the statement is true, provide an
     * explanation. If the statement is false, provide a counterexample.
     *
     * 中文翻译
     * 回答关于加权无向图最短路径算法的问题。若命题为真，请解释；若为
     * 假，请给出反例。
     */

    /*
     * 2a - English - Official question
     * (T/F) If all edge weights are equal and positive, the breadth-first
     * search starting from node A will return the shortest path from a node A
     * to a target node B.
     *
     * 2a - 中文翻译
     * 判断：若所有边权相等且为正，从 A 开始的 BFS 会返回 A 到目标 B 的
     * 最短路径。
     *
     * My answer / 我的答案：TODO
     * Explanation or counterexample / 解释或反例：TODO
     */

    /*
     * 2b - English - Official question
     * (T/F) If all edges have distinct weights, the shortest path between any
     * two vertices is unique.
     *
     * 2b - 中文翻译
     * 判断：若所有边的权重互不相同，则任意两顶点之间的最短路径唯一。
     *
     * My answer / 我的答案：TODO
     * Explanation or counterexample / 解释或反例：TODO
     */

    /*
     * 2c - English - Official question
     * (T/F) Adding a constant positive integer k to all edge weights will not
     * affect any shortest path between two vertices.
     *
     * 2c - 中文翻译
     * 判断：给所有边权加上同一个正常数 k，不会影响任意两顶点间的最短
     * 路径。
     *
     * My answer / 我的答案：TODO
     * Explanation or counterexample / 解释或反例：TODO
     */

    /*
     * 2d - English - Official question
     * (T/F) Multiplying a constant positive integer k to all edge weights will
     * not affect any shortest path between two vertices.
     *
     * 2d - 中文翻译
     * 判断：给所有边权乘以同一个正常数 k，不会影响任意两顶点间的最短
     * 路径。
     *
     * My answer / 我的答案：TODO
     * Explanation or counterexample / 解释或反例：TODO
     */

    /*
     * Compilation scaffold / 编译脚手架（非官方题目内容）
     * 可用 WeightedEdge 表示你为 2b 或 2c 构造的反例；不要把结论编码成
     * 断言，以免题目文件泄露答案。
     */
    public record WeightedEdge(char first, char second, double weight) {
    }

    private Dis09ExamPrepQ2ConceptualShortestPaths() {
    }
}
