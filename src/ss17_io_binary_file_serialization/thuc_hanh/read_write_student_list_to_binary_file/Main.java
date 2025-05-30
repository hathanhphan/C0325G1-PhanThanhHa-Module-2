package ss17_io_binary_file_serialization.thuc_hanh.read_write_student_list_to_binary_file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String path = "D:\\CodeGym\\module2\\src\\ss17_io_binary_file_serialization\\thuc_hanh\\read_write_student_list_to_binary_file\\student.txt";
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Vũ Kiều Anh", "Hà Nội"));
        students.add(new Student(2, "Nguyễn Minh Quân", "Hà Nội"));
        students.add(new Student(3, "Đặng Huy Hoà", "Đà Nẵng"));
        students.add(new Student(4, "Nguyễn Khánh Tùng", "Hà Nội"));
        students.add(new Student(5, "Nguyễn Khắc Nhật", "Hà Nội"));
        writeDataToFile(path, students);
        List<Student> studentDataFromFile = readDataFromFile(path);
        for (Student student : studentDataFromFile){
            System.out.println(student);
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void writeDataToFile(String path, List<Student> students) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(students);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"CallToPrintStackTrace", "unchecked"})
    public static List<Student> readDataFromFile(String path){
        List<Student> students = new ArrayList<>();
        try{
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            students = (List<Student>) ois.readObject();
            fis.close();
            ois.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return students;
    }
}
