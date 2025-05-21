package ss12_java_collection_framework.thuc_hanh.sort_with_comparable_and_comparator;

import ss12_java_collection_framework.thuc_hanh.difference_between_hashmap_and_hashset.Student;

import java.util.Comparator;

public class AgeComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.getAge() > o2.getAge()) {
            return 1;
        } else if (o1.getAge() == o2.getAge()) {
            return 0;
        } else {
            return -1;
        }
    }
}
