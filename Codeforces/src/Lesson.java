import java.util.*;
public class Lesson {
    private static final int INITMARK = 0;
    private String lessonName;
    private String lessonClassroom;
    private ArrayList<Integer> lessonTime;
    private int lessonFullMark;
    private int lessonMark;
    public String toString(){
        return "lessonName:"+lessonName+" classroom:"+lessonClassroom+" lessonFullMark:"+lessonFullMark+" lessonMark:"+lessonMark+" lessonTime:"+lessonTime.toString();
    }
    public Lesson(String name,String classroom,ArrayList<Integer> T,int lessonFullMark){
        setName(name);
        setClassroom(classroom);
        setTime(T);
        setFullMark(lessonFullMark);
        setMark(INITMARK);
    }
    public void setName(String name){this.lessonName=name;}
    public void setClassroom(String classroom){this.lessonClassroom=classroom;}
    public void setTime(ArrayList<Integer> T){this.lessonTime=T;}
    public void setFullMark(int mark){this.lessonFullMark=mark;}
    public void setMark(int mark){this.lessonMark=mark;}
    public String getName(){return this.lessonName;}
    public String getClassroom(){return this.lessonClassroom;}
    public ArrayList<Integer> getTime(){return this.lessonTime;}
    public int getFullMark(){return this.lessonFullMark;}
    public int getMark(){return this.lessonMark;}

}
