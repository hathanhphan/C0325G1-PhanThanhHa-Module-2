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
    public static int[] inputInt(int min, int max, String message) {
        Scanner sc = new Scanner(System.in);
        int[] array;
        int size = inputNumOfElement(min, max, message);
        array = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.print("Nhập phần tử thứ " + (i + 1) + ": ");
            array[i] = sc.nextInt();
        }
        return array;
    }

    public static int[] inputInt(int min, int max) {
        return inputInt(min, max, "Nhập số lượng phần tử của mảng: ");
    }

    public static int[][] input2DMatrixInt(int min, int max, String messageRow, String messageCol) {
        Scanner sc = new Scanner(System.in);
        int[][] matrix;
        int row = inputNumOfElement(min, max, messageRow);
        int col = inputNumOfElement(min, max, messageCol);
        matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.printf("Nhập phần tử [%d][%d]: ", i, j);
                matrix[i][j] = sc.nextInt();
            }
        }
        return matrix;
    }

    public static int[][] input2DMatrixInt(int min, int max) {
        return input2DMatrixInt(min, max, "Nhập số hàng: ", "Nhập số cột: ");
    }

    public static void outputInt(int[] array, String titleMessage) {
        if (!titleMessage.isEmpty()) {
            System.out.printf("%-20s%s", titleMessage, "");
        }
        for (int i : array) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    public static void output2DMatrixInt(int[][] matrix, String titleMessage) {
        if (!titleMessage.isEmpty()) {
            System.out.printf("%-20s%s\n", titleMessage, "");
        }
        for (int[] i : matrix) {
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
        System.out.println();
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

    public static int[] maxIn2DMatrixInt(int[][] matrix) {
        int max = matrix[0][0];
        int indexRow = 0;
        int indexCol = 0;
        for (int i = 1; i < matrix.length; i++) {
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
}
