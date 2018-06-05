package com.biencloud.smarthome.service.push.service;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.push.model.PushFinish;
/**
 * 
 * 类名称：IPushFinishService 
 * 类描述： 推送完成业务接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-9 上午11:27:10
 */
public interface IPushFinishService extends IService<PushFinish, Long>{
	/**
	 * 
	 * 方法的描述:插入已推送内容 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-9 下午1:55:34
	 * @param pushFinish
	 * @return
	 */
		public boolean insertPushFinish(PushFinish pushFinish);
		
		/**
		 * 
		 * 方法的描述: 通过ID查找
		 * @author: kouy  
		 * @version: 0.1
		 * @date: 2012-5-9 上午11:36:10
		 * @param id
		 * @return
		 */
		public PushFinish findById(Long id);
		/**
		 * 
		 * 方法的描述: 根据ID删除
		 * @author: kouy  
		 * @version: 0.1
		 * @date: 2012-5-9 上午11:37:05
		 * @param id
		 * @return
		 */
		public boolean deleteById(Long id);
		/**
		 * 
		 * 方法的描述: 通过实体类进行删除
		 * @author: kouy  
		 * @version: 0.1
		 * @date: 2012-5-9 上午11:37:58
		 * @param pushFinish
		 * @return
		 */
		public boolean deleteByEntity(PushFinish pushFinish);
		/**
		 * 
		 * 方法的描述: 分页处理
		 * @author: kouy  
		 * @version: 0.1
		 * @date: 2012-5-9 下午12:03:27
		 * @param pageNum
		 * @param pageSize
		 * @param condition 条件不包含
		 * @return
		 */
		public Paging<PushFinish> queryPushFinishForPaging(
				 int pageNum, int pageSize,String condition,String orderString);
		/**
		 * 
		 * 方法的描述: 
		 * @author: kouy  
		 * @version: 0.1
		 * @date: 2012-6-13 上午9:07:28
		 * @param pageNum 页码
		 * @param pageSize 每页大小
		 * @param condition 条件
		 * @param orderString 顺序
		 * @param values 条件值
		 * @return
		 */
		public Paging<PushFinish> queryPushFinishForPaging(
				 int pageNum, int pageSize,String condition,String orderString,final Object... values);
		/**
		 * 
		 * 方法的描述: 将待推送记录变成已推送记录
		 * @author: kouy  
		 * @version: 0.1
		 * @date: 2012-9-12 下午4:00:27
		 * @param pushId待推送记录id
		 * @return
		 */
		public boolean savePushFinish(Long pushId);
	
	
}
