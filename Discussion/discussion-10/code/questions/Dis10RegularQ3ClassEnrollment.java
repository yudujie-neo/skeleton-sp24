package questions;

import java.util.List;

/** CS 61B Spring 2024 - Discussion 10, Regular Question 3. */
public class Dis10RegularQ3ClassEnrollment {

    /*
     * English - Official question preamble
     * You're planning your CS classes for the upcoming semesters, but it's
     * hard to keep track of all the prerequisites! Let's figure out a valid
     * ordering of the classes you're interested in. A valid ordering is an
     * ordering of classes such that every prerequisite of a class is taken
     * before the class itself. Assume we're taking one CS class per semester.
     *
     * 中文翻译
     * 规划未来学期的 CS 课程顺序，使每门课的所有先修课都先完成。假设
     * 每学期只修一门 CS 课程。
     */

    /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
    public record Prerequisite(String prerequisite, String course) {
    }

    /*
     * 3a - English - Official question
     * The list of prerequisites for each course is given below (not
     * necessarily accurate to actual courses!). Draw a graph to represent our
     * scenario.
     * CS 61A: None; CS 61B: CS 61A; CS 61C: CS 61B; CS 70: None;
     * CS 170: CS 61B, CS 70; CS 161: CS 61C, CS 70.
     *
     * 3a - 中文翻译
     * 根据上述课程与先修课列表画图。用“先修课 -> 后续课程”的有向边
     * 表示约束。官方图继续保留在
     * ../../assets/regular-q3a-prerequisite-graph.png。
     *
     * Your answer / 你的答案：TODO
     */
    public static final List<String> COURSES =
            List.of("CS 61A", "CS 61B", "CS 61C", "CS 70", "CS 170", "CS 161");
    public static final List<Prerequisite> ORIGINAL_PREREQUISITES = List.of(
            new Prerequisite("CS 61A", "CS 61B"),
            new Prerequisite("CS 61B", "CS 61C"),
            new Prerequisite("CS 61B", "CS 170"),
            new Prerequisite("CS 70", "CS 170"),
            new Prerequisite("CS 61C", "CS 161"),
            new Prerequisite("CS 70", "CS 161")
    );

    /*
     * 3b - English - Official question
     * Suppose we added a new prerequisite where the student must take CS 161
     * before CS 170 and CS 170 before CS 61C. Is there still a valid ordering
     * of classes such that no prerequisites are broken? If no, explain.
     *
     * 3b - 中文翻译
     * 新增“CS 161 先于 CS 170”以及“CS 170 先于 CS 61C”。是否仍有
     * 合法顺序？若没有，请解释。官方图继续保留在
     * ../../assets/regular-q3b-cycle-graph.png。
     *
     * Your answer / 你的答案：TODO
     */
    public static final List<Prerequisite> NEW_PREREQUISITES = List.of(
            new Prerequisite("CS 161", "CS 170"),
            new Prerequisite("CS 170", "CS 61C")
    );

    /*
     * 3c - English - Official question
     * With the original graph, perform a topological sort to find a valid
     * ordering of the 6 classes. Break ties by going to the lower course
     * number first.
     *
     * 3c - 中文翻译
     * 对原始图执行拓扑排序，给出六门课的合法顺序；平局时先访问课程
     * 编号较小者。
     *
     * Your answer / 你的答案
     * DFS postorder / DFS 后序：TODO
     * Reversed postorder / 逆后序：TODO
     */
    public static List<String> topologicalSort() {
        // TODO: Implement or trace the requested topological sort.
        throw new UnsupportedOperationException("TODO: topologicalSort");
    }

    private Dis10RegularQ3ClassEnrollment() {
    }
}
