package tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author  allen.an
 */
public class ExcelLoad {
    private String targetPath = null;
    private int startPosition = 0;
    private int excelLine = 1;
    private int excelHeaderLength = 0;

    HSSFWorkbook wb=null;
    HSSFSheet sheet=null;
    HSSFRow row=null;
    FileOutputStream out = null;

    /**
     *
     * @author  allen.an
     * @param path url
     * @param params excel header
     *
     */
    public ExcelLoad(String path,String[] params){
        this(path,params,0);
    }

    public ExcelLoad(String path,String[] params,int startPos){
        try {
            if (!isEmpty(path)){
                throw new Exception("the path is empty!");
            } else if (!(new File(path)).exists()){
                throw new Exception("the path do not exist!");
            }

            setHeaderLength(params);

            this.targetPath = path;
            this.startPosition = startPos;

            wb=new HSSFWorkbook();
            sheet=wb.createSheet("input");
            out=new FileOutputStream(targetPath);

            createExcelHeader(out,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createExcelHeader(FileOutputStream out,String[] params) {
        int i = startPosition;
        row = sheet.createRow(0);
        for(String param:params) {
            row.createCell(i).setCellValue(param);
            i++;
        }
    }

    private void setHeaderLength(String[] params) {
        int length = params.length;
        this.excelHeaderLength = length;
    }

    private boolean checkParamsLength(String[] values) {
        return values.length == excelHeaderLength ? true : false;
    }

    /**
     * @param values
     * @example
     *      ExcelLoad el = new ExcelLoad(excelPath,params);
     *      try{
     *          el.load(values);
     *      }catch (Exception e){
     *          e.printStackTrace();
     *      }finally {
     *          el.close();
     *      }
     * @return it should be rebuild by json
     */

    public String load(String[] values) {
        if(!checkParamsLength(values)) {
            return "error";
        }

        int i = startPosition;
        row = sheet.createRow(excelLine);

        for(String value:values) {
            row.createCell(i).setCellValue(value);
            i++;
        }
        excelLine++;
        return "";

    }

    public void close() {
        try {
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isEmpty(String str){
        if ( str == null || str.length() == 0 ){
            return true;
        }
        return false;
    }


}
