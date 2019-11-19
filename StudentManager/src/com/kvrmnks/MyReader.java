package com.kvrmnks;

/*
请输入编号，指定你要的服务
1，添加老师
2，批量添加老师
4，添加课程
5，批量添加课程
6，添加学生
7，批量添加学生
8，给学生添加课程
9，批量给学生添加课程
10，查看老师信息
11，查看课程信息
12，查看学生信息
13，修改学生学分
14，批量修改学生学分
15，保存
16，保存并退出

*/
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MyReader {
    private DataBase db;
    private Scanner sc;
    private final static String POS = "data.db";
    public MyReader(){
        sc = new Scanner(System.in);
        db = new DataBase();
        try{
            FileInputStream filein = new FileInputStream(MyReader.POS);
            ObjectInputStream in =  new ObjectInputStream(filein);
            DataBase ddb = (DataBase)in.readObject();
            db = ddb;
            filein.close();
            in.close();
        }
        catch(FileNotFoundException e){}
        catch(IOException e){e.printStackTrace();}
        catch(ClassNotFoundException e){e.printStackTrace();}
    }
    private void printHelpList(){
        System.out.println("请输入编号，指定你要的服务\n" +
                "1，添加老师\n" +
                "2，添加课程\n" +
                "3，添加学生\n" +
                "4，给学生添加课程\n" +
                "5，查询老师信息\n" +
                "6，查询课程信息\n" +
                "7，查询学生信息\n" +
                "8，保存\n" +
                "9，退出并保存");
    }
    private void print(String s){System.out.println(s);}
    private int readInt()throws TypeException{
        return Integer.parseInt(sc.nextLine());
    }
    private String readString(){
        return sc.nextLine();
    }
    private void addTeacher()throws TypeException{
        this.print("请输入教师的姓名");
        String name = sc.nextLine();
        this.print("请输入教师的联系方式");
        String tel = sc.nextLine();
        this.print("请输入教师的编号");
        String id = sc.nextLine();
        int port = 0;
        try{
            port = Integer.parseInt(id);
        }catch(NumberFormatException e){
            throw new TypeException();
        }
        Teacher ret = db.getTeacherById(port);
        if(ret != null) throw new TypeException();
        db.addTeacher(new Teacher(name,tel,port));
    }
    private void addSubject()throws TypeException{
        /*String subjectName,String teacherId,double maxPoint,int classroomId
            ,ArrayList<Integer> timePeriod,int id
        * */
        try {
            this.print("请输入学科名");
            String subjectName = sc.nextLine();

            this.print("请输入教师编号");
            String sTeacherId = sc.nextLine();
            int teacherId = 0;
            teacherId = Integer.parseInt(sTeacherId);

            this.print("请输入学科学分");
            String sMaxPoint = sc.nextLine();
            double maxPoint = 0;
            maxPoint = Double.parseDouble(sMaxPoint);

            this.print("请输入教室编号");
            String sClassroomId = sc.nextLine();
            int classroomId = Integer.parseInt(sClassroomId);

            this.print("请输入时间列表，以空格分隔");
            String stimePeriod = sc.nextLine();
            String[] timePeriod = stimePeriod.split(" ");
            ArrayList<Integer> al = new ArrayList<Integer>();
            for(String str : timePeriod){
                if(str==null || str.equals("")){continue;}
                al.add(Integer.parseInt(str));
            }

            this.print("请输入课程编号");
            String sId = sc.nextLine();
            int id = Integer.parseInt(sId);

            db.addSubject(new Subject(subjectName,teacherId,maxPoint,classroomId,al,id));

        }catch(NumberFormatException e){
            throw new TypeException();
        }
    }
    private void addStudent()throws TypeException{
        /*int id,int grade,int sex,int old,String name*/
        this.print("请输入学生编号");
        int id = this.readInt();

        this.print("请输入学生年级");
        int grade = this.readInt();

        this.print("请输入性别 男为0 女为1");
        int sex = this.readInt();

        this.print("请输入学生年龄");
        int age = this.readInt();

        this.print("请输入学生姓名");
        String name = this.readString();

        db.addStudent(new Student(id,grade,sex,age,name));
    }
    private void addSubjectForStudent()throws TypeException{
        try{
            this.print("请输入该学生编号");
            Student s = db.getStudentById(this.readInt());
            this.print("请输入要添加的课程编号，用空格分隔开");
            String[] subjects = readString().split(" ");
            for(String str : subjects){
                if(str == null || str.equals(""))continue;
                Subject subject = db.getSubjectById(Integer.parseInt(str));
                if(subject != null)
                    s.addSubject(subject);
            }
        }catch(NullPointerException e){
            this.print("没有该学生");
            throw new TypeException();
        }
    }
    private void getTeacher()throws TypeException{
        ArrayList<Teacher> al = db.getTeachers();
        for(int i=0;i<al.size();i++){
            this.print(al.get(i).toString());
        }
    }
    private void getSubject()throws TypeException{
        ArrayList<Subject> al = db.getSubjects();
        for(int i=0;i<al.size();i++){
            this.print(al.get(i).toString());
        }
    }
    private void getStudent()throws TypeException{
        ArrayList<Student> al = db.getStudents();
        for(int i=0;i<al.size();i++){
            this.print(al.get(i).toString());
        }
    }
    private void save(){
        FileOutputStream fileout;
        ObjectOutputStream out;
        try{
            File f = new File(MyReader.POS);
            f.delete();
            f.createNewFile();
            fileout = new FileOutputStream(MyReader.POS);
            out = new ObjectOutputStream(fileout);
            out.writeObject(db);
            out.close();
            fileout.close();
        }
        catch(IOException e){}
    }
    private void quit(){}
    private int getOptionNumber() throws TypeException{
        String str = sc.nextLine();
        try{
            int ret = Integer.parseInt(str);
            return ret;
        }catch(NumberFormatException e){
            throw new TypeException();
        }
    }
    public void mainloop(){
        while(true){
            try {
                this.printHelpList();
                switch (this.getOptionNumber()) {
                    case 1:
                        this.addTeacher();
                        break;
                    case 2:
                        this.addSubject();
                        break;
                    case 3:
                        this.addStudent();
                        break;
                    case 4:
                        this.addSubjectForStudent();
                        break;
                    case 5:
                        this.getTeacher();
                        break;
                    case 6:
                        this.getSubject();
                        break;
                    case 7:
                        this.getStudent();
                        break;
                    case 8:
                        this.save();
                        break;
                    case 9:
                        this.save();
                        this.quit();
                        return;
                    default:

                }
            }catch(TypeException  e){
                System.out.println("输入格式有误，请重新输入!");
            }
        }
    }

}
