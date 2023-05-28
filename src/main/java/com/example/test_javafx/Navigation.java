package com.example.test_javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    public static final String ATTEND ="views/attend.fxml" ;
    public static final String CREAT_LECTUER ="views/creatLecture.fxml" ;
    public final String MANGER_FXML = "views/Admin.fxml";
    public final String TEACHER_FXML = "views/teacher.fxml";
    public final String STUDENT_FXML = "views/Student.fxml";
    public final String SHOW_STUDENTS_FXML = "views/showStudents.fxml";
    public final String LOGOUT = "views/login.fxml";
    public final String CREAT_TEACHER = "views/CreatTeacher.fxml";
    public final String EDIT_TEACHER = "views/editTeacher.fxml";
    public final String CREAT_COURS = "views/creatCours.fxml";
    public final String EDIT_COURS = "views/editCours.fxml";
    public final String REPORT = "views/Rebort.fxml";



    public void navigateTo(Parent rootPane, String path) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
            rootPane.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}



