package case_study_hospital_management.view;

import case_study_hospital_management.common.constants.ConfigurationConstants;
import case_study_hospital_management.common.constants.WorkingHoursConstants;
import case_study_hospital_management.common.enums.AppointmentStatus;
import case_study_hospital_management.common.enums.DoctorSpecialization;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.DoctorEntity;
import case_study_hospital_management.entity.PatientEntity;
import case_study_hospital_management.util.ConsoleUtil;
import case_study_hospital_management.util.DateUtil;
import case_study_hospital_management.util.InputValidatorUtil;
import ss12_java_collection_framework.bai_tap.student_management.util.ConsoleColorUtil;

import java.time.LocalDate;
import java.util.*;

public class AppointmentView {
    private static final Scanner sc = new Scanner(System.in);
    private static AppointmentView instance;
    private AppointmentView() {
    }

    public static synchronized AppointmentView getInstance() {
        if (instance == null) {
            instance = new AppointmentView();
        }
        return instance;
    }

    public void displayMenu() {
        System.out.println();
        System.out.println("=".repeat(ConfigurationConstants.SEPARATION_LENGTH));
        ConsoleUtil.printlnBold("\uD83D\uDC65 QUẢN LÝ LỊCH HẸN");
        ConsoleUtil.printlnBold("➕ 1. ĐẶT LỊCH HẸN MỚI");
        ConsoleUtil.printlnItalic("\t\uD83D\uDCDD Nhập thông tin lịch hẹn mới vào hệ thống");
        ConsoleUtil.printlnBold("\uD83D\uDD0D 2. TÌM KIẾM LỊCH HẸN");
        ConsoleUtil.printlnItalic("\t\uD83C\uDD94 Tìm theo mã lịch hẹn");
        ConsoleUtil.printlnItalic("\t\uD83D\uDC64 Tìm theo bệnh nhân (mã, tên hoặc số điện thoại)");
        ConsoleUtil.printlnItalic("\t\uD83D\uDC68\u200D⚕\uFE0F Tìm theo bác sĩ (mã, tên hoặc số điện thoại)");
        ConsoleUtil.printlnItalic("\t\uD83D\uDD39 Tìm theo trạng thái");
        ConsoleUtil.printlnItalic("\t\uD83D\uDCC5 Tìm theo ngày");
        ConsoleUtil.printlnBold("\uD83D\uDCCB 3. DANH SÁCH LỊCH HẸN");
        ConsoleUtil.printlnItalic("\t\uD83D\uDCCA Hiển thị toàn bộ danh sách");
        ConsoleUtil.printlnBold("✏\uFE0F 4. CẬP NHẬT LỊCH HẸN");
        ConsoleUtil.printlnItalic("\t\uD83D\uDD04 Cập nhật thông tin lịch hẹn");
        ConsoleUtil.printlnItalic("\t\uD83D\uDD39 Cập nhật trạng thái");
        ConsoleUtil.printlnBold("\uD83D\uDDD1\uFE0F 5. XÓA LỊCH HẸN");
        ConsoleUtil.printlnItalic("\t⚠\uFE0F Xóa lịch hẹn khỏi hệ thống (cẩn thận!)");
        ConsoleUtil.printlnBold("\uD83D\uDCC5 6. XEM LỊCH HẸN HÔM NAY");
        ConsoleUtil.printlnBold("\uD83D\uDCCA 7. BÁO CÁO & THỐNG KÊ");
        ConsoleUtil.printlnBold("\uD83D\uDD19 0. QUAY LẠI MENU CHÍNH");
        System.out.println("=".repeat(ConfigurationConstants.SEPARATION_LENGTH));
    }

    public void display(List<AppointmentEntity> appointments, String title) {
        System.out.println();
        System.out.println("=".repeat(title.length() / 2) + title + "=".repeat(title.length() / 2));
        displayAtTable(appointments);
    }

    public void display(List<AppointmentEntity> appointments) {
        display(appointments, "DANH SÁCH LỊCH HẸN");
    }

