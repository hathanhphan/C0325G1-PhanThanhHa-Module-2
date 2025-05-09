package ss3_array_and_method_in_java.bai_tap;

import ss3_array_and_method_in_java.helpers.ArrayIO;

import java.util.Scanner;

public class CalculateSumColumnInMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float[][] matrix = ArrayIO.input2DMatrixFloat(3, 50);
        ArrayIO.output2DMatrixFloat(matrix, "Ma trận 2 chiều đã nhập: ");
        System.out.printf("Nhập cột cần tính tổng (từ 0 đến %d): ", matrix[0].length - 1);
        int col = sc.nextInt();
        double sum = ArrayIO.sumColIn2DMatrixFloat(matrix, col);
        if (sum == Double.MIN_VALUE) {
            System.out.printf("Không có cột %d", col);
        } else {
            System.out.printf("Tổng các phần tử ở cột %d là %.2f", col, sum);
        }
    }
}
