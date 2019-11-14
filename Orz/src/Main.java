import java.util.Scanner;

public class Main{
    int T,n;
    long k;
    Scanner sc;
    int[] data;
    int[] pos;
    boolean[] used;
    int cnt,W;
    void solve(){
        n = sc.nextInt();
        k = sc.nextLong();
        data = new int[n+1];
        pos = new int[n+1];
        used  = new boolean[n+1];
        cnt = 0;
        W = 0;
        String str = sc.next();
        for(int i=1;i<=n;i++){
            data[i] = str.charAt(i-1)-'0';
            if(data[i] == 0){
                pos[++cnt] = i;
            }
            used[i] = false;
        }
        int L = 1;
        long cur = 0;
        for(int i=1;i<=n;i++){
            if(used[i]){W--;continue;}
            if(data[i] == 0){
                L++;
                System.out.print(0);
            }else{
                //System.out.println();
                if(L<=cnt && cur + pos[L]-i-W <= k){
                    //System.out.println("\nvalue is"+(pos[L]-i-W));
                    cur += pos[L]-i-W;
                    W++;
                    used[pos[L]]=true;
                    L++;
                    i--;
                    System.out.print(0);
                }else{
                    System.out.print(1);
                }
            }

        }
        System.out.println();
     //   System.out.println(cur+pos[L]-W);
    }
    Main(){
        sc = new Scanner(System.in);
        T = sc.nextInt();
        for(int i=1;i<=T;i++){
            solve();
        }
    }
    public static void main(String[] args){
        new Main();
    }
}