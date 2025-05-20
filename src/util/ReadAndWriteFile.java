package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteFile {
    public static List<String> readFileCSV(String path, boolean hasHeader) {
        File file = new File(path);
        List<String> lines = new ArrayList<>();
        try (FileReader fr = new FileReader(file);
            BufferedReader bf = new BufferedReader(fr)) {
            String line;
            if (hasHeader) {
                bf.readLine();
            }
            while ((line = bf.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void writeFileCSV(String path, List<String> stringList, boolean append) {
        File file = new File(path);
        try (FileWriter fw = new FileWriter(file, append);
            BufferedWriter bw = new BufferedWriter(fw)) {
            for (String line : stringList) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}