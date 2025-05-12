package ss7_abstract_class_and_interface.thuc_hanh.animal_and_interface_edible;

public class Main {
    public static void main(String[] args) {
        Tiger tiger = new Tiger();
        Chicken chicken = new Chicken();
        System.out.println(tiger.makeSound());
        System.out.println(chicken.makeSound());
        System.out.println(chicken.howToEat());
        Apple apple = new Apple();
        Orange orange = new Orange();
        System.out.println(apple.howToEat());
        System.out.println(orange.howToEat());
    }
}
