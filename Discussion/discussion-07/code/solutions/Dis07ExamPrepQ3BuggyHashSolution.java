package solutions;

import java.util.Objects;

/** Official solution and supplemental corrected implementations for Discussion 07 Exam Prep Q3. */
public class Dis07ExamPrepQ3BuggyHashSolution {

    /*
     * Timezone - Official solution / 官方答案
     * Although equal objects will have the same hashcode, but the problem here
     * is that hashCode() is not deterministic. This may result in weird
     * behaviors (e.g. the element getting lost) when we try to put or access
     * elements.
     *
     * Course - Official solution / 官方答案
     * The problem with this hashCode() is that not all equal objects have the
     * same hashcode. This may produce unexpected behavior, e.g. multiple
     * "equal" objects may exist in different buckets in the HashMap, the
     * containsKey operation may return false, etc. One key thing to remember
     * is that when we override equals(), we have to also override hashCode()
     * to ensure equal objects have the same hashCode.
     *
     * Supplemental analysis / 补充分析（非官方答案）：原类的 equals 都没有
     * 检查 null 或运行时类型，可能抛出 NullPointerException 或
     * ClassCastException。下面的 Fixed 类展示一种完整、健壮且与 equals
     * 一致的实现；它不是官方答案原文。
     */

    static final class TimezoneFixed {
        private final String timeZone;
        private final boolean dayLight;
        private final String location;

        TimezoneFixed(String timeZone, boolean dayLight, String location) {
            this.timeZone = timeZone;
            this.dayLight = dayLight;
            this.location = location;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(timeZone);
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof TimezoneFixed that
                    && Objects.equals(timeZone, that.timeZone);
        }
    }

    static final class CourseFixed {
        private final int courseCode;
        private final int yearOffered;
        private final String[] staff;

        CourseFixed(int courseCode, int yearOffered, String[] staff) {
            this.courseCode = courseCode;
            this.yearOffered = yearOffered;
            this.staff = staff;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(courseCode);
        }

        @Override
        public boolean equals(Object other) {
            return other instanceof CourseFixed that && courseCode == that.courseCode;
        }
    }

    /** Smoke test / 冒烟测试（非官方答案）。 */
    public static void main(String[] args) {
        TimezoneFixed firstZone = new TimezoneFixed("PST", false, "Berkeley");
        TimezoneFixed secondZone = new TimezoneFixed("PST", true, "Seattle");
        if (!firstZone.equals(secondZone) || firstZone.hashCode() != secondZone.hashCode()) {
            throw new AssertionError("Equal timezones must have equal hash codes");
        }

        CourseFixed firstCourse = new CourseFixed(61, 2023, new String[]{"A"});
        CourseFixed secondCourse = new CourseFixed(61, 2024, new String[]{"B"});
        if (!firstCourse.equals(secondCourse) || firstCourse.hashCode() != secondCourse.hashCode()) {
            throw new AssertionError("Equal courses must have equal hash codes");
        }
    }
}
