package hashmap;

import java.util.Collection;

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
    /* 中文：实例变量。 */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    // 中文：你可能还需要定义更多实例变量！

    /** Constructors */
    /** 中文：构造方法。 */
    public MyHashMap() { }

    public MyHashMap(int initialCapacity) { }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     * 中文：创建底层数组初始容量为 initialCapacity 的 MyHashMap。
     * 装载因子（元素数 / 桶数）应始终小于或等于 loadFactor。
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) { }

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
        return null;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // 中文：实现下面 Map61B 接口中的方法。
    // Your code won't compile until you do so!
    // 中文：完成这些方法之前，代码无法编译！

}
