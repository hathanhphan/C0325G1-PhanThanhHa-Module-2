package ss16_io_text_file.thuc_hanh.max_in_file_text;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ReadAndWriteFile readAndWriteFile = new ReadAndWriteFile();
        List<Integer> numbers = readAndWriteFile.readFile("D:\\CodeGym\\module2\\src\\ss16_io_text_file\\thuc_hanh\\numbers.txt");
        int maxValue = FindMaxValue.findMax(numbers);
        readAndWriteFile.writeFile("D:\\CodeGym\\module2\\src\\ss16_io_text_file\\thuc_hanh\\max_in_file_text\\result.txt", maxValue);
    }
}
