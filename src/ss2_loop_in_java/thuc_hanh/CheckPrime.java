package ss2_loop_in_java.thuc_hanh;

import ss2_loop_in_java.helpers.PrimeNumber;

import java.util.Scanner;

public class CheckPrime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào một số: ");
        int number = sc.nextInt();
        if (PrimeNumber.isPrimeNumber(number)) {
            System.out.println(number + " là số nguyên tố");
        } else {
            System.out.println(number + " không là số nguyên tố");
        }
    }
}
