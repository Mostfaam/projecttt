package com.example.test_javafx.controllers;
import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.Course;
import com.example.test_javafx.models.DataModel;
import com.example.test_javafx.models.Lecture;
import com.example.test_javafx.models.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.Array;
import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;
public class CreatLectuerController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    TextField topic;
    @FXML
    TextField classRoom;
    @FXML
    TextField userName ;
    @FXML
    TextField coursName;
    @FXML
    private ListView<String> listOfLectuer;
    @FXML
    private ComboBox<String> leCource ;
    @FXML
    public Button creatL;
    @FXML
    public Button saveEdit;

    private int in ;
    Navigation navigation = new Navigation();
    DataModel dataModel =new DataModel();
    Lecture lecture ;
    String courseNameSelected;
    public void back(ActionEvent actionEvent) {
        navigation.navigateTo(anchorPane,navigation.TEACHER_FXML);
    }
    /*package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.Course;
import com.example.test_javafx.models.DataModel;
import com.example.test_javafx.models.Lecture;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.lang.reflect.Array;
import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreatLectuerController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    TextField topic;
    @FXML
    TextField classRoom;
    @FXML
    TextField userName ;
    @FXML
    TextField coursName;
    @FXML
    private ListView<String> list;

    @FXML
    Navigation navigation = new Navigation();
    DataModel dataModel =new DataModel();
    String courseNameSelected;
    public void back(ActionEvent actionEvent) {
        navigation.navigateTo(anchorPane,navigation.TEACHER_FXML);

    }

    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> courseforTeatcher = new ArrayList<String>();
        for(Course s : DataModel.getTeatcherByUsername(dataModel.getWhoTeatcher()).getCourse()){
            courseforTeatcher.add(s.getCourseName());

        }
    list.getItems().addAll(courseforTeatcher);
    list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
            courseNameSelected=list.getSelectionModel().getSelectedItem();
        }
    });
    }

                public void creatLectuer(ActionEvent actionEvent) {
        //(String topic, String classRoom, LocalDateTime dateTime,String userName,String coursName)
        Lecture lecture = new Lecture(topic.getText(),classRoom.getText(),userName.getText(),courseNameSelected);
           dataModel.getLecture().add(lecture);
           dataModel.getCourseByname(courseNameSelected).addLecture(lecture);
           dataModel.saveLectures();
    }
}*/
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> courseforTeatcher = new ArrayList<String>();
        for(Course s : DataModel.getTeatcherByUsername(dataModel.getWhoTeatcher()).getCourse()){
            courseforTeatcher.add(s.getCourseName());
        }


        leCource.getItems().addAll(courseforTeatcher);
        leCource.setOnAction(event -> {
            listOfLectuer.setItems(FXCollections.observableArrayList(dataModel.getCourseByname(getCourseNameSelected()).getTopic()));
        });
    }
    public void creatLectuer(ActionEvent actionEvent) {
        //(String topic, String classRoom, LocalDateTime dateTime,String userName,String coursName)
        Lecture lecture = new Lecture(topic.getText(),classRoom.getText(),dataModel.getWhoTeatcher(),getCourseNameSelected());
        dataModel.getLecture().add(lecture);
        dataModel.getCourseByname(leCource.getValue()).addLecture(lecture);
        listOfLectuer.getItems().add(topic.getText());
        dataModel.saveLectures();

    }
    public void delitLectuer(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (listOfLectuer.getSelectionModel().isEmpty()) {
            alert.setHeaderText("you are not select a lecture");
            alert.setTitle("eroor");
            alert.setContentText("select a lecture");
            alert.showAndWait();
        } else {
            ArrayList<Integer> selectedIndices = new ArrayList<>(listOfLectuer.getSelectionModel().getSelectedIndices());
            for (int index : selectedIndices) {
                String lecture = listOfLectuer.getItems().get(index);
                listOfLectuer.getItems().remove(lecture);
                dataModel.getCourseByname(getCourseNameSelected()).removeLecture(dataModel.getLectureBytopic(lecture));
            }}


    }public void editLectuer(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (listOfLectuer.getSelectionModel().getSelectedIndex()==-1) {
            alert.setHeaderText("you are not select a lecture");
            alert.setTitle("eroor");
            alert.setContentText("select a lecture");
            alert.showAndWait();
        } else{
            ArrayList<Integer> selectedIndices = new ArrayList<>(listOfLectuer.getSelectionModel().getSelectedIndices());
            for (int in : selectedIndices) {
                String lecture = listOfLectuer.getItems().get(in);
                topic.setText(lecture);
                classRoom.setText(dataModel.getLectureBytopic(lecture).getClassRoom());
                creatL.setVisible(false);
                saveEdit.setVisible(true);
            }
        }
    }
    public void saveEdit(ActionEvent actionEvent) {
        String tempTopic = topic.getText();
        String tempClassRoom = classRoom.getText();
        String lecture = listOfLectuer.getItems().get(in);
        listOfLectuer.getItems().removeAll(dataModel.getCourseByname(getCourseNameSelected()).getTopic());
        dataModel.getCourseByname(getCourseNameSelected()).removeLecture(dataModel.getLectureBytopic(lecture));
        dataModel.getLecture().remove(dataModel.getLectureBytopic(lecture));
        dataModel.getCourseByname(getCourseNameSelected()).addLecture(new Lecture(tempTopic,tempClassRoom,dataModel.getWhoTeatcher(),getCourseNameSelected()));
        listOfLectuer.getItems().addAll(dataModel.getCourseByname(getCourseNameSelected()).getTopic());

        topic.setText("");
        classRoom.setText("");
        saveEdit.setVisible(false);
        creatL.setVisible(true);
    }
    private String getCourseNameSelected() {
        return  leCource.getValue();
    }
}