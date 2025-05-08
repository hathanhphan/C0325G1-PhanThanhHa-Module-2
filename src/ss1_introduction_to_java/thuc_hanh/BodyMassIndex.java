package ss1_introduction_to_java.thuc_hanh;

import java.util.Scanner;

public class BodyMassIndex {
    public static void main(String[] args) {
        double weight;
        double height;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập chiều cao: ");
        height = sc.nextDouble();
        System.out.print("Nhập cân nặng: ");
        weight = sc.nextDouble();
        double bmi = weight / Math.pow(height, 2);
        System.out.printf("Chỉ số của bạn là: %.2f\n", bmi);
        if (bmi < 18.5) {
            System.out.println("Tình trạng: Thiếu cân");
        } else if (bmi < 25) {
            System.out.println("Tình trạng: Bình thường");
        } else if (bmi < 30) {
            System.out.println("Tình trạng: Thừa cân");
        } else {
            System.out.println("Tình trạng: Béo phì");
        }
    }
}
