package case_study_hospital_management.service.impl;

import case_study_hospital_management.entity.PatientEntity;
import case_study_hospital_management.repository.PatientRepository;
import case_study_hospital_management.repository.impl.PatientRepositoryImpl;
import case_study_hospital_management.service.PatientService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PatientServiceImpl implements PatientService {
    private static PatientServiceImpl instance;
    private final PatientRepository patientRepository;

    private PatientServiceImpl() {
        patientRepository = PatientRepositoryImpl.getInstance();
    }

    public static synchronized PatientServiceImpl getInstance() {
        if (instance == null) {
            instance = new PatientServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean registerPatient(PatientEntity patient) {
        patient.setId(generateId());
        patient.setRegistrationDate(LocalDate.now());
        return patientRepository.save(patient);
    }

    @Override
    public List<PatientEntity> findByName(String name) {
        return patientRepository.findByName(name);
    }

    @Override
    public List<PatientEntity> findByPhoneNumber(String phoneNumber) {
        return patientRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<PatientEntity> getAll() {
        return patientRepository.findAll().stream().filter(p -> !p.getDeleted()).toList();
    }

    @Override
    public PatientEntity findById(String id) {
        return patientRepository.findById(id);
    }

    @Override
    public boolean update(PatientEntity patient) {
        return patientRepository.update(patient);
    }

    @Override
    public boolean delete(String id) {
        return patientRepository.delete(id);
    }

    @Override
    public String generateId() {
        List<PatientEntity> patients = patientRepository.findAll();
        int maxId = patients.stream().mapToInt(p -> Integer.parseInt(p.getId().substring(1))).max().orElse(0);
        return String.format("P%03d", maxId + 1);
    }
}
