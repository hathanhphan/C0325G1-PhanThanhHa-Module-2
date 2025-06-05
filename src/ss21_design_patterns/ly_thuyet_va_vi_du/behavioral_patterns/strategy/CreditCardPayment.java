package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.strategy;

public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String holderName;
    private String cvv;

    public CreditCardPayment(String cardNumber, String holderName, String cvv) {
        this.cardNumber = cardNumber;
        this.holderName = holderName;
        this.cvv = cvv;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Đang xử lý thanh toán $" + amount + " bằng thẻ tín dụng...");
        // Giả lập quá trình xử lý thanh toán
        if (isValidCard()) {
            System.out.println("Thanh toán thành công!");
            return true;
        } else {
            System.out.println("Thanh toán thất bại - Thẻ không hợp lệ!");
            return false;
        }
    }

    @Override
    public String getPaymentDetails() {
        return "Thẻ tín dụng: ****" + cardNumber.substring(cardNumber.length() - 4)
                + " - " + holderName;
    }

    private boolean isValidCard() {
        // Giả lập validation logic
        return cardNumber.length() == 16 && cvv.length() == 3;
    }
}
