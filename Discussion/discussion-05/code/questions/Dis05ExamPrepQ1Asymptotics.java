package questions;

/** Discussion 05 Exam Prep Question 1 - Asymptotics / 渐近分析。 */
public class Dis05ExamPrepQ1Asymptotics {

    /*
     * English (official question)
     * Give the runtime of f1 and f2 in Theta notation. Simplify the answer so
     * it has no unnecessary leading constants or lower-order terms.
     *
     * 中文翻译
     * 用 Theta 记号给出 f1 和 f2 的运行时间。答案应尽可能简洁，不含
     * 不必要的常数因子或低阶项。
     *
     * My answer / 我的答案：
     * f1: Theta(TODO)
     * f2: Theta(TODO)
     * Reasoning / 推导：TODO
     */
    //
    private static void f1(int N) {
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < i; j++) {
                System.out.println("shreyas 1.0");
            }
        }
    }

    private static void f2(int N) {
        for (int i = 1; i < N; i *= 2) {
            for (int j = 1; j < i; j++) {
                System.out.println("shreyas 2.0");
            }
        }
    }
}
