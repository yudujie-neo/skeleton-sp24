/**
 * Discussion 06, Regular Question 3: Re-cursed with Asymptotics!
 *
 * 3a — English: What is the runtime of curse in terms of n?
 * 中文：用 n 表示 curse 的运行时间。
 * My answer / 我的答案: TODO
 */
public class Dis06RegularQ3RecursiveAsymptotics {
    public static int curse(int n) {
        if (n <= 0) {
            return 0;
        } else {
            return n + curse(n - 1);
        }
    }

    /**
     * 3b — English — Official question
     * Find a runtime bound for silly. Assume System.arraycopy takes Theta(N) time,
     * where N is the number of elements copied. The official signature printed on
     * the worksheet is System.arrayCopy(Object sourceArr, int srcPos, Object dest,
     * int destPos, int length). srcPos and destPos are the starting points and
     * length is the number of elements copied.
     *
     * 中文：求 silly 的运行时间界。假设复制 N 个元素的 System.arraycopy
     * 耗时 Theta(N)。
     * My answer / 我的答案: TODO
     */
    public static void silly(int[] arr) {
        if (arr.length <= 1) {
            System.out.println("You won!");
            return;
        }

        int newLen = arr.length / 2;
        int[] firstHalf = new int[newLen];
        int[] secondHalf = new int[newLen];

        System.arraycopy(arr, 0, firstHalf, 0, newLen);
        System.arraycopy(arr, newLen, secondHalf, 0, newLen);

        silly(firstHalf);
        silly(secondHalf);
    }

    /**
     * 3c — English: Given that exponentialWork runs in Theta(3^N) time with
     * respect to input N, what is the runtime of ronnie?
     * 中文：已知 exponentialWork(N) 耗时 Theta(3^N)，求 ronnie 的运行时间。
     * My answer / 我的答案: TODO
     */
    public void ronnie(int N) {
        if (N <= 1) {
            return;
        }
        ronnie(N - 2);
        ronnie(N - 2);
        ronnie(N - 2);
        exponentialWork(N); // Runs in Theta(3^N) time.
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    private void exponentialWork(int N) {
        throw new UnsupportedOperationException(
                "The worksheet defines exponentialWork(N) as taking Theta(3^N) time");
    }
}
