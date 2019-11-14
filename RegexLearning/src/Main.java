import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main{
    Main() {
        String regex = "ture\\b";
        String content = "help capture picture lecture wise local value";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        int count = 0;
        while(m.find()){
            count++;
            System.out.println("Match Number "+count);
            System.out.println("start at "+m.start());
            System.out.println("end at "+m.end());
        }
        Class.forName("System.in");
    }
    public static void main(String[] args){
        new Main();
    }
}