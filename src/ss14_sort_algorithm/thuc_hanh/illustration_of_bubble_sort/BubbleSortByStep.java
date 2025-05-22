package ss14_sort_algorithm.thuc_hanh.illustration_of_bubble_sort;

import java.util.Scanner;

import static ss14_sort_algorithm.thuc_hanh.bubble_sort.BubbleSort.bubbleSortByStep;

public class BubbleSortByStep {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter list size: ");
        int size = Integer.parseInt(scanner.nextLine());
        int[] list = new int[size];
        System.out.println("Enter " + list.length + " values:");
        for (int i = 0; i < list.length; i++) {
            list[i] = scanner.nextInt();
        }
        System.out.print("Your input list: ");
        for (int item : list) {
            System.out.print(item + "\t");
        }
        System.out.println("\nBegin sort processing...");
        bubbleSortByStep(list);
    }
}
