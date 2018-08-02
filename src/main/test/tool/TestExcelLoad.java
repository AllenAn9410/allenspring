package tool;

import org.junit.jupiter.api.Test;
import tools.ExcelLoad;

public class TestExcelLoad {
    @Test
    public void testExcelLoad(){
        String excelPath = "./target/target.xls";
        String[] params = {"name","age"};
        String[] values = {"allen","24"};
        ExcelLoad el = new ExcelLoad(excelPath,params);
        try{
            el.load(values);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            el.close();
        }
    }

}
