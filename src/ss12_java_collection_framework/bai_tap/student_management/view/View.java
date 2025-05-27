package ss12_java_collection_framework.bai_tap.student_management.view;

import ss12_java_collection_framework.bai_tap.student_management.controller.StudentController;

public class View {
    public static void main(String[] args) {
        StudentController studentController = new StudentController();
        studentController.displayMenu();
    }
}
