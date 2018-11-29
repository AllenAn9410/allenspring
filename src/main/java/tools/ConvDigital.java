package tools;

import java.util.LinkedHashMap;

public class ConvDigital {
    private static LinkedHashMap<String,String> map = new LinkedHashMap<String, String>();

    static String num = "零一二三四五六七八九";

    static String aa = "十百千万亿";

    public ConvDigital(){
        map.put("万亿","");
        map.put("千亿","");
        map.put("百亿","");
        map.put("十亿","");
        map.put("亿","");
        map.put("千万","");
        map.put("百万","");
        map.put("十万","");
        map.put("万","");
        map.put("千","");
        map.put("百","");
        map.put("十","");
        map.put("个","");
    }



    public String convertToDigital(String temp){
        for(int i=temp.length();i>0;i--){
            String str = temp.substring(temp.length()-i-1);
            if(num.contains(str)){
                map.put("个",str);
            }

        }
        return "";
    }


}
