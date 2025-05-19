package ss0_more_learn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TextProcessingExample {
    public static void main(String[] args) {
        String text = "Java 8 introduced Stream API. Stream API provides a functional approach to process collections. "
                + "It allows sequential and parallel execution. Streams can be created from collections, arrays, "
                + "or I/O resources. Stream operations are either intermediate or terminal. Stream API works with "
                + "lambda expressions to facilitate functional-style programming.";

        // Tách văn bản thành các từ
        String[] words = text.split("\\s+|\\.|\\,");

        // 1. Đếm số từ
        long wordCount = Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .count();
        System.out.println("Word count: " + wordCount);

        // 2. Tìm từ dài nhất
        String longestWord = Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .max(Comparator.comparing(String::length))
                .orElse("");
        System.out.println("Longest word: " + longestWord + " (" + longestWord.length() + " characters)");

        // 3. Đếm tần suất xuất hiện của mỗi từ
        Map<String, Long> wordFrequency = Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
        System.out.println("Word frequency (top 5):");
        wordFrequency.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        // 4. Tính độ dài trung bình của từ
        double averageWordLength = Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .mapToInt(String::length)
                .average()
                .orElse(0);
        System.out.println("Average word length: " + String.format("%.2f", averageWordLength));

        // 5. Tìm các từ bắt đầu bằng một chữ cái cụ thể
        char startLetter = 'S';
        List<String> wordsStartingWith = Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .filter(word -> word.toUpperCase().startsWith(String.valueOf(startLetter).toUpperCase()))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Words starting with '" + startLetter + "': " + wordsStartingWith);

        // 6. Nhóm từ theo độ dài
        Map<Integer, List<String>> wordsByLength = Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Words grouped by length:");
        wordsByLength.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println(entry.getKey() + " chars: " + entry.getValue()));

        // 7. Tạo chuỗi chứa tất cả các từ đã chuyển thành chữ hoa
        String upperCaseText = Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .map(String::toUpperCase)
                .collect(Collectors.joining(" "));
        System.out.println("Uppercase text (first 50 chars): " + upperCaseText.substring(0, Math.min(50, upperCaseText.length())) + "...");

        // 8. Kiểm tra xem văn bản có chứa từ cụ thể không
        String searchWord = "API";
        boolean containsWord = Arrays.stream(words)
                .anyMatch(word -> word.equalsIgnoreCase(searchWord));
        System.out.println("Text contains '" + searchWord + "': " + containsWord);

        // 9. Tìm các từ có độ dài lớn hơn độ dài trung bình
        List<String> wordsLongerThanAverage = Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .filter(word -> word.length() > averageWordLength)
                .distinct()
                .sorted(Comparator.comparing(String::length).reversed())
                .collect(Collectors.toList());
        System.out.println("Words longer than average length (top 5): " +
                wordsLongerThanAverage.stream().limit(5).collect(Collectors.toList()));

        // 10. Thống kê số lượng từ theo chữ cái đầu tiên
        Map<Character, Long> wordCountByFirstLetter = Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(
                        word -> word.toUpperCase().charAt(0),
                        Collectors.counting()
                ));
        System.out.println("Word count by first letter:");
        wordCountByFirstLetter.entrySet().stream()
                .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}

