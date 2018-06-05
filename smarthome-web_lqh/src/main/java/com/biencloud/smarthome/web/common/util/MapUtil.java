package com.biencloud.smarthome.web.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * 
 * 类名称：MapUtil 类描述： map工具类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-6-13 上午11:55:51
 */
public class MapUtil {

	private static Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * 
	 * 方法的描述: 清空Map
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-13 上午11:54:30
	 */
	public static void clearMap() {
		map.clear();
	}

	/**
	 * 
	 * 方法的描述: 在map中添加key,value值
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-13 上午11:43:34
	 * @param key
	 * @param value
	 */
	public static void addKeyValueToMap(String key, Object value) {
		map.put(key, value);
	}

	/**
	 * 
	 * 方法的描述:
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-13 上午11:53:20
	 * @return
	 */
	public static String covertMapKeyToCondition() {
		String condition = null;
		Set<String> keySet = map.keySet();
		if (keySet != null && !keySet.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (String key : keySet) {
				if (key != null) {
					sb.append(" ");
					sb.append(key);
					if(key.contains("like"))
					{
						sb.append(" ");
						sb.append("?");
					}else
					{
						sb.append("=?");
					}				
					sb.append(" ");
					sb.append("and");
				}
			}
			condition = sb.toString();
			if (condition.endsWith("and")) {
				int index = sb.lastIndexOf("and");
				condition = condition.substring(0, index);
			}
		}

		return condition;
	}

	/**
	 * 
	 * 方法的描述: 将map中所有的值转为对象集合
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-13 上午11:54:51
	 * @return
	 */
	public static List<Object> covertMapValueToObjectList() {
		List<Object> list = new ArrayList<Object>();
		Collection<Object> valueSet = map.values();
		//Iterator it=collect.iterator();
		
		if (valueSet != null && !valueSet.isEmpty()) {

			for (Object o : valueSet) {
				if (o != null) {
					list.add(o);
				}
			}
		}
		return list;
	}

	/**
	 * 
	 * 方法的描述: 将指定对象属性名称和属性值转化为Map键值对
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-13 上午11:57:41
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static HashMap objectToMap(Object obj) throws Exception {
		if (obj == null) {
			throw new Exception("对象为空");
		}

		Class clazz = obj.getClass();
		HashMap map = new HashMap();
		getClass(clazz, map, obj);
		HashMap newMap = convertHashMap(map);
		return newMap;
	}

	/**
	 * 
	 * 方法的描述: map转换
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-13 上午11:58:17
	 * @param map
	 * @return
	 * @throws Exception
	 */
	private static HashMap convertHashMap(HashMap map) throws Exception {

		HashMap newMap = new HashMap();
		Set keys = map.keySet();
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			Object key = it.next();
			convertToString(map.get(key), newMap, key);
		}

		return newMap;
	}

	/**
	 * 
	 * 方法的描述:根据类及属性类型转为map
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-13 上午11:58:08
	 * @param clazz
	 * @param map
	 * @param obj
	 * @throws Exception
	 */
	private static void getClass(Class clazz, HashMap map, Object obj) throws Exception {
		if (clazz.getSimpleName().equals("Object")) {
			return;
		}

		Field[] fields = clazz.getDeclaredFields();
		if (fields == null || fields.length <= 0) {
			throw new Exception("当前对象中没有任何属性值");
		}
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			String name = fields[i].getName();
			Object value = fields[i].get(obj);
			map.put(name, value);

		}
		Class superClzz = clazz.getSuperclass();
		getClass(superClzz, map, obj);
	}

	/**
	 * 
	 * 方法的描述: 将key,value对象加入map
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-13 下午12:00:09
	 * @param value
	 * @param newMap
	 * @param key
	 */
	private static void convertToString(Object value, HashMap<Object, Object> newMap, Object key) {
		if (value != null) {
			Class clazz = value.getClass();
			if (isBaseType(clazz)) {
				newMap.put(key, value.toString());
			} else if (clazz == String.class) {
				newMap.put(key, value.toString());
			} else if (clazz == Date.class) {
				Date date = (Date) value;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				newMap.put(key, sdf.format(date));
			} else if (clazz == Timestamp.class) {
				Timestamp timestamp = (Timestamp) value;
				long times = timestamp.getTime();
				Date date = new Date(times);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				newMap.put(key, sdf.format(date));
			} else if (clazz == java.sql.Date.class) {
				java.sql.Date sqlDate = (java.sql.Date) value;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				newMap.put(key, sdf.format(sqlDate));
			} else {
				newMap.put(key, value);
			}
		} else {
			newMap.put(key, value);
		}

	}

	/**
	 * 
	 * 方法的描述: 判断是否基本类型
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-13 上午11:58:53
	 * @param clazz
	 * @return
	 */
	private static boolean isBaseType(Class clazz) {

		if (clazz == Integer.class) {
			return true;
		}
		if (clazz == Long.class) {
			return true;
		}
		if (clazz == Double.class) {
			return true;
		}
		if (clazz == Byte.class) {
			return true;
		}
		if (clazz == Float.class) {
			return true;
		}
		if (clazz == Short.class) {
			return true;
		}
		if (clazz == Boolean.class) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 方法的描述: 测试
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-6-13 上午11:59:12
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		System.out.println("132131");
		// CustomerSendForm user = new CustomerSendForm();
		// user.setAge(1);
		// user.setId("1");
		// user.setName("qqqq");
		// user.setBirthday(new Date());
		// user.setFriedns(new ArrayList());
		// user.setHobbies(new String[]{"1","2"});
		HashMap map = objectToMap(null);
		Set entrys = map.entrySet();
		Iterator it = entrys.iterator();
		System.out.println("sfsdfsdf");
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

}
