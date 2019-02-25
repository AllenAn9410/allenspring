package tools;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class FGO {
    public static Document getConn(String path) throws Exception {
            return Jsoup.connect(path).get();
        }

    public static void main(String[] args) throws Exception {
        Document res= null;
        for(int i=1;i<94561;i++){
            String path = "https://fgo.umowang.com/servant/"+ i;
           res = getConn(path);
            System.out.println(i+ " : " +res.title());
        }

    }


}
