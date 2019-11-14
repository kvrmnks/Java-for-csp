import java.io.*;
import java.net.Socket;

public class Main {
    Main(){
        try {
            Socket s = new Socket("localhost",6789);
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF("d:\\s2429顿凯&王昊临&于逸潇.mov");
            dos.flush();
            DataInputStream dis = new DataInputStream(s.getInputStream());
            File f = new File("d:\\23333.mov");
            f.createNewFile();
            DataOutputStream fdos = new DataOutputStream(new FileOutputStream(f));
            byte[] bin = new byte[4096];
            long wholelen = dis.readLong();
            int len=0;
            while(len<wholelen){
                int tmp = dis.read(bin);
                len += tmp;
                fdos.write(bin,0,tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        new Main();
    }
}
