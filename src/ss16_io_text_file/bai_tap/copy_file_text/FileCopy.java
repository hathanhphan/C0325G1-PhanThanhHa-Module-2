package ss16_io_text_file.bai_tap.copy_file_text;

import java.io.*;
import java.util.Scanner;

public class FileCopy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // D:\CodeGym\module2\src\ss16_io_text_file\bai_tap\copy_file_text\source.txt
        // D:\CodeGym\module2\src\ss16_io_text_file\bai_tap\copy_file_text\target.txt
        try {
            // Input validation
            String sourceFile = getValidSourceFile(scanner);
            String targetFile = getValidTargetFile(scanner);
            // Copy operation
            long characterCount = copyFile(sourceFile, targetFile);
            // Display results
            System.out.println("\n✓ File copied successfully!");
            System.out.println("Characters copied: " + characterCount);
            displayFileInfo(sourceFile, targetFile);
            scanner.close();
        } catch (IOException e) {
            System.err.println("Copy failed: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
    private static String getValidSourceFile(Scanner sc) throws FileNotFoundException {
        while (true) {
            System.out.print("Enter source file path: ");
            String sourceFile = sc.nextLine().trim();
            File file = new File(sourceFile);
            if (!file.exists()) {
                System.out.println("ERROR: Source file does not exist!");
                continue;
            }
            if (!file.isFile()) {
                System.out.println("ERROR: Path is not a file!");
                continue;
            }
            if (!file.canRead()) {
                System.out.println("ERROR: Cannot read source file!");
                continue;
            }
            return sourceFile;
        }
    }

    private static String getValidTargetFile(Scanner sc) {
        System.out.print("Enter target file path: ");
        String targetFile = sc.nextLine().trim();
        File file = new File(targetFile);
        if (file.exists()) {
            System.out.print("WARNING: Target file exists. Overwrite? (y/n): ");
            if (!sc.nextLine().trim().equalsIgnoreCase("y")) {
                System.out.println("Copy operation cancelled");
                System.exit(1);
            }
        }
        return targetFile;
    }

    private static long copyFile(String sourceFile, String targetFile) throws IOException {
        long characterCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(targetFile))
        ) {
            int character;
            while ((character = reader.read()) != -1) {
                writer.write(character);
                characterCount++;
            }
        }
        return characterCount;
    }

    private static void displayFileInfo(String sourceFile, String targetFile) {
        try {
            File source = new File(sourceFile);
            File target = new File(targetFile);
            System.out.println("\n==========COPY SUMMARY==========");
            System.out.println("Source: " + sourceFile + " (" + source.length() + " bytes)");
            System.out.println("Target: " + targetFile + " (" + target.length() + " bytes)");
            System.out.println("Copy completed successfully!");
        } catch (Exception e) {
            System.err.println("Cannot display file info: " + e.getMessage());
        }
    }
}
