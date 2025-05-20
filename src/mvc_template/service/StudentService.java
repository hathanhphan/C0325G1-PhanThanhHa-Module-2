package mvc_template.service;

import mvc_template.entity.Student;
import mvc_template.repository.IStudentRepository;
import mvc_template.repository.StudentRepository;

import java.util.List;

public class StudentService implements IStudentService {
    private IStudentRepository studentRepository = new StudentRepository();
    @Override
    public List<Student> findAll() {

        return studentRepository.findAll();
    }
    @Override
    public void add(Student student) {

        studentRepository.add(student);
    }
}
