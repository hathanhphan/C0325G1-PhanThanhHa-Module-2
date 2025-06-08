package case_study_hospital_management.controller;

import case_study_hospital_management.common.constants.DoctorMenuConstants;
import case_study_hospital_management.common.enums.DoctorSpecialization;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.DoctorEntity;
import case_study_hospital_management.service.DoctorService;
import case_study_hospital_management.service.impl.DoctorServiceImpl;
import case_study_hospital_management.util.ConsoleUtil;
import case_study_hospital_management.util.InputValidatorUtil;
import case_study_hospital_management.view.CommonView;
import case_study_hospital_management.view.DoctorView;

import java.util.List;
import java.util.Map;

public class DoctorController {
    private static final DoctorService doctorService = DoctorServiceImpl.getInstance();
    private static final DoctorView doctorView = DoctorView.getInstance();
    private static final AppointmentController appointmentController = AppointmentController.getInstance();
    private static DoctorController instance;
    private DoctorController() {}

    public static synchronized DoctorController getInstance() {
        if (instance == null) {
            instance = new DoctorController();
        }
        return instance;
    }

    public void displayMenu() {
        int choice;
        boolean shouldShowMenu = true;
        while (true) {
            if (shouldShowMenu) {
                doctorView.displayMenu();
            }
            choice = CommonView.inputFeatureSelection();
            shouldShowMenu = true;
            switch (choice) {
                case DoctorMenuConstants.ADD_DOCTOR:
                    addDoctor();
                    CommonView.displayContinueAction();
                    break;
                case DoctorMenuConstants.SEARCH_DOCTOR:
                    searchDoctor();
                    break;
                case DoctorMenuConstants.LIST_DOCTOR:
                    displayDoctorList();
                    break;
                case DoctorMenuConstants.UPDATE_DOCTOR:
                    updateDoctor();
                    CommonView.displayContinueAction();
                    break;
                case DoctorMenuConstants.DELETE_DOCTOR:
                    deleteDoctor();
                    CommonView.displayContinueAction();
                    break;
                case DoctorMenuConstants.RETURN:
                    return;
                default:
                    shouldShowMenu = false;
                    ConsoleUtil.printlnYellow("Không có tính năng phù hợp. Vui lòng chọn lại.");
            }
        }
    }

    private void displayDoctorList() {
        List<DoctorEntity> doctors = doctorService.getAll();
        doctorView.display(doctors);
        displaySelectDoctor(doctors);
    }

    private void addDoctor() {
        DoctorEntity doctor = doctorView.inputDoctor();
        if (doctorService.add(doctor)) {
            ConsoleUtil.printlnGreen("Thêm mới thành công bác sĩ " + doctor.getFullName() + " (" + doctor.getId() + ")");
        } else {
            ConsoleUtil.printlnRed("Thêm mới không thành công. Đã có lỗi xảy ra!");
        }
    }

    private void searchDoctor() {
        int choice;
        doctorView.displaySearchMenu();
        while (true) {
            choice = CommonView.inputFeatureSelection();
            switch (choice) {
                case DoctorMenuConstants.SEARCH_DOCTOR_BY_NAME:
                    searchDoctorByName();
                    return;
                case DoctorMenuConstants.SEARCH_DOCTOR_BY_SPEC:
                    searchDoctorBySpec();
                    return;
                case DoctorMenuConstants.SEARCH_DOCTOR_BY_PHONE_NUMBER:
                    searchDoctorByPhoneNumber();
                    return;
                case DoctorMenuConstants.SEARCH_DOCTOR_BY_YEAR_EXP:
                    searchDoctorByYearExp();
                    return;
                case DoctorMenuConstants.RETURN:
                    return;
                default:
                    ConsoleUtil.printlnYellow("Không có tính năng phù hợp. Vui lòng chọn lại.");
            }
        }
    }

