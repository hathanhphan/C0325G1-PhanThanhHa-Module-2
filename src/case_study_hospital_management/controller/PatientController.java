package case_study_hospital_management.controller;

import case_study_hospital_management.common.constants.PatientMenuConstants;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.PatientEntity;
import case_study_hospital_management.service.PatientService;
import case_study_hospital_management.service.impl.PatientServiceImpl;
import case_study_hospital_management.util.ConsoleUtil;
import case_study_hospital_management.view.CommonView;
import case_study_hospital_management.view.PatientView;

import java.util.List;
import java.util.Map;

public class PatientController {
    private static final PatientService patientService = PatientServiceImpl.getInstance();
    private static final PatientView patientView = PatientView.getInstance();
    private static final AppointmentController appointmentController = AppointmentController.getInstance();
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
                case PatientMenuConstants.REGISTER_PATIENT:
                    registerPatient();
                    CommonView.displayContinueAction();
                    break;
                case PatientMenuConstants.SEARCH_PATIENT:
                    searchPatient();
                    break;
                case PatientMenuConstants.LIST_PATIENT:
                    displayPatientList();
                    break;
                case PatientMenuConstants.UPDATE_PATIENT:
                    updatePatient();
                    CommonView.displayContinueAction();
                    break;
                case PatientMenuConstants.DELETE_PATIENT:
                    deletePatient();
                    CommonView.displayContinueAction();
                    break;
                case PatientMenuConstants.STATISTIC_PATIENT:
                    statisticPatient();
                    CommonView.displayContinueAction();
                    break;
                case PatientMenuConstants.RETURN:
                    return;
                default:
                    shouldShowMenu = false;
                    ConsoleUtil.printlnYellow("Không có tính năng phù hợp. Vui lòng chọn lại.");
            }
        }
    }

    private void searchPatient() {
        int choice;
        patientView.displaySearchMenu();
        while (true) {
            choice = CommonView.inputFeatureSelection();
            switch (choice) {
                case PatientMenuConstants.SEARCH_PATIENT_BY_ID:
                    searchPatientById();
                    return;
                case PatientMenuConstants.SEARCH_PATIENT_BY_NAME:
                    searchPatientByName();
                    return;
                case PatientMenuConstants.SEARCH_PATIENT_BY_PHONE_NUMBER:
                    searchPatientByPhoneNumber();
                    return;
                case PatientMenuConstants.RETURN:
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
            if (foundPatients.size() == 1) {
                patientView.showDetail(foundPatients.get(0));
                displayDetailMenu(foundPatients.get(0));
            } else {
                patientView.display(foundPatients);
                displaySelectPatient(foundPatients);
            }
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy bệnh nhân nào mà số điện thoại có chứa \"" + phoneNumber + "\"");
        }
    }

    private void searchPatientByName() {
        List<PatientEntity> foundPatients;
        String name = CommonView.inputStringKeyword("Nhập tên bệnh nhân cần tìm: ");
        foundPatients = patientService.findByName(name);
        if (!foundPatients.isEmpty()) {
            ConsoleUtil.printlnGreen("Tìm thấy " + foundPatients.size() + " bệnh nhân tên có chứa \"" + name + "\"");
            if (foundPatients.size() == 1) {
                patientView.showDetail(foundPatients.get(0));
                displayDetailMenu(foundPatients.get(0));
            } else {
                patientView.display(foundPatients);
                displaySelectPatient(foundPatients);
            }
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy bệnh nhân nào mà tên có chứa \"" + name + "\"");
        }
    }

    private void displaySelectPatient(List<PatientEntity> patients) {
        System.out.println("Nhập [STT] tương ứng để tương tác chi tiết với bệnh nhân. Hoặc [ENTER] để tiếp tục...");
        String input;
        int choice;
        while (true) {
            input = CommonView.inputStringKeyword("Lựa chọn của bạn: ");
            if (input.isEmpty()) {
                return;
            }
            try {
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= patients.size()) {
                    patientView.showDetail(patients.get(choice - 1));
                    displayDetailMenu(patients.get(choice - 1));
                    return;
                } else {
                    ConsoleUtil.printlnRed("Vui lòng nhập lựa chọn của bạn là 1 số nguyên tương ứng với [STT]. Hoặc [ENTER] để tiếp tục...");
                }
            } catch (NumberFormatException e) {
                ConsoleUtil.printlnRed("Vui lòng nhập lựa chọn của bạn là 1 số nguyên tương ứng với [STT]. Hoặc [ENTER] để tiếp tục...");
            }
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

    private void statisticPatient() {
        int choice;
        patientView.displayStatisticMenu();
        while (true) {
            choice = CommonView.inputFeatureSelection();
            switch (choice) {
                case PatientMenuConstants.STATISTIC_PATIENT_BY_AGE_GROUP:
                    CommonView.displayStatistic(patientService.statisticByAge(), "Độ tuổi", "Số lượng", "THỐNG KÊ BỆNH NHÂN THEO ĐỘ TUỔI");
                    return;
                case PatientMenuConstants.STATISTIC_PATIENT_BY_GENDER:
                    CommonView.displayStatistic(patientService.statisticByGender(), "Giới tính", "Số lượng", "THỐNG KÊ BỆNH NHÂN THEO GIỚI TÍNH");
                    return;
                case PatientMenuConstants.STATISTIC_PATIENT_BY_BLOOD_TYPE:
                    CommonView.displayStatistic(patientService.statisticByBloodType(), "Nhóm máu", "Số lượng", "THỐNG KÊ BỆNH NHÂN THEO NHÓM MÁU");
                    return;
                case PatientMenuConstants.RETURN:
                    return;
                default:
                    ConsoleUtil.printlnYellow("Không có tính năng phù hợp. Vui lòng chọn lại.");
            }
        }
    }

    private void displayDetailMenu(PatientEntity patient) {
        int choice;
        patientView.displayDetailMenu();
        while (true) {
            choice = CommonView.inputFeatureSelection();
            switch (choice) {
                case PatientMenuConstants.UPDATE_PATIENT_IN_DETAIL:
                    updatePatient(patient);
                    return;
                case PatientMenuConstants.DELETE_PATIENT_IN_DETAIL:
                    deletePatient(patient);
                    return;
                case PatientMenuConstants.ADD_APPOINTMENT_IN_DETAIL:
                    appointmentController.addAppointment(patient, null);
                    return;
                case PatientMenuConstants.VIEW_APPOINTMENT_IN_DETAIL:
                    displayAppointmentsOfPatient(patient);
                    CommonView.displayContinueAction();
                    return;
                case PatientMenuConstants.RETURN:
                    return;
                default:
                    ConsoleUtil.printlnYellow("Không có tính năng phù hợp. Vui lòng chọn lại.");
            }
        }
    }

    private void displayAppointmentsOfPatient(PatientEntity patient) {
        List<AppointmentEntity> appointments = patientService.findAllAppointmentByPatientId(patient.getId());
        Map<String, List<AppointmentEntity>> groupAppointmentsByDate = patientService.groupAppointmentByDate(appointments);
        if (groupAppointmentsByDate.isEmpty()) {
            ConsoleUtil.printlnRed("Bệnh nhân này chưa có lịch hẹn nào!");
        } else {
            CommonView.displayGroupByAppointmentList(groupAppointmentsByDate);
            appointmentController.displaySelectGroupAppointmentsByDate(appointments);
        }
    }

    private void updatePatient(PatientEntity patient) {
        PatientEntity updatedPatient = patientView.inputPatient(patient, true);
        if (patientService.update(updatedPatient)) {
            ConsoleUtil.printlnGreen("Cập nhật thành công bệnh nhân " + patient.getFullName() + " (" + patient.getId() + ")");
            patientView.showDetail(updatedPatient);
            displayDetailMenu(updatedPatient);
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
        if (patients.isEmpty()) {
            ConsoleUtil.printlnRed("Danh sách trống!!!");
            return;
        }
        displaySelectPatient(patients);
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
