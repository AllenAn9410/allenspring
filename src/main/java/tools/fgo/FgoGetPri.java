package tools.fgo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class FgoGetPri implements Runnable {
    private String path ;
    private ConcurrentHashMap map = new ConcurrentHashMap();
    public FgoGetPri(String path){
        this.path = path;
    }

    @Override
    public void run() {
        try
        {
            Document document = Jsoup.connect(path).timeout(5000).get();
            String title = document.title();
            String name = title.trim().substring(title.indexOf(">")+1,title.indexOf("-"));
            //System.out.println();
            Elements jpgs = document.select("img");
            for(Element e : jpgs){
                String jpgName = e.attr("title");
                String jpgUrl = e.attr("src");
                map.put(jpgName,jpgUrl);
            }
            System.out.println("///");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
