package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.strategy;

public interface PaymentStrategy {
    boolean pay(double amount);
    String getPaymentDetails();
}
