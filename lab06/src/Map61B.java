import java.util.Set;

/* Your implementation BSTMap should implement this interface. To do so,
 * append "implements Map61B<K, V>" to the end of your "public class..."
 * declaration, though you can and should use other type parameters when
 * necessary.
 */
/* 中文：你的 BSTMap 实现应实现此接口。为此，请在
 * "public class..." 声明末尾添加 "implements Map61B<K, V>"。
 * 必要时，你可以也应该使用其他类型参数。
 */
public interface Map61B<K, V> extends Iterable<K> {

    /** Associates the specified value with the specified key in this map.
     *  If the map already contains the specified key, replaces the key's mapping
     *  with the value specified. */
    /** 中文：将指定值与指定键关联。如果键已存在，用新值替换原映射。 */
    void put(K key, V value);

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. */
    /** 中文：返回指定键映射的值；如果不存在该键，返回 null。 */
    V get(K key);

    /** Returns whether this map contains a mapping for the specified key. */
    /** 中文：返回映射中是否包含指定键。 */
    boolean containsKey(K key);

    /** Returns the number of key-value mappings in this map. */
    /** 中文：返回键值映射的数量。 */
    int size();

    /** Removes every mapping from this map. */
    /** 中文：删除此映射中的所有键值对。 */
    void clear();

    /** Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    /** 中文：返回包含所有键的 Set 视图。本实验不要求实现；
     * 如果不实现，请抛出 UnsupportedOperationException。 */
    Set<K> keySet();

    /** Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    /** 中文：如果指定键存在，删除并返回其映射值；否则返回 null。
     * 本实验不要求实现；如果不实现，请抛出 UnsupportedOperationException。 */
    V remove(K key);
}
