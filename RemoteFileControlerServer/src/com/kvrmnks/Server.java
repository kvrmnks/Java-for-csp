package com.kvrmnks;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket ss;
    private Socket s;
    private DataInputStream dis;
    private DataOutputStream dos;
    private File curFile;
    private String curPath;
    private int port;
    private static final int DICTIONARY = 0;
    private static final int FILE = 1;
    public Server(){}
    public Server(int p){
      //  Scanner sc = new Scanner(System.in);
        port = p;
        try {
            ss = new ServerSocket(port);
            s = ss.accept();
            System.out.println("linked in "+s);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
            while(s.isInputShutdown()==false){

                String str = dis.readUTF();
                if(str==null)continue;
                String[] buf = str.split("\\$");
                switch(buf[0]){
                    case "Link":
                        break;
                    case "Path":
                        curFile = new File(buf[1]);
                        curPath = buf[1];
                        System.out.println("now path is "+curPath);
                        break;
                    case "CurrentPath":
                        dos.writeUTF(curPath);
                        break;
                    case "ShowFileDictionary":
                        File[] f = curFile.listFiles();
                        dos.writeInt(f.length);
                        for(File ff : f){
                            if(ff.isDirectory()){dos.writeInt(DICTIONARY);}
                            else{dos.writeInt(FILE);}
                            dos.writeUTF(ff.getName());
                        }
                        break;
                    case "UploadFile":
                        String fileName = curPath + buf[1];
                        File cfile = new File(fileName);
                        DataOutputStream fdos = new DataOutputStream(new FileOutputStream(cfile));
                        long len = dis.readLong();
                        byte[] bin = new byte[4096*16];
                        long curLen = 0;
                        while(curLen<len){
                            int tmp = dis.read(bin);
                            curLen += tmp;
                            fdos.write(bin,0,tmp);
                        }
                        fdos.close();
                        System.out.println("UploadFile finished");
                        break;
                    case "UploadFileDictionary":
                        // 咕咕咕
                        break;
                    case "DownLoadFile":
                        String fileName2 = curPath + buf[1];
                        File cfile2 = new File(fileName2);
                        DataInputStream fdis = new DataInputStream(new FileInputStream(cfile2));
                        long len2 = cfile2.length();
                        dos.writeLong(len2);
                        byte[] bin2 = new byte[4096*16];
                        long curLen2 = 0;
                        while(curLen2 < len2){
                            int tmp = fdis.read(bin2);
                            curLen2 += tmp;
                            dos.write(bin2,0,tmp);
                        }
                        fdis.close();
                        break;
                    case "DownLoadFileDictionary":
                        //咕咕咕
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ss.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int port = sc.nextInt();
        while(true) {
            new Server(port);
        }
    }
}
