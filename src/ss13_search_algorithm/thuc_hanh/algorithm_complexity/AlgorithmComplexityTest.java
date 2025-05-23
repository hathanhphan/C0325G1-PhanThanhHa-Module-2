package ss13_search_algorithm.thuc_hanh.algorithm_complexity;

import java.util.Scanner;

public class AlgorithmComplexityTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String inputString = scanner.nextLine();
        int[] frequentChar = new int[255];                          //{1}
        for (int i = 0; i < inputString.length(); i++) {            //{2}
            int ascii = inputString.charAt(i);                      //{3}
            frequentChar[ascii] += 1;                               //{4}
        }
        int max = 0;                                                //{5}
        char character = (char) 255; /* empty character */          //{6}
        for (int j = 0; j < 255; j++) {                             //{7}
            if (frequentChar[j] > max) {                            //{8}
                max = frequentChar[j];                              //{9}
                character = (char) j;                               //{10}
            }
        }
        System.out.println("The most appearing letter is '" + character + "' with a frequency of " + max + " times");
        /*
        * {1} {3} {4} {5} {6} {8} {9} {10} mỗi lệnh đều tốn O(1) thời gian
        * {2} Vòng lặp n lần sẽ tốn n thời gian
        * {7} Vòng lặp 255 lần sẽ tốn 255 thời gian
        * Sẽ có 2 trường hợp:
        * - TH1: n <= 255 => Độ phức tạp của thuật toán = thời gian của {7} = O(255)
        * - TH2: n > 255 => Độ phức tạp của thuật toán = thời gian của {2} = O(n)
        * */
    }
}
