package com.biencloud.smarthome.web.user.action;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.service.IHousingDistrictInfoService;
import com.biencloud.smarthome.web.login.vo.LoginVO;
import com.biencloud.smarthome.web.permissions.service.IPermissionsService;
import com.biencloud.smarthome.web.permissions.vo.RoleVO;
import com.biencloud.smarthome.web.user.service.IPaUserService;
import com.biencloud.smarthome.web.user.vo.PaUserVO;

/**
 * 物业用户管理动作类。
 * @author kouy
 * @since 1.0 2012-5-15
 */
@SuppressWarnings("serial")
public class PaUserAction extends BaseUserAction<PaUserVO> {

	private String userName;
	private String idCard;
	private String department;
	private String districtName;
	private String status;
	
	private PaUserVO user;
	
	private List<RoleVO> roles;
	
	private boolean unallowedFlag;
	
	private IPaUserService paUserService;
	private IPermissionsService permissionsService;
	private IHousingDistrictInfoService housingDistrictInfoService;
	
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

	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public PaUserVO getUser() {
		return user;
	}
	public void setUser(PaUserVO user) {
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
	
	public IPaUserService getPaUserService() {
		return paUserService;
	}
	public void setPaUserService(IPaUserService paUserService) {
		this.paUserService = paUserService;
	}
	
	public IPermissionsService getPermissionsService() {
		return permissionsService;
	}
	public void setPermissionsService(IPermissionsService permissionsService) {
		this.permissionsService = permissionsService;
	}
	
	public IHousingDistrictInfoService getHousingDistrictInfoService() {
		return housingDistrictInfoService;
	}
	public void setHousingDistrictInfoService(
			IHousingDistrictInfoService housingDistrictInfoService) {
		this.housingDistrictInfoService = housingDistrictInfoService;
	}
	
	
	/**
	 * 查看指定物业管理用户的详细信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewDetail() throws Exception {
		setUser(getPaUserService().getPaUserDetail(getCurrUserId()));
		logger.info("********************返回物业管理用户的详细信息：{}", getUser());
		return SUCCESS;
	}
	
	/**
	 * 查看个人信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewPersonalInfo() throws Exception {
		setUser(getPaUserService().getPaUserDetail(getUserId()));
		logger.info("********************返回个人信息：{}", getUser());
		return SUCCESS;
	}
	
	/**
	 * 查询物业管理用户列表。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String queryList() throws Exception {
		PagingVO<PaUserVO> page = getPage();
		if(page == null)
			page = new PagingVO<PaUserVO>();
		
		if(user == null)
			user = new PaUserVO();
		user.setRoleCode(getRoleCode());
		if(Constants.LOGIN_USER_TYPE_PAUSER.equals(getUserType()))
			user.setDistrictId(getDistrictId());
		
		LoginVO loginVO = new LoginVO();		
		loginVO.setLoginName(getLoginName());
		loginVO.setUserType(getUserType());
		user.setLogin(loginVO);
		
		page = getPaUserService().queryPaUsersForPaging(
				user, page.getPageNum(), page.getPageSize());
		
		logger.info("********************返回物业管理用户分页信息：{}", page);
		
		setPage(page);
		
		if(Constants.LOGIN_USER_TYPE_SYSTEM.equals(getUserType()))		
			setRequestAttribute("allDistricts", getHousingDistrictInfoService().getDistricts(null));
				
		return SUCCESS;
	}
	
	/**
	 * 新增物业管理用户信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String addInput() throws Exception {
		setLoginInitPass();
		setDistrictName(getLoginVO().getDistrictName());
		List<RoleVO> roles = queryRoles();
		if(roles == null || roles.size() == 0){
			setUnallowedFlag(true);
			return SUCCESS;
		}
		setRoles(roles);
		return SUCCESS;
	}
	
	/**
	 * 保存新增物业管理用户信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String save() throws Exception {
		user.setUserId(null);
		user.setCreatedUser(getLoginName());
		setDistrictIdForPA(user);
		logger.info("********************新增物业管理用户信息：{}", user);
		getPaUserService().addPaUser(user);
		setRoles(queryRoles());
		user = null;
		setPromptFlag(true);
		setLoginInitPass();
		return SUCCESS;
	}
	
	//设置物业管理用户所在的小区
	private void setDistrictIdForPA(PaUserVO userVO){
		if(Constants.LOGIN_USER_TYPE_PAUSER.equals(getUserType())){
			userVO.setDistrictId(getDistrictId());
		}
	}
	
	/**
	 * 获取物业管理用户信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateInput() throws Exception {
		setUser(getPaUserService().getPaUserDetail(getCurrUserId()));
		setRoles(queryRoles());
		setLoginInitPass();
		setDistrictName(getLoginVO().getDistrictName());
		logger.info("********************获取待更新的物业管理用户信息：{}", getUser());
		return SUCCESS;
	}
	
	/**
	 * 更新物业管理用户信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String update() throws Exception {
		user.setUpdatedUser(getLoginName());
		user.setUpdatedTime(new Date());
		setDistrictIdForPA(user);
		logger.info("********************更新物业管理用户信息：{}", user);
		getPaUserService().updatePaUser(user);
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
		setUser(getPaUserService().getPaUserDetail(getUserId()));
		logger.info("********************获取待更新的个人信息：{}", getUser());
		return SUCCESS;
	}
	
	/**
	 * 更新个人信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updatePersonalInfo() throws Exception {
		PaUserVO pu = getPaUserService().getPaUserDetail(getUserId());
		pu.setGender(user.getGender());
		pu.setDepartment(user.getDepartment());
		pu.setPost(user.getPost());
		pu.setBirthDate(user.getBirthDate());
		pu.setEmail(user.getEmail());
		pu.setAddr(user.getAddr());
		pu.setOfficePhone(user.getOfficePhone());
		pu.setMobilePhone(user.getMobilePhone());
		pu.setDegree(user.getDegree());
		pu.setMajor(user.getMajor());
		pu.setIntro(user.getIntro());
		pu.setUpdatedUser(getLoginName());
		pu.setUpdatedTime(new Date());
		pu.getLogin().setLoginAlias(user.getLogin().getLoginAlias());		
		logger.info("********************更新个人信息：{}", pu);
		getPaUserService().updatePaUser(pu);
		user = null;
		setPromptFlag(true);
		return SUCCESS;
	}
	
	private List<RoleVO> queryRoles(){
		return getPermissionsService().queryRoles(getRoleCode(), 
				getLoginName(), getUserType(), Constants.LOGIN_USER_TYPE_PAUSER, true);
	}
}
