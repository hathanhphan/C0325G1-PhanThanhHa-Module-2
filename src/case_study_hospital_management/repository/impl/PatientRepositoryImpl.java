package case_study_hospital_management.repository.impl;

import case_study_hospital_management.common.constants.Constants;
import case_study_hospital_management.common.enums.BloodType;
import case_study_hospital_management.entity.PatientEntity;
import case_study_hospital_management.repository.PatientRepository;
import case_study_hospital_management.util.CSVUtil;
import case_study_hospital_management.util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientRepositoryImpl implements PatientRepository {
    private static PatientRepositoryImpl instance;
    private String fileName;

    private PatientRepositoryImpl() {
        this.fileName = Constants.PATIENTS_FILE;
    }

    public static synchronized PatientRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new PatientRepositoryImpl();
        }
        return instance;
    }

    @Override
    public List<PatientEntity> findByName(String name) {
        List<PatientEntity> patients = findAll();
        if (patients.isEmpty()) {
            return null;
        }
        List<PatientEntity> foundPatients = new ArrayList<>();
        for (PatientEntity patient : patients) {
            if (patient.getFullName().toLowerCase().contains(name.toLowerCase())) {
                foundPatients.add(patient);
            }
        }
        return foundPatients;
    }

    @Override
    public List<PatientEntity> findByPhoneNumber(String phoneNumber) {
        List<PatientEntity> patients = findAll();
        if (patients.isEmpty()) {
            return null;
        }
        List<PatientEntity> foundPatients = new ArrayList<>();
        for (PatientEntity patient : patients) {
            if (patient.getPhoneNumber().contains(phoneNumber)) {
                foundPatients.add(patient);
            }
        }
        return foundPatients;
    }

    @Override
    public List<PatientEntity> findAll() {
        List<PatientEntity> patients = new ArrayList<>();
        List<String> lines = CSVUtil.readCSVFile(fileName);
        for (String line : lines) {
            String[] properties = CSVUtil.parseCsvLine(line);
            PatientEntity patient = new PatientEntity(
                    properties[0],
                    properties[1],
                    properties[2].equals("null") ? null : Boolean.parseBoolean(properties[2]),
                    properties[3],
                    DateUtil.parseDate(properties[4]),
                    properties[5],
                    properties[6],
                    BloodType.fromDisplayName(properties[7]),
                    properties[8],
                    DateUtil.parseDate(properties[9])
            );
            patient.setDeleted(Boolean.parseBoolean(properties[10]));
            patients.add(patient);
        }
        return patients;
    }

    @Override
    public int findIndexById(String id) {
        List<PatientEntity> patients = findAll().stream().filter(p -> !p.getDeleted()).toList();
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public PatientEntity findById(String id) {
        List<PatientEntity> patients = findAll().stream().filter(p -> !p.getDeleted()).toList();
        for (PatientEntity patient : patients) {
            if (patient.getId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

    @Override
    public boolean save(PatientEntity patient) {
        List<PatientEntity> patients = findAll();
        patients.add(patient);
        return CSVUtil.writeCSVFile(fileName, patients);
    }

    @Override
    public boolean update(PatientEntity patient) {
        List<PatientEntity> patients = findAll();
        int foundIndex = findIndexById(patient.getId());
        if (foundIndex > -1) {
            patients.set(foundIndex, patient);
            return CSVUtil.writeCSVFile(fileName, patients);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        List<PatientEntity> patients = new ArrayList<>(findAll());
        int foundIndex = findIndexById(id);
        if (foundIndex > -1) {
            PatientEntity patient = patients.get(foundIndex);
            patient.setDeleted(true);
            patients.set(foundIndex, patient);
            return CSVUtil.writeCSVFile(fileName, patients);
        }
        return false;
    }
}
