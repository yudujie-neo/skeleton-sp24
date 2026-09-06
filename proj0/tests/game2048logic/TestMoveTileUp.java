package game2048logic;
import jh61b.grader.GradedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static com.google.common.truth.Truth.assertWithMessage;

/** Tests the moveTileUpAsFarAsPossible() method of Model.
 * 中文：测试 Model 的 moveTileUpAsFarAsPossible() 方法。
 *
 *
 * @author Erik Kizior
 */
@Timeout(value = 60, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
public class TestMoveTileUp {

    /** No merging required.
     * 中文：无需合并。 */
    @Test
    @Tag("moveTileUpAsFarAsPossible")
    @DisplayName("Single tile in empty column")
    @GradedTest(number = "10.1")
    public void testOneTile() {
        int[][] board = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0}
        };
        Model before = new Model(board, 0);
        before.moveTileUpAsFarAsPossible(0, 0);

        int[][] result = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        Model after = new Model(result, 0);
        assertWithMessage("Boards should match:").that(before.toString()).isEqualTo(after.toString());
    }

    /** No merging required. Tile blocks movement.
     * 中文：无需合并；前方的方块会阻挡移动。 */
    @Test
    @Tag("moveTileUpAsFarAsPossible")
    @DisplayName("two tiles, different values")
    @GradedTest(number = "10.2")
    public void testTwoTiles() {
        int[][] board = {
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0}
        };
        Model before = new Model(board, 0);
        before.moveTileUpAsFarAsPossible(0, 0);

        int[][] result = {
                {4, 0, 0, 0},
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        Model after = new Model(result, 0);
        assertWithMessage("Boards should match:").that(before.toString()).isEqualTo(after.toString());
    }

    /** Merging required. Tiles of same value in same column. Does not depend on the score.
     * 中文：同一列中数值相同的方块需要合并；此测试不依赖分数实现。 */
    @Test
    @Tag("moveTileUpAsFarAsPossible")
    @DisplayName("two tiles merge no score")
    @GradedTest(number = "10.3")
    public void testTwoTilesMergeNoScore() {
        int[][] board = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0}
        };
        Model before = new Model(board, 0);
        before.moveTileUpAsFarAsPossible(0, 0);

        int[][] result = {
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        Model after = new Model(result, before.score());
        assertWithMessage("Boards should match:").that(before.toString()).isEqualTo(after.toString());
    }

    /** Merging required. Tiles of same value in same column. Checks that score updates correctly.
     * 中文：同一列中数值相同的方块需要合并，并检查分数是否正确更新。 */
    @Test
    @Tag("moveTileUpAsFarAsPossible")
    @DisplayName("two tiles merge with score update")
    @GradedTest(number = "10.4")
    public void testTwoTilesMergeScore() {
        int[][] board = {
                {2, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {2, 0, 0, 0}
        };
        Model before = new Model(board, 0);
        before.moveTileUpAsFarAsPossible(0, 0);

        int[][] result = {
                {4, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };

        Model after = new Model(result, 4);
        assertWithMessage("Boards should match:").that(before.toString()).isEqualTo(after.toString());
    }

}
