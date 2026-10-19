package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

public class DummyHistoryTextHandler extends NgordnetQueryHandler {
    @Override
    public String handle(NgordnetQuery q) {
        // 中文：NGrams 和时间序列与 Project 2B 无关！此按钮不应执行任何操作。
        return "NGrams and Timeseries aren't relevant for 2B! This button should do nothing.";
    }
}
