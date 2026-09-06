package adventure;

public class AdventureUtils {

    /** Returns whether the given string is a valid int.
     * 中文：返回给定字符串是否表示一个合法的 int。 */
    static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
