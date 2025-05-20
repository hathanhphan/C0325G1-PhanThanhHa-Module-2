package mvc_template.repository;

import mvc_template.entity.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findAll();
    void add(Student student);
}
