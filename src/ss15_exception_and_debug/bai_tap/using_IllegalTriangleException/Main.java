package ss15_exception_and_debug.bai_tap.using_IllegalTriangleException;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            try {
                double aSide = inputDouble("Enter A side: ");
                double bSide = inputDouble("Enter B side: ");
                double cSide = inputDouble("Enter C side: ");
                if (isTriangle(aSide, bSide, cSide)) {
                    System.out.printf("Three sides of triangle are: %.0f %.0f %.0f", aSide, bSide, cSide);
                    flag = false;
                }
            } catch (IllegalTriangleException e) {
                printErrorMessage(e.getMessage());
            }
        }
    }
    public static boolean isTriangle(double aSide, double bSide, double cSide) throws IllegalTriangleException {
        if ((aSide + bSide <= cSide) || (aSide + cSide <= bSide) || (bSide + cSide <= aSide)) {
            throw new IllegalTriangleException("Is not a triangle!");
        }
        return true;
    }
    public static double inputDouble(String message) {
        double number;
        while (true) {
            try {
                System.out.print(message);
                number = Double.parseDouble(sc.nextLine());
                if (number <= 0) {
                    throw new IllegalTriangleException("Must be positive number");
                }
                return number;
            } catch (NumberFormatException | IllegalTriangleException e) {
                printErrorMessage(e.getMessage());
            }
        }
    }
    public static void printErrorMessage(String message) {
        final String RED = "\u001B[31m";
        final String RESET = "\u001B[0m";
        System.out.println(RED + "❌ Error: " + message + ". Please try again:" + RESET);
    }
}
