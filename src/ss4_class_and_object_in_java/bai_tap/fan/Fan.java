package ss4_class_and_object_in_java.bai_tap.fan;

public class Fan {
    public static final int SLOW = 1;
    public static final int MEDIUM = 2;
    public static final int FAST = 3;
    private int speed = SLOW;
    private boolean on = false;
    private double radius = 5;
    private String color = "blue";

    public Fan() {
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed == SLOW || speed == MEDIUM || speed == FAST) {
            this.speed = speed;
        } else {
            System.out.println("Tốc độ không phù hợp. Vui lòng chọn 1 trong 3 mức: SLOW, MEDIUM hoặc FAST");
        }
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Fan{" +
                (on ? "speed=" + speed + ", " : "" ) +
                "radius=" + radius +
                ", color='" + color + '\'' +
                "}\n" +
                "Fan is " + (on ? "on" : "off");
    }
}
