package case_study_hospital_management.util;

import case_study_hospital_management.common.constants.ConfigurationConstants;
import ss12_java_collection_framework.bai_tap.student_management.util.ConsoleColorUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidatorUtil {
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
                            String.format("%s phải là số nguyên dương!", capitalize(displayName)));
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed(
                        String.format("%s phải là số nguyên dương!", capitalize(displayName)));
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
    public static Integer inputInteger(String prompt, String displayName, int min, int max, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (allowEmpty && input.isEmpty()) {
                return null;
            }
            try {
                int value = Integer.parseInt(input);
                if (value < min || value > max) {
                    ConsoleColorUtil.printlnRed(
                            String.format("Giá trị của %s phải từ %d đến %d.", displayName, min, max));
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed(
                        String.format("%s phải là số nguyên!", capitalize(displayName)));
            }
        }
    }

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

    public static Double inputDouble(String prompt, String displayName, double min, double max, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (allowEmpty && input.isEmpty()) {
                return null;
            }
            try {
                double value = Double.parseDouble(input);
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
    public static String inputVietnamPhoneNumber(String prompt, String displayName, boolean allowEmpty) {
        String phoneRegex = "0[0-9]{9,10}";
        return inputPattern(prompt, displayName, phoneRegex, "Vui lòng nhập số điện thoại bắt đầu bằng số 0 và độ dài từ 10 đến 11 chữ số!", allowEmpty);
    }

    // Viết hoa chữ cái đầu
    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

    // Validate ngày tháng format dd/MM/yyyy, trả về LocalDate
    public static LocalDate inputDate(String prompt, String displayName, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (allowEmpty && input.isEmpty()) {
                return null;
            }

            // Kiểm tra format trước khi parse
            if (!Pattern.matches("\\d{2}/\\d{2}/\\d{4}", input)) {
                ConsoleColorUtil.printlnRed(
                        String.format("%s phải có định dạng dd/MM/yyyy (ví dụ: 15/03/1985)!",
                                capitalize(displayName)));
                continue;
            }

            try {
                LocalDate date = LocalDate.parse(input, ConfigurationConstants.DATE_FORMATTER);

                // Kiểm tra ngày có hợp lệ không (không quá xa trong quá khứ hoặc tương lai)
                LocalDate minDate = LocalDate.of(1900, 1, 1);
                LocalDate maxDate = LocalDate.now().plusYears(100);

                if (date.isBefore(minDate) || date.isAfter(maxDate)) {
                    ConsoleColorUtil.printlnRed(
                            String.format("%s phải trong khoảng từ %s đến %s!",
                                    capitalize(displayName),
                                    minDate.format(ConfigurationConstants.DATE_FORMATTER),
                                    maxDate.format(ConfigurationConstants.DATE_FORMATTER)));
                    continue;
                }

                return date;
            } catch (DateTimeParseException e) {
                ConsoleColorUtil.printlnRed(
                        String.format("%s không hợp lệ! Vui lòng nhập theo định dạng dd/MM/yyyy.",
                                capitalize(displayName)));
            }
        }
    }

    // Validate ngày sinh (phải trong quá khứ và hợp lý)
    public static LocalDate inputBirthDate(String prompt, String displayName, boolean allowEmpty) {
        while (true) {
            LocalDate date = inputDate(prompt, displayName, allowEmpty);

            if (date == null && allowEmpty) {
                return null;
            }

            if (date != null) {
                // Kiểm tra ngày sinh phải trong quá khứ
                if (date.isAfter(LocalDate.now())) {
                    ConsoleColorUtil.printlnRed(
                            String.format("%s không thể là ngày trong tương lai!",
                                    capitalize(displayName)));
                    continue;
                }

                // Kiểm tra tuổi hợp lý (không quá 150 tuổi)
                LocalDate minBirthDate = LocalDate.now().minusYears(150);
                if (date.isBefore(minBirthDate)) {
                    ConsoleColorUtil.printlnRed(
                            String.format("%s không hợp lý (tuổi quá cao)!",
                                    capitalize(displayName)));
                    continue;
                }

                return date;
            }
        }
    }

    // Validate ngày hẹn (phải trong tương lai)
    public static LocalDate inputFutureDate(String prompt, String displayName, boolean allowEmpty) {
        while (true) {
            LocalDate date = inputDate(prompt, displayName, allowEmpty);

            if (date == null && allowEmpty) {
                return null;
            }

            if (date != null) {
                // Kiểm tra ngày hẹn phải từ hôm nay trở đi
                if (date.isBefore(LocalDate.now())) {
                    ConsoleColorUtil.printlnRed(
                            String.format("%s phải từ hôm nay trở đi!",
                                    capitalize(displayName)));
                    continue;
                }

                // Kiểm tra không quá xa trong tương lai (1 năm)
                LocalDate maxDate = LocalDate.now().plusYears(1);
                if (date.isAfter(maxDate)) {
                    ConsoleColorUtil.printlnRed(
                            String.format("%s không được quá %s!",
                                    capitalize(displayName),
                                    maxDate.format(ConfigurationConstants.DATE_FORMATTER)));
                    continue;
                }

                return date;
            }
        }
    }

    // Validate thời gian format HH:mm, trả về LocalTime
    public static LocalTime inputTime(String prompt, String displayName, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (allowEmpty && input.isEmpty()) {
                return null;
            }

            // Kiểm tra format trước khi parse
            if (!Pattern.matches("\\d{2}:\\d{2}", input)) {
                ConsoleColorUtil.printlnRed(
                        String.format("%s phải có định dạng HH:mm (ví dụ: 14:30)!",
                                capitalize(displayName)));
                continue;
            }

            try {
                LocalTime time = LocalTime.parse(input, ConfigurationConstants.TIME_FORMATTER);
                return time;
            } catch (DateTimeParseException e) {
                ConsoleColorUtil.printlnRed(
                        String.format("%s không hợp lệ! Vui lòng nhập theo định dạng HH:mm.",
                                capitalize(displayName)));
            }
        }
    }

    // Validate giờ làm việc (7:00 - 18:00)
    public static LocalTime inputWorkingTime(String prompt, String displayName, boolean allowEmpty) {
        while (true) {
            LocalTime time = inputTime(prompt, displayName, allowEmpty);
            if (time == null && allowEmpty) {
                return null;
            }
            if (time != null) {
                LocalTime startWork = LocalTime.of(7, 0);
                LocalTime endWork = LocalTime.of(18, 0);

                if (time.isBefore(startWork) || time.isAfter(endWork)) {
                    ConsoleColorUtil.printlnRed(
                            String.format("%s phải trong giờ làm việc (07:00 - 18:00)!",
                                    capitalize(displayName)));
                    continue;
                }
                return time;
            }
        }
    }

    // Validate khoảng thời gian (startTime < endTime)
    public static LocalTime inputTimeRange(String prompt, String displayName, LocalTime startTime, boolean allowEmpty) {
        while (true) {
            LocalTime time = inputTime(prompt, displayName, allowEmpty);
            if (time == null && allowEmpty) {
                return null;
            }
            if (time != null && startTime != null) {
                if (time.isBefore(startTime) || time.equals(startTime)) {
                    ConsoleColorUtil.printlnRed(
                            String.format("%s phải sau %s!",
                                    capitalize(displayName),
                                    startTime.format(ConfigurationConstants.TIME_FORMATTER)));
                    continue;
                }
                return time;
            }
            return time;
        }
    }
}
