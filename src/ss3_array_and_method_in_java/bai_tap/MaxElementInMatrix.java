package ss3_array_and_method_in_java.bai_tap;

import ss3_array_and_method_in_java.helpers.ArrayIO;

public class MaxElementInMatrix {
    public static void main(String[] args) {
        float[][] matrix = ArrayIO.input2DMatrixFloat(3, 50);
        ArrayIO.output2DMatrixFloat(matrix, "Ma trận 2 chiều đã nhập: ");
        int[] findIndex = ArrayIO.maxIn2DMatrixFloat(matrix);
        System.out.printf("Phần tử lớn nhất trong mảng là %.2f tại vị trí [%d][%d]", matrix[findIndex[0]][findIndex[1]], findIndex[0], findIndex[1]);
    }
}
