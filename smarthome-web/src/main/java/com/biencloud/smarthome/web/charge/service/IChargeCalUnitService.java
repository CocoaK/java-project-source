package com.biencloud.smarthome.web.charge.service;

import java.util.List;

import com.biencloud.smarthome.web.charge.vo.ChargeCalUnitVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IChargeCalUnitService 
 * 类描述： 收费计算单位管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:07:55
 */
public interface IChargeCalUnitService {

	/**
	 * 
	 * 方法的描述: 查询收费计算单位列表(分页)
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:02:17
	 * @param paramsOb:收费计算单位对象
	 * @param pageNum:页码
	 * @param pageSize:显示条数
	 * @return
	 */
	public PagingVO<ChargeCalUnitVO> queryChargeCalUnitVOForPaging(ChargeCalUnitVO paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新收费计算单位
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:04:05
	 * @param entity:收费计算单位对象
	 */
	public boolean updateChargeCalUnitVO(ChargeCalUnitVO entity) ;
	/**
	 * 
	 * 方法的描述: 保存收费计算单位
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:04:41
	 * @param entity:收费计算单位对象
	 */
	public boolean saveChargeCalUnitVO(ChargeCalUnitVO entity);
	/**
	 * 
	 * 方法的描述: 获取单个收费计算单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:05:00
	 * @param entityId:收费计算单位主键
	 * @return
	 */
	public ChargeCalUnitVO getChargeCalUnitVO(String entityId);
	/**
	 * 
	 * 方法的描述: 删除收费计算单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:07:04
	 * @param entity:收费计算单位对象
	 */
	public boolean delChargeCalUnitVO(String  id);
	/**
	 * 
	 * 方法的描述: 查询收费项目列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午9:57:07
	 * @param paramsOb:收费项目对象
	 * @return
	 */
	public List<ChargeCalUnitVO> queryChargeCalUnitForList(ChargeCalUnitVO paramsOb);
	/**
	 * 
	 * 方法的描述: 根据条件查询收费计算单位对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-4 下午4:12:47
	 * @param paramsOb：收费计算单位对象
	 * @return
	 */
	public ChargeCalUnitVO queryChargeCalUnitByParams(ChargeCalUnitVO paramsOb);
}
