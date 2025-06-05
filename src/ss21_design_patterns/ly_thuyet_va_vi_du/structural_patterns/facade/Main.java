package ss21_design_patterns.ly_thuyet_va_vi_du.structural_patterns.facade;

/*
* Facade Pattern cung cấp một interface đơn giản cho một hệ thống phức tạp.
* Nó che giấu sự phức tạp của hệ thống và cung cấp một interface dễ sử dụng cho client.
* */

public class Main {
    public static void main(String[] args) {
        // Không cần biết chi tiết phức tạp bên trong
        ComputerFacade computer = new ComputerFacade();
        computer.startComputer();

        // So sánh với việc phải gọi từng subsystem
        System.out.println("--- Nếu không có Facade ---");
        CPU cpu = new CPU();
        Memory memory = new Memory();
        HardDrive hardDrive = new HardDrive();

        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();
    }
}
