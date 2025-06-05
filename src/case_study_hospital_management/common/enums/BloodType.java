package case_study_hospital_management.common.enums;

public enum BloodType {
    O_POSITIVE("O+"),
    O_NEGATIVE("O-"),
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    UNKNOWN("Chưa xác định");

    private final String displayName;

    BloodType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static BloodType fromDisplayName(String displayName) {
        for (BloodType bloodType : values()) {
            if (bloodType.displayName.equalsIgnoreCase(displayName)) {
                return bloodType;
            }
        }
        throw new IllegalArgumentException();
    }
}
