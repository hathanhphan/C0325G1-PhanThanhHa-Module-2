package ss11_dsa.bai_tap.count_num_of_occurrences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class NumOfOccurrences {
    public static void main(String[] args) {
        String str = "Kiểm tra xem trong map có key này hay chưa. " +
                "Nếu có đẩy key này vào map và tăng value (số lượng từ) lên 1. " +
                "Nếu key này chưa có trong map thì đẩy key vào map mặc định gán value là 1.";
        String[] wordArray = str.trim().toLowerCase().split("[\\s\\p{Punct}]+");
        TreeMap<String, Integer> wordCountMap = Arrays.stream(wordArray)
                .collect(Collectors.toMap(
                        word -> word,
                        word -> 1,
                        Integer::sum,
                        TreeMap::new));

        System.out.println("\nSố lần xuất hiện của từng từ:");
        System.out.println("---------------------------");
        wordCountMap.forEach((word, count) ->
                System.out.println(word + ": " + count)
        );
    }
}
