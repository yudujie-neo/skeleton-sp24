package browser;

import static spark.Spark.*;

/**
 * Created by hug.
 * 中文：由 hug 创建。
 */
public class NgordnetServer {
    public void register(String URL, NgordnetQueryHandler nqh) {
        get(URL, nqh);
    }

    public void startUp() {
        staticFiles.externalLocation("static");

        /* Allow for all origin requests (since this is not an authenticated server, we do not
         * care about CSRF).  */
        // 中文：允许来自所有来源的请求（由于此服务器不进行身份验证，
        // 中文：因此这里不考虑 CSRF，即跨站请求伪造问题）。
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "*");
            response.header("Access-Control-Allow-Headers", "*");
        });
    }
}
