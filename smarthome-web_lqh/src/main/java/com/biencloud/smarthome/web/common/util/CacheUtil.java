package com.biencloud.smarthome.web.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.biencloud.smarthome.web.template.VO.ModuleVO;

/**
 * 
 * 类名称：CacheUtil 类描述： 缓存工具类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-10-31 下午3:17:14
 */
public class CacheUtil {
	private static CacheUtil uniqueInstance = null;
	private static Map<String, Object> map = new HashMap<String, Object>();
	private static List<ModuleVO> list = new ArrayList<ModuleVO>();

	private CacheUtil() {

	}

	public static CacheUtil getInstance() {
		if (uniqueInstance == null) {

			uniqueInstance = new CacheUtil();

		}

		return uniqueInstance;

	}

	/**
	 * 
	 * 方法的描述: 判断是否包含key
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-11-6 下午5:20:04
	 * @param key
	 * @return 包含为true,否则为false
	 */
	public boolean isContainKey(String key) {
		boolean flag = false;
		if (map.containsKey(key)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * 方法的描述: 根据key,value保存到缓存中
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-10-31 下午3:18:06
	 * @param key
	 * @param value
	 */
	public void addCache(String key, Object value) {

		map.put(key, value);

	}

	/**
	 * 
	 * 方法的描述: 清空map
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-12-6 上午10:32:15
	 */
	public void clearCache() {
		map.clear();

	}

	/**
	 * 
	 * 方法的描述: 从缓存中取值
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-10-31 下午3:18:41
	 * @param key
	 * @return
	 */
	public Object getValue(String key) {

		Object obj = map.get(key);

		return obj;

	}

	/**
	 * 
	 * 方法的描述: 删除key
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-10-31 下午4:11:57
	 * @param key
	 */
	public void removeKey(String key) {

		if (map.containsKey(key)) {
			map.remove(key);
		}

	}
	
	public void addListObj(ModuleVO module){
		if(list.size() >= Constants.TEMPLATE_COLUMN_NUMBER*Constants.TEMPLATE_ROW_NUMBER){
			list.clear();
		}
		list.add(module);
	}
	
	public void clearList(){
		list.clear();
	}
	
	public List<ModuleVO> getList(){
		if(list.size()>Constants.TEMPLATE_COLUMN_NUMBER*Constants.TEMPLATE_ROW_NUMBER){
			list.clear();
		}
		return list;
	}
	
	//获取组件上部的组件的ID
	public String getTopModuleId(Integer index){
		if(index > Constants.TEMPLATE_COLUMN_NUMBER - 1){
			return getList().get(index-Constants.TEMPLATE_COLUMN_NUMBER).getModuleId();
		}
	return null;
	}
	
	//获取组件左部的组件的ID
		public String getLeftModuleId(Integer index){
			if(index % Constants.TEMPLATE_COLUMN_NUMBER != 0 ){
				return getList().get(index-1).getModuleId();
			}else
				return null;
		}
	
	//测试
	public static void main(String[] args){
		//System.out.println(Constants.TEMPLATE_MODULE_NUMBER+1/2);
	}
}
