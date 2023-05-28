package com.example.test_javafx.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Course {

    private String courseName;
private  String discribtion;
    private String textbook;
    private String instructor;
    private String classRoom;
    private String teatcherAssiment;
    private ArrayList<Lecture> lectures =new ArrayList<Lecture>();
    private ArrayList<Student> students=new ArrayList<Student>();

    //name,subject,textbook,teatcher,classroom
    public Course(String courseName, String discribtion, String textbook,
                  String instructor,String teatcherAssiment, String virtualRoom) {
        this.courseName = courseName;
        this.textbook = textbook;
        this.instructor = instructor;
        this.teatcherAssiment=teatcherAssiment;
        this.classRoom = virtualRoom;
        this.discribtion= discribtion;

        }

    public String getClassRoom() {
        return classRoom;
    }

    public String getDiscribtion() {
        return discribtion;
    }

    public Course() {

    }

    public ArrayList<String>  getTopic() {
        ArrayList<String> topic = new ArrayList<String>();
        for (Lecture l : lectures) {
            topic.add(l.getTopic());
        }
        return topic;
    }
    public void csvToArrayStudent( String idStudent ){
        String [] idStudentsarray = idStudent.split(";");

        for (String ss: idStudentsarray){

            Student student =DataModel.getStudentById(ss);
            this.addStudent(student);
            student.addCourse(this);

        }

    }

    public void csvToArraysLecture(  String topicLecture ){
        for (String st : topicLecture.split(";")){
            addLecture(DataModel.getLectureBytopic(st));
        }

        }




    public String getTeatcherAssiment() {
        return teatcherAssiment;
    }

    public String toStringCsvCourse() {

        String toString =  this.courseName + "," + this.discribtion + "," + this.textbook + "," + this.instructor + "," + this.teatcherAssiment + "," + this.classRoom;
        String stString="";
for (Student student : students){
    stString+=student.getUniversityId()+";";
    //1202;125;56666
}
toString+=","+stString;


        String stLecture="";
        for (Lecture lecture : lectures){

            stLecture+=lecture.getTopic()+";";
            //oop;oop2;
        }
        toString+=","+stLecture;
//user,name,classroom, 1202;125;56666,oop;oop2;

return toString;
     }

    public String getCourseName() {
        return courseName;
    }



    public String getTextbook() {
        return textbook;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getVirtualRoom() {
        return classRoom;
    }

    public ArrayList<Lecture> getLectures() {
        return lectures;
    }

    public void addLecture(Lecture  lecture) {
        lectures.add(lecture);
    }
    public void removeLecture(Lecture lecture) {
        lectures.remove(lecture);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
    public void addStudent (Student student){
        if(!isStudentExist(student)) {
            students.add(student);
            student.addCourse(this);
        }
    }

    public boolean isStudentExist(Student student){
        for (Student student1:students){
            if(student1==student){
                return true;
            }
        }
        return false;
    }
    public void removeStudent(String  student) {
        Iterator<Student> iterator =getStudents().iterator();
        while (iterator.hasNext()) {
            Student studentRemoved = iterator.next();
            if (studentRemoved.getUniversityId().equals(student)) {
                iterator.remove();
                break;
            }
        }
    }



    // Other methods as needed
}
