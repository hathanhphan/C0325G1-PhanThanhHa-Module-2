package ss3_array_and_method_in_java.bai_tap;

import ss3_array_and_method_in_java.helpers.ArrayIO;

public class MaxElementInMatrix {
    public static void main(String[] args) {
        int[][] matrix = ArrayIO.input2DMatrixInt(3, 50);
        ArrayIO.output2DMatrixInt(matrix, "Ma trận 2 chiều đã nhập: ");
        int[] findIndex = ArrayIO.maxIn2DMatrixInt(matrix);
        System.out.printf("Phần tử lớn nhất trong mảng là %d tại vị trí [%d][%d]", matrix[findIndex[0]][findIndex[1]], findIndex[0], findIndex[1]);
    }
}
