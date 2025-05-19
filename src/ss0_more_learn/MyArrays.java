package ss0_more_learn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyArrays {
    public static void main(String[] args) {
        // Sắp xếp mảng
        int[] numbers = {5, 2, 9, 1, 5, 6};
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers)); // [1, 2, 5, 5, 6, 9]

        // Sắp xếp một phần của mảng
        int[] partialSort = {5, 2, 9, 1, 5, 6};
        Arrays.sort(partialSort, 1, 4); // Sắp xếp từ index 1 đến 3
        System.out.println(Arrays.toString(partialSort)); // [5, 1, 2, 9, 5, 6]

        // Sắp xếp với Comparator
        String[] names = {"John", "jane", "Jack", "james"};
        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(names)); // [Jack, james, jane, John]

        // Tìm kiếm nhị phân (mảng phải được sắp xếp trước)
        int[] sortedNumbers = {1, 2, 5, 5, 6, 9};
        int index = Arrays.binarySearch(sortedNumbers, 5);
        System.out.println("Found 5 at index: " + index);

        // Tìm kiếm với Comparator
        String[] sortedNames = {"Jack", "James", "Jane", "John"};
        int nameIndex = Arrays.binarySearch(sortedNames, "jane", String.CASE_INSENSITIVE_ORDER);
        System.out.println("Found 'jane' at index: " + nameIndex);

        // Điền toàn bộ mảng với một giá trị
        int[] filledArray = new int[5];
        Arrays.fill(filledArray, 10);
        System.out.println(Arrays.toString(filledArray)); // [10, 10, 10, 10, 10]

        // Điền một phần của mảng
        int[] partialFill = new int[5];
        Arrays.fill(partialFill, 1, 4, 20); // Điền từ index 1 đến 3
        System.out.println(Arrays.toString(partialFill)); // [0, 20, 20, 20, 0]

        // So sánh hai mảng
        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};
        int[] array3 = {1, 2, 4};

        boolean isEqual1 = Arrays.equals(array1, array2);
        boolean isEqual2 = Arrays.equals(array1, array3);

        System.out.println("array1 equals array2: " + isEqual1); // true
        System.out.println("array1 equals array3: " + isEqual2); // false

        // So sánh sâu cho mảng đa chiều
        int[][] deepArray1 = {{1, 2}, {3, 4}};
        int[][] deepArray2 = {{1, 2}, {3, 4}};
        int[][] deepArray3 = {{1, 2}, {3, 5}};

        boolean isDeepEqual1 = Arrays.deepEquals(deepArray1, deepArray2);
        boolean isDeepEqual2 = Arrays.deepEquals(deepArray1, deepArray3);

        System.out.println("deepArray1 equals deepArray2: " + isDeepEqual1); // true
        System.out.println("deepArray1 equals deepArray3: " + isDeepEqual2); // false

        // Chuyển mảng thành String
        int[] array = {1, 2, 3, 4, 5};
        String arrayString = Arrays.toString(array);
        System.out.println(arrayString); // [1, 2, 3, 4, 5]

        // Chuyển mảng đa chiều thành String
        int[][] matrix = {{1, 2}, {3, 4}};
        String matrixString = Arrays.deepToString(matrix);
        System.out.println(matrixString); // [[1, 2], [3, 4]]

        // Sao chép mảng
        int[] original = {1, 2, 3, 4, 5};
        int[] copy = Arrays.copyOf(original, original.length);
        System.out.println(Arrays.toString(copy)); // [1, 2, 3, 4, 5]

        // Sao chép với kích thước mới
        int[] expanded = Arrays.copyOf(original, 7); // Mở rộng thành 7 phần tử
        System.out.println(Arrays.toString(expanded)); // [1, 2, 3, 4, 5, 0, 0]

        int[] truncated = Arrays.copyOf(original, 3); // Cắt bớt còn 3 phần tử
        System.out.println(Arrays.toString(truncated)); // [1, 2, 3]

        // Sao chép một phần của mảng
        int[] range = Arrays.copyOfRange(original, 1, 4); // Từ index 1 đến 3
        System.out.println(Arrays.toString(range)); // [2, 3, 4]

        // Tạo stream từ mảng
        int[] numbers2 = {1, 2, 3, 4, 5};
        IntStream stream = Arrays.stream(numbers2);

        // Sử dụng stream để xử lý mảng
        int sum = stream.sum();
        System.out.println("Sum: " + sum); // 15

        // Tạo stream từ một phần của mảng
        IntStream partialStream = Arrays.stream(numbers2, 1, 4); // Từ index 1 đến 3
        System.out.println("Partial sum: " + partialStream.sum()); // 9

        // Điền mảng bằng lambda expression
        int[] numbers3 = new int[5];
        Arrays.setAll(numbers3, i -> i * 2);
        System.out.println(Arrays.toString(numbers3)); // [0, 2, 4, 6, 8]

        // Điền mảng song song
        int[] largeArray = new int[1000];
        Arrays.parallelSetAll(largeArray, i -> i + 1);
        System.out.println("First few elements: " + largeArray[0] + ", " + largeArray[1] + ", " + largeArray[2]);

        String[] names2 = {"John", "Jane", "Jack", "James", "Jill"};

        // Sắp xếp mảng với lambda
        Arrays.sort(names2, (a, b) -> a.length() - b.length());
        System.out.println("Sorted by length: " + Arrays.toString(names2));

        // Chuyển mảng thành stream và xử lý
        String result = Arrays.stream(names2)
                .filter(name -> name.length() > 4)
                .map(String::toUpperCase)
                .collect(Collectors.joining(", "));

        System.out.println("Names longer than 4 characters: " + result);

        // Tìm tên dài nhất
        Arrays.stream(names2)
                .max(Comparator.comparing(String::length))
                .ifPresent(name -> System.out.println("Longest name: " + name));

        // Tính tổng độ dài các tên
        int totalLength = Arrays.stream(names)
                .mapToInt(String::length)
                .sum();

        System.out.println("Total length: " + totalLength);


    }
}
