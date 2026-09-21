package solutions;

import java.util.Arrays;

/** Official solution and executable implementation for Discussion 12 Exam Prep Q3. */
public class Dis12ExamPrepQ3ShuffledExamsSolution {

    /*
     * Official solution / 官方答案
     * Let's begin by creating an ExamWrapper class that contains two attributes
     * — an Exam instance and the index of the corresponding Exam in the Exams
     * array. Next, for each Exam, create the corresponding ExamWrapper instance.
     *
     * Run radix sort on the ExamWrappers, sorting them on the sid of the Exam
     * instances. Similarly run radix sort on the list of Students, sorting them
     * on sid as well. Note that both iterations of radix sort take linear time
     * since the sid is of fixed length and of base 10.
     *
     * At this point in the algorithm, we have ”completed” the hint, but we still
     * need move the ith Student to its proper place relative to the original
     * Exams array. To acheive this, for the i-th Student, we will access the ith
     * ExamWrapper, and set the index of the i-th Student as the ExamWrapper's
     * index attribute.
     *
     * 中文解析：包装器把每份试卷的 sid 与原始数组下标绑定。按 sid 对包装
     * 器和学生分别执行固定长度、十进制的基数排序后，第 i 个学生与第 i 个
     * 包装器对应同一 sid。随后把该学生放到包装器记录的原下标。
     *
     * Supplemental analysis / 补充分析（非官方答案）：
     * 官方末句写成 “set the index of the i-th Student as the ExamWrapper's
     * index attribute”。但题目说明 Student 只有 sid 属性，因此更合理的算法
     * 含义是把第 i 个已排序学生写入 Students[wrapper.index]，而不是给
     * Student 设置不存在的 index 字段。下面的可执行实现采用前一种解释。
     */

    public static final class Exam {
        public final int sid;

        public Exam(int sid) {
            requireNonnegativeSid(sid);
            this.sid = sid;
        }
    }

    public static final class Student {
        public final int sid;

        public Student(int sid) {
            requireNonnegativeSid(sid);
            this.sid = sid;
        }
    }

    private static final class ExamWrapper {
        private final Exam exam;
        private final int originalIndex;

        private ExamWrapper(Exam exam, int originalIndex) {
            this.exam = exam;
            this.originalIndex = originalIndex;
        }
    }

    /**
     * Reorders {@code students} in place and also returns that same array.
     * The ten decimal passes are constant because a Java int has at most ten
     * base-10 digits, so the total running time is O(N).
     */
    public static Student[] reorderStudents(Exam[] exams, Student[] students) {
        if (exams.length != students.length) {
            throw new IllegalArgumentException("array lengths must match");
        }

        ExamWrapper[] wrappers = new ExamWrapper[exams.length];
        for (int i = 0; i < exams.length; i += 1) {
            wrappers[i] = new ExamWrapper(exams[i], i);
        }

        Student[] sortedStudents = Arrays.copyOf(students, students.length);
        radixSortWrappers(wrappers);
        radixSortStudents(sortedStudents);

        Student[] restored = new Student[students.length];
        for (int i = 0; i < sortedStudents.length; i += 1) {
            if (wrappers[i].exam.sid != sortedStudents[i].sid) {
                throw new IllegalArgumentException("exam and student sid sets must match");
            }
            restored[wrappers[i].originalIndex] = sortedStudents[i];
        }
        System.arraycopy(restored, 0, students, 0, students.length);
        return students;
    }

    private static void radixSortWrappers(ExamWrapper[] values) {
        ExamWrapper[] output = new ExamWrapper[values.length];
        int divisor = 1;
        for (int pass = 0; pass < 10; pass += 1) {
            int[] counts = new int[10];
            for (ExamWrapper value : values) {
                counts[(value.exam.sid / divisor) % 10] += 1;
            }
            accumulate(counts);
            for (int i = values.length - 1; i >= 0; i -= 1) {
                int digit = (values[i].exam.sid / divisor) % 10;
                output[--counts[digit]] = values[i];
            }
            System.arraycopy(output, 0, values, 0, values.length);
            if (pass < 9) {
                divisor *= 10;
            }
        }
    }

    private static void radixSortStudents(Student[] values) {
        Student[] output = new Student[values.length];
        int divisor = 1;
        for (int pass = 0; pass < 10; pass += 1) {
            int[] counts = new int[10];
            for (Student value : values) {
                counts[(value.sid / divisor) % 10] += 1;
            }
            accumulate(counts);
            for (int i = values.length - 1; i >= 0; i -= 1) {
                int digit = (values[i].sid / divisor) % 10;
                output[--counts[digit]] = values[i];
            }
            System.arraycopy(output, 0, values, 0, values.length);
            if (pass < 9) {
                divisor *= 10;
            }
        }
    }

    private static void accumulate(int[] counts) {
        for (int i = 1; i < counts.length; i += 1) {
            counts[i] += counts[i - 1];
        }
    }

    private static void requireNonnegativeSid(int sid) {
        if (sid < 0) {
            throw new IllegalArgumentException("sid must be nonnegative");
        }
    }

    public static void main(String[] args) {
        Exam[] exams = {new Exam(42), new Exam(7), new Exam(105), new Exam(19)};
        int[] originalExamSids = Arrays.stream(exams).mapToInt(exam -> exam.sid).toArray();
        Student[] students = {
            new Student(19), new Student(42), new Student(7), new Student(105)
        };

        Student[] returned = reorderStudents(exams, students);
        if (returned != students) {
            throw new AssertionError("Expected in-place reordering");
        }
        for (int i = 0; i < exams.length; i += 1) {
            if (exams[i].sid != students[i].sid) {
                throw new AssertionError("Student does not match exam at index " + i);
            }
        }
        int[] afterExamSids = Arrays.stream(exams).mapToInt(exam -> exam.sid).toArray();
        if (!Arrays.equals(originalExamSids, afterExamSids)) {
            throw new AssertionError("The exams array must remain unchanged");
        }
    }
}
