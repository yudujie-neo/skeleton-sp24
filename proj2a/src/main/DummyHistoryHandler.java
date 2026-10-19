package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.TimeSeries;
import plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;


public class DummyHistoryHandler extends NgordnetQueryHandler {
    @Override
    public String handle(NgordnetQuery q) {
        // 中文：收到的查询内容如下：
        System.out.println("Got query that looks like:");
        // 中文：单词：
        System.out.println("Words: " + q.words());
        // 中文：起始年份：
        System.out.println("Start Year: " + q.startYear());
        // 中文：结束年份：
        System.out.println("End Year: " + q.endYear());

        // 中文：不过，我完全忽略了这些信息，只绘制一条抛物线和一条正弦曲线，
        // 因为你的任务是弄清楚如何真正使用查询中的数据。
        System.out.println("But I'm totally ignoring that and just plotting a parabola\n" +
                        "and a sine wave, because your job will be to figure out how to\n" +
                        "actually use the query data.");

        TimeSeries parabola = new TimeSeries();
        for (int i = 1400; i < 1500; i += 1) {
            parabola.put(i, (i - 50.0) * (i - 50.0) + 3);
        }

        TimeSeries sinWave = new TimeSeries();
        for (int i = 1400; i < 1500; i += 1) {
            sinWave.put(i, 1000 + 500 * Math.sin(i/100.0*2*Math.PI));
        }

        ArrayList<TimeSeries> lts = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        // 中文：抛物线。
        labels.add("parabola");
        // 中文：正弦曲线。
        labels.add("sine wave");

        lts.add(parabola);
        lts.add(sinWave);

        XYChart chart = Plotter.generateTimeSeriesChart(labels, lts);
        String encodedImage = Plotter.encodeChartAsString(chart);

        return encodedImage;
    }
}
