package tetris;

import tileengine.TETile;
import tileengine.Tileset;

/**
 *  Provides the logic for movement of Tetris pieces.
 *  中文：提供俄罗斯方块的移动逻辑。
 *
 *  @author Erik Nelson, Omar Yu, and Jasmine Lin
 */

public class Movement {

    private int WIDTH;

    private int GAME_HEIGHT;

    Tetris tetris;

    public Movement (int width, int game_height, Tetris tetris) {
        this.WIDTH = width;
        this.GAME_HEIGHT = game_height;
        this.tetris = tetris;
    }

    /**
     * Rotate the current Tetromino 90 degrees to the right (clockwise).
     * 中文：将当前方块向右（顺时针）旋转 90 度。
     */
    public void rotateRight() {
        rotate(Rotation.RIGHT);
    }

    /**
     * Rotate the current Tetromino 90 degrees to the left (counter-clockwise).
     * 中文：将当前方块向左（逆时针）旋转 90 度。
     */
    public void rotateLeft() {
        rotate(Rotation.LEFT);
    }

    /**
     * Attempts to move the current Tetromino by a shift of deltaX and deltaY.
     * If the Tetromino cannot move and will collide with a boundary or existing piece,
     * it is placed at its current position and nullified so a new Tetromino can spawn.
     * 中文：尝试将当前方块移动 deltaX、deltaY。如果移动会越界或与已有方块冲突，
     * 则将它固定在当前位置并置空，以便生成新方块。
     * @param deltaX
     * @param deltaY
     */
    public void tryMove(int deltaX, int deltaY) {
        Tetromino t = tetris.getCurrentTetromino();

        if (canMove(deltaX, deltaY)) {
            t.pos.x += deltaX;
            t.pos.y += deltaY;
        } else {
            if (deltaY < 0) {
                TETile[][] board = tetris.getBoard();
                Tetromino.draw(t, board, t.pos.x, t.pos.y);
                tetris.fillAux();

                tetris.setAuxTrue();
                tetris.setCurrentTetromino();
            }
        }
    }

    /**
     * Checks whether moving the current Tetromino by a shift of deltaX and deltaY
     * is valid, i.e. within bounds and does not collide with other pieces.
     * 中文：检查当前方块移动 deltaX、deltaY 后是否合法，
     * 即是否未越界且不与其他方块冲突。
     * @param deltaX
     * @param deltaY
     * @return a boolean representing if the move is possible or not
     */
    public boolean canMove(int deltaX, int deltaY) {
        Tetromino t = tetris.getCurrentTetromino();

        for (int tx = 0; tx < t.width; tx++){
            for (int ty = 0; ty < t.height; ty++){
                if (t.shape[tx][ty]) {

                    // Out of bounds check
                    // 中文：越界检查。
                    if (t.pos.x + tx + deltaX >= WIDTH ||
                            t.pos.x + tx + deltaX < 0 ||
                            t.pos.y + ty + deltaY >= GAME_HEIGHT ||
                            t.pos.y + ty + deltaY < 0) {
                        return false;
                    }

                    // Board check
                    // 中文：棋盘占用检查。
                    TETile[][] board = tetris.getBoard();
                    if (board[t.pos.x + tx + deltaX][t.pos.y + ty + deltaY] != Tileset.NOTHING) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Moves the current Tetromino down one tile, if not able to move down,
     * set the block in place and allow for a new Tetromino to be spawned.
     * 中文：将当前方块向下移动一格；如果无法下移，
     * 则将它固定并允许生成新方块。
     */
    public void dropDown() {
        Tetromino t = tetris.getCurrentTetromino();

        if (canMove(0, -1)) {
            t.pos.y -= 1;
        } else {
            TETile[][] board = tetris.getBoard();
            Tetromino.draw(t, board, t.pos.x, t.pos.y);
            tetris.fillAux();

            tetris.setAuxTrue();
            tetris.setCurrentTetromino();
        }
    }

    /**
     * Checks whether rotating the current Tetromino is valid,
     * i.e. it will remain within bounds and does not rotate/collide into
     * other pieces.
     * 中文：检查将当前方块旋转为 newShape 后是否合法，
     * 即旋转后仍在边界内，且不与其他方块冲突。
     * @param newShape
     * @return a boolean representing if the rotation is possible or not
     */
    public boolean canRotate(boolean[][] newShape) {
        Tetromino t = tetris.getCurrentTetromino();
        boolean valid = true;
        for (int tx = 0; tx < newShape.length; tx++) {
            for (int ty = 0; ty < newShape[0].length; ty++) {
                if (newShape[tx][ty]) {
                    if (t.pos.x + tx < 0 || t.pos.y + ty < 0
                            || t.pos.x + tx >= tetris.getAuxiliary().length
                            || t.pos.y + ty >= tetris.getAuxiliary()[0].length
                            || tetris.getAuxiliary()[t.pos.x + tx][t.pos.y + ty] != Tileset.NOTHING) {
                        valid = false;
                    }
                }
            }
        }
        return valid;
    }

    /**
     * Rotation enum used to discern between left and right rotations.
     * 中文：用于区分左旋和右旋的枚举。
     */
    public enum Rotation {
        RIGHT, LEFT
    }

    /**
     * Attempts to rotate the current Tetromino by the given direction r (left or right).
     * If the Tetromino cannot rotate, it will remain in its current orientation.
     * 中文：尝试按给定方向 r（左或右）旋转当前方块。
     * 如果无法旋转，则保持当前方向。
     * @param r
     */
    public void rotate(Rotation r) {
        Tetromino t = tetris.getCurrentTetromino();
        int h = t.shape.length;
        int w = t.shape[0].length;
        boolean[][] newShape = new boolean[h][w];
        if (r == Rotation.LEFT) {
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++){
                    newShape[i][j] = t.shape[j][h - i - 1];
                }
            }
        } else if (r == Rotation.RIGHT) {
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    newShape[i][j] = t.shape[w - j - 1][i];
                }
            }
        }

        if (canRotate(newShape)) {
            t.shape = newShape;
            t.height = t.shape[0].length;
            t.width = t.shape.length;
        }
    }
}
