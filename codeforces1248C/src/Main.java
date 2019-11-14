import java.util.*;
public class Main{
    int n,m,data[];
    final int MOD = 1000000007;
    Main(){
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        m=sc.nextInt();
        if(n<m){
            int t=n;
            n=m;
            m=t;
        }
        data = new int[n+2];
        data[1] = 1;
        data[2] = 2;
        for(int i=3;i<=n;i++){
            data[i] = (data[i-1]+data[i-2])%MOD;
        //    System.out.println(data[i]);
        }
        System.out.println((2*((data[n]+data[m]-1)%MOD))%MOD);
    }
    public static void main(String[] args){
        new Main();
    }
}