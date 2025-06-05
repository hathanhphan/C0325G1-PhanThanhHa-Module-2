package case_study_hospital_management.repository;

import java.util.List;

public interface IRepository<T> {
    List<T> findAll();
    int findIndexById(String id);
    T findById(String id);
    boolean save(T t);
    boolean update(T t);
    boolean delete(String id);
}
