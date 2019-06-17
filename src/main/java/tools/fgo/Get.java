package tools.fgo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Get {
    private static void getConnection(String path){
        try {
            URL url = new URL(path);
            URLConnection conn = url.openConnection();
            InputStream inputStream = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
            String line = "";
            StringBuilder builder = new StringBuilder();
            while((line = br.readLine()) != null){
                builder.append(line).append("\n");
            }
            System.out.println(builder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        String path = "https://fgo.umowang.com/servant/1";
        getConnection(path);
    }


}
