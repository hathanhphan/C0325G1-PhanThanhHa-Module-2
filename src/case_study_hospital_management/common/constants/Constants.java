package case_study_hospital_management.common.constants;

import java.time.format.DateTimeFormatter;

public class Constants {
    public static final String DATA_FOLDER = "D:/CodeGym/module2/src/case_study_hospital_management/data/";
    public static final String PATIENTS_FILE = "patients.csv";
    public static final String DOCTORS_FILE = "doctors.csv";
    public static final String APPOINTMENTS_FILE = "appointments.csv";
    public static final String MEDICAL_RECORDS_FILE = "medical_records.csv";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final int SEPARATION_LENGTH = 60;
}
