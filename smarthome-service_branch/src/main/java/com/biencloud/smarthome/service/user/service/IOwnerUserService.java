package com.biencloud.smarthome.service.user.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.user.model.OwnerUser;

/**
 * 住户用户管理领域服务接口。
 * @author kouy
 * @since 1.0 2012-5-18
 * @see IService
 * @throws RuntimeException 当方法执行发生异常时
 */
public interface IOwnerUserService extends IService<OwnerUser, String> {

	/**
	 * 查询住户用户列表，分页。
	 * @param user 住户用户对象
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public Paging<OwnerUser> queryOwnerUsersForPaging(OwnerUser user, int pageNum, int pageSize);
	
	/**
	 * 新增住户信息列表。
	 * @param users 住户信息列表
	 */
	public void addUsers(List<OwnerUser> users);
	
	/**
	 * 通过用户真实姓名模糊查询满足的用户。
	 * @param userName 用户真实姓名
	 * @return
	 */
	public List<OwnerUser> findUsersByLikeName(String userName);
	
	/**
	 * 统计住户用户数。
	 * @param user 住户用户对象
	 */
	public long countUsers(OwnerUser user);
	
	/**
	 * 通过房间号获取住户信息，如果获取不到返回Null。
	 * @param houseId 房间号
	 * @return
	 */
	public OwnerUser getUserByHouseId(String houseId);
	
	/**
	 * 通过用户编号获取用户真实姓名，获取不到返回Null。
	 * @param userId 用户编号
	 * @return
	 */
	public String getUserNameById(String userId);
	
	/**
	 * 判断用户的身份证是否存在。
	 * 1）如果用户编号为空，则验证是否存在身份证；<br/>
	 * 2）如果用户编号不为空，则验证身份证对应的用户编号是否和当前用户编号相同；<br/>
	 * @param userId 用户编号
	 * @param idCard 身份证
	 * @return
	 */
	public boolean existIdCard(String userId, String idCard);
}
