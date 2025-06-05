package case_study_hospital_management.service;

import java.util.List;

public interface IService<T> {
    List<T> getAll();
    T findById(String id);
    boolean update(T t);
    boolean delete(String id);
    String generateId();
}
