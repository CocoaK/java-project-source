package com.smarthome.socket.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 
 * 类名称：StringUtil 
 * 类描述：字符串工具类 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-9-12 下午2:16:12
 */
public class StringUtil {
	/**
	 * 
	 * 方法的描述:将空字符串转为Null 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-9-12 下午2:15:47
	 * @param str
	 * @return
	 */
   public static String coverEmptyToNull(String str)
   {
	   String s=null;
	   if(str==null)
	   {
		   
	   }else if("".equals(str.trim()))
	   {
		   
	   }else
	   {
		   s=str;
	   }
	   return s;
   }
   
   public static String coverMacToString(String str){
	   if (str!=null && !"".equals(str)){
		   return str.replace(":", "");
	   }
	   return null;
   }
   
   /**
	 * 生成随机数
	 * @param length 位数，位数不大于9
	 * @return length位随机数
	 */
	public static String randomNum(int length){
		if(length>9){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<length-1;i++){
			sb.append("0");
		}
		int x = Integer.parseInt("9"+sb.toString());
		int y = Integer.parseInt("1"+sb.toString());
		Integer i = new Random().nextInt(x)+y;
		return i.toString();
	}
	
	 /** Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。  
	 * @param src byte[] data  
	 * @return  hex string  
	 */     
	public static String bytesToHexString(byte[] src){  
	    StringBuilder stringBuilder = new StringBuilder("");  
	    if (src == null || src.length <= 0) {  
	        return null;  
	    }  
	    for (int i = 0; i < src.length; i++) {  
	        int v = src[i] & 0xFF;  
	        String hv = Integer.toHexString(v);  
	        if (hv.length() < 2) {  
	            stringBuilder.append(0);  
	        }  
	        stringBuilder.append(hv);  
	    }  
	    return stringBuilder.toString();  
	}
	
	public static String toHexString(String s) {  
        String str = "";  
        for (int i = 0; i < s.length(); i++) {  
            int ch = (int) s.charAt(i);  
            String s4 = Integer.toHexString(ch);  
            str = str + s4;  
        }  
        return "0x" + str;//0x表示十六进制  
    }
   
   public static void main(String [] arg)
   {
//	   System.out.println(coverEmptyToNull(null));
//	   System.out.println(coverEmptyToNull(""));
//	   System.out.println(coverEmptyToNull("  "));
//	   System.out.println(coverEmptyToNull("sdfds"));
//	   System.out.println("--:"+coverMacToString(""));
   }
   
   /**
    * 将指定规则字符串转换为map
    * @author Cocoa
    * @param msg:以","分隔的"="键值字符串，例如"name=java,age=10"
    * @return map
    */
   public static Map<String,String> getMapFromString(String msg){
	   Map<String,String> map = new HashMap<String,String>();
	   if(msg==null){
		   return map;
	   }
	   String[] arrayString = msg.split(",");
	   for(String s : arrayString){
		   if(s!=null){
			  String[] keyValue = s.split("=");
			  if(keyValue.length==2){
				  map.put(keyValue[0], keyValue[1]);
			  }
		   }
	   }
	   return map;
   }
}
