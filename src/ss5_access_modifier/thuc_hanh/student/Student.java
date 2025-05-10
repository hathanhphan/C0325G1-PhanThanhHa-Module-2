package ss5_access_modifier.thuc_hanh.student;

public class Student {
    private int rollNo;
    private String name;
    private static String college = "BBDIT";
    public Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    static void changeCollege() {
        college = "CODEGYM";
    }
    void display() {
        System.out.println(rollNo + " " + name + " " + college);
    }
}
