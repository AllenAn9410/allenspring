package pipeThread;

import java.io.PipedOutputStream;
import java.io.PipedWriter;

public class ThreadWrite implements Runnable {
    private WriteData writeData;
    private PipedOutputStream out;
    private PipedWriter writer;

    ThreadWrite(PipedOutputStream out,WriteData writeData){
        this.writeData = writeData;
        this.out = out;
    }

    ThreadWrite(PipedWriter writer, WriteData writeData){
        this.writeData = writeData;
        this.writer = writer;
    }
    public void run() {
        // writeData.writeMethod(out);
        writeData.writeMethod(writer);
    }
}
