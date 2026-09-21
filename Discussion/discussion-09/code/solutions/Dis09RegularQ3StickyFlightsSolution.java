package solutions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/** Official solution and runnable example for Discussion 09 Regular Q3. */
public class Dis09RegularQ3StickyFlightsSolution {

    /*
     * Official solution / 官方答案
     * Since we want to find a path of minimum time (weight), using a Shortest
     * Paths Tree algorithm would make sense for this problem. The problem
     * states that we can represent the airports as a graph, so we first create
     * the graph. We have one node for each of the n airports and for all
     * airports directly reachable from a particular airport, we create an
     * undirected edge with the flight time (in hours) between the two airports
     * as the edge weight. From here, there are multiple approaches we can take
     * to adjust the graph.
     *
     * 1. We can increase the edge weights by the associated refueling or
     * grounding time. Think of undirected edges as two directed edges pointing
     * in opposite directions; we can increase the weight of the directed edge
     * by the refueling/grounding time of the node it points to.
     * 2. We can attach the additional weights to the nodes themselves and
     * modify Dijkstra's algorithm to take into account both edge and node
     * weight. This approach will end up looking very similar to A*.
     * 3. We can split airports into two nodes, with the two nodes connected by
     * an edge of weight equal to the refueling/grounding time.
     *
     * Once the graph is prepared, we run Dijkstra's algorithm starting at the
     * node corresponding to Honeysville and terminate once the node
     * corresponding to Apēs City is popped off the fringe. The distTo value of
     * Apēs City is the minimum time and backtracking from Apēs City to
     * Honeysville gives the shortest path.
     *
     * 中文解析
     * 实现采用方案 1：把无向航线拆成两个方向，进入普通中转机场的方向边
     * 加 1，进入宵禁中转机场的方向边加 6，然后运行 Dijkstra。
     *
     * Supplemental analysis / 补充分析（非官方答案）
     * 题意对“沿途”机场收费，因此本实现不在起点和最终目的地重复收取中转
     * 费。官方三种方案未明确说明端点边界；若采用拆点法，也应确保每次
     * 中转恰好收费一次。
     */

    public record Flight(String first, String second, int hours) {
        public Flight {
            if (hours < 0) {
                throw new IllegalArgumentException("flight time must be nonnegative");
            }
        }
    }

    public record Route(List<String> airports, long totalHours) {
        public Route {
            airports = List.copyOf(airports);
        }
    }

    private record Step(String airport, long distance) {
    }

    private record Leg(String destination, int hours) {
    }

    public static Route fastestRoute(
            List<Flight> flights,
            Set<String> curfewAirports,
            String honeysville,
            String apesCity) {
        Map<String, List<Leg>> graph = new HashMap<>();
        for (Flight flight : flights) {
            graph.computeIfAbsent(flight.first(), unused -> new ArrayList<>())
                    .add(new Leg(flight.second(), flight.hours()));
            graph.computeIfAbsent(flight.second(), unused -> new ArrayList<>())
                    .add(new Leg(flight.first(), flight.hours()));
        }
        graph.computeIfAbsent(honeysville, unused -> new ArrayList<>());
        graph.computeIfAbsent(apesCity, unused -> new ArrayList<>());

        Map<String, Long> distTo = new HashMap<>();
        Map<String, String> edgeTo = new HashMap<>();
        PriorityQueue<Step> fringe = new PriorityQueue<>(Comparator.comparingLong(Step::distance));
        distTo.put(honeysville, 0L);
        fringe.add(new Step(honeysville, 0L));

        while (!fringe.isEmpty()) {
            Step current = fringe.remove();
            if (current.distance() != distTo.getOrDefault(current.airport(), Long.MAX_VALUE)) {
                continue;
            }
            if (current.airport().equals(apesCity)) {
                return new Route(reconstruct(edgeTo, honeysville, apesCity), current.distance());
            }
            for (Leg leg : graph.getOrDefault(current.airport(), List.of())) {
                long stopTime = leg.destination().equals(apesCity)
                        ? 0
                        : (curfewAirports.contains(leg.destination()) ? 6 : 1);
                long candidate = current.distance() + leg.hours() + stopTime;
                if (candidate < distTo.getOrDefault(leg.destination(), Long.MAX_VALUE)) {
                    distTo.put(leg.destination(), candidate);
                    edgeTo.put(leg.destination(), current.airport());
                    fringe.add(new Step(leg.destination(), candidate));
                }
            }
        }
        throw new IllegalArgumentException("Apēs City is unreachable");
    }

    private static List<String> reconstruct(
            Map<String, String> edgeTo, String start, String goal) {
        LinkedList<String> path = new LinkedList<>();
        String current = goal;
        while (current != null) {
            path.addFirst(current);
            if (current.equals(start)) {
                return path;
            }
            current = edgeTo.get(current);
        }
        throw new IllegalStateException("broken predecessor chain");
    }

    public static void main(String[] args) {
        List<Flight> flights = List.of(
                new Flight("Honeysville", "Plain", 2),
                new Flight("Plain", "Apēs City", 2),
                new Flight("Honeysville", "Curfew", 1),
                new Flight("Curfew", "Apēs City", 1));
        Route route = fastestRoute(flights, new HashSet<>(Set.of("Curfew")),
                "Honeysville", "Apēs City");
        if (!route.airports().equals(List.of("Honeysville", "Plain", "Apēs City"))
                || route.totalHours() != 5) {
            throw new AssertionError(route);
        }
    }

    private Dis09RegularQ3StickyFlightsSolution() {
    }
}
