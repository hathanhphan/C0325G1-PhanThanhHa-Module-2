package ss6_inherit.thuc_hanh.shape;

import ss7_abstract_class_and_interface.bai_tap.resizable.Resizable;

public class Rectangle extends Shape implements Resizable {
    private double width = 1;
    private double length = 1;

    public Rectangle() {}

    public Rectangle(double width, double length) {
        this.width = width;
        this.length = length;
    }

    public Rectangle(String color, boolean filled, double width, double length) {
        super(color, filled);
        this.width = width;
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getArea() {
        return width * this.length;
    }

    public double getPerimeter() {
        return 2 * (width + this.length);
    }

    @Override
    public String toString() {
        return "A " + this.getClass().getSimpleName() + " with width = "
                + getWidth() + ", length = "
                + getLength() + ", color of "
                + getColor()
                + " and "
                + (isFilled() ? "filled" : "not filled");
    }

    @Override
    public void resize(double percent) {
        setLength(getLength() * (1 + percent / 100));
        setWidth(getWidth() * (1 + percent / 100));
    }
}
