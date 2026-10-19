package main;

import browser.NgordnetServer;
import org.slf4j.LoggerFactory;

public class Main {
    static {
        // 中文：将文本颜色改为白色。
        LoggerFactory.getLogger(Main.class).info("\033[1;38mChanging text color to white");
    }
    public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();
        
        /* The following code might be useful to you.
        中文：下面的代码可能对你有帮助。

        String wordFile = "./data/ngrams/top_14377_words.csv";
        String countFile = "./data/ngrams/total_counts.csv";
        NGramMap ngm = new NGramMap(wordFile, countFile);

        */

        hns.startUp();
        hns.register("history", new DummyHistoryHandler());
        hns.register("historytext", new DummyHistoryTextHandler());

        // 中文：服务器启动完成！请访问 http://localhost:4567/ngordnet.html。
        System.out.println("Finished server startup! Visit http://localhost:4567/ngordnet.html");
    }
}
