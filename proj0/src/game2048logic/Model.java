package game2048logic;

import game2048rendering.Board;
import game2048rendering.Side;
import game2048rendering.Tile;

import java.util.Formatter;


/** The state of a game of 2048.
 *  中文：表示一局 2048 游戏的状态。
 *  @author P. N. Hilfinger + Josh Hug
 */
public class Model {
    /** Current contents of the board.
     * 中文：棋盘当前的内容。 */
    private final Board board;
    /** Current score.
     * 中文：当前分数。 */
    private int score;

    /* Coordinate System: column x, row y of the board (where x = 0,
     * y = 0 is the lower-left corner of the board) will correspond
     * to board.tile(x, y).  Be careful!
     *
     * 中文：坐标系使用棋盘的列 x、行 y；左下角是 (0, 0)，并对应
     * board.tile(x, y)。这里很容易弄错，请特别注意！
     */

    /** Largest piece value.
     * 中文：方块允许达到的最大值。 */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0.
     *  中文：新建一局 2048 游戏，棋盘边长为 SIZE，初始时没有方块，
     *  分数为 0。 */
    public Model(int size) {
        board = new Board(size);
        score = 0;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (x, y) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes.
     * 中文：根据 RAWVALUES 新建一局 2048 游戏；数组保存各方块的值，
     * 空位置用 0 表示。数组按 (x, y) 索引，(0, 0) 对应左下角。
     * 此构造方法用于测试。 */
    public Model(int[][] rawValues, int score) {
        board = new Board(rawValues);
        this.score = score;
    }

    /** Return the current Tile at (x, y), where 0 <= x < size(),
     *  0 <= y < size(). Returns null if there is no tile there.
     *  Used for testing.
     *  中文：返回当前位置 (x, y) 的方块，其中 0 <= x < size() 且
     *  0 <= y < size()。如果该位置为空则返回 null。此方法用于测试。 */
    public Tile tile(int x, int y) {
        return board.tile(x, y);
    }

    /** Return the number of squares on one side of the board.
     * 中文：返回棋盘的边长（单边格子数）。 */
    public int size() {
        return board.size();
    }

    /** Return the current score.
     * 中文：返回当前分数。 */
    public int score() {
        return score;
    }


    /** Clear the board to empty and reset the score.
     * 中文：清空棋盘并将分数重置为 0。 */
    public void clear() {
        score = 0;
        board.clear();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position.
     *  中文：把 TILE 加入棋盘；其目标位置当前必须没有其他方块。 */
    public void addTile(Tile tile) {
        board.addTile(tile);
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board).
     *  中文：当且仅当游戏结束时返回 true。游戏结束是指已经没有合法移动，
     *  或棋盘上已经出现值为 2048 的方块。 */
    public boolean gameOver() {
        return maxTileExists() || !atLeastOneMoveExists();
    }

    /** Returns this Model's board.
     * 中文：返回此 Model 所使用的棋盘。 */
    public Board getBoard() {
        return board;
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     *  中文：如果棋盘上至少有一个空格则返回 true；空格以 null 保存。
     * */
    public boolean emptySpaceExists() {
        // TODO: Task 2. Fill in this function.
        // 中文：任务 2：补全此函数。
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by this.MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     *
     * 中文：如果棋盘上任意方块的值等于允许的最大值，则返回 true。
     * 最大值由 this.MAX_PIECE 给出。对于 Tile 对象 t，可调用 t.value()
     * 取得它的值。
     */
    public boolean maxTileExists() {
        // TODO: Task 3. Fill in this function.
        // 中文：任务 3：补全此函数。
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     *
     * 中文：如果棋盘上存在任意合法移动，则返回 true。合法移动存在于
     * 以下两种情况之一：
     * 1. 棋盘上至少有一个空格；
     * 2. 存在两个上下或左右相邻、且数值相同的方块。
     */
    public boolean atLeastOneMoveExists() {
        // TODO: Fill in this function.
        // 中文：补全此函数。
        return false;
    }

    /**
     * Moves the tile at position (x, y) as far up as possible.
     *
     * Rules for Tilt:
     * 1. If two Tiles are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     *
     * 中文：把位置 (x, y) 的方块尽可能向上移动。
     * 倾斜规则：
     * 1. 如果沿移动方向相邻的两个方块数值相同，它们会合并成一个数值为
     *    原来两倍的新方块，并把这个新方块的值加到实例变量 score 中；
     * 2. 一次倾斜中，由合并产生的新方块不能再次参与合并。因此每次移动时，
     *    每个方块最多参与一次合并（也可能一次都不参与）；
     * 3. 如果沿移动方向有三个相邻且数值相同的方块，只合并移动方向上最靠前
     *    的两个，最后面的那个不合并。
     */
    public void moveTileUpAsFarAsPossible(int x, int y) {
        Tile currTile = board.tile(x, y);
        int myValue = currTile.value();
        int targetY = y;

        // TODO: Tasks 5, 6, and 10. Fill in this function.
        // 中文：任务 5、6 和 10：补全此函数。
    }

    /** Handles the movements of the tilt in column x of the board
     * by moving every tile in the column as far up as possible.
     * The viewing perspective has already been set,
     * so we are tilting the tiles in this column up.
     * 中文：处理棋盘第 x 列的倾斜移动，把这一列的每个方块尽可能向上移动。
     * 此时棋盘的观察方向已经设置好，因此只需把该列按“向上”处理。
     * */
    public void tiltColumn(int x) {
        // TODO: Task 7. Fill in this function.
        // 中文：任务 7：补全此函数。
    }

    public void tilt(Side side) {
        // TODO: Tasks 8 and 9. Fill in this function.
        // 中文：任务 8 和 9：补全此函数。
    }

    /** Tilts every column of the board toward SIDE.
     * 中文：把棋盘的每一列都朝 SIDE 指定的方向倾斜。
     */
    public void tiltWrapper(Side side) {
        board.resetMerged();
        tilt(side);
    }


    @Override
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int y = size() - 1; y >= 0; y -= 1) {
            for (int x = 0; x < size(); x += 1) {
                if (tile(x, y) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(x, y).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (game is %s) %n", score(), over);
        return out.toString();
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Model m) && this.toString().equals(m.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
