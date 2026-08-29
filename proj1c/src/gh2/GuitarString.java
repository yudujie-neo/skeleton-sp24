package gh2;

// TODO: maybe more imports
// 中文：可能需要导入更多内容。

//Note: This file will not compile until you complete the Deque61B implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    // TODO: uncomment the following line once you're ready to start this portion
    // 中文：准备开始此部分后，取消下一行的注释。
    // private Deque61B<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Initialize the buffer with capacity = SR / frequency. You'll need to
        // 中文：以 capacity = SR / frequency 初始化缓冲区。你需要
        //       cast the result of this division operation into an int. For
        // 中文：将此除法运算的结果强制转换为 int。为了
        //       better accuracy, use the Math.round() function before casting.
        // 中文：获得更高的精度，请在强制转换前使用 Math.round() 函数。
        //       Your should initially fill your buffer with zeros.
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        // 中文：将缓冲区中的所有元素出队，并替换为随机数，
        //       between -0.5 and 0.5. You can get such a number by using:
        // 中文：随机数应介于 -0.5 和 0.5 之间。可以使用以下方式生成：
        //       double r = Math.random() - 0.5;
        // 中文：double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other. This does not mean that you need to check that the numbers
        //       are different from each other. It means you should repeatedly call
        //       Math.random() - 0.5 to generate new random numbers for each array index.
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        // 中文：将队首样本出队，并将一个新样本入队；该新样本是
        //       the average of the two multiplied by the DECAY factor.
        // 中文：两个样本平均值乘以 DECAY 衰减因子的结果。
        //       **Do not call StdAudio.play().**
        // 中文：**不要调用 StdAudio.play()。**
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        // 中文：返回正确的内容。
        return 0;
    }
}
    // TODO: Remove all comments that say TODO when you're done.
    // 中文：完成后删除所有包含 TODO 的注释。
