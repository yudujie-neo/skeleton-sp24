/** Discussion 06, Exam Prep Question 2 — Solution. */
public class Dis06ExamPrepQ2AsymptoticsIsFunSolution {
    public static void g(int N, int x) {
        if (N == 0) {
            return;
        }
        for (int i = 1; i <= x; i++) {
            g(N - 1, i);
        }
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）: renamed from g to coexist with 2a. */
    public static void randomizedG(int N, int x) {
        if (N == 0) {
            return;
        }
        for (int i = 1; i <= f(x); i++) {
            randomizedG(N - 1, x);
        }
    }

    /**
     * Official solution (English)
     * 2a: g(N, 1) is Theta(N). The loop executes once and produces a single chain
     * of N recursive calls, each doing constant work.
     * g(N, 2) is Theta(N^2). Each call makes g(N-1,1) and g(N-1,2); the former is
     * linear work, so the total over N levels is (N-1)+(N-2)+...+1.
     *
     * 2b: g(N, 2): Omega(N), O(2^N). g(N, N): Omega(N), O(N^N).
     * If f(x) always returns 1, the recursion is a linear chain. If it always
     * returns x, the tree has 1,x,x^2,... nodes per level and x work per node,
     * giving x(1+x+...+x^(N-1)). Substituting x=2 or x=N gives the upper bounds.
     *
     * 中文解释
     * 2a 的 x=1 是单链；x=2 每层多累加一段线性工作。2b 最小分支始终为 1，
     * 最大分支为 x，分别得到线性下界和 2^N、N^N 上界。
     * Official recursion-tree drawing remains in discussion-06.md/assets.
     */
    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    private static int f(int x) {
        throw new UnsupportedOperationException("The worksheet defines f(x) as random in [1, x]");
    }

    public static void main(String[] args) {
        g(2, 1);
    }
}
/* Official solution transcript (English; mathematical typography normalized for Java comments): When x is 1, the loop gets executed once and makes a single recursive call to g(N - 1). The recursion goes g(N), g(N - 1), g(N - 2), and so on. This is a total of N recursive calls, each doing constant work. When x is 2, the loop gets executed twice. This means a call to g(N) makes 2 recursive calls to g(N - 1, 1) and g(N - 1, 2). From the first part, we know g(..., 1) does linear work. Thus, this is a recursion tree with N levels, and the total work is (N-1)+(N-2)+...+1=Theta(N^2) work. Suppose f(x) always returns 1. Then, this is the same as case 1 from (a), resulting in a linear runtime. On the other hand, suppose f(x) always returns x. Then g(N, x) makes x recursive calls to g(N - 1, x), each of which makes x recursive calls to g(N - 2, x), and so on, so the recursion tree has 1,x,x^2,... nodes per level. Outside of the recursion, the function g does x work per node. Thus, the overall work is x*1+x*x+x*x^2+...+x*x^(N-1)=x(1+x+x^2+...+x^(N-1)). Plug in x=2 to get O(2^N) for our first upper bound. Plug in x=N to get O(N^N), ignoring lower-order terms. */
