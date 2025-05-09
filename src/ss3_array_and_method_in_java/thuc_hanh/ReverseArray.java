package ss3_array_and_method_in_java.thuc_hanh;

import ss3_array_and_method_in_java.helpers.ArrayIO;

public class ReverseArray {
    public static void main(String[] args) {
        int[] array = ArrayIO.inputInt(3, 50);
        int size = array.length;
        ArrayIO.outputInt(array, "Mảng đã nhập: ");
        for (int i = 0; i < size / 2; i++) {
            int temp = array[i];
            array[i] = array[size - 1 - i];
            array[size - 1 - i] = temp;
        }
        ArrayIO.outputInt(array, "Mảng đảo ngược: ");
    }
}
