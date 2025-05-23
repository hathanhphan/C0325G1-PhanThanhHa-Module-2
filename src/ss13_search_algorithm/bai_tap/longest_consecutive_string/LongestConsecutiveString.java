package ss13_search_algorithm.bai_tap.longest_consecutive_string;

import java.util.Arrays;
import java.util.LinkedList;

public class LongestConsecutiveString {
    public static void main(String[] args) {
        String str = "Welcome";
        System.out.println("Chuỗi liên tiếp có độ dài dài nhất là: " + findString(str));
    }
    public static String findString(String str) {
        LinkedList<Character> max = new LinkedList<>();
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < str.length(); i++) {
            if (list.size() > 1 && str.charAt(i) <= list.getLast() && list.contains(str.charAt(i))) {
                list.clear();
            }
            list.add(str.charAt(i));
            if (list.size() > max.size()) {
                max.clear();
                max.addAll(list);
            }
        }
        StringBuilder result = new StringBuilder();
        for (Character ch : max) {
            result.append(ch.toString());
        }
        return result.toString();
    }
}
