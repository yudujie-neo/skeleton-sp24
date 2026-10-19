package main;

import static utils.Utils.*;

import org.slf4j.LoggerFactory;

import browser.NgordnetServer;

public class Main {
    static {
        // 中文：将文本颜色改为白色。
        LoggerFactory.getLogger(Main.class).info("\033[1;38mChanging text color to white");
    }
    /* Do not delete or modify the code above! */
    // 中文：不要删除或修改上面的代码！

    public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();

        /* The following code might be useful to you.
        中文：下面的代码可能对你有帮助。

        NGramMap ngm = new NGramMap(SHORT_WORDS_FILE, TOTAL_COUNTS_FILE);

        */

        hns.startUp();
        hns.register("history", new DummyHistoryHandler());
        hns.register("historytext", new DummyHistoryTextHandler());

        // 中文：服务器启动完成！请访问 http://localhost:4567/ngordnet_2a.html。
        System.out.println("Finished server startup! Visit http://localhost:4567/ngordnet_2a.html");
    }
}
