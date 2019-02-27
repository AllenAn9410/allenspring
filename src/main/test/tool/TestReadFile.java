package tool;

import org.junit.jupiter.api.Test;
import tools.ReadFile;

public class TestReadFile {
    @Test
    public void test() {
        int a = ReadFile.count("永夜君王.txt");
        System.out.println(a);
    }
}
