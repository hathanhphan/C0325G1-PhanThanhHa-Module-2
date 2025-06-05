package ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.entity;

public abstract class Vehicle {
    protected String brand;
    protected double price;

    public abstract void start();
    public abstract void displayInfo();
}
