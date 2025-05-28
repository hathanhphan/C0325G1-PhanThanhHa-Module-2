package ss17_io_binary_file_serialization.bai_tap.binary_file_copy;

import java.io.*;
import java.util.Scanner;

public class FileCopyProgram {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== CHƯƠNG TRÌNH SAO CHÉP FILE NHỊ PHÂN ===");
        while (true) {
            try {
                System.out.println("\n1. Sao chép file");
                System.out.println("2. Thoát");
                System.out.print("Chọn chức năng: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        copyFile();
                        break;
                    case 2:
                        System.out.println("Kết thúc chương trình...");
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    public static void copyFile() {
        System.out.print("Nhập đường dẫn file nguồn: ");
        String sourcePath = scanner.nextLine().trim();

        System.out.print("Nhập đường dẫn file đích: ");
        String targetPath = scanner.nextLine().trim();

        File sourceFile = new File(sourcePath);
        if (!sourceFile.exists()) {
            System.err.println("❌ Lỗi: File nguồn không tồn tại!");
            return;
        }

        if (!sourceFile.isFile()) {
            System.err.println("❌ Lỗi: Đường dẫn nguồn không phải là file!");
            return;
        }

        File targetFile = new File(targetPath);
        if (targetFile.exists()) {
            System.out.print("⚠️ Cảnh báo: File đích đã tồn tại! Bạn có muốn ghi đè? (y/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            if (!confirm.equals("y") && !confirm.equals("yes")) {
                System.out.println("Hủy bỏ sao chép file.");
                return;
            }
        }

        File parentDir = targetFile.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try {
            long bytesCopied = performCopy(sourceFile, targetFile);
            System.out.println("✅ Sao chép thành công!");
            System.out.println("📊 Số byte đã sao chép: " + bytesCopied + " bytes");
            System.out.println("📁 File đích: " + targetFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi sao chép file: " + e.getMessage());
        }
    }

    private static long performCopy(File sourceFile, File targetFile) throws IOException {
        long totalBytes = 0;
        long lastProgress = 0;
        long fileSize = sourceFile.length();
        long progressInterval = Math.max(fileSize / 25, 1024 * 1024);

        try (FileInputStream fis = new FileInputStream(sourceFile);
             BufferedInputStream bis = new BufferedInputStream(fis);
             FileOutputStream fos = new FileOutputStream(targetFile);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            byte[] buffer = new byte[8192]; // Buffer 8KB
            int bytesRead;

            System.out.print("Đang sao chép");

            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
                totalBytes += bytesRead;

                // Hiển thị tiến trình
                if (totalBytes - lastProgress >= progressInterval) {
                    System.out.print(".");
                    lastProgress = totalBytes;
                }
            }

            System.out.println();
        }

        return totalBytes;
    }
}
