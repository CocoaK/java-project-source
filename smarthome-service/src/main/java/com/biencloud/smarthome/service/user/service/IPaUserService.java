package com.biencloud.smarthome.service.user.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.user.model.PaUser;

/**
 * 物业管理用户领域服务接口。
 * @author kouy
 * @since 1.0 2012-5-12
 * @see IService
 * @throws RuntimeException 当方法执行发生异常时
 */
public interface IPaUserService extends IService<PaUser, String> {

	/**
	 * 查询物业管理用户列表，分页。
	 * @param paUser 物业管理用户对象
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public Paging<PaUser> queryPaUsersForPaging(PaUser paUser, int pageNum, int pageSize);

	/**
	 * 通过用户姓名来模糊查询用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public List<PaUser> findUsersByLikeName(String userName);
	
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
	
	/**
	 * 判断指定编号的小区是否存在物业管理用户。
	 * @param districtId 小区编号
	 * @return
	 */
	public boolean existUsersByDistrictId(String districtId);
}
