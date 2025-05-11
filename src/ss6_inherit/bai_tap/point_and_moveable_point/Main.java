package ss6_inherit.bai_tap.point_and_moveable_point;

public class Main {
    public static void main(String[] args) {
        Point point = new Point(3, 5);
        System.out.println(point);
        MoveablePoint moveablePoint = new MoveablePoint(3, 5, 2, 3);
        System.out.println(moveablePoint);
        moveablePoint.move();
        System.out.println(moveablePoint);
        moveablePoint.move();
        System.out.println(moveablePoint);
    }
}
