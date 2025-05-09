package ss3_array_and_method_in_java.bai_tap;

import ss3_array_and_method_in_java.helpers.ArrayIO;

public class MergeArray {
    public static void main(String[] args) {
        int[] array1 = ArrayIO.inputInt(3, 50, "Nhập số lượng phần tử của mảng 1: ", "");
        int[] array2 = ArrayIO.inputInt(3, 50, "Nhập số lượng phần tử của mảng 2: ", "");
        int[] mergeArray = ArrayIO.mergeInt(array1, array2);
        ArrayIO.outputInt(array1, "Mảng 1 đã nhập: ");
        ArrayIO.outputInt(array2, "Mảng 2 đã nhập: ");
        ArrayIO.outputInt(mergeArray, "Mảng gộp: ");
    }
}
