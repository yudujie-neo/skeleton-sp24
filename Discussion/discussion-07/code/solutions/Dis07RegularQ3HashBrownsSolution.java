package solutions;

import java.util.ArrayList;
import java.util.List;

/** Official solutions and executable verification for Discussion 07 Regular Q3. */
public class Dis07RegularQ3HashBrownsSolution {

    /*
     * 3a - Official solution / 官方答案
     * Note that we resize from 4 to 8 when adding escargot (because we have 3
     * items and 4 buckets) and then from 8 to 16 when adding buffalo wings
     * (because we have 6 items and 8 buckets).
     *
     * The official final drawing remains in
     * ../../assets/regular-q3a-hashmap.png.
     *
     * Supplemental analysis / 补充分析（非官方答案）：官方图把部分键写成
     * "Brown Banana"、"Burrito"、"Buffalo Wings"、"Banh Mi" 和
     * "Hash browns"，与题目代码的大小写、单复数或空格不完全一致。
     * 下方可执行结果使用题目代码中的精确字符串。
     */

    /*
     * 3b - Official solution / 官方答案
     * Here, adding a bunch of food items that start with the letter "B" result
     * in one bucket with a lot of items. No matter how many times we resize,
     * our current hashCode will result in this problem! Imagine if we added in
     * 100 more items that started with the letter b. Though we would resize and
     * keep our load factor low, it wouldn't change the fact that our operations
     * will now be slow (hint: linear time) because now we essentially have to
     * iterate over a linked list with pretty much all the items in the hashMap
     * to do things like get("Burrito"), for example.
     *
     * A solution to this would be to have a better hashCode implementation. A
     * better implementation would distribute the Strings more randomly and
     * evenly. While knowing how to write such a hashCode is difficult and out
     * of scope for this class, you can look at the real hashCode implementation
     * for Java Strings if you're curious!
     */

    /** Executable verification scaffold / 可执行验证脚手架（非官方答案）。 */
    private static final class FirstLetterHashMap {
        private List<List<Entry>> buckets = newBuckets(4);
        private int size;

        void put(String key, int value) {
            int index = bucketIndex(key, buckets.size());
            for (Entry entry : buckets.get(index)) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return;
                }
            }
            buckets.get(index).add(new Entry(key, value));
            size += 1;
            if ((double) size / buckets.size() >= 0.75) {
                resize(buckets.size() * 2);
            }
        }

        List<String> bucketEntries(int index) {
            List<String> result = new ArrayList<>();
            for (Entry entry : buckets.get(index)) {
                result.add(entry.key + "=" + entry.value);
            }
            return result;
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
            return Math.floorMod(Character.toUpperCase(key.charAt(0)) - 'A', capacity);
        }

        private static List<List<Entry>> newBuckets(int capacity) {
            List<List<Entry>> result = new ArrayList<>(capacity);
            for (int i = 0; i < capacity; i += 1) {
                result.add(new ArrayList<>());
            }
            return result;
        }

        private static final class Entry {
            final String key;
            int value;

            Entry(String key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    /** Smoke test / 冒烟测试（非官方答案）。 */
    public static void main(String[] args) {
        FirstLetterHashMap hm = new FirstLetterHashMap();
        hm.put("Hashbrowns", 7);
        hm.put("Dim sum", 10);
        hm.put("Escargot", 5);
        hm.put("Brown bananas", 1);
        hm.put("Burritos", 2);
        hm.put("Buffalo wings", 8);
        hm.put("Banh mi", 9);
        hm.put("Burritos", 10);

        if (hm.buckets.size() != 16 || hm.size != 7) {
            throw new AssertionError("Expected 16 buckets and 7 distinct keys");
        }
        List<String> expectedBucketOne = List.of(
                "Brown bananas=1", "Burritos=10", "Buffalo wings=8", "Banh mi=9");
        if (!hm.bucketEntries(1).equals(expectedBucketOne)
                || !hm.bucketEntries(3).equals(List.of("Dim sum=10"))
                || !hm.bucketEntries(4).equals(List.of("Escargot=5"))
                || !hm.bucketEntries(7).equals(List.of("Hashbrowns=7"))) {
            throw new AssertionError("Final buckets do not match the worksheet result");
        }
    }
}
