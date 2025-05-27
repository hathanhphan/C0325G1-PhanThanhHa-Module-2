package ss12_java_collection_framework.bai_tap.student_management.util;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidator {
    private static final Scanner scanner = new Scanner(System.in);

    // Validate số nguyên dương, độ dài tối đa (cho phép null)
    public static Long inputPositiveLong(String prompt, String fieldName, int maxLength, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (allowEmpty && input.isEmpty()) {
                return null;
            }
            if (input.length() > maxLength) {
                ConsoleColorUtil.printlnRed(
                        String.format("Độ dài tối đa của %s là %d ký tự.", fieldName, maxLength));
                continue;
            }
            try {
                long value = Long.parseLong(input);
                if (value < 0) {
                    ConsoleColorUtil.printlnRed(
                            String.format("%s phải là số dương!", capitalize(fieldName)));
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed(
                        String.format("%s phải là số!", capitalize(fieldName)));
            }
        }
    }

    // Validate chuỗi theo min/max, cho phép null
    public static String inputString(String prompt, String fieldName, int minLength, int maxLength, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (allowEmpty && input.isEmpty()) {
                return null;
            }
            if (input.length() < minLength || input.length() > maxLength) {
                ConsoleColorUtil.printlnRed(
                        String.format("Độ dài của %s phải từ %d đến %d ký tự.", fieldName, minLength, maxLength));
                continue;
            }
            return input;
        }
    }

    // Validate số thực theo min/max, cho phép null
    public static Float inputFloat(String prompt, String fieldName, float min, float max, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (allowEmpty && input.isEmpty()) {
                return null;
            }
            try {
                float value = Float.parseFloat(input);
                if (value < min || value > max) {
                    ConsoleColorUtil.printlnRed(
                            String.format("Giá trị của %s phải từ %.2f đến %.2f.", fieldName, min, max));
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed(
                        String.format("%s phải là số thực!", capitalize(fieldName)));
            }
        }
    }

    // Validate theo regex, cho phép null
    public static String inputPattern(
            String prompt, String fieldName, String pattern, String errorMsg, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (allowEmpty && input.isEmpty()) {
                return null;
            }
            if (!Pattern.matches(pattern, input)) {
                ConsoleColorUtil.printlnRed(
                        String.format("%s: %s", capitalize(fieldName), errorMsg));
                continue;
            }
            return input;
        }
    }

    // Validate email, cho phép null
    public static String inputEmail(String prompt, String fieldName, boolean allowEmpty) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return inputPattern(prompt, fieldName, emailRegex, "Email không hợp lệ!", allowEmpty);
    }

    // Validate số điện thoại (Việt Nam), cho phép null
    public static String inputPhone(String prompt, String fieldName, boolean allowEmpty) {
        String phoneRegex = "0[0-9]{9,10}";
        return inputPattern(prompt, fieldName, phoneRegex, "Số điện thoại không hợp lệ!", allowEmpty);
    }

    // Viết hoa chữ cái đầu
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}
