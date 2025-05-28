package ss17_io_binary_file_serialization.bai_tap.product_management.util;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidator {
    private static final Scanner scanner = new Scanner(System.in);

    // Validate số nguyên dương, độ dài tối đa (cho phép null)
    public static Long inputPositiveLong(String prompt, String displayName, int maxLength, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (allowEmpty && input.isEmpty()) {
                return null;
            }
            if (input.length() > maxLength) {
                ConsoleColorUtil.printlnRed(
                        String.format("Độ dài tối đa của %s là %d ký tự.", displayName, maxLength));
                continue;
            }
            try {
                long value = Long.parseLong(input);
                if (value < 0) {
                    ConsoleColorUtil.printlnRed(
                            String.format("%s phải là số dương!", capitalize(displayName)));
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed(
                        String.format("%s phải là số!", capitalize(displayName)));
            }
        }
    }

    // Validate chuỗi theo min/max, cho phép null
    public static String inputString(String prompt, String displayName, int minLength, int maxLength, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (allowEmpty && input.isEmpty()) {
                return null;
            }
            if (input.length() < minLength || input.length() > maxLength) {
                ConsoleColorUtil.printlnRed(
                        String.format("Độ dài của %s phải từ %d đến %d ký tự.", displayName, minLength, maxLength));
                continue;
            }
            return input;
        }
    }

    // Validate số thực theo min/max, cho phép null
    public static Float inputFloat(String prompt, String displayName, float min, float max, boolean allowEmpty) {
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
                            String.format("Giá trị của %s phải từ %.2f đến %.2f.", displayName, min, max));
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed(
                        String.format("%s phải là số thực!", capitalize(displayName)));
            }
        }
    }

    public static BigDecimal inputBigDecimal(String prompt, String displayName, BigDecimal min, BigDecimal max, boolean allowEmpty) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (allowEmpty && input.isEmpty()) {
                return null;
            }
            try {
                BigDecimal value = new BigDecimal(input);
                if ((min != null && value.compareTo(min) < 0) || (max != null && value.compareTo(max) > 0)) {
                    assert min != null;
                    ConsoleColorUtil.printlnRed(
                            String.format("Giá trị của %s phải từ %s đến %s.", displayName, min.toPlainString(), max.toPlainString())
                    );
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed(
                        String.format("%s phải là số thực!", capitalize(displayName))
                );
            }
        }
    }

    // Validate theo regex, cho phép null
    public static String inputPattern(String prompt, String displayName, String pattern, String errorMsg, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (allowEmpty && input.isEmpty()) {
                return null;
            }
            if (!Pattern.matches(pattern, input)) {
                ConsoleColorUtil.printlnRed(
                        String.format("%s: %s", capitalize(displayName), errorMsg));
                continue;
            }
            return input;
        }
    }

    // Validate email, cho phép null
    public static String inputEmail(String prompt, String displayName, boolean allowEmpty) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return inputPattern(prompt, displayName, emailRegex, "Email không hợp lệ!", allowEmpty);
    }

    // Validate số điện thoại (Việt Nam), cho phép null
    public static String inputPhone(String prompt, String displayName, boolean allowEmpty) {
        String phoneRegex = "0[0-9]{9,10}";
        return inputPattern(prompt, displayName, phoneRegex, "Số điện thoại không hợp lệ!", allowEmpty);
    }

    // Viết hoa chữ cái đầu
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}
