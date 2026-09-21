package solutions;

import java.util.ArrayList;
import java.util.List;

/** Official solutions and executable checks for Discussion 08 Regular Q2. */
public class Dis08RegularQ2MinHeapOperationsSolution {

    /*
     * 2a - Official solution / 官方答案
     * after inserting 'f': [-, 'f']
     * after inserting 'h': [-, 'f', 'h']
     * after inserting 'd': [-, 'd', 'h', 'f']
     * after inserting 'b': [-, 'b', 'd', 'f', 'h']
     * after inserting 'c': [-, 'b', 'c', 'f', 'h', 'd']
     * after removing min: [-, 'c', 'd', 'f', 'h']
     * after removing min: [-, 'd', 'h', 'f']
     *
     * 中文解释：插入先追加到完全二叉树末尾，再向上交换；删除最小值时
     * 用末尾元素替换根，再向下交换。官方逐步树图保留在 Markdown/assets。
     */
    public static List<List<Character>> officialStates() {
        MinHeap<Character> heap = new MinHeap<>();
        List<List<Character>> states = new ArrayList<>();
        heap.insert('f');
        states.add(heap.snapshot());
        heap.insert('h');
        states.add(heap.snapshot());
        heap.insert('d');
        states.add(heap.snapshot());
        heap.insert('b');
        states.add(heap.snapshot());
        heap.insert('c');
        states.add(heap.snapshot());
        heap.removeMin();
        states.add(heap.snapshot());
        heap.removeMin();
        states.add(heap.snapshot());
        return states;
    }

    /*
     * 2b - Official solution / 官方答案
     * Yes. For every insert operation, negate the number and add it to the
     * min-heap.
     *
     * For a removeMax operation call removeMin on the min-heap and negate the
     * number returned. Any number negated twice is itself, and since we store
     * the negation of numbers, the order is now reversed (what used to be the
     * max is now the min).
     *
     * Small note: There's actually one exception in Java to what we said about
     * negation above: -2^31, the most negative number that we can represent in
     * Java, will not be itself when negated twice. This is mostly due to number
     * representation constraints in code, but you don't need to worry about
     * that for this question.
     *
     * 中文解释：映射 x -> -x 会反转整数次序；插入和删除时各转换一次。
     *
     * Supplemental analysis / 补充分析（非官方答案）：
     * Integer.MIN_VALUE 的相反数超出 int 正上界并溢出，所以这个包装器并
     * 不支持该值。若要覆盖全部 int，可把底层值扩宽为 long，或使用反序
     * Comparator；这里保留官方的取负方案和限制。
     */
    public static final class MaxHeapUsingMinHeap {
        private final MinHeap<Integer> backing = new MinHeap<>();

        public void insert(int value) {
            if (value == Integer.MIN_VALUE) {
                throw new IllegalArgumentException("negation overflows for Integer.MIN_VALUE");
            }
            backing.insert(-value);
        }

        public int getMax() {
            return -backing.getMin();
        }

        public int removeMax() {
            return -backing.removeMin();
        }
    }

    /** Independent executable implementation matching the assumed MinHeap. */
    public static final class MinHeap<T extends Comparable<? super T>> {
        private final List<T> items = new ArrayList<>();

        public MinHeap() {
            items.add(null);
        }

        public void insert(T value) {
            items.add(value);
            swim(items.size() - 1);
        }

        public T getMin() {
            ensureNotEmpty();
            return items.get(1);
        }

        public T removeMin() {
            ensureNotEmpty();
            T result = items.get(1);
            T last = items.remove(items.size() - 1);
            if (items.size() > 1) {
                items.set(1, last);
                sink(1);
            }
            return result;
        }

        public List<T> snapshot() {
            return List.copyOf(items.subList(1, items.size()));
        }

        private void swim(int index) {
            while (index > 1 && less(index, index / 2)) {
                swap(index, index / 2);
                index /= 2;
            }
        }

        private void sink(int index) {
            while (2 * index < items.size()) {
                int child = 2 * index;
                if (child + 1 < items.size() && less(child + 1, child)) {
                    child += 1;
                }
                if (!less(child, index)) {
                    return;
                }
                swap(index, child);
                index = child;
            }
        }

        private boolean less(int a, int b) {
            return items.get(a).compareTo(items.get(b)) < 0;
        }

        private void swap(int a, int b) {
            T temporary = items.get(a);
            items.set(a, items.get(b));
            items.set(b, temporary);
        }

        private void ensureNotEmpty() {
            if (items.size() == 1) {
                throw new IllegalStateException("heap is empty");
            }
        }
    }

    public static void main(String[] args) {
        List<List<Character>> expected = List.of(
            List.of('f'),
            List.of('f', 'h'),
            List.of('d', 'h', 'f'),
            List.of('b', 'd', 'f', 'h'),
            List.of('b', 'c', 'f', 'h', 'd'),
            List.of('c', 'd', 'f', 'h'),
            List.of('d', 'h', 'f')
        );
        if (!officialStates().equals(expected)) {
            throw new AssertionError("Unexpected 2a heap states: " + officialStates());
        }
        MaxHeapUsingMinHeap heap = new MaxHeapUsingMinHeap();
        heap.insert(4);
        heap.insert(9);
        heap.insert(-2);
        if (heap.getMax() != 9 || heap.removeMax() != 9 || heap.getMax() != 4) {
            throw new AssertionError("2b max-heap wrapper failed");
        }
    }
}
