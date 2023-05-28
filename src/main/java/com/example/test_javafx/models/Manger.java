package com.example.test_javafx.models;
public class Manger extends User {

    public Manger(String username, String password) {
        super(username, password);
    }

    public void createCourse(String courseName, String subject, String instructor, String venue) {
        // Code to create a new course
    }

    public void editCourse(String courseName, String subject, String instructor, String venue) {
        // Code to edit an existing course
    }

    public void createUserAccount(String username, String password) {
        // Code to create a new user account
    }

    public void editUserAccount(String username, String newPassword) {
        // Code to edit an existing user account
    }

    public void assignInstructorToCourse(String courseName, String instructor) {
        // Code to assign an instructor to a course
    }
}