package tools;

import com.cs.esp.org.json.JSONException;
import com.cs.esp.org.json.JSONObject;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.*;

public class SwiftTemplate {
    public SwiftTemplate() {}

    private static JSONObject loadJson(String path) throws Exception {
        String content = "";
        try {
            File file = new File(path);
            content = FileUtils.readFileToString(file, "UTF-8");
        } catch (Exception e) {
            throw new Exception("load json exception: " + path);
        }
        JSONObject jsonObject = new JSONObject(content);
        return jsonObject;
    }

    public String analysisJson(String path,String mt) throws Exception {
        TraverseJson ts = new TraverseJson();
        JSONObject jsonObject = loadJson(path);
        JSONObject jsonArrayA = null;
        JSONObject jsonArrayB = null;
        JSONObject jsonArrayC = null;
        JSONObject jsonArray = null;
        JSONObject parentJsonObject = null;
        boolean isSeqABC = false;
        if ( jsonObject.has(mt) ){
            parentJsonObject = jsonObject.getJSONObject(mt);
            if ( parentJsonObject.has("SeqA")){
                jsonArrayA= parentJsonObject.getJSONObject("SeqA");
                isSeqABC = true;
                ts.traverse(jsonArrayA);
            }
            if ( parentJsonObject.has("SeqB")){
                jsonArrayB= parentJsonObject.getJSONObject("SeqB");
                isSeqABC = true;
                ts.traverse(jsonArrayB);
            }
            if ( parentJsonObject.has("SeqC")){
                jsonArrayC= parentJsonObject.getJSONObject("SeqC");
                isSeqABC = true;
                ts.traverse(jsonArrayC);
            }
            if ( parentJsonObject.has("F20Z") && isSeqABC){
                String f20zValue = parentJsonObject.getString("F20Z");
                ts.f20z(f20zValue);
            }
        }
        if( !isSeqABC ){
            jsonArray = jsonObject.getJSONObject(mt);
            ts.traverse(jsonArray);
        }
        List<TemplateBean> list = ts.getHashMap();
        generateXML(list);
        return "";
    }

    public void generateXML(List list) throws JSONException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");
        Element tags = root.addElement("tags");
        for(int i=0;i<list.size();i++){
            TemplateBean tb = (TemplateBean) list.get(i);
            String tag_code = tb.getTagCode();
            tag_code = tag_code.substring(1);
            String str = tb.getStr();
            Element tag = tags.addElement("tag");
            tag.addElement("tag-code").addText(tag_code);
            tag.addElement("tag-desc");
            tag.addElement("tag-form").addText("7");
            // System.out.println(str);
            String start = str.substring(0,1);
            String end = str.substring(str.length()-1);
            if ( !start.equals("{") || !end.equals("}") ){
                int[] segment = calculateLen(str);
                tag.addElement("segment-1")
                        .addAttribute("cols",segment[0]+"")
                        .addAttribute( "illegal-char","")
                        .addAttribute("rows",segment[1]+"");
            } else {
                boolean isMutiSegm = false;
                JSONObject jsonObject = new JSONObject(str);
                Iterator<String> it = jsonObject.keys();
                List<String> segmList = new ArrayList<String>();
                while( it.hasNext() ){
                    String key = it.next();
                    String value = jsonObject.getString(key);
                    if (key.contains("@occur")) {
                        continue;
                    }
                    if( value.contains("\r\n") ){
                        isMutiSegm = true;
                    }
                    segmList.add(value);
                }
                int[] temp ={0,0};
                for(int j=0;j<segmList.size();j++){
                    int[] segment = calculateLen(segmList.get(j));
                    if( isMutiSegm ){
                        tag.addElement("segment-"+j+1)
                                .addAttribute("cols",segment[0]+"")
                                .addAttribute("illegal-char","")
                                .addAttribute("rows",segment[1]+"");
                    } else {
                        temp[0] += segment[0];
                        temp[1] = temp[1] > segment[1] ? temp[1] : segment[1];
                    }

                }
                if( !isMutiSegm ){
                    tag.addElement("segment-1")
                            .addAttribute("cols",temp[0]+"")
                            .addAttribute("illegal-char","")
                            .addAttribute("rows",temp[1]+"");
                }
            }
        }
        try {
            XMLWriter writer = new XMLWriter(new FileOutputStream(new File("./test.xml")));
            writer.write(document);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] calculateLen(String str){
        int rows = 1;
        String regNum = "[0-9]";
        int start = 0;
        int end = 0;
        if ( str.contains("(")){
            start = str.indexOf("(");
            end = str.indexOf(")");
            String temp = str.substring(start,end+1);
            String replace = "";
            if ( !temp.contains("|")){
                String tempp = str.substring(start+1, end);
                replace = calculateLen(tempp)[0]+"";
                str = str.replace(temp,replace);
                String[] special = str.split("\\*");
                int[] t = {Integer.parseInt(special[1]),Integer.parseInt(special[0])};
                return t;
            }
            str = str.replace(temp,replace);
        }
        if ( str.contains("{")){
            start = str.indexOf("{");
            end = str.indexOf("}");
            String temp = str.substring(start,end+1);
            str = str.replace(temp,"");
        }
        str = str.trim();
        int total = 0;
        String temp = "";
        for(int i=0;i<str.length();i++){
            String a = String.valueOf(str.charAt(i));
            if ("/".equals(a)){
                total += 1;
            }
            if ( "x".equals(a) || "c".equals(a) || "d".equals(a) || "n".equals(a) || "a".equals(a) ){
                boolean isExcla = false;
                int ii = i;
                while (true){
                    ii--;
                    String aa = String.valueOf(str.charAt(ii));
                    if ( "!".equals(aa) ) {
                        isExcla = true;
                        continue;
                    }
                    if( aa.matches(regNum) || "*".equals(aa) ){
                        start = ii;
                        end = i;
                    } else {
                        if(isExcla) {
                            end = end -1;
                        }
                        temp = str.substring(start,end);
                        if(temp.contains("*")){
                            String[] arr = temp.split("\\*");
                            rows = Integer.valueOf(arr[0]);
                            total += Integer.valueOf(arr[1]);
                        } else {
                            total += Integer.valueOf(temp);
                        }
                        break;
                    }
                    if( ii == 0 ){
                        if(isExcla) {
                            end = end -1;
                        }
                        temp = str.substring(start,end);
                        if(temp.contains("*")){
                            String[] arr = temp.split("\\*");
                            rows = Integer.valueOf(arr[0]);
                            total += Integer.valueOf(arr[1]);
                        } else {
                            total += Integer.valueOf(temp);
                        }
                        break;
                    }
                }
            }
        }
        int[] t = {total,rows};
        return t;
    }
}
