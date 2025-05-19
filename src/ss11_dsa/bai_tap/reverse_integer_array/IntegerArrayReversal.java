package ss11_dsa.bai_tap.reverse_integer_array;

import ss3_array_and_method_in_java.helpers.ArrayIO;

import java.util.Stack;

public class IntegerArrayReversal {
    public static void main(String[] args) {
        int[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Stack<Integer> intStack = new Stack<>();

        ArrayIO.outputInt(intArray, "Mảng ban đầu là: ");
        for (int i : intArray) {
            intStack.push(i);
        }
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = intStack.pop();
        }
        ArrayIO.outputInt(intArray, "Mảng đảo ngược là: ");

    }
}
