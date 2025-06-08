package case_study_hospital_management.util;

import case_study_hospital_management.common.constants.ConfigurationConstants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil{
    private CSVUtil() {}

    public static List<String> readCSVFile(String fileName) {
        List<String> lines = new ArrayList<>();
        String filePath = ConfigurationConstants.DATA_FOLDER + fileName;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            ConsoleUtil.printlnRed("✗ ERROR: " + e.getMessage());
            System.exit(1);
        }
        return lines;
    }

    public static <T> boolean writeCSVFile(String fileName, List<T> objects) {
        String filePath = ConfigurationConstants.DATA_FOLDER + fileName;
        File dataDir = new File(ConfigurationConstants.DATA_FOLDER);
        if(!dataDir.exists()) {
            dataDir.mkdir();
        }
        List<String> lines = new ArrayList<>();
        for (T obj : objects) {
            lines.add(obj.toString());
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            ConsoleUtil.printlnRed("✗ ERROR: " + e.getMessage());
            return false;
        }
    }

    public static String[] parseCsvLine(String csvLine) {
        return csvLine.split(",", -1);
    }

    public static String escapeCSV(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
