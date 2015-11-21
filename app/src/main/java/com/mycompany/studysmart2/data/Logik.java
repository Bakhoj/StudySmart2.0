package com.mycompany.studysmart2.data;

/**
 * Created by anders on 21-Nov-15.
 */
public class Logik {
    public static Logik instance;
    public Student[] students;

    public void makeTestData() {
        students = new Student[]{
                new Student("Lars Larsen"),
                new Student("Anders Andersen")};

        Student lars = students[0];
        lars.Course = new Course[]{
                new Course("Economics"),
                new Course("Business")};

        StudentChoice.instance = new StudentChoice();
        StudentChoice.instance.student = lars;    }


}
