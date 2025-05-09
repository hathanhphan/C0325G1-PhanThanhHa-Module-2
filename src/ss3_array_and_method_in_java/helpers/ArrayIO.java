package ss3_array_and_method_in_java.helpers;

import java.util.Scanner;

public class ArrayIO {
    public static int[] inputInt(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int[] array;
        int size;
        do {
            System.out.print("Nhập số lượng phần tử của mảng: ");
            size = sc.nextInt();
            if (size < 1) {
                System.out.println("Số lượng không được nhỏ hơn 3");
            } else if (size > 50) {
                System.out.println("Số lượng không được vượt quá 50");
            }
        } while (size < 1 || size > 50);
        array = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.print("Nhập phần tử thứ " + (i + 1) + ": ");
            array[i] = sc.nextInt();
        }
        return array;
    }

    public static void outputInt(int[] array, String titleMessage) {
        if (!titleMessage.isEmpty()) {
            System.out.printf("%-20s%s", titleMessage, "");
        }
        for (int i : array) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    public static int maxInt(int[] array) {
        int max = array[0];
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                index = i;
            }
        }
        return index;
    }

    public static int minInt(int[] array) {
        int min = array[0];
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                index = i;
            }
        }
        return index;
    }
}
