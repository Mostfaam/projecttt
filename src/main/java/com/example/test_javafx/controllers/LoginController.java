package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DataModel;
import com.example.test_javafx.models.Manger;
import com.example.test_javafx.models.Teatcher;
import com.example.test_javafx.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController  {
    @FXML
    public AnchorPane rootPane;
    public TextField userName;
    public TextField pass;
    DataModel dataModel = new DataModel();
    Navigation navigation = new Navigation();
    final Manger ADMIN = new Manger("admin","123456789");
    Alert alert = new Alert(Alert.AlertType.WARNING);


    public void loginTeatcher(ActionEvent actionEvent) throws IOException {
        if (dataModel.getTeatchers() == null) {
            if (dataModel.isManeger(userName.getText(),pass.getText(), ADMIN)) {
                navigation.navigateTo(rootPane, navigation.MANGER_FXML);
            }} else  if (!dataModel.varUserName(userName.getText())&&!dataModel.isManeger(userName.getText(),pass.getText(), ADMIN)){
                {
                    alert.setHeaderText("you are not admin or teatcher");
                    alert.setTitle("eroor");
                    alert.setContentText("please contact admin to add you to teatchers");
                    alert.showAndWait();
                    System.out.println();
                    System.out.println(userName.getText());


                    // Show the Dialog

                }
            } else {
                if (dataModel.isManeger(userName.getText(),pass.getText(), ADMIN)) {
                    navigation.navigateTo(rootPane, navigation.MANGER_FXML);
                } else {
                  if(dataModel.varUserName(userName.getText())&&!dataModel.varPassword(pass.getText())) {
                      alert.setHeaderText("the password thet enterd is incoreect");
                      alert.setTitle("eroor");
                      alert.setContentText("please try again");
                      alert.showAndWait();
                  }
            }
                if(dataModel.varUserName(userName.getText())&&dataModel.varPassword(pass.getText())){
                    dataModel.wohTeatcher(userName.getText());
                    navigation.navigateTo(rootPane, navigation.TEACHER_FXML);
                    dataModel.setion(userName.getText());

                }
            }
        }


}