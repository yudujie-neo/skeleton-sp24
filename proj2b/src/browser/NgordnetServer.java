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
        // 中文：允许来自任意来源的请求（由于此服务器不进行身份认证，这里不考虑跨站请求伪造 CSRF）。
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Request-Method", "*");
            response.header("Access-Control-Allow-Headers", "*");
        });
    }
}
