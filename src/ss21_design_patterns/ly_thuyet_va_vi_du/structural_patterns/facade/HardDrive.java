package ss21_design_patterns.ly_thuyet_va_vi_du.structural_patterns.facade;

public class HardDrive {
    public byte[] read(long lba, int size) {
        System.out.println("HardDrive: Đọc " + size + " bytes từ sector " + lba);
        return new byte[size];
    }
}
