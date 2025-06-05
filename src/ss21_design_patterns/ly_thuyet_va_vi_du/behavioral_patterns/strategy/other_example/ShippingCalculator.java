package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.strategy.other_example;

/*
*   4. Strategy Pattern - ⭐⭐⭐⭐⭐
    Tần suất sử dụng: Rất cao (75% dự án business logic)

    Strategy được sử dụng rộng rãi trong business logic:

    Payment Processing: PayPal, Credit Card, Bank Transfer
    Sorting Algorithms: Collections.sort() với Comparator
    Validation Systems: Different validation rules
    Pricing Strategies: Discount calculations, Tax calculations
    Copy// Ví dụ thực tế: E-commerce Shipping Calculator
*
* */

public class ShippingCalculator {
    private ShippingStrategy strategy;

    public void setStrategy(ShippingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateShipping(Order order) {
        return strategy.calculate(order);
    }
}
