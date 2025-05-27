package ss14_sort_algorithm.bai_tap.insertion_sort;

import static ss14_sort_algorithm.bai_tap.insertion_sort.InsertionSort.insertionSort;

public class Main {
    public static void main(String[] args) {
        int[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        System.out.println("Initial list: ");
        for (int j : list) System.out.print(j + "\t");
        insertionSort(list);
        System.out.println("\nSorted list: ");
        for (int j : list) System.out.print(j + "\t");
    }
}
