package questions;

import java.util.List;
import java.util.Set;

/** CS 61B Spring 2024 - Discussion 10, Exam-Level Question 3. */
public class Dis10ExamPrepQ3Wordsearch {

    /*
     * English - Official question
     * Given an N by N wordsearch and N words, devise an algorithm (using
     * pseudocode or describe it in plain English) to solve the wordsearch in
     * O(N^3). For simplicity, assume no word is contained within another, i.e.
     * if the word "bear" is given, "be" wouldn't also be given.
     *
     * If you are unfamiliar with wordsearches or want to gain some wordsearch
     * solving intuition, see below for an example wordsearch. Note that the
     * below wordsearch doesn't follow the precise specification of an N by N
     * wordsearch with N words, but your algorithm should work on this
     * wordsearch regardless.
     *
     * Hint: Add the words to a Trie. The longestPrefixOf operation may help.
     * Recall that longestPrefixOf accepts a String key and returns the longest
     * prefix of key that exists in the Trie, or null if no prefix exists.
     *
     * 中文翻译
     * 给定 N*N 单词搜索方格和 N 个单词，设计 O(N^3) 算法。假设不存在
     * 一个目标词包含另一个目标词的情形。示例方格不严格满足 N*N 方格配
     * N 个单词，但算法仍应适用。提示：将单词加入 Trie，并考虑使用
     * longestPrefixOf；若不存在前缀，提示约定返回 null。
     *
     * Your answer / 你的答案
     * Algorithm / 算法：TODO
     * Runtime derivation / 复杂度推导：TODO
     * The official example image remains in
     * ../../assets/exam-q3-wordsearch.png.
     */

    /**
     * Compilation scaffold / 编译脚手架（非官方题目内容）。
     * This transcribes the official example image as input data; it is not a
     * precomputed answer.
     */
    public static final char[][] EXAMPLE_GRID = {
        "CMUHOSAED".toCharArray(),
        "TRATHANKA".toCharArray(),
        "OCYESRTUT".toCharArray(),
        "NIRSAIOLS".toCharArray(),
        "YRRMTNNHR".toCharArray(),
        "YEAEVARUE".toCharArray(),
        "AAAIMELCR".toCharArray(),
        "NHDJYUACI".toCharArray(),
        "TYSAARSUC".toCharArray(),
        "ARSIGYESA".toCharArray()
    };

    public static final List<String> EXAMPLE_WORDS = List.of(
            "ajay", "crystal", "grace", "luke", "rica", "sherry", "sohum", "tony",
            "anton", "eric", "isha", "naama", "sarina", "shreyas", "sumer", "vidya"
    );

    public static Set<String> solve(char[][] grid, List<String> words) {
        // TODO: Implement the requested O(N^3) wordsearch algorithm.
        throw new UnsupportedOperationException("TODO: solve wordsearch");
    }

    private Dis10ExamPrepQ3Wordsearch() {
    }
}
