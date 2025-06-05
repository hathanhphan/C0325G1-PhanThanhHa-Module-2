package case_study_hospital_management.controller;

import case_study_hospital_management.common.constants.MainMenuSelection;
import case_study_hospital_management.view.CommonView;
import case_study_hospital_management.view.HospitalView;
import ss17_io_binary_file_serialization.bai_tap.product_management.util.ConsoleColorUtil;

public class HospitalController {
    private static final HospitalView hospitalView = HospitalView.getInstance();

    private static final PatientController patientController = PatientController.getInstance();

    public static void main(String[] args) {
        displayMenu();
    }

    public static void displayMenu() {
        int choice;
        boolean shouldShowMenu = true;
        while (true) {
            if (shouldShowMenu) {
                hospitalView.displayMenu();
            }
            choice = CommonView.inputFeatureSelection();
            shouldShowMenu = true;
            switch (choice) {
                case MainMenuSelection.PATIENT_MANAGEMENT:
                    patientController.displayMenu();
                    break;
                case MainMenuSelection.DOCTOR_MANAGEMENT:
                    System.out.println();
                    break;
                case MainMenuSelection.APPOINTMENT_MANAGEMENT:
                    System.out.println("Hehe");
                    break;
                case MainMenuSelection.EXIT:
                    ConsoleColorUtil.printlnYellow("Kết thúc chương trình...");
                    return;
                default:
                    shouldShowMenu = false;
                    ConsoleColorUtil.printlnYellow("Không có tính năng phù hợp. Vui lòng chọn lại.");
            }
        }
    }
}
