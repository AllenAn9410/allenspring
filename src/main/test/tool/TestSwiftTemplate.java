package tool;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import tools.SwiftTemplate;

import java.io.File;

public class TestSwiftTemplate {
    @Test
    public void testSwiftTemplate() throws Exception {
        String path = "./src/main/test/2018/";
        File file = null;
//        for(int i=101;i<1000;i++){
//            String name = "";
//            name = "fin." + String.valueOf(i) + ".ESP.json";
//            file = new File(path + name);
//            if(file.exists()){
//                System.out.println(name);
//                continue;
//            } else {
//                name = "fin." + String.valueOf(i) + ".json";
//                file = new File(path + name);
//            }
//            if(file.exists()) {
//                name = "fin." + String.valueOf(i) + ".json";
//                System.out.println(name);
//            }
//        }
        SwiftTemplate st = new SwiftTemplate();

//        S jsonObject = st.loadJson(path);
//        System.out.println(jsonObject);
        String a = st.analysisJson("./src/main/test/2018/fin.103.ESP.json","MT103");


    }



}
