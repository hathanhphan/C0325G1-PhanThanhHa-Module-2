package ss21_design_patterns.ly_thuyet_va_vi_du.structural_patterns.facade;

public class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void startComputer() {
        System.out.println("=== BẮT ĐẦU KHỞI ĐỘNG MÁY TÍNH ===");
        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();
        System.out.println("=== MÁY TÍNH ĐÃ KHỞI ĐỘNG XONG ===\n");
    }
}
