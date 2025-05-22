package ss14_sort_algorithm.thuc_hanh.bubble_sort;

import static ss14_sort_algorithm.thuc_hanh.bubble_sort.BubbleSort.bubbleSort;

public class Main {
    public static void main(String[] args) {
        int[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        bubbleSort(list);
        for (int j : list) System.out.print(j + " ");
    }
}
