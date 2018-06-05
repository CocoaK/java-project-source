package com.biencloud.smarthome.web.charge.service;

import java.util.List;

import com.biencloud.smarthome.web.charge.vo.ChargeTypeResultVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IChargeTypeResultService 
 * 类描述： 收费项目结果管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:20:18
 */
public interface IChargeTypeResultService{

	/**
	 * 
	 * 方法的描述:  查询收费项目结果列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:20:55
	 * @param paramsOb：收费项目结果对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public PagingVO<ChargeTypeResultVO> queryChargeTypeResultVOForPaging(ChargeTypeResultVO paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述:  更新收费项目结果
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:22:09
	 * @param entity：收费项目结果对象
	 */
	public boolean updateChargeTypeResultVO(ChargeTypeResultVO entity) ;
	/**
	 * 
	 * 方法的描述: 保存收费项目结果
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:22:25
	 * @param entity：收费项目结果对象
	 */
	public boolean saveChargeTypeResultVO(ChargeTypeResultVO entity);
	/**
	 * 
	 * 方法的描述: 获取收费项目结果对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:22:38
	 * @param entityId：收费项目结果对象主键
	 * @return
	 */
	public ChargeTypeResultVO getChargeTypeResultVO(String entityId);
	/**
	 * 
	 * 方法的描述: 删除收费项目结果对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:23:06
	 * @param entity：收费项目结果对象
	 */
	public boolean delChargeTypeResultVO(String  id);
	/**
	 * 
	 * 方法的描述: 查询收费项目列表（分页）
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:20:28
	 * @param paramsOb：收费项目对象
	 * @return
	 */
	public List<ChargeTypeResultVO> queryChargeTypeResultForList(ChargeTypeResultVO paramsOb);
}
