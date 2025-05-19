package ss11_dsa.bai_tap.reverse_integer_array;

import java.util.Arrays;
import java.util.Stack;

public class StringReversal {
    public static void main(String[] args) {
        String str = "    Đảo   ngược   phần     tử trong    mảng số    nguyên sử     dụng      Stack      ";
        String[] wordArray = str.trim().split("\\s+");
        Stack<String> wordStack = new Stack<>();

        System.out.println("Chuỗi ban đầu là: " + String.join(" ", wordArray));
        for (String word : wordArray) {
            wordStack.push(word);
        }
        for (int i = 0; i < wordArray.length; i++) {
            wordArray[i] = wordStack.pop();
        }
        System.out.println("Chuỗi đảo ngược là: " + String.join(" ", wordArray));
    }
}
