package com.mycompany.studysmart2.data;

/**
 * Created by anders on 21-Nov-15.
 */
public class StudentChoice {
    public static StudentChoice instance;
    public Student student;
    public Homework homework;
    public int sgmPos, coursePos, fromView;
    public Course course;

    public static final int FROM_POSTPONEDHOMEWORK = 0;
    public static final int FROM_HOMEWORKCALENDAR = 1;

    public StudentChoice(){

    }

    public void setCourse(){
        for (int i = 0; i < Logic.instance.student.Course.length; i++){
            for (int j = 0; j < Logic.instance.student.Course[i].homeworks.length; j++) {
                if (Logic.instance.student.Course[i].homeworks[j] == homework){
                    course = Logic.instance.student.Course[i];
                    return;
                }
            }
        }
    }
}
