package case_study_hospital_management.view;

import case_study_hospital_management.common.constants.Constants;
import case_study_hospital_management.util.ConsoleUtil;
import ss12_java_collection_framework.bai_tap.student_management.common.ViewSelector;
import ss12_java_collection_framework.bai_tap.student_management.util.ConsoleColorUtil;

import java.util.Scanner;

public class CommonView {
    private static final Scanner sc = new Scanner(System.in);

    public static void displayTip() {
        System.out.println("\uD83D\uDCA1 Mẹo: Nhập số tương ứng với chức năng bạn muốn sử dụng");
        System.out.println("=".repeat(Constants.SEPARATION_LENGTH));
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
}
