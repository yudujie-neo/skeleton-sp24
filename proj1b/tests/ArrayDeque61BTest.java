import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

    // 中文：此结构测试要求 ArrayDeque61B 除底层数组、基本类型字段和合成字段外，
    // 不能包含其他字段。
    @Test
    @DisplayName("ArrayDeque61B only has an array and primitives / 仅含数组和基本类型字段")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                .filter(f -> !(f.getType().isPrimitive()
                        || f.getType().equals(Object[].class)
                        || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not array or primitives / 发现非数组或基本类型字段")
                .that(badFields).isEmpty();
    }

    @Test
    @DisplayName("A new deque is empty / 新队列为空")
    void newDequeIsEmptyTest() {
        Deque61B<String> deque = new ArrayDeque61B<>();

        assertThat(deque.isEmpty()).isTrue();
        assertThat(deque.size()).isEqualTo(0);
        assertThat(deque.toList()).isEmpty();
        assertThat(deque.removeFirst()).isNull();
        assertThat(deque.removeLast()).isNull();
    }

    @Test
    @DisplayName("addFirst and addLast preserve order / 首尾添加保持顺序")
    void addFirstAndAddLastTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        deque.addLast(0);       // [0]
        deque.addLast(1);       // [0, 1]
        deque.addFirst(-1);     // [-1, 0, 1]
        deque.addLast(2);       // [-1, 0, 1, 2]
        deque.addFirst(-2);     // [-2, -1, 0, 1, 2]

        assertThat(deque.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
        assertThat(deque.size()).isEqualTo(5);
        assertThat(deque.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Adding many items resizes the deque / 大量添加时正确扩容")
    void addManyItemsResizeTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        for (int i = 0; i < 100; i += 1) {
            deque.addLast(i);
        }

        assertThat(deque.size()).isEqualTo(100);
        for (int i = 0; i < 100; i += 1) {
            assertWithMessage("Wrong item at index %s / 索引 %s 处元素错误", i, i)
                    .that(deque.get(i)).isEqualTo(i);
        }
    }

    @Test
    @DisplayName("Adding many items at the front resizes the deque / 队首大量添加时正确扩容")
    void addManyItemsAtFrontResizeTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();

        for (int i = 0; i < 100; i += 1) {
            deque.addFirst(i);
        }

        assertThat(deque.size()).isEqualTo(100);
        for (int i = 0; i < 100; i += 1) {
            assertWithMessage("Wrong item at index %s / 索引 %s 处元素错误", i, i)
                    .that(deque.get(i)).isEqualTo(99 - i);
        }
    }

    @Test
    @DisplayName("removeFirst and removeLast return correct items / 首尾删除返回正确元素")
    void removeFirstAndRemoveLastTest() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("middle");
        deque.addFirst("front");
        deque.addLast("back");

        assertThat(deque.removeFirst()).isEqualTo("front");
        assertThat(deque.removeLast()).isEqualTo("back");
        assertThat(deque.toList()).containsExactly("middle").inOrder();
        assertThat(deque.size()).isEqualTo(1);

        assertThat(deque.removeLast()).isEqualTo("middle");
        assertThat(deque.removeFirst()).isNull();
        assertThat(deque.removeLast()).isNull();
        assertThat(deque.isEmpty()).isTrue();
        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Items can be added after removing to empty / 清空后仍可继续添加")
    void addAfterRemoveToEmptyTest() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("old");
        assertThat(deque.removeFirst()).isEqualTo("old");

        deque.addFirst("front");
        deque.addLast("back");

        assertThat(deque.toList()).containsExactly("front", "back").inOrder();
        assertThat(deque.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Circular indices wrap around correctly / 循环索引正确回绕")
    void wrapAroundTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i < 20; i += 1) {
            deque.addLast(i);
            assertThat(deque.removeFirst()).isEqualTo(i);
        }

        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);

        assertThat(deque.toList()).containsExactly(1, 2, 3).inOrder();
    }

    @Test
    @DisplayName("get handles valid and invalid indices / get 处理有效和越界索引")
    void getTest() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("first");
        deque.addLast("second");
        deque.addLast("third");

        assertThat(deque.get(0)).isEqualTo("first");
        assertThat(deque.get(1)).isEqualTo("second");
        assertThat(deque.get(2)).isEqualTo("third");
        assertThat(deque.get(-1)).isNull();
        assertThat(deque.get(3)).isNull();
        assertThat(deque.get(100)).isNull();
        assertThat(deque.toList()).containsExactly("first", "second", "third").inOrder();
    }

    @Test
    @DisplayName("toList returns an independent copy / toList 返回独立副本")
    void toListReturnsIndependentCopyTest() {
        Deque61B<String> deque = new ArrayDeque61B<>();
        deque.addLast("first");
        deque.addLast("second");

        List<String> copy = deque.toList();
        copy.clear();

        assertThat(deque.toList()).containsExactly("first", "second").inOrder();
        assertThat(deque.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Removing many items preserves remaining order / 大量删除后保持剩余顺序")
    void removeManyItemsResizeTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i < 64; i += 1) {
            deque.addLast(i);
        }
        for (int i = 0; i < 60; i += 1) {
            assertThat(deque.removeFirst()).isEqualTo(i);
        }

        assertThat(deque.toList()).containsExactly(60, 61, 62, 63).inOrder();
        assertThat(deque.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("Removing many items from the back preserves order / 队尾大量删除后保持顺序")
    void removeManyItemsFromBackResizeTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        for (int i = 0; i < 64; i += 1) {
            deque.addLast(i);
        }
        for (int i = 63; i >= 4; i -= 1) {
            assertThat(deque.removeLast()).isEqualTo(i);
        }

        assertThat(deque.toList()).containsExactly(0, 1, 2, 3).inOrder();
        assertThat(deque.size()).isEqualTo(4);
    }
}
