package com.smarthome.socket.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 类名称：JsonUtil 类描述： json工具类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-9 下午7:38:51
 */
public class JsonUtil {

	/**
	 * 方法的描述:
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-4 下午8:35:45
	 * @param args
	 */
	public static void main(String[] args) {
		// String json = "{\"address\":\"chian\"," +
		// "\"email\":\"email@123.com\",\"id\":22,\"name\":\"tom\"}";
		String json = "{\"jsonType\":\"sceneDeviceData\",\"sceneId\":1,\"sceneName\":\"xxx\",\"imageName\":\"sss.jpg\",\"deviceNo\":\"x01\",\"SceneDevice\":[{\"deviceName\":\"aaa\",\"position\":\"23-13\",\"status\":\"0\",\"positionName\":\"room1\",\"kind\":\"lamp\"},{\"deviceName\":\"bbb\",\"position\":\"33-13\",\"status\":\"1\",\"positionName\":\"room2\",\"kind\":\"lamp\"}]}";
		// Scene scene=new Scene();
		// String json =
		// "{\"jsonType\":\"sceneDeviceData\",\"sceneId\":1,\"sceneName\":\"xxx\",\"imageName\":\"sss.jpg\",\"deviceNo\":\"x01\",\"SceneDevice\":{{\"deviceName\":\"bbb\",\"position\":\"33,13\",\"status\":\"1\",\"positionName\":\"room2\",\"kind\":\"lamp\"}}}";
		JSONArray ja = jsonStringToJsonObject(json).getJSONArray("SceneDevice");
		System.out.println(getDataFromJsonObject(jsonStringToJsonObject(ja.get(0).toString()), "deviceName"));

		// System.out.println(getDataFromJsonObject(jsonStringToJsonObject(json),"SceneDevice[0]"));
		// System.out.println(objectToJsonString(scene));
	}

	/**
	 * 
	 * 方法的描述: 将json对象转为Json对象
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-4 下午8:38:05
	 * @param jsonString
	 *            json字符串
	 * @return
	 */
	public static JSONObject jsonStringToJsonObject(String jsonString) {
		// JSON格式数据解析对象
		JSONObject jb = JSONObject.fromObject(jsonString);
		return jb;
	}

	/**
	 * 
	 * 方法的描述: 解析出json数据
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-4 下午8:41:56
	 * @param jb
	 * @param key
	 * @return
	 */
	public static String getDataFromJsonObject(JSONObject jb, String key) {
		String value = null;
		try {
			value = jb.getString(key);
			if (value != null && !"".equals(value.trim())) {
				value = value.trim();
			} else {
				value = null;
			}
		} catch (Exception e) {

		}
		return value;
	}

	/**
	 * 
	 * 方法的描述: 将对象转为json字符串
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-4 下午8:49:21
	 * @param o
	 *            对象
	 * @return
	 */
	public static String objectToJsonString(Object o) {
		return JSONObject.fromObject(o).toString();
	}

	public static String objectToJsonString(Object o, String deleteKey) {
		return (JSONObject.fromObject(o).remove(deleteKey)).toString();
	}

}
