package ss11_dsa.bai_tap.palindrome_string;

import java.util.*;

public class PalindromeString {
    public static void main(String[] args) {
        // example: able was I ere I saw elba
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập chuỗi: ");
        String str = sc.nextLine();
        if (isPalindromeString(str)) {
            System.out.println(str + " là chuỗi palindrome");
        } else {
            System.out.println(str + " không là chuỗi palindrome");
        }
    }
    public static boolean isPalindromeString(String str) {
        Stack<Character> characterStack = new Stack<>();
        Queue<Character> characterQueue = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            char character = str.charAt(i);
            characterStack.push(character);
            characterQueue.add(character);
        }
        while (!characterStack.isEmpty()) {
            if (characterStack.pop() != characterQueue.poll()) {
                return false;
            }
        }
        return true;
    }
}
