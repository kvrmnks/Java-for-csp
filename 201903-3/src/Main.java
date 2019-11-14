import java.util.Scanner;

public class Main {
    Scanner sc;
    int n,s,l,location[],len,lineSize;
    String[] data;
    void init(){
        sc = new Scanner(System.in);
        n=sc.nextInt();s=sc.nextInt();l=sc.nextInt();
        data = new String[n];
        for(int i=0;i<l;i++){
            int pos = sc.nextInt();
            data[pos] = sc.next();
            len=data[pos].length();
        }
        lineSize = len / (8*s);
        location = new int[lineSize * (n-1)];
        int cur = n-1,tmp = 0;
        for(int i=0;i<lineSize;i++){
            for(int j=cur+1;j<n;j++){
                location[tmp++] = j;
            }
            for(int j=0;j<cur;j++){
                location[tmp++] = j;
            }
            cur --;
            if(cur == -1)cur = n - 1;
        }
        int m;
        m = sc.nextInt();

        for(int i=0;i<m;i++){
            int k = sc.nextInt();
            if(k > tmp * s){System.out.println("-");continue;}
            int line = k / s; // 第几个条带
            int block = k % s; // 条带的第几个块
            int linePosX =  line / (n-1);//条带在哪行
            int linePosY = location[line]; //条带在哪个磁盘
            line /= (n-1);
            if(k > tmp * s || (l<n-1 && data[linePosY] == null)){
                System.out.println("-");
                continue;
            }else{
                if(data[linePosY] != null){
                    for(int j=0;j<8;j++){
                        int x = line * 8 * s+ 8 * block;
                        System.out.print(data[linePosY].charAt(x+j));
                    }
                    System.out.println("");
                }else{
                    for(int j=0;j<8;j++){
                        int x = line * 8 * s+ 8 * block;
                        int ans = 0;
                        for(int h=0;h<n;h++){
                            if(h==linePosY)continue;
                            char ccc = data[h].charAt(x+j);
                            int tmpp = (ccc>='0'&&ccc<='9')?ccc-'0':ccc-'A'+10;
                            ans ^= tmpp;
                        }
                        System.out.print((char)(ans>9?ans-10+'A':ans+'0'));
                    }
                    System.out.println("");
                }
            }
        }
    }
    Main(){
        init();
        System.exit(0);
    }
    public static void main(String[] args){
        new Main();
    }
}
