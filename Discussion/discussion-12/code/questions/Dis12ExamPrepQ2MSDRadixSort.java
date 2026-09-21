package questions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * CS 61B Spring 2024 - Discussion 12, Exam Prep Question 2.
 * MSD Radix Sort / MSD 基数排序
 */
public class Dis12ExamPrepQ2MSDRadixSort {

    /*
     * English - Official question
     * Recursively implement the method msd below, which runs MSD radix sort on
     * a List of Strings and returns a sorted List of Strings. For simplicity,
     * assume that each string is of the same length. You may not need all of
     * the lines below.
     *
     * In lecture, recall that we used counting sort as the subroutine for MSD
     * radix sort, but any stable sort works! For the subroutine here, you may
     * use the stableSort method, which sorts the given list of strings in
     * place, comparing two strings by the given index. Finally, you may find
     * following methods of the List class helpful:
     *
     * 1. List<E> subList(int fromIndex, int toIndex). Returns the portion of
     *    this list between the specified fromIndex, inclusive, and toIndex,
     *    exclusive.
    * 2. addAll(Collection<? extends E> c). Appends all of the elements in the
    *    specified collection to the end of this list, in the order that they
    *    are returned by the specified collection's iterator.
     *
     * Official fill-in template / 官方填空模板
     *
     * public static List<String> msd(List<String> items) {
     *     return ____________________________________________________________;
     * }
     *
     * private static List<String> msd(List<String> items, int index) {
     *     if (______________________________________________________________) {
     *         return items;
     *     }
     *     List<String> answer = new ArrayList<>();
     *     int start = 0;
     *     _________________________________________________________________;
     *     for (int end = 1; end <= items.size(); end += 1) {
     *         if (_________________________________________________________) {
     *             _________________________________________________________;
     *             _________________________________________________________;
     *             _________________________________________________________;
     *         }
     *     }
     *     return answer;
     * }
     *
     * /* Sorts the strings in `items` by their character at the `index` index
     *  * alphabetically. * /
     * private static void stableSort(List<String> items, int index) {
     *     // Implementation not shown
     * }
     *
     * 中文翻译
     * 递归实现 msd 方法，对字符串列表执行 MSD 基数排序并返回有序列表。
     * 假设所有字符串等长，且不一定需要使用所有给出的代码行。课堂中使用
     * 计数排序作为子程序，但任何稳定排序都可以。这里可调用 stableSort，
     * 它按指定字符索引原地稳定排序。还可使用 subList 取得左闭右开的列表
     * 视图，并用 addAll 按迭代顺序追加集合元素。
     */

    /*
     * Official fill-in locations / 官方填空位置：
     * - public msd return expression
     * - recursive base-case condition
     * - stableSort call
     * - bucket-boundary condition
     * - subList extraction
     * - recursive addAll call
     * - start update
     */

    /**
     * Your answer / 你的答案.
     *
     * <p>TODO: Replace the neutral compilable scaffold with the recursive MSD
     * implementation requested above.</p>
     *
     * <p>Compilation scaffold / 编译脚手架（非官方题目内容）：the initial
     * implementation returns a mutable copy so this Question file compiles
     * before the student fills in the answer. It does not sort the data.</p>
     */
    public static List<String> msd(List<String> items) {
        return new ArrayList<>(items);
    }

    /**
     * Compilation scaffold / 编译脚手架（非官方题目内容）。
     * TODO: Use this helper recursively in the completed answer.
     */
    private static List<String> msd(List<String> items, int index) {
        return new ArrayList<>(items);
    }

    /* Sorts the strings in `items` by their character at the `index` index alphabetically. */
    /**
     * Compilation scaffold / 编译脚手架（非官方题目内容）。
     * The official worksheet says "Implementation not shown"; this stable
     * library sort supplies the promised subroutine without solving msd.
     */
    private static void stableSort(List<String> items, int index) {
        items.sort(Comparator.comparingInt(item -> item.charAt(index)));
    }

    private Dis12ExamPrepQ2MSDRadixSort() {
        // Utility class.
    }
}
