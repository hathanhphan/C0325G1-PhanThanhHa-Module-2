package ss1_introduction_to_java.bai_tap;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExchangeCurrency {
    public static void main(String[] args) {
        final int RATE = 26000;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Nhập số USD: ");
            double usd = sc.nextDouble();
            while (usd <= 0 || usd > Integer.MAX_VALUE) {
                System.out.printf("Nhập số USD trong khoảng (0, %d): ", Integer.MAX_VALUE);
                usd = sc.nextDouble();
            }
            System.out.printf("Số VND tương ứng là: %.2f", usd * RATE);
        } catch (InputMismatchException e) {
            System.out.println("Bạn nhập giá trị không phải kiểu số. Kết thúc chương trình.");
        }
    }
}
