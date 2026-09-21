package questions;

import java.util.List;
import java.util.Set;

/** Discussion 09 Exam Prep Question 3: Shortest Paths Algorithm Design. */
public class Dis09ExamPrepQ3ShortestPathsDesign {

    /*
     * English - Official question
     * Two countries, Mondstadt and Fontaine, are located in the fictional
     * world of Teyvat. The railroad system of Teyvat can be modeled as a
     * weighted directed graph, with V vertices, E edges, and weights being the
     * length of the railway. Circle the traveler wishes to take railway from
     * Mondstadt to Fontaine, and needs to determine the shortest railway
     * distance between them. Define the set M to be all cities in Mondstadt,
     * and F to be all cities in Fontaine. The shortest distance between the
     * two countries is the shortest distance between any city c_M in
     * Mondstadt and c_F in Fontaine.
     *
     * For each of the subparts below, describe an algorithm that compute the
     * minimum railway distance from Mondstadt to Fontaine, in
     * O((V + E) log V) time. You are able to call all graph algorithms you
     * learned in class as a black box.
     *
     * Hint: For some parts, consider modifying the graph so that running a
     * graph algorithm yields an equivalent answer to solving the original
     * problem.
     *
     * 中文翻译
     * 提瓦特铁路是含 V 个顶点、E 条边的加权有向图。M 和 F 分别是蒙德与
     * 枫丹的城市集合，两国距离是任意 c_M 到任意 c_F 的最短距离。请为各
     * 小问设计 O((V+E)log V) 算法；可把已学图算法作为黑盒，并可修改图。
     */

    /*
     * 3a - English - Official question
     * Mondstadt only contains 1 city (|M| = 1), but Fontaine contains many
     * cities (|F| > 1).
     *
     * 3a - 中文翻译
     * 蒙德只有一个城市，枫丹有多个城市。
     *
     * Your answer / 你的答案：TODO
     */

    /*
     * 3b - English - Official question
     * Mondstadt contains many cities (|M| > 1), but Fontaine only contains one
     * city (|F| = 1).
     *
     * 3b - 中文翻译
     * 蒙德有多个城市，枫丹只有一个城市。
     *
     * Your answer / 你的答案：TODO
     */

    /*
     * 3c - English - Official question
     * Both countries contain many cities (|M|, |F| > 1).
     *
     * 3c - 中文翻译
     * 两国都包含多个城市。
     *
     * Your answer / 你的答案：TODO
     */

    /** Compilation scaffold / 编译脚手架（非官方题目内容）. */
    public record Railway(String from, String to, long length) {
    }

    /**
     * TODO: implement the algorithm described for 3c. This compilable stub is
     * intentionally answer-free.
     */
    public static long minimumCountryDistance(
            Set<String> cities,
            List<Railway> railways,
            Set<String> mondstadt,
            Set<String> fontaine) {
        throw new UnsupportedOperationException("TODO: implement Question 3");
    }

    private Dis09ExamPrepQ3ShortestPathsDesign() {
    }
}
