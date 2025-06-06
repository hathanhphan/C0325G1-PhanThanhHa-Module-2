package case_study_hospital_management.view;

import case_study_hospital_management.common.constants.ConfigurationConstants;
import case_study_hospital_management.common.enums.BloodType;
import case_study_hospital_management.common.enums.DoctorSpecialization;
import case_study_hospital_management.entity.DoctorEntity;
import case_study_hospital_management.entity.PatientEntity;
import case_study_hospital_management.util.ConsoleUtil;
import case_study_hospital_management.util.DateUtil;
import case_study_hospital_management.util.InputValidatorUtil;
import case_study_hospital_management.util.PersonHelper;
import ss12_java_collection_framework.bai_tap.student_management.util.ConsoleColorUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class DoctorView {
    private static final Scanner sc = new Scanner(System.in);
    private static DoctorView instance;
    private DoctorView() {
    }

    public static synchronized DoctorView getInstance() {
        if (instance == null) {
            instance = new DoctorView();
        }
        return instance;
    }

    public void displayMenu() {
        System.out.println();
        System.out.println("=".repeat(ConfigurationConstants.SEPARATION_LENGTH));
        ConsoleUtil.printlnBold("\uD83D\uDC65 QUẢN LÝ BÁC SĨ");
        ConsoleUtil.printlnBold("➕ 1. THÊM BÁC SĨ MỚI");
        ConsoleUtil.printlnItalic("\t\uD83D\uDCDD Nhập thông tin bác sĩ mới vào hệ thống");
        ConsoleUtil.printlnBold("\uD83D\uDD0D 2. TÌM KIẾM BÁC SĨ");
        ConsoleUtil.printlnItalic("\t\uD83D\uDC64 Tìm theo tên");
        ConsoleUtil.printlnItalic("\t\uD83C\uDFE5 Tìm theo chuyên khoa");
        ConsoleUtil.printlnItalic("\t\uD83D\uDCDE Tìm theo số điện thoại");
        ConsoleUtil.printlnItalic("\t⭐ Tìm theo số năm kinh nghiệm");
        ConsoleUtil.printlnBold("\uD83D\uDCCB 3. DANH SÁCH BÁC SĨ");
        ConsoleUtil.printlnItalic("\t\uD83D\uDCCA Hiển thị toàn bộ danh sách");
        ConsoleUtil.printlnBold("✏\uFE0F 4. CẬP NHẬT THÔNG TIN");
        ConsoleUtil.printlnItalic("\t\uD83D\uDD04 Sửa đổi thông tin bác sĩ");
        ConsoleUtil.printlnBold("\uD83D\uDDD1\uFE0F 5. XÓA BÁC SĨ");
        ConsoleUtil.printlnItalic("\t⚠\uFE0F Xóa bác sĩ khỏi hệ thống (cẩn thận!)");
        ConsoleUtil.printlnBold("\uD83D\uDD19 0. QUAY LẠI MENU CHÍNH");
        System.out.println("=".repeat(ConfigurationConstants.SEPARATION_LENGTH));
    }

    public void display(List<DoctorEntity> doctors, String title) {
        System.out.println();
        System.out.println("=".repeat(title.length() / 2) + title + "=".repeat(title.length() / 2));
        displayAtTable(doctors);
    }

    public void display(List<DoctorEntity> doctors) {
        display(doctors, "DANH SÁCH BÁC SĨ");
    }

    public void displayAtTable(List<DoctorEntity> doctors) {
        String ordinalNumberLabel = "STT";
        String idLabel = "Mã BS";
        String fullNameLabel = "Họ tên";
        String genderLabel = "Giới tính";
        String phoneNumberLabel = "Số điện thoại";
        String specLabel = "Chuyên ngành";
        String emailLabel = "Email";
        String licenseNumberLabel = "Số chứng chỉ";
        String yearOfExperienceLabel = "Kinh nghiệm";
        String workingHours = "Giờ làm việc";
        String consultationFeeLabel = "Phí khám";
        int maxOrdinalNumberLength = 5;
        int maxIdLength = idLabel.length();
        int maxFullNameLength = fullNameLabel.length();
        int maxGenderLength = genderLabel.length();
        int maxPhoneNumberLength = phoneNumberLabel.length();
        int maxSpecLength = specLabel.length();
        int maxEmailLength = emailLabel.length();
        int maxLicenseNumberLength = licenseNumberLabel.length();
        int maxYearOfExperienceLength = yearOfExperienceLabel.length();
        int maxWorkingHoursLength = workingHours.length();
        int maxConsultationFeeLength = consultationFeeLabel.length();
        for (DoctorEntity doctor : doctors) {
            maxIdLength = Math.max(maxIdLength, String.valueOf(doctor.getId()).length());
            maxFullNameLength = Math.max(maxFullNameLength, doctor.getFullName().length());
            maxGenderLength = Math.max(maxGenderLength, String.valueOf(doctor.getGender()).length());
            maxPhoneNumberLength = Math.max(maxPhoneNumberLength, doctor.getPhoneNumber().length());
            maxSpecLength = Math.max(maxSpecLength, doctor.getSpecialization().getDisplayName().length());
            maxEmailLength = Math.max(maxEmailLength, doctor.getEmail().length());
            maxLicenseNumberLength = Math.max(maxLicenseNumberLength, doctor.getLicenseNumber().length());
            maxYearOfExperienceLength = Math.max(maxYearOfExperienceLength, (doctor.getYearOfExperience().toString() + " năm").length());
            maxWorkingHoursLength = Math.max(maxWorkingHoursLength, doctor.getWorkingHours().length());
            maxConsultationFeeLength = Math.max(maxConsultationFeeLength, PersonHelper.formatVietNamCurrency(doctor.getConsultationFee()).length());
        }

        // Tạo định dạng cho dòng
        String lineFormat = "+-" + "-".repeat(maxOrdinalNumberLength) + "-+-" +
                "-".repeat(maxIdLength) + "-+-" +
                "-".repeat(maxFullNameLength) + "-+-" +
                "-".repeat(maxGenderLength) + "-+-" +
                "-".repeat(maxPhoneNumberLength) + "-+-" +
                "-".repeat(maxSpecLength) + "-+-" +
                "-".repeat(maxEmailLength) + "-+-" +
                "-".repeat(maxLicenseNumberLength) + "-+-" +
                "-".repeat(maxYearOfExperienceLength) + "-+-" +
                "-".repeat(maxWorkingHoursLength) + "-+-" +
                "-".repeat(maxConsultationFeeLength) + "-+%n";

        // Tạo định dạng cho dữ liệu
        String dataFormat = "| %-" +
                maxOrdinalNumberLength + "s | %-" +
                maxIdLength + "s | %-" +
                maxFullNameLength + "s | %-" +
                maxGenderLength + "s | %-" +
                maxPhoneNumberLength + "s | %-" +
                maxSpecLength + "s | %-" +
                maxEmailLength + "s | %-" +
                maxLicenseNumberLength + "s | %-" +
                maxYearOfExperienceLength + "s | %-" +
                maxWorkingHoursLength + "s | %-" +
                maxConsultationFeeLength + "s |%n";

        // In bảng
        System.out.printf(lineFormat);
        System.out.printf(dataFormat, ordinalNumberLabel, idLabel, fullNameLabel, genderLabel, phoneNumberLabel,
                specLabel, emailLabel, licenseNumberLabel, yearOfExperienceLabel, workingHours, consultationFeeLabel);
        System.out.printf(lineFormat);
        int i = 1;
        for (DoctorEntity doctor : doctors) {
            System.out.printf(dataFormat,
                    i,
                    doctor.getId(),
                    doctor.getFullName(),
                    PersonHelper.getGenderDisplay(doctor.getGender()),
                    doctor.getPhoneNumber(),
                    doctor.getSpecialization().getDisplayName(),
                    doctor.getEmail(),
                    doctor.getLicenseNumber(),
                    String.format("%02d năm", doctor.getYearOfExperience()),
                    doctor.getWorkingHours(),
                    PersonHelper.formatVietNamCurrency(doctor.getConsultationFee()));
            i++;
        }
        System.out.printf(lineFormat);
    }

    public void showDetail(DoctorEntity doctor) {
        System.out.println("=".repeat(ConfigurationConstants.SEPARATION_LENGTH));
        ConsoleUtil.printlnBold("\uD83D\uDC68\u200D⚕\uFE0F CHI TIẾT BÁC SĨ");
        System.out.print("\uD83C\uDD94 Mã bác sĩ: ");
        ConsoleUtil.printlnItalic(doctor.getId());
        System.out.print("📝 Họ và tên: ");
        ConsoleUtil.printlnItalic(doctor.getFullName());
        System.out.print("\uD83D\uDC64 Giới tính: ");
        ConsoleUtil.printlnItalic(PersonHelper.getGenderDisplay(doctor.getGender()));
        System.out.print("📞 Số điện thoại: ");
        ConsoleUtil.printlnItalic(doctor.getPhoneNumber());
        System.out.print("\uD83C\uDFE5 Chuyên khoa: ");
        ConsoleUtil.printlnItalic(doctor.getSpecialization().getDisplayName());
        System.out.print("\uD83D\uDCE7 Email: ");
        ConsoleUtil.printlnItalic(doctor.getEmail());
        System.out.print("\uD83C\uDD94 Số chứng chỉ: ");
        ConsoleUtil.printlnItalic(doctor.getLicenseNumber());
        System.out.print("⭐ Kinh nghiệm: ");
        ConsoleUtil.printlnItalic(String.format("%02d năm", doctor.getYearOfExperience()));
        System.out.print("\uD83D\uDD50 Giờ làm việc: ");
        ConsoleUtil.printlnItalic(doctor.getWorkingHours());
        System.out.print("\uD83D\uDCB0 Phí khám: ");
        ConsoleUtil.printlnItalic(PersonHelper.formatVietNamCurrency(doctor.getConsultationFee()));
        System.out.println("=".repeat(ConfigurationConstants.SEPARATION_LENGTH));
    }

    public DoctorEntity inputDoctor() {
        return inputDoctor(null, false);
    }

    public DoctorEntity inputDoctor(DoctorEntity doctor, boolean isUpdate) {
        if (isUpdate) {
            ConsoleColorUtil.printlnYellow("Để trống nếu không muốn cập nhật thông tin nào đó...");
        }
        String fullName = InputValidatorUtil.inputString("📝 Họ và tên: ", "họ và tên", 3, 50, isUpdate);
        Boolean gender = CommonView.selectGender(isUpdate);
        String phoneNumber = InputValidatorUtil.inputVietnamPhoneNumber("📞 Số điện thoại: ", "số điện thoại", isUpdate);
        DoctorSpecialization specialization = selectSpecialization(isUpdate);
        String email = InputValidatorUtil.inputEmail("\uD83D\uDCE7 Email: ", "email", isUpdate);
        String licenseNumber = InputValidatorUtil.inputString("\uD83C\uDD94 Số chứng chỉ: ", "số chứng chỉ", 3, 50, isUpdate);
        Integer yearOfExperience = InputValidatorUtil.inputInteger("⭐ Kinh nghiệm (số năm): ", "kinh nghiệm", 0, 30, isUpdate);
        LocalTime startTime = InputValidatorUtil.inputWorkingTime("🕐 Giờ bắt đầu ca (HH:mm): ", "giờ bắt đầu", isUpdate);
        LocalTime endTime = null;
        if (startTime != null) {
            endTime = InputValidatorUtil.inputTimeRange("🕐 Giờ kết thúc ca (HH:mm): ", "giờ kết thúc", startTime, isUpdate);
        }
        String workingHours = null;
        if (startTime != null && endTime != null) {
            workingHours = DateUtil.formatTime(startTime) + "-" + DateUtil.formatTime(endTime);
        }
        Double consultationFee = InputValidatorUtil.inputDouble("\uD83D\uDCB0 Phí khám: ", "phí khám", 0, Double.MAX_VALUE, isUpdate);
        if (isUpdate) {
            if (fullName == null) fullName = doctor.getFullName();
            if (gender == null) gender = doctor.getGender();
            if (phoneNumber == null) phoneNumber = doctor.getPhoneNumber();
            if (specialization == null) specialization = doctor.getSpecialization();
            if (email == null) email = doctor.getEmail();
            if (licenseNumber == null) licenseNumber = doctor.getLicenseNumber();
            if (yearOfExperience == null) yearOfExperience = doctor.getYearOfExperience();
            if (workingHours == null) workingHours = doctor.getWorkingHours();
            if (consultationFee == null) consultationFee = doctor.getConsultationFee();
            return new DoctorEntity(doctor.getId(), fullName, gender, phoneNumber, specialization, email, licenseNumber, yearOfExperience, workingHours, consultationFee);
        } else {
            return new DoctorEntity(fullName, gender, phoneNumber, specialization, email, licenseNumber, yearOfExperience, workingHours, consultationFee);
        }
    }

    public DoctorSpecialization selectSpecialization(String message, boolean allowEmpty) {
        String specialization;
        System.out.println(message);
        int index = 1;
        int breakLineIndex = 4;
        int maxCodeLength = 0;
        int maxDisplayNameLength = 0;
        int specListLength = DoctorSpecialization.values().length;
        for (DoctorSpecialization spec : DoctorSpecialization.values()) {
            if (spec.getCode().length() > maxCodeLength && spec != DoctorSpecialization.UNKNOWN) {
                maxCodeLength = spec.getCode().length();
            }
            if (spec.getDisplayName().length() > maxDisplayNameLength) {
                maxDisplayNameLength = spec.getDisplayName().length();
            }
        }
        for (DoctorSpecialization spec : DoctorSpecialization.values()) {
            System.out.printf("[%-" + maxCodeLength + "s] %-" + (maxDisplayNameLength + 5) + "s", spec.getCode(), spec.getDisplayName());
            if (index % breakLineIndex == 0 && index != specListLength) {
                System.out.println();
            }
            index++;
        }
        System.out.println();
        while (true) {
            try {
                System.out.print("Nhập chuyên ngành tương ứng (Có thể nhập đầy đủ tên hoặc mã): ");
                specialization = sc.nextLine();
                if (specialization.isEmpty() && allowEmpty) {
                    return null;
                }
                return DoctorSpecialization.from(specialization);
            } catch (IllegalArgumentException e) {
                ConsoleColorUtil.printlnRed("Vui lòng nhập 1 chuyên ngành hợp lệ.");
            }
        }
    }

    public DoctorSpecialization selectSpecialization(boolean allowEmpty) {
        return selectSpecialization("\uD83C\uDFE5 Chuyên ngành: ", allowEmpty);
    }

    public void displaySearchMenu() {
        System.out.println("\uD83D\uDC64 1. Tìm theo tên");
        System.out.println("\uD83C\uDFE5 2. Tìm theo chuyên khoa");
        System.out.println("\uD83D\uDCDE 3. Tìm theo số điện thoại");
        System.out.println("⭐ 4. Tìm theo số năm kinh nghiệm");
    }

    public void displayDetailMenu() {
        System.out.println("✏\uFE0F 1. Cập nhật thông tin");
        System.out.println("\uD83D\uDDD1\uFE0F 2. Xoá bệnh nhân");
        System.out.println("\uD83D\uDD19 0. Trở về");
    }
}
