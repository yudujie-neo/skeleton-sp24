package solutions;

import java.util.List;

/** Official solutions and checkable operation sequences for Discussion 07 Exam Prep Q1. */
public class Dis07ExamPrepQ1LLRBInsertionsSolution {

    /*
     * 1a - Official solution / 官方答案
     * For a visualization of the process, see here:
     * https://docs.google.com/presentation/d/1uWEnm4L2cNTmFNeT4qqATHWRA9tbYbcV78uJRFIZtqw/edit#slide=id.g8336e82847_0_2584
     *
     * 1. Insert 7
     *    - Nothing
     * 2. Insert 6
     *    - rotateRight(9)
     *    - colorFlip(7)
     *    - colorFlip(5)
     * 3. Insert 2
     *    - rotateLeft(1)
     * 4. Insert 8
     *    - Nothing
     * 5. Insert 8.5
     *    - rotateLeft(8)
     *    - rotateRight(9)
     *    - colorFlip(8.5)
     *    - rotateLeft(7)
     * 6. Final state: see ../../assets/exam-q1a-final-llrb.png.
     */
    public static final List<List<String>> OPERATIONS = List.of(
            List.of("Nothing"),
            List.of("rotateRight(9)", "colorFlip(7)", "colorFlip(5)"),
            List.of("rotateLeft(1)"),
            List.of("Nothing"),
            List.of("rotateLeft(8)", "rotateRight(9)", "colorFlip(8.5)", "rotateLeft(7)")
    );

    /*
     * 1b - Official solution / 官方答案
     * The final corresponding 2-3 Tree is shown in
     * ../../assets/exam-q1b-final-23-tree.png.
     *
     * 中文说明（非官方答案）：收缩每条红色左链接；1 与 2 合成 [1, 2]，
     * 7 与 8.5 合成 [7, 8.5]，其余黑链接保持父子关系。
     */

    /** Smoke test / 冒烟测试（非官方答案）。 */
    public static void main(String[] args) {
        if (OPERATIONS.size() != 5
                || !OPERATIONS.get(1).equals(
                List.of("rotateRight(9)", "colorFlip(7)", "colorFlip(5)"))
                || !OPERATIONS.get(4).get(3).equals("rotateLeft(7)")) {
            throw new AssertionError("LLRB balancing sequence mismatch");
        }
    }
}