    public void displayAtTable(List<AppointmentEntity> appointments) {
        String ordinalNumberLabel = "STT";
        String idLabel = "Mã lịch hẹn";
        String patientLabel = "Bệnh nhân";
        String doctorLabel = "Bác sĩ";
        String appointmentDateLabel = "Ngày";
        String appointmentTimeLabel = "Giờ";
        String reasonLabel = "Lý do";
        String statusLabel = "Trạng thái";
        String newIdLabel = "Mã lịch hẹn mới";
        int maxOrdinalNumberLength = 5;
        int maxIdLength = idLabel.length();
        int maxPatientLength = patientLabel.length();
        int maxDoctorLength = doctorLabel.length();
        int maxAppointmentDateLength = appointmentDateLabel.length();
        int maxAppointmentTimeLength = appointmentTimeLabel.length();
        int maxReasonLength = reasonLabel.length();
        int maxStatusLength = newIdLabel.length();
        int maxNewIdLength = newIdLabel.length();
        for (AppointmentEntity appointment : appointments) {
            maxIdLength = Math.max(maxIdLength, appointment.getId().length());
            maxPatientLength = Math.max(maxPatientLength, (String.format("%s (%s)", appointment.getPatient().getFullName(), appointment.getPatient().getId())).length());
            maxDoctorLength = Math.max(maxDoctorLength, (String.format("%s (%s)", appointment.getDoctor().getFullName(), appointment.getDoctor().getId())).length());
            maxAppointmentDateLength = Math.max(maxAppointmentDateLength, DateUtil.formatDate(appointment.getAppointmentDate()).length());
            maxAppointmentTimeLength = Math.max(maxAppointmentTimeLength, appointment.getAppointmentTime().length());
            maxReasonLength = Math.max(maxReasonLength, appointment.getReason().length());
            maxStatusLength = Math.max(maxStatusLength, appointment.getStatus().getDisplayName().length());
            maxNewIdLength = Math.max(maxNewIdLength, appointment.getNewAppointmentId().length());
        }

        // Tạo định dạng cho dòng
        String lineFormat = "+-" + "-".repeat(maxOrdinalNumberLength) + "-+-" +
                "-".repeat(maxIdLength) + "-+-" +
                "-".repeat(maxPatientLength) + "-+-" +
                "-".repeat(maxDoctorLength) + "-+-" +
                "-".repeat(maxAppointmentDateLength) + "-+-" +
                "-".repeat(maxAppointmentTimeLength) + "-+-" +
                "-".repeat(maxReasonLength) + "-+-" +
                "-".repeat(maxStatusLength) + "-+-" +
                "-".repeat(maxNewIdLength) + "-+%n";

        // Tạo định dạng cho dữ liệu
        String dataFormat = "| %-" +
                maxOrdinalNumberLength + "s | %-" +
                maxIdLength + "s | %-" +
                maxPatientLength + "s | %-" +
                maxDoctorLength + "s | %-" +
                maxAppointmentDateLength + "s | %-" +
                maxAppointmentTimeLength + "s | %-" +
                maxReasonLength + "s | %-" +
                maxStatusLength + "s | %-" +
                maxNewIdLength + "s |%n";

        // In bảng
        System.out.printf(lineFormat);
        System.out.printf(dataFormat, ordinalNumberLabel, idLabel, patientLabel, doctorLabel, appointmentDateLabel, appointmentTimeLabel, reasonLabel, statusLabel, newIdLabel);
        System.out.printf(lineFormat);
        int i = 1;
        for (AppointmentEntity appointment : appointments) {
            System.out.printf(dataFormat,
                    i,
                    appointment.getId(),
                    String.format("%s (%s)", appointment.getPatient().getFullName(), appointment.getPatient().getId()),
                    String.format("%s (%s)", appointment.getDoctor().getFullName(), appointment.getDoctor().getId()),
                    DateUtil.formatDate(appointment.getAppointmentDate()),
                    appointment.getAppointmentTime(),
                    appointment.getReason(),
                    appointment.getStatus().getDisplayName(),
                    appointment.getNewAppointmentId());
            i++;
        }
        System.out.printf(lineFormat);
    }

    public void showDetail(AppointmentEntity appointment) {
        System.out.println("=".repeat(ConfigurationConstants.SEPARATION_LENGTH));
        ConsoleUtil.printlnBold("\uD83D\uDCC4 CHI TIẾT LỊCH HẸN");
        System.out.print("\uD83C\uDD94 Mã lịch hẹn: ");
        ConsoleUtil.printlnItalic(appointment.getId());
        System.out.print("\uD83D\uDC64 Bệnh nhân: ");
        ConsoleUtil.printlnItalic(String.format("%s (%s) | 📞: %s ", appointment.getPatient().getFullName(), appointment.getPatient().getId(), appointment.getPatient().getPhoneNumber()));
        System.out.print("\uD83D\uDC68\u200D⚕\uFE0F Bác sĩ: ");
        ConsoleUtil.printlnItalic(String.format("%s (%s) | 📞: %s", appointment.getDoctor().getFullName(), appointment.getDoctor().getId(), appointment.getDoctor().getPhoneNumber()));
        System.out.print("\uD83C\uDFE5 Chuyên khoa: ");
        ConsoleUtil.printlnItalic(appointment.getDoctor().getSpecialization().getDisplayName());
        System.out.print("\uD83D\uDCC5 Ngày: ");
        ConsoleUtil.printlnItalic(DateUtil.formatDate(appointment.getAppointmentDate()));
        System.out.print("⏰ Giờ: ");
        ConsoleUtil.printlnItalic(appointment.getAppointmentTime());
        System.out.print("\uD83D\uDCCB Lý do: ");
        ConsoleUtil.printlnItalic(appointment.getReason());
        System.out.print("\uD83D\uDCDD Ghi chú: ");
        ConsoleUtil.printlnItalic(appointment.getNotes());
        System.out.print("\uD83D\uDD39 Trạng thái: ");
        ConsoleUtil.printlnItalic(appointment.getStatus().getDisplayName());
        System.out.println("=".repeat(ConfigurationConstants.SEPARATION_LENGTH));
    }

