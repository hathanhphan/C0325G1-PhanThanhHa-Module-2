package ss15_exception_and_debug.bai_tap.using_IllegalTriangleException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            try {
                System.out.print("Enter A side: ");
                double aSide = Double.parseDouble(sc.nextLine());
                System.out.print("Enter B side: ");
                double bSide = Double.parseDouble(sc.nextLine());
                System.out.print("Enter C side: ");
                double cSide = Double.parseDouble(sc.nextLine());
                if (isTriangle(aSide, bSide, cSide)) {
                    System.out.printf("Three sides of triangle are: %.0f %.0f %.0f", aSide, bSide, cSide);
                    flag = false;
                }
            } catch (NumberFormatException | IllegalTriangleException e) {
                final String RED = "\u001B[31m";
                final String RESET = "\u001B[0m";
                System.out.println(RED + "❌ Error: " + e.getMessage() + ". Please try again:" + RESET);
            }
        }
    }
    public static boolean isTriangle(double aSide, double bSide, double cSide) throws IllegalTriangleException {
        if (aSide < 0 || bSide < 0 || cSide < 0) {
            throw new IllegalTriangleException("Must be positive number!");
        } else if ((aSide + bSide <= cSide) || (aSide + cSide <= bSide) || (bSide + cSide <= aSide)) {
            throw new IllegalTriangleException("Is not a triangle!");
        }
        return true;
    }
}
