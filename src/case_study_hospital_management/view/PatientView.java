package case_study_hospital_management.view;

import case_study_hospital_management.common.constants.Constants;
import case_study_hospital_management.common.enums.BloodType;
import case_study_hospital_management.entity.PatientEntity;
import case_study_hospital_management.util.ConsoleUtil;
import case_study_hospital_management.util.DateUtil;
import case_study_hospital_management.util.InputValidatorUtil;
import ss12_java_collection_framework.bai_tap.student_management.util.ConsoleColorUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PatientView {
    private static final Scanner sc = new Scanner(System.in);
    private static PatientView instance;
    private PatientView() {
    }

    public static synchronized PatientView getInstance() {
        if (instance == null) {
            instance = new PatientView();
        }
        return instance;
    }

    private String getGenderDisplay(PatientEntity patient) {
        String genderDisplay = "Khác";
        if (patient.getGender() != null && patient.getGender()) {
            genderDisplay = "Nam";
        } else if ((patient.getGender() != null && !patient.getGender())) {
            genderDisplay = "Nữ";
        }
        return genderDisplay;
    }

    public void displayMenu() {
        System.out.println();
        System.out.println("=".repeat(Constants.SEPARATION_LENGTH));
        ConsoleUtil.printlnBold("\uD83D\uDC65 QUẢN LÝ BỆNH NHÂN");
        ConsoleUtil.printlnBold("➕ 1. ĐĂNG KÝ BỆNH NHÂN MỚI");
        ConsoleUtil.printlnItalic("\t\uD83D\uDCDD Nhập thông tin bệnh nhân mới vào hệ thống");
        ConsoleUtil.printlnBold("\uD83D\uDD0D 2. TÌM KIẾM BỆNH NHÂN");
        ConsoleUtil.printlnItalic("\t\uD83C\uDD94 Tìm theo mã bệnh nhân");
        ConsoleUtil.printlnItalic("\t\uD83D\uDC64 Tìm theo tên");
        ConsoleUtil.printlnItalic("\t\uD83D\uDCDE Tìm theo số điện thoại");
        ConsoleUtil.printlnBold("\uD83D\uDCCB 3. DANH SÁCH BỆNH NHÂN");
        ConsoleUtil.printlnItalic("\t\uD83D\uDCCA Hiển thị toàn bộ danh sách");
        ConsoleUtil.printlnBold("✏\uFE0F 4. CẬP NHẬT THÔNG TIN");
        ConsoleUtil.printlnItalic("\t\uD83D\uDD04 Sửa đổi thông tin bệnh nhân");
        ConsoleUtil.printlnBold("\uD83D\uDDD1\uFE0F 5. XÓA BỆNH NHÂN");
        ConsoleUtil.printlnItalic("\t⚠\uFE0F Xóa bệnh nhân khỏi hệ thống (cẩn thận!)");
        ConsoleUtil.printlnBold("\uD83D\uDCC8 6. THỐNG KÊ BỆNH NHÂN");
        ConsoleUtil.printlnItalic("\t\uD83D\uDCCA Theo độ tuổi, giới tính, nhóm máu");
        ConsoleUtil.printlnBold("\uD83D\uDD19 0. QUAY LẠI MENU CHÍNH");
        System.out.println("=".repeat(Constants.SEPARATION_LENGTH));
    }

    public void display(List<PatientEntity> patients, String title) {
        System.out.println();
        System.out.println("=".repeat(title.length() / 2) + title + "=".repeat(title.length() / 2));
        displayAtTable(patients);
    }

    public void display(List<PatientEntity> patients) {
        display(patients, "DANH SÁCH BỆNH NHÂN");
    }

    public void displayAtTable(List<PatientEntity> patients) {
        String ordinalNumberLabel = "STT";
        String idLabel = "Mã BN";
        String fullNameLabel = "Họ tên";
        String genderLabel = "Giới tính";
        String phoneNumberLabel = "Số điện thoại";
        String dobLabel = "Ngày sinh";
        String addressLabel = "Địa chỉ";
        String emergencyContactLabel = "Liên hệ khẩn cấp";
        String bloodTypeLabel = "Nhóm máu";
        String allergiesLabel = "Dị ứng";
        String registrationDateLabel = "Ngày đăng kí";
        int maxOrdinalNumberLength = 5;
        int maxIdLength = idLabel.length();
        int maxFullNameLength = fullNameLabel.length();
        int maxGenderLength = genderLabel.length();
        int maxPhoneNumberLength = phoneNumberLabel.length();
        int maxDobLength = dobLabel.length();
        int maxAddressLength = addressLabel.length();
        int maxEmergencyContactLength = emergencyContactLabel.length();
        int maxBloodTypeLength = bloodTypeLabel.length();
        int maxAllergiesLength = allergiesLabel.length();
        int maxRegistrationDateLength = registrationDateLabel.length();
        for (PatientEntity patient : patients) {
            maxIdLength = Math.max(maxIdLength, String.valueOf(patient.getId()).length());
            maxFullNameLength = Math.max(maxFullNameLength, patient.getFullName().length());
            maxGenderLength = Math.max(maxGenderLength, String.valueOf(patient.getGender()).length());
            maxPhoneNumberLength = Math.max(maxPhoneNumberLength, patient.getPhoneNumber().length());
            maxDobLength = Math.max(maxDobLength, patient.getDob().toString().length());
            maxAddressLength = Math.max(maxAddressLength, patient.getAddress().length());
            maxEmergencyContactLength = Math.max(maxEmergencyContactLength, patient.getEmergencyContact().length());
            maxBloodTypeLength = Math.max(maxBloodTypeLength, patient.getBloodType().getDisplayName().length());
            maxAllergiesLength = Math.max(maxAllergiesLength, patient.getAllergies().length());
            maxRegistrationDateLength = Math.max(maxRegistrationDateLength, patient.getRegistrationDate().toString().length());
        }

        // Tạo định dạng cho dòng
        String lineFormat = "+-" + "-".repeat(maxOrdinalNumberLength) + "-+-" +
                "-".repeat(maxIdLength) + "-+-" +
                "-".repeat(maxFullNameLength) + "-+-" +
                "-".repeat(maxGenderLength) + "-+-" +
                "-".repeat(maxPhoneNumberLength) + "-+-" +
                "-".repeat(maxDobLength) + "-+-" +
                "-".repeat(maxAddressLength) + "-+-" +
                "-".repeat(maxEmergencyContactLength) + "-+-" +
                "-".repeat(maxBloodTypeLength) + "-+-" +
                "-".repeat(maxAllergiesLength) + "-+-" +
                "-".repeat(maxRegistrationDateLength) + "-+%n";

        // Tạo định dạng cho dữ liệu
        String dataFormat = "| %-" +
                maxOrdinalNumberLength + "s | %-" +
                maxIdLength + "s | %-" +
                maxFullNameLength + "s | %-" +
                maxGenderLength + "s | %-" +
                maxPhoneNumberLength + "s | %-" +
                maxDobLength + "s | %-" +
                maxAddressLength + "s | %-" +
                maxEmergencyContactLength + "s | %-" +
                maxBloodTypeLength + "s | %-" +
                maxAllergiesLength + "s | %-" +
                maxRegistrationDateLength + "s |%n";

        // In bảng
        System.out.printf(lineFormat);
        System.out.printf(dataFormat, ordinalNumberLabel, idLabel, fullNameLabel, genderLabel, phoneNumberLabel,
                dobLabel, addressLabel, emergencyContactLabel, bloodTypeLabel, allergiesLabel, registrationDateLabel);
        System.out.printf(lineFormat);
        int i = 1;
        for (PatientEntity patient : patients) {
            System.out.printf(dataFormat,
                    i,
                    patient.getId(),
                    patient.getFullName(),
                    getGenderDisplay(patient),
                    patient.getPhoneNumber(),
                    DateUtil.formatDate(patient.getDob()),
                    patient.getAddress(),
                    patient.getEmergencyContact(),
                    patient.getBloodType().getDisplayName(),
                    patient.getAllergies(),
                    DateUtil.formatDate(patient.getRegistrationDate()));
            i++;
        }
        System.out.printf(lineFormat);
    }

    public void showDetail(PatientEntity patient) {
        System.out.println("=".repeat(Constants.SEPARATION_LENGTH));
        ConsoleUtil.printlnBold("\uD83D\uDCDD MÃ BỆNH NHÂN: " + patient.getId());
        ConsoleUtil.printlnBold("\uD83D\uDCC5 NGÀY ĐĂNG KÝ: " + DateUtil.formatDate(patient.getRegistrationDate()));
        System.out.println("-".repeat(Constants.SEPARATION_LENGTH / 2));
        ConsoleUtil.printlnBold("\uD83D\uDC64 THÔNG TIN CÁ NHÂN");
        System.out.print("📝 Họ và tên: ");
        ConsoleUtil.printlnItalic(patient.getFullName());
        System.out.print("\uD83D\uDC64 Giới tính: ");
        ConsoleUtil.printlnItalic(getGenderDisplay(patient));
        System.out.print("📞 Số điện thoại: ");
        ConsoleUtil.printlnItalic(patient.getPhoneNumber());
        System.out.print("🎂 Ngày sinh: ");
        ConsoleUtil.printlnItalic(DateUtil.formatDate(patient.getDob()));
        System.out.print("🏠 Địa chỉ: ");
        ConsoleUtil.printlnItalic(patient.getAddress());
        System.out.print("🆘 Liên hệ khẩn cấp: ");
        ConsoleUtil.printlnItalic(patient.getEmergencyContact());
        System.out.println("-".repeat(Constants.SEPARATION_LENGTH / 2));
        ConsoleUtil.printlnBold("\uD83E\uDE78 THÔNG TIN Y TẾ");
        System.out.print("\uD83E\uDE78 Nhóm máu: ");
        ConsoleUtil.printlnItalic(patient.getBloodType().getDisplayName());
        System.out.print("⚠\uFE0F Dị ứng: ");
        ConsoleUtil.printlnItalic(patient.getAllergies());
        System.out.println("=".repeat(Constants.SEPARATION_LENGTH));
    }

    public PatientEntity inputPatient() {
        return inputPatient(null, false);
    }

    public PatientEntity inputPatient(PatientEntity patient, boolean isUpdate) {
        if (isUpdate) {
            ConsoleColorUtil.printlnYellow("Để trống nếu không muốn cập nhật thông tin nào đó...");
        }
        System.out.println("\uD83D\uDC64 NHẬP THÔNG TIN CÁ NHÂN");
        String fullName = InputValidatorUtil.inputString("📝 Họ và tên: ", "họ và tên", 3, 50, isUpdate);
        Boolean gender = selectGender(isUpdate);
        String phoneNumber = InputValidatorUtil.inputVietnamPhoneNumber("📞 Số điện thoại: ", "số điện thoại", isUpdate);
        LocalDate dob = InputValidatorUtil.inputBirthDate("🎂 Ngày sinh (dd/MM/yyyy): ", "ngày sinh", isUpdate);
        String address = InputValidatorUtil.inputString("🏠 Địa chỉ: ", "địa chỉ", 5, 100, isUpdate);
        String emergencyContact = InputValidatorUtil.inputVietnamPhoneNumber("🆘 Liên hệ khẩn cấp: ", "số điện thoại khẩn cấp", isUpdate);
        System.out.println("\uD83E\uDE78 NHẬP THÔNG TIN Y TẾ");
        BloodType bloodType = selectBloodType(isUpdate);
        String allergies = InputValidatorUtil.inputString("⚠\uFE0F Dị ứng (Tùy chọn): ", "dị ứng", 5, 100, true);

        if (isUpdate) {
            if (fullName == null) fullName = patient.getFullName();
            if (gender == null) gender = patient.getGender();
            if (phoneNumber == null) phoneNumber = patient.getPhoneNumber();
            if (dob == null) dob = patient.getDob();
            if (address == null) address = patient.getAddress();
            if (emergencyContact == null) emergencyContact = patient.getEmergencyContact();
            if (bloodType == null) bloodType = patient.getBloodType();
            if (allergies == null) allergies = patient.getAllergies();
            return new PatientEntity(patient.getId(), fullName, gender, phoneNumber, dob, address, emergencyContact, bloodType, allergies == null ? "Không" : allergies, patient.getRegistrationDate());
        } else {
            return new PatientEntity(fullName, gender, phoneNumber, dob, address, emergencyContact, bloodType, allergies == null ? "Không" : allergies);
        }
    }

    private Boolean selectGender(boolean allowEmpty) {
        int choice;
        System.out.println("\uD83D\uDC64 Chọn giới tính:\t1. Nam\t2. Nữ\t3. Khác");
        while (true) {
            try {
                System.out.print("Lựa chọn của bạn: ");
                String input = sc.nextLine();
                if (input.isEmpty() && allowEmpty) {
                    return null;
                }
                choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        return true;
                    case 2:
                        return false;
                    case 3:
                        return null;
                    default:
                        ConsoleColorUtil.printlnRed("Vui lòng nhập 1 số nguyên tương ứng với giới tính.");
                }
            } catch (NumberFormatException e) {
                ConsoleColorUtil.printlnRed("Vui lòng nhập 1 số nguyên tương ứng với giới tính.");
            }
        }
    }

    private BloodType selectBloodType(boolean allowEmpty) {
        String bloodTypeName;
        System.out.println("\uD83E\uDE78 Chọn nhóm máu:");
        int index = 0;
        int mid = BloodType.values().length / 2;
        for (BloodType bloodType : BloodType.values()) {
            System.out.printf("[%s]\t", bloodType.getDisplayName());
            if (index == mid) {
                System.out.println();
            }
            index++;
        }
        System.out.println();
        while (true) {
            try {
                System.out.print("Nhập nhóm máu tương ứng: ");
                bloodTypeName = sc.nextLine();
                if (bloodTypeName.isEmpty() && allowEmpty) {
                    return null;
                }
                return BloodType.fromDisplayName(bloodTypeName);
            } catch (IllegalArgumentException e) {
                ConsoleColorUtil.printlnRed("Vui lòng nhập 1 nhóm máu hợp lệ hoặc \"Chưa xác định\".");
            }
        }
    }

    public void displaySearchMenu() {
        System.out.println("\uD83C\uDD94 1. Tìm theo mã bệnh nhân");
        System.out.println("\uD83D\uDC64 2. Tìm theo tên");
        System.out.println("\uD83D\uDCDE 3. Tìm theo số điện thoại");
    }

    public void displayDetailMenu() {
        System.out.println("✏\uFE0F 1. Cập nhật thông tin");
        System.out.println("\uD83D\uDDD1\uFE0F 2. Xoá bệnh nhân");
        System.out.println("\uD83D\uDD19 0. Trở về");
    }
}
