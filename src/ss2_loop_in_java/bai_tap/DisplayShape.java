package ss2_loop_in_java.bai_tap;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DisplayShape {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            int yourChoice = inputChoice(sc);
            while (yourChoice != 4) {
                switch (yourChoice) {
                    case 1:
                        printRectangle(sc);
                        break;
                    case 2:
                        printSquareTriangle(sc);
                        break;
                    case 3:
                        printIsoscelesTriangle(sc);
                        break;
                }
                System.out.println();
                yourChoice = inputChoice(sc);
            }
            System.out.println("Đã kết thúc chương trình");
        } catch (InputMismatchException e) {
            System.out.println("Bạn nhập giá trị không phải kiểu số. Kết thúc chương trình.");
        }
    }

    private static int inputChoice(Scanner sc) {
        System.out.println("========= Danh sách tính năng =========");
        System.out.println("1. In hình chữ nhật");
        System.out.println("2. In hình tam giác vuông");
        System.out.println("3. In hình tam giác cân");
        System.out.println("4. Thoát chương trình");
        System.out.println("=======================================");
        System.out.print("Lựa chọn của bạn là: ");
        return sc.nextInt();
    }

    private static void printRectangle(Scanner sc) {
        System.out.print("Nhập chiều dài: ");
        int longSide = sc.nextInt();
        System.out.print("Nhập chiều rộng: ");
        int widthSide = sc.nextInt();
        System.out.println("Kết quả:");
        for (int i = 0; i < widthSide; i++) {
            for (int j = 0; j < longSide; j++) {
                System.out.print(" * ");
            }
            System.out.println();
        }
    }

    private static void printSquareTriangle(Scanner sc) {
        System.out.print("Nhập chiều cao: ");
        int height = sc.nextInt();
        System.out.println("Nhập hướng góc vuông:");
        System.out.println("1. Trên cùng bên phải");
        System.out.println("2. Trên cùng bên trái");
        System.out.println("3. Dưới cùng bên phải");
        System.out.println("4. Dưới cùng bên trái");
        System.out.print("Nhập lựa chọn của bạn: ");
        int yourChoice = sc.nextInt();
        System.out.println("Kết quả:");
        switch (yourChoice) {
            case 1:
                for (int i = height; i >= 1; i--) {
                    for (int j = 1; j <= i; j++) {
                        System.out.print(" * ");
                    }
                    System.out.println();
                }
                break;
            case 2:
                for (int i = height; i >= 1; i--) {
                    for (int j = 1; j <= height - i; j++) {
                        System.out.print("   ");
                    }
                    for (int j = 1; j <= i; j++) {
                        System.out.print(" * ");
                    }
                    System.out.println();
                }
                break;
            case 3:
                for (int i = 1; i <= height; i++) {
                    for (int j = 1; j <= height - i; j++) {
                        System.out.print("   ");
                    }
                    for (int j = 1; j <= i; j++) {
                        System.out.print(" * ");
                    }
                    System.out.println();
                }
                break;
            case 4:
                for (int i = 1; i <= height; i++) {
                    for (int j = 1; j <= i; j++) {
                        System.out.print(" * ");
                    }
                    System.out.println();
                }
                break;
            default:
                System.out.println("Không có lựa chọn phù hợp. Kết thúc chương trình");
        }
    }

    private static void printIsoscelesTriangle(Scanner sc) {
        System.out.print("Nhập chiều cao: ");
        int height = sc.nextInt();
        System.out.println("Kết quả:");
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= height - i; j++) {
                System.out.print("   ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                System.out.print(" * ");
            }
            System.out.println();
        }
    }
}
