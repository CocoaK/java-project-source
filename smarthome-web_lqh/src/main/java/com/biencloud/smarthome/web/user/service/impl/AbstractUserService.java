package com.biencloud.smarthome.web.user.service.impl;

import org.apache.commons.lang.ArrayUtils;

import com.biencloud.smarthome.web.base.service.BaseService;
import com.biencloud.smarthome.web.login.vo.LoginVO;
import com.biencloud.smarthome.web.wsclient.stub.Login;

/**
 * 用户管理服务代理基类。
 * @author kouy
 * @since 1.0 2012-5-15
 * @see BaseService
 */
public abstract class AbstractUserService<VO, Entity> extends BaseService<VO> {

	protected static final String RESULTS = "results";
	protected static final String BIRTH_DATE = "birthDate";
	protected static final String CREATED_TIME = "createdTime";
	protected static final String UPDATED_TIME = "updatedTime";
	protected static final String LOGIN = "login";
	
	private static final String LAST_LOGIN_TIME = "lastLoginTime";
		
	/**
	 * 获取实体对象的类型。
	 * @return
	 */
	protected abstract Class<Entity> getEntityClass();

	
	/**
	 * 用户实体对象转换为值对象。
	 * @param user 用户实体对象
	 * @param ignoreProperties 忽略属性
	 * @return
	 */
	protected VO convertToUserVO(Entity user, String... ignoreProperties) {
		VO userVO = org.springframework.beans.BeanUtils.instantiate(voClass);
		copyUserObject(user, userVO, true, ignoreProperties, BIRTH_DATE,
				CREATED_TIME, UPDATED_TIME);
		return userVO;
	}

	/**
	 * 用户值对象转换为实体对象。
	 * @param userVO 用户值对象
	 * @param ignoreProperties 忽略属性
	 * @return
	 */
	protected Entity convertToUserEntity(VO userVO,
			String... ignoreProperties) {
		Entity user = org.springframework.beans.BeanUtils.instantiate(getEntityClass());
		copyUserObject(user, userVO, false, ignoreProperties, BIRTH_DATE,
				CREATED_TIME, UPDATED_TIME);
		return user;
	}
	
	/**
	 * 用户值对象和实体对象相互拷贝。
	 * @param user 用户实体对象
	 * @param userVO 用户值对象
	 * @param copyToVO 是否从实体对象拷贝到值对象
	 * @param ignoreProperties 忽略属性
	 * @param dateProperties 日期属性
	 */
	protected void copyUserObject(Entity user, VO userVO, boolean copyToVO, 
			String[] ignoreProperties, String... dateProperties) {
		// 忽略属性是否包含登录对象属性
		boolean contains = ArrayUtils.contains(ignoreProperties, LOGIN);
		if (!contains)
			ignoreProperties = (String[]) ArrayUtils.add(ignoreProperties,
					LOGIN);

		if (copyToVO) {
			copyProperties(user, userVO, ignoreProperties, true, dateProperties);
		} else {
			copyProperties(userVO, user, ignoreProperties, false, dateProperties);
		}

		// 忽略属性不包含登录对象属性，需对登录对象属性进行拷贝
		if (!contains) {
			if (copyToVO) {
				LoginVO loginVO = new LoginVO();
				copyLoginObject(getProperty(user, LOGIN), loginVO, copyToVO, null);
				copyProperty(userVO, LOGIN, loginVO);
			} else {
				Login login = new Login();
				copyLoginObject(login, getProperty(userVO, LOGIN), copyToVO, null);
				copyProperty(user, LOGIN, login);
			}
		}
	}

	/**
	 * 登录值对象和实体对象相互拷贝。
	 * @param login 登录实体对象
	 * @param loginVO 登录值对象
	 * @param copyToVO 是否从实体对象拷贝到值对象
	 * @param ignoreProperties 忽略属性
	 */
	protected void copyLoginObject(Object login, Object loginVO,
			boolean copyToVO, String[] ignoreProperties) {
		if (copyToVO) {
			copyProperties(login, loginVO, ignoreProperties, true,
					CREATED_TIME, LAST_LOGIN_TIME);
		} else {
			copyProperties(loginVO, login, ignoreProperties, false,
					CREATED_TIME, LAST_LOGIN_TIME);
		}
	}
}
