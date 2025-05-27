package ss0_more_learn.lambda;

import java.util.Arrays;
import java.util.List;

public class ForEachDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 3, 6, 7, 2, 11, 55, 2, 22);
        list.forEach(System.out::println);
    }
}
