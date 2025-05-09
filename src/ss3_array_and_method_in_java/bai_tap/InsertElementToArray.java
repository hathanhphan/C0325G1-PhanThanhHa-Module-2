package ss3_array_and_method_in_java.bai_tap;

import ss3_array_and_method_in_java.helpers.ArrayIO;

import java.util.Scanner;

public class InsertElementToArray {
    public static void main(String[] args) {
        int[] array = ArrayIO.inputInt(3, 50);
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập phần tử cần chèn: ");
        int number = sc.nextInt();
        System.out.print("Nhập vị trí chèn: ");
        int position = sc.nextInt();
        ArrayIO.outputInt(array, "Mảng đã nhập: ");
        int[] newArray = ArrayIO.insertInt(array, number, position);
        if (newArray != null) {
            ArrayIO.outputInt(newArray, "Mảng sau khi chèn: ");
        } else {
            System.out.println("Vị trí vượt ngoài mảng, không thể chèn");
        }
    }
}
