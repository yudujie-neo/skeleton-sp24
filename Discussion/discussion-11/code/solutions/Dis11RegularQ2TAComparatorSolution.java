import java.util.Arrays;
import java.util.Comparator;

/**
 * Discussion 11, Regular, Question 2 - Solution.
 *
 * Solution overview / 答案概述（非官方摘要）
 *
 * 2a. The official comparator returns -1 when o1.height < o2.height, 1 when
 * o1.height > o2.height, and 0 otherwise. The PDF also gives two alternatives:
 * return o1.height - o2.height; and Integer.compare(o1.height, o2.height).
 *
 * 2b. Generally speaking, the worst pivot for Quicksort on a collection is
 * that collection's minimum and maximum values, because the sublists would be
 * partitioned very poorly. The worst pivots are Ronnie (maximum height) and
 * Aditya (minimum height). The best pivot is the median. Since there are an
 * even number of elements, Sree, Jasmine, or Austin would all make good pivots.
 *
 * 2c. If we want to preserve ordering of same-valued elements in the original
 * collection when sorted, we should use a stable sort like insertion sort or
 * merge sort. Technically, a stable Quicksort is possible, but we generally do
 * not use that version.
 *
 * 2d. Insertion sort is most efficient on an already-sorted or nearly-sorted
 * list. Tack Elisa and Wilson onto the end and run insertion sort starting with
 * them. At worst, we make two linear passes, so the additional runtime is
 * Theta(N).
 *
 * 中文解释
 * 极值是最差枢轴，接近中位数的元素是较好枢轴。相等身高的原顺序需要稳定排序。
 * 已排序队列追加两个元素后，从新增位置开始插入，最坏总额外工作仍为线性。
 *
 * Supplemental analysis / 补充分析（非官方答案）
 * The PDF declares height private but shows TAComparator as another public class
 * directly reading o1.height. As separate top-level classes that code does not
 * compile. This file nests both types solely as compilation scaffolding. Also,
 * o1.height - o2.height can overflow; Integer.compare is the robust alternative.
 */
/*
 * Complete official solution text / 完整官方答案英文
 *
 * 2a official code:
 * if (o1.height < o2.height) return -1;
 * else if (o1.height > o2.height) return 1;
 * return 0;
 * Alternatively: return o1.height - o2.height;
 * Or: return Integer.compare(o1.height, o2.height);
 *
 * 2b. Generally speaking, the worst pivot for Quicksort on a collection is that
 * collection's minimum and maximum values, because the sublists would be
 * partitioned very poorly. The worst pivots in the list are therefore Ronnie
 * (maximum height) and Aditya (minimum height). On the other hand, the best pivot
 * for Quicksort on a collection is that collection's median value. Since there
 * are an even number of elements, we would say that Sree, Jasmine, or Austin
 * would all make good pivots.
 *
 * 2c. If we want to preserve ordering of same-valued elements in the original
 * collection when sorted, we should use a stable sort like insertion sort or
 * merge sort! Technically speaking, there is a stable Quicksort possible, but we
 * generally don't use that version.
 *
 * 2d. Insertion sort: it is the most efficient on an already-sorted or
 * nearly-sorted list (i.e. on a completely sorted list, it will make a linear
 * pass and terminate without swaps, unlike Quicksort, mergesort, or heapsort,
 * which would indiscriminately try to sort the list without checking whether it
 * is already sorted). We can tack Elisa and Wilson onto the end of the already-
 * sorted list and run insertion sort starting with them. At worst, we make two
 * linear passes (Elisa and Wilson are the two shortest TAs), so the overall
 * additional runtime is Theta(N).
 */
public class Dis11RegularQ2TAComparatorSolution {
    public static class TA {
        private final String name;
        private final int height;

        public TA(String name, int height) {
            this.name = name;
            this.height = height;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static class TAComparator implements Comparator<TA> {
        @Override
        public int compare(TA o1, TA o2) {
            return Integer.compare(o1.height, o2.height);
        }
    }

    public static TA[] tas() {
        return new TA[] {
            new TA("Eddie", 6), new TA("Ronnie", 9001),
            new TA("Aditya", 1), new TA("Elana", 5),
            new TA("Sree", 7), new TA("Noah", 25),
            new TA("Dhruti", 9), new TA("William", 4),
            new TA("Jasmine", 8), new TA("Austin", 8)
        };
    }

    public static void main(String[] args) {
        TAComparator comparator = new TAComparator();
        if (comparator.compare(new TA("Min", Integer.MIN_VALUE),
                new TA("Max", Integer.MAX_VALUE)) >= 0) {
            throw new AssertionError("Comparator must not overflow");
        }
        TA[] people = tas();
        Arrays.sort(people, comparator);
        String[] expected = {
            "Aditya", "William", "Elana", "Eddie", "Sree",
            "Jasmine", "Austin", "Dhruti", "Noah", "Ronnie"
        };
        for (int i = 0; i < expected.length; i += 1) {
            if (!expected[i].equals(people[i].name)) {
                throw new AssertionError("Unexpected stable order at index " + i);
            }
        }
    }
}
