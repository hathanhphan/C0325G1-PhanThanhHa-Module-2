package case_study_hospital_management.controller;

import case_study_hospital_management.common.constants.AppointmentMenuConstants;
import case_study_hospital_management.common.enums.AppointmentStatus;
import case_study_hospital_management.common.enums.DayOfWeekInVietnamese;
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
import case_study_hospital_management.util.DateUtil;
import case_study_hospital_management.util.InputValidatorUtil;
import case_study_hospital_management.view.AppointmentView;
import case_study_hospital_management.view.CommonView;
import case_study_hospital_management.view.DoctorView;
import case_study_hospital_management.view.PatientView;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AppointmentController {
    private static final AppointmentService appointmentService = AppointmentServiceImpl.getInstance();
    private static final DoctorService doctorService = DoctorServiceImpl.getInstance();
    private static final PatientService patientService = PatientServiceImpl.getInstance();
    private static final AppointmentView appointmentView = AppointmentView.getInstance();
    private static final PatientView patientView = PatientView.getInstance();
    private static final DoctorView doctorView = DoctorView.getInstance();
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
                    updateAppointment();
                    CommonView.displayContinueAction();
                    break;
                case AppointmentMenuConstants.DELETE_APPOINTMENT:
                    deleteAppointment();
                    CommonView.displayContinueAction();
                    break;
                case AppointmentMenuConstants.LIST_TODAY_APPOINTMENT:
                    displayTodayAppointmentList();
                    break;
                case AppointmentMenuConstants.STATISTIC_APPOINTMENT:
                    statisticAppointment();
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
            doctor = selectDoctor(false);
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
                doctor = selectDoctor(false);
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

    private DoctorEntity selectDoctor(boolean isUpdate) {
        String keyword;
        List<DoctorEntity> foundDoctors;
        boolean selection;
        DoctorEntity selectedDoctor = null;
        String message = !isUpdate ? "Nhập mã, tên, chuyên khoa hoặc số điện thoại của bác sĩ cần lên lịch hẹn: "
                : "Nhập mã, tên, chuyên khoa hoặc số điện thoại của bác sĩ khác cần lên lịch hẹn. Hoặc để trống nếu không cần đổi bác sĩ: ";
        do {
            keyword = CommonView.inputStringKeyword(message);
            if (isUpdate && keyword.isEmpty()) {
                return null;
            }
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
        if (appointments.isEmpty()) {
            ConsoleUtil.printlnRed("Danh sách trống!!!");
            return;
        }
        appointmentView.display(appointments);
        displaySelectAppointment(appointments);
    }

    private void displaySelectAppointment(List<AppointmentEntity> appointments) {
        System.out.println("Nhập [STT] tương ứng để tương tác chi tiết với lịch hẹn. Hoặc [ENTER] để tiếp tục...");
        String input;
        int choice;
        while (true) {
            input = CommonView.inputStringKeyword("Lựa chọn của bạn: ");
            if (input.isEmpty()) {
                return;
            }
            try {
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= appointments.size()) {
                    appointmentView.showDetail(appointments.get(choice - 1));
                    displayDetailMenu(appointments.get(choice - 1));
                    return;
                } else {
                    ConsoleUtil.printlnRed("Vui lòng nhập lựa chọn của bạn là 1 số nguyên tương ứng với [STT]. Hoặc [ENTER] để tiếp tục...");
                }
            } catch (NumberFormatException e) {
                ConsoleUtil.printlnRed("Vui lòng nhập lựa chọn của bạn là 1 số nguyên tương ứng với [STT]. Hoặc [ENTER] để tiếp tục...");
            }
        }
    }

    private void displayDetailMenu(AppointmentEntity appointment) {
        if (appointment.getStatus().equals(AppointmentStatus.RESCHEDULED)) {
            CommonView.displayContinueAction();
            return;
        }
        int choice;
        appointmentView.displayDetailMenu();
        while (true) {
            choice = CommonView.inputFeatureSelection();
            switch (choice) {
                case AppointmentMenuConstants.UPDATE_APPOINTMENT_IN_DETAIL:
                    updateAppointment(appointment);
                    return;
                case AppointmentMenuConstants.UPDATE_STATUS_APPOINTMENT_IN_DETAIL:
                    updateStatusAppointment(appointment);
                    return;
                case AppointmentMenuConstants.RESCHEDULE_APPOINTMENT_IN_DETAIL:
                    rescheduleAppointment(appointment);
                    CommonView.displayContinueAction();
                    return;
                case AppointmentMenuConstants.DELETE_APPOINTMENT_IN_DETAIL:
                    deleteAppointment(appointment);
                    return;
                case AppointmentMenuConstants.RETURN:
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
                displayDetailMenu(appointment);
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
        AppointmentStatus status = appointmentView.selectAppointmentStatus("Chọn trạng thái lịch hẹn cần tìm: ", false, false, null);
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

    private void updateAppointment(AppointmentEntity appointment) {
        List<AppointmentEntity> foundAppointments = appointmentService.findByDoctorAndDate(appointment.getDoctor().getId(), appointment.getAppointmentDate());
        Map<String, Boolean> emptySchedules = appointmentService.findEmptyAppointmentsByDoctorAndDate(foundAppointments, appointment.getDoctor());
        AppointmentEntity updatedAppointment = appointmentView.inputAppointment(appointment.getPatient(), appointment.getDoctor(), appointment.getAppointmentDate(), emptySchedules, appointment, true);
        if (appointmentService.update(updatedAppointment)) {
            ConsoleUtil.printlnGreen("Cập nhật thành công lịch hẹn của bệnh nhân " + appointment.getPatient().getFullName() + " (" + appointment.getPatient().getId() + ")");
            appointmentView.showDetail(updatedAppointment);
            displayDetailMenu(updatedAppointment);
        } else {
            ConsoleUtil.printlnRed("Cập nhật không thành công. Đã có lỗi xảy ra!");
        }
    }

    private void updateAppointment() {
        String id = CommonView.inputStringKeyword("Nhập mã lịch hẹn cần cập nhật: ");
        AppointmentEntity appointment = appointmentService.findById(id);
        if (appointment != null) {
            ConsoleUtil.printlnGreen("Tìm thấy lịch hẹn có mã tương ứng.");
            appointmentView.showDetail(appointment);
            boolean isUpdateInfo = appointmentView.selectTypeUpdate();
            if (isUpdateInfo) {
                updateAppointment(appointment);
            } else {
                updateStatusAppointment(appointment);
            }
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy lịch hẹn nào có mã " + id);
        }
    }

    private void updateStatusAppointment(AppointmentEntity appointment) {
        AppointmentStatus status = appointmentView.selectAppointmentStatus("Chọn trạng thái cần cập nhật: ", false, true, appointment);
        appointment.setStatus(status);
        if (appointmentService.update(appointment)) {
            ConsoleUtil.printlnGreen("Cập nhật thành công lịch hẹn của bệnh nhân " + appointment.getPatient().getFullName() + " (" + appointment.getPatient().getId() + ")");
            appointmentView.showDetail(appointment);
            displayDetailMenu(appointment);
        } else {
            ConsoleUtil.printlnRed("Cập nhật không thành công. Đã có lỗi xảy ra!");
        }
    }

    private void deleteAppointment(AppointmentEntity appointment) {
        Boolean confirmDelete = CommonView.confirmDelete();
        if (confirmDelete) {
            if (appointmentService.delete(appointment.getId())) {
                ConsoleUtil.printlnGreen("Xoá thành công lịch hẹn của bệnh nhân " + appointment.getPatient().getFullName() + " (" + appointment.getPatient().getId() + ")");
            } else {
                ConsoleUtil.printlnRed("Xoá không thành công. Đã có lỗi xảy ra!");
            }
        }
    }

    private void deleteAppointment() {
        String id = CommonView.inputStringKeyword("Nhập mã lịch hẹn cần xoá: ");
        AppointmentEntity appointment = appointmentService.findById(id);
        if (appointment != null) {
            ConsoleUtil.printlnGreen("Tìm thấy lịch hẹn có mã tương ứng.");
            appointmentView.showDetail(appointment);
            deleteAppointment(appointment);
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy lịch hẹn nào có mã " + id);
        }
    }

    private void displayTodayAppointmentList() {
        List<AppointmentEntity> appointments = appointmentService.findByDate(LocalDate.now());
        if (appointments.isEmpty()) {
            ConsoleUtil.printlnRed("Danh sách trống!!!");
            return;
        }
        appointmentView.display(appointments, String.format("DANH SÁCH LỊCH HẸN NGÀY HÔM NAY (%s, %s)", DayOfWeekInVietnamese.fromCode(LocalDate.now().getDayOfWeek().toString()).getDisplayName(), DateUtil.formatDate(LocalDate.now())));
        displaySelectAppointment(appointments);
    }

    private void rescheduleAppointment(AppointmentEntity appointment) {
        if (appointment.getStatus().isCompleted() || appointment.getStatus().isCancelled()) {
            ConsoleUtil.printlnYellow("Lịch hẹn khám này đã hoàn thành hoặc bị huỷ, không thể giờ lịch nữa. Vui lòng cập nhật trạng thái hoặc lên lịch mới.");
            return;
        }
        DoctorEntity doctor = selectDoctor(true);
        if (doctor == null) {
            doctor = appointment.getDoctor();
        }
        LocalDate appointmentDate = appointmentView.selectAppointmentDate("\uD83D\uDCC5 Ngày hẹn khám mới (dd/MM/yyyy): ");
        List<AppointmentEntity> foundAppointments = appointmentService.findByDoctorAndDate(doctor.getId(), appointmentDate);
        Map<String, Boolean> emptySchedules = appointmentService.findEmptyAppointmentsByDoctorAndDate(foundAppointments, doctor);
        boolean isReSelectDoctor; 
        while (emptySchedules.entrySet().stream().noneMatch(Map.Entry::getValue)) {
            isReSelectDoctor = appointmentView.reSelectDoctorOrDate(doctor, appointmentDate);
            if (isReSelectDoctor) {
                doctor = selectDoctor(false);
                if (doctor == null) {
                    return;
                }
            } else {
                appointmentDate = appointmentView.selectAppointmentDate();
            }
            foundAppointments = appointmentService.findByDoctorAndDate(doctor.getId(), appointmentDate);
            emptySchedules = appointmentService.findEmptyAppointmentsByDoctorAndDate(foundAppointments, doctor);
        }
        String appointmentTime = appointmentView.selectAppointmentTime(emptySchedules, appointment, false);
        String rescheduleReason = InputValidatorUtil.inputString("Lý do dời lịch: ", "lý do dời lịch", 3, 255, false);
        appointment.setStatus(AppointmentStatus.RESCHEDULED);
        AppointmentEntity rescheduleAppointment = new AppointmentEntity(appointment.getPatientId(), doctor.getId(), appointmentDate, appointmentTime, AppointmentStatus.SCHEDULED, appointment.getReason(), appointment.getNotes(), appointment.getId(), null, rescheduleReason);
        if (appointmentService.reschedule(appointment, rescheduleAppointment)) {
            ConsoleUtil.printlnGreen("Dời lịch thành công cho bệnh nhân " + rescheduleAppointment.getPatient().getFullName() + " (" + rescheduleAppointment.getPatient().getId() + ")");
        } else {
            ConsoleUtil.printlnRed("Dời lịch không thành công. Đã có lỗi xảy ra!");
        }
    }

    @SuppressWarnings("DataFlowIssue")
    private void statisticAppointment() {
        int choice;
        appointmentView.displayStatisticMenu();
        while (true) {
            choice = CommonView.inputFeatureSelection();
            switch (choice) {
                case AppointmentMenuConstants.STATISTIC_TODAY_APPOINTMENT:
                    appointmentView.displayStatisticAppointment(appointmentService.statisticTodayAppointment(), String.format("THỐNG KÊ LỊCH HẸN NGÀY HÔM NAY (%s, %s)", DayOfWeekInVietnamese.fromCode(LocalDate.now().getDayOfWeek().toString()).getDisplayName(), DateUtil.formatDate(LocalDate.now())));
                    return;
                case AppointmentMenuConstants.STATISTIC_APPOINTMENT_BY_DATE:
                    LocalDate date = InputValidatorUtil.inputDate("Nhập ngày cần thống kê (dd/MM/yyyy): ", "ngày", false);
                    assert date != null;
                    appointmentView.displayStatisticAppointment(appointmentService.statisticAppointmentByDate(date), String.format("THỐNG KÊ LỊCH HẸN (%s, %s)", DayOfWeekInVietnamese.fromCode(date.getDayOfWeek().toString()).getDisplayName(), DateUtil.formatDate(date)));
                    return;
                case AppointmentMenuConstants.STATISTIC_THIS_MONTH_APPOINTMENT:
                    appointmentView.displayStatisticAppointment(appointmentService.statisticThisMonthAppointment(), String.format("THỐNG KÊ LỊCH HẸN THÁNG NÀY (THÁNG %s - %s)", LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
                    return;
                case AppointmentMenuConstants.STATISTIC_APPOINTMENT_BY_MONTH:
                    Integer month = InputValidatorUtil.inputInteger("Nhập tháng: ", "tháng", 1, 12, false);
                    Integer year = InputValidatorUtil.inputInteger("Nhập năm: ", "năm", 2000, LocalDate.now().getYear(), false);
                    appointmentView.displayStatisticAppointment(appointmentService.statisticAppointmentByMonth(month, year), String.format("THỐNG KÊ LỊCH HẸN THÁNG NÀY (THÁNG %s - %s)", month, year));
                    return;
                case AppointmentMenuConstants.RETURN:
                    return;
                default:
                    ConsoleUtil.printlnYellow("Không có tính năng phù hợp. Vui lòng chọn lại.");
            }
        }
    }
}
