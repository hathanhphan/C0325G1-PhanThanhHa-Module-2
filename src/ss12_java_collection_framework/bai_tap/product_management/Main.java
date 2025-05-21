package ss12_java_collection_framework.bai_tap.product_management;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductManager productManagerArrayList = new ProductManager();

        // Hiển thị danh sách sản phẩm
        productManagerArrayList.displayAll();

        // Thêm mới sản phẩm
        if (productManagerArrayList.add(new Product(11, "Smart Key", 12.99))) {
            System.out.println("\nThêm thành công!");
        } else {
            System.out.println("\nMã sản phẩm đã tồn tại!");
        }

        // Sửa sản phẩm
        if (productManagerArrayList.update(8, new Product(8, "Backpack White", 88.99))) {
            System.out.println("\nSửa thành công!");
        } else {
            System.out.println("\nKhông tìm thấy sản phẩm cần sửa!");
        }

        // Xoá sản phẩm
        if (productManagerArrayList.delete(4)) {
            System.out.println("\nXoá thành công!");
        } else {
            System.out.println("\nKhông tìm thấy sản phẩm cần xoá!");
        }

        // Tìm kiếm sản phẩm theo tên
        String keyword = "13";
        List<Product> foundProductList = productManagerArrayList.searchByName(keyword, false, false);
        if (!foundProductList.isEmpty()) {
            System.out.printf("\nTìm thấy %d sản phẩm có chứa từ khoá %s%n", foundProductList.size(), keyword);
            System.out.println("=========== KẾT QUẢ TÌM KIẾM ===========");
            productManagerArrayList.displayAtTable(foundProductList);
        } else {
            System.out.printf("\nKhông tìm thấy sản phẩm nào có chứa từ khoá %s%n", keyword);
        }

        // Sắp xếp sản phẩm theo đơn giá
        List<Product> sortedProductList = productManagerArrayList.sortByPrice(true);
        System.out.println("\n=========== SẮP XẾP TĂNG DẦN THEO GIÁ ===========");
        productManagerArrayList.displayAtTable(sortedProductList);
        System.out.println("\n=========== SẮP XẾP GIẢM DẦN THEO GIÁ ===========");
        sortedProductList = productManagerArrayList.sortByPrice(false);
        productManagerArrayList.displayAtTable(sortedProductList);
    }
}
