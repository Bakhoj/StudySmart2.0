package com.mycompany.studysmart2.data;

/**
 * Created by anders on 19-11-2015.
 */
public class HomeWork {
    private int id, date, session;
    private String title, description, shortD;

    public HomeWork(int id, int date, int session, String title, String description, String shortD){
        this.id = id;
        this.date = date;
        this.session = session;
        this.title = title;
        this.description = description;
        this.shortD = shortD;
    }

    public int getId(){
        return this.id;
    }
    public int getDate(){
        return this.date;
    }
    public int getSession() {
        return this.session;
    }
    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public String getShortD(){
        return this.shortD;
    }

    public String toListString() {
        return (title + "\n" + shortD);
    }
}
