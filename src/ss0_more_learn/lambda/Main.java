package ss0_more_learn.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Data {
    String name;

    public Data(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        List<Data> list = new ArrayList<>();
        list.add(new Data("Ha2"));
        list.add(new Data("Ha3"));
        list.add(new Data("Ha1"));
        list.add(new Data("Ha4"));
        list.add(new Data("Ha5"));
        list.sort(Comparator.comparing(Data::getName));
        list.forEach(System.out::println);
    }
}
