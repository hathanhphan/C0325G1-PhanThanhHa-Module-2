package case_study_hospital_management.view;

import case_study_hospital_management.common.constants.ConfigurationConstants;
import case_study_hospital_management.common.constants.WorkingHoursConstants;
import case_study_hospital_management.common.enums.AppointmentStatus;
import case_study_hospital_management.common.enums.StatisticAppointmentCriteria;
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
        if (!title.isEmpty()) {
            System.out.println();
            System.out.println("=".repeat(title.length() / 2) + title + "=".repeat(title.length() / 2));
        }
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
        String appointmentTime;
        if (isUpdate) {
            appointmentTime = selectAppointmentTime(emptySchedules, appointment, true);
        } else {
            appointmentTime = selectAppointmentTime(emptySchedules, null, false);
        }
        String reason = InputValidatorUtil.inputString("\uD83D\uDCCB Lý do khám: ", "lý do khám", 3, 255, isUpdate);
        String notes = InputValidatorUtil.inputString("\uD83D\uDCDD Ghi chú: ", "ghi chú", 3, 255, true);

        if (isUpdate) {
            if (appointmentTime == null) appointmentTime = appointment.getAppointmentTime();
            if (reason == null) reason = appointment.getReason();
            if (notes == null) notes = appointment.getNotes();
            return new AppointmentEntity(appointment.getId(), patient.getId(), doctor.getId(), appointmentDate, appointmentTime, appointment.getStatus(), reason, notes, appointment.getParentAppointmentId(), appointment.getNewAppointmentId(), appointment.getRescheduleReason());
        } else {
            return new AppointmentEntity(patient.getId(), doctor.getId(), appointmentDate, appointmentTime, AppointmentStatus.SCHEDULED, reason, notes, null, null, "");
        }
    }

    public String selectAppointmentTime(Map<String, Boolean> emptySchedules, AppointmentEntity appointment, boolean allowEmpty) {
        int index = 1;
        String scheduleStatus;
        int breakLineIndex = 4;
        int schedulesLength = emptySchedules.size();
        Map<Integer, String> indexesCanChoose = new HashMap<>();
        for (Map.Entry<String, Boolean> schedule : emptySchedules.entrySet()) {
            if (appointment != null && schedule.getKey().equalsIgnoreCase(appointment.getAppointmentTime())) {
                scheduleStatus = ConsoleUtil.formatYellow(WorkingHoursConstants.CURRENT_SCHEDULE);
            } else {
                if (schedule.getValue()) {
                    scheduleStatus = ConsoleUtil.formatGreen(WorkingHoursConstants.EMPTY_SCHEDULE);
                    indexesCanChoose.put(index, schedule.getKey());
                } else {
                    scheduleStatus = ConsoleUtil.formatYellow(WorkingHoursConstants.NOT_EMPTY_SCHEDULE);
                }
            }
            System.out.printf("%-46s", String.format("[%d] \uD83D\uDD58 %s - %s", index, schedule.getKey(), scheduleStatus));
            if (index % breakLineIndex == 0 && index != schedulesLength) {
                System.out.println();
            }
            index++;
        }
        System.out.println();
        String input;
        int choice;
        while (true) {
            try {
                System.out.print("⏰ Thời gian (Chọn số tương ứng lịch): ");
                input = sc.nextLine();
                if (input.isEmpty() && allowEmpty) {
                    return null;
                }
                choice = Integer.parseInt(input);
                if (!indexesCanChoose.containsKey(choice)) {
                    ConsoleColorUtil.printlnRed("Bạn nhập 1 số vượt ngoài lựa chọn hoặc thời gian đó đã có người đặt. Vui lòng chọn lại.");
                } else {
                    return indexesCanChoose.get(choice);
                }
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed("Vui lòng nhập lựa chọn là 1 số nguyên tương ứng các lịch trên.");
            }
        }
    }

    public LocalDate selectAppointmentDate() {
        return selectAppointmentDate("\uD83D\uDCC5 Ngày hẹn khám (dd/MM/yyyy): ");
    }

    public LocalDate selectAppointmentDate(String msg) {
        return InputValidatorUtil.inputFutureDate(msg, "ngày hẹn khám", false);
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

    public void displaySearchMenu() {
        System.out.println("\t\uD83C\uDD94 1. Tìm theo mã lịch hẹn");
        System.out.println("\t\uD83D\uDC64 2. Tìm theo bệnh nhân (mã, tên hoặc số điện thoại)");
        System.out.println("\t\uD83D\uDC68\u200D⚕\uFE0F 3. Tìm theo bác sĩ (mã, tên hoặc số điện thoại)");
        System.out.println("\t\uD83D\uDD39 4. Tìm theo trạng thái");
        System.out.println("\t\uD83D\uDCC5 5. Tìm theo ngày");
    }

    public AppointmentStatus selectAppointmentStatus(String message, boolean allowEmpty, boolean isUpdate, AppointmentEntity appointment) {
        String status;
        System.out.println(message);
        int index = 1;
        int breakLineIndex = 3;
        int statusListLength = AppointmentStatus.values().length;
        for (AppointmentStatus as : AppointmentStatus.values()) {
            if (isUpdate && as.equals(AppointmentStatus.RESCHEDULED)) {
                continue;
            }
            String formatString = String.format("[%s] %s", as.getCode(), as.getDisplayName());
            if (appointment.getStatus().equals(as)) {
                System.out.printf("%-40s", ConsoleUtil.formatGreen(formatString));
            } else {

                System.out.printf("%-40s", formatString);
            }
            if (index % breakLineIndex == 0 && index != statusListLength) {
                System.out.println();
            }
            index++;
        }
        System.out.println();
        while (true) {
            try {
                System.out.print("Nhập trạng thái tương ứng tương ứng (Có thể nhập đầy đủ tên hoặc mã): ");
                status = sc.nextLine();
                if (status.isEmpty() && allowEmpty) {
                    return null;
                }
                return AppointmentStatus.from(status);
            } catch (IllegalArgumentException e) {
                ConsoleColorUtil.printlnRed("Vui lòng nhập 1 trạng thái hợp lệ.");
            }
        }
    }

    public boolean selectTypeUpdate() {
        int choice;
        while (true) {
            try {
                System.out.println("Chọn loại cập nhật: ");
                System.out.println("\t1. Cập nhật thông tin lịch hẹn");
                System.out.println("\t2. Cập nhật trạng thái");
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

    public void displayStatisticMenu() {
        System.out.println("\uD83D\uDCC8 1. Thống kê lịch hẹn ngày hôm nay");
        System.out.println("\uD83D\uDCC8 2. Thống kê lịch hẹn theo ngày");
        System.out.println("\uD83D\uDCC8 3. Thống kê lịch hẹn tháng này");
        System.out.println("\uD83D\uDCC8 4. Thống kê lịch hẹn theo tháng");
    }

    public void displayStatisticAppointment(Map<String, Double> statisticResult, String title) {
        if (!title.isEmpty()) {
            System.out.println();
            System.out.println("=".repeat(title.length() / 2) + title + "=".repeat(title.length() / 2));
        }
        if (statisticResult.containsKey(StatisticAppointmentCriteria.TOTAL.getCode())
            && statisticResult.get(StatisticAppointmentCriteria.TOTAL.getCode()) == 0) {
            ConsoleUtil.printlnYellow("Không tìm thấy lịch hẹn nào của tháng này!");
            return;
        }
        int maxLengthPerCol = 0;
        int length;
        for (Map.Entry<String, Double> item : statisticResult.entrySet()) {
            length = (StatisticAppointmentCriteria.fromCode(item.getKey()).getDisplayName() + ": " + String.format("%.2f", item.getValue())).length();
            if (length > maxLengthPerCol) {
                maxLengthPerCol = length;
            }
        }
        int gap = 30;
        maxLengthPerCol += gap;
        String firstColumnContent;
        String secondColumnContent;
        // TOTAL & COMPLETED
        if (statisticResult.containsKey(StatisticAppointmentCriteria.COMPLETED.getCode())
            && statisticResult.containsKey(StatisticAppointmentCriteria.COMPLETED_PERCENT.getCode())) {
            firstColumnContent = ConsoleUtil.formatBold("\uD83D\uDCC5 " + StatisticAppointmentCriteria.TOTAL.getDisplayName() + ": ")
                    + ConsoleUtil.formatGreen(String.format("%.0f", statisticResult.get(StatisticAppointmentCriteria.TOTAL.getCode())));
            secondColumnContent = ConsoleUtil.formatBold("✅ " + StatisticAppointmentCriteria.COMPLETED.getDisplayName() + ": ")
                    + ConsoleUtil.formatGreen(String.format("%.0f (%.2f", statisticResult.get(StatisticAppointmentCriteria.COMPLETED.getCode()), statisticResult.get(StatisticAppointmentCriteria.COMPLETED_PERCENT.getCode())) + "%)");
            CommonView.printOneLineTwoCol(firstColumnContent, secondColumnContent, maxLengthPerCol);
        } else {
            firstColumnContent = ConsoleUtil.formatBold("\uD83D\uDCC5 " + StatisticAppointmentCriteria.TOTAL.getDisplayName() + ": ")
                    + ConsoleUtil.formatGreen(String.format("%.0f", statisticResult.get(StatisticAppointmentCriteria.TOTAL.getCode())));
            secondColumnContent = ConsoleUtil.formatBold("✅ " + StatisticAppointmentCriteria.COMPLETED.getDisplayName() + ": ") + ConsoleUtil.formatGreen("0");
            CommonView.printOneLineTwoCol(firstColumnContent, secondColumnContent, maxLengthPerCol);
        }

        // EXAMINATION and SCHEDULED
        if (statisticResult.containsKey(StatisticAppointmentCriteria.EXAMINATION.getCode())) {
            firstColumnContent = ConsoleUtil.formatBold("\uD83D\uDD04 " + StatisticAppointmentCriteria.EXAMINATION.getDisplayName() + ": ")
                    + ConsoleUtil.formatGreen(String.format("%.0f", statisticResult.get(StatisticAppointmentCriteria.EXAMINATION.getCode())));
        } else {
            firstColumnContent = ConsoleUtil.formatBold("\uD83D\uDD04 " + StatisticAppointmentCriteria.EXAMINATION.getDisplayName() + ": ") + ConsoleUtil.formatGreen("0");
        }
        if (statisticResult.containsKey(StatisticAppointmentCriteria.SCHEDULED.getCode())) {
            secondColumnContent = ConsoleUtil.formatBold("⏳ " + StatisticAppointmentCriteria.SCHEDULED.getDisplayName() + ": ")
                    + ConsoleUtil.formatGreen(String.format("%.0f", statisticResult.get(StatisticAppointmentCriteria.SCHEDULED.getCode())));
        } else {
            secondColumnContent = ConsoleUtil.formatBold("⏳ " + StatisticAppointmentCriteria.SCHEDULED.getDisplayName() + ": ") + ConsoleUtil.formatGreen("0");
        }
        CommonView.printOneLineTwoCol(firstColumnContent, secondColumnContent, maxLengthPerCol);

        // ATTENDANCE and CANCELLED
        if (statisticResult.containsKey(StatisticAppointmentCriteria.ATTENDANCE_PERCENT.getCode())) {
            firstColumnContent = ConsoleUtil.formatBold("\uD83D\uDCC8 " + StatisticAppointmentCriteria.ATTENDANCE_PERCENT.getDisplayName() + ": ")
                    + ConsoleUtil.formatGreen(String.format("%.2f", statisticResult.get(StatisticAppointmentCriteria.ATTENDANCE_PERCENT.getCode())) + "%");
        } else {
            firstColumnContent = ConsoleUtil.formatBold("\uD83D\uDCC8 " + StatisticAppointmentCriteria.ATTENDANCE_PERCENT.getDisplayName() + ": ") + ConsoleUtil.formatGreen("0%");
        }
        if (statisticResult.containsKey(StatisticAppointmentCriteria.CANCELLED.getCode())) {
            secondColumnContent = ConsoleUtil.formatBold("❌ " + StatisticAppointmentCriteria.CANCELLED.getDisplayName() + ": ")
                    + ConsoleUtil.formatGreen(String.format("%.0f", statisticResult.get(StatisticAppointmentCriteria.CANCELLED.getCode())));
        } else {
            secondColumnContent = ConsoleUtil.formatBold("❌ " + StatisticAppointmentCriteria.CANCELLED.getDisplayName() + ": ") + ConsoleUtil.formatGreen("0");
        }
        CommonView.printOneLineTwoCol(firstColumnContent, secondColumnContent, maxLengthPerCol);

        // RESCHEDULED and NO_SHOW
        if (statisticResult.containsKey(StatisticAppointmentCriteria.NO_SHOW.getCode())) {
            firstColumnContent = ConsoleUtil.formatBold("\uD83D\uDC64 " + StatisticAppointmentCriteria.NO_SHOW.getDisplayName() + ": ")
                    + ConsoleUtil.formatGreen(String.format("%.0f", statisticResult.get(StatisticAppointmentCriteria.NO_SHOW.getCode())));
        } else {
            firstColumnContent = ConsoleUtil.formatBold("\uD83D\uDC64 " + StatisticAppointmentCriteria.NO_SHOW.getDisplayName() + ": ") + ConsoleUtil.formatGreen("0");
        }
        if (statisticResult.containsKey(StatisticAppointmentCriteria.RESCHEDULED.getCode())) {
            secondColumnContent = ConsoleUtil.formatBold("⏰ " + StatisticAppointmentCriteria.RESCHEDULED.getDisplayName() + ": ")
                    + ConsoleUtil.formatGreen(String.format("%.0f", statisticResult.get(StatisticAppointmentCriteria.RESCHEDULED.getCode())));
        } else {
            secondColumnContent = ConsoleUtil.formatBold("⏰ " + StatisticAppointmentCriteria.RESCHEDULED.getDisplayName() + ": ") + ConsoleUtil.formatGreen("0");
        }
        CommonView.printOneLineTwoCol(firstColumnContent, secondColumnContent, maxLengthPerCol);
        System.out.println();
    }
}
