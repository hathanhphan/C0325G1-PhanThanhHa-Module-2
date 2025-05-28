package ss17_io_binary_file_serialization.bai_tap.product_management.service;

import ss17_io_binary_file_serialization.bai_tap.product_management.entity.Product;
import ss17_io_binary_file_serialization.bai_tap.product_management.repository.IProductRepository;
import ss17_io_binary_file_serialization.bai_tap.product_management.repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService {
    private final IProductRepository studentRepository = new ProductRepository();
    @Override
    public List<Product> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public boolean add(Product product) {
        return studentRepository.add(product);
    }

    @Override
    public boolean update(Product product) {
        return studentRepository.update(product);
    }

    @Override
    public boolean delete(Long id) {
        return studentRepository.delete(id);
    }

    @Override
    public List<Product> searchByName(String keyword, boolean caseSensitive, boolean exactMatch) {
        return studentRepository.searchByName(keyword, caseSensitive, exactMatch);
    }

    @Override
    public List<Product> sortByPrice(boolean ascending) {
        return studentRepository.sortByPrice(ascending);
    }
}
