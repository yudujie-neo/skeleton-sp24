/**
 * Discussion 06, Regular Question 1 — Solution.
 *
 * Official solution (English)
 * 1. You should use a set because we only want to keep track of unique users
 * (i.e. if a user logs on twice, they shouldn't show up in our data structure
 * twice). Additionally, our task doesn't seem to require that the structure is
 * ordered.
 *
 * 2. You should use a map. Maps naturally let you pair a key and value, and here
 * we could have the file name be the key, and the blob be the value.
 *
 * 3. We should use a Stack. When papers are added to a pile, the top of the pile
 * is the last paper added. Since we want to grade the top of the pile first, it
 * makes sense for us to use a last-in-first-out (LIFO) approach in which we
 * continually pop papers off the top of our Stack as we grade them.
 *
 * 4. We should use a Queue. We can push clients to the front of the Queue as they
 * arrive, and pop them off the Queue as we service them.
 *
 * 5. We should use a List because a List is an ordered collection of items.
 * Additionally, we need to allow for duplicate items because we have multiple
 * copies of some books.
 *
 * 中文解释
 * 1 Set：保证用户唯一；2 Map：表达文件名到 Blob 的映射；3 Stack：后进先出；
 * 4 Queue：按到达顺序先进先出；5 List：保持顺序且允许重复副本。
 *
 * Supplemental analysis / 补充分析（非官方答案）
 * 第 4 项官方文字写“push clients to the front”并同时从同一端 pop；标准 FIFO
 * 队列应在一端入队、另一端出队。这里保留官方原文，不静默修正。
 */
public class Dis06RegularQ1ADTMatchmakingSolution {
    // Conceptual solution; no executable implementation is required.
}
