package questions;

/** CS 61B Spring 2024 - Discussion 10, Regular Question 2. */
public class Dis10RegularQ2ATreeTakesOnGraphs {

    /*
     * English - Official question
     * Your friend at Stanford has come to you for help on their homework! For
     * each of the following statements, determine whether they are true or
     * false; if false, provide counterexamples.
     *
     * 中文翻译
     * 你在 Stanford 的朋友来请你帮忙完成作业。判断下面每个陈述的真假；
     * 若为假，请给出反例。
     */

    /*
     * 2a - English - Official question
     * "A graph with edges that all have the same weight will always have
     * multiple MSTs."
     *
     * 2a - 中文翻译
     * “所有边权都相同的图一定有多棵最小生成树。”
     *
     * Your answer / 你的答案
     * True or false / 真或假：TODO
     * Counterexample if false / 若为假，给出反例：TODO
     */

    /*
     * 2b - English - Official question
     * "No matter what heuristic you use, A* search will always find the
     * correct shortest path."
     *
     * 2b - 中文翻译
     * “无论使用什么启发式函数，A* 搜索总能找到正确的最短路径。”
     *
     * Your answer / 你的答案：TODO
     * The official graph remains in
     * ../../assets/regular-q2b-a-star-counterexample.png.
     * 官方图继续保留在上述 assets 路径中。
     */

    /*
     * 2c - English - Official question
     * "If you add a constant factor to each edge in a graph, Dijkstra's
     * algorithm will return the same shortest paths tree."
     *
     * 2c - 中文翻译
     * “若给图中的每条边都加上同一个常数，Dijkstra 算法会返回相同的
     * 最短路径树。”
     *
     * Your answer / 你的答案：TODO
     * The official before/after graph remains in
     * ../../assets/regular-q2c-dijkstra-counterexample.png.
     */

    /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
    public record WeightedEdge(char from, char to, int weight) {
    }

    /** Compilation scaffold for checking the cost of a proposed path. */
    public static int pathCost(WeightedEdge... path) {
        int total = 0;
        for (WeightedEdge edge : path) {
            total += edge.weight();
        }
        return total;
    }

    private Dis10RegularQ2ATreeTakesOnGraphs() {
    }
}
