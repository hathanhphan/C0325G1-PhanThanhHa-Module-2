package ss14_sort_algorithm.bai_tap.insertion_sort;

public class InsertionSort {
    public static void insertionSort(int[] list) {
        int position;
        int currentElement;
        for (int i = 1; i < list.length; i++) {
            currentElement = list[i];
            position = i;
            while (position > 0 && currentElement < list[position - 1]) {
                list[position] = list[position - 1];
                position--;
            }
            list[position] = currentElement;
        }
    }
}
