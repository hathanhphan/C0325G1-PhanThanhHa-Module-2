package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.strategy.other_example;

public class ExpressShipping implements ShippingStrategy {
    public double calculate(Order order) {
        return order.getWeight() * 5.0 + 10.0; // $5 per kg + $10 express fee
    }
}
