package ss12_java_collection_framework.bai_tap.student_management.controller;

import ss12_java_collection_framework.bai_tap.student_management.entity.Student;
import ss12_java_collection_framework.bai_tap.student_management.service.IStudentService;
import ss12_java_collection_framework.bai_tap.student_management.service.StudentService;
import ss12_java_collection_framework.bai_tap.student_management.util.ConsoleColorUtil;
import ss12_java_collection_framework.bai_tap.student_management.view.CommonView;
import ss12_java_collection_framework.bai_tap.student_management.view.StudentView;

import java.util.List;

public class StudentController {
    private final static IStudentService studentService = new StudentService();

    public void displayMenu() {
        while (true) {
            System.out.println("""
                    Quản lý học viên:
                    1. Danh sách học viên
                    2. Thêm mới học viên
                    3. Cập nhật học viên
                    4. Xoá học viên
                    5. Tìm kiếm học viên
                    6. Sắp xếp học viên
                    7. Thoát""");
            switch (CommonView.inputFeatureSelection()) {
                case DISPLAY:
                    displayStudentList();
                    break;
                case ADD:
                    addNewStudent();
                    break;
                case UPDATE:
                    updateStudent();
                    break;
                case DELETE:
                    deleteStudent();
                    break;
                case SEARCH:
                    searchStudent();
                    break;
                case SORT:
                    sortStudentList();
                    break;
                case EXIT:
                    ConsoleColorUtil.printlnYellow("Kết thúc chương trình...");
                    return;
                default:
                    ConsoleColorUtil.printlnYellow("Không có chức năng tương ứng. Vui lòng chọn lại.");
            }
        }
    }

    public static void displayStudentList() {
        List<Student> studentList = studentService.findAll();
        if (!studentList.isEmpty()) {
            StudentView.display(studentList);
        } else {
            ConsoleColorUtil.printlnYellow("Danh sách học viên trống!");
        }
    }

    public static void addNewStudent() {
        Long studentId;
        Student student;
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
    }

    public static void updateStudent() {
        Long studentId;
        Student student;
        studentId = StudentView.inputStudentId("Nhập mã học viên cần cập nhật: ");
        if ((student = studentService.findById(studentId)) != null) {
            ConsoleColorUtil.printlnGreen("Tìm thấy học viên " + student.getName() + " (" + studentId + ")");
            student = StudentView.inputUpdateStudent(studentId, student);
            if (studentService.update(student)) {
                ConsoleColorUtil.printlnGreen("Cập nhật thành công học viên " + student.getName() + " (" + studentId + ")");
            } else {
                ConsoleColorUtil.printlnRed("Không tìm thấy học viên có mã là " + studentId);
            }
        } else {
            ConsoleColorUtil.printlnRed("Không tìm thấy học viên có mã là " + studentId);
        }
    }

    public static void deleteStudent() {
        Student student;
        Long studentId;
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
    }

    public static void searchStudent() {
        String studentNameKeyword = StudentView.inputStudentName();
        List<Student> foundStudentList = studentService.searchByName(studentNameKeyword, false, false);
        if (!foundStudentList.isEmpty()) {
            StudentView.display(foundStudentList, "\n=========== DANH SÁCH HỌC VIÊN TÊN CÓ CHỨA '" + studentNameKeyword + "' ===========");
        } else {
            ConsoleColorUtil.printlnYellow("Không tìm thấy học viên nào mà tên có chứa '" + studentNameKeyword + "'");
        }
    }

    public static void sortStudentList() {
        boolean isAscending = StudentView.selectSortType();
        List<Student> sortedStudentList = studentService.sortByName(isAscending);
        if (!sortedStudentList.isEmpty()) {
            StudentView.display(sortedStudentList, "\n=========== DANH SÁCH HỌC VIÊN TÊN SẮP XẾP " + (isAscending ? "TĂNG" : "GIẢM") + " DẦN THEO TÊN ===========");
        } else {
            ConsoleColorUtil.printlnYellow("Danh sách học viên trống!");
        }
    }
}
