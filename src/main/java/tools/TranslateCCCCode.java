package tools;

import com.cs.esp.org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author allen.an
 *
 */

public class TranslateCCCCode {
    private final static String ZH = "chinese";
    private final static String CCCCODE = "ccc";
    private final static String UNICODE = "unicode";
    private final static String UNICODESC = "uni-sc";
    private final static String UNICODETC = "uni-tc";
    private final static String ZHCN = "zh-CN";
    private final static String ZHTW = "zh-TW";
    JSONObject jsonObject = null;

    public String seekSource(String msg, String lang) throws Exception {
        System.out.println(this);
        if (jsonObject == null) {
            jsonObject = readCCCJsonFile();
        }
        String inputType = judgeInputType(msg);
        String[] arr = splitMsg(msg, inputType);
        String backMsg = "";
        try {
            for (int i = 0; i < arr.length; i++) {
                backMsg += seek(arr[i], jsonObject, inputType, lang);
            }
        } catch (Exception e) {
            //CONST.log.debug("Failed to translate");
        }
        backMsg = backMsg.replaceAll("\r\n ", "\r\n");
        return backMsg.trim();

    }

    private JSONObject readCCCJsonFile() throws Exception {
        BufferedReader reader = null;
        String lastStr = "";
        JSONObject jsonObject = null;
        File file = new File("ccc.json");
        if (!file.exists()) {
            throw new Exception("can not find ccc.json");
        } else {
            try {
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                reader = new BufferedReader(isr);
                String temp = null;
                while ((temp = reader.readLine()) != null) {
                    lastStr += temp;
                }
                reader.close();
                jsonObject = new JSONObject(lastStr);
            } catch (Exception e) {
                //CONST.log.debug("Failed to read the ccc.json");
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {

                    }
                }
            }
        }
        return jsonObject;
    }

    private boolean validatorLang(String lang) {
        boolean isLang = false;
        if (CCCCODE.equals(lang)) {
            isLang = true;
        }
        if (ZHCN.equals(lang)) {
            isLang = true;
        }
        if (ZHTW.equals(lang)) {
            isLang = true;
        }
        if (UNICODESC.equals(lang)) {
            isLang = true;
        }
        if (UNICODETC.equals(lang)) {
            isLang = true;
        }
        return isLang;
    }

    private String seek(String input, JSONObject jsonObject, String inputType, String lang) throws JSONException {
        String result = null;
        if (isEmpty(input)) {
            return " ";
        }
        DecimalFormat df = new DecimalFormat("0000");
        String cccCode = null;
        String simplifiedChinese = null;
        String traditionalChinese = null;
        JSONObject jsonC = null;
        if (CCCCODE.equals(inputType) && (ZHCN.equals(lang) || ZHTW.equals(lang))) {
            if (jsonObject.has(input)) {
                cccCode = input;
                jsonC = jsonObject.getJSONObject(cccCode);
                simplifiedChinese = jsonC.getString(ZHCN);
                traditionalChinese = jsonC.getString(ZHTW);
                if ((ZHCN).equals(lang)) {
                    result = simplifiedChinese;
                } else if ((ZHTW).equals(lang)) {
                    result = traditionalChinese;
                }
            } else {
                result = otherCharacter(result, lang, input);
            }
        } else {
            for (int i = 0; i < jsonObject.length(); i++) {
                cccCode = df.format(i + 1);
                jsonC = jsonObject.getJSONObject(cccCode);
                simplifiedChinese = jsonC.getString(ZHCN);
                traditionalChinese = jsonC.getString(ZHTW);
                if (input.equalsIgnoreCase(simplifiedChinese)) {
                    if (ZHCN.equals(lang) || ZHTW.equals(lang)) {
                        result = jsonC.getString(lang);
                    } else {
                        result = cccCode;
                    }
                } else if (input.equalsIgnoreCase(traditionalChinese)) {
                    if (ZHCN.equals(lang) || ZHTW.equals(lang)) {
                        result = jsonC.getString(lang);
                    } else {
                        result = cccCode;
                    }
                }
            }
        }
        result = otherCharacter(result, lang, input);
        return (ZHCN.equals(lang) || ZHTW.equals(lang) || "\r\n".equals(result)) ? result : result + " ";
    }

    // It's best not to maintain this code
    private String[] splitMsg(String msg, String inputType) {
        String[] arr = null;
        String regCh = "[\\u4e00-\\u9fa5]+";
        String regEn = "[a-zA-Z]";
        String regNum = "[0-9]";
        String inputMsg = convertChineseSymbols(msg);
        int cnNum = chNum(inputMsg);
        if (ZH.equals(inputType)) {
            String lastMsg = "";
            String tempMsg = "";
            int times = 0;
            boolean isBracket = false;
            boolean isLetter = false;
            boolean isBlank = false;
            boolean isNum = false;
            for (int i = 0; i < inputMsg.length(); i++) {
                tempMsg = String.valueOf(inputMsg.charAt(i));
                if (" ".equals(tempMsg) && !isBracket) {
                    isBlank = true;
                    isLetter = false;
                    isNum = false;
                    isBracket = false;
                    lastMsg += tempMsg;
                    continue;
                }
                // if(tempMsg.matches(regCh)){
                if (tempMsg.matches(regCh) && !isBracket) {
                    times += 1;
                    tempMsg = " " + tempMsg;
                    if (times == (cnNum)) {
                        tempMsg = tempMsg + " ";
                    }
                    isBlank = false;
                    isLetter = false;
                    isNum = false;
                    lastMsg += tempMsg;
                    continue;
                }
                if (tempMsg.matches(regEn)) {
                    if (!isBlank && i != 0 && times != (cnNum) && !isBracket) {
                        if (!String.valueOf(inputMsg.charAt(i - 1)).matches(regEn)) {
                            tempMsg = " " + tempMsg;
                            lastMsg += tempMsg;
                        } else {
                            lastMsg += tempMsg;
                        }
                    } else {
                        lastMsg += tempMsg;
                    }
                    isBlank = false;
                    isLetter = true;
                    isNum = false;
                    continue;
                }
                if (tempMsg.matches(regNum) || tempMsg.equals("(") || tempMsg.equals(")")) {
                    //Numbers have brackets
                    if ("(".equals(tempMsg)) {
                        isBracket = true;
                        lastMsg += " " + tempMsg;
                        isBlank = false;
                        isNum = false;
                        isLetter = false;
                        continue;
                    }
                    if (")".equals(tempMsg)) {
                        lastMsg += tempMsg;
                        isBracket = false;
                        isBlank = false;
                        isNum = false;
                        isLetter = false;
                        continue;
                    }
                    if (isBracket) {
                        lastMsg += tempMsg;
                        isBlank = false;
                        isNum = true;
                        isLetter = false;
                        continue;
                    }
                    //Numbers do not have brackets
                    if (!isNum) {
                        if (i != 0 && " ".equals(String.valueOf(inputMsg.charAt(i - 1)))) {
                            tempMsg = "(" + tempMsg;
                        } else {
                            tempMsg = " (" + tempMsg;
                        }
                        isNum = true;
                    }
                    if (isNum && i != (inputMsg.length() - 1) && !(String.valueOf(inputMsg.charAt(i + 1))).matches(regNum)) {
                        tempMsg = tempMsg + ")";
                        isNum = false;
                    }
                    if ((i == (inputMsg.length() - 1)) && (String.valueOf(inputMsg.charAt(inputMsg.length() - 1))).matches(regNum)) {
                        tempMsg = tempMsg + ")";
                        isNum = false;
                    }
                    lastMsg += tempMsg;
                    isLetter = false;
                    isBlank = false;
                    continue;
                }
                lastMsg += tempMsg;
            }
            lastMsg = replaceRetracement(lastMsg);
            arr = split(lastMsg);
        }
        if (CCCCODE.equals(inputType)) {
            inputMsg = replaceRetracement(inputMsg);
            String tempMsg = "";
            String lastMsg = "";
            for (int i = 0; i < inputMsg.length(); i++) {
                tempMsg = String.valueOf(inputMsg.charAt(i));
                if ("/".equals(tempMsg)) {
                    tempMsg = tempMsg + " ";
                }
                lastMsg += tempMsg;
            }
            arr = split(lastMsg);
        }
        if (UNICODE.equals(inputType)) {
            arr = split(inputMsg);
        } else if (isEmpty(inputType)) {
            arr = split(inputMsg);
        }
        return arr;
    }

    private String judgeInputType(String msg) {
        String regCcc = "\\s*[0-9]{4}\\s*";
        String regU = ".*[U][\\S]{5}.*";
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(msg);
        if (m.find()) {
            return ZH;
        }
        p = Pattern.compile(regCcc);
        m = p.matcher(msg);
        if (m.find()) {
            return CCCCODE;
        }
        if (msg.matches(regU)) {
            return UNICODE;
        }
        return "";
    }

    private String[] splitMsg(String msg) {
        msg = msg.trim();
        String reg = " ";
        String[] arr = msg.split(reg);
        return arr;
    }

    private static int chNum(String inputMsg) {
        String regEx = "[\u4e00-\u9fa5]";
        int count = 0;
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(inputMsg);
        while (m.find()) {
            count++;
        }
        return count;
    }

    private static String[] split(String strMsg) {
        strMsg = strMsg.trim();
        String reg = " ";
        String[] arr = strMsg.split(reg);
        return arr;
    }

    private static String otherCharacter(String result, String backType, String input) {
        if (isEmpty(result)) {
            result = input;
            if ("<br>".equals(result)) {
                result = "\r\n";
            }
        }
        return result;
    }

    private String replaceRetracement(String input) {
        String msg = "";
        Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r)");
        Matcher m = CRLF.matcher(input);
        if (m.find()) {
            msg = m.replaceAll(" <br> ");
        } else {
            return input;
        }
        return msg;
    }

    private static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private String convertChineseSymbols(String msg) {
        String input = msg;
        input = input.replaceAll("（", "(");
        input = input.replaceAll("）", ")");
        input = input.replaceAll("？", "?");
        return input;
    }
}
