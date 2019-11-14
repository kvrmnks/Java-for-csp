import java.util.*;
public class Person {
    private String name;
    private ArrayList<Lesson> lesson;
    public String toString(){
        return name+lesson.toString();
    }
    public Person(){this.lesson=new ArrayList<Lesson>();}
    public Person(String aname){this.name=aname;this.lesson=new ArrayList<Lesson>();}
    public Person(String aname,ArrayList<Lesson> alesson){
        this.name=aname;
        this.lesson=alesson;
    }
    public double getAverageMark(){
        int mark=0,fullMark=0;
        double averageMark=0;
        for(Lesson i:lesson){
            fullMark+=i.getFullMark();
            mark+=i.getMark();
        }
        for(Lesson i:lesson){
            averageMark += (double)i.getFullMark()/fullMark * i.getMark();
        }
        return averageMark;
    }
    public void addLesson(Lesson les){this.lesson.add(les);}
    public void setName(String aname){this.name=aname;}
    public void setLesson(ArrayList<Lesson> alesson){this.lesson=alesson;}
    public ArrayList<Lesson> getLesson(){return this.lesson;}
    public String getName(){return this.name;}
    public ArrayList<Lesson> getLess(){return this.lesson;}
}
