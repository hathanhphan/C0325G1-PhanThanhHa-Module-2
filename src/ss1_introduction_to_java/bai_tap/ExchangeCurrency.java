package ss1_introduction_to_java.bai_tap;

import java.util.Scanner;

public class ExchangeCurrency {
    public static void main(String[] args) {
        int rate = 26000;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số USD: ");
        double usd = sc.nextDouble();
        System.out.printf("Số VND tương ứng là: %.2f", usd * rate);
    }
}
