import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Discussion 11, Exam Prep, Question 3 - Solution.
 *
 * Solution overview / 答案概述（非官方摘要）
 * Modify quicksort. Choose a random Bear as pivot to avoid quicksort's
 * worst-case behavior on sorted input. Partition Beds into those less than,
 * equal to, and greater than that pivot Bear. The unique equal Bed becomes the
 * pivot Bed. Use it to partition Bears into less than, equal to, and greater.
 * Match the pivot Bear and Bed at the same index, then recursively process the
 * corresponding smaller groups and larger groups. Random pivots give O(N log N)
 * time on average.
 *
 * 中文解释
 * 随机选熊，用它把床三向分区；唯一匹配床再把熊三向分区。递归处理较小两组，
 * 放入枢轴配对，再递归处理较大两组。全过程只发生熊-床比较。随机枢轴使期望
 * 递归深度为 O(log N)，每层总分区工作为 O(N)。
 */
/*
 * Complete official solution text / 完整官方答案英文
 *
 * Our solution will modify quicksort. Let's begin by choosing a pivot from the
 * Bears list. To avoid quicksort's worst case behavior on a sorted array, we will
 * choose a random Bear as the pivot. Next we will partition the Beds into three
 * groups - those less than, equal to, and greater than the pivot Bear. Next, we
 * select a pivot from the Beds list: the Bed equal to the pivot Bear. Since sizes
 * are unique, exactly one Bed is equal. Next we partition the Bears into three
 * groups - those less than, equal to, and greater than the pivot Bed.
 *
 * Next, we "match" the pivot Bear with the pivot Bed by adding them to the Bears
 * and Beds lists at the same index. Finally, as in quicksort, we make two
 * recursive calls. The first contains Beds and Bears less than their respective
 * pivots. The second contains Beds and Bears greater than their respective pivots.
 */
public class Dis11ExamPrepQ3BearsAndBedsSolution {
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

    public record Matching(List<Bear> bears, List<Bed> beds) {
    }

    public static Matching match(List<Bear> bears, List<Bed> beds) {
        if (bears.size() != beds.size()) {
            throw new IllegalArgumentException("Bear and Bed counts must match");
        }
        List<Bear> matchedBears = new ArrayList<>();
        List<Bed> matchedBeds = new ArrayList<>();
        matchRecursive(new ArrayList<>(bears), new ArrayList<>(beds),
                matchedBears, matchedBeds, new Random(61_011L));
        return new Matching(matchedBears, matchedBeds);
    }

    private static void matchRecursive(List<Bear> bears, List<Bed> beds,
                                       List<Bear> matchedBears,
                                       List<Bed> matchedBeds,
                                       Random random) {
        if (bears.isEmpty()) {
            if (!beds.isEmpty()) {
                throw new IllegalArgumentException("Unmatched Bed");
            }
            return;
        }

        Bear pivotBear = bears.get(random.nextInt(bears.size()));
        List<Bed> smallerBeds = new ArrayList<>();
        List<Bed> largerBeds = new ArrayList<>();
        Bed pivotBed = null;
        for (Bed bed : beds) {
            int comparison = bed.compareTo(pivotBear);
            if (comparison < 0) {
                smallerBeds.add(bed);
            } else if (comparison > 0) {
                largerBeds.add(bed);
            } else if (pivotBed == null) {
                pivotBed = bed;
            } else {
                throw new IllegalArgumentException("Bed sizes must be unique");
            }
        }
        if (pivotBed == null) {
            throw new IllegalArgumentException("Pivot Bear has no matching Bed");
        }

        List<Bear> smallerBears = new ArrayList<>();
        List<Bear> largerBears = new ArrayList<>();
        Bear equalBear = null;
        for (Bear bear : bears) {
            int comparison = bear.compareTo(pivotBed);
            if (comparison < 0) {
                smallerBears.add(bear);
            } else if (comparison > 0) {
                largerBears.add(bear);
            } else if (equalBear == null) {
                equalBear = bear;
            } else {
                throw new IllegalArgumentException("Bear sizes must be unique");
            }
        }

        if (smallerBears.size() != smallerBeds.size()
                || largerBears.size() != largerBeds.size()) {
            throw new IllegalArgumentException("Bear and Bed sizes do not match");
        }
        matchRecursive(smallerBears, smallerBeds, matchedBears, matchedBeds, random);
        matchedBears.add(equalBear);
        matchedBeds.add(pivotBed);
        matchRecursive(largerBears, largerBeds, matchedBears, matchedBeds, random);
    }

    public static void main(String[] args) {
        List<Bear> bears = List.of(
            new Bear("B4", 4), new Bear("B1", 1),
            new Bear("B3", 3), new Bear("B2", 2));
        List<Bed> beds = List.of(
            new Bed("D2", 2), new Bed("D4", 4),
            new Bed("D1", 1), new Bed("D3", 3));
        Matching result = match(bears, beds);
        if (result.bears().size() != 4 || result.beds().size() != 4) {
            throw new AssertionError("Wrong number of matches");
        }
        for (int i = 0; i < result.bears().size(); i += 1) {
            if (result.bears().get(i).compareTo(result.beds().get(i)) != 0) {
                throw new AssertionError("Mismatched pair at index " + i);
            }
        }
    }
}
