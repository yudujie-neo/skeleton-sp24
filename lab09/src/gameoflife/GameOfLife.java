package gameoflife;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.FileUtils;

import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Am implementation of Conway's Game of Life using StdDraw.
 * Credits to Erik Nelson, Jasmine Lin and Elana Ho for
 * creating the assignment.
 * 中文：使用 StdDraw 实现康威生命游戏。
 */
public class GameOfLife {

    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 50;
    private static final String SAVE_FILE = "src/save.txt";
    private long prevFrameTimestep;
    private TERenderer ter;
    private Random random;
    private TETile[][] currentState;
    private int width;
    private int height;

    /**
     * Initializes our world.
     * 中文：使用指定随机种子初始化世界。
     * @param seed
     */
    public GameOfLife(long seed) {
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
        ter = new TERenderer();
        ter.initialize(width, height);
        random = new Random(seed);
        TETile[][] randomTiles = new TETile[width][height];
        fillWithRandomTiles(randomTiles);
        currentState = randomTiles;
    }

    /**
     * Constructor for loading in the state of the game from the
     * given filename and initializing it.
     * 中文：从指定文件名载入游戏状态并进行初始化。
     * @param filename
     */
    public GameOfLife(String filename) {
        this.currentState = loadBoard(filename);
        ter = new TERenderer();
        ter.initialize(width, height);
    }

    /**
     * Constructor for loading in the state of the game from the
     * given filename and initializing it. For testing purposes only, so
     * do not modify.
     * 中文：从指定文件载入状态的测试专用构造方法；请勿修改。
     * @param filename
     */
    public GameOfLife(String filename, boolean test) {
        this.currentState = loadBoard(filename);
    }

    /**
     * Initializes our world without using StdDraw. For testing purposes only,
     * so do not modify.
     * 中文：不使用 StdDraw 初始化世界，仅供测试；请勿修改。
     * @param seed
     */
    public GameOfLife(long seed, boolean test) {
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
        random = new Random(seed);
        TETile[][] randomTiles = new TETile[width][height];
        fillWithRandomTiles(randomTiles);
        currentState = randomTiles;
    }

    /**
     * Initializes our world with a given TETile[][] without using StdDraw.
     * For testing purposes only, so do not modify.
     * 中文：用给定的 TETile[][] 且不使用 StdDraw 初始化世界，
     * 仅供测试；请勿修改。
     * @param tiles
     * @param test
     */
    public GameOfLife(TETile[][] tiles, boolean test) {
        TETile[][] transposeState = transpose(tiles);
        this.currentState = flip(transposeState);
        this.width = tiles[0].length;
        this.height = tiles.length;
    }

    /**
     * Flips the matrix along the x-axis.
     * 中文：沿 x 轴翻转矩阵。
     * @param tiles
     * @return
     */
    private TETile[][] flip(TETile[][] tiles) {
        int w = tiles.length;
        int h = tiles[0].length;

        TETile[][] rotateMatrix = new TETile[w][h];
        int y = h - 1;
        for (int j = 0; j < h; j++) {
            for (int i = 0; i < w; i++) {
                rotateMatrix[i][y] = tiles[i][j];
            }
            y--;
        }
        return rotateMatrix;
    }

