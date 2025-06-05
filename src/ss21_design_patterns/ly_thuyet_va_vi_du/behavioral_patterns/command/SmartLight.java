package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.command;

// Receiver - đối tượng thực hiện công việc thực tế
public class SmartLight {
    private String location;
    private boolean isOn;
    private int brightness; // 0-100

    public SmartLight(String location) {
        this.location = location;
        this.isOn = false;
        this.brightness = 0;
    }

    public void turnOn() {
        isOn = true;
        brightness = 50; // Mặc định 50%
        System.out.println("Đèn " + location + " đã BẬT (độ sáng: " + brightness + "%)");
    }

    public void turnOff() {
        isOn = false;
        brightness = 0;
        System.out.println("Đèn " + location + " đã TẮT");
    }

    public void setBrightness(int brightness) {
        if (isOn) {
            this.brightness = Math.max(0, Math.min(100, brightness));
            System.out.println("Đèn " + location + " điều chỉnh độ sáng: " + this.brightness + "%");
        } else {
            System.out.println("Không thể điều chỉnh độ sáng - Đèn " + location + " đang tắt");
        }
    }

    public boolean isOn() { return isOn; }
    public int getBrightness() { return brightness; }
    public String getLocation() { return location; }
}
