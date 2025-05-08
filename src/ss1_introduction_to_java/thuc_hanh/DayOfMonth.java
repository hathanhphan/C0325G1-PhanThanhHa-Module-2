package ss1_introduction_to_java.thuc_hanh;

import java.util.Scanner;

public class DayOfMonth {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tháng bạn muốn tính số ngày: ");
        int month = sc.nextInt();
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.printf("Tháng '%d' có 31 ngày!", month);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.printf("Tháng '%d' có 30 ngày!", month);
                break;
            case 2:
                System.out.print("Tháng '2' có 28 và 29 ngày!");
                break;
        }
    }
}
