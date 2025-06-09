package case_study_hospital_management.common.enums;

public enum StatisticAppointmentCriteria {
    SCHEDULED("Chờ khám", "SCHEDULED"),
    COMPLETED("Hoàn thành", "COMPLETED"),
    EXAMINATION("Đang khám", "EXAMINATION"),
    CANCELLED("Đã huỷ", "CANCELLED"),
    NO_SHOW("Không đến khám", "NO_SHOW"),
    RESCHEDULED("Đã dời lịch", "RESCHEDULED"),
    UNKNOWN("Chưa xác định", "UNKNOWN"),
    TOTAL("Tổng lịch khám", "TOTAL"),
    COMPLETED_PERCENT("Tỉ lệ hoàn thành", "COMPLETED_PERCENT"),
    CANCELLED_PERCENT("Tỉ lệ huỷ", "CANCELLED_PERCENT"),
    ATTENDANCE_PERCENT("Tỉ lệ đến", "ATTENDANCE_PERCENT"),
    RESCHEDULED_PERCENT("Tỉ lệ dời", "RESCHEDULED_PERCENT");

    private final String displayName;
    private final String code;

    StatisticAppointmentCriteria(String displayName, String code) {
        this.displayName = displayName;
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCode() {
        return code;
    }

    public static StatisticAppointmentCriteria fromCode(String code) {
        for (StatisticAppointmentCriteria criteria : values()) {
            if (criteria.code.equalsIgnoreCase(code)) {
                return criteria;
            }
        }
        throw new IllegalArgumentException();
    }
}
