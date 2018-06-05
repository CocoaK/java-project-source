package com.biencloud.smarthome.service.requestrepair.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.requestrepair.model.RequestRepair;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IRequestRepairService 
 * 类描述： 报修管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 下午2:20:09
 */
public interface IRequestRepairService extends IService<RequestRepair, Long> {
	/**
	 * 
	 * 方法的描述:  查询报修列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:20:18
	 * @param paramsOb
	 * @return
	 */
	public List<RequestRepair> queryRequestRepairForList(RequestRepair paramsOb);
	/**
	 * 
	 * 方法的描述: 查询报修列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:20:25
	 * @param paramsOb: 报修对象
	 * @param pageNum:页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public Paging<RequestRepair> queryRequestRepairForPaging(RequestRepair paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新报修
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:20:46
	 * @param entity：报修对象
	 */
	public void updateRequestRepair(RequestRepair entity) ;
	/**
	 * 
	 * 方法的描述: 保存报修
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:20:57
	 * @param entity：报修对象
	 */
	public void saveRequestRepair(RequestRepair entity);
	/**
	 * 
	 * 方法的描述: 获取单个报修对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:21:08
	 * @param entityId：报修对象主键
	 * @return
	 */
	public RequestRepair getRequestRepair(String entityId);
	/**
	 * 
	 * 方法的描述: 删除报修对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:21:22
	 * @param entity：报修对象
	 */
	public void DelRequestRepair(RequestRepair entity);
	/**
	 * 
	 * 方法的描述: 查询报修数量
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 下午2:21:37
	 * @param paramsOb:报修对象
	 * @return
	 */
	public Long getRequestRepairCount(RequestRepair paramsOb) ;
	/**
	 * 
	 * 方法的描述:  判断报修数据表中指定的房间ID是否有记录
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-8-13 下午2:59:47
	 * @param houseId
	 * @return
	 */
	public boolean isExistForHouseHold(String houseId);
}
