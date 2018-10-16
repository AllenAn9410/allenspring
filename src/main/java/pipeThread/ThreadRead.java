package pipeThread;

import java.io.PipedInputStream;
import java.io.PipedReader;

public class ThreadRead implements Runnable{
    private PipedInputStream input;
    private ReadData readData;
    private PipedReader reader;

    public ThreadRead(PipedInputStream input,ReadData readData){
        this.input = input;
        this.readData = readData;
    }

    public ThreadRead(PipedReader reader,ReadData readData){
        this.reader = reader;
        this.readData = readData;
    }



    public void run() {
        readData.readMethod(reader);
    }
}
