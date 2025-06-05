package case_study_hospital_management.view;

import case_study_hospital_management.common.enums.BloodType;
import case_study_hospital_management.entity.PatientEntity;
import case_study_hospital_management.repository.impl.PatientRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PatientRepositoryImpl patientRepository = PatientRepositoryImpl.getInstance();
        patientRepository.update(new PatientEntity("a", "aaaaaa", true, "0334859991", LocalDate.parse("2000-10-30"), "a", "a", BloodType.A_NEGATIVE, "Không", LocalDate.parse("2025-10-30")));
        patientRepository.delete("a");
        List<PatientEntity> patients = patientRepository.findAll();
        patients.forEach(System.out::println);
    }
}
