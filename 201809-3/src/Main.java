import java.util.Scanner;

public class Main {
    public static int n, m;
    public static String[] html = new String[102];
    public static int[] rk = new int[102];
    public static String[] tag = new String[102];
    public static String[] id = new String[102];
    private static int[] Ans = new int[102];

    private static void match(String[] s) throws InterruptedException {
        int[] arr = new int[102];
        int top = 0;
        for(int i=1;i<=n;i++){
            if(s[s.length-1].charAt(0) == '#'){
                if(id[i]!=null && s[s.length-1].equals('#'+id[i])){
                    ;
                }else{
                    continue;
                }
            }else{
                if(s[s.length-1].equalsIgnoreCase(tag[i])){
                    ;
                }else{
                    continue;
                }
            }
            top = 0;
            arr[++top] = i;
            for(int j=i-1;j>=1;j--){
                if(rk[j] < rk[arr[top]]){
                    arr[++top] = j;
                }
            }
            int curMatch = 0;
            for(int j=top;j>1;j--){
                if(s[curMatch].charAt(0)=='#'){
                    String[] sss = s[curMatch].split("#");
                    if(id[arr[j]]!=null && sss[1].equals(id[arr[j]])){
                        curMatch++;
                    }
                }else{
                    if(s[curMatch].equalsIgnoreCase(tag[arr[j]])){
                        curMatch++;
                    }
                }
                if(curMatch == s.length-1){Ans[i]=1;break;}
                /*
                int tmp = curMatch;
                if(s[curMatch].equalsIgnoreCase(tag[arr[j]])){
                    if(curMatch + 1 < s.length && s[curMatch+1].charAt(0) == '#'){
                        if(id[arr[j]]!=null && s[curMatch+1].equals("#"+id[arr[j]])){
                            curMatch+=2;
                            if(curMatch == s.length){
                                Ans[arr[j]] = 1;
                                curMatch = tmp;
                            }
                        }
                    }else{
                        curMatch++;
                        if(curMatch == s.length){
                            Ans[arr[j]] = 1;
                            curMatch = tmp;
                        }
                    }
                }*/
                /*
                if(s[curMatch].charAt(0)=='#'){
                    //Thread.sleep(2000);
                    String[] sss = s[curMatch].split("#");
                    if(sss[1].equals(id[arr[j]])){
                        curMatch++;
                    }
                }else{
                    if(s[curMatch].equalsIgnoreCase(tag[arr[j]])){
                        curMatch++;
                    }
                }
                if(curMatch == s.length-1){
                    for(int k=j-1;k>=1;k--){
                        if(s[curMatch].charAt(0)=='#'){//Thread.sleep(2000);
                            String[] sss = s[curMatch].split("#");
                            if(sss[1].equals(id[arr[k]])){
                                Ans[arr[k]] = 1;//curMatch++;
                            }
                        }else{
                            if(s[curMatch].equalsIgnoreCase(tag[arr[k]])){
                                Ans[arr[k]] = 1;
                            }
                        }
                    }
                    break;
                }



                 */
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= n; i++) {
            html[i] = sc.nextLine();
            int pos = 0;
            for (int j = 0; j < html[i].length(); j++) {
                if (html[i].charAt(j) != '.') {
                    pos = j;
                    break;
                }
                rk[i]++;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = pos; j < html[i].length(); j++) {
                if (html[i].charAt(j) == ' ') {
                    pos = j;
                    break;
                }
                sb.append(html[i].charAt(j));
            }
            tag[i] = sb.toString();
            tag[i] = tag[i].toLowerCase();
            boolean flag = false;
            for (int j = pos; j < html[i].length(); j++) {
                if (html[i].charAt(j) == '#') {
                    pos = j + 1;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                sb = new StringBuilder();
                for (int j = pos; j < html[i].length(); j++) {
                    char t = html[i].charAt(j);
                    if ((t >= 'a' && t <= 'z') || (t >= 'A' && t <= 'Z'))
                        ;
                    else
                        break;
                    sb.append(t);
                }
                id[i] = sb.toString();
            }
           // System.out.println(html[i] + rk[i] + tag[i] + id[i]);
        }
        for (int i = 1; i <= m; i++) {
            String s = sc.nextLine();
        //    System.out.println(s);
            String[] buf = s.split(" ");
            if(buf.length == 1){
                if(s.charAt(0)=='#'){
                    buf = s.split("#");
                    s = buf[1];
                    int tot = 0;
                    for(int j=1;j<=n;j++){
                        if(id[j]==null)continue;
                        if(s.equals(id[j])){
                            tot++;
                        }
                    }
                    System.out.print(tot);
                    for(int j=1;j<=n;j++){
                        if(id[j]==null)continue;
                        if(s.equals(id[j])){
                            System.out.print(" "+j);
                        }
                    }
                    System.out.println("");
                }else{
                    int tot = 0;
                    s = s.toLowerCase();
                    for(int j=1;j<=n;j++){
                        if(tag[j].equals(s))
                            tot++;
                    }
                    System.out.print(tot);
                    for(int j=1;j<=n;j++){
                        if(tag[j].equalsIgnoreCase(s)){
                            System.out.print(" "+j);
                        }
                    }
                    System.out.println("");
                }
            }else{
                for(int j=1;j<=n;j++)Ans[j] = 0;
                int ans=0;
                match(buf);
                for(int j=1;j<=n;j++){
                    if(Ans[j] == 1)
                        ans++;
                }
                System.out.print(ans);
                for(int j=1;j<=n;j++){
                    if(Ans[j] == 1){
                        System.out.print(" "+j);
                    }
                }
                System.out.println("");

            }
        }
    }
}
/*
* #include <iostream>
#include <stdio.h>
#include <vector>
#include <string>
using namespace std;

struct PROPERTY {
    string label;
    string id;
};

int main() {//freopen("2333.txt","r",stdin);
    int n, m;
    cin >> n >> m;
    getchar();
    vector<vector<PROPERTY> > lines;
    vector<PROPERTY> cur;
    for (int k = 0; k < n; ++k) {
        string s;
        getline(cin, s);
        int i = 0, len = s.length();
        int j = i;
        while (j < len && s[j] == '.') ++j;
        int level = j - i >> 1;
        while (cur.size() > level) cur.pop_back();
        PROPERTY pro;
        i = j;
        while (j < len && s[j] != ' ') ++j;
        pro.label = s.substr(i, j - i);
        for (int k = 0; k < pro.label.size(); ++k) pro.label[k] = tolower(pro.label[k]);
        if (j < len) pro.id = s.substr(j + 1);
        cur.push_back(pro);
        lines.push_back(cur);
    }
    while (m--) {
        string s;
        getline(cin, s);
        vector<string> selectors;
        for (int i = 0; i < s.size();) {
            int j = i;
            while (++j < s.size() && s[j] != ' ');
            string temp = s.substr(i, j - i);
            if (temp[0] != '#') for (int k = 0; k < temp.size(); ++k) temp[k] = tolower(temp[k]);
            selectors.push_back(temp);
            i = j + 1;
        }
        vector<int> res;
        for (int k = 0; k < n; ++k) {
            int i = 0, j = 0;
            while (i+1 < selectors.size()) {
                while (j < lines[k].size()) {
                    if (lines[k][j].label == selectors[i] || lines[k][j].id == selectors[i]) break;
                    ++j;
                }
                if (j == lines[k].size()) break;
                ++i, ++j;
            }
            if (i + 1 == selectors.size()) {
                if (j == lines[k].size()) continue;
                if (lines[k].back().label == selectors[i] || lines[k].back().id == selectors[i]) {
                    res.push_back(k + 1);
                }
            }
        }
        cout << res.size();
        for (int i = 0; i < res.size(); ++i) cout << " " << res[i];
        cout << endl;
    }
    return 0;
}*/