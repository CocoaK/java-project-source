package com.biencloud.smarthome.web.sysparam.service;


import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.sysparam.vo.SystemParamVO;

/**
 * 系统参数模块调用服务接口。
 * @author kouy
 * @since 1.0 2012-4-16
 * @throws Exception 当方法执行发生异常时
 */
public interface ISysParamService {

	/**
	 * 查询系统参数列表，分页。
	 * @param paramName 参数名称，如果为空不作为查询条件，否则作为模糊查询条件。
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagingVO<SystemParamVO> querySystemParams(
			String paramName, int pageNum, int pageSize) throws Exception;
	
	/**
	 * 通过参数代码获取系统参数详细信息，获取不到返回Null。
	 * @param paramCode 参数代码
	 * @return 系统参数值对象
	 */
	public SystemParamVO getSystemParamDetail(String paramCode) throws Exception;
	
	/**
	 * 更新系统参数信息。
	 * @param systemParamVO 系统参数值对象
	 */
	public void updateSystemParam(SystemParamVO systemParamVO) throws Exception;
	/**
	 * 
	 * 方法的描述: 获得文件服务器地址
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-15 上午11:04:03
	 * @return 文件服务器地址:如http://127.0.0.1:8080/fileserver
	 */
	public String getFileServerUrl();
	
	/**
	 * 
	 * 方法的描述: 获得参数值
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-5 下午8:46:06
	 * @param paramCode
	 * @return
	 * @throws Exception
	 */
	public String getParamValue(String paramCode) throws Exception;
	/**
	 * 
	 * 方法的描述: app下载绝对地址
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-8 下午5:06:43
	 * @return
	 */
	public String getAppDownloadAbsoluteUrl();
	
	/**
	 * 
	 * 方法的描述: web下载绝对地址
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-11-8 下午5:06:43
	 * @return
	 */
	public String getWebDownloadAbsoluteUrl();
}
