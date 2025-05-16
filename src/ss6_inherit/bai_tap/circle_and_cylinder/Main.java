package ss6_inherit.bai_tap.circle_and_cylinder;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5, "red");
        Cylinder cylinder = new Cylinder(5, "red", 10);
        System.out.println(circle);
        System.out.println("Area: " + circle.getArea());
        System.out.println(cylinder);
        System.out.println("Volume: " + cylinder.getVolume());
    }
}
