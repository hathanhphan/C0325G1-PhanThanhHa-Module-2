package ss21_design_patterns.ly_thuyet_va_vi_du.structural_patterns.facade;

public class Memory {
    public void load(long position, byte[] data) {
        System.out.println("Memory: Đang tải " + data.length + " bytes vào vị trí " + position);
    }
}
