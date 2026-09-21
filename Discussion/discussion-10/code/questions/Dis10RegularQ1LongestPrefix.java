package questions;

import java.util.HashMap;
import java.util.Map;

/** CS 61B Spring 2024 - Discussion 10, Regular Question 1. */
public class Dis10RegularQ1LongestPrefix {

    /*
     * English - Official question
     * Fill in the longestPrefixOf(String word) method below such that it
     * returns the longest prefix of word that is also a prefix of a key in the
     * trie.
     *
     * For example, if a TrieSet t contains keys {"cryst", "tries", "cr"},
     * then t.longestPrefixOf("crystal") returns "cryst" and
     * t.longestPrefixOf("crys") returns "crys".
     *
     * The code uses the StringBuilder class to build strings
     * character-by-character. To add a character to the end of the
     * StringBuilder, use append(char c). Once all characters have been
     * appended, the resulting String is returned by toString().
     *
     * 中文翻译
     * 补全 longestPrefixOf(String word)，使其返回 word 的最长前缀，且该
     * 前缀也是 Trie 中某个键的前缀。例如，Trie 含有
     * {"cryst", "tries", "cr"} 时，查询 "crystal" 返回 "cryst"，
     * 查询 "crys" 返回 "crys"。使用 StringBuilder.append(char) 逐字符
     * 构造答案，最后调用 toString()。
     *
     * Your answer / 你的答案
     * Complete longestPrefixOf below. / 补全下方方法。
     */

    /*
     * Official examples and code template / 官方示例与代码模板
     *
     * StringBuilder sb = new StringBuilder();
     * sb.append('a');
     * sb.append('b');
     * System.out.println(sb.toString()); // "ab"
     *
     * public class TrieSet {
     *     private Node root;
     *     private class Node {
     *         boolean isKey;
     *         Map<Character, Node> map;
     *
     *         private Node() {
     *             isKey = false;
     *             map = new HashMap<>();
     *         }
     *     }
     *
     *     public String longestPrefixOf(String word) {
     *         int n = word.length();
     *         StringBuilder prefix = new StringBuilder();
     *         Node curr = _________________________;
     *
     *         for (_____________________________________________) {
     *             _____________________________________________________
     *             _____________________________________________________
     *             _____________________________________________________
     *             _____________________________________________________
     *             _____________________________________________________
     *             _____________________________________________________
     *         }
     *
     *         return ______________________________________________
     *     }
     * }
     */

    /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
    public static final class TrieSet {
        private final Node root = new Node();

        private static final class Node {
            private boolean isKey;
            private final Map<Character, Node> map = new HashMap<>();
        }

        /** Compilation scaffold for constructing a trie; not part of Q1. */
        public void add(String key) {
            Node current = root;
            for (int i = 0; i < key.length(); i += 1) {
                current = current.map.computeIfAbsent(key.charAt(i), c -> new Node());
            }
            current.isKey = true;
        }

        public String longestPrefixOf(String word) {
            // TODO: Replace this scaffold with your implementation.
            throw new UnsupportedOperationException("TODO: longestPrefixOf");
        }
    }

    private Dis10RegularQ1LongestPrefix() {
    }
}
