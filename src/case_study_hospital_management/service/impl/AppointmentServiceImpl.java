package case_study_hospital_management.service.impl;

import case_study_hospital_management.common.enums.AppointmentStatus;
import case_study_hospital_management.entity.AppointmentEntity;
import case_study_hospital_management.entity.DoctorEntity;
import case_study_hospital_management.repository.AppointmentRepository;
import case_study_hospital_management.repository.impl.AppointmentRepositoryImpl;
import case_study_hospital_management.service.AppointmentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AppointmentServiceImpl implements AppointmentService {
    private static AppointmentServiceImpl instance;
    private final AppointmentRepository appointmentRepository;

    private AppointmentServiceImpl() {
        appointmentRepository = AppointmentRepositoryImpl.getInstance();
    }

    public static synchronized AppointmentServiceImpl getInstance() {
        if (instance == null) {
            instance = new AppointmentServiceImpl();
        }
        return instance;
    }

    @Override
    public List<AppointmentEntity> getAll() {
        return appointmentRepository.findAll().stream().filter(a -> !a.getDeleted()).toList();
    }

    @Override
    public AppointmentEntity findById(String id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public boolean add(AppointmentEntity appointment) {
        appointment.setId(generateId());
        return appointmentRepository.save(appointment);
    }

    @Override
    public boolean update(AppointmentEntity appointmentEntity) {
        return appointmentRepository.update(appointmentEntity);
    }

    @Override
    public boolean delete(String id) {
        return appointmentRepository.delete(id);
    }

    @Override
    public String generateId() {
        List<AppointmentEntity> appointments = appointmentRepository.findAll();
        int maxId = appointments.stream().mapToInt(a -> Integer.parseInt(a.getId().substring(1))).max().orElse(0);
        return String.format("A%06d", maxId + 1);
    }

    @Override
    public List<AppointmentEntity> findByDoctorAndDate(String doctorId, LocalDate appointmentDate) {
        return appointmentRepository.findByDoctorAndDate(doctorId, appointmentDate);
    }

    @Override
    public Map<String, Boolean> findEmptyAppointmentsByDoctorAndDate(List<AppointmentEntity> appointments, DoctorEntity doctor) {
        return appointmentRepository.findEmptyAppointmentsByDoctorAndDate(appointments, doctor);
    }

    @Override
    public List<AppointmentEntity> findByKeywordOfPatient(String keyword) {
        return appointmentRepository.findByKeywordOfPatient(keyword);
    }

    @Override
    public List<AppointmentEntity> findByKeywordOfDoctor(String keyword) {
        return appointmentRepository.findByKeywordOfDoctor(keyword);
    }

    @Override
    public List<AppointmentEntity> findByStatus(AppointmentStatus status) {
        return appointmentRepository.findByStatus(status);
    }

    @Override
    public List<AppointmentEntity> findByDate(LocalDate appointmentDate) {
        return appointmentRepository.findByDate(appointmentDate);
    }

    @Override
    public boolean reschedule(AppointmentEntity appointment, AppointmentEntity rescheduleAppointment) {
        String rescheduleId = generateId();
        rescheduleAppointment.setId(rescheduleId);
        appointment.setNewAppointmentId(rescheduleId);
        return appointmentRepository.reschedule(appointment, rescheduleAppointment);
    }

    @Override
    public Map<String, Double> statisticTodayAppointment() {
        return appointmentRepository.statisticTodayAppointment();
    }

    @Override
    public Map<String, Double> statisticAppointmentByDate(LocalDate date) {
        return appointmentRepository.statisticAppointmentByDate(date);
    }

    @Override
    public Map<String, Double> statisticThisMonthAppointment() {
        return appointmentRepository.statisticThisMonthAppointment();
    }

    @Override
    public List<AppointmentEntity> findByMonth(int month, int year) {
        return appointmentRepository.findByMonth(month, year);
    }

    @Override
    public Map<String, Double> statisticAppointmentByMonth(int month, int year) {
        return appointmentRepository.statisticAppointmentByMonth(month, year);
    }
}
