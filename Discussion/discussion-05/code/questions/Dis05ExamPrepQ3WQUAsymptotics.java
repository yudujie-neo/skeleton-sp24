package questions;

/** Discussion 05 Exam Prep Question 3 - WQU asymptotics / WQU 渐近分析。 */
public class Dis05ExamPrepQ3WQUAsymptotics {

    /*
     * 3a - English (official question)
     * Suppose we have a Weighted Quick Union (WQU) without path compression
     * with N elements. What is the runtime, in big Omega and big O, of
     * isConnected? What is the runtime, in big Omega and big O, of connect?
     *
     * 3a - 中文翻译
     * 对包含 N 个元素且不带路径压缩的 WQU，给出 isConnected 和 connect
     * 最紧的 Omega 与 O 界。
     *
     * My answer / 我的答案：
     * isConnected: Omega(TODO), O(TODO)
     * connect: Omega(TODO), O(TODO)
     * Reasoning / 推导：TODO
     */

    /*
     * 3b - English (official question)
     * Suppose we add the method addToWQU to a WQU without path compression. The
     * method takes in a list of elements and connects them in a random order,
     * stopping when all elements are connected. Assume that all the elements
     * are disconnected before the method call. Assume pairs and size run in
     * constant time. What is the runtime in big Omega and big O?
     *
     * 3b - 中文翻译
     * addToWQU 以随机顺序连接所有可能的元素对，并在全部元素连通时停止。
     * 初始时元素互不连通；假设 pairs 和 size 为常数时间。给出最紧的
     * Omega 与 O 界。
     *
     * My answer / 我的答案：Omega(TODO), O(TODO)
     * Reasoning / 推导：TODO
     */
    public abstract static class WQUExercise {
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
     * 3c - English (official question)
     * Let us define a matching size connection as connecting two components in
     * a WQU of equal size. What is the minimum and maximum number of matching
     * size connections that can occur after executing addToWQU? Assume N, i.e.
     * elements.length, is a power of two. Your answers should be exact.
     *
     * 3c - 中文翻译
     * 等大小连接是指连接两个大小相同的 WQU 分量。执行 addToWQU 后，
     * 等大小连接次数的精确最小值和最大值分别是多少？假设 N 是 2 的幂。
     *
     * My answer / 我的答案：minimum = TODO; maximum = TODO
     * Reasoning / 推导：TODO
     */
}
