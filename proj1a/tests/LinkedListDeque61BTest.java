import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests.
 * 中文：执行一些基本的链表测试。 */
public class LinkedListDeque61BTest {

    // @Test
    // /** In this test, we have three different assert statements that verify that addFirst works correctly.
    //  * 中文：此测试使用三个不同的断言验证 addFirst 是否正确工作。 */
    // public void addFirstTestBasic() {
    //     Deque61B<String> lld1 = new LinkedListDeque61B<>();

    //     lld1.addFirst("back"); // after this call we expect: ["back"] / 中文：调用后期望为 ["back"]
    //     assertThat(lld1.toList()).containsExactly("back").inOrder();

    //     lld1.addFirst("middle"); // after this call we expect: ["middle", "back"] / 中文：调用后期望如右
    //     assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

    //     lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"] / 中文：调用后期望如右
    //     assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

    //     /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
    //        to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
    //        but not ["front", "middle", "back"].
    //        中文：前两个断言其实并非必需。很难想象一种错误会让较短的两个结果失败，
    //        却让最终的完整结果通过。
    //      */
    // }

    // @Test
    // /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
    //  *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    // /** 中文：此测试只使用一个 assertThat；它与 addFirstTestBasic 同样有效，
    //  * 因此没有必要机械地添加多余断言。 */
    // public void addLastTestBasic() {
    //     Deque61B<String> lld1 = new LinkedListDeque61B<>();

    //     lld1.addLast("front"); // after this call we expect: ["front"]
    //     lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
    //     lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
    //     assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    // }

    // @Test
    // /** This test performs interspersed addFirst and addLast calls.
    //  * 中文：此测试交替调用 addFirst 和 addLast。 */
    // public void addFirstAndAddLastTest() {
    //     Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

    //     /* I've decided to add in comments the state after each call for the convenience of the
    //        person reading this test. Some programmers might consider this excessively verbose. */
    //     /* 中文：为方便阅读，在每次调用后标出了队列状态；有些程序员可能认为这过于详细。 */
    //     lld1.addLast(0);   // [0]
    //     lld1.addLast(1);   // [0, 1]
    //     lld1.addFirst(-1); // [-1, 0, 1]
    //     lld1.addLast(2);   // [-1, 0, 1, 2]
    //     lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

    //     assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    // }

    // Below, you'll write your own tests for LinkedListDeque61B.
    // 中文：请在下方为 LinkedListDeque61B 编写自己的测试。
}
