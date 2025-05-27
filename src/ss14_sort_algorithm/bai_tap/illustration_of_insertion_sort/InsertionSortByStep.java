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
            System.out.printf("\nPhần tử hiện tại là %d", currentElement);
            while (position > 0 && currentElement < list[position - 1]) {
                System.out.printf("\n%d nhỏ hơn %d. Di chuyển %d sang phải", currentElement, list[position - 1], list[position - 1]);
                list[position] = list[position - 1];
                position--;
                hasMoved = true;
            }
            list[position] = currentElement;
            if (hasMoved) {
                System.out.printf("\nChèn %d vào vị trí %d", currentElement, position);
            } else {
                System.out.printf("\nKhông có phần tử nào ở phía trước lớn hơn %d", currentElement);
            }

            System.out.println("\nMảng sau khi thực hiện sắp xếp lần " + i + ": ");
            for (int item : list) {
                System.out.print(item + "\t");
            }
            System.out.println();
        }
    }
}
