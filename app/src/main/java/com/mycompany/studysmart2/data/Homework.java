package com.mycompany.studysmart2.data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anders on 19-11-2015.
 */
public class Homework implements Comparable<Homework>{
    public Date date;
    public int id, session, status;
    public String title, description, shortdescription;

    public Homework(int id, Date date, int session, String title, String description, String shortD, int status){
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
        SimpleDateFormat dt = new SimpleDateFormat("EEEE d MMM HH:mm");

        return title + "\n"
                + dt.format(date) + "\n"
                + shortdescription;
    }

    @Override
    public int compareTo(Homework another) {
        return this.date.compareTo(another.date);
    }
}
