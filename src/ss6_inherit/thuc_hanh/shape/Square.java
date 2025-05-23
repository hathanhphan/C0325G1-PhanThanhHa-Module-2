package ss6_inherit.thuc_hanh.shape;

import ss7_abstract_class_and_interface.bai_tap.colorable.Colorable;
import ss7_abstract_class_and_interface.bai_tap.resizable.Resizable;

public class Square extends Rectangle implements Resizable, Colorable {
    public Square() {}

    public Square(double side) {
        super(side, side);
    }

    public Square(String color, boolean filled, double side) {
        super(color, filled, side, side);
    }

    public double getSide() {
        return getWidth();
    }

    public void setSide(double side) {
        setWidth(side);
        setLength(side);
    }

    @Override
    public String toString() {
        return "A " + this.getClass().getSimpleName() + " with side = "
                + getSide() + ", color of "
                + getColor()
                + " and "
                + (isFilled() ? "filled" : "not filled");
    }

    @Override
    public void resize(double percent) {
        setSide(getSide() * (1 + percent / 100));
    }

    @Override
    public void howToColor() {
        System.out.println(this.getClass().getSimpleName() + ": Color all four sides");
    }
}
