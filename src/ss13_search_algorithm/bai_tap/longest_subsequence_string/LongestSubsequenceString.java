package ss13_search_algorithm.bai_tap.longest_subsequence_string;

import java.util.LinkedList;

public class LongestSubsequenceString {
    public static void main(String[] args) {
        String str = "Welcome";
        System.out.println("Chuỗi tăng dần có độ dài lớn nhất là: " + findString(str));
    }
    public static String findString(String str) {
        LinkedList<Character> longestString = new LinkedList<>();
        LinkedList<Character> checkingString;
        for (int i = 0; i < str.length() - 1; i++) {
            checkingString = new LinkedList<>();
            checkingString.add(str.charAt(i));
            for (int j = 1; j < str.length(); j++) {
                if (checkingString.getLast() < str.charAt(j)) {
                    checkingString.add(str.charAt(j));
                }
            }
            if (checkingString.size() > longestString.size() || longestString.isEmpty()) {
                longestString.clear();
                longestString.addAll(checkingString);
            }
        }
        StringBuilder result = new StringBuilder();
        for (Character ch : longestString) {
            result.append(ch.toString());
        }
        return result.toString();
    }
}
