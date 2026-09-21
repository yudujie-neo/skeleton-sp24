import java.util.Comparator;

/**
 * Discussion 11, Regular, Question 2 - Crystal Has Been Waiting For This.
 *
 * Question overview / 题目概述（非官方摘要）
 * Claire and Ada, two alumni 61B TAs, are trying to sort the TAs by height so
 * they can snap a photo. Can you help them out?
 *
 * 2a. Implement a TAComparator such that it compares two TAs' height. Recall
 * that compare returns a negative number when o1 is "less than" o2, a positive
 * number when o1 is "greater than" o2, and 0 when they are the same.
 *
 * 2b. Anniyat suggests that we use Quicksort with our comparator. Given the
 * supplied list of TAs, who would make the worst pivot? What about the best?
 *
 * 2c. Austin got in line after Jasmine but ended up in front of Jasmine in the
 * Quicksort result. How might we ensure that Austin ends up behind Jasmine?
 *
 * 2d. The TAs have been sorted by height, but Elisa and Wilson arrive late.
 * Which sort will do the most minimal work to put them in the correct spots,
 * and what additional runtime will it take (excluding the original sort)?
 *
 * English - Official question / 英文原题
 * Claire and Ada, two alumni 61B TAs, are trying to sort the TAs by height so
 * they can snap a photo. Can you help them out?
 *
 * 2a. Implement a TAComparator below such that it compares two TAs' height.
 * Recall that a Comparator's compare method returns a negative number when o1
 * is "less than" o2, positive number when o1 is "greater than" o2, and 0 when
 * they are the same.
 *
 * 2b. Anniyat suggests that we use Quicksort with our comparator. Given the
 * following list of TAs, who would make the worst pivot? What about the best
 * pivot?
 *
 * 2c. Austin points out that even though he got in line after Jasmine, he ended
 * up in front of Jasmine in the sorted list produced by Quicksort (which he
 * doesn't like because that makes it seem like he's shorter than Jasmine)! How
 * might we ensure that Austin ends up behind Jasmine?
 *
 * 2d. Our TAs have just been sorted by height, but suddenly Elisa and Wilson
 * come running in late! Which sort will do the most minimal work to get them in
 * their correct spots, and what is the additional runtime it will take (i.e. not
 * including the runtime for sorting all the other TAs first)?
 *
 * 中文翻译
 * Claire 和 Ada 想按身高排列 TA。2a 实现身高比较器；2b 判断给定列表中
 * 最差和最好的快速排序枢轴；2c 说明如何保持 Jasmine 与 Austin 这两个等高
 * 元素的原始顺序；2d 说明两名 TA 迟到后应使用哪种排序以及额外运行时间。
 */
public class Dis11RegularQ2TAComparator {
    /** Official given code / 官方给定代码。 */
    public static class TA {
        private String name;
        private int height;

        public TA(String name, int height) {
            this.name = name;
            this.height = height;
        }

        /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Compilation scaffold / 编译脚手架（非官方题目内容）.
     * Nesting TAComparator in the same top-level class lets the official private
     * fields coexist with a separately compilable exercise file.
     */
    public static class TAComparator implements Comparator<TA> {
        @Override
        public int compare(TA o1, TA o2) {
            // TODO 2a / 你的答案：比较 o1 与 o2 的身高。
            throw new UnsupportedOperationException("TODO: implement height comparison");
        }
    }

    /** Official data for 2b / 2b 官方数据。 */
    public static TA[] tas() {
        return new TA[] {
            new TA("Eddie", 6), new TA("Ronnie", 9001),
            new TA("Aditya", 1), new TA("Elana", 5),
            new TA("Sree", 7), new TA("Noah", 25),
            new TA("Dhruti", 9), new TA("William", 4),
            new TA("Jasmine", 8), new TA("Austin", 8)
        };
    }

    /*
     * My answer / 我的答案
     * TODO 2b: Worst pivot(s) / Best pivot(s):
     * TODO 2c: How to preserve Jasmine before Austin:
     * TODO 2d: Sort and additional runtime:
     */
}
