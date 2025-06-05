package ss21_design_patterns.ly_thuyet_va_vi_du.structural_patterns.facade;

public class CPU {
    public void freeze() {
        System.out.println("CPU: Đang đóng băng...");
    }

    public void jump(long position) {
        System.out.println("CPU: Nhảy đến vị trí " + position);
    }

    public void execute() {
        System.out.println("CPU: Đang thực thi lệnh...");
    }
}
