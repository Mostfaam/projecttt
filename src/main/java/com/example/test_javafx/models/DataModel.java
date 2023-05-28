package com.example.test_javafx.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class DataModel {
    private static String whoTeatcher;
    private int indexToVerPass;
    private static ArrayList<Student> students = new ArrayList<Student>();
    private static ArrayList<Teatcher> teatchers = new ArrayList<Teatcher>();
    private static ArrayList<Course> courses = new ArrayList<Course>();
    private static ArrayList<Lecture> lectures = new ArrayList<Lecture>();


    public DataModel() {
        initialize();
    }

    public static void initialize() {
        if (students.isEmpty()) {
            File file = new File("students.csv");
            if (file.exists()) {
                try {
                    Scanner scanner = new Scanner(file);
                    String[] strings = scanner.nextLine().split(", ");
                    //read header line

                    while (scanner.hasNext()) {

                         strings = scanner.nextLine().split(",");
                      Student student =  new Student(strings[0], strings[1], strings[2], strings[3], Float.parseFloat(strings[4]));
                        students.add(student);


                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                // add Test data when you run the program for the first time
                students.add(new Student("Student 1", "120210994", "0599480220", "gaza", 95f));
                students.add(new Student("Student 2", "120210994", "0599480220", "gaza", 95f));
                students.add(new Student("Student 3", "120210994", "0599480220", "gaza", 95f));
                students.add(new Student("Student 4", "120210994", "0599480220", "gaza", 95f));
            }
        }



        if (lectures.isEmpty()) {
            File file = new File("lectuers.csv");
            if (file.exists()) {
                try {
                    Scanner scanner = new Scanner(file);
                    //read header line
                    String[] strings = scanner.nextLine().split(",");

                    while (scanner.hasNext()) {
                        strings = scanner.nextLine().split(",");
                         Lecture lecture = new Lecture(strings[0], strings[1], strings[2], strings[3]);

                        lectures.add(lecture);
                        if(strings.length==5) {
                            lecture.csvtoDate(strings[4]);
                        }
                        if (strings.length==6){
                            lecture.csvToArray(strings[5]);

                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
        if (courses.isEmpty()) {
            File file = new File("courses.csv");
            if (file.exists()) {
                try {
                    Scanner scanner = new Scanner(file);
                    //read header line
                    String[] strings = scanner.nextLine().split(",");
                    while (scanner.hasNext()) {
                        strings = scanner.nextLine().split(",");
                        //String courseName,  String textbook, String instructor,String teatcherAssiment, String virtualRoom

                       Course course=new Course(strings[0], strings[1], strings[2], strings[3], strings[4], strings[5]);
                        courses.add(course);
                        if(strings.length==8) {
                            course.csvToArrayStudent(strings[6]);
                            course.csvToArraysLecture(strings[7]);
                        }

                    }






                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }
        if (teatchers.isEmpty()) {
            File file = new File("teatchers.csv");
            if (file.exists()) {
                try {
                    Scanner scanner = new Scanner(file);
                    //read header line


                    String[] strings = scanner.nextLine().split(",");


                    while (scanner.hasNext()) {
                        strings = scanner.nextLine().split(",");
                       Teatcher teatcher= new Teatcher(strings[0], strings[1], strings[2], strings[3]);

                        teatchers.add(teatcher);
                        if(strings.length==5) {
                            teatcher.csvToArrays(strings[4]);
                        }
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public static void setion (String userName){
        try (PrintWriter pw = new PrintWriter("login.csv")) {
            //print header line
            if(userName == null){
                pw.println("userName");
            }
            else {
                pw.println("userName");
                pw.println(userName);
            }
    }catch (FileNotFoundException e) {
            e.printStackTrace();
        }}
    public static String getSetion(){
        String login="";
        File file = new File("login.csv");
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                 String s = scanner.nextLine();
                 if( scanner.hasNextLine()) {
                     login ="views/teacher.fxml";
                     String userNameEnterd = scanner.nextLine();
                     whoTeatcher= (userNameEnterd);

                 }
                 else login= "views/login.fxml";




            }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }}
        return login;
    }
    public static ArrayList<Teatcher> getTeatchers() {
        return teatchers;
    }

    public static void addTeatcher(Teatcher teatcher) {
        teatchers.add(teatcher);
    }

    public static void deleteTeatcherByUsername(String name) {
        for (int i = 0; i < teatchers.size(); i++) {
            if (Objects.equals(teatchers.get(i).getUsername(), name)) {
                teatchers.remove(i);
            }
        }
    }

    public static int getIndexByUsername(String username) {
        int index = -1;
        for (int i = 0; i < teatchers.size(); i++) {
            if (Objects.equals(teatchers.get(i).getUsername(), username)) {
                index = i;
            }

        }
        return index;
    }
    public static Teatcher getTeatcherByUsername(String username) {

        for (int i = 0; i < teatchers.size(); i++) {
            if (Objects.equals(teatchers.get(i).getUsername(), username)) {
              return teatchers.get(i);
            }

        }
        return null;
    }

    public boolean varUserName(String userName) {
        boolean torf = false;
        for (int i = 0; i < teatchers.size(); i++) {
            if (userName.equals(teatchers.get(i).getUsername())) {
                indexToVerPass = i;
                torf = true;
            }
        }
        return torf;
    }

    public boolean isManeger(String userName, String pass, User u) {
        boolean torf = false;
        if ((userName.equals(u.getUsername())) && (pass.equals(u.getPassword()))) {
            torf = true;

        } else torf = false;
        return torf;
    }


    public boolean varPassword(String pass) {

        return pass.equals(teatchers.get(indexToVerPass).getPassword());
    }

    public void setWhoTeatcher(String whoTeatcher) {
        this.whoTeatcher = whoTeatcher;
    }

    public void wohTeatcher(String userName) {
        setWhoTeatcher(userName);
    }

    public String getWhoTeatcher() {
        return whoTeatcher;
    }

    public void saveTeatchers() {
        try (PrintWriter pw = new PrintWriter("teatchers.csv")) {
            //print header line
            pw.println("name, username,password,phone,course");
            for (int i = 0; i < teatchers.size(); i++) {
                //print each student object as string
                Teatcher teatcher = teatchers.get(i);
                pw.println(teatcher.toStringCsv());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static void addCourses(Course course) {
        courses.add(course);
    }

    public static void deleteCourses(Course course) {
        courses.remove(course);
    }

    public static void deleteCourseByName(String name) {
     courses.remove(getCourseByname(name));
    }
    public static Course getCourseByname(String courseName) {

        for (int i = 0; i < courses.size(); i++) {

            if ((courses.get(i).getCourseName()).equals(courseName)) {
                return  courses.get(i);

            }
        }
    return null;}


    public void saveCourse() {
        try (PrintWriter pw = new PrintWriter("courses.csv")) {
            //print header line
            //name,subject,textbook,teatcher,classroom
            pw.println("name,discription,textbook,teatcher,teatcherAssiment,classroom,id,topic");
            for (int i = 0; i < courses.size(); i++) {
                //print each student object as string
                Course course = courses.get(i);
                pw.println(course.toStringCsvCourse());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Lecture> getLecture() {
        return lectures;
    }

    public static void addLecture(Lecture lecture) {
        lectures.add(lecture);
    }
    public static Lecture getLectureBytopic(String topic) {

        for (int i = 0; i < lectures.size(); i++) {
            if (Objects.equals(lectures.get(i).getTopic(),topic)) {
                return  lectures.get(i);
            }
        }
        return  null;
    }
    public void saveLectures() {
        try (PrintWriter pw = new PrintWriter("lectuers.csv")) {
            //print header line
            pw.println("topic,classRoom,userName,couseName");
            for (int i = 0; i < lectures.size(); i++) {
                //print each student object as string
                Lecture lecture = lectures.get(i);
                pw.println(lecture.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save_Students() {
        try (PrintWriter pw = new PrintWriter("students.csv")) {
            //print header line
            pw.println("name, universityId, phoneNumber, residenceArea, gpa");
            for (Student s : students) {
                //print each student object as string
                pw.println(s.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

public void addStudent(Student student) {
        students.add(student);
    }

    public static Student getStudentByName(String name) {
        for (Student student : students) {
            if (Objects.equals(student.getName(), name)) {
                return student;
            }
        }
        return null;
    }
    public static Student getStudentById(String id) {

        for (int i = 0; i < students.size(); i++) {
            if (Objects.equals(students.get(i).getUniversityId(),id)) {
              return  students.get(i);
            }
        }
        return null;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Student deleteStudentByName(String name) {
        for (Student student : students) {
            if (Objects.equals(student.getName(), name)) {
                students.remove(student);
                return student;
            }
        }
        return null;
    }
}
