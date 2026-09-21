package solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Official solutions and deterministic checks for Discussion 10 Regular Q3. */
public class Dis10RegularQ3ClassEnrollmentSolution {
    public record Prerequisite(String prerequisite, String course) {
    }

    private static final List<Prerequisite> ORIGINAL = List.of(
            new Prerequisite("CS 61A", "CS 61B"),
            new Prerequisite("CS 61B", "CS 61C"),
            new Prerequisite("CS 61B", "CS 170"),
            new Prerequisite("CS 70", "CS 170"),
            new Prerequisite("CS 61C", "CS 161"),
            new Prerequisite("CS 70", "CS 161")
    );

    /*
     * 3a - Official solution / 官方答案
     * The official directed graph is preserved at
     * ../../assets/regular-q3a-prerequisite-graph.png.
     * Its edges are 61A->61B, 61B->61C, 61B->170, 70->170,
     * 61C->161, and 70->161.
     *
     * 中文解释：边从先修课指向后续课程；图为 DAG 时，其拓扑序就是合法
     * 修课顺序。
     */

    /*
     * 3b - Official solution / 官方答案
     * The new graph is preserved at
     * ../../assets/regular-q3b-cycle-graph.png.
     * There exists a cycle 161 -> 170 -> 61C -> 161, so a valid ordering does
     * not exist. The graph must be directed and acyclic for a topological sort
     * to work.
     *
     * 中文解释：环中每门课都要求另一门先完成，因此无法形成合法线性序。
     */

    /*
     * 3c - Official solution / 官方答案
     * With topological sorting, if an edge from vertex u to vertex v exists in
     * the graph, then u must come before v in the sorted order. Every edge in
     * our graph represents a prerequisite where class u must be taken before
     * v. So, our topologically sorted order will ensure that we meet all
     * prerequisites!
     *
     * To topological sort on a graph, perform DFS from each vertex with
     * indegree 0 (no incoming edges), but don't clear the node's we've marked
     * between each new DFS traversal. Afterwards, we reverse the postorder to
     * get our topologically sorted order.
     *
     * In our graph, there are two vertices with indegree 0: CS 61A and CS 70.
     * If we DFS from CS 61A then CS 70, we get a postorder of:
     * [CS 161, CS 61C, CS 170, CS 61B, CS 61A, CS 70], whose reverse is
     * a valid ordering of:
     * [CS 70, CS 61A, CS 61B, CS 170, CS 61C, CS 161].
     *
     * If we DFS from CS 70 then CS 61A, we get a postorder of:
     * [CS 161, CS 170, CS 70, CS 61C, CS 61B, CS 61A], whose reverse is
     * a different but still valid ordering of:
     * [CS 61A, CS 61B, CS 61C, CS 70, CS 170, CS 161].
     *
     * 中文解释：DFS 在顶点完成时写入后序，最后反转。平局规则约束 DFS
     * 的选择顺序，但不表示反转后的结果必为字典序最小拓扑序。时间为
     * O(V+E)，辅助空间为 O(V)。
     */
    public static final List<String> OFFICIAL_ORDER_ONE =
            List.of("CS 70", "CS 61A", "CS 61B", "CS 170", "CS 61C", "CS 161");
    public static final List<String> OFFICIAL_ORDER_TWO =
            List.of("CS 61A", "CS 61B", "CS 61C", "CS 70", "CS 170", "CS 161");

    public static boolean isValidTopologicalOrder(List<String> order,
                                                   List<Prerequisite> edges) {
        Map<String, Integer> position = new HashMap<>();
        for (int i = 0; i < order.size(); i += 1) {
            position.put(order.get(i), i);
        }
        for (Prerequisite edge : edges) {
            if (!position.containsKey(edge.prerequisite())
                    || !position.containsKey(edge.course())
                    || position.get(edge.prerequisite()) >= position.get(edge.course())) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasCycle(List<Prerequisite> edges) {
        Map<String, List<String>> graph = new HashMap<>();
        for (Prerequisite edge : edges) {
            graph.computeIfAbsent(edge.prerequisite(), key -> new ArrayList<>()).add(edge.course());
            graph.computeIfAbsent(edge.course(), key -> new ArrayList<>());
        }
        Set<String> visiting = new HashSet<>();
        Set<String> visited = new HashSet<>();
        for (String vertex : graph.keySet()) {
            if (cycleFrom(vertex, graph, visiting, visited)) {
                return true;
            }
        }
        return false;
    }

    private static boolean cycleFrom(String vertex, Map<String, List<String>> graph,
                                     Set<String> visiting, Set<String> visited) {
        if (visiting.contains(vertex)) {
            return true;
        }
        if (visited.contains(vertex)) {
            return false;
        }
        visiting.add(vertex);
        for (String neighbor : graph.getOrDefault(vertex, List.of())) {
            if (cycleFrom(neighbor, graph, visiting, visited)) {
                return true;
            }
        }
        visiting.remove(vertex);
        visited.add(vertex);
        return false;
    }

    public static void main(String[] args) {
        require(isValidTopologicalOrder(OFFICIAL_ORDER_ONE, ORIGINAL));
        require(isValidTopologicalOrder(OFFICIAL_ORDER_TWO, ORIGINAL));
        List<Prerequisite> withCycle = new ArrayList<>(ORIGINAL);
        withCycle.add(new Prerequisite("CS 161", "CS 170"));
        withCycle.add(new Prerequisite("CS 170", "CS 61C"));
        require(hasCycle(withCycle));
    }

    private static void require(boolean condition) {
        if (!condition) {
            throw new AssertionError("Discussion 10 Regular Q3 smoke test failed");
        }
    }
}
