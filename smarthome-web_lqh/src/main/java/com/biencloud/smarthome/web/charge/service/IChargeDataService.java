package com.biencloud.smarthome.web.charge.service;

import java.util.List;

import com.biencloud.smarthome.web.charge.vo.ChargeDataVO;
import com.biencloud.smarthome.web.charge.vo.ChargeStatiticsVO;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.wsclient.stub.ChargeData;

/**
 * 
 * 项目名称：smarthome-service-new 
 * 类名称：IChargeDataService 
 * 类描述： 收费数据管理领域服务接口
 * @author: kouy 
 * @version: 0.1
 * @date: 2012-6-12 上午10:10:42
 */
public interface IChargeDataService{

	/**
	 * 
	 * 方法的描述: 查询收费数据列表(分页）
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:11:13
	 * @param paramsOb：收费数据对象
	 * @param pageNum：页码
	 * @param pageSize：显示条数
	 * @return
	 */
	public PagingVO<ChargeDataVO> queryChargeDataVOForPaging(ChargeDataVO paramsOb, int pageNum, int pageSize);
	/**
	 * 
	 * 方法的描述: 更新收费数据
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:11:58
	 * @param entity:收费数据对象
	 */
	public boolean updateChargeDataVO(ChargeDataVO entity) ;
	/**
	 * 
	 * 方法的描述: 保存收费数据
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:12:11
	 * @param entity:收费数据对象
	 */
	public boolean saveChargeDataVO(ChargeDataVO entity);
	/**
	 * 
	 * 方法的描述: 获取单个收费数据对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:12:29
	 * @param entityId:收费数据对象主键
	 * @return
	 */
	public ChargeDataVO getChargeDataVO(String entityId);
	/**
	 * 
	 * 方法的描述: 删除收费数据对象
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:12:59
	 * @param entity:收费数据对象
	 */
	public boolean delChargeDataVO(String  id);
	/**
	 * 
	 * 方法的描述: 查询收费项目列表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:10:53
	 * @param paramsOb:收费数据对象
	 * @return
	 */
	public List<ChargeDataVO> queryChargeDataForList(ChargeDataVO paramsOb);
	/**
	 * 
	 * 方法的描述: 实体类转换为VO类
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:58:16
	 * @param ob:实体类
	 * @param vo:VO类
	 */
	public void copyPropertiesObToVo(ChargeData ob ,ChargeDataVO vo);
	/**
	 * 
	 * 方法的描述: VO类转换为实体类
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:58:46
	 * @param vo：VO类
	 * @param ob：实体类
	 */
	public void copyPropertiesVoToOb(ChargeDataVO vo ,ChargeData ob);
	/**
	 * 
	 * 方法的描述: 统计收费报表
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-6-12 上午10:13:13
	 * @param ob：:收费数据对象
	 * @return
	 */
	public List<ChargeStatiticsVO> statisticsCharge(ChargeDataVO paramsOb);
	/**
	 * 
	 * 方法的描述: 判断指定业主指定月份是否已存在收费数据
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-14 上午10:08:05
	 * @param houseId
	 * @param chargeTime
	 * @return
	 */
	public boolean isExistForChargeTimeByUserId(String houseId,String chargeTime) ;
}
