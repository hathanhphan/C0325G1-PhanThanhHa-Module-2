package case_study_hospital_management.service;

import case_study_hospital_management.entity.PatientEntity;
import case_study_hospital_management.repository.IRepository;

import java.util.List;

public interface PatientService extends IService<PatientEntity> {
    boolean registerPatient(PatientEntity patient);
    List<PatientEntity> findByName(String name);
    List<PatientEntity> findByPhoneNumber(String phoneNumber);
}
