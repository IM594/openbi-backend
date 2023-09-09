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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel 相关工具类
 */
public class ExcelUtils {

    public static String excelToCsv(MultipartFile multipartFile) {

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

        //由于easyexcel对日期格式的支持不好，所以需要手动转换，日期在exsayexcel里面是一个数字，需要转换为字符串


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

        // 2. 获取表内容。如果是日期格式，需要转换为字符串
        for (int i = 1; i < list.size(); i++) {
            LinkedHashMap<Integer, String> row = (LinkedHashMap<Integer, String>) list.get(i);
            for (Map.Entry<Integer, String> entry : row.entrySet()) {
                // 去除null值
                if (StringUtils.isEmpty(entry.getValue())) {
                    continue;
                }

                // 日期格式：如果开头是t，则检查并转换
                if (entry.getValue().startsWith("t") && entry.getValue().length() > 1) {
                    String date = dateToString(entry.getValue());
                    sb.append(date).append(",");
                }
                // 数字格式：检查并转换
                else if (entry.getValue().contains(",")) {
                    String number = numberToString(entry.getValue());
                    sb.append(number).append(",");
                } else {
                    sb.append(entry.getValue()).append(",");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
        }


        //3. 返回csv文件
        return sb.toString();
    }

    public static String csvToString(MultipartFile multipartFile) {
        //读取csv文件
        List<Map<Integer, String>> list = null;
        try {
            list = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.CSV)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        } catch (IOException e) {
            throw new RuntimeException("读取csv文件失败");
        }

        //校验文件
        if (list == null || list.isEmpty()) {
            throw new RuntimeException("文件内容为空");
        }

        //转换为String
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            LinkedHashMap<Integer, String> row = (LinkedHashMap<Integer, String>) list.get(i);
            for (Map.Entry<Integer, String> entry : row.entrySet()) {
                //去除null值
                if (StringUtils.isEmpty(entry.getValue())) {
                    continue;
                }
                sb.append(entry.getValue()).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
        }

        //返回String
        return sb.toString();
    }

    /**
     * 日期格式转换
     * @param date
     * @return
     */
    public static String dateToString(String date) {
        // 日期格式：如果开头是t然后是五个数字，例如t12345；或者t41388.00，或者t41388.0000
        if (date.startsWith("t") && date.length() > 1) {
            String dateValue = date.substring(1); // 去掉开头的 't'
            String[] parts = dateValue.split("\\.");

            if (parts.length >= 1 && parts[0].matches("[0-9]+")) {
                long daysSinceEpoch = Long.parseLong(parts[0]);
                long millisecondsSinceEpoch = (daysSinceEpoch - 25569) * 24 * 60 * 60 * 1000L; // 从1900年1月1日起的天数

                if (parts.length == 2 && parts[1].matches("[0-9]+")) {
                    long millisecondsOfDay = (long) (Double.parseDouble("0." + parts[1]) * 24 * 60 * 60 * 1000L);
                    millisecondsSinceEpoch += millisecondsOfDay;
                }

                // 从毫秒数创建日期
                Date date1 = new Date(millisecondsSinceEpoch);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = dateFormat.format(date1);
                return formattedDate;
            }
        }

        return date;
    }

    //如果数字格式为123,456.00，需要转换为123456.00，否则会和csv的逗号冲突
    public static String numberToString(String number) {
        //如果确认是数字格式，而不是文字中包含,，则转换
        if (number.matches("[0-9]+,[0-9]+(.[0-9]+)?")) {
            String[] parts = number.split(",");
            String result = "";
            for (String part : parts) {
                result += part;
            }
            return result;
        }
        return number;
    }



    public static void main(String[] args) {
        excelToCsv(null);
    }
}
