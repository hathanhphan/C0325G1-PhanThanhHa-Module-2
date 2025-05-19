package ss0_more_learn;

import java.util.function.*;

/**
 * Function<T, R>: Nhận tham số kiểu T, trả về kiểu R
 * Predicate: Nhận tham số kiểu T, trả về boolean
 * Consumer: Nhận tham số kiểu T, không trả về gì
 * Supplier: Không nhận tham số, trả về kiểu T
 * BiFunction<T, U, R>: Nhận hai tham số kiểu T và U, trả về kiểu R
 * BinaryOperator: Nhận hai tham số cùng kiểu T, trả về kiểu T
* */

public class Lambda {
    public static void main(String[] args) {
        // Function: nhận String, trả về Integer
        Function<String, Integer> stringLength = String::length;

        // Predicate: kiểm tra điều kiện
        Predicate<Integer> isEven = n -> n % 2 == 0;

        // Consumer: thực hiện hành động với tham số
        Consumer<String> printer = System.out::println;

        // Supplier: tạo đối tượng mới
        Supplier<Double> randomValue = Math::random;

        // BiFunction: tính tổng hai số
        BiFunction<Integer, Integer, Integer> sum = Integer::sum;

        System.out.println(stringLength.apply("abc"));
        System.out.println(isEven.test(23));
        printer.accept("hẹ hẹ hẹ");
        System.out.println(randomValue.get());
        System.out.println(sum.apply(3, 5));
    }
}