    /**
     * Transposes the tiles.
     * 中文：转置 tiles 矩阵。
     * @param tiles
     * @return
     */
    private TETile[][] transpose(TETile[][] tiles) {
        int w = tiles[0].length;
        int h = tiles.length;

        TETile[][] transposeState = new TETile[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                transposeState[x][y] = tiles[y][x];
            }
        }
        return transposeState;
    }

    /**
     * Runs the game. You don't have to worry about how this method works.
     * DO NOT MODIFY THIS METHOD!
     * 中文：运行游戏。不需要理解此方法的工作原理，也不要修改它！
     */
    public void runGame() {
        boolean paused = false;
        long evoTimestamp = System.currentTimeMillis();
        long pausedTimestamp = System.currentTimeMillis();
        long clickTimestamp = System.currentTimeMillis();
        while (true) {
            if (!paused && System.currentTimeMillis() - evoTimestamp > 250) {
                evoTimestamp = System.currentTimeMillis();
                currentState = nextGeneration(currentState);
            }
            if (System.currentTimeMillis() - prevFrameTimestep > 17) {
                prevFrameTimestep = System.currentTimeMillis();

                double mouseX = StdDraw.mouseX();
                double mouseY = StdDraw.mouseY();
                int tileX = (int) mouseX;
                int tileY = (int) mouseY;

                TETile currTile = currentState[tileX % width][tileY % height];

                if (StdDraw.isMousePressed() && System.currentTimeMillis() - clickTimestamp > 250) {
                    clickTimestamp = System.currentTimeMillis();
                    if (currTile == Tileset.CELL) {
                        currentState[tileX][tileY] = Tileset.NOTHING;
                    } else {
                        currentState[tileX][tileY] = Tileset.CELL;
                    }
                }
                if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && System.currentTimeMillis() - pausedTimestamp > 500) {
                    pausedTimestamp = System.currentTimeMillis();
                    paused = !paused;
                }
                if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
                    saveBoard();
                    System.exit(0);
                }
                ter.renderFrame(currentState);
            }
        }
    }


    /**
     * Fills the given 2D array of tiles with RANDOM tiles.
     * 中文：使用随机图块填充给定的二维数组。
     * @param tiles
     */
    public void fillWithRandomTiles(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = randomTile();
            }
        }
    }

    /**
     * Fills the 2D array of tiles with NOTHING tiles.
     * 中文：使用 NOTHING 图块填充二维数组。
     * @param tiles
     */
    public void fillWithNothing(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    /**
     * Selects a random tile, with a 50% change of it being a CELL
     * and a 50% change of being NOTHING.
     * 中文：随机选择图块：50% 概率为 CELL，50% 概率为 NOTHING。
     */
    private TETile randomTile() {
        // The following call to nextInt() uses a bound of 3 (this is not a seed!) so
        // the result is bounded between 0, inclusive, and 3, exclusive. (0, 1, or 2)
        // 中文：下面的 nextInt() 调用使用界限参数（不是随机种子），
        // 因此返回值位于包含 0、不包含上界的范围内。
        int tileNum = random.nextInt(2);
        return switch (tileNum) {
            case 0 -> Tileset.CELL;
            default -> Tileset.NOTHING;
        };
    }

    /**
     * Returns the current state of the board.
     * 中文：返回棋盘的当前状态。
     * @return
     */
    public TETile[][] returnCurrentState() {
        return currentState;
    }

    /**
     * At each timestep, the transitions will occur based on the following rules:
     *  1.Any live cell with fewer than two live neighbors dies, as if by underpopulation.
     *  2.Any live cell with two or three neighbors lives on to the next generation.
     *  3.Any live cell with more than three neighbors dies, as if by overpopulation,
     *  4.Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     * 中文：每个时间步按以下规则转换：
     *  1. 活细胞的活邻居少于两个时，因人口不足而死亡。
     *  2. 活细胞有两个或三个活邻居时，存活到下一代。
     *  3. 活细胞的活邻居多于三个时，因过度拥挤而死亡。
     *  4. 死细胞恰好有三个活邻居时，因繁殖而变为活细胞。
     * @param tiles
     * @return
     */
    public TETile[][] nextGeneration(TETile[][] tiles) {
        TETile[][] nextGen = new TETile[width][height];
        // The board is filled with Tileset.NOTHING
        // 中文：先用 Tileset.NOTHING 填充新棋盘。
        fillWithNothing(nextGen);

        // TODO: Implement this method so that the described transitions occur.
        // 中文：实现此方法，使上述状态转换能够发生。
        // TODO: The current state is represented by TETiles[][] tiles and the next
        // 中文：当前状态由 TETile[][] tiles 表示，而下一
        // TODO: state/evolution should be returned in TETile[][] nextGen.
        // 中文：状态（演化结果）应通过 TETile[][] nextGen 返回。




        // TODO: Returns the next evolution in TETile[][] nextGen.
        // 中文：返回 TETile[][] nextGen 中的下一代演化结果。
        return null;
    }

    /**
     * Helper method for saveBoard without rendering and running the game.
     * 中文：在不渲染、不运行游戏的情况下调用 saveBoard 的辅助方法。
     * @param tiles
     */
    public void saveBoard(TETile[][] tiles) {
        TETile[][] transposeState = transpose(tiles);
        this.currentState = flip(transposeState);
        this.width = tiles[0].length;
        this.height = tiles.length;
        saveBoard();
    }

    /**
     * Saves the state of the current state of the board into the
     * save.txt file (make sure it's saved into this specific file).
     * 0 represents NOTHING, 1 represents a CELL.
     * 中文：将当前棋盘状态保存到指定的 save.txt。
     * 0 表示 NOTHING，1 表示 CELL。
     */
    public void saveBoard() {
        // TODO: Save the dimensions of the board into the first line of the file.
        // 中文：将棋盘尺寸保存到文件的第一行。
        // TODO: The width and height should be separated by a space, and end with "\n".
        // 中文：宽度和高度之间应以空格分隔，并以 "\n" 结尾。



        // TODO: Save the current state of the board into save.txt. You should
        // 中文：将棋盘的当前状态保存到 save.txt。你应该
        // TODO: use the provided FileUtils functions to help you. Make sure
        // 中文：使用提供的 FileUtils 函数来辅助完成。请确保
        // TODO: the orientation is correct! Each line in the board should
        // 中文：方向正确！棋盘中的每一行都应该
        // TODO: end with a new line character.
        // 中文：以换行符结尾。





    }

    /**
     * Loads the board from filename and returns it in a 2D TETile array.
     * 0 represents NOTHING, 1 represents a CELL.
     * 中文：从 filename 载入棋盘并以二维 TETile 数组返回。
     * 0 表示 NOTHING，1 表示 CELL。
     */
    public TETile[][] loadBoard(String filename) {
        // TODO: Read in the file.
        // 中文：读入文件。

        // TODO: Split the file based on the new line character.
        // 中文：按换行符拆分文件内容。

        // TODO: Grab and set the dimensions from the first line.
        // 中文：从第一行获取并设置棋盘尺寸。

        // TODO: Create a TETile[][] to load the board from the file into
        // 中文：创建一个 TETile[][]，用于载入文件中的棋盘，
        // TODO: and any additional variables that you think might help.
        // 中文：并创建你认为可能有帮助的其他变量。


        // TODO: Load the state of the board from the given filename. You can
        // 中文：从给定文件名所指的文件中载入棋盘状态。你可以
        // TODO: use the provided builder variable to help you and FileUtils
        // 中文：使用提供的 builder 变量以及 FileUtils
        // TODO: functions. Make sure the orientation is correct!
        // 中文：函数来辅助完成。请确保方向正确！




        // TODO: Return the board you loaded. Replace/delete this line.
        // 中文：返回你载入的棋盘。替换或删除此行。
        return null;
    }

    /**
     * This is where we run the program. DO NOT MODIFY THIS METHOD!
     * 中文：程序从此处运行。请勿修改此方法！
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            // Read in the board from a file.
            // 中文：从文件读入棋盘。
            if (args[0].equals("-l")) {
                GameOfLife g = new GameOfLife(args[1]);
                g.runGame();
            }
            System.out.println("Verify your program arguments!");
            System.exit(0);
        } else {
            long seed = args.length > 0 ? Long.parseLong(args[0]) : (new Random()).nextLong();
            GameOfLife g = new GameOfLife(seed);
            g.runGame();
        }
    }
}
