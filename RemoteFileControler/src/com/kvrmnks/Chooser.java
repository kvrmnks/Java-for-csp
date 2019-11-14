package com.kvrmnks;

import java.util.Scanner;

public class Chooser {
    private MainWork mainwork;
    public Chooser(){
        mainwork = new MainWork();
        /*
        Scanner sc = new Scanner(System.in);
        while(true){
            if(sc.hasNext()){
                String s = sc.nextLine();
                sendMessage(s);
            }
        }*/

    }
    private void showMessage(String s){
        System.out.println(s);
    }
    private void sendMessage(String s){
        if(s==null)return;
        String[] str = s.split("\\$");
        switch(str[0]){
            case "Link":
                boolean flag=mainwork.link(str[1],str[2]);
                if(flag){
                    showMessage("Link succeed");
                }else{
                    showMessage("Link failed");
                }
                break;
            case "Path":
                mainwork.messagePath(s);
                showMessage(mainwork.readMessage());
                break;
            case "CurrentPath":
                showMessage(mainwork.messageCurrentPath(s));
                //showMessage(mainwork.readMessage());
                break;
            case "ShowFileDictionary":
                showMessage(mainwork.messageShowFileDictionary(s));
                //showMessage(mainwork.readMessage());
                break;
            case "UploadFile":
                mainwork.messageUploadFile(s);
                break;
            case "UploadFileDictionary":
                // 没写
                break;
            case "DownLoadFile":
                mainwork.messageDownLoadFile(s);
                break;
            case "DownLoadFileDictionary":
                //没写
                break;
        }
    }
   // public static void main(String[] args){
    //    new Chooser();
   // }
}
