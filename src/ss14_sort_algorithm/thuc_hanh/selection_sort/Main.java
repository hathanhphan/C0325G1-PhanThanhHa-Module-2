package ss14_sort_algorithm.thuc_hanh.selection_sort;

import static ss14_sort_algorithm.thuc_hanh.selection_sort.SelectionSort.selectionSort;

public class Main {
    public static void main(String[] args) {
        double[] list = {1, 9, 4.5, 6.6, 5.7, -4.5};
        selectionSort(list);
        for (double v : list) System.out.print(v + " ");
    }
}
