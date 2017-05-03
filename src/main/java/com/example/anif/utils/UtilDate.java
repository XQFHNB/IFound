package com.example.anif.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XQF
 * @created 2017/5/1
 */
public class UtilDate {
    public static String getPublishTime(String str) {
        String[] strs = str.split(" ");
        Map<String, String> dateMap = new HashMap<>();
        dateMap.put("Mon", "星期一");
        dateMap.put("Tue", "星期二");
        dateMap.put("Wed", "星期三");
        dateMap.put("Thu", "星期四");
        dateMap.put("Fri", "星期五");
        dateMap.put("Sat", "星期六");
        dateMap.put("Sun", "星期日");
        Map<String, String> monthMap = new HashMap<>();
        monthMap.put("Jan", "1月");
        monthMap.put("Feb", "2月");
        monthMap.put("Mar", "3月");
        monthMap.put("Apr", "4月");
        monthMap.put("May", "5月");
        monthMap.put("Jun", "6月");
        monthMap.put("Jul", "7月");
        monthMap.put("Aug", "8月");
        monthMap.put("Sep", "9月");
        monthMap.put("Oct", "10月");
        monthMap.put("Nov", "11月");
        monthMap.put("Dec", "12月");
        String date = dateMap.get(strs[0]);
        String month = monthMap.get(strs[1]);
        String day = strs[2];
        String detailTime = strs[3];
        String year = strs[5];
        StringBuffer sb = new StringBuffer();
        sb.append(year + " " + month + " " + day + " " + date + " " + detailTime);
        return sb.toString();
    }
}
