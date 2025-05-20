package mvc_template.view;

import mvc_template.controller.StudentController;

public class View {
    public static void main(String[] args) {
        StudentController studentController = new StudentController();
        studentController.displayMenu();
    }
}
