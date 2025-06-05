package ss21_design_patterns.ly_thuyet_va_vi_du.structural_patterns.decorator;

public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + Sữa";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }
}
