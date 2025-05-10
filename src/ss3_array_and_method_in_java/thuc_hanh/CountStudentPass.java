package ss3_array_and_method_in_java.thuc_hanh;

import ss3_array_and_method_in_java.helpers.ArrayIO;

public class CountStudentPass {
    public static void main(String[] args) {
        int[] students = ArrayIO.inputInt(3, 50, "Nhập số lượng sinh viên: ", "Nhập điểm của sinh viên thứ ");
        ArrayIO.outputInt(students, "Danh sách điểm đã nhập: ");
        System.out.printf("Số lượng sinh viên qua môn là %d", countStudentPass(students));
    }

    public static int countStudentPass(int[] students) {
        int count = 0;
        for (int student : students) {
            if (student >= 5 && student <= 10)
                count++;
        }
        return count;

    }
}
