package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.List;

public class DummyHistoryTextHandler extends NgordnetQueryHandler {
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        // 中文：你在浏览器中输入了以下信息：
        String response = "You entered the following info into the browser:\n";
        // 中文：单词：
        response += "Words: " + q.words() + "\n";
        // 中文：起始年份：
        response += "Start Year: " + q.startYear() + "\n";
        // 中文：结束年份：
        response += "End Year: " + q.endYear() + "\n";
        return response;
    }
}
