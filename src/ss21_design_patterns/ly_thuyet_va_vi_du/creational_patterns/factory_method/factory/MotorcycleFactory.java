package ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.factory;

import ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.entity.Motorcycle;
import ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.entity.Vehicle;

public class MotorcycleFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle(String brand, double price) {
        return new Motorcycle(brand, price);
    }
}
