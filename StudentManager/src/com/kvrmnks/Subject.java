package com.kvrmnks;

import java.io.Serializable;
import java.util.ArrayList;

public class Subject implements Serializable {
    private int id,teacherId;

    private String subjectName;

    private double maxPoint,currentPoint;

    private int classroomId;

    private ArrayList<Integer> timePeriod;

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("subject id: "+id+"\n");
        sb.append("subjectName: "+subjectName+" teacherId: "+teacherId+"\n");
        return sb.toString();
    }

    private Subject(){}

    public Subject(String subjectName,int teacherId,double maxPoint,int classRoomId
            ,ArrayList<Integer> timePeriod,int id){
        this.subjectName = subjectName;
        this.teacherId = teacherId;
        this.maxPoint = maxPoint;
        this.classroomId = classRoomId;
        this.timePeriod = timePeriod;
        this.currentPoint = 0;
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherName(int teacherId) {
        this.teacherId = teacherId;
    }

    public double getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(double maxPoint) {
        this.maxPoint = maxPoint;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public ArrayList<Integer> getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(ArrayList<Integer> timePeriod) {
        this.timePeriod = timePeriod;
    }

    public double getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(double currentPoint) {
        this.currentPoint = currentPoint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
