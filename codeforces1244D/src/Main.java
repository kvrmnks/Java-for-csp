import java.util.*;
public class Main {
    int n,cost[][],h[],nx[],to[],tot,rk[],data[],buf[],color[];
    void add_edge(int x,int y){
        to[++tot]=y;
        nx[tot]=h[x];
        h[x]=tot;
        to[++tot]=x;
        nx[tot]=h[y];
        h[y]=tot;
        rk[x]++;
        rk[y]++;
    }
    void dfs(int x,int y,int depth){
        data[depth]=x;
        for(int i=h[x];i!=0;i=nx[i]){
            if(to[i]==y)continue;
            dfs(to[i],x,depth+1);
        }
    }
    Main(){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        cost = new int[n+1][4];
        for(int i=1;i<=n;i++)cost[i]=new int[4];
        data = new int[n+1];
        buf = new int[n+1];
        h = new int[n+1];
        nx = new int[(n+1)<<1];
        to = new int[(n+1)<<1];
        rk = new int[n+1];
        color = new int[n+1];
        for(int i=1;i<=3;i++){
            for(int j=1;j<=n;j++){
                cost[j][i] = sc.nextInt();
            }
        }
        for(int i=1;i<n;i++){
            int a,b;
            a = sc.nextInt();
            b = sc.nextInt();
            add_edge(a,b);
        }
        for(int i=1;i<=n;i++){
            if(rk[i]>=3){
                System.out.println(-1);
                return;
            }
        }
        for(int i=1;i<=n;i++)
            if(rk[i]==1){
                dfs(i,0,1);
                break;
            }
        long totalCost = 0x7fffffffffffffffL;
        for(int i=1;i<=3;i++){
            for(int j=1;j<=3;j++){
                if(i==j)
                    continue;
                buf[1]=i;
                buf[2]=j;
                for(int k=3;k<=n;k++){
                    buf[k] = 6 - buf[k-1] - buf[k-2];
                }
                long tmp = 0;
                for(int k=1;k<=n;k++) tmp += cost[data[k]][buf[k]];
                totalCost = totalCost > tmp ? tmp : totalCost;
            }
        }
        System.out.println(totalCost);
        for(int i=1;i<=3;i++){
            for(int j=1;j<=3;j++){
                if(i==j)
                    continue;
                buf[1]=i;
                buf[2]=j;
                for(int k=3;k<=n;k++){
                    buf[k] = 6 - buf[k-1] - buf[k-2];
                }
                long tmp = 0;
                for(int k=1;k<=n;k++) tmp += cost[data[k]][buf[k]];
                if(tmp == totalCost){
                    for(int k=1;k<=n;k++)
                        color[data[k]] = buf[k];
                    for(int k=1;k<=n;k++){
                        System.out.print(color[k]+" ");
                    }
                    return;
                }
            }
        }

    }
    public static void main(String[] args){
        new Main();
    }
}
