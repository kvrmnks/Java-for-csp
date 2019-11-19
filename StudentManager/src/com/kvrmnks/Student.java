package com.kvrmnks;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
    public final static int SEXBOY = 0x3f3f3f3f;
    public final static int SEXGIRL = 0x7f7f7f7f;
    private ArrayList<Subject> subjects;
    private int id,grade,sex,old;
    private String name;

    @Override
    public String toString(){
        return name + " " + id +" "+grade+" "+this.getAveragePoint();
    }

    private Student(){}


    public Student(int id,int grade,int sex,int old,String name){
        this.id = id;
        this.grade = grade;
        this.sex = sex;
        this.old = old;
        this.name = name;
        this.subjects = new ArrayList<Subject>();
    }

    public void addSubject(Subject subject){
        this.subjects.add(subject);
    }

    public void addSubject(Subject[] subject){
        for(Subject s : subject){
            this.subjects.add(s);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void sort(Comparator<Subject> cmp){
        for(int i=0;i<this.subjects.size();i++){
            for(int j=0;j<this.subjects.size()-1;j++){
                if(cmp.compare(this.subjects.get(j),this.subjects.get(j+1))>0){
                    Subject tmp = this.subjects.get(j);
                    this.subjects.set(j,this.subjects.get(j+1));
                    this.subjects.set(j+1,tmp);
                }
            }
        }
    }

    public double getAveragePoint(){
        double pointSum = 0;
        for(Subject s : this.subjects){
            pointSum += s.getMaxPoint();
        }
        double averagePoint = 0;
        if(averagePoint == 0){
            return 0;
        }
        for(Subject s : this.subjects){
            averagePoint += s.getMaxPoint()/pointSum*s.getCurrentPoint();
        }
        return averagePoint;
    }
}

