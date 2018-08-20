package tools;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class SwiftTemplate {
    public SwiftTemplate() {}

    private JSONObject loadJson(String path) throws Exception {
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

    private String analysisJson(JSONObject jsonObject,String mt){
        JSONArray jsonArray = null;
        if ( jsonObject.has(mt) ){
            if ( jsonObject.has("SeqA")){
                jsonArray = jsonObject.getJSONArray("SeqA");
            }

        }
        jsonArray = jsonObject.getJSONArray("mt");
        return "";
    }



}
