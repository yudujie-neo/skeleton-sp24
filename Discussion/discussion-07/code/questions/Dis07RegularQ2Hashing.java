package questions;

import java.util.Date;

/**
 * CS 61B Spring 2024 - Discussion 07, Regular Question 2.
 * Hashing / 哈希
 */
public class Dis07RegularQ2Hashing {

    /*
     * 2a - English - Official question
     * Here are five potential implementations of the Integer class's
     * hashCode() method. Categorize each as (1) invalid, (2) valid but not
     * good, and (3) valid and good. If it is invalid, explain why. If it is
     * valid but not good, point out a flaw or disadvantage. For the 2nd
     * implementation, note that intValue() will return that Integer's number
     * value as an int, and assume that Integer's equals method checks for
     * equality of the compared Integers' intValues.
     *
     * 2a - 中文翻译
     * 将下面五种 Integer.hashCode() 候选实现分类为：(1) 无效，(2) 有效但
     * 不好，或 (3) 有效且良好。无效时解释原因；有效但不好时指出缺陷。
     * 第二种实现中 intValue() 返回整数值，并假设 equals 比较 intValue。
     *
     * My answer / 我的答案
     * 1. ConstantHash：TODO
     * 2. SquareHash：TODO
     * 3. IdentityHash：TODO
     * 4. TimeHash：TODO
     * 5. PlusThreeHash：TODO
     */

    /** Compilation scaffold / 编译脚手架（非官方题目内容）。 */
    abstract static class IntegerLike {
        final int value;

        IntegerLike(int value) {
            this.value = value;
        }

        int intValue() {
            return value;
        }

        @Override
        public boolean equals(Object other) {
            // Scaffold isolation: each nested class represents one alternative
            // Integer implementation, so alternatives are never compared as peers.
            return other != null
                    && getClass() == other.getClass()
                    && intValue() == ((IntegerLike) other).intValue();
        }
    }

    static final class ConstantHash extends IntegerLike {
        ConstantHash(int value) { super(value); }
        @Override public int hashCode() { return -1; }
    }

    static final class SquareHash extends IntegerLike {
        SquareHash(int value) { super(value); }
        @Override public int hashCode() { return intValue() * intValue(); }
    }

    static final class IdentityHash extends IntegerLike {
        IdentityHash(int value) { super(value); }
        @Override public int hashCode() {
            return super.hashCode(); // Object's hashCode() is based on memory location
        }
    }

    static final class TimeHash extends IntegerLike {
        TimeHash(int value) { super(value); }
        @Override public int hashCode() {
            return (int) (new Date()).getTime(); // returns the current time as an int
        }
    }

    static final class PlusThreeHash extends IntegerLike {
        PlusThreeHash(int value) { super(value); }
        @Override public int hashCode() { return intValue() + 3; }
    }

    /*
     * 2b - English - Official question
     * For each of the following questions, answer Always, Sometimes, or Never.
     *
     * 1. If you were able to modify a key that has been inserted into a
     * HashMap would you be able to retrieve that entry again later? For
     * example, let us suppose you were mapping ID numbers to student names,
     * and you did a put(303, "Elisa") operation. Now, let us suppose we
     * somehow went to that item in our HashMap and manually changed the key
     * to be 304. If we later do get(304), will we be able to find and return
     * "Elisa"? Explain.
     *
     * 2. When you modify a value that has been inserted into a HashMap will
     * you be able to retrieve that entry again? For example, in the above
     * scenario, suppose we first inserted put(303, "Elisa") and then changed
     * that item's value from "Elisa" to "William". If we later do get(303),
     * will we be able to find and return "William"? Explain.
     *
     * 2b - 中文翻译
     * 对每种情况回答 Always、Sometimes 或 Never。情形 1 修改已插入条目的
     * 键 303 为 304，再执行 get(304)；情形 2 保持键 303 不变，只把值从
     * "Elisa" 改为 "William"，再执行 get(303)。分别说明能否取回。
     *
     * My answer / 我的答案
     * 1. Mutable key / 可变键：TODO；Reason / 原因：TODO
     * 2. Mutable value / 可变值：TODO；Reason / 原因：TODO
     */

    private Dis07RegularQ2Hashing() {
    }
}
