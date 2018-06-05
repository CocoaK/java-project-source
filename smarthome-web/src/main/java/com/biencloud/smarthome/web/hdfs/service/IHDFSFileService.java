package com.biencloud.smarthome.web.hdfs.service;

import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.hdfs.vo.LocalHdfsVO;

/**
 * 
 * 类名称：IHDFSFileService 类描述：
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-12 上午9:27:32
 */
public interface IHDFSFileService {
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
	public PagingVO<LocalHdfsVO> queryHDFSFileForPaging(int pageNum, int pageSize,LocalHdfsVO localHdfsVO);
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
	public PagingVO<LocalHdfsVO> queryHDFSFileForPaging(int pageNum, int pageSize,String condition,String orderString,String orderBy,List<Object> conditionValue);
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
	public PagingVO<LocalHdfsVO> query(
			 int pageNum, int pageSize,LocalHdfsVO localHdfsVO);
	/**
	 * 
	 * 方法的描述: 通过id查询详细
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-9 上午11:42:47
	 * @param id
	 * @return
	 */
	public LocalHdfsVO getLocalHdfsById(Long id);
}
