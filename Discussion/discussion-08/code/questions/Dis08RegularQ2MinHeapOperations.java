package questions;

import java.util.ArrayList;
import java.util.List;

/** CS 61B Spring 2024 - Discussion 08, Regular Question 2. */
public class Dis08RegularQ2MinHeapOperations {

    /*
     * 2a - English - Official question
     * Assume that we have a binary min-heap (smallest value on top) data
     * structure called MinHeap that has properly implemented the insert and
     * removeMin methods. Draw the heap and its corresponding array
     * representation after each of the operations below:
     *
     * MinHeap<Character> h = new MinHeap<>();
     * h.insert('f');
     * h.insert('h');
     * h.insert('d');
     * h.insert('b');
     * h.insert('c');
     * h.removeMin();
     * h.removeMin();
     *
     * 2a - 中文翻译
     * 假设 MinHeap 是正确实现的二叉最小堆。请画出上述每一步操作后的
     * 堆，以及相应的数组表示。
     *
     * Your answer / 你的答案：TODO
     * Drawings / 图示：请在纸上或 Markdown 中完成；官方提供的堆状态图保留在
     * ../../assets/solution-02a-heaps-part-1.png 和 part-2.png。
     */
    public static void runOfficialOperations() {
        MinHeap<Character> h = new MinHeap<>();
        h.insert('f');
        h.insert('h');
        h.insert('d');
        h.insert('b');
        h.insert('c');
        h.removeMin();
        h.removeMin();
        // TODO: Record/draw the heap and array after each operation.
    }

    /*
     * 2b - English - Official question
     * Your friendly TA Allen challenges you to create an integer max-heap
     * without writing a whole new data structure. Can you use your min-heap to
     * mimic the behavior of a max-heap? Specifically, we want to be able to get
     * the largest item in the heap in constant time, and add things to the heap
     * in Theta(log n) time, as a normal max heap should.
     *
     * Hint: You should treat the MinHeap as a black box and think about how you
     * should modify the arguments/return values of the heap functions.
     *
     * 2b - 中文翻译
     * 不重新实现整套数据结构，如何把整数 MinHeap 当作黑盒来模拟最大堆？
     * 要求常数时间取得最大值，并以 Theta(log n) 时间插入。请考虑如何转换
     * 传给底层堆的参数和从底层堆返回的值。
     *
     * Your answer / 你的答案：TODO
     */
    public static final class MaxHeapUsingMinHeap {
        private final MinHeap<Integer> backing = new MinHeap<>();

        public void insert(int value) {
            // TODO: Transform value before delegating to backing.insert(...).
            throw new UnsupportedOperationException("TODO: max-heap insert");
        }

        public int getMax() {
            // TODO: Read and transform the backing minimum.
            throw new UnsupportedOperationException("TODO: getMax");
        }

        public int removeMax() {
            // TODO: Remove and transform the backing minimum.
            throw new UnsupportedOperationException("TODO: removeMax");
        }
    }

    /**
     * Compilation scaffold / 编译脚手架（非官方题目内容）。
     * A small generic binary min-heap implementing the black-box contract that
     * the official question assumes. Its 1-based array uses index 0 as a null
     * placeholder. It intentionally does not print the requested 2a states.
     */
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

        /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
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
}
