package solutions;

import java.util.Arrays;

/** Official solutions for Discussion 09 Exam Prep Question 1. */
public class Dis09ExamPrepQ1DijkstraAStarSolution {

    /*
     * 1a - Official solution / 官方答案
     * The SPT of the whole graph is drawn below for your reference. You should
     * be able to “read-off” the edgeTo pointers by
     * locating the last time the priority value of a node changes, and search
     * for where the “checkmark” is for that row - that row would be the edge
     * you came from in the SPT!
     */
    /*
     * Official table transcription / 官方表格转写（非官方 Java 表示）
     * Infinity is Integer.MAX_VALUE. A removed vertex is represented by -1;
     * DIJKSTRA_POP_ORDER identifies the vertex carrying the official checkmark
     * in each iteration, so later blank cells remain distinguishable from the
     * checkmark row.
     *
     * The official drawing remains in
     * ../../assets/exam-q1a-shortest-path-tree.png.
     *
     * Chinese explanation / 中文解析（非官方）
     * Dijkstra 的移除顺序为 A, B, C, E, F, D, G。最短路径树边为 AB、AE、
     * BC、CF、FD、DG，另有 DH（若继续构造全图 SPT）。
     */
    private static final int INF = Integer.MAX_VALUE;
    private static final int REMOVED = -1;

    public static final int[][] DIJKSTRA_TABLE = {
        {0, INF, INF, INF, INF, INF, INF, INF},
        {REMOVED, 1, INF, INF, 3, INF, INF, INF},
        {REMOVED, REMOVED, 3, INF, 3, 5, INF, INF},
        {REMOVED, REMOVED, REMOVED, INF, 3, 4, INF, INF},
        {REMOVED, REMOVED, REMOVED, INF, REMOVED, 4, INF, INF},
        {REMOVED, REMOVED, REMOVED, 6, REMOVED, REMOVED, 8, INF},
        {REMOVED, REMOVED, REMOVED, REMOVED, REMOVED, REMOVED, 7, 10},
        {REMOVED, REMOVED, REMOVED, REMOVED, REMOVED, REMOVED, REMOVED, REMOVED}
    };
    public static final char[] DIJKSTRA_POP_ORDER = {'A', 'B', 'C', 'E', 'F', 'D', 'G'};

    /*
     * 1b - Official solution / 官方答案
     * The shortest path is: A → B, B → C, C → F, F → D, D → G.
     *
     * This heuristic is not admissible (h(A,G) > dist(A,G) = 7). However, in
     * this graph A* still returns the correct shortest path from A to G (recall
     * in lecture, admissible and consistent heuristic is only a sufficient
     * condition, so you can still have inadmissible heuristics and still
     * correct results).
     */
    public record Entry(int distance, int priority) {
    }

    /**
     * Official table transcription / 官方表格转写（非官方 Java 表示）.
     * Rows are Start and Iterations 1 through 6; columns are A through H. A
     * null entry means that vertex has already been removed; A_STAR_POP_ORDER
     * identifies the vertex carrying the official checkmark in each iteration.
     */
    public static final Entry[][] A_STAR_TABLE = {
        {new Entry(0, 9), inf(), inf(), inf(), inf(), inf(), inf(), inf()},
        {null, new Entry(1, 8), inf(), inf(), new Entry(3, 13), inf(), inf(), inf()},
        {null, null, new Entry(3, 7), inf(), new Entry(3, 13),
                new Entry(5, 8), inf(), inf()},
        {null, null, null, inf(), new Entry(3, 13), new Entry(4, 7), inf(), inf()},
        {null, null, null, new Entry(6, 7), new Entry(3, 13), null,
                new Entry(8, 8), inf()},
        {null, null, null, null, new Entry(3, 13), null,
                new Entry(7, 7), new Entry(10, 15)},
        {null, null, null, null, null, null, null, null}
    };
    public static final char[] A_STAR_POP_ORDER = {'A', 'B', 'C', 'F', 'D', 'G'};

    private static Entry inf() {
        return new Entry(INF, INF);
    }

    public static final String[] A_STAR_PATH = {"A", "B", "C", "F", "D", "G"};
    public static final int A_STAR_DISTANCE = 7;
    public static final boolean HEURISTIC_IS_ADMISSIBLE = false;

    /*
     * Chinese explanation / 中文解析（非官方）
     * h(A,G)=9 高于真实最短距离 7，所以不可采纳；但本图上的实际出队过程
     * 仍返回 A-B-C-F-D-G。
     */

    public static void main(String[] args) {
        if (!Arrays.equals(A_STAR_PATH, new String[]{"A", "B", "C", "F", "D", "G"})) {
            throw new AssertionError("A* path");
        }
        if (A_STAR_DISTANCE != 7 || HEURISTIC_IS_ADMISSIBLE) {
            throw new AssertionError("A* distance/admissibility");
        }
        if (A_STAR_TABLE[5][6].distance() != 7 || A_STAR_TABLE[5][6].priority() != 7
                || !allNull(A_STAR_TABLE[6])) {
            throw new AssertionError("A* termination row");
        }
        if (!Arrays.equals(A_STAR_POP_ORDER, new char[]{'A', 'B', 'C', 'F', 'D', 'G'})) {
            throw new AssertionError("A* pop order");
        }
        if (DIJKSTRA_TABLE[6][6] != 7
                || !Arrays.equals(DIJKSTRA_TABLE[7], new int[]{
                    REMOVED, REMOVED, REMOVED, REMOVED,
                    REMOVED, REMOVED, REMOVED, REMOVED})) {
            throw new AssertionError("Dijkstra termination row");
        }
        if (!Arrays.equals(DIJKSTRA_POP_ORDER, new char[]{'A', 'B', 'C', 'E', 'F', 'D', 'G'})) {
            throw new AssertionError("Dijkstra pop order");
        }
    }

    private static boolean allNull(Entry[] row) {
        for (Entry entry : row) {
            if (entry != null) {
                return false;
            }
        }
        return true;
    }

    private Dis09ExamPrepQ1DijkstraAStarSolution() {
    }
}
