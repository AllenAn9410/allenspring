package tool;

import org.junit.jupiter.api.Test;
import tools.TranslateCCCCode;

public class TestTranCCCCode {
    TranslateCCCCode tc = new TranslateCCCCode();

    @Test
    public void test() throws Exception {
        String str = "丁程鑫是大帅逼";
        String lang = "ccc";
        String res = tc.seekSource(str, lang);
        System.out.println(res);
        // testCCC();
    }

    @Test
    public void testCCC() throws Exception {
        String str = "3968 4544 5071 1133 0048 0008 2508 0087 8010 1921 7621";
        String lang = "zh-CN";
        String res = tc.seekSource(str, lang);
        System.out.println(res);
    }
}
