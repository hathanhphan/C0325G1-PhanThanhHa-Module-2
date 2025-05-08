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
        if (number < 0 || number > 999) {
            System.out.printf("%d is out of ability", number);
            return;
        }
        int ones = number % 10;
        number = number / 10;
        int tens = number % 10;
        number = number / 10;
        int hundreds = number % 10;
        System.out.print("Result is: ");
        if (hundreds != 0) {
            System.out.print(onesString[hundreds] + " hundred");
            if (ones != 0 || tens != 0) {
                System.out.print(" and ");
            }
        }
        int numberInDozen = tens * 10 + ones;
        if (numberInDozen > 9 && numberInDozen < 20) {
            System.out.print(tenToNineteenString[numberInDozen - 10]);
        } else {
            if (tens != 0) {
                System.out.print(tensString[tens] + " ");
            }
            if (ones != 0) {
                System.out.print(onesString[ones]);
            } else if (number == 0) {
                System.out.print(onesString[number]);
            }
        }
    }
}
