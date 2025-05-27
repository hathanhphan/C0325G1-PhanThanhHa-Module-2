package ss12_java_collection_framework.bai_tap.student_management.service;

import ss12_java_collection_framework.bai_tap.student_management.entity.Student;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
    Student findById(Long id);
    boolean add(T t);
    boolean update(T t);
    boolean delete(Long id);
}
