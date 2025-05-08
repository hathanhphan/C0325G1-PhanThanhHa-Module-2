package ss1_introduction_to_java.bai_tap;

import java.util.Scanner;

public class Greeting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên của bạn: ");
        String yourName = sc.nextLine();
        System.out.println("Xin chào: " + yourName);
    }
}
