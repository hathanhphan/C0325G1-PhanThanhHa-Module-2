package ss17_io_binary_file_serialization.bai_tap.product_management.util;

import ss17_io_binary_file_serialization.bai_tap.product_management.entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"CallToPrintStackTrace", "unchecked"})
public class IOBinaryFile {
    private IOBinaryFile() {}

    public static void writeToFile(String path, List<Product> productList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(productList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<Product> readFromFile(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (List<Product>) ois.readObject();
        } catch (FileNotFoundException e) {
            ConsoleColorUtil.printlnRed("Không tìm thấy database!");
            System.exit(1);
        } catch (Exception e) {
            ConsoleColorUtil.printlnYellow("Database chưa có bản ghi nào!");
        }
        return new ArrayList<>();
    }
}
