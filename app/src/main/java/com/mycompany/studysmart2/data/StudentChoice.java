package com.mycompany.studysmart2.data;

/**
 * Created by anders on 21-Nov-15.
 */
public class StudentChoice {
    public static StudentChoice instance;
    public int sgmPos, coursePos, homeworkPos, fromView;

    public static final int FROM_POSTPONEDHOMEWORK = 0;
    public static final int FROM_HOMEWORKCALENDAR = 1;

    public StudentChoice(){

    }

    public void setPos(Homework homework) {
        for (int i = 0; i < Logic.instance.student.Course.length; i++){
            for (int j = 0; j < Logic.instance.student.Course[i].homeworks.length; j++) {
                if (Logic.instance.student.Course[i].homeworks[j] == homework){
                    coursePos = i;
                    homeworkPos = j;
                    return;
                }
            }
        }
    }
}
