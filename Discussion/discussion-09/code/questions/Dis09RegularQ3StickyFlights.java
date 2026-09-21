package questions;

import java.util.List;
import java.util.Set;

/** Discussion 09 Regular Question 3: Sticky Flights / 蜂蜜航班. */
public class Dis09RegularQ3StickyFlights {

    /*
     * English - Official question
     * Your airline company has been contracted to fly a large shipment of
     * honey from Honeysville to the 61Bees in Apēs City. However, the airplane
     * doesn't have enough fuel capacity to fly directly to Apēs City so it
     * will stop at at least one of n airports along the way to refuel.
     * Refueling takes an hour, and if the airport is one of k < n airports,
     * your airplane will be grounded for six hours due to curfews (refueling
     * is included in the six hours). The 61Bees want their honey as soon as
     * possible so please design an algorithm to find the route that will allow
     * your airplane to reach Apēs City in the least amount of hours.
     *
     * Hint: Think of the n airports as a graph, where the paths between them
     * are edges of weight equivalent to the number of hours it takes to fly
     * from airport A to airport B. You may assume that the amount of time it
     * takes to fly from A to B is equal to the amount of time it takes to fly
     * from B to A.
     *
     * 中文翻译
     * 飞机要把蜂蜜从 Honeysville 运到 Apēs City，途中至少在 n 个机场中的
     * 一个加油。普通加油耗时 1 小时；k 个受宵禁影响的机场会停留 6 小时，
     * 其中已含加油时间。请把机场建模为无向加权图，设计算法求总耗时最少
     * 的路线。
     *
     * Constraints / 限制
     * - Flight times are symmetric, as stated by the official hint.
     * - Use the problem's nonnegative time model so a shortest-path algorithm
     *   for nonnegative weights is applicable.
     *
     * Your answer / 你的答案
     * Graph transformation / 图转换：TODO
     * Algorithm / 算法：TODO
     * Runtime / 运行时间：TODO
     */

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    public record Flight(String first, String second, int hours) {
    }

    /** Compilation scaffold: result shape only; it contains no solved route. */
    public record Route(List<String> airports, long totalHours) {
    }

    /**
     * TODO: Implement the algorithm described in your answer.
     * The initial question file deliberately compiles without revealing it.
     */
    public static Route fastestRoute(
            List<Flight> flights,
            Set<String> curfewAirports,
            String honeysville,
            String apesCity) {
        throw new UnsupportedOperationException("TODO: implement Sticky Flights");
    }

    private Dis09RegularQ3StickyFlights() {
    }
}