    private void searchDoctorByName() {
        List<DoctorEntity> foundDoctors;
        String name = CommonView.inputStringKeyword("Nhập tên bác sĩ cần tìm: ");
        foundDoctors = doctorService.findByName(name);
        if (!foundDoctors.isEmpty()) {
            ConsoleUtil.printlnGreen("Tìm thấy " + foundDoctors.size() + " bác sĩ tên có chứa \"" + name + "\"");
            if (foundDoctors.size() == 1) {
                doctorView.showDetail(foundDoctors.get(0));
                displayDetailMenu(foundDoctors.get(0));
            } else {
                doctorView.display(foundDoctors);
                displaySelectDoctor(foundDoctors);
            }
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy bệnh nhân nào mà tên có chứa \"" + name + "\"");
        }
    }

    private void searchDoctorBySpec() {
        List<DoctorEntity> foundDoctors;
        DoctorSpecialization spec = doctorView.selectSpecialization("Chọn chuyên ngành cần tìm: ", false);
        foundDoctors = doctorService.findBySpecialization(spec);
        if (!foundDoctors.isEmpty()) {
            ConsoleUtil.printlnGreen("Tìm thấy " + foundDoctors.size() + " bác sĩ có chuyên ngành là \"" + spec.getDisplayName() + "\"");
            if (foundDoctors.size() == 1) {
                doctorView.showDetail(foundDoctors.get(0));
                displayDetailMenu(foundDoctors.get(0));
            } else {
                doctorView.display(foundDoctors);
                displaySelectDoctor(foundDoctors);
            }
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy bệnh nhân nào thuộc chuyên ngành \"" + spec.getDisplayName() + "\"");
        }
    }

    private void searchDoctorByPhoneNumber() {
        List<DoctorEntity> foundDoctors;
        String phoneNumber = CommonView.inputStringKeyword("Nhập số điện thoại bác sĩ cần tìm: ");
        foundDoctors = doctorService.findByPhoneNumber(phoneNumber);
        if (!foundDoctors.isEmpty()) {
            ConsoleUtil.printlnGreen("Tìm thấy " + foundDoctors.size() + " bác sĩ số điện thoại có chứa \"" + phoneNumber + "\"");
            if (foundDoctors.size() == 1) {
                doctorView.showDetail(foundDoctors.get(0));
                displayDetailMenu(foundDoctors.get(0));
            } else {
                doctorView.display(foundDoctors);
                displaySelectDoctor(foundDoctors);
            }
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy bác sĩ nào mà số điện thoại có chứa \"" + phoneNumber + "\"");
        }
    }

    private void searchDoctorByYearExp() {
        List<DoctorEntity> foundDoctors;
        Integer yearOfExperience = InputValidatorUtil.inputInteger("Nhập số năm kinh nghiệm của bác sĩ cần tìm: ", "kinh nghiệm", 0, 30, false);
        assert yearOfExperience != null;
        foundDoctors = doctorService.findByYearOfExperience(yearOfExperience);
        if (!foundDoctors.isEmpty()) {
            ConsoleUtil.printlnGreen("Tìm thấy " + foundDoctors.size() + " bác sĩ có số năm kinh nghiệm là \"" + yearOfExperience + "\"");
            if (foundDoctors.size() == 1) {
                doctorView.showDetail(foundDoctors.get(0));
                displayDetailMenu(foundDoctors.get(0));
            } else {
                doctorView.display(foundDoctors);
                displaySelectDoctor(foundDoctors);
            }
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy bác sĩ nào có số năm kinh nghiệm là \"" + yearOfExperience + "\"");
        }
    }

    private void displayDetailMenu(DoctorEntity doctor) {
        int choice;
        doctorView.displayDetailMenu();
        while (true) {
            choice = CommonView.inputFeatureSelection();
            switch (choice) {
                case DoctorMenuConstants.UPDATE_DOCTOR_IN_DETAIL:
                    updateDoctor(doctor);
                    return;
                case DoctorMenuConstants.DELETE_DOCTOR_IN_DETAIL:
                    deleteDoctor(doctor);
                    return;
                case DoctorMenuConstants.RETURN:
                    return;
                case DoctorMenuConstants.ADD_APPOINTMENT_IN_DETAIL:
                    appointmentController.addAppointment(null, doctor);
                    return;
                case DoctorMenuConstants.VIEW_APPOINTMENT_IN_DETAIL:
                    displayAppointmentsOfDoctor(doctor);
                    CommonView.displayContinueAction();
                    return;
                default:
                    ConsoleUtil.printlnYellow("Không có tính năng phù hợp. Vui lòng chọn lại.");
            }
        }
    }

    private void displayAppointmentsOfDoctor(DoctorEntity doctor) {
        List<AppointmentEntity> appointments = doctorService.findAllAppointmentByDoctorId(doctor.getId());
        Map<String, List<AppointmentEntity>> groupAppointmentsByDate = doctorService.groupAppointmentByDate(appointments);
        if (groupAppointmentsByDate.isEmpty()) {
            ConsoleUtil.printlnRed("Bác sĩ này chưa có lịch hẹn nào!");
        } else {
            CommonView.displayGroupByAppointmentList(groupAppointmentsByDate);
            appointmentController.displaySelectGroupAppointmentsByDate(appointments);
        }
    }

    private void updateDoctor(DoctorEntity doctor) {
        DoctorEntity updatedDoctor = doctorView.inputDoctor(doctor, true);
        if (doctorService.update(updatedDoctor)) {
            ConsoleUtil.printlnGreen("Cập nhật thành công bác sĩ " + doctor.getFullName() + " (" + doctor.getId() + ")");
            doctorView.showDetail(updatedDoctor);
        } else {
            ConsoleUtil.printlnRed("Cập nhật không thành công. Đã có lỗi xảy ra!");
        }
    }

    private void updateDoctor() {
        String id = CommonView.inputStringKeyword("Nhập mã bác sĩ cần cập nhật: ");
        DoctorEntity doctor = doctorService.findById(id);
        if (doctor != null) {
            ConsoleUtil.printlnGreen("Tìm thấy bác sĩ có mã tương ứng.");
            doctorView.showDetail(doctor);
            updateDoctor(doctor);
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy bác sĩ nào có mã " + id);
        }
    }

    private void deleteDoctor(DoctorEntity doctor) {
        Boolean confirmDelete = CommonView.confirmDelete();
        if (confirmDelete) {
            if (doctorService.delete(doctor.getId())) {
                ConsoleUtil.printlnGreen("Xoá thành công bác sĩ " + doctor.getFullName() + " (" + doctor.getId() + ")");
            } else {
                ConsoleUtil.printlnRed("Xoá không thành công. Đã có lỗi xảy ra!");
            }
        }
    }

    private void deleteDoctor() {
        String id = CommonView.inputStringKeyword("Nhập mã bác sĩ cần xoá: ");
        DoctorEntity doctor = doctorService.findById(id);
        if (doctor != null) {
            ConsoleUtil.printlnGreen("Tìm thấy bác sĩ có mã tương ứng.");
            doctorView.showDetail(doctor);
            deleteDoctor(doctor);
        } else {
            ConsoleUtil.printlnYellow("Không tìm thấy bác sĩ nào có mã " + id);
        }

    }

    private void displaySelectDoctor(List<DoctorEntity> doctors) {
        System.out.println("Nhập [STT] tương ứng để tương tác chi tiết với bác sĩ. Hoặc [ENTER] để tiếp tục...");
        String input = CommonView.inputStringKeyword("Lựa chọn của bạn: ");
        try {
            int choice = Integer.parseInt(input);
            if (choice >= 1 && choice <= doctors.size()) {
                doctorView.showDetail(doctors.get(choice - 1));
                displayDetailMenu(doctors.get(choice - 1));
            }
        } catch (NumberFormatException ignored) {}
    }
}
