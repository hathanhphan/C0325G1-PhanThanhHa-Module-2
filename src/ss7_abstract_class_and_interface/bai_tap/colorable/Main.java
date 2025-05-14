package ss7_abstract_class_and_interface.bai_tap.colorable;

import ss6_inherit.thuc_hanh.shape.Square;

public class Main {
    public static void main(String[] args) {
        Square square = new Square(10);
        System.out.println(square);
        square.howToColor();
    }
}