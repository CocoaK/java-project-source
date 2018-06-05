package com.quhwa.scalehouse.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.quhwa.scalehouse.service.scale.model.Pressure;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

/**
 * json解析类
 *
 */
public class JsonUtils {
	private JsonUtils(){}

	
	/**
	 * 解析json纯数组
	 * [
	 * 	{"id":1,"type":2,"name":"lxz"},
	 *  {"id":2,"type":1,"name":"zhang"}
	 * ]
	 * @param json
	 * @param cls
	 * @param <T>
     * @return List集合
     */
	static{
		String[] dateFormats = new String[] {"yyyy-MM-dd HH:mm:ss"};
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));//日期格式化
	}
	@SuppressWarnings("unchecked")
	public static <T> List<T> getObjectList(String json,Class<T> cls){ 
		{
			String[] dateFormats = new String[] {"yyyy-MM-dd HH:mm:ss"};
			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));//日期格式化
		}
//        List<T> list = new ArrayList<T>(); 
        JSONArray jsonArray = JSONArray.fromObject(json); // 首先把字符串转成 JSONArray  对象
        //System.out.println(jsonArray);
        List<T> list = JSONArray.toList(jsonArray, cls);
        return list;
    } 
	
	public static <T> List<T> getObject(String json,Class<T> cls){ 
	  List<T> list = new ArrayList<T>();  
	  try {
		  Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm").create();
//		  GsonBuilder builder = new GsonBuilder(); // Register an adapter to manage the date types as long values 
//		  builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
//			@Override
//			public Date deserialize(JsonElement json, Type arg1,
//					JsonDeserializationContext arg2)
//					throws JsonParseException {
//				// TODO Auto-generated method stub
//				return new Date(json.getAsJsonPrimitive().getAsLong());
//			} 
//			}); 
//		  Gson gson = builder.create();
		  JsonArray arry = new JsonParser().parse(json).getAsJsonArray();  
		  for (JsonElement jsonElement : arry) {  
		  	list.add(gson.fromJson(jsonElement, cls));
		  }
	  } catch (Exception e) {  
	      e.printStackTrace();  
	  }  
	  return list;
	} 
	
	public static <T> T getJsonObject(String json, Class<T> cls) {
		T t = null;
		try {
			t = new Gson().fromJson(json, cls);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return t;
	}
	
	public static void main(String[] args) throws IOException {
//        String jsonString = "{\"id\":\"1\",\"name\":\"bigbeef\",\"list\":[{\"str\":\"str1\",\"integer\":\"1\"}," +
//                "{\"str\":\"str2\",\"integer\":\"2\"}]}";
		
		String jsonStr = "[{\"note\":\"pressuretest\",\"diastolic\":\"90.5\",\"recordTime\":\"1526615149000\",\"systolic\":\"105.6\",\"pulse\":\"86\"},{\"note\":\"pressuretest\",\"recordTime\":\"1526615149000\",\"diastolic\":\"90.5\",\"systolic\":\"105.6\",\"pulse\":\"86\"},{\"note\":\"pressuretest\",\"recordTime\":\"1526615149000\",\"diastolic\":\"90.5\",\"systolic\":\"105.6\",\"pulse\":\"86\"}]";
//		String jsonStr = "{\"note\":\"pressuretest\",\"diastolic\":\"90.5\",\"recordTime\":\"05/05/2018 10:47\",\"systolic\":\"105.6\",\"pulse\":\"86\"}";		
//        JSONObject obj = new JSONObject().fromObject(jsonStr);//将json字符串转换为json对象
//        //将json对象转换为java对象
//        Pressure jb = (Pressure)JSONObject.toBean(obj,Pressure.class);//将建json对象转换为Person对象
		List<Pressure> list = JsonUtils.getObject(jsonStr, Pressure.class);
        System.out.println("----:"+list.toString());
    }
	
}






