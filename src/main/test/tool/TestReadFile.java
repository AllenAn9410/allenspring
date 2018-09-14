package tool;

import org.junit.jupiter.api.Test;
import tools.ReadFile;

public class TestReadFile {
    @Test
    public void test(){
        ReadFile rf = new ReadFile("pom.xml");
        String a = rf.read();
        System.out.println(a);
    }
}
