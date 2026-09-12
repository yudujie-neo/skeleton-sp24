import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B <T> implements Deque61B<T>{



    private Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        this.sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    private class Node {
        Node prev;
        T data;
        Node next;

        public Node(T data) {
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

        // sentinel a b sentinel
    }

    @Override
    public void addLast(T x) {
        size++;
        Node newNode = new Node(x);
        newNode.prev = sentinel.prev;
        newNode.next = sentinel;
        sentinel.prev.next = newNode;
        sentinel.next= newNode;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node curr = sentinel.next;
        for (int i = 0 ; i < size ; i++ ) {
            list.add(curr.data);
            curr = curr.next;
        }

        return list;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
           return null;
       }

       @Override
       public T get(int index) {
           return null;
       }

       @Override
       public T getRecursive(int index) {
           return null;
       }

}
