package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class EditCorseController {

    @FXML
    public AnchorPane anchorPane;
    @FXML
    private AnchorPane paneEdit;
    @FXML
    private Label labelVis;
    @FXML
    private VBox vBoxCourseName;
    @FXML
     private TextField courseNameEdit;
     @FXML
     private TextField textBookEdit;
     @FXML
     private TextField teacherAssmintEdit;
     @FXML
     private TextField teacherEdit;
     @FXML
     private TextField classRoomEdit;
     @FXML
     private TextField courseName;
    DataModel model = new DataModel();
    String exClassRoom ="";
    String dis ="";
String anyCourse="";
    Navigation navigation = new Navigation();

    public void onOK(){
        ArrayList<Teatcher> teatchers  =model.getTeatchers();
        boolean tOrF = false ;
        if (model.getCourseByname(courseName.getText())==null){
            tOrF = false ;
        }

        else {
            tOrF = true;
            anyCourse=courseName.getText();
            dis=model.getCourseByname(courseName.getText()).getDiscribtion();
            exClassRoom=model.getCourseByname(courseName.getText()).getClassRoom();
        }



        if (tOrF){
            vBoxCourseName.setVisible(false);
            labelVis.setVisible(false);
            paneEdit.setVisible(true);
            courseNameEdit.setText(model.getCourseByname(courseName.getText()).getCourseName());
            textBookEdit.setText(model.getCourseByname(courseName.getText()).getTextbook());
            teacherEdit.setText(model.getCourseByname(courseName.getText()).getInstructor());
            teacherAssmintEdit.setText(model.getCourseByname(courseName.getText()).getTeatcherAssiment());
            classRoomEdit.setText(model.getCourseByname(courseName.getText()).getClassRoom());

        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("The username is not exist");
            alert.setTitle("Done");
            alert.setContentText("refill true username");
            alert.showAndWait();
        }
    }
    public void teName(ActionEvent actionEvent) {



    }

    public void editCourse(ActionEvent actionEvent) {


        ArrayList<Student> studentsForCourse =   model.getCourseByname(anyCourse).getStudents();
        ArrayList<Lecture> lecturesForCourse =   model.getCourseByname(anyCourse).getLectures();
        model.deleteCourseByName(anyCourse);

       Course course = new Course(courseNameEdit.getText(),dis,
               textBookEdit.getText(),teacherEdit.getText(),teacherAssmintEdit.getText(),classRoomEdit.getText());
        for(Student student :studentsForCourse){
           course.csvToArrayStudent(student.getUniversityId()+";");
        }
        for(Lecture lecture :lecturesForCourse){
            course.csvToArraysLecture(lecture.getTopic()+";");
        }
        model.addCourses(course);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("The teatcher edited");
        alert.setTitle("Done");
        alert.setContentText("the classRoom edit from " +exClassRoom+" to "+ classRoomEdit.getText()+
                " " + " and the teatcher's passsword edit from " + anyCourse + " to "+ courseNameEdit.getText());
        alert.showAndWait();
        paneEdit.setVisible(false);
        vBoxCourseName.setVisible(true);
        labelVis.setVisible(true);
        courseName.setText(null);
    }
    public void back(ActionEvent actionEvent) {
        navigation.navigateTo(anchorPane,navigation.MANGER_FXML);
    }
}
