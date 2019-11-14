import java.util.Scanner;

public class Main {
    private static int[] data = new int[400];
    private static int[] ans = new int[400];
    public static boolean check(int pos,int n){
        if(pos == n+1){

        }
        boolean flag = false;
        if(pos == 2){
            for(int i=1;i<=400;i++){
                if((i+ans[1])/2 == data[1]){

                }
            }
        }else{

        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n;
        n=sc.nextInt();
        for(int i=1;i<=n;i++){
            data[i] = sc.nextInt();
        }
        for(int i=1;i<=200;i++){
            ans[1] = i;
            boolean flag = check(2,n);
        }
    }
}
