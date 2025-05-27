package ss12_java_collection_framework.bai_tap.student_management.service;

import ss12_java_collection_framework.bai_tap.student_management.entity.Student;

import java.util.List;

public interface IStudentService extends IService<Student> {
    List<Student> searchByName(String keyword, boolean caseSensitive, boolean exactMatch);
    List<Student> sortByName(boolean ascending);
}
