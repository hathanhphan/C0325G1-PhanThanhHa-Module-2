package ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.factory;

import ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.entity.Car;
import ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.entity.Vehicle;

public class CarFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle(String brand, double price) {
        return new Car(brand, price);
    }
}
