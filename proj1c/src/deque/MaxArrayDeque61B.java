package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {

    private final Comparator<T> comparator;

    /** Creates a deque that uses {@code c} as its default comparator. */
    public MaxArrayDeque61B(Comparator<T> c) {
        comparator = c;
    }

    /** Returns the maximum item according to the default comparator, or null if empty. */
    public T max() {
        return max(comparator);
    }

    /** Returns the maximum item according to {@code c}, or null if empty. */
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T max = get(0);
        for (int i = 1; i < size(); i++) {
            T item = get(i);
            if (c.compare(max, item) < 0) {
                max = item;
            }
        }
        return max;
    }
}
