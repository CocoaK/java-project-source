/**
 * 
 */
package com.quhwa.scalehouse.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/** 
 * @Title:        GetPastDateUtils 
 * @Description:  TODO(这里用一句话描述这个方法的作用)         
 * @author        kouzhao
 * @Date          2018-5-22 下午2:29:47 
 */
public class GetPastDateUtils {
	
	public static ArrayList<String> rev(ArrayList<String> array) {
	    int k=array.size();
	    int j=k;
	    ArrayList<String> result=new ArrayList<String>();
	    for(int i=0;i<k;i++) {
	        result.add(array.get(j-1));
	        j--;
	    }
	    return result;
	}
	
	public static ArrayList<String> dateArray(int intervals ) {  
       ArrayList<String> pastDaysList = new ArrayList<String>();  
       ArrayList<String> fetureDaysList = new ArrayList<String>();  
       for (int i = 0; i <intervals; i++) {  
           pastDaysList.add(getPastDate(i));  
           fetureDaysList.add(getFetureDate(i));  
       }  
       return rev(pastDaysList);  
    }
		
	public static String getPastDate(int past) {  
       Calendar calendar = Calendar.getInstance();  
       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);  
       Date today = calendar.getTime();  
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
       String result = format.format(today);  
       return result;  
    }
		
	public static String getFetureDate(int past) {  
       Calendar calendar = Calendar.getInstance();  
       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);  
       Date today = calendar.getTime();  
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
       String result = format.format(today);  
       return result;  
    }
}
