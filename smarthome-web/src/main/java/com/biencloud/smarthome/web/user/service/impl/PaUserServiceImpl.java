package com.biencloud.smarthome.web.user.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.user.service.IPaUserService;
import com.biencloud.smarthome.web.user.vo.PaUserVO;
import com.biencloud.smarthome.web.wsclient.stub.PaUser;
import com.biencloud.smarthome.web.wsclient.stub.Paging;

/**
 * 物业管理用户调用服务实现类。
 * @author kouy
 * @since 1.0 2012-5-15
 * @see BaseService
 * @see IPaUserService
 */
public class PaUserServiceImpl extends AbstractUserService<PaUserVO, PaUser> implements IPaUserService {

	@Override
	public PagingVO<PaUserVO> queryPaUsersForPaging(PaUserVO userVO,
			int pageNum, int pageSize) {
//		PaUser user = new PaUser();
//		copyProperties(userVO, user, new String[]{LOGIN}, 
//				false, BIRTH_DATE, CREATED_TIME, UPDATED_TIME);
		
		PaUser user = convertToUserEntity(userVO);
		
		Paging paging = getSmartHomeService().queryPaUsersForPaging(
				user, pageNum, pageSize);
		
		PagingVO<PaUserVO> pagingVO = new PagingVO<PaUserVO>();
		copyProperties(paging, pagingVO, new String[]{RESULTS}, true);
		
		List<Object> list = paging.getResults();
		if(list != null && list.size() > 0){
			List<PaUserVO> results = new ArrayList<PaUserVO>();
			for (int index = 0, size = list.size(); index < size; index++) {
				results.add(convertToUserVO((PaUser)list.get(index)));
			}
			pagingVO.setResults(results);
		}
		
		return pagingVO;
	}

	@Override
	public PaUserVO getPaUserDetail(String userId) {
		PaUser user = getSmartHomeService().getPaUserDetail(userId);
		return convertToUserVO(user);
	}

	@Override
	public void addPaUser(PaUserVO userVO) {
		getSmartHomeService().addPaUser(convertToUserEntity(userVO));
	}

	@Override
	public void updatePaUser(PaUserVO userVO) {
		getSmartHomeService().updatePaUser(convertToUserEntity(userVO));
	}
	
	@Override
	@SuppressWarnings("unchecked")
	protected Class<PaUser> getEntityClass() {
		return (Class<PaUser>)((ParameterizedType)getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}
}
