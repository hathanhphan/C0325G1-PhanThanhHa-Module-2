package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.strategy;

import java.util.ArrayList;
import java.util.List;

// Context
public class ShoppingCart {
    private List<String> items;
    private double totalAmount;
    private PaymentStrategy paymentStrategy;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.totalAmount = 0;
    }

    public void addItem(String item, double price) {
        items.add(item + " - $" + price);
        totalAmount += price;
        System.out.println("Đã thêm: " + item + " ($" + price + ")");
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
        System.out.println("Đã chọn phương thức thanh toán: " +
                paymentStrategy.getPaymentDetails());
    }

    public void checkout() {
        if (paymentStrategy == null) {
            System.out.println("Vui lòng chọn phương thức thanh toán!");
            return;
        }

        System.out.println("\n=== HÓA ĐƠN ===");
        System.out.println("Các sản phẩm:");
        for (String item : items) {
            System.out.println("- " + item);
        }
        System.out.println("Tổng cộng: $" + totalAmount);
        System.out.println("Phương thức thanh toán: " + paymentStrategy.getPaymentDetails());
        System.out.println("================");

        boolean success = paymentStrategy.pay(totalAmount);

        if (success) {
            System.out.println("Đơn hàng đã được xử lý thành công!");
            items.clear();
            totalAmount = 0;
        } else {
            System.out.println("Đơn hàng chưa được xử lý. Vui lòng thử lại!");
        }
        System.out.println("---");
    }
}
