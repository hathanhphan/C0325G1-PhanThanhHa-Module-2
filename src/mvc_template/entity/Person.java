package mvc_template.entity;

public class Person {
    private long code;
    private String name;
    private String address;

    public Person() {
    }

    public Person(long code, String name, String address) {
        this.code = code;
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "code=" + code +
                ", name='" + name + '\'' +
                ", address='" + address + '\'';
    }
}
