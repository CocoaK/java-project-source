package com.biencloud.smarthome.web.user.service;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.user.vo.PaUserVO;

/**
 * 物业管理用户调用服务接口。
 * @author kouy
 * @since 1.0 2012-5-14
 * @throws RuntimeException 当方法执行发生异常时
 */
public interface IPaUserService {

	/**
	 * 查询物业管理用户列表，分页。
	 * @param userVO 物业管理用户对象
	 * @param pageNum 待查询的页码
	 * @param pageSize 每页条数
	 * @return
	 */
	public PagingVO<PaUserVO> queryPaUsersForPaging(
			PaUserVO userVO, int pageNum, int pageSize);
	
	/**
	 * 获取物业管理用户详细信息。
	 * @param userId 用户编号
	 * @return
	 */
	public PaUserVO getPaUserDetail(String userId);
	
	/**
	 * 新增物业管理用户信息。
	 * @param userVO 物业管理用户信息
	 */
	public void addPaUser(PaUserVO userVO);
	
	/**
	 * 更新物业管理用户信息。
	 * @param userVO 物业管理用户信息
	 */
	public void updatePaUser(PaUserVO userVO);
}
