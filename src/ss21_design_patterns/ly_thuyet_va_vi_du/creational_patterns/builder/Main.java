package ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.builder;

/*
* Builder Pattern được sử dụng để xây dựng các đối tượng phức tạp từng bước một.
* Pattern này đặc biệt hữu ích khi bạn cần tạo đối tượng có nhiều thuộc tính tùy chọn hoặc khi quá trình khởi tạo phức tạp.
* */

public class Main {
    public static void main(String[] args) {
        // Tạo computer cơ bản
        Computer basicComputer = new Computer.ComputerBuilder("Intel i5", "8GB").build();

        // Tạo computer gaming cao cấp
        Computer gamingComputer = new Computer.ComputerBuilder("Intel i9", "64GB")
                .setStorage("1TB NVMe SSD")
                .setGraphicsCard("RTX 4080")
                .setWiFi(true)
                .setBluetooth(true)
                .build();

        System.out.println("Máy tính cơ bản: " + basicComputer);
        System.out.println("Máy tính gaming: " + gamingComputer);
    }
}
