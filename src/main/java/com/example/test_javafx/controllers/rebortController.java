package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.Course;
import com.example.test_javafx.models.DataModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class rebortController  implements Initializable {
    @FXML
    public AnchorPane rootPane;
    @FXML
    public AnchorPane lectuer;
    @FXML
    public AnchorPane student;
    @FXML
    public AnchorPane Student25Absence;
    @FXML
    public TableView courseLecuter;
    @FXML
    ComboBox comboBoxForCourse;
    @FXML
    ComboBox comboBoxForReportType;

    @FXML
    public Label coution;
    DataModel dataModel = new DataModel();
    Navigation navigation = new Navigation();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> courseforTeatcher = new ArrayList<String>();
        for (Course s : DataModel.getTeatcherByUsername(dataModel.getWhoTeatcher()).getCourse()) {
            courseforTeatcher.add(s.getCourseName());

        }
        comboBoxForCourse.getItems().addAll(courseforTeatcher);

        comboBoxForCourse.setOnAction(event -> {
            String selectedString = (String) comboBoxForCourse.getValue();

        });
        comboBoxForReportType.getItems().add("Lecture");
        comboBoxForReportType.getItems().add("Course lecture");
        comboBoxForReportType.getItems().add("Student");
        comboBoxForReportType.getItems().add("show over 25% Student absence");



    }
    public void onBack(ActionEvent actionEvent) {
        navigation.navigateTo(rootPane,navigation.TEACHER_FXML);

    }

    public void onExport(ActionEvent actionEvent) {
    }

    public void onAbsencesOnly(ActionEvent actionEvent) {
    }

    public void onAttendingOnly(ActionEvent actionEvent) {
    }

    public void onAllStudent(ActionEvent actionEvent) {
    }

    public void onshowStudent(ActionEvent actionEvent) {
    }


    public void getReport(ActionEvent actionEvent) {
        if((comboBoxForCourse.getSelectionModel().getSelectedIndex() != -1)){
            if((comboBoxForReportType.getValue().equals("Lecture"))) {
                coution.setVisible(false);
                student.setVisible(false);
                courseLecuter.setVisible(false);
                Student25Absence.setVisible(false);
                lectuer.setVisible(true);
            }else if ((comboBoxForReportType.getValue().equals("Student"))) {
                coution.setVisible(false);
                lectuer.setVisible(false);
                Student25Absence.setVisible(false);
                courseLecuter.setVisible(false);
                student.setVisible(true);
            }else if ((comboBoxForReportType.getValue().equals("show over 25% Student absence"))) {
                coution.setVisible(false);
                lectuer.setVisible(false);
                student.setVisible(false);
                courseLecuter.setVisible(false);
                Student25Absence.setVisible(true);

            }else{
                coution.setVisible(false);
                lectuer.setVisible(false);
                student.setVisible(false);
                Student25Absence.setVisible(false);
                courseLecuter.setVisible(true);
            }
        }else {}
    }
}