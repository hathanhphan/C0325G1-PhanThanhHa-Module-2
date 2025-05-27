package ss0_more_learn.s11_java_generics;

import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Data> elements = new LinkedList<>();
        elements.add(new Data("Chaand"));
        elements.add(new Data("E"));
        elements.add(new Data(25));
        elements.add(new Data(52.65));
        System.out.println(elements);

        GenericData<String> genericData = new GenericData<>("ABC");
        String data = genericData.getData();
        System.out.println(data);
    }
}

class Data {
    private Object object;

    public Data(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return object.toString();
    }
}

class GenericData<T> {
    private T data;

    public GenericData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
