package ss7_abstract_class_and_interface.bai_tap.resizable;

import ss6_inherit.thuc_hanh.shape.Circle;
import ss6_inherit.thuc_hanh.shape.Rectangle;
import ss6_inherit.thuc_hanh.shape.Square;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5);
        System.out.println(circle);
        circle.resize(80);
        System.out.println(circle);
        System.out.println();

        Rectangle rectangle = new Rectangle(3, 5);
        System.out.println(rectangle);
        rectangle.resize(70);
        System.out.println(rectangle);
        System.out.println();

        Square square = new Square(4);
        System.out.println(square);
        square.resize(200);
        System.out.println(square);
        System.out.println();
    }
}
