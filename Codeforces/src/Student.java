import java.util.ArrayList;

public class Student extends Person {
    public Student(String aname){super(aname);}
    public Student(String aname, ArrayList<Lesson> alesson){
        super(aname,alesson);
    }
    public Student() {
        super();
    }
}