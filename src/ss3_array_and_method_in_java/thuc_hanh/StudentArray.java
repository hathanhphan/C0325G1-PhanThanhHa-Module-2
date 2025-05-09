package ss3_array_and_method_in_java.thuc_hanh;

import java.util.Scanner;

public class StudentArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] students = {"Bảo", "Quân", "Nam", "Tịnh"};
        System.out.print("Nhập tên sinh viên: ");
        String studentName = sc.nextLine();
        boolean isExist = false;
        for (int i = 0; i < students.length; i++) {
            if (students[i].equals(studentName)) {
                System.out.printf("Tìm thấy học viên có tên %s tại vị trí thứ %d", studentName, i);
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            System.out.printf("Không tìm thấy học viên nào có tên %s", studentName);
        }
    }
}
