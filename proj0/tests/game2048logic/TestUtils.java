package game2048logic;

import game2048rendering.Side;

import static com.google.common.truth.Truth.assertWithMessage;

public class TestUtils {

    /**
     * Checks that performing a tilt in the specified direction on the before
     * Model results in the after Model
     * 中文：检查对 before Model 朝指定方向倾斜后，结果是否等于 after Model。
     */
    public static void checkTilt(Model before, Model after, Side direction) {
        String prevBoard = before.toString();
        before.tiltWrapper(direction);
        String errMsg = String.format("棋盘结果不正确。朝 %s 倾斜之前，棋盘为：%s%n"
                        + "调用 tilt 后，期望棋盘为：%s%n但实际棋盘为：%s。",
                direction, prevBoard, after, before);
        assertWithMessage(errMsg).that(before).isEqualTo(after);
    }
}
