package ss1_introduction_to_java.bai_tap;

import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Your First Name: ");
        String firstName = sc.nextLine();
        System.out.println("Enter Your Last Name: ");
        String lastName = sc.nextLine();
        System.out.println("Enter Your Age: ");
        int age = sc.nextInt();
        System.out.println(firstName + " " + lastName + " " + age);

    }
}
