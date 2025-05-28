package ss17_io_binary_file_serialization.bai_tap.product_management.view;

import ss17_io_binary_file_serialization.bai_tap.product_management.entity.Product;
import ss17_io_binary_file_serialization.bai_tap.product_management.util.ConsoleColorUtil;
import ss17_io_binary_file_serialization.bai_tap.product_management.util.InputValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    private static final Scanner scanner = new Scanner(System.in);

    public static void display(List<Product> productList, String title) {
        System.out.println(title);
        displayAtTable(productList);
        System.out.println();
    }

    public static void display(List<Product> productList) {
        display(productList, "\n=========== DANH SÁCH SẢN PHẨM ===========");
    }

    public static void displayAtTable(List<Product> productList) {
        String idLabel = "Mã SP";
        String nameLabel = "Tên sản phẩm";
        String priceLabel = "Giá (VND)";
        String brandNameLabel = "Hãng sản xuất";
        String descriptionLabel = "Mô tả";
        int maxIdLength = idLabel.length();
        int maxNameLength = nameLabel.length();
        int maxPriceLength = priceLabel.length();
        int maxBrandNameLength = brandNameLabel.length();
        int maxDescriptionLength = descriptionLabel.length();
        for (Product product : productList) {
            maxIdLength = Math.max(maxIdLength, String.valueOf(product.getId()).length());
            maxNameLength = Math.max(maxNameLength, product.getName().length());
            maxPriceLength = Math.max(maxPriceLength, String.format("%.2f", product.getPrice()).length());
            maxBrandNameLength = Math.max(maxBrandNameLength, product.getBrandName().length());
            maxDescriptionLength = Math.max(maxDescriptionLength, product.getDescription().length());
        }

        // Tạo định dạng cho dòng
        String lineFormat = "+-" + "-".repeat(maxIdLength) + "-+-" +
                "-".repeat(maxNameLength) + "-+-" +
                "-".repeat(maxPriceLength) + "-+-" +
                "-".repeat(maxBrandNameLength) + "-+-" +
                "-".repeat(maxDescriptionLength) + "-+%n";

        // Tạo định dạng cho dữ liệu
        String dataFormat = "| %-" + maxIdLength + "s | %-" + maxNameLength + "s | %-" +
                maxPriceLength + "s | %-" + maxBrandNameLength + "s | %-" + maxDescriptionLength + "s |%n";
        // In bảng
        System.out.printf(lineFormat);
        System.out.printf(dataFormat, idLabel, nameLabel, priceLabel, brandNameLabel, descriptionLabel);
        System.out.printf(lineFormat);
        for (Product product : productList) {
            System.out.printf(dataFormat,
                    product.getId(),
                    product.getName(),
                    String.format("%.2f", product.getPrice()),
                    product.getBrandName(),
                    product.getDescription());
        }
        System.out.printf(lineFormat);
    }

    public static Product inputNewProduct() {
        Long id = InputValidator.inputPositiveLong("Nhập mã sản phẩm: ", "mã sản phẩm", 5, false);
        String name = InputValidator.inputString("Nhập tên sản phẩm: ", "tên sản phẩm", 5, 50, false);
        BigDecimal price = InputValidator.inputBigDecimal("Nhập giá: ", "giá", BigDecimal.ZERO, new BigDecimal(Double.MAX_VALUE), false);
        String brandName = InputValidator.inputString("Nhập hãng sản xuất: ", "hãng sản xuất", 2, 255, false);
        String description = InputValidator.inputString("Nhập mô tả: ", "mô tả", 5, 255, false);
        return new Product(id, name, price, brandName, description);
    }

    public static Long inputProductId(String message) {
        return InputValidator.inputPositiveLong(message, "mã sản phẩm", 5, false);
    }

    public static Product inputUpdateProduct(Long productId, Product product) {
        ConsoleColorUtil.printlnYellow("Để trống nếu không muốn cập nhật thông tin nào đó...");
        String name = InputValidator.inputString("Nhập tên sản phẩm cần cập nhật: ", "tên sản phẩm", 5, 50, true);
        BigDecimal price = InputValidator.inputBigDecimal("Nhập giá cần cập nhật: ", "giá", BigDecimal.ZERO, new BigDecimal(Double.MAX_VALUE), true);
        String brandName = InputValidator.inputString("Nhập hãng sản xuất cần cập nhật: ", "hãng sản xuất", 2, 255, true);
        String description = InputValidator.inputString("Nhập mô tả cần cập nhật: ", "mô tả", 5, 255, true);

        if (name == null) name = product.getName();
        if (price == null) price = product.getPrice();
        if (brandName == null) brandName = product.getBrandName();
        if (description == null) description = product.getDescription();
        return new Product(productId, name, price, brandName, description);
    }

    public static String inputProductName() {
        System.out.print("Nhập tên sản phẩm cần tìm kiếm: ");
        return scanner.nextLine();
    }

    public static boolean selectSortType() {
        while (true) {
            try {
                System.out.println("""
                        Chọn kiểu sắp xếp:
                        \t1. Tăng dần theo giá
                        \t2. Giảm dần theo giá""");
                System.out.print("Chọn chức năng: ");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) return true;
                if (choice == 2) return false;
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed("Bạn chọn chức năng không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    public static boolean selectReInputStudentId() {
        while (true) {
            try {
                System.out.println("""
                        Chọn hướng giải quyết:
                        \t1. Nhập lại mã sản phẩm
                        \t2. Kết thúc chức năng thêm mới""");
                System.out.print("Chọn chức năng: ");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) return true;
                if (choice == 2) return false;
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed("Bạn chọn chức năng không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
