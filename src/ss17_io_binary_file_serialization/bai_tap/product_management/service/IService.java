package ss17_io_binary_file_serialization.bai_tap.product_management.service;

import ss17_io_binary_file_serialization.bai_tap.product_management.entity.Product;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
    Product findById(Long id);
    boolean add(T t);
    boolean update(T t);
    boolean delete(Long id);
}
