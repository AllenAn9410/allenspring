package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static tools.ExcelLoad.isEmpty;

public class analyse {
    public static void main (String[]args){
        String path = "./target/target2.xls";
        int sheetAt = 1;
        List<String[]> list = ExcelRead.readExcel(path,sheetAt);
        HashMap<String,Integer> map1 = new HashMap<String, Integer>();
        String[] cells = null;
        for(int i=1;i<list.size();i++){
            cells = list.get(i);
            if( isEmpty(cells[1]) ){
                continue;
            }
            String name = cells[1].trim()+"&"+cells[2].trim();
            int num = Integer.valueOf(cells[5]);
            map1.put(name,map1.containsKey(name) ? map1.get(name)+num : num);
        }
        System.out.println();
    }



}
