package ss11_dsa.bai_tap.demerging_using_queue;

import java.time.LocalDate;

public class Employee {
    private String name;
    private boolean gender;
    private LocalDate dob;

    public Employee() {
    }

    public Employee(String name, boolean gender, LocalDate dob) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", dob=" + dob +
                '}';
    }
}
