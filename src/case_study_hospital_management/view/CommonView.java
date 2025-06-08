package case_study_hospital_management.view;

import case_study_hospital_management.common.constants.ConfigurationConstants;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.util.ConsoleUtil;
import ss12_java_collection_framework.bai_tap.student_management.util.ConsoleColorUtil;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CommonView {
    private static final AppointmentView appointmentView = AppointmentView.getInstance();

    private static final Scanner sc = new Scanner(System.in);

    public static void displayTip() {
        System.out.println("\uD83D\uDCA1 Mẹo: Nhập số tương ứng với chức năng bạn muốn sử dụng");
        System.out.println("=".repeat(ConfigurationConstants.SEPARATION_LENGTH));
    }

    public static int inputFeatureSelection() {
        int choice;
        while (true) {
            try {
                System.out.print("Chọn chức năng: ");
                choice = Integer.parseInt(sc.nextLine());
                return choice;
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed("Vui lòng nhập 1 số nguyên tương ứng với chức năng.");
            }
        }
    }

    public static String inputStringKeyword(String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    public static Boolean confirmDelete() {
        ConsoleUtil.printlnYellow("Bạn có chắc chắn muốn xoá?");
        ConsoleUtil.printlnYellow("1. Xác nhận");
        ConsoleUtil.printlnYellow("2. Huỷ");
        int choice;
        while (true) {
            try {
                System.out.print("Chọn chức năng: ");
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) {
                    return true;
                } else if (choice == 2) {
                    return false;
                } else {
                    ConsoleColorUtil.printlnRed("Vui lòng nhập 1 số nguyên tương ứng với chức năng.");
                }
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed("Vui lòng nhập 1 số nguyên tương ứng với chức năng.");
            }
        }
    }

    public static void displayStatistic(Map<String, Integer> map, String keyHeaderLabel, String valueHeaderLabel, String title) {
        System.out.println();
        System.out.println("=".repeat(title.length() / 2) + title + "=".repeat(title.length() / 2));
        displayStatisticAtTable(map, keyHeaderLabel, valueHeaderLabel);
    }

    public static void displayStatisticAtTable(Map<String, Integer> map, String keyHeaderLabel, String valueHeaderLabel) {
        int maxKeyLength = keyHeaderLabel.length();
        int maxValueLength = valueHeaderLabel.length();
        for (Map.Entry<String, Integer> item : map.entrySet()) {
            maxKeyLength = Math.max(maxKeyLength, item.getKey().length());
            maxValueLength = Math.max(maxValueLength, item.getValue().toString().length());
        }
        // Tạo định dạng cho dòng
        String lineFormat = "+-" + "-".repeat(maxKeyLength) + "-+-" +
                "-".repeat(maxValueLength) + "-+%n";

        // Tạo định dạng cho dữ liệu
        String dataFormat = "| %-" +
                maxKeyLength + "s | %-" +
                maxValueLength + "s |%n";

        // In bảng
        System.out.printf(lineFormat);
        System.out.printf(dataFormat, keyHeaderLabel, valueHeaderLabel);
        System.out.printf(lineFormat);
        for (Map.Entry<String, Integer> item : map.entrySet()) {
            System.out.printf(dataFormat, item.getKey(), item.getValue());
        }
        System.out.printf(lineFormat);
    }

    public static Boolean selectGender(boolean allowEmpty) {
        int choice;
        System.out.println("\uD83D\uDC64 Chọn giới tính:\t1. Nam\t2. Nữ\t3. Khác");
        while (true) {
            try {
                System.out.print("Lựa chọn của bạn: ");
                String input = sc.nextLine();
                if (input.isEmpty() && allowEmpty) {
                    return null;
                }
                choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        return true;
                    case 2:
                        return false;
                    case 3:
                        return null;
                    default:
                        ConsoleColorUtil.printlnRed("Vui lòng nhập 1 số nguyên tương ứng với giới tính.");
                }
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed("Vui lòng nhập 1 số nguyên tương ứng với giới tính.");
            }
        }
    }

    public static void displayContinueAction() {
        System.out.print("Nhấn phím bất kí để tiếp tục...");
        sc.nextLine();
    }

    public static void displayGroupByAppointmentList(Map<String, List<AppointmentEntity>> groupAppointmentsByDate) {
        for(Map.Entry<String, List<AppointmentEntity>> group : groupAppointmentsByDate.entrySet()) {
            ConsoleUtil.printlnBold(group.getKey());
            appointmentView.display(group.getValue(), "");
            System.out.println();
        }
    }
}
