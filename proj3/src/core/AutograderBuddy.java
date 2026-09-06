package core;

import tileengine.TETile;
import tileengine.Tileset;

public class AutograderBuddy {

    /**
     * Simulates a game, but doesn't render anything or call any StdDraw
     * methods. Instead, returns the world that would result if the input string
     * had been typed on the keyboard.
     * 中文：模拟一次游戏，但不渲染任何内容，也不调用任何 StdDraw 方法。
     * 改为返回“假如在键盘上输入了该字符串”后所得的世界。
     *
     * Recall that strings ending in ":q" should cause the game to quit and
     * save. To "quit" in this method, save the game to a file, then just return
     * the TETile[][]. Do not call System.exit(0) in this method.
     * 中文：请记住，以 ":q" 结尾的字符串应使游戏保存并退出。
     * 在本方法中，“退出”指先将游戏保存到文件，再直接返回 TETile[][]。
     * 不要在本方法中调用 System.exit(0)。
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     * 中文参数：input 是提供给程序的输入字符串。
     * 中文返回：表示世界状态的二维 TETile[][]。
     */
    public static TETile[][] getWorldFromInput(String input) {

        throw new RuntimeException("Please fill out AutograderBuddy!");

    }


    /**
     * Used to tell the autograder which tiles are the floor/ground (including
     * any lights/items resting on the ground). Change this
     * method if you add additional tiles.
     * 中文：用于告诉自动评测器哪些图块属于地面，包括地面上的灯或物品。
     * 如果添加了其他地面图块，请修改此方法。
     */
    public static boolean isGroundTile(TETile t) {
        return t.character() == Tileset.FLOOR.character()
                || t.character() == Tileset.AVATAR.character()
                || t.character() == Tileset.FLOWER.character();
    }

    /**
     * Used to tell the autograder while tiles are the walls/boundaries. Change
     * this method if you add additional tiles.
     * 中文：用于告诉自动评测器哪些图块属于墙或边界。
     * 如果添加了其他边界图块，请修改此方法。
     */
    public static boolean isBoundaryTile(TETile t) {
        return t.character() == Tileset.WALL.character()
                || t.character() == Tileset.LOCKED_DOOR.character()
                || t.character() == Tileset.UNLOCKED_DOOR.character();
    }
}
