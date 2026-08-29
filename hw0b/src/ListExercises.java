import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        // 中文：补全此函数。
        int sum = 0;
       for(int i = 0; i < L.size(); i++){
           sum += L.get(i);
       }
       return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        // 中文：补全此函数。
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < L.size(); i++){
            if(L.get(i) % 2 == 0){
                list.add(L.get(i));
            }
        }
        return list;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        // 中文：补全此函数。
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < L1.size(); i++){
            for(int j = 0; j < L2.size(); j++){
                if(Objects.equals(L1.get(i), L2.get(j))){
                    list.add(L1.get(i));
                    break;
                }
            }
        }
        return list;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        // 中文：补全此函数。
        int count = 0;
        for(int i = 0; i < words.size(); i++){
            String word = words.get(i);

            for(int j = 0; j < word.length(); j++){
                if(word.charAt(j) == c){
                    count++;
                }
            }
        }
        return count;
    }
}
