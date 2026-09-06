package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 * 中文：使用声音合成器模拟拨动吉他弦声音的客户端程序。
 */
public class GuitarHeroLite {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    private static final int WIDTH = 512;
    private static final int HEIGHT = 512;

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C
         * 中文：创建两根吉他弦，分别对应标准音 A 和 C。 */
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);
        StdDraw.setCanvasSize(WIDTH, HEIGHT);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.05);
        StdDraw.text(WIDTH / 2, (HEIGHT + 16) / 2, "Play the guitar!");
        StdDraw.text(WIDTH / 2, (HEIGHT - 32) / 2, "Type A or C");
        while (true) {

            /* check if the user has typed a key; if so, process it
             * 中文：检查用户是否按键；如果按下，则处理该按键。 */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 'a') {
                    StdDraw.clear();
                    StdDraw.text(WIDTH / 2, HEIGHT / 2, "A");

                    StdDraw.show();
                    stringA.pluck();

                } else if (key == 'c') {
                    StdDraw.clear();
                    StdDraw.text(WIDTH / 2, HEIGHT / 2, "C");
                    StdDraw.show();

                    stringC.pluck();
                }
            }

            /* compute the superposition of samples
             * 中文：计算各个样本的叠加值。 */
            double sample = stringA.sample() + stringC.sample();

            /* play the sample on standard audio
             * 中文：通过标准音频输出播放样本。 */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step
             * 中文：将每根吉他弦的模拟推进一个时间步。 */
            stringA.tic();
            stringC.tic();

        }
    }
}

