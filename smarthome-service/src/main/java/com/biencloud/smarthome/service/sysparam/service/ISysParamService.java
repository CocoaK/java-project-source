package com.biencloud.smarthome.service.sysparam.service;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.sysparam.model.SystemParam;

/**
 * 系统参数模块领域服务接口。
 * @author kouy
 * @since 1.0 2012-4-14
 * @throws RuntimeException 当方法执行发生异常时
 */
public interface ISysParamService extends IService<SystemParam, String> {

	/**
	 * 查询系统参数列表，分页。
	 * @param paramName 参数名称，如果为空不作为查询条件，否则作为模糊查询条件。
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return 分页信息
	 */
	public Paging<SystemParam> querySystemParamsForPaging(
			String paramName, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述:获得文件服务器地址 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-15 上午10:52:52
	 * @return
	 */
	public String getFileServerUrl();
	
	/**
	 * 
	 * 获得从外网访问文件服务器地址 。
	 * @author kouy
	 * @since 1.0 2012-11-6
	 * @return
	 */
	public String getExternalFileServerUrl();
	
	/**
	 * 
	 * 方法的描述: 根据参数代码（Key）查询参数值(Value)
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-1 上午10:24:18
	 * @param paramCode
	 * @return
	 */
	public String queryParamValueByParamCode(String paramCode);
	/**
	 * 
	 * 方法的描述: 更新参数
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-1 上午10:50:52
	 * @param systemParam
	 */
	public void updateSysParam(SystemParam systemParam);
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
