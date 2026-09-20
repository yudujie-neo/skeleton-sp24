/**
 * Discussion 06, Regular Question 2: I Am Speed.
 *
 * English — Official question (2a)
 * For each code block below, fill in the blank(s) so that the function has the
 * desired runtime. Do not use any commas. If the answer is impossible, just write
 * "impossible" in the blank. Assume System.out.println runs in constant time.
 * You may use Java's Math.pow(x, y) to raise x to the power y.
 *
 * 中文翻译
 * 补全代码，使四个函数依次达到 Theta(N)、Theta(log N)、Theta(1) 和
 * Theta(2^N)。答案中不要使用逗号；若不可能则写 impossible。
 *
 * Compilation scaffold / 编译脚手架（非官方题目内容）
 * The TODO helper methods replace the illegal blank expressions from the worksheet.
 * They deliberately do not implement the requested runtimes.
 */
public class Dis06RegularQ2IAmSpeed {
    public static void f1(int N) {
        for (int i = 1; i < N; i = todoNextForF1(i, N)) { // TODO: replace update
            System.out.println("hi Dom");
        }
    }

    public static void f2(int N) {
        for (int i = 1; i < N; i = todoNextForF2(i, N)) { // TODO: replace update
            System.out.println("howdy Ergun");
        }
    }

    public static void f3(int N) {
        for (int i = 1; todoConditionForF3(i, N); i += 1) { // TODO: replace condition
            System.out.println("hello Anniyat");
        }
    }

    // This one is tricky! Hint: think about the dominating term in
    // 1 + 2 + 4 + 8 + ... + f(N).
    public static void f4(int N) {
        for (int i = 1; todoConditionForF4(i, N); i *= 2) { // TODO: replace condition
            for (int j = 0; j < i; j += 1) {
                System.out.println("what's up Alyssa");
            }
        }
    }

    /**
     * English — Official question (2b, Extra)
     * Give the worst case and best case running time in Theta notation in terms of
     * M and N. Assume kachow() runs in Theta(N^2) time and returns a boolean.
     *
     * 中文翻译
     * 用 M 和 N 表示最好、最坏运行时间。假设 kachow() 耗时 Theta(N^2)。
     *
     * My answer / 我的答案
     * Worst case: TODO
     * Best case: TODO
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

    private static int todoNextForF1(int i, int N) {
        throw new UnsupportedOperationException("TODO: replace the f1 update expression");
    }

    private static int todoNextForF2(int i, int N) {
        throw new UnsupportedOperationException("TODO: replace the f2 update expression");
    }

    private static boolean todoConditionForF3(int i, int N) {
        throw new UnsupportedOperationException("TODO: replace the f3 condition");
    }

    private static boolean todoConditionForF4(int i, int N) {
        throw new UnsupportedOperationException("TODO: replace the f4 condition");
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    private static boolean kachow() {
        throw new UnsupportedOperationException(
                "The worksheet defines kachow() as a boolean operation taking Theta(N^2) time");
    }
}
