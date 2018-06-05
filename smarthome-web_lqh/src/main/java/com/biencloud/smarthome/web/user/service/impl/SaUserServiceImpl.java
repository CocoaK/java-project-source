package com.biencloud.smarthome.web.user.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.user.service.ISaUserService;
import com.biencloud.smarthome.web.user.vo.SaUserVO;
import com.biencloud.smarthome.web.wsclient.stub.Paging;
import com.biencloud.smarthome.web.wsclient.stub.SaUser;

/**
 * 系统管理用户调用服务实现类。
 * @author kouy
 * @since 1.0 2012-5-15
 * @see BaseService
 * @see ISaUserService
 */
public class SaUserServiceImpl extends AbstractUserService<SaUserVO, SaUser> implements ISaUserService {
	
	@Override
	public PagingVO<SaUserVO> querySaUsersForPaging(SaUserVO userVO, int pageNum,
			int pageSize) {
//		SaUser user = new SaUser();
//		copyProperties(userVO, user, new String[]{LOGIN}, 
//				false, BIRTH_DATE, CREATED_TIME, UPDATED_TIME);
		
		SaUser user = convertToUserEntity(userVO);
		
		Paging paging = getSmartHomeService().querySaUsersForPaging(
				user, pageNum, pageSize);
		
		PagingVO<SaUserVO> pagingVO = new PagingVO<SaUserVO>();
		copyProperties(paging, pagingVO, new String[]{RESULTS}, true);
		
		List<Object> list = paging.getResults();
		if(list != null && list.size() > 0){
			List<SaUserVO> results = new ArrayList<SaUserVO>();
			for (int index = 0, size = list.size(); index < size; index++) {
				results.add(convertToUserVO((SaUser)list.get(index)));
			}
			pagingVO.setResults(results);
		}
		
		return pagingVO;
	}
	
	@Override
	public SaUserVO getSaUserDetail(String userId,String saUserType) {
		SaUser user = getSmartHomeService().getSaUser(userId,saUserType);
		return convertToUserVO(user);
	}
	
	@Override
	public SaUserVO getSaUserDetail(String userId) {
		SaUser user = getSmartHomeService().getSaUserDetail(userId);
		return convertToUserVO(user);
	}

	@Override
	public void addSaUser(SaUserVO userVO) {
		getSmartHomeService().addSaUser(convertToUserEntity(userVO));
	}

	@Override
	public void updateSaUser(SaUserVO userVO) {
		getSmartHomeService().updateSaUser(convertToUserEntity(userVO));
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Class<SaUser> getEntityClass() {
		return (Class<SaUser>)((ParameterizedType)getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}
}
