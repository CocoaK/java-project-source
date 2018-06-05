package com.biencloud.smarthome.web.charge.service;

import java.util.List;

import com.biencloud.smarthome.web.charge.vo.ChargeMonetaryUnitVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IChargeMonetaryUnitService 
 * 类描述： 收费货币单位管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:16:40
 */
public interface IChargeMonetaryUnitService{

	/**
	 * 
	 * 方法的描述: 查询收费货币单位列表（分页）
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:17:10
	 * @param paramsOb：收费货币单位对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public PagingVO<ChargeMonetaryUnitVO> queryChargeMonetaryUnitVOForPaging(ChargeMonetaryUnitVO paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新收费货币单位
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:19:01
	 * @param entity：收费货币单位对象
	 */
	public boolean updateChargeMonetaryUnitVO(ChargeMonetaryUnitVO entity) ;
	/**
	 * 
	 * 方法的描述: 保存收费货币单位
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:19:19
	 * @param entity：收费货币单位对象
	 */
	public boolean saveChargeMonetaryUnitVO(ChargeMonetaryUnitVO entity);
	/**
	 * 
	 * 方法的描述: 获取单个收费货币单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:19:30
	 * @param entityId：收费货币单位对象主键
	 * @return
	 */
	public ChargeMonetaryUnitVO getChargeMonetaryUnitVO(String entityId);
	/**
	 * 
	 * 方法的描述: 删除收费货币单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:19:54
	 * @param entity：收费货币单位对象
	 */
	public boolean delChargeMonetaryUnitVO(String  id);
	/**
	 * 
	 * 方法的描述: 查询收费项目列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:16:53
	 * @param paramsOb：收费货币单位对象
	 * @return
	 */
	public List<ChargeMonetaryUnitVO> queryChargeMonetaryUnitForList(ChargeMonetaryUnitVO paramsOb);
	/**
	 * 
	 * 方法的描述: 根据条件查询货币单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-4 下午4:12:47
	 * @param paramsOb：收费货币单位对象
	 * @return
	 */
	public ChargeMonetaryUnitVO queryChargeMonetaryUnitByParams(ChargeMonetaryUnitVO paramsOb);
}
