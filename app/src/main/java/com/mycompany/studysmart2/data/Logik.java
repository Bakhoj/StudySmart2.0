package com.mycompany.studysmart2.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anders on 21-Nov-15.
 */
public class Logik {
    public static Logik instance;
    public Student student;
    public Date date;
    public University[] availableUniversities;


    public void makeTestData(){
        student = new Student(1234, "Anders", "Andersen", "male", "anders@cbs.dk", "19900821", 12345678, "studievej 2");

        try {
            date  = new SimpleDateFormat("dd-M-yyyy hh:mm:ss").parse("21-08-1992 20:12:10");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("THE MOTHERFUCKING DATE IS: " + date);


        student.Course = new Course[]{
                new Course(1001, "Economics", "Something about economics"),
                new Course(1002, "Business", "Something about Business and stuff")};

        student.Course[0].homeWork = new HomeWork[] {
                new HomeWork(2001, date, 1, "First homework", "bla bla bla long description", "short message", 1),
                new HomeWork(2002, date, 2, "Second homework", "bla bla bla long description", "short message", 2),
                new HomeWork(2003, date, 3, "Third homework", "bla bla bla long description", "short message", 0)
        };

        availableUniversities = new University[]{
                new University(1, "CBS"),
                new University(2, "DTU"),
        };

        StudentChoice.instance = new StudentChoice();
        StudentChoice.instance.student = student;
    }


}
