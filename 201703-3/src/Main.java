import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    void solveString(String string){
        for(int i=0;i<string.length();i++){
            char c = string.charAt(i);
            if(c == '_'){
                int L = i , R = i + 2;
                for(;R<string.length();R++)if(string.charAt(R)=='_')break;
                System.out.print("<em>");
                StringBuilder sb = new StringBuilder();
                for(int j=L+1;j<R;j++){sb.append(string.charAt(j));}
                solveString(sb.toString());
                System.out.print("</em>");
                i=R;
            }else if(c == '[') {
                StringBuilder sb1 = new StringBuilder(); // []
                StringBuilder sb2 = new StringBuilder(); // ()
                int L1, L2, R1, R2;
                L1 = i;
                for (R1 = L1; R1 < string.length(); R1++) if (string.charAt(R1) == ']') break;
                for (int j = L1 + 1; j < R1; j++) sb1.append(string.charAt(j));

                L2 = R1 + 1;
                for (R2 = L2; R2 < string.length(); R2++) if (string.charAt(R2) == ')') break;
                for (int j = L2 + 1; j < R2; j++) sb2.append(string.charAt(j));

                System.out.print("<a herf=\"");
                solveString(sb2.toString());
                System.out.print("\">");
                solveString(sb1.toString());
                System.out.print("</a>");
                i = R2;
            }else{
                System.out.print(c);
            }
        }
    }
    void convertString(String string,int rankOfHead){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=rankOfHead;i<string.length();i++){
            char currentChar = string.charAt(i);
            if(currentChar == ' '){
                int tmp = i;
                while(i+1<string.length() && string.charAt(i+1)==' ')i++;
                if(tmp != rankOfHead)solveString(" ");
                continue;
            }else{
                int j = i;
                for(;j<string.length();j++){
                    if(string.charAt(j)==' ')break;
                    stringBuilder.append(string.charAt(j));
                }
                solveString(stringBuilder.toString());
                stringBuilder = new StringBuilder();
                i = j - 1;
            }
        }

    }
    void solveBlock(ArrayList<String> al){
        if(al.size()==1){
            char c = al.get(0).charAt(0);
            if(c == '#'){
                int rankOfHead = 0;
                for(int i=0;i<al.get(0).length();i++){
                    if(al.get(0).charAt(i)=='#')rankOfHead++;
                    else break;
                }

                System.out.print("<h"+rankOfHead+">");
                convertString(al.get(0),rankOfHead);
                System.out.println("</h"+rankOfHead+">");
                return;
            }
        }
        if(al.get(0).charAt(0)=='*'){
            System.out.println("<ul>");
            for(int i=0;i<al.size();i++){
                System.out.print("<li>");
                String currentString = al.get(i);
                for(int j=0;j<currentString.length();j++){
                    if(currentString.charAt(j)!='*'){
                        convertString(currentString,j);
                        break;
                    }
                }
                System.out.println("</li>");
            }
            System.out.println("</ul>");
        }else{
            System.out.print("<p>");
            for(int i=0;i<al.size();i++){
                convertString(al.get(i),0);
                if(i+1!=al.size())System.out.println("");
            }
            System.out.println("</p>");
        }
    }
    Main() throws FileNotFoundException {
      //  File f = new File("d:\\data.txt");
        Scanner sc = new Scanner(System.in);/*new FileInputStream(f));*/
        ArrayList<String> al = new ArrayList<String>();

        while(sc.hasNextLine()){
            String buf  =sc.nextLine();
            if(buf.equals("")){
                solveBlock(al);
                al = new ArrayList<String>();
            }else{
                al.add(buf);
            }
        }
        if(!al.isEmpty()){
            solveBlock(al);
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        new Main();
    }
}
