import core.AutograderBuddy;
import edu.princeton.cs.algs4.StdDraw;
import org.junit.jupiter.api.Test;
import tileengine.TERenderer;
import tileengine.TETile;

public class WorldGenTests {
    @Test
    public void basicTest() {
        // put different seeds here to test different worlds
        // 中文：在此处填入不同的随机种子，以测试不同世界。
        TETile[][] tiles = AutograderBuddy.getWorldFromInput("n1234567890123456789s");

        TERenderer ter = new TERenderer();
        ter.initialize(tiles.length, tiles[0].length);
        ter.renderFrame(tiles);
        StdDraw.pause(5000); // pause for 5 seconds so you can see the output
        // 中文：暂停 5 秒，便于查看输出。
    }

    @Test
    public void basicInteractivityTest() {
        // TODO: write a test that uses an input like "n123swasdwasd"
        // 中文：编写一个使用类似 "n123swasdwasd" 输入的测试。
    }

    @Test
    public void basicSaveTest() {
        // TODO: write a test that calls getWorldFromInput twice, with "n123swasd:q" and with "lwasd"
        // 中文：编写一个测试，分别以 "n123swasd:q" 和 "lwasd" 为参数调用两次 getWorldFromInput。
    }
}
