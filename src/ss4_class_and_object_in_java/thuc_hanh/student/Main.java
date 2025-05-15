package ss4_class_and_object_in_java.thuc_hanh.student;

import util.InputValidation;
import util.Validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class Main {
    public static ArrayList<Student> students = new ArrayList<>();
    public static void main(String[] args) {
        students.add(new Student(1, "HaiTT", "1", LocalDate.now(), "QN", true));
        Student student = createStudent();
    }

    public static Student createStudent() {
        InputValidation inputValidation = new InputValidation();

        // Add validation rules for studentCode and get studentCode for user's input
        String studentCodeLabel = "Mã học viên";
        Map<Function<String, Boolean>, String> studentCodeRules = new HashMap<>();
        studentCodeRules.put(Validation::isNotBlank, studentCodeLabel + " không được để trống");
        studentCodeRules.put(Validation::isNumber, studentCodeLabel + " phải là số");
        studentCodeRules.put(str -> Validation.isUnique(str, students, Student::getCode), studentCodeLabel + " đã tồn tại");
        long studentCode = inputValidation.inputLong("Nhập mã học viên: ", studentCodeRules);
        return new Student();
    }
}
