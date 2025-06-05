package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.strategy;

public class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Đang xử lý thanh toán $" + amount + " qua PayPal...");
        if (isValidAccount()) {
            System.out.println("Đăng nhập PayPal thành công!");
            System.out.println("Thanh toán hoàn tất!");
            return true;
        } else {
            System.out.println("Thanh toán thất bại - Tài khoản PayPal không hợp lệ!");
            return false;
        }
    }

    @Override
    public String getPaymentDetails() {
        return "PayPal: " + email;
    }

    private boolean isValidAccount() {
        return email.contains("@") && password.length() >= 6;
    }
}
