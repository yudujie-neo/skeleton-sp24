package questions;

import java.util.Arrays;

/**
 * CS 61B Spring 2024 - Discussion 12, Exam Prep Question 3.
 * Shuffled Exams / 被打乱的试卷
 */
public class Dis12ExamPrepQ3ShuffledExams {

    /*
     * English - Official question
     * For this problem, we will be working with Exam and Student objects, both
     * of which have only one attribute: sid, which is a integer like any
     * student ID.
     *
     * PrairieLearn thought it was ready for the final. It had meticulously
     * created two arrays, one of Exams and the other of Students, and ordered
     * both on sid such that the ith Exam in the Exams array has the same sid as
     * the ith Student in the Students array. Note the arrays are not necessarily
     * sorted by sid. However, PrairieLearn crashed, and the Students array was
     * shuffled, but the Exams array somehow remained untouched.
     *
     * Time is precious, so you must design a O(N) time algorithm to reorder the
     * Students array appropriately without changing the Exams array!
     *
     * Hint: While you cannot modify the Exams array, you can sort a copy of the
     * Exams array with some added information. Think about what information
     * would be useful to put back the Students array in the same order as the
     * exams.
     *
     * 中文翻译
     * 本题使用 Exam 和 Student 对象，它们都只有一个整数属性 sid。
     * PrairieLearn 原先创建了试卷数组和学生数组，使相同下标的试卷与学生
     * 具有相同 sid；两个数组本身不一定按 sid 排序。系统崩溃后学生数组被
     * 打乱，而试卷数组保持不变。请设计 O(N) 时间算法恢复学生数组顺序，
     * 同时不能修改试卷数组。提示：可以对带有额外信息的试卷数组副本排序，
     * 思考恢复原下标需要保存什么信息。
     */

    /** Compilation scaffold / 编译脚手架（非官方给定类）。 */
    public static final class Exam {
        public final int sid;

        public Exam(int sid) {
            this.sid = sid;
        }
    }

    /** Compilation scaffold / 编译脚手架（非官方给定类）。 */
    public static final class Student {
        public final int sid;

        public Student(int sid) {
            this.sid = sid;
        }
    }

    /**
     * Your answer / 你的答案.
     *
     * <p>TODO: Implement the requested O(N) algorithm. Explain which fixed-key
     * sorting assumption makes the bound linear, how the original exam indices
     * are retained, and how students are written back.</p>
     *
     * <p>Compilation scaffold / 编译脚手架（非官方题目内容）：the initial
     * body returns a shallow copy of the shuffled input and intentionally does
     * not solve the exercise.</p>
     */
    public static Student[] reorderStudents(Exam[] exams, Student[] shuffledStudents) {
        if (exams.length != shuffledStudents.length) {
            throw new IllegalArgumentException("array lengths must match");
        }
        return Arrays.copyOf(shuffledStudents, shuffledStudents.length);
    }

    private Dis12ExamPrepQ3ShuffledExams() {
        // Utility class.
    }
}
