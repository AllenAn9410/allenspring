package tools;



import com.cs.esp.org.json.JSONArray;
import com.cs.esp.org.json.JSONException;
import com.cs.esp.org.json.JSONObject;

import java.util.*;

public class TraverseJson {
    String choice = "Choice";
    String Loops = "Loop";
    HashMap hashMap = new LinkedHashMap();
    List<TemplateBean> list = new ArrayList<TemplateBean>();

    public void traverse(JSONObject jsonObject) throws JSONException {
        Iterator<String> it = jsonObject.keys();
        JSONObject specialStr = null;
        while (it.hasNext()) {
            TemplateBean tb = new TemplateBean();
            String key = it.next();
            String value = jsonObject.getString(key);
            if (key.contains(choice) || key.contains(Loops)) {
                specialStr = new JSONObject(value);
                Iterator<String> its = specialStr.keys();
                while (its.hasNext()) {
                    tb = new TemplateBean();
                    String specialKey = "";
                    String specialValue = "";
                    specialKey = its.next();
                    specialValue = specialStr.getString(specialKey);
                    if (specialKey.contains("@occur")) {
                        continue;
                    }
                    tb.setTagCode(specialKey);
                    tb.setStr(specialValue);
                    list.add(tb);
                }
                continue;
            }
            if (key.contains("@occur")) {
                continue;
            }
            tb.setTagCode(key);
            tb.setStr(value);
            list.add(tb);
        }
        // System.out.println(jsonObject);


    }


    public void f20z(String value) {
        TemplateBean tb = new TemplateBean();
        tb.setTagCode("F20Z");
        tb.setStr(value);
        list.add(tb);
    }


    public List<TemplateBean> getHashMap(){
        return list;
    }
}
