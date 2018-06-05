package com.biencloud.smarthome.web.gate.service;

import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.gate.vo.IdCardVisitVO;

/**
 * 身份证刷卡管理调用服务接口。
 * @author kouy
 * @since 1.0 2012-5-10
 */
public interface IIdCardVisitService {

	/**
	 * 查询身份证刷卡记录列表，分页。
	 * @param idCardVisit 身份证刷卡对象
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagingVO<IdCardVisitVO> queryIdCardVisitsForPaging(
			IdCardVisitVO idCardVisit, int pageNum, int pageSize);
	
	/**
	 * 获取身份证刷卡详细信息。
	 * @param visitId 身份证刷卡编号
	 * @return
	 */
	public IdCardVisitVO getIdCardVisitDetail(String visitId);
	
	/**
	 * 
	 * 方法的描述: 保存或修改身份证刷卡信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-23 上午9:28:47
	 * @param idCardVisit
	 */
	public void saveOrUpdateIdCardVisit(IdCardVisitVO idCardVisit);
	
	/**
	 * 
	 * 方法的描述: 删除身份证刷卡记录
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-23 上午9:29:55
	 * @param idCardVisit
	 */
	public void removeIdCardVisit(IdCardVisitVO idCardVisit);
	
	/**
	 * 
	 * 方法的描述: 查询身份证刷卡记录
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-5-25 下午4:38:39
	 * @param idCardVisit
	 * @return List
	 */
	public List<IdCardVisitVO> queryIdCardVisits(IdCardVisitVO idCardVisit);
}
