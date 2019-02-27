package netty;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket client = null;
        try {
            client = new Socket("127.0.0.1", 8080);
            client.setSoTimeout(10000);
            PrintStream ps = new PrintStream(client.getOutputStream());
            ps.println("this is my request");
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println(buf.readLine());
            ps.close();
            buf.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
