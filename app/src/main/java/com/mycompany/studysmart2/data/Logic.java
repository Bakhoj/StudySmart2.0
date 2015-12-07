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

        date1 = new Date(new Date().getTime() + (1000*60*60*24*7));
        date2 = new Date(date1.getTime() + (1000*60*60*24*7));
        date3 = new Date(date2.getTime() + (1000*60*60*24*7));

        student.Course = new Course[]{
                new Course(1001, "Økonomi", "The basale omkring handel, skat og revision"),
                new Course(1002, "International Business", "Start et firma fra bunden, hvad kræver det og hvad skal der til?")};

        student.Course[0].homeworks = new Homework[] {
                new Homework(2001, date1, 1, "Introduktion", "Læs afsnit om introduktion og indledning til økonomi og handel", "Læs 1-3 kapitel", 1),
                new Homework(2002, date2, 2, "Anden Undervisning", "Læs de resterende afsnit om handel og begynd på kapitlerne omkring skatteret \n\n Der vil være gruppe arbejde efter lektionen", "Læs 5,7-9 kapitel", 2),
                new Homework(2003, date3, 3, "Første Projekt", "Genlæs alle tidligere kapitler \n\n Vi gennemgår første projekt some skal afleveres om 2uger, og skal gennemgåes", "Genlæs 1-3, 5, 7-9", 0)
        };

        student.Course[1].homeworks = new Homework[] {
                new Homework(3001, new Date(date1.getTime() + (1000*60*60*24*2)), 2, "1. Lektor", "Read page 12-243 and prepare a business concept for furture project", "Introduction to Business", 2),
                new Homework(3003, new Date(date2.getTime() + (1000*60*60*24*2)), 3, "2. Lektor", "Read pager 350-365, 540-600 \n\n Work furthere on your business concept", "International taxes", 0),
                new Homework(3002, new Date(date3.getTime() + (1000*60*60*24*2)), 2, "3. Lektor", "Form groups of 2-5 people and find a project you wish to work on", "European import taxes", 0)
        };

        availableUniversities = new University[]{
                new University(1, "CBS"),
                new University(2, "DTU")
        };

        StudentChoice.instance = new StudentChoice();

        studyGroupsMaster = new StudyGroupsMaster();
        studyGroupsMaster.addGroup("0001", "Økonomi", "Økonomi teksts gennemgang", "Gennemgang af teksten og diskussion og den indfydelse", "CBS Frederiksberg", student.Course[0], new Date(date1.getTime() + (1000*60*60*24*2)));
        studyGroupsMaster.addGroup("0002", "Internationalbuisness", "Diskussion", "Diskussion om opstartsvirksomheder DO's & DONT's", "Kantinen, CBS Frederiksberg", student.Course[1], new Date(new Date().getTime() + (1000*60*60)));
    }




}