    public void displayDetailMenu() {
        System.out.println("✏\uFE0F 1. Cập nhật thông tin");
        System.out.println("\uD83D\uDD39 2. Cập nhật trạng thái");
        System.out.println("\uD83D\uDCC5 3. Dời lịch hẹn");
        System.out.println("\uD83D\uDDD1\uFE0F 4. Xoá lịch hẹn");
        System.out.println("\uD83D\uDD19 0. Trở về");
    }

    public AppointmentEntity inputAppointment(PatientEntity patient, DoctorEntity doctor, LocalDate appointmentDate, Map<String, Boolean> emptySchedules) {
        return inputAppointment(patient, doctor, appointmentDate, emptySchedules, null, false);
    }

    public AppointmentEntity inputAppointment(PatientEntity patient, DoctorEntity doctor, LocalDate appointmentDate, Map<String, Boolean> emptySchedules, AppointmentEntity appointment, boolean isUpdate) {
        if (isUpdate) {
            ConsoleColorUtil.printlnYellow("Để trống nếu không muốn cập nhật thông tin nào đó...");
        }
        String appointmentTime = selectAppointmentTime(emptySchedules);
        String reason = InputValidatorUtil.inputString("\uD83D\uDCCB Lý do khám: ", "lý do khám", 3, 255, isUpdate);
        String notes = InputValidatorUtil.inputString("\uD83D\uDCDD Ghi chú: ", "ghi chú", 3, 255, true);
        return new AppointmentEntity(patient.getId(), doctor.getId(), appointmentDate, appointmentTime, AppointmentStatus.SCHEDULED, reason, notes, null, null, "");
    }

    public String selectAppointmentTime(Map<String, Boolean> emptySchedules) {
        int index = 1;
        String scheduleStatus;
        int breakLineIndex = 4;
        int schedulesLength = emptySchedules.size();
        Map<Integer, String> indexesCanChoose = new HashMap<>();
        for (Map.Entry<String, Boolean> schedule : emptySchedules.entrySet()) {
            if (schedule.getValue()) {
                scheduleStatus = ConsoleUtil.formatGreen("Đang trống");
                indexesCanChoose.put(index, schedule.getKey());
            } else {
                scheduleStatus = ConsoleUtil.formatYellow("Đã đặt");
            }
            System.out.printf("%-46s", String.format("[%d] %s - %s", index, schedule.getKey(), scheduleStatus));
            if (index % breakLineIndex == 0 && index != schedulesLength) {
                System.out.println();
            }
            index++;
        }
        System.out.println();
        int choice;
        while (true) {
            try {
                System.out.print("⏰ Thời gian: ");
                choice = Integer.parseInt(sc.nextLine());
                if (!indexesCanChoose.containsKey(choice)) {
                    ConsoleColorUtil.printlnRed("Bạn nhập 1 số vượt ngoài lựa chọn hoặc thời gian đó đã có người đặt. Vui lòng nhập lại");
                } else {
                    return indexesCanChoose.get(choice);
                }
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed("Vui lòng nhập lựa chọn là 1 số nguyên tương ứng các lịch trên.");
            }
        }
    }

    public LocalDate selectAppointmentDate() {
        return InputValidatorUtil.inputFutureDate("\uD83D\uDCC5 Ngày hẹn khám (dd/MM/yyyy): ", "ngày hẹn khám", false);
    }

    public boolean selectThisPerson(String displayName) {
        int choice;
        while (true) {
            try {
                System.out.println("Chọn " + displayName + " này: ");
                System.out.println("\t1. Xác nhận");
                System.out.println("\t2. Trở về");
                System.out.print("Lựa chọn của bạn: ");
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) return true;
                else if (choice == 2) return false;
                else ConsoleColorUtil.printlnRed("Bạn chọn chức năng không hợp lệ. Vui lòng chọn lại.");
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed("Bạn chọn chức năng không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    public boolean reSelectDoctorOrDate(DoctorEntity doctor, LocalDate appointmentDate) {
        int choice;
        while (true) {
            try {
                System.out.println("Hiện không có lịch trống đối với bác sĩ " + doctor.getFullName() + " trong ngày " + DateUtil.formatDate(appointmentDate));
                System.out.println("Vui lòng chọn bác sĩ khác hoặc ngày khác. Lựa chọn của bạn là: ");
                System.out.println("\t1. Chọn bác sĩ khác");
                System.out.println("\t2. Chọn ngày khác");
                System.out.print("Lựa chọn của bạn: ");
                choice = Integer.parseInt(sc.nextLine());
                if (choice == 1) return true;
                else if (choice == 2) return false;
                else ConsoleColorUtil.printlnRed("Bạn chọn chức năng không hợp lệ. Vui lòng chọn lại.");
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed("Bạn chọn chức năng không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}
