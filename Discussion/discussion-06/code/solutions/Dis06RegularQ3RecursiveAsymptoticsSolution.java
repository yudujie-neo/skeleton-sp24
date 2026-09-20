/** Discussion 06, Regular Question 3 — Solution. */
public class Dis06RegularQ3RecursiveAsymptoticsSolution {
    public static int curse(int n) {
        if (n <= 0) {
            return 0;
        } else {
            return n + curse(n - 1);
        }
    }

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

    /**
     * Official solution (English)
     * 3a: Theta(n). Each recursive call does constant work, and there are n calls.
     *
     * 3b: At each recursion-tree level the two arraycopy calls total Theta(N)
     * work. Each level halves the array length, so there are log_2(N) levels.
     * Therefore the runtime is Theta(N log N).
     *
     * 3c: Theta(3^N). The first level has Theta(3^N) work, the next has
     * Theta(3^(N-1)), and so on to approximately Theta(3^(N/2)). The decreasing
     * geometric sum is dominated by Theta(3^N).
     *
     * 中文解释
     * 3a 是深度 n 的线性递归；3b 每层复制总量为 N、层数为 log N；3c 每层
     * 总工作依次为 3^N、3^(N-1)...，首项主导。
     *
     * Supplemental analysis / 补充分析（非官方答案）
     * 题干写 System.arrayCopy，但 Java API 实名为 System.arraycopy。奇数长度时
     * 两个 floor(N/2) 子数组遗漏最后一个元素，不影响复杂度但影响数据语义。
     */
    public static void main(String[] args) {
        if (curse(4) != 10) {
            throw new AssertionError("curse smoke test failed");
        }
        silly(new int[0]);
    }
}
/* Official solution transcript (English; mathematical typography normalized for Java comments): Theta(n). On each recursive call, we do a constant amount of work. We make n recursive calls, because we go from n to 1. Then n recursive layers with 1 work at each layer is overall Theta(n) work. At each level, we do N work, because the call to System.arraycopy. You can see that at the top level, this is N work. At the next level, we make two calls that each operate on arrays of length N/2, but that total work sums up to N. On the level after that, in four separate recursive function frames we'll call System.arraycopy on arrays of length N/4, which again sums up to N for that whole layer of recursive calls. Now we look for the height of our recursive tree. Each time, we halve the length of N, which means that the length of the array N on recursive level k is roughly N*(1/2)^k. Then we will finally reach our base case N <= 1 when we have N*(1/2)^k=1. Doing some math, we see this can be transformed into N=2^k, which means k=log_2(N). In other words, the number of layers in our recursive tree is log_2(N). If we have log_2(N) layers with Theta(N) work on each layer, we must have Theta(N log(N)) runtime. Theta(3^N). Drawing out the recursive tree, the first level has Theta(3^N) work, the next level has Theta(3^(N-1)) work, and so on until the last level which has approximately Theta(3^(N/2)) work. This gives the sum Theta(3^N)+Theta(3^(N-1))+...+Theta(3^(N/2))=Theta(3^N). */
