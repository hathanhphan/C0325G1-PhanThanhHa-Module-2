package ss12_java_collection_framework.bai_tap.student_management.entity;

public class Person {
    private Long id;
    private String name;
    private String address;

    public Person() {
    }

    public Person(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'';
    }
}
