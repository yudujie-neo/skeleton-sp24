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

/** Tests the iterator, equals, and toString methods of LinkedListDeque61B. */
public class LinkedListDeque61BTest {

    @Test
    @DisplayName("iterator visits linked-list items from front to back / 迭代器按队首到队尾遍历链表")
    void iteratorTest() {
        Deque61B<Integer> deque = new LinkedListDeque61B<>();
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
        Deque61B<Integer> deque = new LinkedListDeque61B<>();
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
    @DisplayName("equals compares linked-list deques by contents / equals 按内容比较链表双端队列")
    void equalsTest() {
        Deque61B<String> linked = new LinkedListDeque61B<>();
        Deque61B<String> sameContents = new LinkedListDeque61B<>();
        Deque61B<String> sameContentsDifferentImplementation = new ArrayDeque61B<>();
        Deque61B<String> differentOrder = new LinkedListDeque61B<>();
        Deque61B<String> differentSize = new LinkedListDeque61B<>();

        for (String item : List.of("front", "middle", "back")) {
            linked.addLast(item);
            sameContents.addLast(item);
            sameContentsDifferentImplementation.addLast(item);
        }
        differentOrder.addLast("back");
        differentOrder.addLast("middle");
        differentOrder.addLast("front");
        differentSize.addLast("front");
        differentSize.addLast("middle");

        assertThat(linked).isEqualTo(linked);
        assertThat(linked).isEqualTo(sameContents);
        assertThat(linked).isEqualTo(sameContentsDifferentImplementation);
        assertThat(linked).isNotEqualTo(differentOrder);
        assertThat(linked).isNotEqualTo(differentSize);
        assertThat(linked).isNotEqualTo(null);
        assertThat(linked).isNotEqualTo(List.of("front", "middle", "back"));
    }

    @Test
    @DisplayName("toString uses list notation for linked-list items / toString 使用列表格式显示链表元素")
    void toStringTest() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        assertThat(deque.toString()).isEqualTo("[]");

        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");

        assertThat(deque.toString()).isEqualTo("[front, middle, back]");
    }
}
