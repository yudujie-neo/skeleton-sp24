package solutions;

/** Official solutions for Discussion 05 Exam Prep Question 3. */
public class Dis05ExamPrepQ3WQUAsymptoticsSolution {

    /*
     * 3a - Official solution / 官方答案
     * isConnected: Omega(1), O(log N)
     * connect: Omega(1), O(log N)
     *
     * 中文解析：两种操作都需要查找一个或两个根。最好情况下只走常数条边；
     * WQU 按大小合并把最坏树高限制在 O(log N)。合并根本身只需 O(1)。
     */

    /*
     * 3b - Official solution / 官方答案
     * Omega(N), O(N^2 log N).
     *
     * 中文解析：连接 N 个独立分量至少需要 N - 1 次成功合并。pairs 含
     * Theta(N^2) 个无序对，而一次不带路径压缩的 WQU connect 最坏为
     * O(log N)，所以总上界为 O(N^2 log N)。
     *
     * Supplemental analysis / 补充分析（非官方答案）：
     * Solutions PDF 写有 up to N^2 - N + 1 iterations，但所有无序元素对
     * 只有 N(N - 1)/2 个。该精确计数不一致，不改变官方渐近上界。
     */
    public abstract static class WQUSolutionContext {
        public void addToWQU(int[] elements) {
            int[][] pairs = pairs(elements);
            for (int[] pair : pairs) {
                if (size() == elements.length) {
                    return;
                }
                connect(pair[0], pair[1]);
            }
        }

        protected abstract int[][] pairs(int[] elements);

        protected abstract int size();

        protected abstract void connect(int first, int second);
    }

    /*
     * 3c - Official solution / 官方答案
     * Minimum: 1. Maximum: N - 1.
     *
     * 中文解析：第一次成功连接必然是两个大小为 1 的分量，所以最少一次。
     * 之后始终把大分量与单元素连接即可达到最小值。最大化时逐层合并等大
     * 分量，次数为 N/2 + N/4 + ... + 1 = N - 1。
     */
}
