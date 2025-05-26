package ss16_io_text_file.bai_tap.read_csv_file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReader {
    public static void main(String[] args) {
        String pathName = "D:\\CodeGym\\module2\\src\\ss16_io_text_file\\bai_tap\\read_csv_file\\countries.csv";
        List<Country> countries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String countryCode = data[1];
                String countryName = data[2];
                countries.add(new Country(id, countryCode, countryName));
            }
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
        countries.forEach(System.out::println);
    }
}
