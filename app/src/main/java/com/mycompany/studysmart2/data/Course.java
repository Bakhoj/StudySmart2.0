package com.mycompany.studysmart2.data;

/**
 * Created by anders on 21-Nov-15.
 */
public class Course {
    public int id;
    public String name;
    public String description;

    public Homework[] homeworks;

    public Course(int id, String name, String description ) {

        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
}
