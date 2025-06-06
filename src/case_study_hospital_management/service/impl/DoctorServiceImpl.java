package case_study_hospital_management.service.impl;

import case_study_hospital_management.common.enums.DoctorSpecialization;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.DoctorEntity;
import case_study_hospital_management.repository.DoctorRepository;
import case_study_hospital_management.repository.impl.DoctorRepositoryImpl;
import case_study_hospital_management.service.DoctorService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {
    private static DoctorServiceImpl instance;
    private final DoctorRepository doctorRepository;

    private DoctorServiceImpl() {
        doctorRepository = DoctorRepositoryImpl.getInstance();
    }

    public static synchronized DoctorServiceImpl getInstance() {
        if (instance == null) {
            instance = new DoctorServiceImpl();
        }
        return instance;
    }
    @Override
    public boolean add(DoctorEntity doctor) {
        doctor.setId(generateId());
        return doctorRepository.save(doctor);
    }

    @Override
    public List<DoctorEntity> findByName(String name) {
        return doctorRepository.findByName(name);
    }

    @Override
    public List<AppointmentEntity> findALlWorkSchedule(String id) {
        return doctorRepository.findALlWorkSchedule(id);
    }

    @Override
    public List<DoctorEntity> findBySpecialization(DoctorSpecialization doctorSpecialization) {
        return doctorRepository.findBySpecialization(doctorSpecialization);
    }

    @Override
    public List<DoctorEntity> findByYearOfExperience(int numberOfYear) {
        return doctorRepository.findByYearOfExperience(numberOfYear);
    }

    @Override
    public List<DoctorEntity> findByPhoneNumber(String phoneNumber) {
        return doctorRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<DoctorEntity> getAll() {
        return doctorRepository.findAll().stream().filter(d -> !d.getDeleted()).toList();
    }

    @Override
    public DoctorEntity findById(String id) {
        return doctorRepository.findById(id);
    }

    @Override
    public boolean update(DoctorEntity doctorEntity) {
        return doctorRepository.update(doctorEntity);
    }

    @Override
    public boolean delete(String id) {
        return doctorRepository.delete(id);
    }

    @Override
    public String generateId() {
        List<DoctorEntity> doctors = doctorRepository.findAll();
        int maxId = doctors.stream().mapToInt(d -> Integer.parseInt(d.getId().substring(1))).max().orElse(0);
        return String.format("D%06d", maxId + 1);
    }
}
