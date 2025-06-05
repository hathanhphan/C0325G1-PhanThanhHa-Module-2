package case_study_hospital_management.entity;

import java.util.ArrayList;
import java.util.List;

public class RescheduleHistoryEntity {
    private AppointmentEntity originalAppointment;
    private List<AppointmentEntity> rescheduleSteps;

    public RescheduleHistoryEntity() {
        this.rescheduleSteps = new ArrayList<>();
    }

    public void setOriginalAppointment(AppointmentEntity appointment) {
        this.originalAppointment = appointment;
    }

    public void addRescheduleStep(AppointmentEntity appointment) {
        this.rescheduleSteps.add(appointment);
    }

    public AppointmentEntity getCurrentAppointment() {
        if (rescheduleSteps.isEmpty()) {
            return originalAppointment;
        }
        return rescheduleSteps.get(rescheduleSteps.size() - 1);
    }

    public int getRescheduleCount() {
        return rescheduleSteps.size();
    }

    public List<AppointmentEntity> getAllAppointments() {
        List<AppointmentEntity> all = new ArrayList<>();
        if (originalAppointment != null) {
            all.add(originalAppointment);
        }
        all.addAll(rescheduleSteps);
        return all;
    }
}
