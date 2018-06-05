package com.smarthome.socket.service.business.service.push;

import java.util.List;
import java.util.Map;

import com.smarthome.socket.service.vo.PushVO;
/**
 * 
 * 类名称：IPushService 
 * 类描述：推送接口 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-9 下午3:43:34
 */
public interface IPushService {
	/**
	 * 
	 * 方法的描述:按条件查询
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 下午2:01:27
	 * @param condition
	 * @return
	 */
	
	public List<PushVO> listPush(String whereCondition);
	/**
	 * 
	 * 方法的描述: 根据ID查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-11 上午11:09:13
	 * @param ids
	 * @return
	 */
	public List<PushVO> listPushByClientID(String ids);
	/**
	 * 
	 * 方法的描述: 批量查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-9-4 下午9:05:51
	 * @param ids
	 * @return
	 */
	public Map<String,List<PushVO>> listPushByClientIDForMap(String ids,String pushKinds) throws Exception;

	/**
	 * 
	 * 方法的描述: 根据ID删除
	 * 
	 * @author: kouy
	 * @version: 0.1
	 * @date: 2012-5-9 上午11:37:05
	 * @param id
	 * @return
	 */
	
	public boolean deleteById(Long id);
	/**
	 * 
	 * 方法的描述: 根据ID查找
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-10 下午10:07:14
	 * @param id
	 * @return
	 */
	public PushVO findById(Long id);
	 /**
		 * 
		 * 方法的描述: 查询定时器时间设置更新
		 * @author: kouy  
		 * @version: 0.1
		 * @date: 2012-12-11 下午3:36:52
		 */	
		public String queryCronTimeUpdate();

	

	
}
