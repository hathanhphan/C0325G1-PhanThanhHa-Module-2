package ss0_more_learn;

import java.util.*;
import java.util.stream.Collectors;

public class Stream {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Jane", "Jack", "James", "Jill");

        // Lọc tên bắt đầu bằng "Ja", chuyển thành chữ hoa và sắp xếp
        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("Ja"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(filteredNames); // [JACK, JAMES, JANE]

        // Tìm tên dài nhất
        Optional<String> longestName = names.stream()
                .max(Comparator.comparing(String::length));

        longestName.ifPresent(name -> System.out.println("Longest name: " + name));

        // Đếm số tên có độ dài lớn hơn 4
        long count = names.stream()
                .filter(name -> name.length() > 4)
                .count();

        System.out.println("Names longer than 4 characters: " + count);

        // Kiểm tra xem tất cả tên có bắt đầu bằng 'J' không
        boolean allStartWithJ = names.stream()
                .allMatch(name -> name.startsWith("J"));

        System.out.println("All names start with J: " + allStartWithJ);

        // Tính tổng độ dài các tên
        int totalLength = names.stream()
                .mapToInt(String::length)
                .sum();

        System.out.println("Total length of all names: " + totalLength);

        // Gom nhóm tên theo độ dài
        Map<Integer, List<String>> groupedByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println("Names grouped by length: " + groupedByLength);

        // Tạo parallel stream từ collection
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sum = numbers.parallelStream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);

        // Chuyển đổi sequential stream thành parallel stream
        int product = numbers.stream()
                .parallel()
                .reduce(1, (a, b) -> a * b);
        System.out.println(product);

    }
}
