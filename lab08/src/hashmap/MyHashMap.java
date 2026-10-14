package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  中文：基于哈希表的 Map 实现。假设永远不会插入 null 键，
 *  并且在 remove() 之后不会缩小容量。
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     * 中文：用于存储键值对的 protected 辅助类；protected 修饰符允许子类访问它。
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size = 0;
    private double loadFactor = 0.75;

    /** Constructors */
    /** 中文：构造方法。 */
    public MyHashMap() {
        buckets = new Collection[16];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialCapacity) {
        buckets = new Collection[initialCapacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     * 中文：创建底层数组初始容量为 initialCapacity 的 MyHashMap。
     * 装载因子（元素数 / 桶数）应始终小于或等于 loadFactor。
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.loadFactor = loadFactor;
        buckets = new Collection[initialCapacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     *
     * 中文：返回一个用作哈希桶的数据结构。哈希桶只需支持插入（add）、
     * 删除（remove）和迭代（iterator）；这些要求针对桶本身，而非整个哈希映射。
     * java.util.Collection 支持这些操作，因此大多数 Java 集合类都可作为桶。
     * 子类可重写本方法以改用其他桶类型。务必调用这个工厂方法，
     * 不要用 new 操作符自行创建桶！
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        // 中文：补全此方法。
        return new LinkedList<Node>();
    }

    @Override
    public void put(K key, V value) {

        int p = Math.floorMod(key.hashCode(), buckets.length);
        for(Node n : buckets[p]) {
            if (n.key.equals(key)){
                n.value = value;
                return;
            }
        }

        size ++;
        buckets[p].add(new Node(key, value));

        // size/length >= lo
        if (size > buckets.length * loadFactor) {
            //resize
            Collection<Node>[] newBuckets = new Collection[buckets.length * 2];
            System.arraycopy(buckets, 0, newBuckets, 0, buckets.length);
            for (int i = buckets.length; i < newBuckets.length; i++) {
                newBuckets[i] = createBucket();
            }
            buckets = newBuckets;

            for(int i = 0; i < buckets.length; i++) {
                Collection<Node> temp = createBucket();
                for (Node node : buckets[i]) {
                    int entry = Math.floorMod(node.key.hashCode(), buckets.length);
                    if (entry == i) {
                        temp.add(node);
                    }
                    else {
                        buckets[entry].add(node);
                    }
                }
                buckets[i] = temp;
            }
        }
    }

    @Override
    public V get(K key) {
        int entry = Math.floorMod(key.hashCode(), buckets.length);
        for(Node n : buckets[entry]) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int entry = Math.floorMod(key.hashCode(), buckets.length);
        for(Node n : buckets[entry]) {
            if (n.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        buckets = new Collection[buckets.length];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
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
