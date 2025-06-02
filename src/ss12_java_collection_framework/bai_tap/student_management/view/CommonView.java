package ss12_java_collection_framework.bai_tap.student_management.view;

import ss12_java_collection_framework.bai_tap.student_management.common.ViewSelector;
import ss12_java_collection_framework.bai_tap.student_management.util.ConsoleColorUtil;

import java.util.Scanner;

public class CommonView {
    public static Scanner sc = new Scanner(System.in);
    private CommonView() {}
    public static ViewSelector inputFeatureSelection() {
        int choice;
        ViewSelector selector;
        while (true) {
            try {
                System.out.print("Chọn chức năng: ");
                choice = Integer.parseInt(sc.nextLine());
                selector = ViewSelector.fromValue(choice);
                return selector;
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnYellow("Vui lòng nhập 1 số nguyên tương ứng với chức năng.");
            }
        }
    }
}
