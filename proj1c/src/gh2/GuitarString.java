package gh2;

import deque.ArrayDeque61B;
import deque.Deque61B;

public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday.
     */
    private static final int SR = 44100;      // Sampling Rate / 中文：采样率
    private static final double DECAY = .996; // energy decay factor / 中文：能量衰减因子

    /* Buffer for storing sound data. 中文：用于保存声音数据的缓冲区。 */
    private final Deque61B<Double> buffer;

    /** Create a guitar string of the given frequency.
     * 中文：创建具有给定频率的吉他弦。 */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayDeque61B<>();
        for (int i = 0; i < capacity; i++) {
            buffer.addFirst(0.0);
        }
    }

    /** Pluck the guitar string by replacing the buffer with white noise.
     * 中文：用白噪声替换缓冲区内容，以模拟拨动吉他弦。 */
    public void pluck() {
        for (int i = 0; i < buffer.size(); i++) {
            buffer.removeLast();
            buffer.addFirst(Math.random() - 0.5);
        }
    }

    /** Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     * 中文：执行一次 Karplus-Strong 算法迭代，使模拟前进一步。
     */
    public void tic() {
        double first = buffer.removeFirst();
        double newSample = (first + buffer.get(0)) / 2;
        buffer.addLast(newSample * DECAY);
    }

    /** Return the double at the front of the buffer.
     * 中文：返回缓冲区队首的 double 值。 */
    public double sample() {
        return buffer.get(0);
    }
}
