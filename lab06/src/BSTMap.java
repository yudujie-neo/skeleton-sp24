import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;
    private int size;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public void put(K key, V value) {
        Node node = new Node(key, value);
        Node current = root;
        if (root == null) {
            root = node;
            size++;
            return;
        }
        while (true) {
            if (current.key.compareTo(key) == 0 ) {
                current.value = value;
                break;
            }
            else if (current.key.compareTo(node.key) < 0) {
                if (current.right == null) {
                    current.right = node;
                    size++;
                    break;
                }
                current = current.right;
            }
            else if (current.key.compareTo(node.key) > 0) {
                if (current.left == null) {
                    current.left = node;
                    size++;
                    break;
                }
                current = current.left;
            }
        }
    }

    @Override
    public V get(K key) {
        Node curr = root;
        while (curr != null) {
            if (key.compareTo(curr.key) == 0) {
                return curr.value;
            }
            if (key.compareTo(curr.key) < 0) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }
        Node current = root;
        while (true) {
            if (key.compareTo(current.key) == 0) {
                return true;
            }
            if (key.compareTo(current.key) < 0) {
                if (current.left == null) {
                    return false;
                }
                current = current.left;
            }
            else {
                if (current.right == null) {
                    return false;
                }
                current = current.right;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }


}
