package case_study_hospital_management.view;

import case_study_hospital_management.common.constants.ConfigurationConstants;
import case_study_hospital_management.util.ConsoleUtil;

public class HospitalView {
    private static HospitalView instance;
    private HospitalView() {
    }

    public static synchronized HospitalView getInstance() {
        if (instance == null) {
            instance = new HospitalView();
        }
        return instance;
    }

    public void displayMenu() {
        System.out.println();
        System.out.println("=".repeat(ConfigurationConstants.SEPARATION_LENGTH));
        ConsoleUtil.printlnBold("\uD83C\uDFE5 HỆ THỐNG QUẢN LÝ BỆNH VIỆN MEDCARE");
        System.out.println("\uD83D\uDC65 1. QUẢN LÝ BỆNH NHÂN");
        System.out.println("\uD83D\uDC68\u200D⚕\uFE0F 2. QUẢN LÝ BÁC SĨ");
        System.out.println("\uD83D\uDCC5 3. QUẢN LÝ LỊCH HẸN");
        System.out.println("\uD83D\uDEAA 0. THOÁT CHƯƠNG TRÌNH");
        System.out.println("=".repeat(ConfigurationConstants.SEPARATION_LENGTH));
        CommonView.displayTip();
    }
}
