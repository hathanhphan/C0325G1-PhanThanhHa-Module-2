package ss12_java_collection_framework.bai_tap.student_management.entity;

public class Student extends Person {
    private Float point;
    private String className;

    public Student() {
    }

    public Student(Long id, String name, String address, Float point, String className) {
        super(id, name, address);
        this.point = point;
        this.className = className;
    }

    public Float getPoint() {
        return point;
    }

    public void setPoint(Float point) {
        this.point = point;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Product{" +
                 super.toString()+
                ", point=" + point +
                ", className='" + className + '\'' +
                '}';
    }
}
