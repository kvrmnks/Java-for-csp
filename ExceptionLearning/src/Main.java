import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;
class MyException extends Exception{

}
public class Main {
    public static void main(String[] args){
        try{
            throw new MyException();
        }catch(Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Logger.getLogger("233").severe(sw.toString());
        };
    }
}
