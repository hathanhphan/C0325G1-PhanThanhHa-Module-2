package ss0_more_learn.s15_file_handling;

import java.io.File;

public class DirectoryCreation {
    public static void main(String[] args) {
        File file = new File("D:\\CodeGym\\module2\\src\\ss0_more_learn\\s15_file_handling\\new_folder");
        if (file.mkdir()) {
            System.out.println("Folder created");
        } else {
            System.out.println("Folder already existed");
        }
    }
}
