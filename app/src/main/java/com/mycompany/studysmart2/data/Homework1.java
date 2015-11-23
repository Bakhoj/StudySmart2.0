package com.mycompany.studysmart2.data;

import java.util.Date;

/**
 * Created by anders on 19-11-2015.
 */
public class Homework1 {
    public Date date;
    public int id, session, status;
    public String title, description, shortdescription;

    public Homework1(int id, Date date, int session, String title, String description, String shortD, int status){
        this.id = id;
        this.date = date;
        this.session = session;
        this.title = title;
        this.description = description;
        this.shortdescription = shortD;
        this.status = status;
    }

    @Override
    public String toString() {
        return title + "\n"
                + date + "\n"
                + shortdescription;
    }

    /*public String toListString() {
        return (title + "\n" + shortdescription);
    }*/
}
