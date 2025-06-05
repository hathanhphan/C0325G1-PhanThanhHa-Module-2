package ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.entity;

public class Car extends Vehicle {
    public Car(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    @Override
    public void start() {
        System.out.println("Xe hơi " + brand + " đã khởi động động cơ");
    }

    @Override
    public void displayInfo() {
        System.out.println("Xe hơi: " + brand + ", Giá: $" + price);
    }
}
