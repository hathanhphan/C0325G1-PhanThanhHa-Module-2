package ss12_java_collection_framework.bai_tap.student_management.repository;

import ss12_java_collection_framework.bai_tap.product_management.Product;
import ss12_java_collection_framework.bai_tap.student_management.entity.Student;

import java.util.List;

public interface IStudentRepository extends IRepository<Student> {
    List<Student> searchByName(String keyword, boolean caseSensitive, boolean exactMatch);
    List<Student> sortByName(boolean ascending);
}
