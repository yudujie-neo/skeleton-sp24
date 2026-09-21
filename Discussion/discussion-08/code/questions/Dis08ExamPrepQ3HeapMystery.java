package questions;

import java.util.List;

/** CS 61B Spring 2024 - Discussion 08, Exam-Level Question 3. */
public class Dis08ExamPrepQ3HeapMystery {

    /*
     * English - Official question
     * We are given the following array representing a min-heap where each
     * letter represents a unique number. Assume the root of the min-heap is at
     * index zero, i.e. A is the root. Our task is to figure out the numeric
     * ordering of the letters. Therefore, there is no significance of the
     * alphabetical ordering. i.e. just because B precedes C in the alphabet, we
     * do not know if B is less than or greater than C.
     *
     * Array: [-, A, B, C, D, E, F, G]
     *
     * Four unknown operations are then executed on the min-heap. An operation
     * is either a removeMin or an insert. The resulting state of the min-heap is
     * shown below.
     *
     * Array: [-, A, E, B, D, X, F, G]
     *
     * 中文翻译
     * 给定上述最小堆数组，每个字母表示互不相同的数，字母顺序本身没有
     * 数值意义。随后执行四个未知操作（removeMin 或 insert），得到第二个
     * 数组。请根据状态变化推断操作和必要的大小关系。
     */
    public static final List<String> INITIAL = List.of("-", "A", "B", "C", "D", "E", "F", "G");
    public static final List<String> FINAL = List.of("-", "A", "E", "B", "D", "X", "F", "G");

    /*
     * 3a - English - Official question
     * Determine the operations executed and their appropriate order. The first
     * operation has already been filled in for you!
     *
     * Hint: Which elements are gone? Which elements are newly added? Which
     * elements are removed and then added back?
     *
     * 1. removeMin()
     * 2. ______
     * 3. ______
     * 4. ______
     *
     * 3a - 中文翻译
     * 确定四个操作及顺序；第一个 removeMin() 已给出。哪些元素消失、新增，
     * 或先删除后重新加入？
     *
     * My answer / 我的答案：TODO
     */
    public static List<String> operations() {
        // TODO: Return all four operations as strings.
        throw new UnsupportedOperationException("TODO: infer heap operations");
    }

    /*
     * 3b - English - Official question
     * Fill in the following comparisons with either >, <, or ? if unknown. We
     * recommend considering which elements were compared to reach the final
     * array.
     *
     * 1. X ______ D
     * 2. X ______ C
     * 3. B ______ C
     * 4. G ______ X
     *
     * 3b - 中文翻译
     * 用 >、< 或表示无法确定的 ? 填空；请考虑为了得到最终数组，哪些元素
     * 实际发生过比较。
     *
     * My answer / 我的答案：TODO
     */
    public static List<Character> comparisons() {
        // TODO: Return the four symbols in question order.
        throw new UnsupportedOperationException("TODO: infer comparisons");
    }
}
