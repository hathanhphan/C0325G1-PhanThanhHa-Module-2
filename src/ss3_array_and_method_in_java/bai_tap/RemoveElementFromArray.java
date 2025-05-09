package ss3_array_and_method_in_java.bai_tap;

import ss3_array_and_method_in_java.helpers.ArrayIO;

import java.util.Scanner;

public class RemoveElementFromArray {
    public static void main(String[] args) {
        int[] array = ArrayIO.inputInt(3, 50);
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập phần tử cần xoá: ");
        int number = sc.nextInt();
        ArrayIO.outputInt(array, "Mảng đã nhập: ");
        int[] newArray = ArrayIO.removeInt(array, number);
        if (newArray != null) {
            ArrayIO.outputInt(newArray, "Mảng sau khi xoá: ");
        } else {
            System.out.printf("Không tìm thấy phần tử %d trong mảng", number);
        }
    }
}
