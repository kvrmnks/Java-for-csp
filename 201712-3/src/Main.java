import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private int n;
    private Order[] o = new Order[22];
    private Scanner sc = new Scanner(System.in);
    private Time begin,end;
    private int transform(String s){
        boolean flag = true;
        int ret = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c>='0'&&c<='9'){ret = ret*10 + c - '0';}
            else flag = false;
        }
        if(flag)return ret;
        s=s.toLowerCase();
        switch(s){
            case "sun":
                return 0;
            case "jan":
            case "mon":
                return 1;
            case "feb":
            case "tue":
                return 2;
            case "mar":
            case "wed":
                return 3;
            case "apr":
            case "thu":
                return 4;
            case "may":
            case "fri":
                return 5;
            case "jun":
            case "sat":
                return 6;
            case "jul":
                return 7;
            case "aug":
                return 8;
            case "sep":
                return 9;
            case "oct":
                return 10;
            case "nov":
                return 11;
            case "dec":
                return 12;
        }
        return 233;
    }
    private HashSet<Integer> construct(String s,int l,int r){
        HashSet<Integer> ret = new HashSet<Integer>();
        if(s.charAt(0)=='*'){for(int i=l;i<=r;i++)ret.add(i);}
        else{
            String[] buf = s.split(",");
            for(String sub:buf){
                boolean flag = false;
                for(int i=0;i<sub.length();i++)flag|=(sub.charAt(i)=='-');
                if(flag){
                    int tmp = 0 , tmp2 = 0;
                    String[] tmps = sub.split("-");
                    tmp = transform(tmps[0]);
                    tmp2 = transform(tmps[1]);
                    for(int i=tmp;i<=tmp2;i++)
                        ret.add(i);
                }else{
                    ret.add(transform(sub));
                }
            }
        }
        return ret;
    }
    private void read(){
        n=sc.nextInt();
        String s = sc.next();
        begin = Time.get(s);
        s = sc.next();
        end = Time.get(s);
        for(int i=1;i<=n;i++){
            o[i]=new Order();
            o[i].setMinutes(construct(sc.next(),0,59));
            o[i].setHours(construct(sc.next(),0,23));
            o[i].setDays(construct(sc.next(),1,31));
            o[i].setMonths(construct(sc.next(),1,12));
            o[i].setWeeks(construct(sc.next(),0,6));
            o[i].setCommand(sc.next());
       //     System.out.println(o[i].weeks);
        }
    }
    private Main(){
        read();
        while(!begin.equals(end)){
            for(int i=1;i<=n;i++){
                if(o[i].match(begin)){
                    System.out.println(begin+" "+o[i].command);
                 //   break;
                }
            }
            begin.forward();
        }
    }
    public static void main(String[] args){
        new Main();
    }
}
class Order{
    public HashSet<Integer> minutes,hours,days,months,weeks;
    public String command;
    public void setDays(HashSet<Integer> days) {
        this.days = days;
    }
    public void setMonths(HashSet<Integer> months) {
        this.months = months;
    }
    public void setWeeks(HashSet<Integer> weeks) {
        this.weeks = weeks;
    }
    public void setCommand(String command) {
        this.command = command;
    }
    public void setHours(HashSet<Integer> hours) {
        this.hours = hours;
    }
    public void setMinutes(HashSet<Integer> al){minutes=al;}
    public boolean match(Time t){
        boolean flag = false;
        /*for(int tmp:minutes)*/{if(minutes.contains(t.getMinutes()))flag=true;}
        if(!flag)return false;
        flag=false;
        /*for(int tmp:hours)*/if(hours.contains(t.getHours()))flag=true;
        if(!flag)return false;
        flag=false;
        /*for(int tmp:days)*/if(days.contains(t.getDays()))flag=true;
        if(!flag)return false;
        flag=false;
        /*for(int tmp:months)*/if(months.contains(t.getMonths()))flag=true;
        if(!flag)return false;
        flag=false;
        /*for(int tmp:weeks)*/if(weeks.contains(t.getWeeks()))flag=true;
        if(!flag)return false;
        return true;
    }

}
class Time{
    private int minutes,hours,days,months,weeks,years;
    private final int INITIALYEAR = 1970;
    private final int INITIALWEEK = 4;
    private final int YEAR = 365;
    private final int LEAPYEAR = 366;
    public static final int[] month = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    private Time(int year,int month,int day,int hour,int minute){
        this.years=year;
        this.months=month;
        this.hours=hour;
        this.minutes=minute;
        this.days=day;
        this.weeks=INITIALWEEK;
        for(int i=INITIALYEAR;i<years;i++){
            weeks = (weeks+(isLeap(i)?LEAPYEAR:YEAR))%7;
        }
        for(int i=1;i<month;i++){
            if(i==2){
                weeks=(weeks+(isLeap(years)?this.month[2]+1:this.month[2]))%7;
            }else{
                weeks=(weeks+this.month[i])%7;
            }
        }
        weeks=(weeks+day+6)%7;
        //System.out.println(this.toString()+" "+weeks);
    }
    public static Time get(String s){
        int year = Integer.parseInt(s.substring(0,4));
        int month = Integer.parseInt(s.substring(4,6));
        int day = Integer.parseInt(s.substring(6,8));
        int hour = Integer.parseInt(s.substring(8,10));
        int minute = Integer.parseInt(s.substring(10,12));
        return new Time(year,month,day,hour,minute);
    }
    private boolean isLeap(int x){
        if(((x%100)==0) && ((x%400)==0))return true;
        if(x%100!=0 && x%4==0)return true;
        return false;
    }
    public boolean equals(Time b){
        return (minutes==b.minutes)&&(hours==b.hours)&&(days==b.days)&&(months==b.months)
                &&(weeks == b.weeks)&&(years==b.years);
    }
    public void forward(){
        minutes++;
        if(minutes == 60){
            minutes = 0;
            hours++;
            checkHours();
        }
    }
    private void checkHours(){
        if(hours == 24){
            hours = 0;
            days++;
            weeks+=1;
            weeks%=7;
            checkDays();
        }
    }
    private void checkDays(){
        int limit = month[months];
        if(months==2&&isLeap(years)){
            limit++;
        }
        if(days>limit){
            days = 1;
            months++;
            checkMonths();
        }
    }
    private void checkMonths(){
        if(months==13){
            years++;
            months=1;
        }
    }
    public int getMinutes(){return minutes;}
    public int getHours(){return hours;}
    public int getDays(){return days;}
    public int getMonths(){return months;}
    public int getWeeks(){return weeks;}
    public int getYears(){return years;}
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append(years+"");
        if(months<10){sb.append('0');}
        sb.append(months+"");
        if(days<10)sb.append('0');
        sb.append(days+"");
        if(hours<10)sb.append('0');
        sb.append(hours+"");
        if(minutes<10)sb.append('0');
        sb.append(minutes+"");
        return sb.toString()/*+" "+weeks*/;
    }
}
