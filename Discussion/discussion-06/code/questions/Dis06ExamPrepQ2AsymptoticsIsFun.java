/**
 * Discussion 06, Exam Prep Question 2: Asymptotics is Fun!
 *
 * 2a — English — Official question
 * Using g below, what is the runtime of g(N, 1) and g(N, 2)? Write each answer
 * in terms of N. Feel free to draw the recursion tree if it helps.
 *
 * 中文：求 g(N, 1) 与 g(N, 2) 关于 N 的运行时间。
 * My answer / 我的答案:
 * g(N, 1): Theta(TODO)
 * g(N, 2): Theta(TODO)
 */
public class Dis06ExamPrepQ2AsymptoticsIsFun {
    public static void g(int N, int x) {
        if (N == 0) {
            return;
        }
        for (int i = 1; i <= x; i++) {
            g(N - 1, i);
        }
    }

    /**
     * 2b — English — Official question
     * Change the recursive call to g(N - 1, x) and the loop condition to i <= f(x),
     * where f returns a random number between 1 and x, inclusive. Find the tightest
     * Omega and big-O bounds for g(N, 2) and g(N, N).
     *
     * 中文：递归调用改为 g(N - 1, x)，循环上界改为随机返回 [1,x] 的 f(x)。
     * 求 g(N,2) 与 g(N,N) 最紧的 Omega 和 O 界。
     *
     * My answer / 我的答案:
     * g(N, 2): Omega(TODO), O(TODO)
     * g(N, N): Omega(TODO), O(TODO)
     *
     * Compilation scaffold / 编译脚手架（非官方题目内容）
     * The second worksheet variant is renamed randomizedG only so both variants compile together.
     */
    /** Compilation scaffold / 编译脚手架（非官方题目内容）: renamed from g to coexist with 2a. */
    public static void randomizedG(int N, int x) {
        if (N == 0) {
            return;
        }
        for (int i = 1; i <= f(x); i++) {
            randomizedG(N - 1, x);
        }
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    private static int f(int x) {
        throw new UnsupportedOperationException("The worksheet defines f(x) as random in [1, x]");
    }
}
