package ss21_design_patterns.ly_thuyet_va_vi_du.structural_patterns.decorator;

/*
* Decorator Pattern cho phép thêm các tính năng mới vào đối tượng
* một cách động mà không cần thay đổi cấu trúc của chúng.
* Pattern này tạo ra một wrapper class chứa đối tượng gốc và cung cấp thêm các chức năng.
* */

public class Main {
    public static void main(String[] args) {
        // Cà phê đen cơ bản
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " - $" + coffee.getCost());

        // Thêm sữa
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " - $" + coffee.getCost());

        // Thêm đường
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " - $" + coffee.getCost());

        // Thêm kem tươi
        coffee = new WhippedCreamDecorator(coffee);
        System.out.println(coffee.getDescription() + " - $" + coffee.getCost());

        System.out.println("\n--- Tạo một ly cà phê khác ---");

        // Tạo một ly cà phê phức tạp khác
        Coffee specialCoffee = new WhippedCreamDecorator(
                new MilkDecorator(
                        new MilkDecorator(
                                new SugarDecorator(
                                        new SimpleCoffee()
                                )
                        )
                )
        );

        System.out.println(specialCoffee.getDescription() + " - $" + specialCoffee.getCost());
    }
}
