package solutions;

/** Official solutions for Discussion 05 Exam Prep Question 2. */
public class Dis05ExamPrepQ2DisjointSetRepresentationsSolution {

    /*
     * Official criteria / 官方判据
     * A representation is invalid if it has a parent-link cycle, if a union
     * would have attached a larger tree below a smaller tree, or if a tree's
     * height exceeds the weighted-union logarithmic height bound.
     *
     * Official verdicts / 官方判断
     * A. Impossible: 0 -> 1 -> 2 -> 3 -> 0 is a cycle.
     * B. Impossible: root 0 would already represent the larger tree and would
     *    not be attached below 9.
     * C. Impossible: the tree has height 9, greater than log2(10).
     * D. Possible.
     * E. Impossible: the tree rooted at 0 has height 4, greater than log2(10).
     * F. Impossible: the size-7 tree rooted at 0 has height 3, greater than
     *    floor(log2(7)) = 2.
     *
     * 中文解析
     * A 含父指针环；B 违反按大小合并；C、E、F 超出 WQU 的树高限制；
     * D 可按合法的等大小/按大小合并顺序构造。路径压缩只会缩短路径，
     * 不会帮助产生更高的树。
     */

    private static final boolean[] POSSIBLE = {false, false, false, true, false, false};

    public static boolean isPossible(char letter) {
        int index = Character.toUpperCase(letter) - 'A';
        if (index < 0 || index >= POSSIBLE.length) {
            throw new IllegalArgumentException("Expected a letter from A through F");
        }
        return POSSIBLE[index];
    }

    public static void main(String[] args) {
        if (isPossible('A') || !isPossible('D') || isPossible('F')) {
            throw new AssertionError("Official verdict table mismatch");
        }
    }
}
