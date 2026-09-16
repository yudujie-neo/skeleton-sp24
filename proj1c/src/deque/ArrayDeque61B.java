package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Math.floorMod;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private T[] array;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque61B() {
        array = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    @Override
    public void addFirst(T x) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        array[nextFirst] = x;
        nextFirst = floorMod(nextFirst - 1, array.length);
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == array.length) {
            resize(array.length * 2);
        }
        array[nextLast] = x;
        nextLast = floorMod(nextLast + 1, array.length);
        size++;
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int curr = nextFirst + 1;

        for (int i = 0; i < size; i++) {
            if (curr == array.length) {
                curr = 0;
            }
            newArray[i] = array[curr];
            curr++;
        }
        array = newArray;
        nextFirst = array.length - 1;
        nextLast = size;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        int curr = nextFirst + 1;

        for (int i = 0; i < size; i++) {
            if (curr == array.length) {
                curr = 0;
            }
            list.add(array[curr]);
            curr++;
        }

        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else if (array.length > 15 && size * 4 <= array.length) {
            resize(array.length / 2);
        }
        size--;
        T oldFirst = array[floorMod(nextFirst + 1, array.length)];
        array[floorMod(nextFirst + 1, array.length)] = null;
        nextFirst = floorMod(nextFirst + 1, array.length);

        return oldFirst;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else if (array.length > 15 && size * 4 <= array.length) {
            resize(array.length / 2);
        }
        size--;
        nextLast = floorMod(nextLast - 1, array.length);
        T oldLast = array[nextLast];
        array[nextLast] = null;
        return oldLast;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        int curr = 0;
        if (index < array.length - (nextFirst + 1)) {
            curr = nextFirst + 1 + index;
        } else {
            curr = index - (array.length - (nextFirst + 1));
        }
        return array[curr];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque61BIterator();
    }

    private class ArrayDeque61BIterator implements Iterator<T> {

        private int curr;

        @Override
        public boolean hasNext() {
            return curr < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return get(curr++);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Deque61B<?> other)) {
            return false;
        }
        return dequeEquals(other);
    }

    @Override
    public int hashCode() {
        return toList().hashCode();
    }

    @Override
    public String toString() {
        return toList().toString();
    }
}
