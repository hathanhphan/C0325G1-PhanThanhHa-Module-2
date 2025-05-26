package ss0_more_learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }

    public static int nthUglyNumber(int n) {
        int count = 0;
        int index = 1;
        while (count != n) {
            if (hasDivisorLessThan5(index)) {
                count++;
            }
            index++;
        }
        return --index;
    }
    public static boolean hasDivisorLessThan5(int number) {
        if (number == 1) {
            return true;
        }
        for (int i = number > 5 ? number - 1 : number; i >= 2; i--) {
            if (number % i == 0) {
                return i <= 5;
            }
        }
        return false;
    }
}
