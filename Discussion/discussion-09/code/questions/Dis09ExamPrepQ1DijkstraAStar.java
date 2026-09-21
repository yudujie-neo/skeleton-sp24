package questions;

/** Discussion 09 Exam Prep Question 1: Dijkstra's, A*. */
public class Dis09ExamPrepQ1DijkstraAStar {

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    public record Edge(char first, char second, int weight) {
    }

    /** Java representation of assets/exam-q1-weighted-graph.png. */
    public static final Edge[] GRAPH = {
        new Edge('A', 'B', 1), new Edge('A', 'E', 3),
        new Edge('B', 'C', 2), new Edge('B', 'F', 4),
        new Edge('C', 'F', 1), new Edge('E', 'F', 7),
        new Edge('F', 'D', 2), new Edge('F', 'G', 4),
        new Edge('D', 'G', 1), new Edge('D', 'H', 4),
        new Edge('G', 'H', 5)
    };

    /** h(A,G), h(B,G), h(C,G), h(D,G), h(E,G), h(F,G), h(G,G), h(H,G). */
    public static final int[] HEURISTIC_TO_G = {9, 7, 4, 1, 10, 3, 0, 5};

    /*
     * 1a - English - Official question
     * Run Dijkstra's Algorithm on the graph above starting from vertex A,
     * breaking ties alphabetically. Fill in how the priority values change.
     * When you remove a node from the fringe, mark it with a check, and leave
     * it blank for the subsequent rows. Stop when you remove G. Also sketch
     * the resulting shortest paths tree in the end.
     *
     * 1a - 中文翻译
     * 从 A 开始运行 Dijkstra，平局按字母序处理；记录每轮优先级，结点从
     * fringe 移除时标记，随后留空。移除 G 时停止，并画出最短路径树。
     *
     * Your answer / 你的答案
     * Priority table / 优先级表：TODO
     * Shortest-path tree / 最短路径树：TODO
     */

    /*
     * 1b - English - Official question
     * The heuristic distance from all nodes to G is defined below. Run A*,
     * starting from A and with G as a goal. For each entry in the table below,
     * fill in the distance, followed by the priority value
     * of each node, separated by a comma. Is the heuristic admissible?
     *
     * 1b - 中文翻译
     * 使用给定的到 G 的启发值，从 A 开始、以 G 为目标运行 A*。表中每格
     * 依次填写实际距离和优先级，并判断启发式是否可采纳。
     *
     * Your answer / 你的答案
     * A* table / A* 表：TODO
     * Returned path / 返回路径：TODO
     * Admissible? / 是否可采纳：TODO
     */

    private Dis09ExamPrepQ1DijkstraAStar() {
    }
}
