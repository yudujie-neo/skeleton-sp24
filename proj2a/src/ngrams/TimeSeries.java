package ngrams;

import java.util.List;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * 中文：将年份（例如 1996）映射到数值数据的对象，并提供用于数据分析的辅助方法。
 * 作者：Josh Hug。
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    // 中文：如果有助于提高代码速度，可以假定传给 NGramMap 的年份参数都在 1400 到 2100 之间。
    // 这两个边界值在这里保存为常量 MIN_YEAR 和 MAX_YEAR。
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     * 中文：构造一个新的空 TimeSeries。
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     * 中文：创建 TS 的副本，只保留 STARTYEAR 到 ENDYEAR 之间的数据，包含起止年份。
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        // TODO: Fill in this constructor.
        // 中文：补全此构造函数。

    }

    /**
     * Returns all years for this TimeSeries (in any order).
     * 中文：返回此 TimeSeries 中的所有年份，顺序不限。
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
        // 中文：补全此方法。
        return null;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     * 中文：返回此 TimeSeries 中的所有数据，顺序不限，但必须与 years() 返回的年份顺序一致。
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        // 中文：补全此方法。
        return null;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     *
     * 中文：按年份将此 TimeSeries 与给定的 TS 相加，也就是将同一年份对应的数据相加。
     * 返回一个新的 TimeSeries，不修改当前 TimeSeries。
     * 如果两个 TimeSeries 都没有任何年份，返回空的 TimeSeries。
     * 如果某个年份只存在于其中一个 TimeSeries 中，结果中应保留该序列在这一年的数值。
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        // 中文：补全此方法。
        return null;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     *
     * 中文：对当前 TimeSeries 中的每个年份，用其数值除以 TS 中同一年份的数值。
     * 返回一个新的 TimeSeries，不修改当前 TimeSeries。
     * 如果 TS 缺少当前 TimeSeries 中已有的某个年份，抛出 IllegalArgumentException。
     * 如果 TS 包含当前 TimeSeries 中没有的年份，则忽略该年份。
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        // 中文：补全此方法。
        return null;
    }

    // TODO: Add any private helper methods.
    // 中文：添加所需的私有辅助方法。
    // TODO: Remove all TODO comments before submitting.
    // 中文：提交前删除所有 TODO 注释。
}
