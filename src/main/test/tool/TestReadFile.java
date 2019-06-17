package tool;

import org.junit.jupiter.api.Test;
import tools.ReadFile;

import java.io.IOException;
import java.io.Reader;

public class TestReadFile {
    @Test
    public void test() {
        int a = ReadFile.count("test.js");
        System.out.println(a);
    }



}
