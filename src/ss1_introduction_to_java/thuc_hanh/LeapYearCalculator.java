package ss1_introduction_to_java.thuc_hanh;

import java.util.Scanner;

public class LeapYearCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int year;
        System.out.print("Nhập 1 năm: ");
        year = sc.nextInt();
        if  (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    System.out.printf("%d là năm nhuận", year);
                } else {
                    System.out.printf("%d không là năm nhuận", year);
                }
            } else {
                System.out.printf("%d là năm nhuận", year);
            }
        } else {
            System.out.printf("%d không là năm nhuận", year);
        }
    }
}
