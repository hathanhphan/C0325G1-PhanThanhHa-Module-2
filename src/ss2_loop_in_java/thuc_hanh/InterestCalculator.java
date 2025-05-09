package ss2_loop_in_java.thuc_hanh;

import java.util.Scanner;

public class InterestCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào số tiền tiết kiệm: ");
        double money = sc.nextDouble();
        System.out.print("Nhập vào số tháng gửi: ");
        int month = sc.nextInt();
        System.out.print("Nhập vào lãi suất theo tháng: ");
        double interestRate = sc.nextDouble();
        double totalInterest = 0;
        for(int i = 0; i < month; i++){
            totalInterest += money * (interestRate/100)/12 * month;
        }
        System.out.println("Tổng lãi suất: " + totalInterest);
    }
}
