package solutions;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Official algorithm and executable check for Discussion 10 Exam-Level Q3. */
public class Dis10ExamPrepQ3WordsearchSolution {
    private static final int[][] DIRECTIONS = {
        {-1, 0}, {-1, 1}, {0, 1}, {1, 1},
        {1, 0}, {1, -1}, {0, -1}, {-1, -1}
    };

    /*
     * Official solution / 官方答案
     * Algorithm: Begin by adding all the words we are querying for into a
     * Trie. Next, we will iterate through each letter in the wordsearch and see
     * if any words start with that letter. For a word to start with a given
     * letter, note that it can go in one of eight directions - N, NE, E, SE, S,
     * SW, W, NW.
     *
     * Looking at each direction, we will check if the string going in that
     * direction has a prefix that exists in our Trie, which we can do using
     * longestPrefixOf. Note that words are not nested inside of others, so at
     * most one word can start from a given letter in a given direction. As
     * such, if longestPrefixOf returns a word, we know it is the only word that
     * goes in that direction from that letter.
     *
     * For instance, if we are at the letter "S" in the middle of the top row
     * of the wordsearch above and are considering the direction west, we would
     * want to see if the string "SOHUMC" has a prefix that exists in the given
     * wordsearch. To efficiently perform this query, we call
     * longestPrefixOf("SOHUMC"), which, in this case, returns "SOHUM", and we
     * proceed by removing "SOHUM" from our Trie to signal that we found the
     * word "SOHUM".
     *
     * We will repeat this process until the all the words have been found,
     * i.e. when the Trie is empty. Finally, note that this is a very open ended
     * problem, so this is one of many possible solutions.
     *
     * Runtime: We look at N^2 letters. At each letter, we execute eight calls
     * to longestPrefixOf which runs in time linear to the length of the
     * inputted string, which can be of at most length N, since that is the
     * height and width of the wordsearch. Thus, if we perform on the order of N
     * work per letter and we look at N^2 letters, the runtime is O(N^3).
     *
     * 中文解释：把目标词加入 Trie；对每个格子沿八个方向读到边界，并查询
     * Trie 前缀。共有 N^2 个起点、常数个方向、每次最多 N 个字符，因此
     * 总时间 O(N^3)。
     *
     * Supplemental analysis / 补充分析（非官方答案）
     * The Exam-Level hint says no match returns null, while Regular Q1's
     * official implementation returns "". An implementation must choose one
     * convention consistently.
     *
     * Regular Q1 returns the longest string that remains a prefix of some key;
     * that string need not itself be a complete key. For example, with only
     * "bear" stored, querying "beaX" returns "bea". Therefore executable code
     * must also check contains(result), or inspect isKey, before reporting and
     * removing a word. The no-nested-words assumption does not eliminate this
     * incomplete-prefix case.
     */

    private static final class Trie {
        private final Node root = new Node();

        private static final class Node {
            private boolean isKey;
            private final Map<Character, Node> children = new HashMap<>();
        }

        void add(String word) {
            Node current = root;
            for (int i = 0; i < word.length(); i += 1) {
                char c = Character.toUpperCase(word.charAt(i));
                current = current.children.computeIfAbsent(c, key -> new Node());
            }
            current.isKey = true;
        }

        String longestPrefixOf(String candidate) {
            Node current = root;
            StringBuilder prefix = new StringBuilder();
            for (int i = 0; i < candidate.length(); i += 1) {
                current = current.children.get(candidate.charAt(i));
                if (current == null) {
                    break;
                }
                prefix.append(candidate.charAt(i));
            }
            return prefix.length() == 0 ? null : prefix.toString();
        }

        boolean contains(String word) {
            Node current = root;
            for (int i = 0; i < word.length(); i += 1) {
                current = current.children.get(word.charAt(i));
                if (current == null) {
                    return false;
                }
            }
            return current.isKey;
        }

        void remove(String word) {
            remove(root, word, 0);
        }

        private boolean remove(Node node, String word, int index) {
            if (index == word.length()) {
                node.isKey = false;
            } else {
                char c = word.charAt(index);
                Node child = node.children.get(c);
                if (child != null && remove(child, word, index + 1)) {
                    node.children.remove(c);
                }
            }
            return !node.isKey && node.children.isEmpty();
        }
    }

    public static Set<String> solve(char[][] grid, List<String> words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }
        Set<String> found = new LinkedHashSet<>();
        for (int row = 0; row < grid.length; row += 1) {
            for (int col = 0; col < grid[row].length; col += 1) {
                for (int[] direction : DIRECTIONS) {
                    String candidate = readToBoundary(grid, row, col,
                            direction[0], direction[1]);
                    String prefix = trie.longestPrefixOf(candidate);
                    if (prefix != null && trie.contains(prefix)) {
                        found.add(prefix.toLowerCase());
                        trie.remove(prefix);
                    }
                }
            }
        }
        return found;
    }

    private static String readToBoundary(char[][] grid, int row, int col,
                                         int rowStep, int colStep) {
        StringBuilder result = new StringBuilder();
        int r = row;
        int c = col;
        while (r >= 0 && r < grid.length && c >= 0 && c < grid[r].length) {
            result.append(Character.toUpperCase(grid[r][c]));
            r += rowStep;
            c += colStep;
        }
        return result.toString();
    }

    private static final char[][] EXAMPLE_GRID = {
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
    private static final List<String> EXAMPLE_WORDS = List.of(
            "ajay", "crystal", "grace", "luke", "rica", "sherry", "sohum", "tony",
            "anton", "eric", "isha", "naama", "sarina", "shreyas", "sumer", "vidya"
    );

    public static void main(String[] args) {
        Set<String> found = solve(EXAMPLE_GRID, EXAMPLE_WORDS);
        require(found.contains("sohum"));
        require(found.contains("crystal"));
        require(found.size() == EXAMPLE_WORDS.size());
    }

    private static void require(boolean condition) {
        if (!condition) {
            throw new AssertionError("Discussion 10 Exam-Level Q3 smoke test failed");
        }
    }
}
