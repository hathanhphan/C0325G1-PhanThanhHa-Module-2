package ss0_more_learn.s15_file_handling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        String path = "D:\\CodeGym\\module2\\src\\ss0_more_learn\\s15_file_handling\\text.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
