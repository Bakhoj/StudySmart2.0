package com.mycompany.studysmart2.data;

/**
 * Created by anders on 21-Nov-15.
 */
public class Student {
    public int id, phone;
    public String name, lastname, sex, email, birth, address;

    public Course[] Course;
    public University university;

    public Student(int id, String name, String lastname, String sex, String email, String birth, int phone, String address) {

        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.sex = sex;
        this.email = email;
        this.birth = birth;
        this.phone = phone;
        this.address = address;
    }
}
