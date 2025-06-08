package case_study_hospital_management.service;

import case_study_hospital_management.common.enums.DoctorSpecialization;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.DoctorEntity;

import java.util.List;

public interface DoctorService extends IService<DoctorEntity> {
    boolean add(DoctorEntity doctor);
    List<DoctorEntity> findByName(String name);
    List<AppointmentEntity> findALlWorkSchedule(String id);
    List<DoctorEntity> findBySpecialization(DoctorSpecialization doctorSpecialization);
    List<DoctorEntity> findByYearOfExperience(int numberOfYear);
    List<DoctorEntity> findByPhoneNumber(String phoneNumber);
    List<DoctorEntity> findByKeyword(String keyword);
}
