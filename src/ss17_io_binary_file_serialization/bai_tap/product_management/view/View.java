package ss17_io_binary_file_serialization.bai_tap.product_management.view;

import ss17_io_binary_file_serialization.bai_tap.product_management.controller.ProductController;

public class View {
    public static void main(String[] args) {
        ProductController productController = new ProductController();
        productController.displayMenu();
    }
}
