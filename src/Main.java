import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Consumer<String> printer = System.out::println;
        printer.accept("hẹ hẹ hẹ");
    }
}
