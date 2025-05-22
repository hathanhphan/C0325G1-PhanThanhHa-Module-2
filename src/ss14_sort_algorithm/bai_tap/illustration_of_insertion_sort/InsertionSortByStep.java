package ss14_sort_algorithm.bai_tap.illustration_of_insertion_sort;

public class InsertionSortByStep {
    // Minh hoạ
    public static void insertionSortByStep(int[] list) {
        int position;
        int currentElement;
        boolean hasMoved;
        for (int i = 1; i < list.length; i++) {
            currentElement = list[i];
            position = i;
            hasMoved = false;
            System.out.printf("\nCurrent element is %d", currentElement);
            while (position > 0 && currentElement < list[position - 1]) {
                System.out.printf("\n%d is less than %d. Move %d to the right", currentElement, list[position - 1], list[position - 1]);
                list[position] = list[position - 1];
                position--;
                hasMoved = true;
            }
            list[position] = currentElement;
            if (hasMoved) {
                System.out.printf("\nInsert %d into position %d", currentElement, position);
            } else {
                System.out.printf("\nNo have any pre-elements is greater than %d", currentElement);
            }

            System.out.println("\nList after the " + i + "' sort: ");
            for (int item : list) {
                System.out.print(item + "\t");
            }
            System.out.println();
        }
    }
}
