package solutions;

/** Official solutions for Discussion 05 Exam Prep Question 1. */
public class Dis05ExamPrepQ1AsymptoticsSolution {

    /*
     * Official solution / 官方答案
     * f1: Theta(N^2)
     * f2: Theta(N)
     *
     * 中文解析：
     * f1 的工作量为 1 + 2 + ... + N，是等差级数 Theta(N^2)。
     * f2 的 i 依次翻倍，总工作量为 1 + 2 + 4 + ... + N，是 Theta(N)。
     * 不能只看到外层倍增就回答 Theta(log N)，因为内层工作量也在增长。
     */
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
