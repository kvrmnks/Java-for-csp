import java.util.Scanner;

public class Main{
    Scanner sc;
    int n,s,l,lineSize,len,cur;
    Disk[] disks;
    Block[] ans;
    void initDisk(){
        sc = new Scanner(System.in);
        n=sc.nextInt();
        s=sc.nextInt();
        l=sc.nextInt();
   //     if(n == 2)System.exit(0);
        disks = new Disk[n];
        for(int i=0;i<n;i++)disks[i]=new Disk();
        len=0;
        for(int i=0;i<l;i++){
            int tmp = sc.nextInt();
            disks[tmp].data = sc.next();
            if(len == 0)len = disks[tmp].data.length();
           // System.out.println(len);
        }
        for(int i=0;i<n;i++){
            disks[i].lists = new Line[len/(8*s)];
            for(int j=0;j<len/(8*s);j++)
                disks[i].lists[j] = new Line();
        }
        lineSize = len/(8*s);
        for(int i=0;i<n;i++){
            for(int j=0;j<lineSize;j++){
                disks[i].lists[j].blocks = new Block[s];
                for(int k=0;k<s;k++){
                    disks[i].lists[j].blocks[k] = new Block();
                    disks[i].lists[j].blocks[k].data = new char[8];
                    disks[i].lists[j].blocks[k].isSolved = false;
                }
            }
        }
        for(int i=0;i<n;i++){
            if(disks[i].data == null)continue;
            for(int j=0;j<lineSize;j++){
                for(int k=0;k<s;k++){
                    disks[i].lists[j].blocks[k].isSolved = true;
                    for(int h=0;h<8;h++){
                        disks[i].lists[j].blocks[k].data[h] = disks[i].data.charAt(s*8*j+k*8+h);
                    }
                }
            }
        }
    }
    void buildDisk(){
        cur = n-1;
        for(int i=0;i<lineSize;i++){
            disks[cur].lists[i].isP = true;
            cur --;
            if(cur == -1){
                cur = n-1;
            }
        }
        cur = 0;
        for(int i=0;i<lineSize;i++){
            /*
            if((i%2)==0){
                for(int j=0;j<n;j++){
                    if(disks[j].lists[i].isP)continue;
                    disks[j].lists[i].id = cur++;
                }
            }else*/{
                int pos = n-1;
                while(disks[pos].lists[i].isP == false) pos--;
                for(int j=pos+1;j<n;j++)disks[j].lists[i].id = cur++;
                for(int j=0;j<pos;j++)disks[j].lists[i].id = cur++;
                /*
                for(int j=n-1;j>=0;j--){
                    if(disks[j].lists[i].isP)continue;
                    disks[j].lists[i].id = cur++;
                }
                 */
            }
        }
        cur *= s;
        ans = new Block[cur];
        for(int i=0;i<n;i++){
            for(int j=0;j<lineSize;j++){
                if(disks[i].lists[j].isP)continue;
                for(int k=0;k<s;k++){
                    disks[i].lists[j].blocks[k].id = disks[i].lists[j].id*s + k;
                    ans[disks[i].lists[j].blocks[k].id] = disks[i].lists[j].blocks[k];
                }
            }
        }

    }
    char[] XOR(char[] a,char[] b){
        char[] ret = new char[a.length];
        for(int i=0;i<a.length;i++){
            int t = 0;
            if(a[i]>='0'&&a[i]<='9'){t = a[i] - '0';}
            else{t = a[i] - 'A' + 10;}
            int tt = 0;
            if(b[i] >= '0' && b[i] <= '9'){tt = b[i] - '0';}
            else{tt = b[i] - 'A' + 10;}
            t ^= tt;
            if(t < 10){ret[i] = (char)('0' + t);}
            else{ret[i] = (char)(t - 10 + 'A');}
        }
        return ret;
    }
    void repair(){
        if(l<n-1)return;

        for(int i=0;i<n;i++){
            if(disks[i].data!=null)continue;
            for(int j=0;j<lineSize;j++){
                for(int k=0;k<s;k++){
                    char[] buf = new char[8];
                    for(int h=0;h<buf.length;h++)buf[h]='0';
                    for(int h=0;h<n;h++){
                        if(h == i)continue;
                        buf = XOR(buf,disks[h].lists[j].blocks[k].data);
                    }
                    for(int h=0;h<buf.length;h++)disks[i].lists[j].blocks[k].data[h]=buf[h];
                    disks[i].lists[j].blocks[k].isSolved = true;
                }
            }
        }
    }
    Main(){
        initDisk();
        buildDisk();
        repair();
/*
        for(int i=0;i<n;i++){
            for(int j=0;j<lineSize;j++){
                for(int k=0;k<s;k++){
                    System.out.println("Disk "+i+" Line "+j+" Block "+k+"\nid is "+disks[i].lists[j].blocks[k].id
                    +" block is "+disks[i].lists[j].isP);
                    disks[i].lists[j].blocks[k].display();
                }
            }
        }
*/

        int m = sc.nextInt();
/*
        for(int i=0;i<cur;i++){
            System.out.println("id is "+i);
            ans[i].display();
        }

*/
        for(int i=0;i<m;i++){
            int tmp = sc.nextInt();
            if(tmp >= cur || ans[tmp].isSolved == false){
                System.out.println("-");
            }else{
                ans[tmp].display();
            }
        }
    }
    public static void main(String[] args){
        new Main();
    }
    class Block{
        char[] data;
        int id;
        boolean isSolved;
        void display(){
            for(char c : data){
                System.out.print(c);
            }
            System.out.println("");
        }
    }
    class Line{
        Block[] blocks;
        int id;
        boolean isP;
    }
    class Disk{
        Line[] lists;
        String data;
    }
}