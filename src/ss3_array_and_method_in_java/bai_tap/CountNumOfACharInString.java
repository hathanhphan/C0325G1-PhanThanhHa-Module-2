package ss3_array_and_method_in_java.bai_tap;

import java.util.Scanner;

public class CountNumOfACharInString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào 1 chuỗi kí tự: ");
        String str = sc.nextLine();
        System.out.print("Nhập vào 1 kí tự cần đếm số lần xuất hiện: ");
        char ch = sc.nextLine().charAt(0);
        System.out.printf("Số lần xuất hiện của %c trong \"%s\" là %d", ch, str, countNumOfOccurrences(str, ch));
    }

    public static int countNumOfOccurrences(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }
}
