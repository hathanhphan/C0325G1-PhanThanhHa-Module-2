package ss3_array_and_method_in_java.thuc_hanh;

import ss3_array_and_method_in_java.helpers.ArrayIO;

public class MinElementInArray {
    public static void main(String[] args) {
        int[] array = ArrayIO.inputInt(3, 50);
        ArrayIO.outputInt(array, "Mảng đã nhập: ");
        int minIndex = ArrayIO.minInt(array);
        System.out.printf("Phần tử nhỏ nhất trong mảng là %d tại vị trí %d", array[minIndex], minIndex);
    }
}
