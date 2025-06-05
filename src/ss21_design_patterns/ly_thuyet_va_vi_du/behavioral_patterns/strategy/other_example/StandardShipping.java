package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.strategy.other_example;

public class StandardShipping implements ShippingStrategy {
    public double calculate (Order order){
        return order.getWeight() * 2.0; // $2 per kg
    }
}

