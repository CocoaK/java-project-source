package com.biencloud.smarthome.web.push.service;

import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.push.vo.PushFinishVO;



/**
 * 
 * 类名称：IPushFinishService 
 * 类描述： 数据推送完成接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-11 下午3:34:02
 */
public interface IPushFinishService {
	/**
	 * 
	 * 方法的描述: 分页处理
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-9 下午12:03:27
	 * @param pageNum
	 * @param pageSize
	 * @param condition 条件
	 * @return
	 */
	public PagingVO<PushFinishVO> queryPushFinishForPaging(
			 int pageNum, int pageSize,PushFinishVO pushFinishVO);
	/**
	 * 
	 * 方法的描述: 分页处理
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-9 下午12:03:27
	 * @param pageNum
	 * @param pageSize
	 * @param condition 条件
	 * @return
	 */
	public PagingVO<PushFinishVO> queryPushFinishForPaging(
			 int pageNum, int pageSize,String condition,String orderString,String orderBy,List<Object> list);
	/**
	 * 
	 * 方法的描述: 查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-14 上午11:48:36
	 * @param pageNum
	 * @param pageSize
	 * @param pushFinishVO
	 * @return
	 */
	public PagingVO<PushFinishVO> query(
			 int pageNum, int pageSize,PushFinishVO pushFinishVO);
	/**
	 * 
	 * 方法的描述: 根据id查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-17 下午4:04:06
	 * @param id
	 * @return
	 */
	public PushFinishVO findById(Long id);
}
