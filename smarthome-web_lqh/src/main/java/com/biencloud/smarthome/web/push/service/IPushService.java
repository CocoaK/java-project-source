package com.biencloud.smarthome.web.push.service;
import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.push.vo.PushVO;

/**
 * 
 * 类名称：IPushService 
 * 类描述： 数据推送接口
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-11 下午3:32:55
 */
public interface IPushService {
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
	public PagingVO<PushVO> queryPushForPaging(
			 int pageNum, int pageSize,PushVO pushVO) throws Exception;
	/**
	 * 
	 * 方法的描述:分页处理 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-13 上午10:45:51
	 * @param pageNum 页号
	 * @param pageSize 分页大小
	 * @param condition 条件
	 * @param orderString 排序
	 * @param orderBy 排序值
	 * @param list 条件值
	 * @return
	 */
	public PagingVO<PushVO> queryPushForPaging(
			 int pageNum, int pageSize,String condition,String orderString,String orderBy,List<Object> list) throws Exception;
	/**
	 * 
	 * 方法的描述: 条件查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-12 下午3:34:08
	 * @param pageNum
	 * @param pageSize
	 * @param pushVO
	 * @return
	 */
	public PagingVO<PushVO> query(
			 int pageNum, int pageSize,PushVO pushVO) throws Exception;
	/**
	 * 
	 * 方法的描述: 重推
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-5-17 下午4:20:01
	 * @param id
	 */
	public void repush(Long id)throws Exception;
	/**
	 * 
	 * 方法的描述: 根据id查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-5 下午7:48:58
	 * @param id
	 * @return
	 */
	public PushVO queryById(Long id)throws Exception;
}
