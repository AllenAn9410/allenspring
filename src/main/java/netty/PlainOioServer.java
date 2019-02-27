package netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class PlainOioServer {

    public static void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        int i = 0;
        try {
            for (; ; ) {
                final Socket clientSocket = socket.accept();
                i++;
                System.out.println(i);
                System.out.println("accepted connected from : " + clientSocket);

                new Thread(new Runnable() {
                    public void run() {
                        OutputStream out;
                        BufferedReader buf;
                        try {
                            out = clientSocket.getOutputStream();
                            out.write("Hi! you have benn connect me!".getBytes(Charset.forName("UTF-8")));
                            out.flush();
                            buf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                            System.out.println(buf.readLine());
//                            boolean flag = true;
//                            while(flag){
//                                String str = buf.readLine();
//                                if( "".equals(str) || str == null){
//                                    flag = false;
//                                } else {
//                                    out.write();
//                                    System.out.println(str);
//                                }
//                            }
                            clientSocket.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                            try {
                                clientSocket.close();
                            } catch (IOException e1) {
                            }
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) throws IOException {
        serve(8080);
    }

}
