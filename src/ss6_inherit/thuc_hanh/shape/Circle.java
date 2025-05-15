package ss6_inherit.thuc_hanh.shape;

import ss7_abstract_class_and_interface.bai_tap.resizable.Resizable;

public class Circle extends Shape implements Resizable {
    private double radius = 1.0;

    public Circle() {}

    public Circle(double radius) {
        this.radius = radius;
    }

    public Circle(String color, boolean filled, double radius) {
        super(color, filled);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return radius * radius * Math.PI;
    }

    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }

    @Override
    public String toString() {
        return "A " + this.getClass().getSimpleName() + " with radius = "
                + getRadius() + ", color of "
                + getColor()
                + " and "
                + (isFilled() ? "filled" : "not filled");
    }

    @Override
    public void resize(double percent) {
        setRadius(getRadius() * (1 + percent / 100));
    }
}
