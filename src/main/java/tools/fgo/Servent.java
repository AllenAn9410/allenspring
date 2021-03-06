package tools.fgo;

import java.util.ArrayList;
import java.util.List;

public class Servent {
    /*
        {
            id:0,
            name:"",
            photo:"",
            class:"",
            limitBreak:[
                { curlevel:1,limitbreakitemlist:[ {name:"",num:""}, {name:"",num:""} ] },
                { curlevel:2,limitbreakitemlist:[ {name:"",num:""}, {name:"",num:""} ] },
                { curlevel:3,limitbreakitemlist:[ {name:"",num:""}, {name:"",num:""} ] },
                { curlevel:2,limitbreakitemlist:[ {name:"",num:""}, {name:"",num:""} ] }
            ],
            skillsName:[ ... , ... , ...],
            skillsMaterials:[
                2:[{},{}],
                3:[{},{}],
                4:[{},{}],
                5:[{},{}],
                6:[{},{}],
                7:[{},{}],
                8:[{},{}],
                9:[{},{}],
                10:[]
            ]
        }
     */
    private String name;
    private List<String> serventUpdateMaterial = new ArrayList<>(6);
    private List<String> skillMaterial = new ArrayList<>(15);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> serventMaterial() {
        return serventUpdateMaterial;
    }

    public void setServentmMaterial(List<String> serventMaterial) {
        this.serventUpdateMaterial = serventMaterial;
    }

    public List<String> getSkillMaterial() {
        return skillMaterial;
    }

    public void setSkillMaterial(List<String> skillMaterial) {
        this.skillMaterial = skillMaterial;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //data =   { "name":"name", "sum":[...], "sm":[...] }
        sb.append("{").append("\"name\":").append(this.name).append(",");
        if(serventUpdateMaterial.size() != 0){
            sb.append("\"sum\":[");
            for(int i=0;i<serventUpdateMaterial.size();i++){
                sb.append("{").append(serventUpdateMaterial.get(i)).append("}");
                if(i != serventUpdateMaterial.size()-1 ){
                    sb.append(",");
                }
            }
            sb.append("]").append(",");
        }
        if(skillMaterial.size() != 0){
            sb.append("\"sm\":[");
            for(int i=0;i<skillMaterial.size();i++){
                sb.append("{").append(skillMaterial.get(i)).append("}");
                if(i != skillMaterial.size()-1 ){
                    sb.append(",");
                }
            }
            sb.append("]");
        }
        sb.append("}");
        return sb.toString();
    }

}
