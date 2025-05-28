package mvc_template.entity;

public class Student extends Person {
    private float point;
    private String className;

    public Student() {
    }

    public Student(long code, String name, String address, float point, String className) {
        super(code, name, address);
        this.point = point;
        this.className = className;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(float point) {
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
