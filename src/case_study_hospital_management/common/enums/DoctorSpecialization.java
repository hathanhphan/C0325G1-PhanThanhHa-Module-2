package case_study_hospital_management.common.enums;

public enum DoctorSpecialization {
    CARDIOLOGY("Tim mạch", "CARD"),
    PEDIATRICS("Nhi khoa", "PEDI"),
    SURGERY("Ngoại khoa", "SURG"),
    OBSTETRICS_GYNECOLOGY("Sản phụ khoa", "OBGY"),
    NEUROLOGY("Thần kinh", "NEUR"),
    DERMATOLOGY("Da liễu", "DERM"),
    ORTHOPEDICS("Chấn thương chỉnh hình", "ORTH"),
    OPHTHALMOLOGY("Mắt", "OPHT"),
    ENT("Tai mũi họng", "ENT"),
    ENDOCRINOLOGY("Nội tiết", "ENDO"),
    GASTROENTEROLOGY("Tiêu hóa", "GAST"),
    PULMONOLOGY("Hô hấp", "PULM"),
    UROLOGY("Tiết niệu", "UROL"),
    PSYCHIATRY("Tâm thần", "PSYC"),
    RADIOLOGY("Chẩn đoán hình ảnh", "RADI"),
    ANESTHESIOLOGY("Gây mê hồi sức", "ANES"),
    EMERGENCY("Cấp cứu", "EMER"),
    FAMILY_MEDICINE("Bác sĩ gia đình", "FAMI"),
    INTERNAL_MEDICINE("Nội tổng quát", "INTE"),
    UNKNOWN("Chưa xác định", "UNKNOWN");

    private final String displayName;
    private final String code;

    DoctorSpecialization(String displayName, String code) {
        this.displayName = displayName;
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCode() {
        return code;
    }

    public static DoctorSpecialization fromDisplayName(String displayName) {
        for (DoctorSpecialization spec : values()) {
            if (spec.displayName.equalsIgnoreCase(displayName)) {
                return spec;
            }
        }
        return UNKNOWN;
    }

    public static DoctorSpecialization fromCode(String code) {
        for (DoctorSpecialization spec : values()) {
            if (spec.code.equalsIgnoreCase(code)) {
                return spec;
            }
        }
        return UNKNOWN;
    }
}
