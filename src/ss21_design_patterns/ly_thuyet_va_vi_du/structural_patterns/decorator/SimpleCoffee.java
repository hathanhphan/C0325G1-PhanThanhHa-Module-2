package ss21_design_patterns.ly_thuyet_va_vi_du.structural_patterns.decorator;

public class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Cà phê đen";
    }

    @Override
    public double getCost() {
        return 2.0;
    }
}
