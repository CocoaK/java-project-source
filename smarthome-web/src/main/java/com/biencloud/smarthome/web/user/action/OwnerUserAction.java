package com.biencloud.smarthome.web.user.action;

import java.util.Date;
import org.apache.commons.lang.StringUtils;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;
import com.biencloud.smarthome.web.user.service.IOwnerUserService;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;

/**
 * 住户用户管理动作类。
 * @author kouy
 * @since 1.0 2012-5-18
 */
@SuppressWarnings("serial")
public class OwnerUserAction extends BaseUserAction<OwnerUserVO> {
	
	private OwnerUserVO user;
	
	private String status;
	private boolean existHouseId;
	private CellHouseholdInfoVo house;
	private IOwnerUserService ownerUserService;
	
	//private IQrcodeCountService qrcodeCountService;
	
	//private QrcodeCountVO qrcodeCountVO;
	private ICellHouseholdInfoService cellHouseholdInfoService;

	public OwnerUserVO getUser() {
		return user;
	}

	public void setUser(OwnerUserVO user) {
		this.user = user;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isExistHouseId() {
		return existHouseId;
	}

	public void setExistHouseId(boolean existHouseId) {
		this.existHouseId = existHouseId;
	}

	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}

	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}
	
	
	/**
	 * 查看指定住户用户的详细信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewDetail() throws Exception {
		setUser(getOwnerUserService().getOwnerUserDetail(getCurrUserId()));
		//qrcodeCountVO = qrcodeCountService.getByHouseId(Integer.parseInt(user.getHouseId()));
		logger.info("********************返回住户用户的详细信息：{}", getUser());
		return SUCCESS;
	}
	
	/**
	 * 查看个人信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewPersonalInfo() throws Exception {
		setUser(getOwnerUserService().getOwnerUserDetail(getUserId()));
		logger.info("********************返回个人信息：{}", getUser());
		return SUCCESS;
	}
	
	/**
	 * 查询住户用户列表。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String queryList() throws Exception {
		PagingVO<OwnerUserVO> page = getPage();
		if(page == null)
			page = new PagingVO<OwnerUserVO>();
		
		if(user == null)
			user = new OwnerUserVO();
		user.setDistrictId(getDistrictId());
		
		page = getOwnerUserService().queryOwnerUsersForPaging(
				user, page.getPageNum(), page.getPageSize());
		
		logger.info("********************返回住户用户分页信息：{}", page);
		
		setPage(page);
		
		return SUCCESS;
	}
	
	/**
	 * 新增住户用户信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String addInput() throws Exception {
		setLoginInitPass();
		house = cellHouseholdInfoService.get(user.getHouseId());
		return SUCCESS;
	}
	
	/**
	 * 保存新增住户用户信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String save() throws Exception {
		user.setUserId(null);
		user.setDistrictId(getDistrictId());
		user.setCreatedUser(getLoginName());
		logger.info("********************新增住户用户信息：{}", user);
		getOwnerUserService().addOwnerUser(user);
		user = null;
		setPromptFlag(true);
		setLoginInitPass();
		return SUCCESS;
	}
	
	/**
	 * 获取住户用户信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateInput() throws Exception {
		setUser(getOwnerUserService().getOwnerUserDetail(getCurrUserId()));
		setLoginInitPass();
		logger.info("********************获取待更新的住户用户信息：{}", getUser());
		return SUCCESS;
	}
	
	/**
	 * 更新住户用户信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String update() throws Exception {
		user.setUpdatedUser(getLoginName());
		user.setUpdatedTime(new Date());
		logger.info("********************更新住户用户信息：{}", user);
		getOwnerUserService().updateOwnerUser(user);
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
		setUser(getOwnerUserService().getOwnerUserDetail(getUserId()));
		logger.info("********************获取待更新的个人信息：{}", getUser());
		return SUCCESS;
	}
	
	/**
	 * 更新个人信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updatePersonalInfo() throws Exception {
		OwnerUserVO ou = getOwnerUserService().getOwnerUserDetail(getUserId());
		ou.setGender(user.getGender());
		ou.setBirthDate(user.getBirthDate());
		ou.setEmail(user.getEmail());
		ou.setAddr(user.getAddr());
		ou.setHomePhone(user.getHomePhone());
		ou.setMobilePhone(user.getMobilePhone());
		ou.setUpdatedUser(getLoginName());
		ou.setUpdatedTime(new Date());
		ou.getLogin().setLoginAlias(user.getLogin().getLoginAlias());		
		logger.info("********************更新个人信息：{}", ou);
		getOwnerUserService().updateOwnerUser(ou);
		user = null;
		setPromptFlag(true);
		return SUCCESS;
	}
	
	/**
	 * 判断房间号是否已经和业主绑定。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String existHouseId() throws Exception {
		OwnerUserVO ou = getOwnerUserService().getUserByHouseId(user.getHouseId());
		if(ou != null && !StringUtils.equals(ou.getUserId(), getCurrUserId()))
			setExistHouseId(true);
		return SUCCESS;
	}

	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}

	public void setCellHouseholdInfoService(
			ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}

	public CellHouseholdInfoVo getHouse() {
		return house;
	}

	public void setHouse(CellHouseholdInfoVo house) {
		this.house = house;
	}

//	public IQrcodeCountService getQrcodeCountService() {
//		return qrcodeCountService;
//	}
//
//	public void setQrcodeCountService(IQrcodeCountService qrcodeCountService) {
//		this.qrcodeCountService = qrcodeCountService;
//	}
//
//	public QrcodeCountVO getQrcodeCountVO() {
//		return qrcodeCountVO;
//	}
//
//	public void setQrcodeCountVO(QrcodeCountVO qrcodeCountVO) {
//		this.qrcodeCountVO = qrcodeCountVO;
//	}
//	
}
