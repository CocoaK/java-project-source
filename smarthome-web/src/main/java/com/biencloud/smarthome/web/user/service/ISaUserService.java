package com.biencloud.smarthome.web.user.service;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.user.vo.SaUserVO;


/**
 * 系统管理用户调用服务接口。
 * @author kouy
 * @since 1.0 2012-5-14
 * @throws RuntimeException 当方法执行发生异常时
 */
public interface ISaUserService {

	/**
	 * 查询系统管理用户列表，分页。
	 * @param userVO 系统管理用户对象
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagingVO<SaUserVO> querySaUsersForPaging(
			SaUserVO userVO, int pageNum, int pageSize);
	
	/**
	 * 获取系统管理用户详细信息。
	 * @param userId 用户编号
	 * @return
	 */
	public SaUserVO getSaUserDetail(String userId);
	
	/**
	 * 新增系统管理用户信息。
	 * @param userVO 物业管理用户信息
	 */
	public void addSaUser(SaUserVO userVO);
	
	/**
	 * 更新系统管理用户信息。
	 * @param userVO 物业管理用户信息
	 */
	public void updateSaUser(SaUserVO userVO);
}
