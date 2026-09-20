/** Discussion 06, Exam Prep Question 1 — Solution. */
public class Dis06ExamPrepQ1FinishTheRuntimesSolution {
    public static void partA(int N) {
        for (int i = 1; i < N; i = i + 1) {
            for (int j = 1; j < i; j = j + 1) {
                System.out.println("This is one is low key hard");
            }
        }
    }

    public static void partB(int N) {
        for (int i = 1; i < N; i = i * 2) {
            for (int j = 1; j < 2; j = j * 2) {
                System.out.println("This is one is mid key hard");
            }
        }
    }

    public static void partC(int N) {
        for (int i = 1; i < N; i = i + 1) {
            for (int j = 1; j < Math.pow(2, i); j = j + 1) {
                System.out.println("This is one is high key hard");
            }
        }
    }

    public static void partD(int N) {
        for (int i = 1; i < Math.pow(2, N); i = i * 2) {
            for (int j = 1; j < N * N; j = j + 1) {
                System.out.println("yikes");
            }
        }
    }

    /**
     * Official solution (English)
     * 1a: j = j + 1. The work is 1 + 2 + ... + N = Theta(N^2).
     * 1b: Any constant inner bound works; 2 is chosen. The outer loop is log N
     * and the inner loop does constant work.
     * 1c: Use Math.pow(2, i). The sum 1 + 2 + ... + 2^N is Theta(2^N).
     * 1d: Use i < Math.pow(2, N) and j = j + 1. The outer loop runs N times
     * and the inner loop runs N^2 times, giving Theta(N^3).
     *
     * 中文解释
     * 1a 为等差求和；1b 外层对数、内层常数；1c 为指数几何级数；1d 外层
     * 通过倍增到 2^N 运行 N 次，再乘每轮 N^2。
     */
    public static void main(String[] args) {
        partA(1);
        partB(1);
        partC(1);
        partD(0);
    }
}
/* Official solution transcript (English; mathematical typography normalized for Java comments): Explanation: Remember the arithmetic series 1 + 2 + 3 + 4 + ... + N = Theta(N^2). We get this series by incrementing j by 1 per inner loop. Any constant would work here, 2 was chosen arbitrarily. Explanation: The outer loop already runs log n times, since i doubles each time. This means the inner loop must do constant work (so any constant j < k would work). Explanation: Remember the geometric series 1 + 2 + 4 + ... + 2^N = Theta(2^N). We notice that i increments by 1 each time, so in order to achieve this 2^N runtime, we must run the inner loop 2^i times per outer loop iteration. Explanation: One way to get N^3 runtime is to have the outer loop run N times, and the inner loop run N^2 times per outer loop iteration. To make the outer loop run N times, we need stop after multiplying i = i * 2 N times, giving us the condition i < Math.pow(2, N). To make the inner loop run N^2 times, we can simply increment by 1 each time. */
