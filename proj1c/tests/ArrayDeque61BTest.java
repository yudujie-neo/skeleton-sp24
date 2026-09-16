import deque.ArrayDeque61B;
import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/** Tests the iterator, equals, and toString methods of ArrayDeque61B. */
public class ArrayDeque61BTest {

    @Test
    @DisplayName("iterator visits array-deque items from front to back / 迭代器按队首到队尾遍历数组双端队列")
    void iteratorTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(0);
        deque.addLast(1);
        deque.addFirst(-1);
        deque.addLast(2);

        List<Integer> iteratedItems = new ArrayList<>();
        for (int item : deque) {
            iteratedItems.add(item);
        }

        assertThat(iteratedItems).containsExactly(-1, 0, 1, 2).inOrder();
    }

    @Test
    @DisplayName("iterator rejects next after exhaustion / 迭代结束后 next 抛出异常")
    void iteratorExhaustionTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        Iterator<Integer> emptyIterator = deque.iterator();
        assertThat(emptyIterator.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, emptyIterator::next);

        deque.addLast(61);
        Iterator<Integer> iterator = deque.iterator();
        assertThat(iterator.next()).isEqualTo(61);
        assertThat(iterator.hasNext()).isFalse();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    @DisplayName("equals compares array deques by contents / equals 按内容比较数组双端队列")
    void equalsTest() {
        Deque61B<String> array = new ArrayDeque61B<>();
        Deque61B<String> sameContents = new ArrayDeque61B<>();
        Deque61B<String> sameContentsDifferentImplementation = new LinkedListDeque61B<>();
        Deque61B<String> differentOrder = new ArrayDeque61B<>();
        Deque61B<String> differentSize = new ArrayDeque61B<>();

        for (String item : List.of("front", "middle", "back")) {
            array.addLast(item);
            sameContents.addLast(item);
            sameContentsDifferentImplementation.addLast(item);
        }
        differentOrder.addLast("back");
        differentOrder.addLast("middle");
        differentOrder.addLast("front");
        differentSize.addLast("front");
        differentSize.addLast("middle");

        assertThat(array).isEqualTo(array);
        assertThat(array).isEqualTo(sameContents);
        assertThat(array).isEqualTo(sameContentsDifferentImplementation);
        assertThat(array).isNotEqualTo(differentOrder);
        assertThat(array).isNotEqualTo(differentSize);
        assertThat(array).isNotEqualTo(null);
        assertThat(array).isNotEqualTo(List.of("front", "middle", "back"));
    }

    @Test
    @DisplayName("toString uses list notation for array-deque items / toString 使用列表格式显示数组元素")
    void toStringTest() {
        Deque61B<Integer> deque = new ArrayDeque61B<>();
        assertThat(deque.toString()).isEqualTo("[]");

        deque.addLast(10);
        deque.addLast(20);
        deque.addFirst(0);

        assertThat(deque.toString()).isEqualTo("[0, 10, 20]");
    }
}
