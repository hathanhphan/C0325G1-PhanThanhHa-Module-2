package ss21_design_patterns.ly_thuyet_va_vi_du.behavioral_patterns.command;

public class AirConditioner {
    private String location;
    private boolean isOn;
    private int temperature; // độ C

    public AirConditioner(String location) {
        this.location = location;
        this.isOn = false;
        this.temperature = 25;
    }

    public void turnOn() {
        isOn = true;
        System.out.println("Điều hòa " + location + " đã BẬT (nhiệt độ: " + temperature + "°C)");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("Điều hòa " + location + " đã TẮT");
    }

    public void setTemperature(int temperature) {
        if (isOn) {
            this.temperature = Math.max(16, Math.min(30, temperature));
            System.out.println("Điều hòa " + location + " điều chỉnh nhiệt độ: " + this.temperature + "°C");
        } else {
            System.out.println("Không thể điều chỉnh nhiệt độ - Điều hòa " + location + " đang tắt");
        }
    }

    public boolean isOn() { return isOn; }
    public int getTemperature() { return temperature; }
    public String getLocation() { return location; }
}
