package com.elec5619.bi.utils;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel 相关工具类
 */
public class ExcelUtils {

    public static String excelToCsv(MultipartFile multipartFile) {
//        //尝试读取文件，如果文件不存在，抛出异常
//        File file = null;
//        try {
//            file = ResourceUtils.getFile("classpath:test_excel.xlsx");
//        } catch (FileNotFoundException e) {
////            e.printStackTrace();
//            throw new RuntimeException(e);
//        }


        //读取Excel文件
        List<Map<Integer, String>> list = null;
        try {
            list = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        } catch (IOException e) {
            throw new RuntimeException("读取Excel文件失败");
        }

        //校验文件
        if (list == null || list.isEmpty()) {
            throw new RuntimeException("文件内容为空");
        }

        //转换为csv文件
        StringBuilder sb = new StringBuilder();

        //1. 获取表头
        LinkedHashMap<Integer, String> header = (LinkedHashMap<Integer, String>) list.get(0);
        for (Map.Entry<Integer, String> entry : header.entrySet()) {
            //去除null值
            if (StringUtils.isEmpty(entry.getValue())) {
                continue;
            }
            sb.append(entry.getValue()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");

        //2. 获取表内容
        for (int i = 1; i < list.size(); i++) {
            LinkedHashMap<Integer, String> row = (LinkedHashMap<Integer, String>) list.get(i);
            for (Map.Entry<Integer, String> entry : row.entrySet()) {
                sb.append(entry.getValue()).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
        }

        //3. 返回csv文件
        return sb.toString();
    }

    public static void main(String[] args) {
        excelToCsv(null);
    }

}
