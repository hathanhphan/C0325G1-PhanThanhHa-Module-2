package ss7_abstract_class_and_interface.bai_tap.colorable;

import ss6_inherit.thuc_hanh.shape.Circle;
import ss6_inherit.thuc_hanh.shape.Rectangle;
import ss6_inherit.thuc_hanh.shape.Shape;
import ss6_inherit.thuc_hanh.shape.Square;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Rectangle("grey", false, 30, 17),
                new Square("purple", true, 15),
                new Circle("yellow", true, 15.9)
        };
        for (Shape shape: shapes) {
            System.out.print(shape.getClass().getSimpleName() + ": Area is ");
            System.out.println(shape.getArea());
            if (shape instanceof Colorable) {
                ((Colorable) shape).howToColor();
            }
            System.out.println();
        }
    }
}