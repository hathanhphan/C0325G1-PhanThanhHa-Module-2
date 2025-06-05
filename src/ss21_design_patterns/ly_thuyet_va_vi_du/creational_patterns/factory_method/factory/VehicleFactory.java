package ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.factory;

import ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.entity.Vehicle;

public abstract class VehicleFactory {
    public abstract Vehicle createVehicle(String brand, double price);

    public Vehicle orderVehicle(String brand, double price) {
        Vehicle vehicle = createVehicle(brand, price);
        vehicle.displayInfo();
        vehicle.start();
        return vehicle;
    }
}
