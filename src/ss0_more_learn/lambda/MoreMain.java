package ss0_more_learn.lambda;

interface Data2 {
    int demo(int x, float y);
}

public class MoreMain {
    public static void main(String[] args) {
        Data2 data = (x, y) -> {
            System.out.println("Value of y is " + y);
            System.out.println("The value of x will be returned by the method");
            return x;
        };
        System.out.println(data.demo(3, 4));
    }
}
