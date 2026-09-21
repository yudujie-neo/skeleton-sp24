package solutions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Official solution and runnable scaffold for Discussion 12 Exam Prep Q2. */
public class Dis12ExamPrepQ2MSDRadixSortSolution {

    /*
     * Official solution / 官方答案
     *
     * public static List<String> msd(List<String> items) {
     *     return msd(items, 0);
     * }
     *
     * private static List<String> msd(List<String> items, int index) {
     *     if (items.size() <= 1 || index >= items.get(0).length()) {
     *         return items;
     *     }
     *     List<String> answer = new ArrayList<>();
     *     int start = 0;
     *     stableSort(items, index);
     *     for (int end = 1; end <= items.size(); end += 1) {
     *         if (end == items.size()
     *                 || items.get(start).charAt(index)
     *                 != items.get(end).charAt(index)) {
     *             List<String> subList = items.subList(start, end);
     *             answer.addAll(msd(subList, index + 1));
     *             start = end;
     *         }
     *     }
     *     return answer;
     * }
     *
     * /* Sorts the strings in `items` by their character at the `index` index alphabetically. * /
     * private static void stableSort(List<String> items, int index) {
     *     // Implementation not shown
     * }
     *
     * 中文解析：入口从字符索引 0 开始。每层先按当前字符稳定排序，再用
     * start 和 end 找出相同字符的连续区间；每个区间递归处理下一字符，并
     * 按桶顺序追加到 answer。end == items.size() 必须位于 || 左侧，利用
     * Java 短路求值避免 items.get(end) 越界。
     */

    public static List<String> msd(List<String> items) {
        return msd(items, 0);
    }

    private static List<String> msd(List<String> items, int index) {
        if (items.size() <= 1 || index >= items.get(0).length()) {
            return items;
        }
        List<String> answer = new ArrayList<>();
        int start = 0;
        stableSort(items, index);
        for (int end = 1; end <= items.size(); end += 1) {
            if (end == items.size()
                    || items.get(start).charAt(index) != items.get(end).charAt(index)) {
                List<String> subList = items.subList(start, end);
                answer.addAll(msd(subList, index + 1));
                start = end;
            }
        }
        return answer;
    }

    /**
     * Compilation scaffold / 编译脚手架（非官方答案实现）。
     * The worksheet explicitly says this implementation is not shown and may
     * be treated as a provided stable sorting subroutine.
     */
    private static void stableSort(List<String> items, int index) {
        items.sort(Comparator.comparingInt(item -> item.charAt(index)));
    }

    public static void main(String[] args) {
        List<String> input = new ArrayList<>(List.of("cab", "abc", "caa", "aba", "abb"));
        List<String> expected = List.of("aba", "abb", "abc", "caa", "cab");
        if (!expected.equals(msd(input))) {
            throw new AssertionError("MSD radix sort produced the wrong order");
        }
        if (!msd(new ArrayList<>()).isEmpty()) {
            throw new AssertionError("Empty input should remain empty");
        }
    }
}
