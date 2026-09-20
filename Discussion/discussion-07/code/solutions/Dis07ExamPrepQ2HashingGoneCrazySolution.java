package solutions;

import java.util.ArrayList;
import java.util.List;

/** Official solution and deterministic bucket verification for Discussion 07 Exam Prep Q2. */
public class Dis07ExamPrepQ2HashingGoneCrazySolution {

    /*
     * Official solution / 官方答案
     * Line 4: jasmine has semester value 10. 10 % 4 = 2, so jasmine is placed
     * in bucket 2 with value 1.
     * 0: [], 1: [], 2: [(jasmine, 1)], 3: []
     *
     * Line 5: noah is placed in bucket 0 with value 2.
     * 0: [(noah, 2)], 1: [], 2: [(jasmine, 1)], 3: []
     *
     * Line 7: Increasing the semester value of noah does not cause it to be
     * rehashed! (This is why modifying objects in a Hashmap is dangerous--it
     * can change the hashcode of your object and make it impossible to find
     * which bucket it belongs to).
     *
     * Line 8: noah now has semester 4, so bucket 2 also has a node pointing to
     * noah, with value 3. (Note that the two noahs refer to the same object).
     * 0: [(noah, 2)], 1: [], 2: [(jasmine, 1), (noah, 3)], 3: []
     *
     * Line 11, 12: noah with semester 22 hashes to bucket 2. However, since we
     * have changed jasmine's name to be "Nasmine", noah.equals(jasmine)
     * returns true. Since we are hashing a key that is already present in the
     * dictionary according to .equals, we replace jasmine's old value with the
     * new value, 4.
     * 0: [(noah, 2)], 1: [], 2: [(jasmine, 4), (noah, 3)], 3: []
     *
     * Line 13, 14: jasmine with semester 12 hashes to bucket 0. However, since
     * we have changed jasmine's name to be "Nasmine", jasmine.equals(noah)
     * returns true. Since we are hashing a key that is already present in the
     * dictionary according to .equals, we replace noah's old value with the
     * new value, 5.
     * 0: [(noah, 5)], 1: [], 2: [(jasmine, 4), (noah, 3)], 3: []
     *
     * Line 16, 17, 18: cheeseGuy hashes to bucket 0. cheeseGuy.equals(noah)
     * returns false, so we add a new node after noah with value 6.
     * 0: [(noah, 5), (cheeseGuy, 6)], 1: [],
     * 2: [(jasmine, 4), (noah, 3)], 3: []
     *
     * Supplemental analysis / 补充分析（非官方答案）：官方 Line 8 写
     * "noah now has semester 4"，但代码实际得到 22；22 % 4 仍为 2，故
     * 官方后续桶结构不受该笔误影响。官方最终图见
     * ../../assets/exam-q2-final-map.png。
     */

    private static final class TA {
        int semester;
        String name;

        TA(String name, int semester) {
            this.name = name;
            this.semester = semester;
        }

        @Override
        public boolean equals(Object o) {
            TA other = (TA) o;
            return other.name.charAt(0) == name.charAt(0);
        }

        @Override
        public int hashCode() {
            return semester;
        }
    }

    /** Executable verification scaffold / 可执行验证脚手架（非官方答案）。 */
    private static final class ECHashMap<K, V> {
        final List<List<Entry<K, V>>> buckets = new ArrayList<>();

        ECHashMap() {
            for (int i = 0; i < 4; i += 1) {
                buckets.add(new ArrayList<>());
            }
        }

        void put(K key, V value) {
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

    /** Smoke test / 冒烟测试（非官方答案）。 */
    public static void main(String[] args) {
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

        if (noah.semester != 22 || jasmine.semester != 12) {
            throw new AssertionError("Unexpected mutated key state");
        }
        List<ECHashMap.Entry<TA, Integer>> bucket0 = map.buckets.get(0);
        List<ECHashMap.Entry<TA, Integer>> bucket2 = map.buckets.get(2);
        if (bucket0.size() != 2 || bucket0.get(0).key != noah || bucket0.get(0).value != 5
                || bucket0.get(1).key != cheeseguy || bucket0.get(1).value != 6) {
            throw new AssertionError("Bucket 0 mismatch");
        }
        if (bucket2.size() != 2 || bucket2.get(0).key != jasmine || bucket2.get(0).value != 4
                || bucket2.get(1).key != noah || bucket2.get(1).value != 3) {
            throw new AssertionError("Bucket 2 mismatch");
        }
    }
}
