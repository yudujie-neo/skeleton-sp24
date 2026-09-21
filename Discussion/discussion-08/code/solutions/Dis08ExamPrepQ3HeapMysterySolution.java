package solutions;

import java.util.List;

/** Official solution and executable checks for Discussion 08 Exam-Level Q3. */
public class Dis08ExamPrepQ3HeapMysterySolution {

    /*
     * 3a - Official solution / 官方答案
     * 1. removeMin()
     * 2. insert(X)
     * 3. removeMin()
     * 4. insert(A)
     *
     * Explanation: We know immediately that A was removed. Then, after looking
     * at the final state of the min-heap, we see that C was removed. Then, for A
     * to remain in the min-heap, we see that A must have been inserted
     * afterwards. And, after seeing a new value X in the min-heap, we see that X
     * must have been inserted as well. We just need to determine the relative
     * ordering of the insert(X) in between the operations removeMin() and
     * insert(A), and we see that the insert(X) must go before both.
     *
     * 中文解释：第一次删除 A，第二次删除 C；最终 A 再次出现且新增 X，因此
     * 二者必须被插入。官方根据堆的移动过程确定 insert(X) 在第二次删除前。
     */
    public static List<String> operations() {
        return List.of("removeMin()", "insert(X)", "removeMin()", "insert(A)");
    }

    /*
     * 3b - Official solution / 官方答案
     * 1. X ? D
     * 2. X > C
     * 3. B > C
     * 4. G < X
     *
     * Reasoning:
     * 1. X is never compared to D
     * 2. X must be greater than C since C is removed after X's insertion.
     * 3. B must also be greater than C otherwise the second call to removeMin
     *    would have removed B
     * 4. X must be greater than G so that it can be "promoted" to the top after
     *    the removal of C. It needs to be promoted to the top to land in its new
     *    position.
     *
     * 中文解释：最小堆只给出操作路径上的偏序，X 与 D 没有确定关系；第二次
     * 删除 C 说明 C 小于 X 和 B；移动路径说明 G 小于 X。
     *
     * Supplemental analysis / 补充分析（非官方答案）：
     * 题面文字称根位于 index 0，但数组以 "-" 开头、A 位于第二项，表现为
     * 1-based 表示。这是官方题面的索引矛盾；这里保留原文和数组，并按 A
     * 是根这一出题意图解答。
     */
    public static List<Character> comparisons() {
        return List.of('?', '>', '>', '<');
    }

    public static void main(String[] args) {
        if (!operations().equals(List.of("removeMin()", "insert(X)", "removeMin()", "insert(A)"))) {
            throw new AssertionError("Unexpected operation sequence");
        }
        if (!comparisons().equals(List.of('?', '>', '>', '<'))) {
            throw new AssertionError("Unexpected comparisons");
        }
    }
}
