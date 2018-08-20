package tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tools.ExcelWrite.isEmpty;

public class AnalyData {
    public static void main(String[] args) {
        collection();
    }

    public static void collection() {
        String path = "./target/target2.xls";
        int sheetAt = 1;
        List<String[]> list = ExcelRead.readExcel(path, sheetAt);
        HashMap<String, Integer> map1 = new HashMap<String, Integer>();
        String[] cells = null;
        for (int i = 1; i < list.size(); i++) {
            cells = list.get(i);
            if (isEmpty(cells[1])) {
                continue;
            }
            String name = cells[1].trim() + "&" + cells[2].trim();
            int num = Integer.valueOf(cells[5]);
            map1.put(name, map1.containsKey(name) ? map1.get(name) + num : num);
        }
        System.out.println();
        String[] head = {list.get(0)[1], list.get(0)[2], list.get(0)[5]};
        ExcelWrite ew = new ExcelWrite("./target/target3.xls", head);
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String[] row = new String[3];
            // System.out.println(entry.getKey() + " ->  " +entry.getValue());
            row[0] = entry.getKey().split("&")[0];
            row[1] = entry.getKey().split("&")[1];
            row[2] = entry.getValue() + "";
            ew.load(row);
        }
        ew.close();
    }
}
