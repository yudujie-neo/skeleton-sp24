import java.util.List;

/**
 * Discussion 11, Exam Prep, Question 3 - Bears and Beds.
 *
 * English - Official question
 * In this problem, we will see how we can sort "pairs" of things without sorting
 * out each individual entry. The hot new Cal startup AirBearsnBeds has hired you
 * to create an algorithm to help them place their bear customers in the best
 * possible beds to improve their experience. Now, a little known fact about
 * bears is that they are very, very picky about their bed sizes: they do not like
 * their beds too big or too little - they like them just right. Bears are also
 * sensitive creatures who don't like being compared to other bears, but they are
 * perfectly fine with trying out beds.
 *
 * The Problem:
 * Inputs: a list of Bears with unique but unknown sizes and a list of Beds with
 * unique but unknown sizes. These two lists are not necessarily in the same order.
 * Output: a list of Bears and a list of Beds such that the ith Bear is the same
 * size as the ith Bed.
 *
 * Constraints / 限制
 * - Bears can only be compared to Beds, with feedback that the Bed is too large,
 *   too small, or just right.
 * - Beds can only be compared to Bears, with analogous feedback.
 * - The algorithm should run in O(N log N) time on average.
 *
 * 中文翻译
 * 输入是尺寸各不相同且未知的熊列表与床列表，二者顺序可能不同。输出两个列表，
 * 使第 i 只熊与第 i 张床尺寸相同。只允许熊与床之间比较，不允许熊熊或床床比较；
 * 算法平均时间应为 O(N log N)。
 *
 */
public class Dis11ExamPrepQ3BearsAndBeds {
    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    public static final class Bear {
        private final String id;
        private final int secretSize;

        public Bear(String id, int secretSize) {
            this.id = id;
            this.secretSize = secretSize;
        }

        public int compareTo(Bed bed) {
            return Integer.compare(secretSize, bed.secretSize);
        }

        @Override
        public String toString() {
            return id;
        }
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    public static final class Bed {
        private final String id;
        private final int secretSize;

        public Bed(String id, int secretSize) {
            this.id = id;
            this.secretSize = secretSize;
        }

        public int compareTo(Bear bear) {
            return Integer.compare(secretSize, bear.secretSize);
        }

        @Override
        public String toString() {
            return id;
        }
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    public record Matching(List<Bear> bears, List<Bed> beds) {
    }

    public static Matching match(List<Bear> bears, List<Bed> beds) {
        // TODO / 你的答案：只用 Bear-Bed 比较完成随机分区与递归匹配。
        throw new UnsupportedOperationException("TODO: match bears and beds");
    }
}
