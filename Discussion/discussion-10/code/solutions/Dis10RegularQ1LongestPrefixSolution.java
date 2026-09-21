package solutions;

import java.util.HashMap;
import java.util.Map;

/** Official solution and executable checks for Discussion 10 Regular Q1. */
public class Dis10RegularQ1LongestPrefixSolution {

    /*
     * Official solution / 官方答案
     *
     * public String longestPrefixOf(String word) {
     *     int n = word.length();
     *     StringBuilder prefix = new StringBuilder();
     *     Node curr = root;
     *     for (int i = 0; i < n; i++) {
     *         char c = word.charAt(i);
     *
     *         if (!curr.map.containsKey(c)) {
     *             break;
     *         }
     *         curr = curr.map.get(c);
     *         prefix.append(c);
     *     }
     *     return prefix.toString();
     * }
     *
     * 中文解释
     * curr 从根结点开始，依次沿 word 的字符向下查找。只要当前结点存在
     * 对应子结点，就把字符加入 prefix；遇到第一条不存在的边时停止。
     * 题目要求“某个键的前缀”，所以不检查 isKey。若 word 为空或首字符
     * 不存在，返回空字符串。若 HashMap 查询按期望 O(1) 计，时间为
     * O(|word|)，结果字符串占 O(|word|) 额外空间。
     */
    public static final class TrieSet {
        private final Node root = new Node();

        private static final class Node {
            private boolean isKey;
            private final Map<Character, Node> map = new HashMap<>();
        }

        /** Runnable scaffold used only to construct deterministic test data. */
        public void add(String key) {
            Node current = root;
            for (int i = 0; i < key.length(); i += 1) {
                current = current.map.computeIfAbsent(key.charAt(i), c -> new Node());
            }
            current.isKey = true;
        }

        public String longestPrefixOf(String word) {
            int n = word.length();
            StringBuilder prefix = new StringBuilder();
            Node curr = root;
            for (int i = 0; i < n; i++) {
                char c = word.charAt(i);
                if (!curr.map.containsKey(c)) {
                    break;
                }
                curr = curr.map.get(c);
                prefix.append(c);
            }
            return prefix.toString();
        }
    }

    public static void main(String[] args) {
        TrieSet trie = new TrieSet();
        trie.add("cryst");
        trie.add("tries");
        trie.add("cr");
        require(trie.longestPrefixOf("crystal").equals("cryst"));
        require(trie.longestPrefixOf("crys").equals("crys"));
        require(trie.longestPrefixOf("unknown").isEmpty());
    }

    private static void require(boolean condition) {
        if (!condition) {
            throw new AssertionError("Discussion 10 Regular Q1 smoke test failed");
        }
    }
}
