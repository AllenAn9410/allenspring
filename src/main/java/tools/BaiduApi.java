package tools;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;

import static tools.ExcelWrite.isEmpty;

public class BaiduApi {
    private String city = null;

    public BaiduApi(String city) {
        this.city = city + "汽车维修";
    }

    public JSONObject excute() {
        String[] excelHead = {"公司名称", "地址", "移动电话", "固话"};
        String excelPath = "./target/ctq.xls";
        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        boolean isRecord = true;
        String output = null;
        int pn = 1;
        int index;
        ExcelWrite ew = null;
        try {
            this.city = URLEncoder.encode(this.city, "UTF-8");
            ew = new ExcelWrite(excelPath, excelHead);
            while (isRecord) {
                output = getMessage(pn, response, httpclient);
                index = output.indexOf("(");
                output = output.substring(index + 1);
                if (isEmpty(output) || output.length() < 5000) {
                    isRecord = false;
                } else {
                    pn++;
                    analyse(output, ew);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return ew.close();
        }
    }

    private String getMessage(int pn, CloseableHttpResponse response, CloseableHttpClient httpclient) {
        String output = "";
        String url = "http://api.map.baidu.com/?qt=s&c=1&wd=" + this.city +
                "&rn=10000&pn=" + pn + "&ie=utf-8&oue=1&fromproduct=jsapi&res=api&callback=BMap._rd._cbk64582";

        HttpGet get = new HttpGet(url);
        get.setHeader("Content-Type", "text/html");
        try {
            response = httpclient.execute(get);
            output = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return output;
    }

    private void analyse(String msg, ExcelWrite ew) {
        String phone;
        JSONObject oneData;
        JSONObject json;
        json = new JSONObject(msg);
        JSONArray jsonArray = json.getJSONArray("content");
        for (int i = 0; i < jsonArray.length(); i++) {
            /*
             {"公司名称","地址","移动电话","固话"}
             {name,address,mobile,num}
             */
            String[] info = {"", "", "", ""};
            oneData = jsonArray.getJSONObject(i);
            info[0] = oneData.getString("name");
            info[1] = oneData.getString("addr");
            if (oneData.has("tel")) {
                phone = oneData.getString("tel");
                if (!isEmpty(phone) && phone.indexOf(",") != -1) {
                    String[] nums = phone.split(",");
                    for (int j = 0; j < nums.length; j++) {
                        if (nums[j].length() == 11) {
                            info[2] += nums[j] + " ";
                            nums[j] = "";
                        } else {
                            info[3] += nums[j] + "  ";
                        }
                    }
                } else {
                    if (phone.length() == 11) {
                        info[2] = phone;
                    } else {
                        info[3] = phone;
                    }
                }
            } else {
                info[2] = "";
                info[3] = "";
            }
            System.out.println(Arrays.toString(info));
            ew.load(info);
        }
    }
}
