/* Imports the required audio library from the
 * edu.princeton.cs.algs4 package.
 * 中文：从 edu.princeton.cs.algs4 包导入所需的音频库。 */
import edu.princeton.cs.algs4.StdAudio;
import org.junit.jupiter.api.Test;
import gh2.GuitarString;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Tests the GuitarString class.
 *  中文：测试 GuitarString 类。
 *  @author Josh Hug
 */
public class TestGuitarString  {

    @Test
    public void testPluckTheAString() {
        double CONCERT_A = 440.0;
        GuitarString aString = new GuitarString(CONCERT_A);
        aString.pluck();
        for (int i = 0; i < 50000; i += 1) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }

    @Test
    public void testSample() {
        GuitarString s = new GuitarString(100);
        assertThat(s.sample()).isEqualTo(0.0);
        assertThat(s.sample()).isEqualTo(0.0);
        assertThat(s.sample()).isEqualTo(0.0);
        s.pluck();

        double sample = s.sample();
        assertWithMessage("After plucking, your samples should not be 0 / 拨弦后样本不应为 0")
                .that(sample).isNotEqualTo(0);

        String errorMsg = "Sample should not change the state of your string / sample() 不应改变吉他弦状态";
        assertWithMessage(errorMsg).that(s.sample()).isWithin(0.0).of(sample);
        assertWithMessage(errorMsg).that(s.sample()).isWithin(0.0).of(sample);
    }

    @Test
    public void testTic() {
        GuitarString s = new GuitarString(100);
        assertThat(s.sample()).isEqualTo(0.0);
        assertThat(s.sample()).isEqualTo(0.0);
        assertThat(s.sample()).isEqualTo(0.0);
        s.pluck();

        double sample1 = s.sample();
        assertWithMessage("After plucking, your samples should not be 0 / 拨弦后样本不应为 0")
                .that(sample1).isNotEqualTo(0);

        s.tic();
        String errorMsg = "After tic(), your samples should not stay the same / tic() 后样本不应保持不变";
        assertWithMessage(errorMsg).that(s.sample()).isNotEqualTo(sample1);
    }

    @Test
    public void testTicCalculations() {
        // Create a GuitarString of frequency 11025, which
        // is a Deque61B of length 4.
        // 中文：创建频率为 11025 的 GuitarString，其 Deque61B 缓冲区长度为 4。
        GuitarString s = new GuitarString(11025);
        s.pluck();

        // Record the front four values, ticcing as we go.
        // 中文：逐次调用 tic，并记录队首的前四个值。
        double s1 = s.sample();
        s.tic();
        double s2 = s.sample();
        s.tic();
        double s3 = s.sample();
        s.tic();
        double s4 = s.sample();

        // If we tic once more, it should be equal to 0.996*0.5*(s1 + s2)
        // 中文：再调用一次 tic 后，新样本应等于 0.996 * 0.5 * (s1 + s2)。
        s.tic();

        double s5 = s.sample();
        double expected = 0.996 * 0.5 * (s1 + s2);

        // Check that new sample is correct, using tolerance of 0.001.
        // 中文：以 0.001 的允许误差检查新样本是否正确。
        String errorMsg = "Wrong tic value. Try running testTic in TestGuitarString.java / "
                + "tic 值错误，请尝试运行 TestGuitarString.java 中的 testTic";
        assertWithMessage(errorMsg).that(s5).isWithin(0.001).of(expected);
    }
}

