package com.biencloud.smarthome.web.user.service;

import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;


/**
 * 住户用户管理调用后台服务接口。
 * @author kouy
 * @since 1.0 2012-5-18
 */
public interface IOwnerUserService {

	/**
	 * 查询住户用户列表，分页。
	 * @param user 住户用户对象
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagingVO<OwnerUserVO> queryOwnerUsersForPaging(
			OwnerUserVO userVO, int pageNum, int pageSize);
	
	/**
	 * 获取住户用户详细信息。
	 * @param userId 用户编号
	 * @return
	 */
	public OwnerUserVO getOwnerUserDetail(String userId);
	
	/**
	 * 新增住户用户信息。
	 * @param user 住户用户信息
	 */
	public void addOwnerUser(OwnerUserVO userVO);
	
	/**
	 * 新增住户用户信息列表。
	 * @param users 住户用户信息列表
	 */
	public void addOwnerUsers(List<OwnerUserVO> userVOs);
	
	/**
	 * 更新住户用户信息。
	 * @param user 住户用户信息
	 */
	public void updateOwnerUser(OwnerUserVO userVO);
	
	/**
	 * 统计住户用户数。
	 * @param user 住户用户对象
	 */
	public long countUsers(OwnerUserVO user);
	
	/**
	 * 通过房间号获取住户信息，如果获取不到返回Null。
	 * @param houseId 房间号
	 * @return
	 */
	public OwnerUserVO getUserByHouseId(String houseId);
}
