package ss4_class_and_object_in_java.thuc_hanh.student;

import java.time.LocalDate;

public class Student {
    private String studentCode; // key
    private String name;
    private int age;
    private String email;

    // Constructor
    public Student(String studentCode, String name, int age, String email) {
        this.studentCode = studentCode;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Getters and setters
    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentCode='" + studentCode + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
