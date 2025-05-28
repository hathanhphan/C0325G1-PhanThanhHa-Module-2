package ss17_io_binary_file_serialization.bai_tap.product_management.controller;

import ss17_io_binary_file_serialization.bai_tap.product_management.common.ViewSelector;
import ss17_io_binary_file_serialization.bai_tap.product_management.entity.Product;
import ss17_io_binary_file_serialization.bai_tap.product_management.service.IProductService;
import ss17_io_binary_file_serialization.bai_tap.product_management.service.ProductService;
import ss17_io_binary_file_serialization.bai_tap.product_management.util.ConsoleColorUtil;
import ss17_io_binary_file_serialization.bai_tap.product_management.view.ProductView;

import java.util.List;
import java.util.Scanner;

public class ProductController {
    private final static IProductService studentService = new ProductService();
    private final static Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        while (true) {
            System.out.println("""
                    Quản lý sản phẩm:
                    1. Danh sách sản phẩm
                    2. Thêm mới sản phẩm
                    3. Cập nhật sản phẩm
                    4. Xoá sản phẩm
                    5. Tìm kiếm sản phẩm
                    6. Sắp xếp sản phẩm
                    7. Thoát""");
            System.out.print("Chọn chức năng: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnYellow("Bạn nhập chức năng không đúng định dạng. Kết thúc chương trình...");
                return;
            }
            ViewSelector selector;
            selector = ViewSelector.fromValue(choice);
            switch (selector) {
                case DISPLAY:
                    displayProductList();
                    break;
                case ADD:
                    addNewProduct();
                    break;
                case UPDATE:
                    updateProduct();
                    break;
                case DELETE:
                    deleteProduct();
                    break;
                case SEARCH:
                    searchProduct();
                    break;
                case SORT:
                    sortProductList();
                    break;
                default:
                    ConsoleColorUtil.printlnYellow("Kết thúc chương trình...");
                    return;
            }
        }
    }

    public static void displayProductList() {
        List<Product> productList = studentService.findAll();
        if (!productList.isEmpty()) {
            ProductView.display(productList);
        } else {
            ConsoleColorUtil.printlnYellow("Danh sách sản phẩm trống!");
        }
    }

    public static void addNewProduct() {
        Long studentId;
        Product product;
        product = ProductView.inputNewProduct();
        if (studentService.add(product)) {
            ConsoleColorUtil.printlnGreen("Thêm mới thành công sản phẩm " + product.getName() + " (" + product.getId() + ")");
        } else {
            boolean isReInputStudentId = true;
            while (isReInputStudentId) {
                ConsoleColorUtil.printlnRed("Thêm mới không thành công. Mã sản phẩm đã tồn tại!");
                isReInputStudentId = ProductView.selectReInputStudentId();
                if (isReInputStudentId) {
                    studentId = ProductView.inputProductId("Nhập lại mã sản phẩm: ");
                    product.setId(studentId);
                    if (studentService.add(product)) {
                        ConsoleColorUtil.printlnGreen("Thêm mới thành công sản phẩm " + product.getName() + " (" + product.getId() + ")");
                        isReInputStudentId = false;
                    }
                }
            }

        }
    }

    public static void updateProduct() {
        Long studentId;
        Product product;
        studentId = ProductView.inputProductId("Nhập mã sản phẩm cần cập nhật: ");
        if ((product = studentService.findById(studentId)) != null) {
            ConsoleColorUtil.printlnGreen("Tìm thấy sản phẩm " + product.getName() + " (" + studentId + ")");
            product = ProductView.inputUpdateProduct(studentId, product);
            if (studentService.update(product)) {
                ConsoleColorUtil.printlnGreen("Cập nhật thành công sản phẩm " + product.getName() + " (" + studentId + ")");
            } else {
                ConsoleColorUtil.printlnRed("Không tìm thấy sản phẩm có mã là " + studentId);
            }
        } else {
            ConsoleColorUtil.printlnRed("Không tìm thấy sản phẩm có mã là " + studentId);
        }
    }

    public static void deleteProduct() {
        Product product;
        Long studentId;
        studentId = ProductView.inputProductId("Nhập mã sản phẩm cần xoá: ");
        if ((product = studentService.findById(studentId)) != null) {
            if (studentService.delete(studentId)) {
                ConsoleColorUtil.printlnGreen("Xoá thành công sản phẩm " + product.getName() + " (" + studentId + ")");
            } else {
                ConsoleColorUtil.printlnRed("Không tìm thấy sản phẩm có mã là " + studentId);
            }
        } else {
            ConsoleColorUtil.printlnRed("Không tìm thấy sản phẩm có mã là " + studentId);
        }
    }

    public static void searchProduct() {
        String studentNameKeyword = ProductView.inputProductName();
        List<Product> foundProductList = studentService.searchByName(studentNameKeyword, false, false);
        if (!foundProductList.isEmpty()) {
            ProductView.display(foundProductList, "\n=========== DANH SÁCH SẢN PHẨM TÊN CÓ CHỨA '" + studentNameKeyword + "' ===========");
        } else {
            ConsoleColorUtil.printlnYellow("Không tìm thấy sản phẩm nào mà tên có chứa '" + studentNameKeyword + "'");
        }
    }

    public static void sortProductList() {
        boolean isAscending = ProductView.selectSortType();
        List<Product> sortedProductList = studentService.sortByPrice(isAscending);
        if (!sortedProductList.isEmpty()) {
            ProductView.display(sortedProductList, "\n=========== DANH SÁCH SẢN PHẨM TÊN SẮP XẾP " + (isAscending ? "TĂNG" : "GIẢM") + " DẦN THEO TÊN ===========");
        } else {
            ConsoleColorUtil.printlnYellow("Danh sách sản phẩm trống!");
        }
    }
}
