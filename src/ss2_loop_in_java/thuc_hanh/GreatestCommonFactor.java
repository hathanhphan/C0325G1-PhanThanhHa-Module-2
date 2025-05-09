package ss2_loop_in_java.thuc_hanh;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GreatestCommonFactor {
    public static void main(String[] args) {
        int numberA = 0;
        int numberB = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số number A: ");
        numberA = sc.nextInt();
        while (numberA < Integer.MIN_VALUE / 2 || numberA > Integer.MAX_VALUE / 2) {
            System.out.printf("Nhập số A trong khoảng (%d, %d): ", Integer.MIN_VALUE / 2, Integer.MAX_VALUE / 2);
            numberA = sc.nextInt();
        }
        System.out.print("Nhập số number B: ");
        numberB = sc.nextInt();
        while (numberB < Integer.MIN_VALUE / 2 || numberB > Integer.MAX_VALUE / 2) {
            System.out.printf("Nhập số B trong khoảng (%d, %d): ", Integer.MIN_VALUE / 2, Integer.MAX_VALUE / 2);
            numberB = sc.nextInt();
        }
        if (numberA == 0 || numberB == 0) {
            System.out.println("Không có ước chung lớn nhất");
        } else {
            System.out.println("Ước chung lớn nhất là: " + greatestCommonFactor(numberA, numberB));
        }
    }
    public static int greatestCommonFactor(int numberA, int numberB) {
        int margin;
        while (numberB != 0) {
            margin = numberA % numberB;
            numberA = numberB;
            numberB = margin;
        }
        return Math.abs(numberA);
    }
}
