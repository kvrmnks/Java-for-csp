import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int[] father = new int[500030];
    private static Edge[] e = new Edge[100050];
    private static int getfather(int x){
        return (father[x] == x)?x:(father[x] = getfather(father[x]));
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n,m,root;
        n = sc.nextInt();
        m = sc.nextInt();
        root = sc.nextInt();
        for(int i=1;i<=m;i++){
            e[i] = new Edge();
            e[i].from = sc.nextInt();
            e[i].to = sc.nextInt();
            e[i].cost = sc.nextInt();
        }
        for(int i=1;i<=n;i++)father[i] = i;
        int tot = 0;
        Arrays.sort(e,1,m+1);
        for(int i=1;i<=m;i++){
            int fx = getfather(e[i].from);
            int fy = getfather(e[i].to);
            if(fx == fy)continue;
            father[fx] = fy;
            tot ++ ;
            if(tot == n-1){
                System.out.println(e[i].cost);
            }
        }
    }
}
class Edge implements Comparable<Edge>{
    public int from,to,cost;

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}
