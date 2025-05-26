package ss16_io_text_file.thuc_hanh.sum_in_file_text;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // D:\CodeGym\module2\src\ss16_io_text_file\thuc_hanh\numbers.txt.txt
        System.out.println("Nhập đường dẫn file:");
        Scanner sc = new Scanner(System.in);
        String filePath = sc.nextLine();
        ReadFileExample readFileExample = new ReadFileExample();
        readFileExample.readFileText(filePath);
    }
}
