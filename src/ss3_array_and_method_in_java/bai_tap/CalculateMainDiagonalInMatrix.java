package ss3_array_and_method_in_java.bai_tap;

import ss3_array_and_method_in_java.helpers.ArrayIO;

import java.util.Scanner;

public class CalculateMainDiagonalInMatrix {
    public static void main(String[] args) {
        float[][] matrix = ArrayIO.input2DSquareMatrixFloat(3, 50);
        ArrayIO.output2DMatrixFloat(matrix, "Ma trận vuông đã nhập: ");
        double sum = ArrayIO.sumMainDiagonalIn2DMatrixFloat(matrix);
        System.out.printf("Tổng các phần tử trên đường chéo chính là %.2f", sum);
    }
}
