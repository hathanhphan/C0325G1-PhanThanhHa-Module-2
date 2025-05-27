package ss0_more_learn.lambda;

import java.util.function.Predicate;

public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<Integer> lessThan10 = integer -> integer < 10;
        System.out.println(lessThan10.test(10));
        System.out.println(lessThan10.test(20));
        System.out.println(lessThan10.test(5));
    }
}
