package com.biencloud.smarthome.service.charge.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.charge.model.ChargeDetail;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.push.model.Push;
/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IChargeDetailService 
 * 类描述： 收费清单管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:13:34
 */
public interface IChargeDetailService extends IService<ChargeDetail, Long> {
	/**
	 * 
	 * 方法的描述: 查询收费项目列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:13:46
	 * @param paramsOb：收费项目对象
	 * @return
	 */
	public List<ChargeDetail> queryChargeDetailForList(ChargeDetail paramsOb);
	/**
	 * 
	 * 方法的描述: 查询收费清单列表（分页）
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:13:58
	 * @param paramsOb：收费清单对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public Paging<ChargeDetail> queryChargeDetailForPaging(ChargeDetail paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新收费清单
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:14:33
	 * @param entity： 收费清单对象
	 */
	public void updateChargeDetail(ChargeDetail entity) ;
	/**
	 * 
	 * 方法的描述: 保存收费清单
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:14:51
	 * @param entity：收费清单对象
	 */
	public void saveChargeDetail(ChargeDetail entity);
	/**
	 * 
	 * 方法的描述: 获取单个收费清单对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:15:02
	 * @param entityId：收费清单对象主键
	 * @return
	 */
	public ChargeDetail getChargeDetail(String entityId);
	/**
	 * 
	 * 方法的描述: 删除收费清单对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:15:29
	 * @param entity：收费清单对象
	 */
	public void DelChargeDetail(ChargeDetail entity);
	/**
	 * 
	 * 方法的描述: 缴费通知
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:15:45
	 * @param pushOb：推送对象
	 * @param roomId：房间ID
	 * @return
	 */
	public boolean publishChargeInfo(Push pushOb,String roomId);
}
