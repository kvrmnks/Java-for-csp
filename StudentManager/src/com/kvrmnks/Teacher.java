package com.kvrmnks;

import java.io.Serializable;
import java.util.ArrayList;

public class Teacher implements Serializable {
    String name,telephoneNumber;
    int id;
    ArrayList<Subject> subjects;

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("teacher`s name is "+this.name+"\n");
        sb.append("tel: "+telephoneNumber+"\n");
        sb.append("subjects:");
        for(Subject s : subjects){
            sb.append(" "+s.getId());
        }
        sb.append("\n");
        return sb.toString();
    }

    private Teacher(){}
    public Teacher(String name,String telephoneNumber,int id){
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.id = id;
        this.subjects = new ArrayList<Subject>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }
}
