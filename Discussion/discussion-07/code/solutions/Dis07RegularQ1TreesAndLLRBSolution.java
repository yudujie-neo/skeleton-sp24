package solutions;

import java.util.List;

/** Official solutions and supplemental Chinese analysis for Discussion 07 Regular Q1. */
public class Dis07RegularQ1TreesAndLLRBSolution {

    /*
     * 1a - Official solution / 官方答案
     * The official solution is the five-step 2-3-tree drawing in
     * ../../assets/regular-q1a-insertions.png. The figure remains authoritative
     * because link and node placement are part of the answer.
     *
     * 中文说明（非官方答案）：最终根结点为 [8, 14]；三个子树根依次为
     * [4, 6]、12、18，最右叶结点为 [20, 38]。
     */
    public static final int[] INSERTION_ORDER = {18, 38, 12, 13, 20};

    /*
     * 1b - Official solution / 官方答案
     * The official converted LLRB is shown in
     * ../../assets/regular-q1b-llrb.png.
     *
     * 中文说明（非官方答案）：每个 3-node [a, b] 变为黑色 b 与其红色左
     * 孩子 a；普通 2-node 变为单个黑结点。
     */

    /*
     * 1c - Official solution / 官方答案
     * 2H + 2 comparisons.
     *
     * The maximum number of comparisons occur from a root to leaf path with
     * the most nodes. Because the height of the tree is H, we know that there
     * is a path down the leaf-leaning red-black tree that consists of at most
     * H black links, for black links in the left-leaning red-black tree are
     * the links that add to the height of the corresponding 2-3 tree. This
     * means that there are H + 1 nodes on the path from the root to the leaf,
     * since there is one less link than nodes.
     *
     * In the worst case, in the 2-3 tree representation, this path can consist
     * entirely of nodes with two items, meaning in the left-leaning red-black
     * tree representation, each blank link is followed by a red link. This
     * doubles the amount of nodes on this path from the root to the leaf.
     *
     * This example will represent our longest path, which is 2H + 2 nodes
     * long, meaning we make at most 2H + 2 comparisons in the left-leaning
     * red-black tree.
     *
     * Supplemental analysis / 补充分析（非官方答案）：官方原文中的
     * "leaf-leaning" 和 "blank link" 应分别理解为 "left-leaning" 和
     * "black link"；这里没有静默修改官方措辞。
     */
    public static int maximumComparisons(int height) {
        if (height < 0) {
            throw new IllegalArgumentException("height must be nonnegative");
        }
        return 2 * height + 2;
    }

    /*
     * 1d - Official solution / 官方答案
     * Remember that we always insert elements as leaf nodes with red links,
     * so after initial insertion, the tree looks like the first state in the
     * official figure.
     *
     * Now there is a right-leaning red link, so we will have to rotateLeft(8).
     * With the previous rotation, we now have two consecutive red left links,
     * which makes our tree unbalanced. To fix, we rotateRight(10).
     * Next, we have red links on both the left and the right, which can be
     * fixed with a colorFlip(9).
     * Finally, we still have red links on both the left and the right, which
     * can be fixed with a colorFlip(7).
     *
     * Supplemental analysis / 补充分析（非官方答案）：题目使用 colorSwap，
     * Solutions 使用 colorFlip；两处都指颜色翻转操作。
     */
    public static final List<String> BALANCING_OPERATIONS = List.of(
            "rotateLeft(8)",
            "rotateRight(10)",
            "colorFlip(9)",
            "colorFlip(7)"
    );

    /** Smoke test / 冒烟测试（非官方答案）。 */
    public static void main(String[] args) {
        if (maximumComparisons(0) != 2 || maximumComparisons(4) != 10) {
            throw new AssertionError("The 2H + 2 comparison formula is incorrect");
        }
        if (!BALANCING_OPERATIONS.get(0).equals("rotateLeft(8)")) {
            throw new AssertionError("Unexpected first balancing operation");
        }
    }
}
