package ss21_design_patterns.ly_thuyet_va_vi_du.structural_patterns.decorator;

public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + Đường";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.2;
    }
}
