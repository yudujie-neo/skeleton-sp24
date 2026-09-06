package tetris;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TETile;
import tileengine.TERenderer;
import tileengine.Tileset;

import java.util.*;

/**
 *  Provides the logic for Tetris.
 *  中文：提供俄罗斯方块的主要游戏逻辑。
 *
 *  @author Erik Nelson, Omar Yu, Noah Adhikari, Jasmine Lin
 */

public class Tetris {

    private static int WIDTH = 10;
    private static int HEIGHT = 20;

    // Tetrominoes spawn above the area we display, so we'll have our Tetris board have a
    // greater height than what is displayed.
    // 中文：七巧板方块会在可见区域上方生成，因此棋盘的实际高度大于显示高度。
    private static int GAME_HEIGHT = 25;

    // Contains the tiles for the board.
    // 中文：保存棋盘图块。
    private TETile[][] board;

    // Helps handle movement of pieces.
    // 中文：辅助处理方块移动。
    private Movement movement;

    // Checks for if the game is over.
    // 中文：记录游戏是否结束。
    private boolean isGameOver;

    // The current Tetromino that can be controlled by the player.
    // 中文：玩家当前可控制的方块。
    private Tetromino currentTetromino;

    // The current game's score.
    // 中文：当前游戏分数。
    private int score;

    /**
     * Checks for if the game is over based on the isGameOver parameter.
     * 中文：根据 isGameOver 字段返回游戏是否结束。
     * @return boolean representing whether the game is over or not
     */
    private boolean isGameOver() {
        return isGameOver;
    }

    /**
     * Renders the game board and score to the screen.
     * 中文：将棋盘和分数渲染到屏幕上。
     */
    private void renderBoard() {
        ter.drawTiles(board);
        renderScore();
        StdDraw.show();

        if (auxFilled) {
            auxToBoard();
        } else {
            fillBoard(Tileset.NOTHING);
        }
    }

    /**
     * Creates a new Tetromino and updates the instance variable
     * accordingly. Flags the game to end if the top of the board
     * is filled and the new piece cannot be spawned.
     * 中文：创建新方块并更新实例变量。如果棋盘顶部已被占用，
     * 新方块无法生成，则标记游戏结束。
     */
    private void spawnPiece() {
        // The game ends if this tile is filled
        // 中文：如果此位置已被占用，游戏结束。
        if (board[4][19] != Tileset.NOTHING) {
            isGameOver = true;
        }

        // Otherwise, spawn a new piece and set its position to the spawn point
        // 中文：否则生成新方块，并将其位置设为出生点。
        currentTetromino = Tetromino.values()[bagRandom.getValue()];
        currentTetromino.reset();
    }

    /**
     * Updates the board based on the user input. Makes the appropriate moves
     * depending on the user's input.
     * 中文：根据用户输入更新棋盘，并执行对应的移动。
     */
    private void updateBoard() {
        // Grabs the current piece.
        // 中文：获取当前方块。
        Tetromino t = currentTetromino;
        if (actionDeltaTime() > 1000) {
            movement.dropDown();
            resetActionTimer();
            Tetromino.draw(t, board, t.pos.x, t.pos.y);
            return;
        }

        // TODO: Implement interactivity, so the user is able to input the keystrokes to move
        // 中文：实现交互功能，使用户能够通过按键移动
        //  the tile and rotate the tile. You'll want to use some provided helper methods here.
        // 中文：方块并旋转方块。这里可以使用一些已提供的辅助方法。


        Tetromino.draw(t, board, t.pos.x, t.pos.y);
    }

    /**
     * Increments the score based on the number of lines that are cleared.
     * 中文：根据消除的行数增加分数。
     *
     * @param linesCleared
     */
    private void incrementScore(int linesCleared) {
        // TODO: Increment the score based on the number of lines cleared.
        // 中文：根据消除的行数增加分数。

    }

    /**
     * Clears lines/rows on the provided tiles/board that are horizontally filled.
     * Repeats this process for cascading effects and updates score accordingly.
     * 中文：清除给定棋盘中水平方向已填满的行。为处理连锁效果，
     * 需重复此过程，并相应地更新分数。
     * @param tiles
     */
    public void clearLines(TETile[][] tiles) {
        // Keeps track of the current number lines cleared
        // 中文：记录本次消除的行数。
        int linesCleared = 0;

        // TODO: Check how many lines have been completed and clear it the rows if completed.
        // 中文：检查有多少行已经填满，并清除所有已填满的行。

        // TODO: Increment the score based on the number of lines cleared.
        // 中文：根据消除的行数增加分数。

        fillAux();
    }

