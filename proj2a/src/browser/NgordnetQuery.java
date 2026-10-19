package browser;

import java.util.List;

/**
 * Created by hug.
 * 中文：由 hug 创建。
 */
public record NgordnetQuery(List<String> words,
        int startYear,
        int endYear,
        int k) {
}
