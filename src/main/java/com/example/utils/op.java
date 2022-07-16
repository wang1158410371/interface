package com.example.utils;

/**
 * @Author: 唐
 * @Date: 2020/2/26 14:40
 */
import java.io.File;
import java.io.IOException;
import java.util.*;

//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class op
{
    public static void main(String[] args) throws Exception

    {

        String key01=null;
        String value01 = null;
        Map<String,String> map=new HashMap<>();
        List list=new LinkedList();
        File xlsFile = new File("test-output/test.xlsx");
        // 获得工作簿
        Workbook workbook = WorkbookFactory.create(xlsFile);
        // 获得工作表个数
        int sheetCount = workbook.getNumberOfSheets();
        // 遍历工作表1
        for (int i = 0; i < sheetCount; i++)
        {
            Sheet sheet = workbook.getSheetAt(i);
            // 获得行数
            int rows = sheet.getLastRowNum() + 1;
            // 获得列数，先获得一行，在得到改行列数
            Row tmp = sheet.getRow(0);
            if (tmp == null)
            {
                continue;
            }
            int cols = tmp.getPhysicalNumberOfCells();
            // 读取数据  getPhysicalNumberOfCells 是获取不为空的列个数。
            for (int row = 0; row < rows; row++)
            {
                Row r = sheet.getRow(row);
                for (int col = 0; col < cols; col++)
                {
                    String  temp01 = r.getCell(col).getStringCellValue();
                    list.add(temp01);
                }
                System.out.println();
            }
//                System.out.println(list);
            for (int j = 0; j <list.size() ; j=j+2) {
//                System.out.println("key"+list.get(j));
//                System.out.println("value"+list.get(j+1));
                map.put(list.get(j).toString(),list.get(j+1).toString());
            }
            for (String key : map.keySet()) {
                String value = map.get(key);
//                System.out.println(key + "--->" + value);
            }
            List<Map.Entry<String,String>> list01 = new ArrayList<Map.Entry<String,String>>(map.entrySet());
            Collections.sort(list01,new Comparator<Map.Entry<String,String>>() {
                //升序排序
                public int compare(Map.Entry<String, String> o1,
                                   Map.Entry<String, String> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }

            });

            for(Map.Entry<String,String> mapping:list01){
                System.out.println(mapping.getKey()+":"+mapping.getValue());
            }
        }


    }
}
