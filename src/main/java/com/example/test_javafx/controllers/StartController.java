package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DataModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class StartController {

    @FXML
    public AnchorPane rootPane;

    Navigation navigation = new Navigation();

    public void onStudent() {
        navigation.navigateTo(rootPane, navigation.STUDENT_FXML);
    }



    public void onReports(ActionEvent actionEvent) {
        navigation.navigateTo(rootPane,navigation.REPORT);
    }

    public void onLectuer(ActionEvent actionEvent) {
        navigation.navigateTo(rootPane, navigation.CREAT_LECTUER);
    }

    public void onPresence(ActionEvent actionEvent) {
        navigation.navigateTo(rootPane, navigation.ATTEND);

    }
    public void logout(ActionEvent actionEvent) {
        navigation.navigateTo(rootPane,navigation.LOGOUT);
        DataModel.setion(null);

    }
}