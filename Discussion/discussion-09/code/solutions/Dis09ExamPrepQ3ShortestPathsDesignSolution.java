package solutions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/** Official solutions and runnable 3c implementation for Exam Prep Q3. */
public class Dis09ExamPrepQ3ShortestPathsDesignSolution {

    /*
     * 3a - Official solution / 官方答案
     * Run Dijkstra's, starting from the only city m in M. This will generate
     * the shortest distance to all cities of Teyvat. Then, iterate over every
     * city f in F, and take the minimum distance from m to f.
     *
     * 中文解析
     * 一次单源 Dijkstra 后扫描所有 F 中目标，时间仍为
     * O((V+E) log V)。
     */

    /*
     * 3b - Official solution / 官方答案
     * Create a dummy vertex d. For each city in M, create an edge from d to
     * that city, with edge weight 0. This creates a new graph G'. Run
     * Dijkstra's, starting from dummy vertex d, on the graph G'. Take the
     * distance from d to the only city in F and return it. This works because
     * for each path in the original graph from any city in M to F, it
     * corresponds to a path in the new graph G' that starts from d and ends in
     * F, and they are equal in length. Therefore, our algorithm correctly
     * computes the shortest path from any city in M to any city in F.
     *
     * 中文解析
     * 零权超级源点把多源问题转成单源最短路；新增一个顶点和 |M| 条边。
     */

    /*
     * 3c - Official solution / 官方答案
     * These steps are the same as previous part: Create a dummy vertex d. For
     * each city in Mondstadt, create an edge from d to that city, with edge
     * weight 0. This creates a new graph G'. Run Dijkstra, starting from dummy
     * vertex d, on the graph G'.
     *
     * Now, instead of directly taking the distance from d to the only city in
     * F as in the previous part, take the minimum distance from d to all cities
     * in F, and return it.
     *
     * 中文解析
     * 实现把所有 M 中城市以距离 0 同时放入优先队列，等价于显式添加超级
     * 源点；结束后对 F 取最小距离。
     *
     * Supplemental analysis / 补充分析（非官方答案）
     * Dijkstra 要求铁路长度非负。题目将权重描述为铁路长度，通常隐含该
     * 条件；实现显式拒绝负权。
     */

    public record Railway(String from, String to, long length) {
        public Railway {
            if (length < 0) {
                throw new IllegalArgumentException("railway length must be nonnegative");
            }
        }
    }

    private record Step(String city, long distance) {
    }

    private record Leg(String destination, long length) {
    }

    public static long minimumCountryDistance(
            Set<String> cities,
            List<Railway> railways,
            Set<String> mondstadt,
            Set<String> fontaine) {
        if (mondstadt.isEmpty() || fontaine.isEmpty()) {
            throw new IllegalArgumentException("both countries need at least one city");
        }
        Map<String, List<Leg>> graph = new HashMap<>();
        for (String city : cities) {
            graph.put(city, new ArrayList<>());
        }
        for (Railway railway : railways) {
            if (!graph.containsKey(railway.from()) || !graph.containsKey(railway.to())) {
                throw new IllegalArgumentException("railway endpoint is not in cities");
            }
            graph.get(railway.from()).add(new Leg(railway.to(), railway.length()));
        }

        Map<String, Long> distTo = new HashMap<>();
        PriorityQueue<Step> fringe = new PriorityQueue<>(Comparator.comparingLong(Step::distance));
        for (String source : mondstadt) {
            if (!graph.containsKey(source)) {
                throw new IllegalArgumentException("Mondstadt city is not in cities");
            }
            distTo.put(source, 0L);
            fringe.add(new Step(source, 0L));
        }

        while (!fringe.isEmpty()) {
            Step current = fringe.remove();
            if (current.distance() != distTo.getOrDefault(current.city(), Long.MAX_VALUE)) {
                continue;
            }
            if (fontaine.contains(current.city())) {
                return current.distance();
            }
            for (Leg leg : graph.get(current.city())) {
                long candidate = current.distance() + leg.length();
                if (candidate < distTo.getOrDefault(leg.destination(), Long.MAX_VALUE)) {
                    distTo.put(leg.destination(), candidate);
                    fringe.add(new Step(leg.destination(), candidate));
                }
            }
        }
        return Long.MAX_VALUE;
    }

    public static void main(String[] args) {
        Set<String> cities = Set.of("M1", "M2", "X", "F1", "F2");
        List<Railway> railways = List.of(
                new Railway("M1", "X", 8),
                new Railway("M2", "X", 2),
                new Railway("X", "F1", 3),
                new Railway("M1", "F2", 10));
        long result = minimumCountryDistance(
                cities, railways, Set.of("M1", "M2"), Set.of("F1", "F2"));
        if (result != 5) {
            throw new AssertionError("expected 5, got " + result);
        }
    }

    private Dis09ExamPrepQ3ShortestPathsDesignSolution() {
    }
}
