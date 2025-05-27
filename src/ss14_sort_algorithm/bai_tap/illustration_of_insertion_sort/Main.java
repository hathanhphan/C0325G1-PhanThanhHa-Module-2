package ss14_sort_algorithm.bai_tap.illustration_of_insertion_sort;

import ss3_array_and_method_in_java.helpers.ArrayIO;

import static ss14_sort_algorithm.bai_tap.illustration_of_insertion_sort.InsertionSortByStep.insertionSortByStep;

public class Main {
    public static void main(String[] args) {
        int[] arr = ArrayIO.inputInt(3, 50);
        System.out.println("Mảng ban đầu: ");
        for (int j : arr) System.out.print(j + "\t");
        System.out.println();
        insertionSortByStep(arr);
        System.out.println("\nMảng đã sắp xếp: ");
        for (int j : arr) System.out.print(j + "\t");
    }
}
