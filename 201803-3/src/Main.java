import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    Scanner sc = new Scanner(System.in);
    int n,m;
    final String forInt = "<int>",forStr = "<str>",forPath="<path>";
    String[][] s = new String[102][];
    String[] name = new String[102];
    boolean isNumber(String str){
        if(str == null)return false;
        boolean flag = true;
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            if(c>='0'&&c<='9');
            else flag=false;
        }
        return flag;
    }
    boolean isString(String str){
        if(str == null)return false;
        boolean flag = true;
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            flag = (flag && (c!='/'));
        }
        return flag;
    }
    String[] divide(String str){
        ArrayList<String> al = new ArrayList<String>();
        StringBuilder sb;//= new StringBuilder();
        for(int i=0;i<str.length();i++){
            sb = new StringBuilder();
            char c = str.charAt(i);
            switch(c){
                case '/':
                    sb.append(c);
                    break;
                case '<':
                    sb.append(c);
                    int j=i+1;
                    for(;j<str.length();j++){
                        char tc = str.charAt(j);
                        sb.append(tc);
                        if(tc == '>')
                            break;
                    }
                    i=j;
                    break;
                default:
                    j=i;
                    for(;j<str.length();j++){
                        char tc = str.charAt(j);
                        if(tc=='/'){j--;break;}
                        sb.append(tc);
                    }
                    i=j;
            }
            al.add(sb.toString());
        }
        String[] ret = new String[al.size()];
        al.toArray(ret);
        return ret;
    }
    boolean check(String[] item,int x){
        boolean flag = true;
        int top = 0;
        for(int i=0;i<s[x].length;i++){
            String str = s[x][i];
            switch(str){
                case forInt:
                    flag = (flag && i < item.length && isNumber(item[i]));
                    top = i;
                    break;
                case forStr:
                    flag = (flag && i < item.length && isString(item[i]));
                    top = i;
                    break;
                case forPath:
                    return flag;
                default:
                    flag = (flag && i < item.length &&str.equals(item[i]));
                    top = i;
            }
            if(!flag)break;
        }
        return flag && (top == item.length-1);
    }
    void display(String[] item,int x){
        System.out.print(name[x]);
        for(int i=0;i<s[x].length;i++){
            String str = s[x][i];
            switch(str){
                case forInt:
                    int xx = Integer.parseInt(item[i]);
                    if(xx!=0)System.out.print(" "+xx);
                    break;
                case forStr:
                    System.out.print(" "+item[i]);
                    break;
                case forPath:
                    System.out.print(" ");
                    for(int j=i;j<item.length;j++){
                        System.out.print(item[j]);
                    }
                    return;
            }
        }
    }
    void search(String str){
        String[] buf = divide(str);
        for(int i=1;i<=n;i++)
            if(check(buf,i)){
                display(buf,i);
                System.out.println("");
                return;
            }
        System.out.println("404");
    }
    Main(){
        n=sc.nextInt();m=sc.nextInt();
        for(int i=1;i<=n;i++){
            String str = sc.next();
            name[i]=sc.next();
            s[i]=divide(str);
        }
        for(int i=1;i<=m;i++){
            String str = sc.next();
            search(str);
        }
    }
    public static void main(String[] args){new Main();}
}
