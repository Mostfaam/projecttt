package com.example.test_javafx.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lecture {
    private String topic;

    public String getIsStudentAttend() {
        return isStudentAttend;
    }

    public void setIsStudentAttend(String isStudentAttend) {
        this.isStudentAttend = isStudentAttend;
    }

    private String isStudentAttend;

    private String classRoom;
    private Date dateTime = new Date();
    private ArrayList<Student> studentsAttendance = new ArrayList<Student>();
    private String userName;
    private String coursName;
    private  String date ;
    private  String attend ;

    public Lecture(String topic, String classRoom,String userName,String coursName) {
        this.topic = topic;
        this.classRoom = classRoom;
        this.studentsAttendance = new ArrayList<>();
        this.coursName = coursName;
        this.userName=userName;
        this.date = dateTime.toString();

    }

public String getAttend(Student student){
        for(Student student1:studentsAttendance){
        if (student==(student1)){
        return "yes";
        }

        }
        return "no";



}



    @Override
    public String toString() {
//        topic,classRoom,userName,couseName
        String toString = this.topic + "," + this.classRoom + "," + this.userName + "," + this.coursName+","+date;
        String sta = "";
        if (studentsAttendance.isEmpty()){
            return toString;
        }
        else {
        for (Student s : studentsAttendance) {
            sta += s.getUniversityId() + ";";
        }
        toString+=","+sta;
        }
    return toString;
    }
    public void csvtoDate(String date){
        this.date = date ;

    }
    public void csvToArray(String id){
        String[] idl = id.split(";");



        for (String ss : idl) {

            Student student = DataModel.getStudentById(ss);
            this.addStudentAttendance(student);
        }


    }

    public String getUserName() {
        return userName;
    }

    public String getCoursName() {
        return coursName;
    }

    public String getTopic() {
        return topic;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<Student> getStudentsAttendance() {
        return studentsAttendance;
    }

    public void addStudentAttendance(Student student) {
        studentsAttendance.add(student);
    }

    public void removeStudentAttendance(Student student) {
        studentsAttendance.remove(student);
    }


}
