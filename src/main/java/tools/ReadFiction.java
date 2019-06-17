package tools;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class ReadFiction {

    private static ReadFiction rf = new ReadFiction();

    static File f;

    static BufferedReader br;

    private ReadFiction(){}

    public static ReadFiction getInstance(String path){
        f = new File(path);
        setBr();
        return rf;
    }

    private HashMap<String,String> map = new LinkedHashMap<>();

    private static void setBr(){
        InputStream is;
        try {
            is = new FileInputStream(f);
            br = new BufferedReader(new InputStreamReader(is,"gbk"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> readFiction(){
        try {
            String line;
            String regex = "[第][\\S]*[章][\\s|\\W][\\S]*";
            String name = "";
            String content = "";
            while ((line = br.readLine()) != null) {
                if(line.trim().matches(regex)) {
                    if(content.length() != 0){
                        map.put(name,content);
                    }
                    name = line;
                    content = "";
                    continue;
                }
                content += line+"\r\n";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
