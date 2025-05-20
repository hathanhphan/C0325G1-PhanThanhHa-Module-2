package mvc_template.controller;

import mvc_template.entity.Student;
import mvc_template.service.IStudentService;
import mvc_template.service.StudentService;
import mvc_template.view.StudentView;

import java.util.List;
import java.util.Scanner;

public class StudentController {
    private IStudentService studentService = new StudentService();
    private  Scanner scanner = new Scanner(System.in);
    private final int DISPLAY =1;
    private final int ADD =2;
    public  void displayMenu(){
        boolean flag = true;
        while (flag){
            System.out.println("Quản lý sinh viên:\n" +
                    "1. Danh sách\n" +
                    "2. Thêm mới\n" +
                    "3. Xoá\n" +
                    "4. Tìm kiếm\n" +
                    "5. Thoát\n");
            System.out.println("Chọn chức năng: \n");
            int choose = Integer.parseInt(scanner.nextLine());
            switch (choose){
                case DISPLAY:
                    // danh sách (gọi dữ liệu từ service => hiển thị
                    List<Student> studentList = studentService.findAll();
                    StudentView.display(studentList);
                    break;
                case ADD:
                    // thêmm ới
                    // hiển thị form thêm dữ liệu => gọi service để them mơ
                    Student student = StudentView.inputData();
                    studentService.add(student);
                    System.out.println("Thêm mới thành công");
                    break;
                case 3:
                    // xoá
                    break;
                case 4:
                    // tìm kiếm
                    break;
                default:
                    flag = false;
            }
        }
    }
}
