package ss10_dsa.bai_tap.my_list;

import ss6_inherit.thuc_hanh.shape.Shape;
import ss6_inherit.thuc_hanh.shape.Square;

import java.util.ArrayList;

public class MyListTest {
    public static void main(String[] args) {
        MyList<Integer> myList = new MyList<>();
        myList.add(100);
        myList.add(50);
        myList.add(200);
        myList.add(300);
        myList.add(250);


        System.out.println("Danh sách vừa nhập: ");
        for (int i = 0; i < myList.size(); i++) {
            System.out.println("Phần tử thứ " + i + ": " + myList.get(i));
        }

        int index = 2;
        System.out.println("\nXoá phần tử thứ " + index);
        Integer removedElement = myList.remove(index);
        System.out.println("Phần tử bị xoá có giá trị là " + removedElement);
        System.out.println("Danh sách sau xoá phần tử thứ " + index + ": ");
        for (int i = 0; i < myList.size(); i++) {
            System.out.println("Phần tử thứ " + i + ": " + myList.get(i));
        }

        index = 1;
        System.out.println("\nThêm phần tử 500 và vị trí thứ " + index);
        myList.add(index, 500);
        System.out.println("Danh sách sau thêm phần tử 500 và vị trí thứ " + index + ": ");
        for (int i = 0; i < myList.size(); i++) {
            System.out.println("Phần tử thứ " + i + ": " + myList.get(i));
        }

        int numberNeedFind = 300;
        System.out.println("\nTìm vị trí phần tử " + numberNeedFind + " trong danh sách");
        int foundIndex = myList.indexOf(300);
        if (foundIndex != -1) {
            System.out.println("Đã tìm thấy phần tử " + numberNeedFind + " tại vị trí " + foundIndex);
        } else {
            System.out.println("Không tìm thấy phần tử " + numberNeedFind + "trong danh sách");
        }

        int otherNumberNeedFind = 450;
        System.out.println("\nKiểm tra phần tử " + otherNumberNeedFind + " trong danh sách hay không");
        if (myList.contains(450)) {
            System.out.println("Danh sách có chưa phần tử " + otherNumberNeedFind);
        } else {
            System.out.println("Không tìm thấy phần tử " + otherNumberNeedFind + " trong danh sách");
        }

        myList.clear();
        System.out.println("\nĐộ dài danh sách sau khi clear là " + myList.size());
    }
}
