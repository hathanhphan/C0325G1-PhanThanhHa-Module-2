package ss12_java_collection_framework.bai_tap.student_management.view;

import ss12_java_collection_framework.bai_tap.student_management.entity.Student;
import ss12_java_collection_framework.bai_tap.student_management.util.ConsoleColorUtil;
import ss12_java_collection_framework.bai_tap.student_management.util.InputValidator;

import java.util.List;
import java.util.Scanner;

public class StudentView {
    private static final Scanner scanner = new Scanner(System.in);

    public static void display(List<Student> studentList, String title) {
        System.out.println(title);
        displayAtTable(studentList);
        System.out.println();
    }

    public static void display(List<Student> studentList) {
        display(studentList, "\n=========== DANH SÁCH HỌC VIÊN ===========");
    }

    public static void displayAtTable(List<Student> studentList) {
        String idLabel = "Mã HV";
        String nameLabel = "Họ tên";
        String addressLabel = "Địa chỉ";
        String pointLabel = "Điểm";
        String classNameLabel = "Lớp";
        int maxIdLength = idLabel.length();
        int maxNameLength = nameLabel.length();
        int maxAddressLength = addressLabel.length();
        int maxPointLength = pointLabel.length();
        int maxClassNameLength = classNameLabel.length();
        for (Student student : studentList) {
            maxIdLength = Math.max(maxIdLength, String.valueOf(student.getId()).length());
            maxNameLength = Math.max(maxNameLength, student.getName().length());
            maxAddressLength = Math.max(maxAddressLength, student.getAddress().length());
            maxPointLength = Math.max(maxPointLength, String.format("%.2f", student.getPoint()).length());
            maxClassNameLength = Math.max(maxClassNameLength, student.getClassName().length());
        }

        // Tạo định dạng cho dòng
        String lineFormat = "+-" + "-".repeat(maxIdLength) + "-+-" +
                "-".repeat(maxNameLength) + "-+-" +
                "-".repeat(maxAddressLength) + "-+-" +
                "-".repeat(maxPointLength) + "-+-" +
                "-".repeat(maxClassNameLength) + "-+%n";

        // Tạo định dạng cho dữ liệu
        String dataFormat = "| %-" + maxIdLength + "s | %-" + maxNameLength + "s | %-" +
                maxAddressLength + "s | %-" + maxPointLength + "s | %-" + maxClassNameLength + "s |%n";
        // In bảng
        System.out.printf(lineFormat);
        System.out.printf(dataFormat, idLabel, nameLabel, addressLabel, pointLabel, classNameLabel);
        System.out.printf(lineFormat);
        for (Student student : studentList) {
            System.out.printf(dataFormat,
                    student.getId(),
                    student.getName(),
                    student.getAddress(),
                    String.format("%.2f", student.getPoint()),
                    student.getClassName());
        }
        System.out.printf(lineFormat);
    }

    public static Student inputNewStudent() {
        Long id = InputValidator.inputPositiveLong("Nhập mã học viên: ", "mã học viên", 5, false);
        String name = InputValidator.inputString("Nhập tên học viên: ", "tên học viên", 5, 50, false);
        String address = InputValidator.inputString("Nhập địa chỉ: ", "địa chỉ", 5, 255, false);
        Float point = InputValidator.inputFloat("Nhập điểm: ", "điểm", 0, 10, false);
        String className = InputValidator.inputString("Nhập tên lớp: ", "tên lớp", 7, 7, false);
        return new Student(id, name, address, point, className);
    }

    public static Long inputStudentId(String message) {
        return InputValidator.inputPositiveLong(message, "mã học viên", 5, false);
    }

    public static Student inputUpdateStudent(Long studentId, Student student) {
        ConsoleColorUtil.printlnYellow("Để trống nếu không muốn cập nhật thông tin nào đó...");
        String name = InputValidator.inputString("Nhập tên học viên cần cập nhật: ", "tên học viên", 5, 50, true);
        String address = InputValidator.inputString("Nhập địa chỉ cần cập nhật: ", "địa chỉ", 5, 255, true);
        Float point = InputValidator.inputFloat("Nhập điểm cần cập nhật: ", "điểm", 0, 10, true);
        String className = InputValidator.inputString("Nhập tên lớp cần cập nhật: ", "tên lớp", 7, 7, true);

        if (name == null) name = student.getName();
        if (address == null) address = student.getAddress();
        if (point == null) point = student.getPoint();
        if (className == null) className = student.getClassName();
        return new Student(studentId, name, address, point, className);
    }

    public static String inputStudentName() {
        System.out.print("Nhập tên học viên cần tìm kiếm: ");
        return scanner.nextLine();
    }

    public static boolean selectSortType() {
        int choice;
        while (true) {
            try {
                System.out.println("""
                        Chọn kiểu sắp xếp:
                        \t1. Tăng dần theo tên
                        \t2. Giảm dần theo tên""");
                System.out.print("Chọn chức năng: ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) return true;
                else if (choice == 2) return false;
                else ConsoleColorUtil.printlnRed("Bạn chọn chức năng không hợp lệ. Vui lòng chọn lại.");
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed("Bạn chọn chức năng không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    public static boolean selectReInputStudentId() {
        int choice;
        while (true) {
            try {
                System.out.println("""
                        Chọn hướng giải quyết:
                        \t1. Nhập lại mã học viên
                        \t2. Kết thúc chức năng thêm mới""");
                System.out.print("Chọn chức năng: ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) return true;
                else if (choice == 2) return false;
                else ConsoleColorUtil.printlnRed("Bạn chọn chức năng không hợp lệ. Vui lòng chọn lại.");
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed("Bạn chọn chức năng không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
