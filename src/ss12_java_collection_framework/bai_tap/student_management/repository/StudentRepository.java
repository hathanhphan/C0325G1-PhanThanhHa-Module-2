package ss12_java_collection_framework.bai_tap.student_management.repository;

import ss12_java_collection_framework.bai_tap.product_management.Product;
import ss12_java_collection_framework.bai_tap.student_management.entity.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StudentRepository implements IStudentRepository {
    private static final List<Student> studentList = new ArrayList<>();
    static {
        studentList.add(new Student(1L, "Nguyễn Văn Minh", "Phường Hải Châu, Quận Hải Châu, Đà Nẵng", 7.5f, "C0325G1"));
        studentList.add(new Student(2L, "Trần Thị Lan", "Phường An Hải, Quận Sơn Trà, Đà Nẵng", 8.3f, "C0225G1"));
        studentList.add(new Student(3L, "Phạm Văn Hùng", "Phường Cẩm Châu, Thành phố Hội An, Quảng Nam", 5.9f, "C0125G1"));
        studentList.add(new Student(4L, "Lê Thị Hằng", "Phường Hòa Thuận, Quận Hải Châu, Đà Nẵng", 9.1f, "C0325G1"));
        studentList.add(new Student(5L, "Nguyễn Văn Minh", "Phường Phước Hòa, Thành phố Tam Kỳ, Quảng Nam", 6.8f, "C0225G1"));
        studentList.add(new Student(6L, "Hoàng Thị Mai", "Phường Thủy Xuân, Thành phố Huế, Huế", 7.0f, "C0125G1"));
        studentList.add(new Student(7L, "Võ Quốc Toàn", "Phường An Cựu, Thành phố Huế, Huế", 8.7f, "C0325G1"));
        studentList.add(new Student(8L, "Trần Thị Lan", "Phường Thanh Hà, Thành phố Hội An, Quảng Nam", 6.2f, "C0325G1"));
        studentList.add(new Student(9L, "Nguyễn Thị Hồng", "Phường Nam Dương, Quận Hải Châu, Đà Nẵng", 5.5f, "C0225G1"));
        studentList.add(new Student(10L, "Phan Văn Tùng", "Phường An Mỹ, Thành phố Tam Kỳ, Quảng Nam", 9.4f, "C0125G1"));
    }

    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public Student findById(Long id) {
        for (Student s : studentList) {
            if (Objects.equals(s.getId(), id)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public boolean add(Student student) {
        for (Student s : studentList) {
            if (Objects.equals(s.getId(), student.getId())) {
                return false;
            }
        }
        return studentList.add(student);
    }

    @Override
    public boolean update(Student student) {
        for (int i = 0; i < studentList.size(); i++) {
            if (Objects.equals(studentList.get(i).getId(), student.getId())) {
                studentList.set(i, student);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (Objects.equals(studentList.get(i).getId(), id)) {
                studentList.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Student> searchByName(String keyword, boolean caseSensitive, boolean exactMatch) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String searchKeyword = keyword.trim();
        if (!caseSensitive) {
            searchKeyword = searchKeyword.toLowerCase();
        }
        final String finalKeyword = searchKeyword;
        return studentList.stream()
                .filter(student -> {
                    String studentName = student.getName();
                    if (!caseSensitive) {
                        studentName = studentName.toLowerCase();
                    }
                    if (exactMatch) {
                        return studentName.equals(finalKeyword);
                    } else {
                        return studentName.contains(finalKeyword);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> sortByName(boolean ascending) {
        List<Student> sortedList = new ArrayList<>(studentList);
        if (ascending) {
            sortedList.sort(Comparator.comparing(Student::getName).thenComparing(Student::getId));
        } else {
            sortedList.sort(Comparator.comparing(Student::getName).thenComparing(Student::getId).reversed());
        }
        return sortedList;
    }
}
