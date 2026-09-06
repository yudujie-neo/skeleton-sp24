package game2048logic;
import game2048rendering.Side;
import jh61b.grader.GradedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static com.google.common.truth.Truth.assertWithMessage;
import static game2048logic.TestUtils.checkTilt;

/** Tests the tilt() method in the up (Side.NORTH) direction only.
 * 中文：仅测试 tilt() 方法向上（Side.NORTH）倾斜的情况。
 *
 * @author Omar Khan
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
public class TestUpOnly {

    /** Move tiles up (no merging).
     * 中文：向上移动方块，不发生合并。 */
    @Test
    @Tag("up")
    @DisplayName("Up Tilt")
    @GradedTest(number = "6.1")
    public void testUpNoMerge() {
        int[][] before = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 0, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][] {
                {0, 0, 4, 2},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 0), Side.NORTH);
    }

    /** A basic merge.
     * 中文：一次基本的合并。 */
    @Test
    @Tag("up")
    @DisplayName("Up merge")
    @GradedTest(number = "6.2")
    public void testUpBasicMerge() {
        int[][] before = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
        };
        int[][] after = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.NORTH);
    }

    /** A triple merge. Only the leading 2 tiles should merge.
     * 中文：三个相同方块的合并情形；只有移动方向上最靠前的两个方块应合并。 */
    @Test
    @Tag("up")
    @DisplayName("Triple merge")
    @GradedTest(number = "6.3")
    public void testUpTripleMerge() {
        int[][] before = new int[][] {
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 2, 0},
                {0, 0, 2, 0},
        };
        int[][] after = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.NORTH);
    }

    /** A tricky merge.
     *
     * The tricky part here is that the 4 tile on the bottom row shouldn't
     * merge with the newly created 4 tile on the top row. If you're failing
     * this test, try seeing how you can ensure that the bottom 4 tile doesn't
     * merge with the newly created 4 tile on top.
     *
     * 中文：这是一个容易出错的合并情形。底行的 4 不应与顶行刚由两个 2
     * 合成的新 4 再次合并。如果此测试失败，请思考如何保证一次倾斜中，
     * 已由合并生成的方块不会再次参与合并。
     */
    @Test
    @Tag("up")
    @DisplayName("Limit Merging")
    @GradedTest(number = "6.4")
    public void testUpTrickyMerge() {
        int[][] before = new int[][] {
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0},
                {0, 0, 4, 0},
        };
        int[][] after = new int[][] {
                {0, 0, 4, 0},
                {0, 0, 4, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        checkTilt(new Model(before, 0), new Model(after, 4), Side.NORTH);
    }
}
