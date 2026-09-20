package questions;

/**
 * CS 61B Spring 2024 - Discussion 07, Exam Prep Question 3.
 * Buggy Hash / 有缺陷的哈希
 */
public class Dis07ExamPrepQ3BuggyHash {

    /*
     * English - Official question
     * The following classes may contain a bug in one of its methods. Identify
     * those errors and briefly explain why they are incorrect and in which
     * situations would the bug cause problems.
     *
     * 中文翻译
     * 下面的类可能在某个方法中包含缺陷。找出错误，简要解释为什么不正确，
     * 并说明什么情况下会造成问题。
     *
     * My answer / 我的答案
     * Timezone：TODO
     * Course：TODO
     */

    static class Timezone {
        String timeZone; // "PST", "EST" etc.
        boolean dayLight;
        String location;

        /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
        Timezone(String timeZone, boolean dayLight, String location) {
            this.timeZone = timeZone;
            this.dayLight = dayLight;
            this.location = location;
        }

        /*
         * Compilation scaffold / 编译脚手架（非官方题目内容）。
         * The worksheet contains an ellipsis and only says this method returns
         * the current time in that time zone. The time-varying behavior is the
         * stated assumption; this body merely makes the file compile.
         */
        public int currentTime() {
            return (int) System.currentTimeMillis();
        }

        public int hashCode() {
            return currentTime();
        }

        public boolean equals(Object o) {
            Timezone tz = (Timezone) o;
            return tz.timeZone.equals(timeZone);
        }
    }

    static class Course {
        int courseCode;
        int yearOffered;
        String[] staff;

        /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
        Course(int courseCode, int yearOffered, String[] staff) {
            this.courseCode = courseCode;
            this.yearOffered = yearOffered;
            this.staff = staff;
        }

        public int hashCode() {
            return yearOffered + courseCode;
        }

        public boolean equals(Object o) {
            Course c = (Course) o;
            return c.courseCode == courseCode;
        }
    }

    /*
     * Source-fidelity note / 来源忠实度说明
     * The Exam-Level question PDF visibly renders line 10 of Timezone as
     * "rn currentTime();". The Exam-Level Solutions PDF renders the complete
     * official line "return currentTime();". This compilable file follows the
     * complete Solutions rendering rather than silently inventing a token.
     *
     * 题目 PDF 中该行可见为 “rn currentTime();”，Solutions PDF 则完整显示
     * “return currentTime();”。这里依据完整的官方 Solutions 版本保留 return。
     */

    private Dis07ExamPrepQ3BuggyHash() {
    }
}
