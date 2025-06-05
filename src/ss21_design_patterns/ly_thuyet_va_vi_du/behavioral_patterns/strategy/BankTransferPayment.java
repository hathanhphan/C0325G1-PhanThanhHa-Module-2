package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.strategy;

public class BankTransferPayment implements PaymentStrategy {
    private String bankAccount;
    private String bankName;

    public BankTransferPayment(String bankAccount, String bankName) {
        this.bankAccount = bankAccount;
        this.bankName = bankName;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Đang xử lý chuyển khoản $" + amount + " qua ngân hàng...");
        System.out.println("Kết nối đến " + bankName + "...");

        // Giả lập quá trình chuyển khoản
        try {
            Thread.sleep(1000); // Giả lập thời gian xử lý
            System.out.println("Chuyển khoản thành công!");
            return true;
        } catch (InterruptedException e) {
            System.out.println("Chuyển khoản thất bại!");
            return false;
        }
    }

    @Override
    public String getPaymentDetails() {
        return "Chuyển khoản: " + bankName + " - ****" +
                bankAccount.substring(bankAccount.length() - 4);
    }
}
