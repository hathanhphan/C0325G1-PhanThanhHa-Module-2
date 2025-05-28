package ss17_io_binary_file_serialization.bai_tap.product_management.service;

import ss17_io_binary_file_serialization.bai_tap.product_management.entity.Product;

import java.util.List;

public interface IProductService extends IService<Product> {
    List<Product> searchByName(String keyword, boolean caseSensitive, boolean exactMatch);
    List<Product> sortByPrice(boolean ascending);
}
