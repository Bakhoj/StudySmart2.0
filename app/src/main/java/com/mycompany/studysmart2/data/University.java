package com.mycompany.studysmart2.data;

/**
 * Created by anders on 22-Nov-15.
 */
public class University {

    public int id;
    public String name;

    public University(int id, String name) {

        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
