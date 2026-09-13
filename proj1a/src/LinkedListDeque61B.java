import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private final Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    private class Node {
        Node prev;
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    @Override
    public void addFirst(T x) {
        size++;
        Node newNode = new Node(x);

        newNode.prev = sentinel;
        newNode.next = sentinel.next;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }

    @Override
    public void addLast(T x) {
        size++;
        Node newNode = new Node(x);
        newNode.prev = sentinel.prev;
        newNode.next = sentinel;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node curr = sentinel.next;
        for (int i = 0; i < size; i++) {
            list.add(curr.data);
            curr = curr.next;
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
        if (isEmpty()) {
            return null;
        }

        Node removedNode = sentinel.next;
        T removedItem = removedNode.data;
        // 推荐思路：先让剩余节点彼此相连，再清除被删除节点的引用，便于垃圾回收。
        sentinel.next = removedNode.next;
        sentinel.next.prev = sentinel;
        size--;
        removedNode.prev = null;
        removedNode.next = null;
        removedNode.data = null;

        return removedItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node removedNode = sentinel.prev;
        T removedItem = removedNode.data;
        sentinel.prev = removedNode.prev;
        sentinel.prev.next = sentinel;
        size--;
        removedNode.prev = null;
        removedNode.next = null;
        removedNode.data = null;

        return removedItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node curr = sentinel.next;
        // 只走 index 步，比遍历 size 次并在循环内部判断目标位置更直接。
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, Node curr) {
        if (index == 0) {
            return curr.data;
        }
        return getRecursiveHelper(index - 1, curr.next);
    }
}
