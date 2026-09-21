package solutions;

/** Official solutions for Discussion 09 Exam Prep Question 2. */
public class Dis09ExamPrepQ2ConceptualShortestPathsSolution {

    /*
     * 2a - Official solution / 官方答案
     * True. If all edges are equal in weight, then the shortest path from A to
     * each node is proportional to the number of nodes on the path, so breadth
     * first search will return the shortest path.
     *
     * 中文解析
     * 等正权时最小化边数就等价于最小化总权重，BFS 按边数分层。
     *
     * Supplemental analysis / 补充分析（非官方答案）
     * 更精确地说，总权重与路径的“边数”成正比，而不是结点数；两者只差 1，
     * 不影响结论。
     */
    public static final boolean ANSWER_2A = true;

    /*
     * 2b - Official solution / 官方答案
     * False. Consider a case of 3 nodes where AB is 3, AC is 5, and BC is 2.
     * Here, the two possible paths from A to C both are of length 5.
     *
     * 中文解析
     * 三条边权 2、3、5 互不相同，但 A-C 直达与 A-B-C 都为 5。
     */
    public static final boolean ANSWER_2B = false;
    public static final double[] COUNTEREXAMPLE_2B = {3, 5, 2};

    /*
     * 2c - Official solution / 官方答案
     * False. Consider a case of 3 nodes A, B, and C where AB is 1, AC is 2.5
     * and BC is 1. Clearly, the best path from A to C is through B, with
     * weight 2. However, if we add 1 to each edge weight, suddenly the path
     * going through B will have weight 4, while the direct path is only 3.5.
     *
     * 中文解析
     * 加常数会按路径边数重复计入，可能改变不同边数路径的相对大小。
     */
    public static final boolean ANSWER_2C = false;
    public static final double[] COUNTEREXAMPLE_2C = {1, 2.5, 1};

    /*
     * 2d - Official solution / 官方答案
     * True. Suppose we have arbitrary nodes u and v. Let's say the shortest
     * path from u to v, before the multiplication by k, was of total weight w.
     * This implies that every other path from u to v was of total weight
     * greater than w. After multiplying each edge weight by k, the total
     * weight of the shortest path becomes w * k and the total weight of every
     * other path becomes some number greater than w * k. Therefore, the
     * original shortest path doesn't change.
     *
     * 中文解析
     * 正比例缩放保持路径总权重的大小关系。
     *
     * Supplemental analysis / 补充分析（非官方答案）
     * 官方证明用“every other path ... greater than w”，隐含最短路径唯一。
     * 若存在并列最短路径，应改为大于或等于；最短路径集合仍保持不变。
     */
    public static final boolean ANSWER_2D = true;

    public static void main(String[] args) {
        require(3 + 2 == 5, "2b equal path totals");
        require(1 + 1 < 2.5, "2c original path order");
        require((1 + 1) + 2 > 2.5 + 1, "2c shifted path order");
        require(ANSWER_2A && !ANSWER_2B && !ANSWER_2C && ANSWER_2D, "official answers");
    }

    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private Dis09ExamPrepQ2ConceptualShortestPathsSolution() {
    }
}
