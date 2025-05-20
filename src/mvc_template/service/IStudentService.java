package mvc_template.service;

import mvc_template.entity.Student;

import java.util.List;

public interface IStudentService  {
    List<Student> findAll();
    void add(Student student);

}
