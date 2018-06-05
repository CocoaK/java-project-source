package com.biencloud.smarthome.web.hdfstask.service;

import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.hdfstask.vo.HdfsTaskVO;


/**
 * 
 * 类名称：IHDFSTaskService 
 * 类描述： 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-5-12 上午9:28:17
 */
public interface IHDFSTaskService {	
	/**
	 * 
	 * 方法的描述:  分页处理
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-8 上午11:31:37
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PagingVO<HdfsTaskVO> queryHDFSTaskForPaging(int pageNum, int pageSize,HdfsTaskVO hdfsTaskVO);
	/**
	 * 
	 * 方法的描述:  分页处理
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-8 上午11:31:37
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PagingVO<HdfsTaskVO> queryHDFSTaskForPaging(int pageNum, int pageSize,String condition,String orderString,String orderBy,List<Object> conditionValue);
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
	public PagingVO<HdfsTaskVO> query(
			 int pageNum, int pageSize,HdfsTaskVO hdfsTaskVO);
	/**
	 * 
	 * 方法的描述: 根据id查询
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-9 下午3:02:41
	 * @param id
	 * @return
	 */
	
	public HdfsTaskVO getHdfsTaskById(Long id);
	
	
}
