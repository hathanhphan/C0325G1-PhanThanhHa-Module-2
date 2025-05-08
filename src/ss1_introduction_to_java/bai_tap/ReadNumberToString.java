package ss1_introduction_to_java.bai_tap;

import java.util.Scanner;

public class ReadNumberToString {
    public static void main(String[] args) {
        String[] onesString = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] tensString = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String[] tenToNineteenString = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = sc.nextInt();
        int hundreds = -1;
        int tens = -1;
        int ones = -1;
        if (number <= 9) {
            ones = number;
        } else if (number <= 99) {
            ones = number % 10;
            tens = number / 10;
        } else if (number <= 999) {
            ones = number % 10;
            number = number / 10;
            tens = number % 10;
            hundreds = number / 10;
        } else {
            System.out.printf("%d is out of ability", number);
        }
        String result = "";
        if (ones != -1) {
            if (tens != -1) {
                if (tens * 10 + ones > 9 && tens * 10 + ones < 20) {
                    result = tenToNineteenString[tens * 10 + ones - 10];
                } else {
                    if (ones != 0) {
                        result = onesString[ones];
                    }
                    if (tens != 0) {
                        result = tensString[tens] + " " +  result;
                    }
                }
                if (hundreds != -1) {
                    if (!result.isEmpty()) {
                        result = onesString[hundreds] + " hundred and " + result;
                    } else {
                        result = onesString[hundreds] + " hundred";
                    }
                }
            } else {
                result = onesString[ones];
            }
        }
        if (!result.isEmpty()) {
            System.out.println("Result: " + result);
        }
    }
}
