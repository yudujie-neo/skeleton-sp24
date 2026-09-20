package questions;

import java.util.ArrayList;
import java.util.List;

/**
 * CS 61B Spring 2024 - Discussion 07, Exam Prep Question 2.
 * Hashing Gone Crazy / 疯狂哈希
 */
public class Dis07ExamPrepQ2HashingGoneCrazy {

    /*
     * English - Official question
     * For this question, use the following TA class for reference.
     *
     * Assume that the hashCode of a TA object returns semester, and the equals
     * method returns true if and only if two TA objects have the same first
     * letter in their name.
     *
     * Assume that the ECHashMap is a HashMap implemented with external chaining
     * as depicted in lecture. The ECHashMap instance begins at size 4 and, for
     * simplicity, does not resize. Draw the contents of map after executing the
     * insertions below.
     *
     * 中文翻译
     * TA.hashCode 返回 semester；当且仅当两个 TA 名字首字母相同时 equals
     * 返回 true。ECHashMap 使用外部链式法，固定 4 个桶且不扩容。执行
     * runOfficialOperations() 后画出 map 的内容。
     *
     * My answer / 我的答案：TODO（逐行桶状态与最终图）
     */

    /** Official TA code, nested only to keep this top-level file self-contained. */
    public static class TA {
        int semester;
        String name;

        TA(String name, int semester) {
            this.name = name;
            this.semester = semester;
        }

        @Override
        public boolean equals(Object o) {
            TA other = (TA) o;
            return other.name.charAt(0) == this.name.charAt(0);
        }

        @Override
        public int hashCode() {
            return semester;
        }
    }

    /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
    public static final class ECHashMap<K, V> {
        private final List<List<Entry<K, V>>> buckets = new ArrayList<>();

        ECHashMap() {
            for (int i = 0; i < 4; i += 1) {
                buckets.add(new ArrayList<>());
            }
        }

        public void put(K key, V value) {
            List<Entry<K, V>> bucket = buckets.get(Math.floorMod(key.hashCode(), 4));
            for (Entry<K, V> entry : bucket) {
                if (key.equals(entry.key)) {
                    entry.value = value;
                    return;
                }
            }
            bucket.add(new Entry<>(key, value));
        }

        private static final class Entry<K, V> {
            final K key;
            V value;

            Entry(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
    }

    /**
     * Compilation scaffold / 编译脚手架（非官方题目内容）。
     * The body preserves the official operation sequence. The method wrapper
     * and returned map only make that sequence executable and inspectable.
     */
    public static ECHashMap<TA, Integer> runOfficialOperations() {
        ECHashMap<TA, Integer> map = new ECHashMap<>();
        TA jasmine = new TA("Jasmine the GOAT", 10);
        TA noah = new TA("Noah", 20);
        map.put(jasmine, 1);
        map.put(noah, 2);

        noah.semester += 2;
        map.put(noah, 3);

        jasmine.name = "Nasmine";
        map.put(noah, 4);

        jasmine.semester += 2;
        map.put(jasmine, 5);

        jasmine.name = "Jasmine";
        TA cheeseguy = new TA("Sam", 24);
        map.put(cheeseguy, 6);
        return map; // Compilation scaffold / 编译脚手架（非官方题目内容）
    }

    private Dis07ExamPrepQ2HashingGoneCrazy() {
    }
}
