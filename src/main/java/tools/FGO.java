package tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FGO {
    static String[] head = {"name","lv1->lv2","lv2->lv3","lv3->lv4","lv4->lv5","lv5->lv6","lv6->lv7","lv7->lv8","lv8->lv9","lv9->lv10"};
    static ExcelWrite ew = new ExcelWrite("./fgo_skill.xls",head);
    public static String[] getConn(String path) throws Exception {
        String[] res = new String[2];
        try{
            StringBuffer buffer;
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream is=conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            buffer=new StringBuffer();
            String line=null;
            boolean isStart = false;
            boolean isRealStart = false;
            boolean isEnd = false;
            String name = "";
            while((line=br.readLine())!=null){
                if(line.contains("<title>")){
                    name = line.trim().substring(line.indexOf(">")+1,line.indexOf("-"));
                }
                if(line.contains("技能升级") || isStart){
                    isStart = true;
                    if(line.contains("<tr>")){
                        isRealStart = true;
                    }
                    if(line.contains("tbody")){
                        isEnd = true;
                    }
                }
                if(isEnd){
                    break;
                }
                if(isRealStart){
                    buffer.append(line).append("\n");
                }
            }
            res[0] = name;
            res[1] = buffer.toString();
        }catch (Exception e){
            ew.close();
            e.printStackTrace();
        }
        //System.out.println(res[1]);
        return res;
    }

    public static String analyseDiv(Element e){
        StringBuilder res = new StringBuilder();
        String imgName = "";
        String num = "";
        Elements ps = e.getElementsByTag("p");
        for(Element p : ps){
            imgName = p.child(0).attr("title");
            num = p.child(1).ownText();
            res.append(imgName).append(":").append(num).append(",");
        }
        return res.toString();

    }


    public static void main(String[] args) throws Exception {

        List<String> list = null;
        for(int i=1;i<300;i++){
            list = new ArrayList<>();
            String path = "https://fgo.umowang.com/servant/";
            path += i;
            String[] res = getConn(path);
            //System.out.println(Arrays.toString(res));
            Document doc = Jsoup.parse(res[1].replaceAll("div","p").replaceAll("tr>","div>"));
            //System.out.println(doc);
            Elements divs = doc.getElementsByTag("div");
            list.add(res[0]);
            //System.out.println(doc);
            for(Element div : divs){
                list.add(analyseDiv(div));
            }
            ew.load(list);
            //System.out.println("===========================");

        }
        ew.close();




    }

}



