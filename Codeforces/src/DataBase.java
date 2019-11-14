import java.util.*;
interface Compare<T>{
    public double compare(T a,T b);
}
public class DataBase {
    private ArrayList<Person> dataPerson;
    public DataBase(){
        dataPerson=new ArrayList<Person>();
    }
    public ArrayList<Person> getWholeData(){
        return (ArrayList<Person>)dataPerson.clone();
    }
    public void addPerson(Person p){
        dataPerson.add(p);
    }
    public ArrayList<Person> getByLessonName(String name){
        ArrayList<Person> ret=new ArrayList<Person>();
        for(Person i:dataPerson){
            ArrayList<Lesson> lesson=i.getLesson();
            for(Lesson j:lesson){
                if(j.getName().equals(name)){
                    ret.add(i);
                    break;
                }
            }
        }
        return ret;
    }
    public Person getByPersonName(String name){
        for(Person i:dataPerson)
            if(i.getName().equals(name))
                return i;
        return null;
    }
    public void Sort(Compare<Person> cmp){
        for(int k=0;k<dataPerson.size();k++){
            for(int i=1;i<dataPerson.size();i++){
                if(cmp.compare(dataPerson.get(i-1),dataPerson.get(i))>0){
                    Person t = dataPerson.get(i-1);
                    dataPerson.set(i-1,dataPerson.get(i));
                    dataPerson.set(i,t);
                }
            }
        }
    }
}
