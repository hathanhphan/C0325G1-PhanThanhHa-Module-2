package case_study_hospital_management.common.enums;

public enum DayOfWeekInVietnamese {
    MONDAY("Thứ 2", "MONDAY"),
    TUESDAY("Thứ 3", "TUESDAY"),
    WEDNESDAY("Thứ 4", "WEDNESDAY"),
    THURSDAY("Thứ 5", "THURSDAY"),
    FRIDAY("Thứ 6", "FRIDAY"),
    SATURDAY("Thứ 7", "SATURDAY"),
    SUNDAY("Chủ nhật", "SUNDAY");

    private final String displayName;
    private final String code;

    DayOfWeekInVietnamese(String displayName, String code) {
        this.displayName = displayName;
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCode() {
        return code;
    }

    public static DayOfWeekInVietnamese fromCode(String code) {
        for (DayOfWeekInVietnamese status : values()) {
            if (status.code.equalsIgnoreCase(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException();
    }
}
