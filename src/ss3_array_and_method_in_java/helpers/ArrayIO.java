package ss3_array_and_method_in_java.helpers;

import java.util.Scanner;

public class ArrayIO {
    private static int inputNumOfElement(int min, int max, String message) {
        Scanner sc = new Scanner(System.in);
        int number;
        do {
            System.out.print(message);
            number = sc.nextInt();
            if (number < min) {
                System.out.printf("Số lượng không được nhỏ hơn %d\n", min);
            } else if (number > max) {
                System.out.printf("Số lượng không được vượt quá %d\n", max);
            }
        } while (number < min || number > max);
        return number;
    }
    public static int[] inputInt(int min, int max, String message, String subMessage) {
        Scanner sc = new Scanner(System.in);
        int[] array;
        int size = inputNumOfElement(min, max, message);
        array = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.print(subMessage + (i + 1) + ": ");
            array[i] = sc.nextInt();
        }
        return array;
    }

    public static int[] inputInt(int min, int max) {
        return inputInt(min, max, "Nhập số lượng phần tử của mảng: ", "Nhập phần tử thứ ");
    }

    public static float[][] input2DMatrixFloat(int min, int max, String messageRow, String messageCol, boolean isSquare) {
        Scanner sc = new Scanner(System.in);
        float[][] matrix;
        int row = inputNumOfElement(min, max, messageRow);
        int col;
        if (isSquare) {
            col = row;
        } else {
            col = inputNumOfElement(min, max, messageCol);
        }
        matrix = new float[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.printf("Nhập phần tử [%d][%d]: ", i, j);
                matrix[i][j] = sc.nextFloat();
            }
        }
        return matrix;
    }

    public static float[][] input2DMatrixFloat(int min, int max) {
        return input2DMatrixFloat(min, max, "Nhập số hàng: ", "Nhập số cột: ", false);
    }

    public static float[][] input2DSquareMatrixFloat(int min, int max) {
        return input2DMatrixFloat(min, max, "Nhập số hàng = số cột: ", "", true);
    }

    public static void outputInt(int[] array, String titleMessage) {
        if (!titleMessage.isEmpty()) {
            System.out.printf("%-20s%s", titleMessage, "");
        }
        for (int i : array) {
            System.out.printf(i + "\t");
        }
        System.out.println();
    }

    public static void output2DMatrixFloat(float[][] matrix, String titleMessage) {
        if (!titleMessage.isEmpty()) {
            System.out.printf("%-20s%s\n", titleMessage, "");
        }
        for (float[] i : matrix) {
            for (float j : i) {
                System.out.printf("%-10.2f", j);
            }
            System.out.println();
        }
    }

    public static int maxInt(int[] array) {
        int max = array[0];
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                index = i;
            }
        }
        return index;
    }

    public static int minInt(int[] array) {
        int min = array[0];
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
                index = i;
            }
        }
        return index;
    }

    public static int[] removeInt(int[] array, int number) {
        int findIndex = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                findIndex = i;
            }
        }
        if (findIndex != -1) {
            int[] newArray = new int[array.length - 1];
            System.arraycopy(array, 0, newArray, 0, findIndex);
            System.arraycopy(array, findIndex + 1, newArray, findIndex, array.length - 1 - findIndex);
            return newArray;
        }
        return null;
    }

    public static int[] insertInt(int[] array, int number, int position) {
        if (position < 0 || position > array.length) {
            return null;
        }
        int[] newArray = new int[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, position);
        newArray[position] = number;
        System.arraycopy(array, position, newArray, position + 1, array.length - position);
        return newArray;
    }

    public static int[] mergeInt(int[] array1, int[] array2) {
        int[] mergeArray = new int[array1.length + array2.length];
        System.arraycopy(array1, 0, mergeArray, 0, array1.length);
        System.arraycopy(array2, 0, mergeArray, array1.length, array2.length);
        return mergeArray;
    }

    public static int[] maxIn2DMatrixFloat(float[][] matrix) {
        float max = matrix[0][0];
        int indexRow = 0;
        int indexCol = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    indexRow = i;
                    indexCol = j;
                }
            }
        }
        return new int[]{ indexRow, indexCol };
    }

    public static double sumColIn2DMatrixFloat(float[][] matrix, int col) {
        double sum = 0;
        if (col < 0 || col >= matrix[0].length) {
            return Double.MIN_VALUE;
        }
        for (float[] element : matrix) {
            sum += element[col];
        }
        return sum;
    }

    public static double sumMainDiagonalIn2DMatrixFloat(float[][] matrix) {
        double sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
        }
        return sum;
    }
}
