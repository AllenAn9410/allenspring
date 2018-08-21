package tool;

import org.junit.jupiter.api.Test;
import tools.ExcelWrite;

public class TestExcelLoad {
    @Test
    public void testExcelLoad() {
        String excelPath = "./target/target10.xls";
        String[] params = {"name", "age"};
        String[] values = {"allen", "24"};
        ExcelWrite el = new ExcelWrite(excelPath, params);
        try {
            el.load(values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            el.close();
        }
    }

}
