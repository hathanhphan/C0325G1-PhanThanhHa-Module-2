package ss0_more_learn.s15_file_handling;

import java.io.File;
import java.io.IOException;

public class FileCreation {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws IOException {
            File file = new File("D:\\CodeGym\\module2\\src\\ss0_more_learn\\s15_file_handling\\text.txt");
        file.createNewFile();
        System.out.println("File created");
    }
}
