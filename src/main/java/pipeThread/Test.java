package pipeThread;

import java.io.*;

public class Test {
    public static void main(String[] args){
        try {
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();

//            PipedInputStream input = new PipedInputStream();
//            PipedOutputStream out = new PipedOutputStream();
            PipedWriter out = new PipedWriter();
            PipedReader input = new PipedReader();


            out.connect(input);

            new Thread(new ThreadRead(input,readData)).start();

            Thread.sleep(2000);

            new Thread(new ThreadWrite(out,writeData)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
