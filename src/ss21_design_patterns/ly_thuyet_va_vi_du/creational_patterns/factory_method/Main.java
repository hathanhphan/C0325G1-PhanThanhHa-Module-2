package ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method;

import ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.entity.Vehicle;
import ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.factory.CarFactory;
import ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.factory.MotorcycleFactory;
import ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.factory_method.factory.VehicleFactory;

/*
* Factory Method tạo ra các đối tượng mà không cần chỉ định chính xác class của chúng.
* Thay vào đó, nó ủy thác việc tạo đối tượng cho các subclass.
* Pattern này đặc biệt hữu ích khi bạn có nhiều loại đối tượng tương tự nhưng cách khởi tạo khác nhau
* */

public class Main {
    public static void main(String[] args) {
        VehicleFactory carFactory = new CarFactory();
        VehicleFactory motoFactory = new MotorcycleFactory();
        Vehicle car = carFactory.orderVehicle("Toyota", 25000);
        Vehicle myMoto = motoFactory.orderVehicle("Honda", 8000);
    }
}
