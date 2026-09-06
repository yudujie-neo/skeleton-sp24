import java.util.List;

/**
 * Created by hug on 2/4/2017. Methods are provided in the suggested order
 * that they should be completed.
 * 中文：方法按照建议的实现顺序排列。
 */
public interface Deque61B<T> {

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     * 中文：把 {@code x} 添加到双端队列头部；假定 {@code x} 永远不为 null。
     *
     * @param x item to add 中文：要添加的元素
     */
    void addFirst(T x);

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     * 中文：把 {@code x} 添加到双端队列尾部；假定 {@code x} 永远不为 null。
     *
     * @param x item to add 中文：要添加的元素
     */
    void addLast(T x);

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     * 中文：返回该双端队列的 List 副本，不修改原队列。
     *
     * @return a new list copy of the deque. 中文：双端队列的新 List 副本。
     */
    List<T> toList();

    /**
     * Returns if the deque is empty. Does not alter the deque.
     * 中文：返回双端队列是否为空，不修改队列。
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     * 中文：没有元素时返回 {@code true}，否则返回 {@code false}。
     */
    boolean isEmpty();

    /**
     * Returns the size of the deque. Does not alter the deque.
     * 中文：返回双端队列的大小，不修改队列。
     *
     * @return the number of items in the deque. 中文：队列中的元素数量。
     */
    int size();

    /**
     * Remove and return the element at the front of the deque, if it exists.
     * 中文：如果队首元素存在，则删除并返回它。
     *
     * @return removed element, otherwise {@code null}. 中文：被删除的元素；不存在时返回 null。
     */
    T removeFirst();

    /**
     * Remove and return the element at the back of the deque, if it exists.
     * 中文：如果队尾元素存在，则删除并返回它。
     *
     * @return removed element, otherwise {@code null}. 中文：被删除的元素；不存在时返回 null。
     */
    T removeLast();

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     * 中文：Deque61B 通常不包含 get 方法，这里加入它是为了额外练习。使用迭代
     * 方式取得元素；索引越界时返回 null，并且不修改队列。
     *
     * @param index index to get 中文：要读取的索引
     * @return element at {@code index} in the deque 中文：队列中该索引处的元素
     */
    T get(int index);

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     * 中文：严格来说，此方法不应出现在接口里，但为了便于测试而保留。使用递归
     * 方式取得元素；索引越界时返回 null，并且不修改队列。
     *
     * @param index index to get 中文：要读取的索引
     * @return element at {@code index} in the deque 中文：队列中该索引处的元素
     */
    T getRecursive(int index);
}
