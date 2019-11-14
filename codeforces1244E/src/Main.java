import java.util.Arrays;
import java.util.Scanner;

public class Main {
    int n,data[];
    long k,prefixSum[];
    long getRange(int l,int r){
        if(l>r)return 0;
        return prefixSum[r] - prefixSum[l-1];
    }
    boolean check(int x){
        int L=1,R=1;
        while(L<=n&&L<=R){
            while(R+1<=n&&data[R+1]-data[L]<=x)R++;
            long tmp=(long)(L-1)*(long)data[L]-getRange(1,L-1);
            tmp += getRange(R+1,n);
            tmp -= (long)(n-R)*(long)(data[L]+x);
            if(tmp <= k)return true;
            else L++;
        }
        L=n;
        R=n;
        while(R>=1&&L<=R){
            while(L-1>=1&&data[R]-data[L-1]<=x)L--;
            long tmp=(long)(L-1)*(long)(data[R]-x)-getRange(1,L-1);
            tmp += getRange(R+1,n)-(long)(n-R)*(long)(data[R]);
            if(tmp <= k)return true;
            else R--;
        }
        return false;
    }
    Main(){
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt(); k=sc.nextLong();
        data=new int[n+1];
        prefixSum=new long[n+1];
        for(int i=1;i<=n;i++) data[i]=sc.nextInt();
        Arrays.sort(data,1,n+1);
        for(int i=1;i<=n;i++)prefixSum[i]=(prefixSum[i-1]+(long)data[i]);
        int l=1,r=1000000000,ans=0;
        while(l<=r){
      //      System.out.println(l+" "+r);
            int mid=(l+r)>>1;
            if(check(mid)){r=mid-1;ans=mid;}
            else{l=mid+1;}
        }
        for(int i=1;i<=n;i++){
            long tmp = ((long)i-1)*(long)data[i]-prefixSum[i-1];
            tmp += getRange(i+1,n)-(long)(n-i)*data[i];
            if(tmp <= k)ans=0;
        }
        System.out.println(ans);
    }
    public static void main(String[] args){
        new Main();
    }
}
