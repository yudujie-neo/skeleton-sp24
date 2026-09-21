package solutions;

import java.util.Arrays;

/** Official solutions and Chinese explanation for Discussion 12 Regular Q2. */
public class Dis12RegularQ2RadixSortsSolution {

    /*
     * 2a - Official solution / 官方答案
     * The underlined sections denote the digits that have already been sorted.
     *
     * Brackets below mark the underlined digits in the official PDF.
     * Original: 30395 30326 43092 30315
     * Round 1: 4309[2] 3039[5] 3031[5] 3032[6]
     * Round 2: 303[15] 303[26] 430[92] 303[95]
     * Round 3: 43[092] 30[315] 30[326] 30[395]
     * Round 4: 3[0315] 3[0326] 3[0395] 4[3092]
     * Round 5: [30315] [30326] [30395] [43092]
     *
     * 中文解析：LSD 从个位开始，依次按十位、百位、千位、万位执行稳定
     * 计数排序。稳定性保证已经确定的低位顺序在后续轮次中仍被保留。
     */
    public static final int[][] LSD_ROUNDS = {
        {30395, 30326, 43092, 30315},
        {43092, 30395, 30315, 30326},
        {30315, 30326, 43092, 30395},
        {43092, 30315, 30326, 30395},
        {30315, 30326, 30395, 43092},
        {30315, 30326, 30395, 43092}
    };

    /*
     * 2b - Official solution / 官方答案
     * Brackets below mark the underlined digits in the official PDF.
     * Original: 21295 22316 30753 21248 30751
     * Round 1: [2]1295 [2]2316 [2]1248 | [3]0753 [3]0751
     * Round 2: [21]295 [21]248 | [22]316 | [30]753 [30]751
     * Round 3: [212]95 [212]48 | [223]16 | [307]53 [307]51
     * Round 4: [2124]8 | [2129]5 | [2231]6 | [3075]3 [3075]1
     * Round 5: [21248] | [21295] | [22316] | [30751] | [30753]
     *
     * 中文解析：MSD 先按最高位分组，然后只在各组内部递归处理下一位。
     * 竖线表示已经分开的递归桶，不同桶之后不会再混合。
     */
    public static final int[][] MSD_ROUNDS = {
        {21295, 22316, 30753, 21248, 30751},
        {21295, 22316, 21248, 30753, 30751},
        {21295, 21248, 22316, 30753, 30751},
        {21295, 21248, 22316, 30753, 30751},
        {21248, 21295, 22316, 30753, 30751},
        {21248, 21295, 22316, 30751, 30753}
    };

    /*
     * 2c - Official solution / 官方答案
     * Sort             Best                 Worst                Stability
     * LSD Radix Sort   Theta(W(N + R))      Theta(W(N + R))      Yes
     * MSD Radix Sort   Theta(N + R)         Theta(W(N + R))      Yes
     *
     * 中文解析：LSD 必须处理全部 W 位。MSD 若第一轮就把元素分成无需继续
     * 处理的桶，最好只需一轮；最坏情况下许多元素共享长前缀。
     */

    /*
     * 2d - Official solution / 官方答案
     * No. Though radix sort runs linear with respect to the number of elements
     * in the list, the runtime also depends on the size of the radix R and the
     * length of the longest ”word” W (or the number of digits in a number).
     * Additionally, it is not always possible to use radix sort, because not
     * all objects can be split up into digits. However, comparison sorts can be
     * used on any object that defines a compareTo method, and would work well
     * with compareTo methods that are fast.
     *
     * 中文解析：对 N 线性不代表总成本总是小；R 很大或键很长时仍可能
     * 昂贵。基数排序还要求键能拆成有限位置和基数。
     */

    public static void main(String[] args) {
        int[] lsdExpected = {30315, 30326, 30395, 43092};
        int[] msdExpected = {21248, 21295, 22316, 30751, 30753};
        if (!Arrays.equals(lsdExpected, LSD_ROUNDS[LSD_ROUNDS.length - 1])) {
            throw new AssertionError("Unexpected final LSD state");
        }
        if (!Arrays.equals(msdExpected, MSD_ROUNDS[MSD_ROUNDS.length - 1])) {
            throw new AssertionError("Unexpected final MSD state");
        }
    }
}
