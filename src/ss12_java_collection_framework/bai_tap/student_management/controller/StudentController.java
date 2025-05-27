package ss12_java_collection_framework.bai_tap.student_management.controller;

import ss12_java_collection_framework.bai_tap.student_management.common.ViewSelector;
import ss12_java_collection_framework.bai_tap.student_management.entity.Student;
import ss12_java_collection_framework.bai_tap.student_management.service.IStudentService;
import ss12_java_collection_framework.bai_tap.student_management.service.StudentService;
import ss12_java_collection_framework.bai_tap.student_management.util.ConsoleColorUtil;
import ss12_java_collection_framework.bai_tap.student_management.view.StudentView;

import java.util.List;
import java.util.Scanner;

public class StudentController {
    private final IStudentService studentService = new StudentService();
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        while (true){
            System.out.println("""
                    Quản lý học viên:
                    1. Danh sách học viên
                    2. Thêm mới học viên
                    3. Cập nhật học viên
                    4. Xoá học viên
                    5. Tìm kiếm học viên
                    6. Sắp xếp học viên
                    7. Thoát""");
            System.out.print("Chọn chức năng: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnYellow("Bạn nhập chức năng không đúng định dạng. Kết thúc chương trình...");
                return;
            }
            ViewSelector selector;
            selector = ViewSelector.fromValue(choice);
            Student student;
            Long studentId;
            switch (selector){
                case DISPLAY:
                    List<Student> studentList = studentService.findAll();
                    if (!studentList.isEmpty()) {
                        StudentView.display(studentList);
                    } else {
                        ConsoleColorUtil.printlnYellow("Danh sách học viên trống!");
                    }
                    break;
                case ADD:
                    student = StudentView.inputNewStudent();
                    if (studentService.add(student)) {
                        ConsoleColorUtil.printlnGreen("Thêm mới thành công học viên " + student.getName() + " (" + student.getId() + ")");
                    } else {
                        boolean isReInputStudentId = true;
                        while (isReInputStudentId) {
                            ConsoleColorUtil.printlnRed("Thêm mới không thành công. Mã học viên đã tồn tại!");
                            isReInputStudentId = StudentView.selectReInputStudentId();
                            if (isReInputStudentId) {
                                studentId = StudentView.inputStudentId("Nhập lại mã học viên: ");
                                student.setId(studentId);
                                if (studentService.add(student)) {
                                    ConsoleColorUtil.printlnGreen("Thêm mới thành công học viên " + student.getName() + " (" + student.getId() + ")");
                                    isReInputStudentId = false;
                                }
                            }
                        }

                    }
                    break;
                case UPDATE:
                    studentId = StudentView.inputStudentId("Nhập mã học viên cần cập nhật: ");
                    if ((student = studentService.findById(studentId)) != null) {
                        student = StudentView.inputUpdateStudent(studentId, student);
                        if (studentService.update(student)) {
                            ConsoleColorUtil.printlnGreen("Cập nhật thành công học viên " + student.getName() + " (" + studentId + ")");
                        } else {
                            ConsoleColorUtil.printlnRed("Không tìm thấy học viên có mã là " + studentId);
                        }
                    } else {
                        ConsoleColorUtil.printlnRed("Không tìm thấy học viên có mã là " + studentId);
                    }
                    break;
                case DELETE:
                    studentId = StudentView.inputStudentId("Nhập mã học viên cần xoá: ");
                    if ((student = studentService.findById(studentId)) != null) {
                        if (studentService.delete(studentId)) {
                            ConsoleColorUtil.printlnGreen("Xoá thành công học viên " + student.getName() + " (" + studentId + ")");
                        } else {
                            ConsoleColorUtil.printlnRed("Không tìm thấy học viên có mã là " + studentId);
                        }
                    } else {
                        ConsoleColorUtil.printlnRed("Không tìm thấy học viên có mã là " + studentId);
                    }
                    break;
                case SEARCH:
                    String studentNameKeyword = StudentView.inputStudentName();
                    List<Student> foundStudentList = studentService.searchByName(studentNameKeyword, false, false);
                    if (!foundStudentList.isEmpty()) {
                        StudentView.display(foundStudentList, "\n=========== DANH SÁCH HỌC VIÊN TÊN CÓ CHỨA '" + studentNameKeyword + "' ===========");
                    } else {
                        ConsoleColorUtil.printlnYellow("Không tìm thấy học viên nào mà tên có chứa '" + studentNameKeyword + "'");
                    }
                    break;
                case SORT:
                    boolean isAscending = StudentView.selectSortType();
                    List<Student> sortedStudentList = studentService.sortByName(isAscending);
                    if (!sortedStudentList.isEmpty()) {
                        StudentView.display(sortedStudentList, "\n=========== DANH SÁCH HỌC VIÊN TÊN SẮP XẾP " + (isAscending ? "TĂNG" : "GIẢM") + " DẦN THEO TÊN ===========");
                    } else {
                        ConsoleColorUtil.printlnYellow("Danh sách học viên trống!");
                    }
                    break;
                default:
                    ConsoleColorUtil.printlnYellow("Kết thúc chương trình...");
                    return;
            }
        }
    }
}
