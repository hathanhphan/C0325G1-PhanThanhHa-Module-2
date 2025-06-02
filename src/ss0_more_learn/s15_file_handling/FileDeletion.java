package ss0_more_learn.s15_file_handling;

import java.io.File;

public class FileDeletion {
    public static void main(String[] args) {
        String path = "D:\\CodeGym\\module2\\src\\ss0_more_learn\\s15_file_handling\\delete.txt";
        File file = new File(path);
        if (file.delete()) {
            System.out.println("File deleted");
        } else {
            System.out.println("Something went wrong");
        }
    }
}
