import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    Main(){
        try {
            ServerSocket ss = new ServerSocket(6789);
            Socket s = ss.accept();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            String buf = dis.readUTF();
            System.out.println(buf);
            File f = new File(buf);
            DataInputStream fdis = new DataInputStream(new FileInputStream(f));
            dos.writeLong(f.length());
            byte[] bin = new byte[4096];
            int len;
            while((len=fdis.read(bin))!=-1){
             //   System.out.print("233");
                dos.write(bin,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void main(String[] args){
        new Main();
    }
}
