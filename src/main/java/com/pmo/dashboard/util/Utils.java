package com.pmo.dashboard.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Utils
{

    public static String getUUID()
    {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
    
    
    public static int caculateMonth(String date){
        if("".equals(date)){
            return 0;
        }
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = df.format(new Date());
        String year1 = todayDate.substring(0,4);
        String month1 = todayDate.substring(5,7);
        String day1 = todayDate.substring(8);
        
        boolean status = date.contains("/");
        String symbol = "";
        
        if(status){
            symbol = "/";
        }else{
            symbol = "-";
        }
        
        String[] strs=date.split(symbol);
        String year2 = strs[0].toString();
        String month2 = strs[1].toString();
        String day2 = strs[2].toString();
        
        int totalMonth = 0;
        int year = Integer.valueOf(year1).intValue() - Integer.valueOf(year2).intValue();
        int month = Integer.valueOf(month1).intValue() - Integer.valueOf(month2).intValue();
        int day = Integer.valueOf(day1).intValue() - Integer.valueOf(day2).intValue();
        if(year>0){
            totalMonth += year * 12;
        }
        
        totalMonth += month;
        
        if(day<0){
            totalMonth -= 1;
        }
        
        return totalMonth;
    }
    
   
}
