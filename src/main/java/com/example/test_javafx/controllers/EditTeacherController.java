    package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.Course;
import com.example.test_javafx.models.DataModel;
import com.example.test_javafx.models.Teatcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

    public class EditTeacherController {
DataModel model=new DataModel();
    @FXML
    public AnchorPane anchorPane;
    public VBox vBox;
    public VBox vBox2;
    public TextField teEditName;
    public TextField teEditCourse;
    public TextField teEditUserName;
    public TextField teEditPass;
    public TextField teEditPhone;
private String passwordTemp;
private String nameTemp;

    Navigation navigation = new Navigation();
    public void teName(ActionEvent actionEvent) {



    }

    public void onOK(){
        ArrayList<Teatcher> teatchers  =model.getTeatchers();
        boolean tOrF = false ;
      if (model.getIndexByUsername(teEditUserName.getText())==-1){
          tOrF = false ;
      }

        else{

            tOrF = true;
        }


    if (tOrF){
        vBox2.setVisible(false);
        vBox.setVisible(true);
        teEditName.setText(teatchers.get(model.getIndexByUsername(teEditUserName.getText())).getName());
        teEditPass.setText(teatchers.get(model.getIndexByUsername(teEditUserName.getText())).getPassword());
        teEditPhone.setText(teatchers.get(model.getIndexByUsername(teEditUserName.getText())).getPhone());
        nameTemp=teEditName.getText();
        passwordTemp=teEditPass.getText();
    }
    else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("The username is not exist");
        alert.setTitle("Done");
        alert.setContentText("refill true username");
        alert.showAndWait();
    }
    }
    public void editTecher(ActionEvent actionEvent) {

     ArrayList<Course> coursesForTeatcher =   model.getTeatcherByUsername(teEditUserName.getText()).getCourse();
        model.deleteTeatcherByUsername(teEditUserName.getText());
        Teatcher teatcher = new Teatcher(teEditName.getText(),teEditUserName.getText(),teEditPass.getText(),teEditPhone.getText());
       for(Course course :coursesForTeatcher){
        teatcher.csvToArrays(course.getCourseName()+";");
       }
        model.addTeatcher(teatcher);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("The teatcher edited");
        alert.setTitle("Done");
        alert.setContentText("the teatcher's name edit from " + nameTemp+ " to "+ teEditUserName.getText()+
        " " + " and the teatcher's passsword edit from " + passwordTemp + " to "+ teEditPass.getText());
        alert.showAndWait();
        vBox.setVisible(false);
        vBox2.setVisible(true);
        teEditUserName.setText(null);
    }
    public void back(ActionEvent actionEvent) {
        navigation.navigateTo(anchorPane,navigation.MANGER_FXML);
    }
}
