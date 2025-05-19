package ss11_dsa.bai_tap.convert_decimal_to_binary;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class DecimalToBinaryConvertor {
    public static void main(String[] args) {
        Stack<Integer> binaryReverseStack = new Stack<>();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Nhập số nguyên không âm cần chuyển: ");
            int number = sc.nextInt();
            while (number < 0) {
                System.out.println("Bạn 1 nhập giá trị là một số nguyên không âm!");
                System.out.print("Nhập số nguyên không âm cần chuyển: ");
                number = sc.nextInt();
            }
            if (number == 0) {
                binaryReverseStack.push(0);
            } else {
                while (number != 0) {
                    int remainder = number % 2;
                    binaryReverseStack.push(remainder);
                    number = number / 2;
                }
            }
            StringBuilder binaryString = new StringBuilder();
            while (!binaryReverseStack.isEmpty()) {
                binaryString.append(binaryReverseStack.pop());
            }
            System.out.printf("Kết quả chuyển đố số %d sang hệ nhị phân là: %s", number, binaryString);
        } catch (InputMismatchException e) {
            System.out.println("Bạn nhập giá trị không phải kiểu số. Kết thúc chương trình.");
        }
    }
}
