package ss12_java_collection_framework.bai_tap.student_management.service;

import ss12_java_collection_framework.bai_tap.student_management.entity.Student;
import ss12_java_collection_framework.bai_tap.student_management.repository.IStudentRepository;
import ss12_java_collection_framework.bai_tap.student_management.repository.StudentRepository;

import java.util.List;

public class StudentService implements IStudentService {
    private final IStudentRepository studentRepository = new StudentRepository();
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public boolean add(Student student) {
        return studentRepository.add(student);
    }

    @Override
    public boolean update(Student student) {
        return studentRepository.update(student);
    }

    @Override
    public boolean delete(Long id) {
        return studentRepository.delete(id);
    }

    @Override
    public List<Student> searchByName(String keyword, boolean caseSensitive, boolean exactMatch) {
        return studentRepository.searchByName(keyword, caseSensitive, exactMatch);
    }

    @Override
    public List<Student> sortByName(boolean ascending) {
        return studentRepository.sortByName(ascending);
    }
}
