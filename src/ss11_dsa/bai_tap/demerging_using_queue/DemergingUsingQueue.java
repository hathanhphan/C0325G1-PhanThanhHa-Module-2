package ss11_dsa.bai_tap.demerging_using_queue;

import util.ReadAndWriteFile;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DemergingUsingQueue {
    public static void main(String[] args) {
        String inputPath = "D:\\CodeGym\\module2\\src\\ss11_dsa\\bai_tap\\demerging_using_queue\\data.csv";
        String outputPath = "D:\\CodeGym\\module2\\src\\ss11_dsa\\bai_tap\\demerging_using_queue\\output.csv";
        List<Employee> employeeList = getEmployeeList(inputPath);
        System.out.println("Danh sách ban đầu:");
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
        writeEmployeeList(employeeList, outputPath, false);
        System.out.println();
        System.out.println("Danh sách sau sắp xếp:");
        employeeList = getEmployeeList(outputPath);
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    private static List<Employee> getEmployeeList(String filePath) {
        List<Employee> employeeList = new ArrayList<>();
        List<String> stringList = ReadAndWriteFile.readFileCSV(filePath, true);
        for (String line : stringList) {
            String[] parts = line.split(",");
            String name = parts[0];
            boolean gender = Boolean.parseBoolean(parts[1]);
            LocalDate birthDate = LocalDate.parse(parts[2]);
            employeeList.add(new Employee(name, gender, birthDate));
        }
        return employeeList;
    }

    public static void writeEmployeeList(List<Employee> employees, String filePath, boolean append) {
        List<String> lines = new ArrayList<>();
        lines.add("Name,Gender,BirthDate");
        Queue<Employee> femaleQueue = new ArrayDeque<>();
        Queue<Employee> maleQueue = new ArrayDeque<>();
        for (Employee employee : employees) {
            if (employee.getGender()) {
                maleQueue.add(employee);
            } else {
                femaleQueue.add(employee);
            }
        }
        while (!femaleQueue.isEmpty()) {
            lines.add(employeeToCsvLine(femaleQueue.poll()));
        }
        while (!maleQueue.isEmpty()) {
            lines.add(employeeToCsvLine(maleQueue.poll()));
        }
        ReadAndWriteFile.writeFileCSV(filePath, lines, append);
    }

    private static String employeeToCsvLine(Employee employee) {
        StringBuilder line = new StringBuilder();
        if (employee.getName().contains(",")) {
            line.append("\"").append(employee.getName()).append("\"");
        } else {
            line.append(employee.getName());
        }
        line.append(",");
        line.append(employee.getGender());
        line.append(",");
        line.append(employee.getDob().toString());
        return line.toString();
    }
}
