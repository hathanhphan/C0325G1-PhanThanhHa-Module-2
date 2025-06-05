package ss21_design_patterns.ly_thuyet_va_vi_du.structural_patterns.decorator;

public class WhippedCreamDecorator extends CoffeeDecorator {
    public WhippedCreamDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return coffee.getDescription() + " + Kem tươi";
    }

    @Override
    public double getCost() {
        return coffee.getCost() + 0.7;
    }
}

