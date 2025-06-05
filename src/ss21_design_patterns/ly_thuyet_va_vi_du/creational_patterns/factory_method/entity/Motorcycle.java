package ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.entity;

public class Motorcycle extends Vehicle {
    public Motorcycle(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    @Override
    public void start() {
        System.out.println("Xe máy " + brand + " đã khởi động");
    }

    @Override
    public void displayInfo() {
        System.out.println("Xe máy: " + brand + ", Giá: $" + price);
    }
}
