package ss19_string_and_regex.bai_tap.validate_classname;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassNameValidator {
    /*
    * - Tên một lớp học hợp lệ cần đạt các yêu cầu sau:
            Bắt đầu bằng một ký tự chữ hoa C hoặc A hoặc P
            Không chứa các ký tự đặc biệt
            Theo sau ký tự bắt đầu là 4 ký tự số
            Kết thúc phải là 1 trong những ký tự chữ hoa sau: G, H, I, K
        - Ví dụ:
         - Tên lớp hợp lệ: C0223G, A0323K
         - Tên lớp không hợp lệ: M0318G, P0323A
    * */

    private static final String CLASS_NAME_PATTERN = "^[CAP][0-9]{4}[GHIK]$";
    private static final Pattern pattern = Pattern.compile(CLASS_NAME_PATTERN);

    public static boolean isValidClassName(String className) {
        if (className == null) {
            return false;
        }
        return pattern.matcher(className).matches();
    }

    public static void main(String[] args) {
        // Test cases hợp lệ
        String[] validNames = {"C0223G", "A0323K", "P1234H", "C9876I"};

        // Test cases không hợp lệ
        String[] invalidNames = {"M0318G", "P0323A", "c0223g", "C023G", "C02234G", "C0223", "C0223X"};

        System.out.println("=== Tên lớp hợp lệ ===");
        for (String name : validNames) {
            System.out.println(name + ": " + isValidClassName(name));
        }

        System.out.println("\n=== Tên lớp không hợp lệ ===");
        for (String name : invalidNames) {
            System.out.println(name + ": " + isValidClassName(name));
        }
    }
}