    /**
     * Where the game logic takes place. The game should continue as long as the game isn't
     * over.
     * 中文：游戏主逻辑所在之处。只要游戏尚未结束，就应继续运行。
     */
    public void runGame() {
        resetActionTimer();

        // TODO: Set up your game loop. The game should keep running until the game is over.
        // 中文：设置游戏循环。游戏应持续运行，直到游戏结束。
        // Use helper methods inside your game loop, according to the spec description.
        // 中文：按照项目说明，在游戏循环中使用辅助方法。


    }

    /**
     * Renders the score using the StdDraw library.
     * 中文：使用 StdDraw 库渲染分数。
     */
    private void renderScore() {
        // TODO: Use the StdDraw library to draw out the score.
        // 中文：使用 StdDraw 库绘制分数。

    }

    /**
     * Use this method to run Tetris.
     * 中文：使用此方法运行俄罗斯方块。
     * @param args
     */
    public static void main(String[] args) {
        long seed = args.length > 0 ? Long.parseLong(args[0]) : (new Random()).nextLong();
        Tetris tetris = new Tetris(seed);
        tetris.runGame();
    }

    /**
     * Everything below here you don't need to touch.
     * 中文：下方的所有内容都不需要修改。
     */

    // This is our tile rendering engine.
    private final TERenderer ter = new TERenderer();

    // Used for randomizing which pieces are spawned.
    private Random random;
    private BagRandomizer bagRandom;

    private long prevActionTimestamp;
    private long prevFrameTimestamp;

    // The auxiliary board. At each time step, as the piece moves down, the board
    // is cleared and redrawn, so we keep an auxiliary board to track what has been
    // placed so far to help render the current game board as it updates.
    private TETile[][] auxiliary;
    private boolean auxFilled;

    public Tetris() {
        board = new TETile[WIDTH][GAME_HEIGHT];
        auxiliary = new TETile[WIDTH][GAME_HEIGHT];
        random = new Random(new Random().nextLong());
        bagRandom = new BagRandomizer(random, Tetromino.values().length);
        auxFilled = false;
        movement = new Movement(WIDTH, GAME_HEIGHT, this);
        fillBoard(Tileset.NOTHING);
        fillAux();
    }

    public Tetris(long seed) {
        board = new TETile[WIDTH][GAME_HEIGHT];
        auxiliary = new TETile[WIDTH][GAME_HEIGHT];
        random = new Random(seed);
        bagRandom = new BagRandomizer(random, Tetromino.values().length);
        auxFilled = false;
        movement = new Movement(WIDTH, GAME_HEIGHT, this);

        ter.initialize(WIDTH, HEIGHT);
        fillBoard(Tileset.NOTHING);
        fillAux();
    }

    // Setter and getter methods.

    /**
     * Returns the current game board.
     * @return
     */
    public TETile[][] getBoard() {
        return board;
    }

    /**
     * Returns the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Returns the current auxiliary board.
     * @return
     */
    public TETile[][] getAuxiliary() {
        return auxiliary;
    }


    /**
     * Returns the current Tetromino/piece.
     * @return
     */
    public Tetromino getCurrentTetromino() {
        return currentTetromino;
    }

    /**
     * Sets the current Tetromino to null.
     * @return
     */
    public void setCurrentTetromino() {
        currentTetromino = null;
    }

    /**
     * Sets the boolean auxFilled to true;
     */
    public void setAuxTrue() {
        auxFilled = true;
    }

    /**
     * Fills the entire board with the specific tile that is passed in.
     * @param tile
     */
    private void fillBoard(TETile tile) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = tile;
            }
        }
    }

    /**
     * Copies the contents of the src array into the dest array using
     * System.arraycopy.
     * @param src
     * @param dest
     */
    private static void copyArray(TETile[][] src, TETile[][] dest) {
        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, src[0].length);
        }
    }

    /**
     * Copies over the tiles from the game board to the auxiliary board.
     */
    public void fillAux() {
        copyArray(board, auxiliary);
    }

    /**
     * Copies over the tiles from the auxiliary board to the game board.
     */
    private void auxToBoard() {
        copyArray(auxiliary, board);
    }

    /**
     * Calculates the delta time with the previous action.
     * @return the amount of time between the previous Tetromino movement with the present
     */
    private long actionDeltaTime() {
        return System.currentTimeMillis() - prevActionTimestamp;
    }

    /**
     * Resets the action timestamp to the current time in milliseconds.
     */
    private void resetActionTimer() {
        prevActionTimestamp = System.currentTimeMillis();
    }

}
