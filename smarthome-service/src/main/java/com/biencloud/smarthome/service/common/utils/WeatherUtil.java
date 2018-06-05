package com.biencloud.smarthome.service.common.utils;

public class WeatherUtil {
	
	public static final String WEATHER_IMAGE_FORMAT = ".png";
	
	//public static final String IMAGE_PATH = "/images/weather/";
	
	public static String weatherName(String weatherDesc){
		if(weatherDesc !=null ){
			if (weatherDesc.contains("雪") && !weatherDesc.contains("大雪") && !weatherDesc.contains("中雪") && !weatherDesc.contains("雨") && !weatherDesc.contains("暴雪"))
	            weatherDesc = "xiaoxue";
			else if(weatherDesc.contains("中雪") && !weatherDesc.contains("大雪") && !weatherDesc.contains("暴雪"))
				weatherDesc = "zhongxue";
			else if(weatherDesc.contains("大雪") || weatherDesc.contains("暴雪"))
				weatherDesc = "daxue";
	        else if (weatherDesc.contains("雨") && !weatherDesc.contains("雪") && !weatherDesc.contains("大雨") && !weatherDesc.contains("中雨") && !weatherDesc.contains("暴雨") && !weatherDesc.contains("雷") && !weatherDesc.contains("阵"))  
	            weatherDesc = "xiaoyu"; 
	        else if (weatherDesc.contains("中雨") && !weatherDesc.contains("雪") && !weatherDesc.contains("大雨") && !weatherDesc.contains("小雨") && !weatherDesc.contains("暴雨") && !weatherDesc.contains("雷"))  
	            weatherDesc = "zhongyu"; 
	        else if (weatherDesc.contains("大雨") && !weatherDesc.contains("雪") && !weatherDesc.contains("小雨") && !weatherDesc.contains("中雨") && !weatherDesc.contains("暴雨") && !weatherDesc.contains("雷"))  
	            weatherDesc = "dayu"; 
	        else if(weatherDesc.contains("雨夹雪"))
				weatherDesc = "yujiaxue";
	        else if(weatherDesc.contains("阵雨"))
				weatherDesc = "leizhenyu";
	        else if(weatherDesc.contains("台风"))
				weatherDesc = "taifeng";
	        else if(weatherDesc.contains("霜"))
				weatherDesc = "shuangdong";
	        else if(weatherDesc.contains("雾"))
				weatherDesc = "wu";
	        else if(weatherDesc.contains("雹"))
				weatherDesc = "bingbao";
	        else if(weatherDesc.contains("霾"))
				weatherDesc = "huimai";
	        else if (weatherDesc.contains("阴") && !weatherDesc.contains("雨") && !weatherDesc.contains("雪"))  
	            weatherDesc = "yin";   
	        else if (weatherDesc.contains("晴") && !weatherDesc.contains("云") && !weatherDesc.contains("阴") && !weatherDesc.contains("雨") && !weatherDesc.contains("雪") && !weatherDesc.contains("雷"))  
	            weatherDesc = "qing";
	        else  
	            weatherDesc = "yin";
			//System.out.println("----------weatherDesc:"+weatherDesc);
			weatherDesc = weatherDesc + WEATHER_IMAGE_FORMAT;
		}
		return weatherDesc;
	}

}
