package questions;

import java.util.Arrays;

/** Discussion 05 Exam Prep Question 2 - Disjoint-set representations. */
public class Dis05ExamPrepQ2DisjointSetRepresentations {
    private static final int[][] CASES = {
        {1, 2, 3, 0, 1, 1, 1, 4, 4, 5},
        {9, 0, 0, 0, 0, 0, 9, 9, 9, -10},
        {1, 2, 3, 4, 5, 6, 7, 8, 9, -10},
        {-10, 0, 0, 0, 0, 1, 1, 1, 6, 2},
        {-10, 0, 0, 0, 0, 1, 1, 1, 6, 8},
        {-7, 0, 0, 1, 1, 3, 3, -3, 7, 7}
    };

    /*
     * English (official question)
     * For each of the arrays below, write whether this could be the array
     * representation of a weighted quick union with path compression and
     * explain your reasoning. Break ties by choosing the smaller integer to be
     * the root.
     *
     * 中文翻译
     * 对数组 A-F，判断它能否表示一个带路径压缩的 weighted quick union，
     * 并解释理由。集合大小相同时选择较小整数作为根。
     *
     * My answer / 我的答案：
     * A: TODO; reason / 理由：TODO
     * B: TODO; reason / 理由：TODO
     * C: TODO; reason / 理由：TODO
     * D: TODO; reason / 理由：TODO
     * E: TODO; reason / 理由：TODO
     * F: TODO; reason / 理由：TODO
     */

    public static int[] caseByLetter(char letter) {
        int index = Character.toUpperCase(letter) - 'A';
        if (index < 0 || index >= CASES.length) {
            throw new IllegalArgumentException("Expected a letter from A through F");
        }
        return Arrays.copyOf(CASES[index], CASES[index].length);
    }
}
