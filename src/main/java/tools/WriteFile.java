package tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    private File writename = null;
    BufferedWriter out = null;

    public WriteFile(String path){
        try {
            writename = new File(path);
            writename.createNewFile();
            out = new BufferedWriter(new FileWriter(writename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String context){
        try {
            out.write(context);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void close(){
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
