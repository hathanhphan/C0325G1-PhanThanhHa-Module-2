package ss0_more_learn.s15_file_handling;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteOntoFile {
    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        String path = "D:\\CodeGym\\module2\\src\\ss0_more_learn\\s15_file_handling\\text.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write("Hẹ hẹ hẹ");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        }
    }
}
