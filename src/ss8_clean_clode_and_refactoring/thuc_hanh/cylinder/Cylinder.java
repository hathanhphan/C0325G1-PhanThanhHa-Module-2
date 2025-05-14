package ss8_clean_clode_and_refactoring.thuc_hanh.cylinder;

public class Cylinder {
    public static double getVolume(int radius, int height){
        double baseArea = getBaseArea(radius);
        double perimeter = getPerimeter(radius);
        return perimeter * height + 2 * baseArea;
    }
    public static double getPerimeter(int radius) {
        return 2 * Math.PI * radius;
    }
    public static double getBaseArea(int radius) {
        return Math.PI * radius * radius;
    }
}
