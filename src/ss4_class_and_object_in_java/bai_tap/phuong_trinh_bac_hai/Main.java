package ss4_class_and_object_in_java.bai_tap.phuong_trinh_bac_hai;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Test case:
        // - 1.0 3 1 => x1 = 0.38, x2 = 2.62
        // - 1 2.0 1 => x1 = x2 = -1
        // - 1 2 3 => vô nghiệm
        // - 0 2 4 => x = -2
        // - 0 0 3 => vô nghiệm
        // - 0 0 0 => vô số nghiệm

        double a = inputNumber("a");
        double b = inputNumber("b");
        double c = inputNumber("c");
        QuadraticEquation quadraticEquation = new QuadraticEquation(a, b, c);
        quadraticEquation.solve();
    }

    public static double inputNumber(String name) {
        double number;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.printf("Nhập vào số %s: ", name);
            number = sc.nextDouble();
            if (number < Integer.MIN_VALUE || number > Integer.MAX_VALUE) {
                System.out.printf("Nhập giá trị của %s trong khoảng (%d, %d)", name, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
        } while (number < Integer.MIN_VALUE || number > Integer.MAX_VALUE);
        return number;
    }
}
