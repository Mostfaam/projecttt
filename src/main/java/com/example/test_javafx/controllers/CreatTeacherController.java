package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DataModel;
import com.example.test_javafx.models.Teatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreatTeacherController implements Initializable {
    @FXML
    public AnchorPane anchorPane;
    @FXML
    TextField name;
    @FXML
    TextField userName;
    @FXML
   TextField password;
    @FXML
    TextField phone;
    @FXML
    TextField course;
DataModel dataModel=new DataModel();
    Navigation navigation = new Navigation();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialize();}

    public void initialize() {
        name.setText(null);
        userName.setText(null);
        password.setText(null);
        phone.setText(null);
    }
    public void back(ActionEvent actionEvent) {
        navigation.navigateTo(anchorPane,navigation.MANGER_FXML);
    }

    public void creatT(ActionEvent actionEvent) {
   boolean isusernameisunique=true;
        ArrayList<Teatcher> teatchers=dataModel.getTeatchers();
        for(int i=0;i<teatchers.size();i++) {

        if(userName.getText().equals(teatchers.get(i).getUsername())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("the username must be unique");
            alert.setTitle("Done");
            alert.setContentText("change the username");
            alert.showAndWait();
            isusernameisunique=false;
            break;
        }
        }
        if(isusernameisunique){
        if(name.getText()==null| userName.getText()==null| password.getText()==null| phone.getText()==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("The teatcher cannat be null");
            alert.setTitle("Done");
            alert.setContentText("fill the feilds");
            alert.showAndWait();
        }
        else {
            Teatcher teatcher = new Teatcher(name.getText(), userName.getText(), password.getText(), phone.getText());
            dataModel.addTeatcher(teatcher);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("The teatcher has been added successfully");
            alert.setTitle("Done");
            alert.setContentText("UserName: " + userName.getText() + ", password: " + password.getText());
            alert.showAndWait();
      initialize();
        }
    }
    }
}
