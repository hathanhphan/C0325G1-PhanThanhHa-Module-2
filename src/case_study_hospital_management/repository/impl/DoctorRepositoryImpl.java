package case_study_hospital_management.repository.impl;

import case_study_hospital_management.common.constants.ConfigurationConstants;
import case_study_hospital_management.common.enums.DoctorSpecialization;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.DoctorEntity;
import case_study_hospital_management.repository.DoctorRepository;
import case_study_hospital_management.util.CSVUtil;

import java.util.ArrayList;
import java.util.List;

public class DoctorRepositoryImpl implements DoctorRepository {
    private static DoctorRepositoryImpl instance;
    private String fileName;

    private DoctorRepositoryImpl() {
        this.fileName = ConfigurationConstants.DOCTORS_FILE;
    }

    public static synchronized DoctorRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new DoctorRepositoryImpl();
        }
        return instance;
    }

    private List<DoctorEntity> getCurrentList() {
        return findAll().stream().filter(p -> !p.getDeleted()).toList();
    }

    @Override
    public List<DoctorEntity> findByName(String name) {
        return getCurrentList().stream().filter(d -> d.getFullName().toLowerCase().contains(name.toLowerCase())).toList();
    }

    @Override
    public List<AppointmentEntity> findALlWorkSchedule(String id) {
        List<AppointmentEntity> appointments = new ArrayList<>();
        return appointments.stream().filter(a -> a.getDoctorId().equals(id)).toList();
    }

    @Override
    public List<DoctorEntity> findBySpecialization(DoctorSpecialization doctorSpecialization) {
        return getCurrentList().stream().filter(d -> d.getSpecialization().equals(doctorSpecialization)).toList();
    }

    @Override
    public List<DoctorEntity> findByYearOfExperience(int numberOfYear) {
        return getCurrentList().stream().filter(d -> d.getYearOfExperience() == numberOfYear).toList();
    }

    @Override
    public List<DoctorEntity> findByPhoneNumber(String phoneNumber) {
        return getCurrentList().stream().filter(d -> d.getPhoneNumber().equals(phoneNumber)).toList();
    }

    @Override
    public List<DoctorEntity> findAll() {
        List<DoctorEntity> doctors = new ArrayList<>();
        List<String> lines = CSVUtil.readCSVFile(fileName);
        for (String line : lines) {
            String[] properties = CSVUtil.parseCsvLine(line);
            DoctorEntity doctor = new DoctorEntity(
                    properties[0],
                    properties[1],
                    properties[2].equals("null") ? null : Boolean.parseBoolean(properties[2]),
                    properties[3],
                    DoctorSpecialization.fromDisplayName(properties[4]),
                    properties[5],
                    properties[6],
                    Integer.parseInt(properties[7]),
                    properties[8],
                    Double.parseDouble(properties[9])
            );
            doctor.setDeleted(Boolean.parseBoolean(properties[10]));
            doctors.add(doctor);
        }
        return doctors;
    }

    @Override
    public int findIndexById(String id) {
        List<DoctorEntity> doctors = getCurrentList();
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public DoctorEntity findById(String id) {
        List<DoctorEntity> doctors = getCurrentList();
        for (DoctorEntity doctor : doctors) {
            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    @Override
    public boolean save(DoctorEntity doctor) {
        List<DoctorEntity> doctors = findAll();
        doctors.add(doctor);
        return CSVUtil.writeCSVFile(fileName, doctors);
    }

    @Override
    public boolean update(DoctorEntity doctor) {
        List<DoctorEntity> doctors = findAll();
        int foundIndex = findIndexById(doctor.getId());
        if (foundIndex > -1) {
            doctors.set(foundIndex, doctor);
            return CSVUtil.writeCSVFile(fileName, doctors);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        List<DoctorEntity> doctors = new ArrayList<>(findAll());
        int foundIndex = findIndexById(id);
        if (foundIndex > -1) {
            DoctorEntity doctor = doctors.get(foundIndex);
            doctor.setDeleted(true);
            doctors.set(foundIndex, doctor);
            return CSVUtil.writeCSVFile(fileName, doctors);
        }
        return false;
    }
}
