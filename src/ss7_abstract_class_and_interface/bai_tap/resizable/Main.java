package ss7_abstract_class_and_interface.bai_tap.resizable;

import ss6_inherit.thuc_hanh.shape.Circle;
import ss6_inherit.thuc_hanh.shape.Rectangle;
import ss6_inherit.thuc_hanh.shape.Shape;
import ss6_inherit.thuc_hanh.shape.Square;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Square("purple", true, 10),
            new Rectangle("grey", false, 20, 10),
            new Circle("yellow", true, 8.5)
        };

        System.out.println("Before resizing:");
        printArea(shapes);
        System.out.println();

        Random random = new Random();
        int randomNumber;
        for (Shape shape: shapes) {
            randomNumber = random.nextInt(100) + 1;
            if (shape instanceof Circle) {
               ((Circle) shape).resize(randomNumber);
            } else if (shape instanceof Square) {
                ((Square) shape).resize(randomNumber);
            } else if (shape instanceof Rectangle) {
                ((Rectangle) shape).resize(randomNumber);
            }
        }

        System.out.println("After resizing:");
        printArea(shapes);
    }
    public static void printArea(Shape[] shapes) {
        for (Shape shape: shapes) {
            System.out.println(shape.getClass().getSimpleName() + ": Area is " + shape.getArea());
        }
    }
}
