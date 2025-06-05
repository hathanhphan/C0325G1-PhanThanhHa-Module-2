package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.strategy;

/*
* Strategy Pattern định nghĩa một họ các thuật toán, đóng gói từng thuật toán và làm cho chúng có thể thay thế cho nhau.
* Pattern này cho phép thuật toán thay đổi độc lập với client sử dụng nó.
* */

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Thêm sản phẩm vào giỏ hàng
        cart.addItem("Laptop Dell", 899.99);
        cart.addItem("Chuột wireless", 25.50);
        cart.addItem("Bàn phím cơ", 120.00);

        System.out.println("\n--- Thanh toán bằng thẻ tín dụng ---");
        cart.setPaymentStrategy(new CreditCardPayment("1234567890123456", "John Doe", "123"));
        cart.checkout();

        // Thêm sản phẩm mới
        cart.addItem("Màn hình 4K", 299.99);
        cart.addItem("Webcam HD", 89.99);

        System.out.println("\n--- Thanh toán qua PayPal ---");
        cart.setPaymentStrategy(new PayPalPayment("john.doe@email.com", "securepassword"));
        cart.checkout();

        // Thêm sản phẩm khác
        cart.addItem("Tai nghe gaming", 159.99);

        System.out.println("\n--- Thanh toán bằng chuyển khoản ---");
        cart.setPaymentStrategy(new BankTransferPayment("9876543210", "Vietcombank"));
        cart.checkout();

        // Test case thất bại
        cart.addItem("SSD 1TB", 199.99);
        System.out.println("\n--- Test thanh toán thất bại ---");
        cart.setPaymentStrategy(new CreditCardPayment("123456", "Invalid User", "12")); // Thẻ không hợp lệ
        cart.checkout();
    }
}
