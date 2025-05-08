package ss1_introduction_to_java.thuc_hanh;

import java.util.Scanner;

public class LinearEquationResolver {
    public static void main(String[] args) {
        System.out.println("Giải phương trình bậc 1");
        System.out.println("Nhập vào phương trình a * x + b = c:");
        Scanner sc = new Scanner(System.in);
        System.out.print("a: ");
        double a = sc.nextDouble();
        System.out.print("b: ");
        double b = sc.nextDouble();
        System.out.print("c: ");
        double c = sc.nextDouble();
        if (a != 0) {
            double x = -b / a;
            System.out.printf("Nghiệm của phương trình là: x = %f!\n", x);
        } else {
            if (b == c) {
                System.out.println("Phương trình vô số nghiệm!");
            } else {
                System.out.println("Phương trình vô nghiệm!");
            }
        }
    }
}
