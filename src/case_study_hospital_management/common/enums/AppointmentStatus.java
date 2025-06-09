package case_study_hospital_management.common.enums;

public enum AppointmentStatus {
    SCHEDULED("Đã đặt lịch", "SCHEDULED"),
    COMPLETED("Đã hoàn thành", "COMPLETED"),
    EXAMINATION("Đang khám", "EXAMINATION"),
    CANCELLED("Đã huỷ", "CANCELLED"),
    NO_SHOW("Không đến khám", "NO_SHOW"),
    RESCHEDULED("Đã dời lịch", "RESCHEDULED"),
    UNKNOWN("Chưa xác định", "UNKNOWN");

    private final String displayName;
    private final String code;

    AppointmentStatus(String displayName, String code) {
        this.displayName = displayName;
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCode() {
        return code;
    }

    public static AppointmentStatus fromCode(String code) {
        for (AppointmentStatus status : values()) {
            if (status.code.equalsIgnoreCase(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException();
    }

    public static AppointmentStatus fromDisplayName(String displayName) {
        for (AppointmentStatus status : values()) {
            if (status.displayName.equalsIgnoreCase(displayName)) {
                return status;
            }
        }
        throw new IllegalArgumentException();
    }

    public static AppointmentStatus from(String value) {
        for (AppointmentStatus status : values()) {
            if (status.displayName.equalsIgnoreCase(value) || status.code.equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean isActive() {
        return this == SCHEDULED || this == RESCHEDULED;
    }

    public boolean isCompleted() {
        return this == COMPLETED;
    }

    public boolean isCancelled() {
        return this == CANCELLED || this == NO_SHOW;
    }
}
