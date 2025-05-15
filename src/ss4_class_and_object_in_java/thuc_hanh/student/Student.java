package ss4_class_and_object_in_java.thuc_hanh.student;

import java.time.LocalDate;

public class Student {
    long code;
    String name;
    String phoneNumber;
    LocalDate dob;
    String address;
    Boolean gender;

    public Student() {
    }

    public Student(long code, String name, String phoneNumber, LocalDate dob, String address, Boolean gender) {
        this.code = code;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        String result;
        if (this.gender == null) {
            result = "Other";
        } else if (this.gender) {
            result = "Male";
        } else {
            result = "Female";
        }
        return String.format("| %-10d | %-20s | %-15s | %-12s | %-20s | %-10s |",
                code,
                name,
                phoneNumber,
                dob.toString(),
                address,
                result
        );
    }
}
