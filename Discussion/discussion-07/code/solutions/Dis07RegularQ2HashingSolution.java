package solutions;

/** Official solutions and supplemental Chinese analysis for Discussion 07 Regular Q2. */
public class Dis07RegularQ2HashingSolution {

    public enum Classification {
        INVALID,
        VALID_BUT_NOT_GOOD,
        VALID_AND_GOOD
    }

    /*
     * 2a - Official solution / 官方答案
     *
     * 1. return -1;
     * Valid but not good. As required, this hash function returns the same
     * hashCode for Integers that are equals() to each other. However, this is
     * a terrible hash function because collisions are extremely frequent
     * (collisions occur 100% of the time).
     *
     * 2. return intValue() * intValue();
     * Valid but not good. Similar to (a), this hash function returns the same
     * hashCode for Integers that are equal. However, integers that share the
     * same absolute values will collide (for example, x = 5 and x = -5 will
     * have the same hash code). A better hash function would be to just return
     * the intValue itself.
     *
     * 3. return super.hashCode();
     * Invalid. When we call super.hashCode() here, we will ulimately end up
     * returning Object.hashCode(). This hash function returns some number
     * corresponding to the Integer object's location in memory.
     *
     * However, note what happens below:
     * Integer x = new Integer(5);
     * Integer y = new Integer(5);
     * Here, x and y are both instantiated as separate objects, meaning they'll
     * be at two distinct memory addresses! Then x and y would receive different
     * hashcodes according to our current hashCode() implementation. However,
     * this is not desired/valid behavior, because x and y are equal according
     * to the equals() method, so their hashcodes should be the same.
     *
     * 4. return (int) (new Date()).getTime();
     * Invalid. This function shouldn't ever have collisions (except for
     * possible collisions as a result of effectively truncating the long to
     * an int), but it is not consistent: calling hashCode() on the same object
     * twice will result in two different ints.
     *
     * 5. return intValue() + 3;
     * Valid and good. This function will return the same hashCode for Integers
     * that are equals, and it is consistent. It is also a good hash function;
     * there will actually be no collisions here and generally distributes
     * elements evenly, as there is no overlap in intValue for two distinct
     * integers (and by proxy, no overlap in intValue() + 3).
     *
     * Supplemental analysis / 补充分析（非官方答案）：同一毫秒内的时间值也
     * 可能碰撞；long 截断到 int 还会周期性重复。根本缺陷仍是非确定性。
     */
    public static final Classification[] CLASSIFICATIONS = {
            Classification.VALID_BUT_NOT_GOOD,
            Classification.VALID_BUT_NOT_GOOD,
            Classification.INVALID,
            Classification.INVALID,
            Classification.VALID_AND_GOOD
    };

    /*
     * 2b.1 - Official solution / 官方答案
     * Sometimes. If the hashCode for the key happens to change as a result of
     * the modification, then we won't be able to retrieve the entry in our
     * hashtable (unless we were to recompute which bucket the new key would
     * belong to). Changing the key can potentially (and likely) change which
     * bucket a key would now be indexed to.
     *
     * In our example, let us suppose the hashCode for a key was just it's
     * integer value, and the bucket was calculated as that number modulo the
     * number of buckets. Let's say we had 10 buckets. In this scenario, 303
     * would be mapped to bucket 3, and 304 would be mapped to bucket 4. So when
     * we put(303, "Elisa"), this item goes to bucket 3. Then we change the key
     * to be 304, but don't change anything else, so this item remains in bucket
     * 3. Now, let us suppose we later do get(304). We will compute which bucket
     * the key 304 would correspond to, which would be bucket 4. We look in
     * bucket 4 and don't see the time there, so we cannot successfully return
     * the item.
     *
     * 2b.2 - Official solution / 官方答案
     * Always. The bucket index for an entry in a HashMap is decided by the key,
     * not the value. Mutating the value does not affect the lookup procedure.
     *
     * In our example this would work, because when we do get(303) we will still
     * go to the correct bucket.
     */
    public static final String MUTABLE_KEY_ANSWER = "Sometimes";
    public static final String MUTABLE_VALUE_ANSWER = "Always";

    /** Smoke test / 冒烟测试（非官方答案）。 */
    public static void main(String[] args) {
        if (CLASSIFICATIONS.length != 5
                || CLASSIFICATIONS[4] != Classification.VALID_AND_GOOD) {
            throw new AssertionError("Unexpected hashCode classifications");
        }
        if (!MUTABLE_KEY_ANSWER.equals("Sometimes")
                || !MUTABLE_VALUE_ANSWER.equals("Always")) {
            throw new AssertionError("Unexpected Always/Sometimes/Never answer");
        }
    }
}
