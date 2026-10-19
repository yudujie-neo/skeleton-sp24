package ngrams;

import java.util.Collection;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * 中文：提供用于查询 Google NGrams 数据集或其子集的辅助方法。
 * NGramMap 保存从“单词文件”和“计数文件”中读取的相关数据。
 * 它并不是严格意义上的映射（Map），但提供了额外的功能。
 * 作者：Josh Hug。
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    // 中文：添加所有必要的静态变量或实例变量。

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     * 中文：使用 WORDSFILENAME 和 COUNTSFILENAME 指定的文件构造 NGramMap。
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        // 中文：补全此构造函数。可参阅项目说明中的“NGramMap Tips”部分获取帮助。
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     *
     * 中文：返回 WORD 在 STARTYEAR 到 ENDYEAR 之间的出现次数历史，包含起止年份。
     * 返回的 TimeSeries 应是副本，而不是指向此 NGramMap 内部 TimeSeries 的引用。
     * 换言之，修改返回的对象不应影响 NGramMap，这称为“防御性拷贝”。
     * 如果数据文件中没有该单词，则返回空的 TimeSeries。
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        // 中文：补全此方法。
        return null;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     *
     * 中文：返回 WORD 的出现次数历史。返回的 TimeSeries 应是副本，
     * 而不是指向此 NGramMap 内部 TimeSeries 的引用。
     * 换言之，修改返回的对象不应影响 NGramMap，这称为“防御性拷贝”。
     * 如果数据文件中没有该单词，则返回空的 TimeSeries。
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        // 中文：补全此方法。
        return null;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     * 中文：返回所有书籍中每年记录的单词总数的防御性拷贝。
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        // 中文：补全此方法。
        return null;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     *
     * 中文：返回包含 WORD 在 STARTYEAR 到 ENDYEAR 之间每年相对频率的 TimeSeries，
     * 包含起止年份。如果数据文件中没有该单词，则返回空的 TimeSeries。
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        // 中文：补全此方法。
        return null;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     *
     * 中文：返回包含 WORD 每年相对频率的 TimeSeries，即该单词当年的出现次数
     * 占当年记录的全部单词总数的比例。如果数据文件中没有该单词，则返回空的 TimeSeries。
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        // 中文：补全此方法。
        return null;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     *
     * 中文：返回 WORDS 中所有单词在 STARTYEAR 到 ENDYEAR 之间每年相对频率的总和，
     * 包含起止年份。如果某个单词在该时间范围内不存在，则忽略该单词，不抛出异常。
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        // 中文：补全此方法。
        return null;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     *
     * 中文：返回 WORDS 中所有单词每年相对频率的总和。
     * 如果某个单词在该时间范围内不存在，则忽略该单词，不抛出异常。
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        // 中文：补全此方法。
        return null;
    }

    // TODO: Add any private helper methods.
    // 中文：添加所需的私有辅助方法。
    // TODO: Remove all TODO comments before submitting.
    // 中文：提交前删除所有 TODO 注释。
}
