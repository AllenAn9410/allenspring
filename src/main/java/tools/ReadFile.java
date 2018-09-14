package tools;

import java.io.*;

public class ReadFile {
    private static File file;
    public ReadFile(String path){
        this.file = new File(path);
    }
    public String read(){
        StringBuffer buffer = new StringBuffer();
        InputStream is;
        try {
            is = new FileInputStream(file);
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while (( line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }
            reader.close();
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

}
