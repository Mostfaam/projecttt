package com.example.test_javafx.controllers;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.Course;
import com.example.test_javafx.models.DataModel;
import com.example.test_javafx.models.Lecture;
import com.example.test_javafx.models.Student;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class attendsController implements Initializable {
    private String path;
    @FXML
    public TableView<Student> table;
    @FXML
    public TableView<Lecture> table1;
    @FXML
    private ListView<String> listAttend;
    @FXML
    private ListView<String> listCourseInAttend;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private AnchorPane anchorPaneForAttend;
    @FXML
    private VBox vboxStart;

    public TableColumn name;
    @FXML
    ComboBox<String> selectTheCourse;
    @FXML
    public TextField id;
    @FXML
    ListView<String> lsitAutoComplete;
    @FXML
    ListView<String> listForLecture;
    @FXML
    Button atend;
    @FXML
    Button importExcel;
    @FXML
    Button rebort;
    @FXML
    Button atendHere;
    @FXML
    Label attendLabel;
    @FXML
    Button creatTopic;
    @FXML
    Button creatLecture;
    @FXML
    TextField classRoom;
    @FXML
AnchorPane anchorPaneForLecture;

    String nameSelected;
    String lectureSelected;
    String studentSelectedInlist;
    Student studentSelectedIntable;
    public TableColumn phoneNumber;
    public TableColumn universityId;
    public TableColumn topic;
    public TableColumn date;
    public TableColumn attend;

    String namecourseSelected = "";

    Navigation navigation = new Navigation();
    DataModel model = new DataModel();
    ArrayList<Student> studentsForTable = new ArrayList<Student>();

    public void autoComplete(KeyEvent event) {
        if (!(namecourseSelected.equals(""))) {
table1.setVisible(false);
table.setVisible(true);

            lsitAutoComplete.setVisible(true);
            ArrayList<String> options = new ArrayList<String>();
            String text = id.getText();
            if (text.isEmpty()) {
                lsitAutoComplete.setVisible(false);
            } else {
                for (Student word : model.getCourseByname(namecourseSelected).getStudents()) {
                    if (word.getName().startsWith(text) || word.getUniversityId().startsWith(text)
                            || word.getPhoneNumber().startsWith(text)) {
                        options.add(word.getName() + "," + word.getUniversityId() + "," + word.getPhoneNumber());
                    }
                }


            }
            lsitAutoComplete.setItems(FXCollections.observableArrayList(options));

        }
    }

    public void getStudent() {
        studentSelectedInlist = lsitAutoComplete.getSelectionModel().getSelectedItem();
        if (studentSelectedInlist != null) {


            String[] data = studentSelectedInlist.split(",");
            for (Student s : model.getStudents()) {
                if (s.getUniversityId().equals(data[1])) {

                    studentsForTable.add(model.getStudentById(data[1]));
                    table.refresh();
                }
            }
        }

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        universityId.setCellValueFactory(new PropertyValueFactory<>("universityId"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        table.setItems(FXCollections.observableArrayList(studentsForTable));

    }

    public void inti() {
        listCourseInAttend.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                namecourseSelected = listCourseInAttend.getSelectionModel().getSelectedItem();
            }
        });
    }

    public void atendHere() {
        vboxStart.setVisible(false);
        anchorPaneForAttend.setVisible(true);

    }

    public void initialize(URL url, ResourceBundle rb) {

        //make sure the property value factory should be exactly same as the getStudentId from your model class
        ArrayList<String> courseforTeatcher = new ArrayList<String>();

        ArrayList<String> lectureForList = new ArrayList<String>();
        for (Course s : DataModel.getTeatcherByUsername(model.getWhoTeatcher()).getCourse()) {
            courseforTeatcher.add(s.getCourseName());
        }

        selectTheCourse.getItems().addAll(courseforTeatcher);

        selectTheCourse.setOnAction(event -> {
            namecourseSelected = selectTheCourse.getValue();
            for (Lecture s : model.getCourseByname(selectTheCourse.getValue()).getLectures()) {
                lectureForList.add(s.getTopic());
            }
            listForLecture.getItems().setAll(lectureForList);
        });
    }

    public void back(ActionEvent actionEvent) {
        navigation.navigateTo(rootPane, navigation.TEACHER_FXML);
    }

    public void atend(ActionEvent actionEvent) {
    }

    public void oAatend(ActionEvent event) {
        for (Student student : studentsForTable)
            model.getLectureBytopic(lectureSelected).addStudentAttendance(student);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Attendance has been successfully recorded");
        alert.setTitle("Done");
        alert.setContentText("done");
        alert.showAndWait();


    }

    public void onLecturt(ActionEvent event) {
        anchorPaneForAttend.setVisible(false);
        anchorPaneForLecture.setVisible(true);


    }

    public ArrayList<String> getAttend() {
        ArrayList<String> attend = new ArrayList<>();
        for (Lecture lecture : model.getCourseByname(namecourseSelected).getLectures()) {
            attend.add(lecture.getAttend(studentSelectedIntable));
        }
        return attend;

    }

    public void onRebort(ActionEvent event) {
        table.setVisible(false);
        table1.setVisible(true);


        topic.setCellValueFactory(new PropertyValueFactory<>("topic"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        attend.setCellValueFactory(new PropertyValueFactory<>("isStudentAttend"));

        ArrayList<Lecture> lectures = new ArrayList<Lecture>();
        for (Lecture lecture : model.getCourseByname(namecourseSelected).getLectures()) {
            lecture.setIsStudentAttend(lecture.getAttend(studentSelectedIntable));
            lectures.add(lecture);
        }
        table1.setItems(FXCollections.observableArrayList(lectures));


    }

    public void setLecture(MouseEvent mouseEvent) {
        lectureSelected = listForLecture.getSelectionModel().getSelectedItem();
    }

    public void getStudentName(MouseEvent mouseEvent) {
        studentSelectedIntable = table.getSelectionModel().getSelectedItem();

    }

    public void creatTopic(ActionEvent event) {
        Lecture lecture = new Lecture(topic.getText(),classRoom.getText(),model.getWhoTeatcher(),namecourseSelected);

    }

    public void importExcel(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        path = file.toURI().toString();
        try {
            Workbook workbook = Workbook.getWorkbook(new File(path));
            Sheet sheet = workbook.getSheet(0); // تحديد ورقة المصنف التي ترغب في قراءتها
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
    }
}