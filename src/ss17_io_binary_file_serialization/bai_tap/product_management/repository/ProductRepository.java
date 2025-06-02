package ss17_io_binary_file_serialization.bai_tap.product_management.repository;

import ss17_io_binary_file_serialization.bai_tap.product_management.entity.Product;
import ss17_io_binary_file_serialization.bai_tap.product_management.util.IOBinaryFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProductRepository implements IProductRepository {
    private static final String path = "D:\\CodeGym\\module2\\src\\ss17_io_binary_file_serialization\\bai_tap\\product_management\\data\\product.dat";
    private static List<Product> products;

    @Override
    public List<Product> findAll() {
        return IOBinaryFile.readFromFile(path);
    }

    @Override
    public Product findById(Long id) {
        products = findAll();
        for (Product p : products) {
            if (Objects.equals(p.getId(), id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean add(Product product) {
        products = findAll();
        for (Product p : products) {
            if (Objects.equals(p.getId(), product.getId())) {
                return false;
            }
        }
        products.add(product);
        IOBinaryFile.writeToFile(path, products);
        return true;
    }

    @Override
    public boolean update(Product product) {
        products = findAll();
        for (int i = 0; i < products.size(); i++) {
            if (Objects.equals(products.get(i).getId(), product.getId())) {
                products.set(i, product);
                IOBinaryFile.writeToFile(path, products);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        products = findAll();
        for (int i = 0; i < products.size(); i++) {
            if (Objects.equals(products.get(i).getId(), id)) {
                products.remove(i);
                IOBinaryFile.writeToFile(path, products);
                return true;
            }
        }

        return false;
    }

    @Override
    public List<Product> searchByName(String keyword, boolean caseSensitive, boolean exactMatch) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String searchKeyword = keyword.trim();
        if (!caseSensitive) {
            searchKeyword = searchKeyword.toLowerCase();
        }
        final String finalKeyword = searchKeyword;
        products = findAll();
        return products.stream()
                .filter(student -> {
                    String studentName = student.getName();
                    if (!caseSensitive) {
                        studentName = studentName.toLowerCase();
                    }
                    if (exactMatch) {
                        return studentName.equals(finalKeyword);
                    } else {
                        return studentName.contains(finalKeyword);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> sortByPrice(boolean ascending) {
        products = findAll();
        List<Product> sortedList = new ArrayList<>(products);
        if (ascending) {
            sortedList.sort(Comparator.comparing(Product::getPrice).thenComparing(Product::getId));
        } else {
            sortedList.sort(Comparator.comparing(Product::getPrice).thenComparing(Product::getId).reversed());
        }
        return sortedList;
    }
}
