package ss0_more_learn;

import java.util.*;
import java.util.stream.Collectors;

class Student {
    private String name;
    private int age;
    private String gender;
    private double gpa;

    public Student(String name, int age, String gender, double gpa) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.gpa = gpa;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public double getGpa() { return gpa; }

    @Override
    public String toString() {
        return "Product{name='" + name + "', age=" + age + ", gender='" + gender + "', gpa=" + gpa + "}";
    }
}

public class StudentExample {
    public static void main(String[] args) {
        Student[] students = {
                new Student("Alice", 22, "female", 3.8),
                new Student("Bob", 20, "male", 3.2),
                new Student("Charlie", 21, "male", 3.5),
                new Student("Diana", 23, "female", 3.9),
                new Student("Eve", 19, "female", 3.4),
                new Student("Frank", 22, "male", 3.7)
        };

        // 1. Tìm học sinh có GPA cao nhất
        Arrays.stream(students)
                .max(Comparator.comparing(Student::getGpa))
                .ifPresent(student -> System.out.println("Highest GPA: " + student));

        // 2. Tính GPA trung bình theo giới tính
        Map<String, Double> averageGpaByGender = Arrays.stream(students)
                .collect(Collectors.groupingBy(
                        Student::getGender,
                        Collectors.averagingDouble(Student::getGpa)
                ));
        System.out.println("Average GPA by gender: " + averageGpaByGender);

        // 3. Lọc học sinh có GPA > 3.5 và sắp xếp theo tuổi
        List<Student> highGpaStudents = Arrays.stream(students)
                .filter(student -> student.getGpa() > 3.5)
                .sorted(Comparator.comparing(Student::getAge))
                .collect(Collectors.toList());
        System.out.println("High GPA students sorted by age:");
        highGpaStudents.forEach(System.out::println);

        // 4. Đếm số học sinh theo tuổi
        Map<Integer, Long> studentCountByAge = Arrays.stream(students)
                .collect(Collectors.groupingBy(
                        Student::getAge,
                        Collectors.counting()
                ));
        System.out.println("Product count by age: " + studentCountByAge);

        // 5. Tìm học sinh trẻ nhất ở mỗi giới tính
        Map<String, Optional<Student>> youngestByGender = Arrays.stream(students)
                .collect(Collectors.groupingBy(
                        Student::getGender,
                        Collectors.minBy(Comparator.comparing(Student::getAge))
                ));
        System.out.println("Youngest student by gender:");
        youngestByGender.forEach((gender, student) ->
                student.ifPresent(s -> System.out.println(gender + ": " + s.getName() + ", " + s.getAge()))
        );

        // 6. Tạo Map với key là tên và value là GPA
        Map<String, Double> nameToGpa = Arrays.stream(students)
                .collect(Collectors.toMap(
                        Student::getName,
                        Student::getGpa
                ));
        System.out.println("Name to GPA mapping: " + nameToGpa);

        // 7. Kiểm tra xem tất cả học sinh có GPA > 3.0 không
        boolean allAbove3 = Arrays.stream(students)
                .allMatch(student -> student.getGpa() > 3.0);
        System.out.println("All students have GPA > 3.0: " + allAbove3);

        // 8. Tìm học sinh nữ có GPA cao nhất
        Arrays.stream(students)
                .filter(student -> student.getGender().equals("female"))
                .max(Comparator.comparing(Student::getGpa))
                .ifPresent(student -> System.out.println("Female student with highest GPA: " + student));
    }
}

