package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.Course;
import com.example.test_javafx.models.DataModel;
import com.example.test_javafx.models.Teatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreatCoursController implements Initializable  {

    public AnchorPane anchorPane;
    Course couerse;
    DataModel dataModel =new DataModel();

    @FXML
    TextField coursName;
    @FXML
    TextField textBook;
    @FXML
    TextField teacher;
    @FXML
    TextField teacherAssmint;
    TextArea discrption = new TextArea();
    @FXML
    TextField classRoom;
    Navigation navigation = new Navigation();


    public void back(ActionEvent actionEvent) {
        navigation.navigateTo(anchorPane,navigation.MANGER_FXML);
    }

    public void creatC(ActionEvent actionEvent) {
        boolean isCourNameisunique = true;
        ArrayList<Course> courses = dataModel.getCourses();
        for (int i = 0; i < courses.size(); i++) {

            if (coursName.getText().equals(courses.get(i).getCourseName())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("the classNmae must be unique");
                alert.setTitle("Done");
                alert.setContentText("change the className");
                alert.showAndWait();
                isCourNameisunique = false;
                break;
            }
        }
        if (isCourNameisunique) {
            if (coursName.getText() == null ||textBook.getText() == null || teacher.getText() == null || teacherAssmint.getText() == null || classRoom.getText() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("The course cannat be null");
                alert.setTitle("Done");
                alert.setContentText("fill the feilds");
                alert.showAndWait();
            } else {
                Course course = new Course(coursName.getText(),discrption.getText() ,textBook.getText(), teacher.getText(), teacherAssmint.getText(), classRoom.getText());
                if(dataModel.getIndexByUsername(teacherAssmint.getText())==-1){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("The teatcheAssimenmt must be exist");
                    alert.setTitle("Done");
                    alert.setContentText("confirm from teatcherAssiment");
                    alert.showAndWait();
                }else {
                    dataModel.addCourses(course);

                    dataModel.getTeatchers().get(dataModel.getIndexByUsername(teacherAssmint.getText())).addCourse(DataModel.getCourseByname(coursName.getText()));

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("The teatcher has been added successfully");
                    alert.setTitle("Done");
                    alert.setContentText("course: " + coursName.getText() + ", teatcher: " + teacher.getText());
                    alert.showAndWait();
                    initialize();
                }
            }
        }
    }

    public void initialize() {
        coursName.setText(null);
        textBook.setText(null);
        teacher.setText(null);
        teacherAssmint.setText(null);
        classRoom.setText(null);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize();
    }
}
