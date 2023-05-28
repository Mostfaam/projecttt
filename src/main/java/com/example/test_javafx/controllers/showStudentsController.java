package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DataModel;
import com.example.test_javafx.models.Student;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class showStudentsController implements Initializable {

    @FXML
    public TableView<Student> table;
    @FXML
    public SplitPane root;
    public TableColumn Name;
    public TableColumn gpa;
    DataModel model = new DataModel();
    Navigation navigation = new Navigation();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //make sure the property value factory should be exactly same as the getStudentId from your model class
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        gpa.setCellValueFactory(new PropertyValueFactory<>("gpa"));

        table.setItems(FXCollections.observableArrayList(model.getStudents()));
    }

    public void onBack(ActionEvent actionEvent) {
        navigation.navigateTo(root, navigation.STUDENT_FXML);
    }
}
