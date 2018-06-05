package com.biencloud.smarthome.service.push.dao;

import java.util.List;

import com.biencloud.smarthome.service.base.dao.IDao;
import com.biencloud.smarthome.service.device.model.Device;
import com.biencloud.smarthome.service.push.model.Push;
/**
 * 
 * 类名称：IPushDao 
 * 类描述： 推送Dao接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-9 上午11:13:00
 */
public interface IPushDao extends IDao<Push, Long>{
	/**
	    * 
	    * 方法的描述:插入推送内容 
	    * @author: kouy  
	    * @version: 0.1
	    * @date: 2012-5-9 上午11:31:22
	    * @param push 推送对象
	    * @return 成功为true,失败为false
	    */
		public boolean insertPush(Push push);
		/**
		 * 
		 * 方法的描述:插入推送内容集合 
		 * @author: kouy  
		 * @version: 0.1
		 * @date: 2012-5-9 上午11:34:14
		 * @param pushList内容集合 
		 * @return 成功为true,失败为false
		 */
		public boolean inserPushCollection(List<Push> pushList);
		/**
		 * 
		 * 方法的描述:推送到终端 
		 * @author: kouy  
		 * @version: 0.1
		 * @date: 2012-8-1 上午11:32:17
		 * @param deviceList
		 */
		public void pushToClients(List<Device> deviceList,Push push);
		
		
	
}
