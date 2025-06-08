package case_study_hospital_management.controller;

import case_study_hospital_management.common.constants.AppointmentMenuConstants;
import case_study_hospital_management.common.constants.DoctorMenuConstants;
import case_study_hospital_management.common.enums.AppointmentStatus;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.DoctorEntity;
import case_study_hospital_management.entity.PatientEntity;
import case_study_hospital_management.service.AppointmentService;
import case_study_hospital_management.service.DoctorService;
import case_study_hospital_management.service.PatientService;
import case_study_hospital_management.service.impl.AppointmentServiceImpl;
import case_study_hospital_management.service.impl.DoctorServiceImpl;
import case_study_hospital_management.service.impl.PatientServiceImpl;
import case_study_hospital_management.util.ConsoleUtil;
import case_study_hospital_management.util.InputValidatorUtil;
import case_study_hospital_management.view.AppointmentView;
import case_study_hospital_management.view.CommonView;
import case_study_hospital_management.view.DoctorView;
import case_study_hospital_management.view.PatientView;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AppointmentController {
    private static final AppointmentService appointmentService = AppointmentServiceImpl.getInstance();
    private static final DoctorService doctorService = DoctorServiceImpl.getInstance();
    private static final PatientService patientService = PatientServiceImpl.getInstance();
    private static final AppointmentView appointmentView = AppointmentView.getInstance();
    private static final PatientView patientView = PatientView.getInstance();
    private static final DoctorView doctorView = DoctorView.getInstance();
    private static final Scanner sc = new Scanner(System.in);
    private static AppointmentController instance;
    private AppointmentController() {}

    public static synchronized AppointmentController getInstance() {
        if (instance == null) {
            instance = new AppointmentController();
        }
        return instance;
    }

    public void displayMenu() {
        int choice;
        boolean shouldShowMenu = true;
        while (true) {
            if (shouldShowMenu) {
                appointmentView.displayMenu();
            }
            choice = CommonView.inputFeatureSelection();
            shouldShowMenu = true;
            switch (choice) {
                case AppointmentMenuConstants.ADD_APPOINTMENT:
                    addAppointment(null, null);
                    CommonView.displayContinueAction();
                    break;
                case AppointmentMenuConstants.SEARCH_APPOINTMENT:
                    searchAppointment();
                    break;
                case AppointmentMenuConstants.LIST_APPOINTMENT:
                    displayAppointmentList();
                    break;
                case AppointmentMenuConstants.UPDATE_APPOINTMENT:
                    //updateDoctor();
                    CommonView.displayContinueAction();
                    break;
                case AppointmentMenuConstants.DELETE_APPOINTMENT:
                    //deleteDoctor();
                    CommonView.displayContinueAction();
                    break;
                case AppointmentMenuConstants.LIST_TODAY_APPOINTMENT:
                    //deleteDoctor();
                    break;
                case AppointmentMenuConstants.STATISTIC_APPOINTMENT:
                    //deleteDoctor();
                    CommonView.displayContinueAction();
                    break;
                case AppointmentMenuConstants.RETURN:
                    return;
                default:
                    shouldShowMenu = false;
                    ConsoleUtil.printlnYellow("Không có tính năng phù hợp. Vui lòng chọn lại.");
            }
        }
    }

    public void addAppointment(PatientEntity patient, DoctorEntity doctor) {
        if (patient == null) {
            patient = selectPatient();
            if (patient == null) {
                return;
            }
        }
        if (doctor == null) {
            doctor = selectDoctor();
            if (doctor == null) {
                return;
            }
        }
        LocalDate appointmentDate = appointmentView.selectAppointmentDate();
        List<AppointmentEntity> foundAppointments = appointmentService.findByDoctorAndDate(doctor.getId(), appointmentDate);
        Map<String, Boolean> emptySchedules = appointmentService.findEmptyAppointmentsByDoctorAndDate(foundAppointments, doctor);
        boolean isReSelectDoctor;
        while (emptySchedules.entrySet().stream().noneMatch(Map.Entry::getValue)) {
            isReSelectDoctor = appointmentView.reSelectDoctorOrDate(doctor, appointmentDate);
            if (isReSelectDoctor) {
                doctor = selectDoctor();
                if (doctor == null) {
                    return;
                }
            } else {
                appointmentDate = appointmentView.selectAppointmentDate();
            }
            foundAppointments = appointmentService.findByDoctorAndDate(doctor.getId(), appointmentDate);
            emptySchedules = appointmentService.findEmptyAppointmentsByDoctorAndDate(foundAppointments, doctor);
        }
        AppointmentEntity appointment = appointmentView.inputAppointment(patient, doctor, appointmentDate, emptySchedules);
        if (appointmentService.add(appointment)) {
            ConsoleUtil.printlnGreen("Thêm mới thành công lịch hẹn cho bệnh nhân " + patient.getFullName() + " (" + patient.getId() + ")");
        } else {
            ConsoleUtil.printlnRed("Thêm mới không thành công. Đã có lỗi xảy ra!");
        }
    }

    private PatientEntity selectPatient() {
        String keyword;
        List<PatientEntity> foundPatients;
        boolean selection;
        PatientEntity selectedPatient = null;
        do {
            keyword = CommonView.inputStringKeyword("Nhập mã, tên hoặc số điện thoại của bệnh nhân cần lên lịch hẹn: ");
            foundPatients = patientService.findByKeyword(keyword);
            if (foundPatients.isEmpty()) {
                ConsoleUtil.printlnYellow("Không tìm thấy bệnh nhân nào tương ứng. Vui lòng nhập lại.");
            }
        } while (foundPatients.isEmpty());
        ConsoleUtil.printlnGreen("Tìm thấy " + foundPatients.size() + " bệnh nhân.");
        if (foundPatients.size() == 1) {
            patientView.showDetail(foundPatients.get(0));
            selection = appointmentView.selectThisPerson("bệnh nhân");
            if (selection) {
                selectedPatient = foundPatients.get(0);
            }
        } else {
            patientView.display(foundPatients);
            selectedPatient = displaySelectPatient(foundPatients);
        }
        return selectedPatient;
    }

    private PatientEntity displaySelectPatient(List<PatientEntity> patients) {
        String input;
        int choice;
        boolean selection;
        while (true) {
            try {
                System.out.println("Nhập [STT] tương ứng để lên lịch hẹn cho bệnh nhân này. Hoặc [ENTER] để huỷ...");
                input = CommonView.inputStringKeyword("Lựa chọn của bạn: ");
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= patients.size()) {
                    patientView.showDetail(patients.get(choice - 1));
                    selection = appointmentView.selectThisPerson("bệnh nhân");
                    if (selection) return patients.get(choice - 1);
                } else {
                    ConsoleUtil.printRed("Bạn nhập [STT] không hợp lệ. Vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    private DoctorEntity selectDoctor() {
        String keyword;
        List<DoctorEntity> foundDoctors;
        boolean selection;
        DoctorEntity selectedDoctor = null;
        do {
            keyword = CommonView.inputStringKeyword("Nhập mã, tên, chuyên khoa hoặc số điện thoại của bác sĩ cần lên lịch hẹn: ");
            foundDoctors = doctorService.findByKeyword(keyword);
            if (foundDoctors.isEmpty()) {
                ConsoleUtil.printlnYellow("Không tìm thấy bác sĩ nào tương ứng. Vui lòng nhập lại.");
            }
        } while (foundDoctors.isEmpty());
        ConsoleUtil.printlnGreen("Tìm thấy " + foundDoctors.size() + " bác sĩ.");
        if (foundDoctors.size() == 1) {
            doctorView.showDetail(foundDoctors.get(0));
            selection = appointmentView.selectThisPerson("bác sĩ");
            if (selection) {
                selectedDoctor = foundDoctors.get(0);
            }
        } else {
            doctorView.display(foundDoctors);
            selectedDoctor = displaySelectDoctor(foundDoctors);
        }
        return selectedDoctor;
    }

    private DoctorEntity displaySelectDoctor(List<DoctorEntity> doctors) {
        String input;
        int choice;
        boolean selection;
        while (true) {
            try {
                System.out.println("Nhập [STT] tương ứng để lên lịch hẹn cho bác sĩ này. Hoặc [ENTER] để huỷ...");
                input = CommonView.inputStringKeyword("Lựa chọn của bạn: ");
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= doctors.size()) {
                    doctorView.showDetail(doctors.get(choice - 1));
                    selection = appointmentView.selectThisPerson("bác sĩ");
                    if (selection) return doctors.get(choice - 1);
                } else {
                    ConsoleUtil.printRed("Bạn nhập [STT] không hợp lệ. Vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    public void displayAppointmentList() {
        List<AppointmentEntity> appointments = appointmentService.getAll();
        appointmentView.display(appointments);
        displaySelectAppointment(appointments);
    }

    private void displaySelectAppointment(List<AppointmentEntity> appointments) {
        System.out.println("Nhập [STT] tương ứng để tương tác chi tiết với lịch hẹn. Hoặc [ENTER] để tiếp tục...");
        String input = CommonView.inputStringKeyword("Lựa chọn của bạn: ");
        try {
            int choice = Integer.parseInt(input);
            if (choice >= 1 && choice <= appointments.size()) {
                appointmentView.showDetail(appointments.get(choice - 1));
                displayDetailMenu(appointments.get(choice - 1));
            }
        } catch (NumberFormatException ignored) {}
    }

    private void displayDetailMenu(AppointmentEntity appointment) {
        if (appointment.getStatus().equals(AppointmentStatus.CANCELLED)) {
            CommonView.displayContinueAction();
            return;
        }
        int choice;
        appointmentView.displayDetailMenu();
        while (true) {
            choice = CommonView.inputFeatureSelection();
            switch (choice) {
                case DoctorMenuConstants.UPDATE_DOCTOR_IN_DETAIL:
                    //updateDoctor(doctor);
                    return;
                case DoctorMenuConstants.DELETE_DOCTOR_IN_DETAIL:
                    //deleteDoctor(doctor);
                    return;
                case DoctorMenuConstants.RETURN:
                    return;
                default:
                    ConsoleUtil.printlnYellow("Không có tính năng phù hợp. Vui lòng chọn lại.");
            }
        }
    }

    public void displaySelectGroupAppointmentsByDate(List<AppointmentEntity> appointments) {
        List<String> idsCanSelect = appointments.stream().map(AppointmentEntity::getId).toList();
        System.out.println("Nhập [Mã lịch hẹn] tương ứng để tương tác chi tiết với lịch hẹn. Hoặc [ENTER] để tiếp tục...");
        String id;
        while (true) {
            id = CommonView.inputStringKeyword("Lựa chọn của bạn: ");
            if (id.isEmpty()) {
                return;
            }
            AppointmentEntity appointment = appointmentService.findById(id);
            if (appointment != null && idsCanSelect.contains(id)) {
                appointmentView.showDetail(appointment);
                return;
            } else {
                ConsoleUtil.printlnYellow("Cần nhập đúng mã lịch hẹn có trên danh sách hiện tại. Vui lòng nhập lại hoặc [ENTER] để tiếp tục...");
            }
        }
    }

    private void searchAppointment() {
        int choice;
        appointmentView.displaySearchMenu();
        while (true) {
            choice = CommonView.inputFeatureSelection();
            switch (choice) {
                case AppointmentMenuConstants.SEARCH_APPOINTMENT_BY_ID:
                    searchAppointmentById();
                    return;
                case AppointmentMenuConstants.SEARCH_APPOINTMENT_BY_PATIENT:
                    searchAppointmentByPatient();
                    return;
                case AppointmentMenuConstants.SEARCH_APPOINTMENT_BY_DOCTOR:
                    searchAppointmentByDoctor();
                    return;
                case AppointmentMenuConstants.SEARCH_APPOINTMENT_BY_STATUS:
                    searchAppointmentByStatus();
                    return;
                case AppointmentMenuConstants.SEARCH_APPOINTMENT_BY_DATE:
                    searchAppointmentByDate();
                    return;
                case AppointmentMenuConstants.RETURN:
                    return;
                default:
                    ConsoleUtil.printlnYellow("Không có tính năng phù hợp. Vui lòng chọn lại.");
            }
        }
    }

    private void searchAppointmentById() {
        String id = CommonView.inputStringKeyword("Nhập mã lịch hẹn cần tìm: ");
        AppointmentEntity appointment = appointmentService.findById(id);
        if (appointment != null) {
            ConsoleUtil.printlnGreen("Tìm thấy lịch hẹn có mã tương ứng.");
            appointmentView.showDetail(appointment);
            displayDetailMenu(appointment);
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy lịch hẹn nào có mã " + id);
        }
    }

    private void searchAppointmentByPatient() {
        List<AppointmentEntity> foundAppointments;
        String keyword = CommonView.inputStringKeyword("Nhập mã, tên hoặc số điện thoại của bệnh nhân cần tìm kiếm lịch hẹn: ");
        foundAppointments = appointmentService.findByKeywordOfPatient(keyword);
        displaySearchResult(foundAppointments);
    }

    private void searchAppointmentByDoctor() {
        List<AppointmentEntity> foundAppointments;
        String keyword = CommonView.inputStringKeyword("Nhập mã, tên hoặc số điện thoại của bác sĩ cần tìm kiếm lịch hẹn: ");
        foundAppointments = appointmentService.findByKeywordOfDoctor(keyword);
        displaySearchResult(foundAppointments);
    }

    private void searchAppointmentByStatus() {
        List<AppointmentEntity> foundAppointments;
        AppointmentStatus status = appointmentView.selectAppointmentStatus("Chọn trạng thái lịch hẹn cần tìm: ", false);
        foundAppointments = appointmentService.findByStatus(status);
        displaySearchResult(foundAppointments);
    }

    private void searchAppointmentByDate() {
        List<AppointmentEntity> foundAppointments;
        LocalDate date = InputValidatorUtil.inputDate("Chọn ngày cần tìm lịch hẹn: ", "ngày cần tìm", false);
        foundAppointments = appointmentService.findByDate(date);
        displaySearchResult(foundAppointments);
    }

    private void displaySearchResult(List<AppointmentEntity> appointments) {
        if (appointments.isEmpty()) {
            ConsoleUtil.printlnYellow("Không tìm thấy lịch hẹn nào tương ứng từ khoá trên.");
        } else {
            if (appointments.size() == 1) {
                appointmentView.showDetail(appointments.get(0));
                displayDetailMenu(appointments.get(0));
            } else {
                appointmentView.display(appointments);
                displaySelectAppointment(appointments);
            }
        }
    }
}
