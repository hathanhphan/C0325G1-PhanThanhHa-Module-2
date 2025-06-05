package case_study_hospital_management.repository;

import case_study_hospital_management.entity.PatientEntity;

import java.util.List;

public interface PatientRepository extends IRepository<PatientEntity> {
    List<PatientEntity> findByName(String name);
    List<PatientEntity> findByPhoneNumber(String phoneNumber);
}
