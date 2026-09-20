package questions;

import java.util.ArrayList;
import java.util.List;

/**
 * CS 61B Spring 2024 - Discussion 07, Regular Question 3.
 * A Side of Hash Browns / 一份薯饼配菜
 */
public class Dis07RegularQ3HashBrowns {

    /*
     * English - Official question
     * We want to map food items to their yumminess. We want to be able to find
     * this information in constant time, so we've decided to use Java's
     * built-in HashMap class! Here, the key is an String representing the food
     * item and the value is an int yumminess rating.
     *
     * For simplicity, let's say that here a String's hashcode is the first
     * letter's position in the alphabet (A = 0, B = 1... Z = 25). For example,
     * the String "Hashbrowns" starts with "H", and "H" is 7th letter in the
     * alphabet (0 indexed), so the hashCode would be 7. Note that in reality,
     * a String has a much more complicated hashCode implementation.
     *
     * Our HashMap will compute the index as the key's hashcode value modulo
     * the number of buckets in our HashMap. Assume the initial size is 4
     * buckets, and we double the size of our HashMap as soon as the load factor
     * reaches 3/4. If we try to put in a duplicate key, simply replace the
     * value associated with that key with the new value.
     *
     * 中文翻译
     * 把食物字符串映射到 int 美味度。简化哈希值为首字母在字母表中的
     * 0 起始位置；桶索引为哈希值对桶数取模。初始 4 桶，负载因子一达到
     * 3/4 就将桶数翻倍；重复键只更新值。
     *
     * 3a - Draw what the HashMap would look like after the operations in
     * runOfficialOperations().
     * 3a - 执行 runOfficialOperations() 中的操作后，画出 HashMap。
     * My answer / 我的答案：TODO（最终图和桶内容）
     *
     * 3b - Do you see a potential problem here with the behavior of our
     * HashMap? How could we solve this?
     * 3b - 这个 HashMap 的行为有什么潜在问题？应如何解决？
     * My answer / 我的答案：TODO
     */

    /*
     * Official given code / 官方给定代码
     *
     * HashMap<String, Integer> hm = new HashMap<>();
     * hm.put("Hashbrowns", 7);
     * hm.put("Dim sum", 10);
     * hm.put("Escargot", 5);
     * hm.put("Brown bananas", 1);
     * hm.put("Burritos", 2);
     * hm.put("Buffalo wings", 8);
     * hm.put("Banh mi", 9);
     * hm.put("Burritos", 10);
     */

    /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
    public static final class FirstLetterHashMap {
        private static final double MAX_LOAD_FACTOR = 0.75;
        private List<List<Entry>> buckets = newBuckets(4);
        private int size;

        public void put(String key, int value) {
            int index = bucketIndex(key, buckets.size());
            for (Entry entry : buckets.get(index)) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return;
                }
            }
            buckets.get(index).add(new Entry(key, value));
            size += 1;
            if ((double) size / buckets.size() >= MAX_LOAD_FACTOR) {
                resize(buckets.size() * 2);
            }
        }

        public int bucketCount() {
            return buckets.size();
        }

        public int size() {
            return size;
        }

        private void resize(int capacity) {
            List<List<Entry>> oldBuckets = buckets;
            buckets = newBuckets(capacity);
            for (List<Entry> bucket : oldBuckets) {
                for (Entry entry : bucket) {
                    buckets.get(bucketIndex(entry.key, capacity)).add(entry);
                }
            }
        }

        private static int bucketIndex(String key, int capacity) {
            if (key == null || key.isEmpty()) {
                throw new IllegalArgumentException("key must have a first letter");
            }
            int hashCode = Character.toUpperCase(key.charAt(0)) - 'A';
            return Math.floorMod(hashCode, capacity);
        }

        private static List<List<Entry>> newBuckets(int capacity) {
            List<List<Entry>> result = new ArrayList<>(capacity);
            for (int i = 0; i < capacity; i += 1) {
                result.add(new ArrayList<>());
            }
            return result;
        }

        private static final class Entry {
            private final String key;
            private int value;

            private Entry(String key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    /**
     * Compilation scaffold / 编译脚手架（非官方题目内容）。
     * Runs the official put sequence against the question's simplified map;
     * the changed local type and returned map are scaffold only.
     */
    public static FirstLetterHashMap runOfficialOperations() {
        FirstLetterHashMap hm = new FirstLetterHashMap();
        hm.put("Hashbrowns", 7);
        hm.put("Dim sum", 10);
        hm.put("Escargot", 5);
        hm.put("Brown bananas", 1);
        hm.put("Burritos", 2);
        hm.put("Buffalo wings", 8);
        hm.put("Banh mi", 9);
        hm.put("Burritos", 10);
        return hm;
    }

    private Dis07RegularQ3HashBrowns() {
    }
}
