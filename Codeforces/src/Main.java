import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    Main(){
        //this();
        ArrayList<Lesson> lesson = new ArrayList<Lesson>();
        ArrayList<Integer> T = new ArrayList<Integer>();
        T.add(1);T.add(7);T.add(11);
        Lesson math = new Lesson("math","105",T,4);
        Lesson cp = new Lesson("computerProgramming","210",T,6);
        lesson.add(math);
        lesson.add(cp);
        Student per1 = new Student("Mike");
        Student per2 = new Student("Hellon");
        Teacher t1 = new Teacher("Bob");
        Teacher t2 = new Teacher("Yellow");
        t1.addLesson(math);
        t2.addLesson(cp);
        per1.addLesson(math);per1.addLesson(cp);
        cp = new Lesson("computerProgramming","210",T,6);
        cp.setMark(4);
        System.out.println(cp.getMark());
        per2.addLesson(cp);
        System.out.println(per2.getAverageMark());
        System.out.println(per1.getAverageMark());
        DataBase db = new DataBase();
        //db.addPerson(t1);
       // db.addPerson(t2);
        db.addPerson(per1);
        db.addPerson(per2);
        System.out.println("if i search math");
        ArrayList<Person> r = db.getByLessonName("math");
        for(Person i : r){
            System.out.println(i);
        }
        System.out.println("**************");
        System.out.println("if i search Hellon");
        Person tmp = db.getByPersonName("Hellon");
        System.out.println(tmp+"\n**************");

        System.out.println("if i sort by averageMark");
        db.Sort((a,b)->{return -a.getAverageMark()+b.getAverageMark();});
        r = db.getWholeData();
        for(Person i:r){
            System.out.println(i);
        }
        System.out.println("**************");

    }
    public static void main(String[] args){
        new Main();
    }
}