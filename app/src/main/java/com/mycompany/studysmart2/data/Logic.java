package com.mycompany.studysmart2.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anders on 21-Nov-15.
 */
public class Logic {
    public static Logic instance;
    public Student student;
    public Date date, date1, date2, date3;
    public University[] availableUniversities;
    public StudyGroupsMaster studyGroupsMaster;




    public void makeTestData(){
        student = new Student(1234, "Anders", "Andersen", "male", "anders@cbs.dk", "19900821", 12345678, "studievej 2");

        try {
            date  = new SimpleDateFormat("dd-M-yyyy hh:mm:ss").parse("21-08-1992 20:12:10");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        date1 = new Date(new Date().getTime() + (1000*60*60));
        date2 = new Date(date1.getTime() + 86400000);
        date3 = new Date(date2.getTime() + 86400000);

        System.out.println("THE MOTHERFUCKING DATE IS: " + date);
        System.out.println("THE MOTHERFUCKING DATE IS: " + date1);
        System.out.println("THE MOTHERFUCKING DATE IS: " + date2);
        System.out.println("THE MOTHERFUCKING DATE IS: " + date3);

        student.Course = new Course[]{
                new Course(1001, "Economics", "Something about economics"),
                new Course(1002, "Business", "Something about Business and stuff")};

        student.Course[0].homeworks = new Homework[] {
                new Homework(2001, date1, 1, "First hw", "bla bla bla long description", "short message", 1),
                new Homework(2002, date2, 2, "Second hw", "bla bla bla long description", "short message", 2),
                new Homework(2003, date3, 3, "Third hw", "bla bla bla long description", "short message", 0)
        };

        student.Course[1].homeworks = new Homework[] {
                new Homework(3001, new Date(date1.getTime() + (1000*60)), 2, "Something homework", "Long long description for dem all", "short message for this homework", 1),
                new Homework(3003, new Date(date2.getTime() + (1000*60*10)), 3, "first homework", "more with long descripttions", "short message for this homework", 2),
                new Homework(3002, new Date(date3.getTime() + (1000*60)), 2, "more with homework", "more dummy text for a very very long description", "short message for this homework", 1)
        };

        availableUniversities = new University[]{
                new University(1, "CBS"),
                new University(2, "DTU")
        };

        StudentChoice.instance = new StudentChoice();
        StudentChoice.instance.student = student;

        studyGroupsMaster = new StudyGroupsMaster();
        studyGroupsMaster.addGroup("0001", "bedstemors bageklub", "Små kage opskrifter", "meget mere om småkager", "kebab huset", student.Course[0], new Date());
        studyGroupsMaster.addGroup("0002", "Den seriøse læse gruppe", "nørdede læse stuff", "meget mere om nørdede læse stuff", "et seriøst sted", student.Course[1], new Date(new Date().getTime() + (1000*60*60)));
    }




}
