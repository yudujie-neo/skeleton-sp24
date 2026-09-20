/** Discussion 06, Regular Question 2 — Solution. */
public class Dis06RegularQ2IAmSpeedSolution {
    // Official solution (2a): i += 1.
    public static void f1(int N) {
        for (int i = 1; i < N; i += 1) {
            System.out.println("hi Dom");
        }
    }

    // Official solution (2a): i *= 2.
    public static void f2(int N) {
        for (int i = 1; i < N; i *= 2) {
            System.out.println("howdy Ergun");
        }
    }

    // Official solution (2a): any constant bound independent of N; 1000 is used.
    public static void f3(int N) {
        for (int i = 1; i < 1000; i += 1) {
            System.out.println("hello Anniyat");
        }
    }

    // Official solution (2a): geometric work 1 + 2 + ... + 2^N.
    public static void f4(int N) {
        for (int i = 1; i < Math.pow(2, N); i *= 2) {
            for (int j = 0; j < i; j += 1) {
                System.out.println("what's up Alyssa");
            }
        }
    }

    /**
     * Official solution (English)
     * f1 may use i += C for a constant C independent of N: N/C is still Theta(N).
     * f2 may use i *= C for a constant C independent of N: log base C of N is
     * still O(log n). f3 may use i < C for a constant independent of N. For f4,
     * the geometric sum is dominated by its final term and is Theta(2^N).
     *
     * 2b: Worst case: Theta(N^3 M). Best case: Theta(N^3 log M).
     * kachow runs on every inner iteration and costs Theta(N^2). If it always
     * returns false, j doubles and the inner loop has log M iterations. If it
     * always returns true, j increases by one and the inner loop has M iterations.
     * The outer loop contributes another factor N.
     *
     * 中文解释
     * f1 常数步长为线性；f2 固定倍增为对数；f3 上界与输入无关；f4 每轮
     * 内层工作形成几何级数。2b 再乘每次 kachow 的 N^2 成本和外层 N 次。
     *
     * Supplemental analysis / 补充分析（非官方答案）
     * 官方 f2 解释末尾写“in general log_i(N)”，更准确的底数应是倍乘常数 C。
     */
    public static void extra(int N, int M) {
        for (int i = 0; i < N; i += 1) {
            for (int j = 1; j <= M; ) {
                if (kachow()) {
                    j += 1;
                } else {
                    j *= 2;
                }
            }
        }
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    private static boolean kachow() {
        throw new UnsupportedOperationException(
                "The worksheet defines kachow() as a boolean operation taking Theta(N^2) time");
    }

    public static void main(String[] args) {
        // Deterministic smoke checks use small inputs and avoid f3's 999 prints.
        f1(1);
        f2(1);
        f4(0);
    }
}
/* Official solution transcript (English; mathematical typography normalized for Java comments): Note the solution could be i += C, where C is some constant independent of N. This is because even if we did for example, i += 10, we would do N/10 work in total, which is still Theta(N). Here, the solution could be i *= C, where C is some constant independent of N. This is because even if we did for example, i *= 5, we would do log_5(N) work in total, and in general log_i(N) work, which is still O(log n). Again, the solution is actually just i < C, where C is some constant independent of the input N. Worst case: Theta(N^3 M), Best case: Theta(N^3 log M). To see this, note that in the best case kachow() always returns false. Regardless of best/worst case, kachow() runs with each iteration of the inner loop. Hence j multiplies by 2 each inner loop iteration, which means the inner loop would take N^2 log M time for each iteration of the outer loop. In the worst case, kachow() always returns true, thus the inner loop iterates M times, each time calling kachow(), so the inner loop would take N^2 M. Since the outer loop always iterates N times, we get the worst and best case by multiplying the time the inner loop takes by N. */
