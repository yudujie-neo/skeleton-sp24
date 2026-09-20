/**
 * Discussion 06, Exam Prep Question 1: Finish the Runtimes.
 *
 * English — Official question
 * Below is the standard nested for loop with missing pieces. For each part, some
 * blanks are filled and a desired runtime is given. Fill the remaining blanks.
 * There may be more than one correct answer. Hint: Math.pow may be helpful.
 *
 * 1a desired runtime: Theta(N^2)
 * 1b desired runtime: Theta(log N)
 * 1c desired runtime: Theta(2^N)
 * 1d desired runtime: Theta(N^3)
 *
 * 中文翻译
 * 补全四组嵌套循环，使其分别达到 Theta(N^2)、Theta(log N)、Theta(2^N)
 * 和 Theta(N^3)。可能不止一种答案，Math.pow 可能有帮助。
 *
 * Compilation scaffold / 编译脚手架（非官方题目内容）
 * TODO helpers stand in for illegal worksheet blanks and intentionally do not
 * implement the requested runtimes.
 */
public class Dis06ExamPrepQ1FinishTheRuntimes {
    public static void partA(int N) {
        for (int i = 1; i < N; i = i + 1) {
            for (int j = 1; j < i; j = todoNextA(j, i, N)) { // TODO
                System.out.println("This is one is low key hard");
            }
        }
    }

    public static void partB(int N) {
        for (int i = 1; i < N; i = i * 2) {
            for (int j = 1; todoConditionB(j, i, N); j = j * 2) { // TODO
                System.out.println("This is one is mid key hard");
            }
        }
    }

    public static void partC(int N) {
        for (int i = 1; i < N; i = i + 1) {
            for (int j = 1; todoConditionC(j, i, N); j = j + 1) { // TODO
                System.out.println("This is one is high key hard");
            }
        }
    }

    public static void partD(int N) {
        for (int i = 1; todoOuterConditionD(i, N); i = i * 2) { // TODO
            for (int j = 1; j < N * N; j = todoNextD(j, i, N)) { // TODO
                System.out.println("yikes");
            }
        }
    }

    private static int todoNextA(int j, int i, int N) {
        throw new UnsupportedOperationException("TODO: replace the 1a update expression");
    }

    private static boolean todoConditionB(int j, int i, int N) {
        throw new UnsupportedOperationException("TODO: replace the 1b bound");
    }

    private static boolean todoConditionC(int j, int i, int N) {
        throw new UnsupportedOperationException("TODO: replace the 1c bound");
    }

    private static boolean todoOuterConditionD(int i, int N) {
        throw new UnsupportedOperationException("TODO: replace the 1d outer bound");
    }

    private static int todoNextD(int j, int i, int N) {
        throw new UnsupportedOperationException("TODO: replace the 1d update expression");
    }
}
