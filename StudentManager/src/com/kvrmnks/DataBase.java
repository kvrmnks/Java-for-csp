package com.kvrmnks;

import com.sun.istack.internal.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

public class DataBase implements Serializable {
    ArrayList<Student> students;
    ArrayList<Subject> subjects;
    ArrayList<Teacher> teachers;

    public DataBase(){
        students = new ArrayList<Student>();
        subjects = new ArrayList<Subject>();
        teachers = new ArrayList<Teacher>();
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    public ArrayList<Subject> getSubjects() {
        return subjects;
    }
    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }
    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }
    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }
    public Student getStudentById(int id){
        for(Student s : students){
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }
    public Subject getSubjectById(int id){
        for(Subject s : subjects){
            if(s.getId() == id){
                return s;
            }
        }
        return null;
    }
    public Teacher getTeacherById(int id){
        for(Teacher t : teachers){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
    private void sort(ArrayList al, Comparator cmp){

        for(int i=0;i<al.size();i++){
            for(int j=0;j<al.size()-1;j++){
                Object x = al.get(j);
                Object y = al.get(j+1);
                if(cmp.compare(x,y) >= 0){
                    al.set(j,y);
                    al.set(j+1,x);
                }
            }
        }

    }
    public void sortStudents(Comparator<Student> cmp){
        this.sort(this.students,cmp);
    }
    public void sortTeachers(Comparator<Teacher> cmp){
        this.sort(this.teachers,cmp);
    }
    public void sortSubjects(Comparator<Subject> cmp){
        this.sort(this.subjects,cmp);
    }
    public void addStudent(Student x){
        this.students.add(x);
    }
    public void addStudent(Student[] x){
        for(Student s : x){
            this.addStudent(s);
        }
    }
    public void addTeacher(Teacher t){
        this.teachers.add(t);
    }
    public void addTeacher(Teacher[] x){
        for(Teacher t : x){
            this.addTeacher(t);
        }
    }
    public void addSubject(Subject x){
        this.subjects.add(x);
    }
    public void addSubject(Subject[] x){
        for(Subject s : x){
            this.addSubject(s);
        }
    }
    public void removeStudentById(int x){
        for(Student s : this.students){
            if(s.getId() == x){
                this.students.remove(s);
                return;
            }
        }
    }
    public void removeStudentById(int[] x){
        for(int t : x){
            this.removeStudentById(t);
        }
    }
    public void removeTeacherById(int x){
        for(Teacher s : this.teachers){
            if(s.getId() == x){
                this.teachers.remove(s);
                return;
            }
        }
    }
    public void removeTeacherById(int[] x){
        for(int t : x){
            this.removeTeacherById(t);
        }
    }
    public void removeSubjectById(int x){
        for(Subject s : this.subjects){
            if(s.getId() == x){
                this.subjects.remove(s);
                return;
            }
        }
    }
    public void removeSubjectById(int[] x){
        for(int t : x){
            this.removeSubjectById(t);
        }
    }
}
