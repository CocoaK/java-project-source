package com.biencloud.smarthome.web.user.action;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.login.vo.LoginVO;
import com.biencloud.smarthome.web.permissions.service.IPermissionsService;
import com.biencloud.smarthome.web.permissions.vo.RoleVO;
import com.biencloud.smarthome.web.user.service.ISaUserService;
import com.biencloud.smarthome.web.user.vo.SaUserVO;

/**
 * 系统用户管理动作类。
 * @author kouy
 * @since 1.0 2012-5-15
 */
@SuppressWarnings("serial")
public class SaUserAction extends BaseUserAction<SaUserVO> {

	private String userName;
	private String idCard;
	private String department;
	private String status;
	
	private SaUserVO user;
	
	private List<RoleVO> roles;
	
	private boolean unallowedFlag;
	
	private ISaUserService saUserService;
	private IPermissionsService permissionsService;
	private String saUserType;
	
	public String getSaUserType() {
		return saUserType;
	}
	public void setSaUserType(String saUserType) {
		this.saUserType = saUserType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public SaUserVO getUser() {
		return user;
	}
	public void setUser(SaUserVO user) {
		this.user = user;
	}

	public List<RoleVO> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleVO> roles) {
		this.roles = roles;
	}
	
	public boolean isUnallowedFlag() {
		return unallowedFlag;
	}
	public void setUnallowedFlag(boolean unallowedFlag) {
		this.unallowedFlag = unallowedFlag;
	}
	
	public ISaUserService getSaUserService() {
		return saUserService;
	}
	public void setSaUserService(ISaUserService saUserService) {
		this.saUserService = saUserService;
	}
	
	public IPermissionsService getPermissionsService() {
		return permissionsService;
	}
	public void setPermissionsService(IPermissionsService permissionsService) {
		this.permissionsService = permissionsService;
	}
	
	
	/**
	 * 查看指定系统管理用户的详细信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewDetail() throws Exception {
		setUser(getSaUserService().getSaUserDetail(getCurrUserId()));
		logger.info("********************返回系统管理用户的详细信息：{}", getUser());
		return SUCCESS;
	}
	
	/**
	 * 查看个人信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewPersonalInfo() throws Exception {
		setUser(getSaUserService().getSaUserDetail(getUserId(),getUserType()));
		logger.info("********************返回个人信息：{}", getUser());
		return SUCCESS;
	}
	
	/**
	 * 查询系统管理用户列表。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String queryList() throws Exception {
		PagingVO<SaUserVO> page = getPage();
		if(page == null)
			page = new PagingVO<SaUserVO>();
		
		if(user == null)
			user = new SaUserVO();
		user.setRoleCode(getRoleCode());	
		LoginVO loginVO = new LoginVO();		
		loginVO.setLoginName(getLoginName());
		user.setLogin(loginVO);
		
		page = getSaUserService().querySaUsersForPaging(
				user, page.getPageNum(), page.getPageSize());
		
		logger.info("********************返回系统管理用户分页信息：{}", page);
		
		setPage(page);
		return SUCCESS;
	}
	
	/**
	 * 新增系统管理用户信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String addInput() throws Exception {		
		setLoginInitPass();
		List<RoleVO> roles = queryRoles();
		if(roles == null || roles.size() == 0){
			setUnallowedFlag(true);
			return SUCCESS;
		}
		setRoles(roles);
		return SUCCESS;
	}
	
	/**
	 * 保存新增系统管理用户信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String save() throws Exception {
		user.setCreatedUser(getLoginName());
		logger.info("********************新增系统管理用户信息：{}", user);
		getSaUserService().addSaUser(user);
		setRoles(queryRoles());
		user = null;
		setPromptFlag(true);
		setLoginInitPass();
		return SUCCESS;
	}
	
	/**
	 * 获取系统管理用户信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateInput() throws Exception {
		setUser(getSaUserService().getSaUserDetail(getCurrUserId(),getUserType()));
		setRoles(queryRoles());
		setLoginInitPass();
		logger.info("********************获取待更新的系统管理用户信息：{}", getUser());
		return SUCCESS;
	}
	
	/**
	 * 更新系统管理用户信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String update() throws Exception {
		user.setUpdatedUser(getLoginName());
		user.setUpdatedTime(new Date());
		logger.info("********************更新系统管理用户信息：{}", user);
		getSaUserService().updateSaUser(user);
		setCurrUserId(user.getUserId());
		setPromptFlag(true);
		return updateInput();
	}
	
	/**
	 * 获取个人信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updatePersonalInfoInput() throws Exception {
		setUser(getSaUserService().getSaUserDetail(getUserId(),getUserType()));
		logger.info("********************获取待更新的个人信息：{}", getUser());
		return SUCCESS;
	}
	
	/**
	 * 更新个人信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updatePersonalInfo() throws Exception {
		SaUserVO su = getSaUserService().getSaUserDetail(getUserId(),getUserType());
		su.setGender(user.getGender());
		su.setDepartment(user.getDepartment());
		su.setPost(user.getPost());
		su.setBirthDate(user.getBirthDate());
		su.setEmail(user.getEmail());
		su.setAddr(user.getAddr());
		su.setOfficePhone(user.getOfficePhone());
		su.setMobilePhone(user.getMobilePhone());
		su.setUpdatedUser(getLoginName());
		su.setUpdatedTime(new Date());
		su.getLogin().setLoginAlias(user.getLogin().getLoginAlias());		
		logger.info("********************更新个人信息：{}", su);
		getSaUserService().updateSaUser(su);
		user = null;
		setPromptFlag(true);
		return SUCCESS;
	}
	
	private List<RoleVO> queryRoles(){
		return getPermissionsService().queryRoles(
				getRoleCode(), getLoginName(), getUserType(), getUserType(), true);
	}
}
