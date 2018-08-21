package tools;

public class TemplateBean {
    private String tagCode;
    private String str;

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "TemplateBean{" +
                "tagCode='" + tagCode + '\'' +
                ", str='" + str + '\'' +
                '}';
    }
}
