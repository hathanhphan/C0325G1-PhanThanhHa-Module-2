package ss12_java_collection_framework.bai_tap.product_management;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductManager {
    private List<Product> productsList;

    public ProductManager() {
        this.productsList = new ArrayList<>();
        // Chỉ cần đổi ArrayList sang LinkedList, các method triển khai bên dưới không cần sửa đổi gì
        //this.productsList = new LinkedList<>();
        initializeSample();
    }

    private void initializeSample() {
        productsList.add(new Product(1, "Laptop Dell XPS 13", 1299.99));
        productsList.add(new Product(2, "iPhone 13 Pro", 999.99));
        productsList.add(new Product(3, "Samsung TV", 799.50));
        productsList.add(new Product(4, "Nike Running Shoes", 129.99));
        productsList.add(new Product(5, "Coffee Maker", 89.99));
        productsList.add(new Product(6, "Book: Java Programming", 49.99));
        productsList.add(new Product(7, "Wireless Headphones", 159.99));
        productsList.add(new Product(8, "Backpack", 79.99));
        productsList.add(new Product(9, "Water Bottle", 19.99));
        productsList.add(new Product(10, "Smart Watch", 249.99));
    }

    public boolean add(Product product) {
        for (Product p : productsList) {
            if (p.getId() == product.getId()) {
                return false;
            }
        }
        return productsList.add(product);
    }

    public boolean update(long id, Product product) {
        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).getId() == id) {
                product.setId(id);
                productsList.set(i, product);
                return true;
            }
        }
        return false;
    }

    public boolean delete(long id) {
        for (int i = 0; i < productsList.size(); i++) {
            if (productsList.get(i).getId() == id) {
                productsList.remove(i);
                return true;
            }
        }
        return false;
    }

    public void displayAll() {
        System.out.println("\n=========== DANH SÁCH SẢN PHẨM ===========");
        displayAtTable(productsList);
    }

    public void displayAtTable(List<Product> products) {
        int maxIdLength = 2;
        int maxNameLength = 4;
        int maxPriceLength = 5;
        for (Product product : products) {
            maxIdLength = Math.max(maxIdLength, String.valueOf(product.getId()).length());
            maxNameLength = Math.max(maxNameLength, product.getName().length());
            maxPriceLength = Math.max(maxPriceLength, String.format("$%.2f", product.getPrice()).length());
        }
        // Tạo định dạng cho dòng
        String lineFormat = "+-" + "-".repeat(maxIdLength) + "-+-" +
                "-".repeat(maxNameLength) + "-+-" +
                "-".repeat(maxPriceLength) + "-+%n";
        // Tạo định dạng cho dữ liệu
        String dataFormat = "| %-" + maxIdLength + "s | %-" + maxNameLength + "s | %-" +
                maxPriceLength + "s |%n";
        // In bảng
        System.out.printf(lineFormat);
        System.out.printf(dataFormat, "Mã", "Tên sản phẩm", "Đơn giá");
        System.out.printf(lineFormat);
        for (Product product : products) {
            System.out.printf(dataFormat,
                    product.getId(),
                    product.getName(),
                    String.format("$%.2f", product.getPrice()));
        }
        System.out.printf(lineFormat);
    }

    public List<Product> searchByName(String keyword, boolean caseSensitive, boolean exactMatch) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String searchKeyword = keyword.trim();
        if (!caseSensitive) {
            searchKeyword = searchKeyword.toLowerCase();
        }
        final String finalKeyword = searchKeyword;
        return productsList.stream()
                .filter(product -> {
                    String productName = product.getName();
                    if (!caseSensitive) {
                        productName = productName.toLowerCase();
                    }
                    if (exactMatch) {
                        return productName.equals(finalKeyword);
                    } else {
                        return productName.contains(finalKeyword);
                    }
                })
                .collect(Collectors.toList());
    }

    public List<Product> sortByPrice(boolean ascending) {
        List<Product> sortedList = new ArrayList<>(productsList);
        if (ascending) {
            sortedList.sort(Comparator.comparing(Product::getPrice));
        } else {
            sortedList.sort(Comparator.comparing(Product::getPrice).reversed());
        }
        return sortedList;
    }

}
