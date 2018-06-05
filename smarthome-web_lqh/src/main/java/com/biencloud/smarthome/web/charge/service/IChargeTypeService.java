package com.biencloud.smarthome.web.charge.service;

import java.util.List;

import com.biencloud.smarthome.web.charge.vo.ChargeTypeVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IChargeTypeService 
 * 类描述： 收费项目管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:23:22
 */
public interface IChargeTypeService{

	/**
	 * 
	 * 方法的描述: 查询收费项目列表（分页）
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:23:44
	 * @param paramsOb：收费项目对象
	 * @param pageNum：页码	
	 * @param pageSize：显示条数
	 * @return
	 */
	public PagingVO<ChargeTypeVO> queryChargeTypeVOForPaging(ChargeTypeVO paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新收费项目
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:24:52
	 * @param entity：收费项目对象
	 */
	public boolean updateChargeTypeVO(ChargeTypeVO entity) ;
	/**
	 * 
	 * 方法的描述: 保存收费项目
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:25:03
	 * @param entity：收费项目对象
	 */
	public boolean saveChargeTypeVO(ChargeTypeVO entity);
	/**
	 * 
	 * 方法的描述:获取单个收费项目对象 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:25:14
	 * @param entityId：收费项目对象 主键
	 * @return
	 */
	public ChargeTypeVO getChargeTypeVO(String entityId);
	/**
	 * 
	 * 方法的描述: 删除收费项目对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:25:37
	 * @param entity：收费项目对象
	 */
	public boolean delChargeTypeVO(String  id);
	/**
	 * 
	 * 方法的描述: 查询收费项目列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:23:32
	 * @param paramsOb：收费项目对象
	 * @return
	 */
	public List<ChargeTypeVO> queryChargeTypeForList(ChargeTypeVO paramsOb);
	/**
	 * 
	 * 方法的描述: 根据参数查询返回收费项目对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-2 上午10:33:47
	 * @param paramsOb：收费项目对象
	 * @return
	 */
	public ChargeTypeVO queryChargeTypeByParams(ChargeTypeVO paramsOb,String comId);
}
