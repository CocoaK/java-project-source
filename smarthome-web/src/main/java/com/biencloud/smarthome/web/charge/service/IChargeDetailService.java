package com.biencloud.smarthome.web.charge.service;

import java.util.List;

import com.biencloud.smarthome.web.charge.vo.ChargeDetailVO;
import com.biencloud.smarthome.web.charge.vo.ChargeStatiticsVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.push.vo.PushVO;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IChargeDetailService 
 * 类描述： 收费清单管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:13:34
 */
public interface IChargeDetailService{

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
	public PagingVO<ChargeDetailVO> queryChargeDetailVOForPaging(ChargeDetailVO paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新收费清单
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:14:33
	 * @param entity： 收费清单对象
	 */
	public boolean updateChargeDetailVO(String id) ;
	/**
	 * 
	 * 方法的描述: 保存收费清单
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:14:51
	 * @param entity：收费清单对象
	 */
	public boolean saveChargeDetailVO(ChargeDetailVO entity);
	/**
	 * 
	 * 方法的描述: 获取单个收费清单对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:15:02
	 * @param entityId：收费清单对象主键
	 * @return
	 */
	public ChargeDetailVO getChargeDetailVO(String entityId);
	/**
	 * 
	 * 方法的描述: 删除收费清单对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:15:29
	 * @param entity：收费清单对象
	 */
	public boolean delChargeDetailVO(String  id);
	/**
	 * 
	 * 方法的描述: 查询收费项目列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:13:46
	 * @param paramsOb：收费项目对象
	 * @return
	 */
	public List<ChargeDetailVO> queryChargeDetailForList(ChargeDetailVO paramsOb);
	/**
	 * 
	 * 方法的描述: 业主查询最近缴费
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:56:51
	 * @param loginUserType：登录用户类型
	 * @param userId：登录用户ID
	 * @param districtId：小区ID
	 * @return
	 */
	public List<ChargeDetailVO> queryChargeDetailVOForIndex(String loginUserType,String userId,String districtId);
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
	public boolean publishChargeInfo(PushVO pushOb,String roomId,String id);
	/**
	 * 
	 * 方法的描述: 查询小区已收费总额/查询小区应收费总额
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:59:10
	 * @param loginUserType：登录用户类型
	 * @param userId：登录用户ID
	 * @param districtId：小区ID
	 * @return
	 */
	public ChargeStatiticsVO statisticsChargeForIndex(String loginUserType,String userId,String districtId);
}
