package com.biencloud.smarthome.web.user.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.user.service.IOwnerUserService;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;
import com.biencloud.smarthome.web.wsclient.stub.OwnerUser;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 住户用户管理调用后台服务实现类。
 * @author kouy
 * @since 1.0 2012-5-18
 */
public class OwnerUserServiceImpl extends AbstractUserService<OwnerUserVO, OwnerUser>
		implements IOwnerUserService {

	@Override
	public PagingVO<OwnerUserVO> queryOwnerUsersForPaging(OwnerUserVO userVO,
			int pageNum, int pageSize) {
		OwnerUser user = new OwnerUser();
		copyProperties(userVO, user, new String[]{LOGIN}, 
				false, BIRTH_DATE, CREATED_TIME, UPDATED_TIME);
		
		Paging paging = getSmartHomeService().queryOwnerUsersForPaging(
				user, pageNum, pageSize);
		
		PagingVO<OwnerUserVO> pagingVO = new PagingVO<OwnerUserVO>();
		copyProperties(paging, pagingVO, new String[]{RESULTS}, true);
		
		List<Object> list = paging.getResults();
		if(list != null && list.size() > 0){
			List<OwnerUserVO> results = new ArrayList<OwnerUserVO>();
			for (Object obj : list) {
				results.add(convertToUserVO((OwnerUser)obj));
			}
			pagingVO.setResults(results);
		}
		
		return pagingVO;
	}

	@Override
	public OwnerUserVO getOwnerUserDetail(String userId) {
		OwnerUser user = getSmartHomeService().getOwnerUserDetail(userId);
		return convertToUserVO(user);
	}

	@Override
	public void addOwnerUser(OwnerUserVO userVO) {
		getSmartHomeService().addOwnerUser(convertToUserEntity(userVO));
	}

	@Override
	public void addOwnerUsers(List<OwnerUserVO> userVOs) {
		List<OwnerUser> users = new ArrayList<OwnerUser>();
		for (OwnerUserVO userVO : userVOs) {
			users.add(convertToUserEntity(userVO));
		}
		getSmartHomeService().addOwnerUsers(users);
	}

	@Override
	public void updateOwnerUser(OwnerUserVO userVO) {
		getSmartHomeService().updateOwnerUser(convertToUserEntity(userVO));
	}

	@Override
	public long countUsers(OwnerUserVO user) {
		return getSmartHomeService().countUsers(convertToUserEntity(user));
	}
	
	@Override
	public OwnerUserVO getUserByHouseId(String houseId) {
		OwnerUser user = getSmartHomeService().getOwnerUserByHouseId(houseId);
		if(user == null)
			return null;
		return convertToUserVO(user);
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	protected Class<OwnerUser> getEntityClass() {
		return (Class<OwnerUser>)((ParameterizedType)getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}
}
