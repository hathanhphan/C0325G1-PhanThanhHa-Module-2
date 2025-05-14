package ss4_class_and_object_in_java.thuc_hanh.student;

import util.InputValidation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create a list of students for uniqueness validation
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("SV0001", "John Doe", 20, "john@example.com"));
        students.add(new Student("SV0002", "Jane Smith", 21, "jane@example.com"));

        // Create the validation instance
        InputValidation validator = new InputValidation();

        // Register collections for uniqueness validation
        validator.registerCollection("students", students, Student::getStudentCode);

        // Define validation rules
        Map<String, String> rules = new HashMap<>();
        rules.put("studentCode", "required|regex:^SV\\d{4}$|unique:students");
        rules.put("name", "required|min:3|max:50");
        rules.put("age", "required|integer|between:18,60");
        rules.put("email", "required|email");

        // Validate interactively
        Map<String, String> validatedData = validator.validateInteractive(rules);

        // Use the validated data
        System.out.println("\nValidated data:");
        System.out.println("Student Code: " + validatedData.get("studentCode"));
        System.out.println("Name: " + validatedData.get("name"));
        System.out.println("Age: " + validatedData.get("age"));
        System.out.println("Email: " + validatedData.get("email"));

        // Create a new student from validated data
        Student newStudent = new Student(
                validatedData.get("studentCode"),
                validatedData.get("name"),
                Integer.parseInt(validatedData.get("age")),
                validatedData.get("email")
        );

        System.out.println("\nCreated new student: " + newStudent);
    }
}
