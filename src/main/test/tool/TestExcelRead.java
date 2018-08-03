package tool;

import org.junit.jupiter.api.Test;
import tools.ExcelRead;

public class TestExcelRead {
    @Test
    public void test(){
        String path = "./target/target2.xls";
        int sheetAt = 1;
        ExcelRead er = new ExcelRead();
        er.readExcel(path,sheetAt);
    }


}
