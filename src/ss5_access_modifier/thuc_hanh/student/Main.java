package ss5_access_modifier.thuc_hanh.student;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student(111, "Hoang");
        Student s2 = new Student(222, "Khanh");
        Student s3 = new Student(333, "Nam");
        s1.display();
        s2.display();
        s3.display();
        Student.changeCollege();
        s1.display();
        s2.display();
        s3.display();
    }
}
