package ss0_more_learn.s15_file_handling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerReadFile {
    public static void main(String[] args) throws FileNotFoundException {
        String path = "D:\\CodeGym\\module2\\src\\ss0_more_learn\\s15_file_handling\\text.txt";
        try (Scanner sc = new Scanner(new File(path))) {
            String line;
            while (sc.hasNext()) {
                line = sc.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
