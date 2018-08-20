package tools;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static tools.ExcelWrite.isEmpty;

public class ExcelRead {
    private static int sheetAt = 0;

    public static List<String[]> readExcel(String path) {
        return readExcel(path, sheetAt);
    }

    public static List<String[]> readExcel(String path, int sheetAt) {
        File file = null;
        InputStream is = null;
        List<String[]> list = new ArrayList<String[]>();
        String[] cells = null;
        try {
            if (isEmpty(path)) {
                throw new Exception("the path is empty!");
            } else if (!(new File(path)).exists()) {
                throw new Exception("the path do not exist!");
            }
            file = new File(path);
            is = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            HSSFSheet sheet = workbook.getSheetAt(sheetAt);
            String[] tableHead = getTableHead(sheet);
            list.add(0, tableHead);
            int tableRowLen = tableHead.length;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                cells = new String[tableRowLen];
                HSSFRow row = null;
                try {
                    row = sheet.getRow(i);
                    if (isEmpty(row.getLastCellNum() + "")) {
                        continue;
                    }
                } catch (NullPointerException e) {
                    continue;
                }
                for (int j = 0; j < tableRowLen; j++) {
                    HSSFCell cell = row.getCell(j);
                    row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                    cells[j] = cell.getStringCellValue();
                }
                list.add(i, cells);
            }
            for (String[] a : list) {
                for (String b : a) {
                    System.out.print(b + "  ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;

    }

    private static String[] getTableHead(HSSFSheet sheet) {
        HSSFRow row0 = sheet.getRow(0);
        String[] head = new String[row0.getLastCellNum()];
        for (int i = 0; i < row0.getLastCellNum(); i++) {
            HSSFCell cell = row0.getCell(i);
            head[i] = cell.getStringCellValue();
        }
        return head;
    }


}
