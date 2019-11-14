package com.kvrmnks;

import java.io.*;
import java.net.Socket;

public class MainWork {
    private Socket s;
    private String ip;
    private int port;
    private static final int DICTIONARY = 0;
    private static final int FILE = 1;
    private DataInputStream in;
    private DataOutputStream out;
    public MainWork(){}
    public boolean link(String ipaddress,String port){
        try {
            this.ip=ipaddress;
            this.port = Integer.parseInt(port);
            this.s=new Socket(this.ip,this.port);
            in=new DataInputStream(s.getInputStream());
            out=new DataOutputStream(s.getOutputStream());
        }catch(Exception e){e.printStackTrace();return false;}
        return true;
    }
    public String readMessage(){
        return null;
    }
    private void sendMessage(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendLong(Long msg){
        try {
            out.writeLong(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendByte(byte[] b,int off,int len){
        try {
            out.write(b,off,len);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private int readByte(byte[] b){
        try {
            return in.read(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
    private long readLong(){
        try {
            return in.readLong();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public void messagePath(String msg){
        sendMessage(msg);
    }

    public String messageCurrentPath(String msg){
        sendMessage(msg);
        try {
            String str = in.readUTF();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] messageShowFileDictionary(String msg){
        sendMessage(msg);
        try {
            int len = in.readInt();
            //StringBuilder sb = new StringBuilder();
            String[] sb = new String[len];
            for(int i=1;i<=len;i++){
                int type = in.readInt();
                //文件类型等待处理
                sb[i] = (in.readUTF());
            }
            return sb;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void messageUploadFile(String msg){
        try {
            String[] args = msg.split("\\$");
            sendMessage(msg);
            File f = new File(args[1]);
            DataInputStream dis = new DataInputStream(new FileInputStream(f));
            sendLong(f.length());
            byte[] buf = new byte[4096*16];
            long len=0,whole=f.length();
            while(len<whole){
                int t = dis.read(buf);
                if(t==-1)return;
                len += t;
                sendByte(buf,0,t);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void messageDownLoadFile(String s) {
        try {
            String[] args = s.split("\\$");
            sendMessage(s);
            System.out.println(s);
           // sendMessage(args[1]);
            long len = readLong();
            if(len == -1)
                return;
            File f = new File(args[2]);
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(f));
            if(f.exists()){
                return;
            }
            f.createNewFile();
            long tmp = 0;
            byte[] buf = new byte[4096*16];
            while(tmp < len){
                int t = readByte(buf);
                if(t == -1){
                    throw new IOException();
                }
                dos.write(buf,0,t);
                tmp += t;
            }
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
