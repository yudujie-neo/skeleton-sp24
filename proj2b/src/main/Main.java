package main;

import browser.NgordnetServer;
import org.slf4j.LoggerFactory;

public class Main {
    static {
        // 中文：将文字颜色改为白色。
        LoggerFactory.getLogger(Main.class).info("\033[1;38mChanging text color to white");
    }
    public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();

        String synsetFile = "./data/wordnet/synsets.txt";
        String hyponymFile = "./data/wordnet/hyponyms.txt";

        hns.startUp();
        hns.register("history", new DummyHistoryHandler());
        hns.register("historytext", new DummyHistoryTextHandler());
        hns.register("hyponyms", new HyponymsHandler());

        // 中文：服务器启动完成！请访问 http://localhost:4567/ngordnet.html。
        System.out.println("Finished server startup! Visit http://localhost:4567/ngordnet.html");
    }
}