package case_study_hospital_management.controller;

import case_study_hospital_management.common.constants.PatientMenuSelection;
import case_study_hospital_management.entity.PatientEntity;
import case_study_hospital_management.service.PatientService;
import case_study_hospital_management.service.impl.PatientServiceImpl;
import case_study_hospital_management.util.ConsoleUtil;
import case_study_hospital_management.view.CommonView;
import case_study_hospital_management.view.PatientView;

import java.util.List;
import java.util.Scanner;

public class PatientController {
    private static final PatientService patientService = PatientServiceImpl.getInstance();
    private static final PatientView patientView = PatientView.getInstance();
    private static final Scanner sc = new Scanner(System.in);
    private static PatientController instance;
    private PatientController() {}

    public static synchronized PatientController getInstance() {
        if (instance == null) {
            instance = new PatientController();
        }
        return instance;
    }

    public void displayMenu() {
        int choice;
        boolean shouldShowMenu = true;
        while (true) {
            if (shouldShowMenu) {
                patientView.displayMenu();
            }
            choice = CommonView.inputFeatureSelection();
            shouldShowMenu = true;
            switch (choice) {
                case PatientMenuSelection.REGISTER_PATIENT:
                    registerPatient();
                    break;
                case PatientMenuSelection.SEARCH_PATIENT:
                    searchPatient();
                    break;
                case PatientMenuSelection.LIST_PATIENT:
                    displayPatientList();
                    break;
                case PatientMenuSelection.UPDATE_PATIENT:
                    updatePatient();
                    break;
                case PatientMenuSelection.DELETE_PATIENT:
                    deletePatient();
                    break;
                case PatientMenuSelection.STATISTIC_PATIENT:
                    System.out.println("Đang update chức năng thống kê.....");
                    break;
                case PatientMenuSelection.RETURN:
                    return;
                default:
                    shouldShowMenu = false;
                    ConsoleUtil.printlnYellow("Không có tính năng phù hợp. Vui lòng chọn lại.");
            }
            System.out.print("Nhấn phím bất kí để tiếp tục...");
            sc.nextLine();
        }
    }

    private void searchPatient() {
        int choice;
        patientView.displaySearchMenu();
        while (true) {
            choice = CommonView.inputFeatureSelection();
            switch (choice) {
                case PatientMenuSelection.SEARCH_PATIENT_BY_ID:
                    searchPatientById();
                    return;
                case PatientMenuSelection.SEARCH_PATIENT_BY_NAME:
                    searchPatientByName();
                    return;
                case PatientMenuSelection.SEARCH_PATIENT_BY_PHONE_NUMBER:
                    searchPatientByPhoneNumber();
                    return;
                case PatientMenuSelection.RETURN:
                    return;
                default:
                    ConsoleUtil.printlnYellow("Không có tính năng phù hợp. Vui lòng chọn lại.");
            }
        }
    }

    private void searchPatientByPhoneNumber() {
        List<PatientEntity> foundPatients;
        String phoneNumber = CommonView.inputStringKeyword("Nhập số điện thoại bệnh nhân cần tìm: ");
        foundPatients = patientService.findByPhoneNumber(phoneNumber);
        if (!foundPatients.isEmpty()) {
            ConsoleUtil.printlnGreen("Tìm thấy " + foundPatients.size() + " bệnh nhân số điện thoại có chứa \"" + phoneNumber + "\"");
            patientView.display(foundPatients);
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy bệnh nhân nào mà thoại có chứa \"" + phoneNumber + "\"");
        }
    }

    private void searchPatientByName() {
        List<PatientEntity> foundPatients;
        String name = CommonView.inputStringKeyword("Nhập tên bệnh nhân cần tìm: ");
        foundPatients = patientService.findByName(name);
        if (!foundPatients.isEmpty()) {
            ConsoleUtil.printlnGreen("Tìm thấy " + foundPatients.size() + " bệnh nhân tên có chứa \"" + name + "\"");
            patientView.display(foundPatients);
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy bệnh nhân nào mà tên có chứa \"" + name + "\"");
        }
    }

    private void searchPatientById() {
        String id = CommonView.inputStringKeyword("Nhập mã bệnh nhân cần tìm: ");
        PatientEntity patient = patientService.findById(id);
        if (patient != null) {
            ConsoleUtil.printlnGreen("Tìm thấy bệnh nhân có mã tương ứng.");
            patientView.showDetail(patient);
            displayDetailMenu(patient);
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy bệnh nhân nào có mã " + id);
        }
    }

    private void displayDetailMenu(PatientEntity patient) {
        int choice;
        patientView.displayDetailMenu();
        while (true) {
            choice = CommonView.inputFeatureSelection();
            switch (choice) {
                case PatientMenuSelection.UPDATE_PATIENT_IN_DETAIL:
                    updatePatient(patient);
                    return;
                case PatientMenuSelection.DELETE_PATIENT_IN_DETAIL:
                    deletePatient(patient);
                    return;
                case PatientMenuSelection.RETURN:
                    return;
                default:
                    ConsoleUtil.printlnYellow("Không có tính năng phù hợp. Vui lòng chọn lại.");
            }
        }
    }

    private void updatePatient(PatientEntity patient) {
        PatientEntity updatedPatient = patientView.inputPatient(patient, true);
        if (patientService.update(updatedPatient)) {
            ConsoleUtil.printlnGreen("Cập nhật thành công bệnh nhân " + patient.getFullName() + " (" + patient.getId() + ")");
            patientView.showDetail(updatedPatient);
        } else {
            ConsoleUtil.printlnRed("Cập nhật không thành công. Đã có lỗi xảy ra!");
        }
    }

    private void updatePatient() {
        String id = CommonView.inputStringKeyword("Nhập mã bệnh nhân cần cập nhật: ");
        PatientEntity patient = patientService.findById(id);
        if (patient != null) {
            ConsoleUtil.printlnGreen("Tìm thấy bệnh nhân có mã tương ứng.");
            patientView.showDetail(patient);
            updatePatient(patient);
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy bệnh nhân nào có mã " + id);
        }
    }

    private void deletePatient(PatientEntity patient) {
        Boolean confirmDelete = CommonView.confirmDelete();
        if (confirmDelete) {
            if (patientService.delete(patient.getId())) {
                ConsoleUtil.printlnGreen("Xoá thành công bệnh nhân " + patient.getFullName() + " (" + patient.getId() + ")");
            } else {
                ConsoleUtil.printlnRed("Xoá không thành công. Đã có lỗi xảy ra!");
            }
        }
    }

    private void deletePatient() {
        String id = CommonView.inputStringKeyword("Nhập mã bệnh nhân cần xoá: ");
        PatientEntity patient = patientService.findById(id);
        if (patient != null) {
            ConsoleUtil.printlnGreen("Tìm thấy bệnh nhân có mã tương ứng.");
            patientView.showDetail(patient);
            deletePatient(patient);
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy bệnh nhân nào có mã " + id);
        }

    }

    private void displayPatientList() {
        List<PatientEntity> patients = patientService.getAll();
        patientView.display(patients);
    }

    private void registerPatient() {
        PatientEntity patient = patientView.inputPatient();
        if (patientService.registerPatient(patient)) {
            ConsoleUtil.printlnGreen("Thêm mới thành công bệnh nhân " + patient.getFullName() + " (" + patient.getId() + ")");
        } else {
            ConsoleUtil.printlnRed("Thêm mới không thành công. Đã có lỗi xảy ra!");
        }
    }
}
