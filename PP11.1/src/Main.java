import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s;
        do {
         s = sc.next();
         try {
             if (s.length() > 20) throw new StringTooLongException("233");
         }catch(StringTooLongException e){
             System.out.println("Toolong");
         }finally{
             System.out.println("InputNext");
         }
        }while(!s.equals("DOWN"));
    }
}
class StringTooLongException extends Exception{
    public StringTooLongException(String s){
        super(s);
    }
}