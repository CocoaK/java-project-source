package com.biencloud.smarthome.web.gate.action;

import java.util.Date;
import java.util.List;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.vo.PagingVO;
import com.biencloud.smarthome.web.device.vo.DeviceVO;
import com.biencloud.smarthome.web.gate.service.IGateCardService;
import com.biencloud.smarthome.web.gate.vo.GateCardVO;
import com.biencloud.smarthome.web.gate.vo.GatePermissionsVO;
import com.biencloud.smarthome.web.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.web.housemgr.vo.CellHouseholdInfoVo;

/**
 * 门卡管理动作类。
 * @author kouy
 * @since 1.0 2012-5-8
 */
@SuppressWarnings("serial")
public class GateCardAction extends BaseAction<GateCardVO> {

	private String gateCardId;
	private String cardNo;
	private String ownerName;
	private String ownerIdCard;
	private String status;
	
	private GateCardVO gateCard;
	
	private List<GatePermissionsVO> gatePermissions;
	
	private DeviceVO device;
	
	private boolean promptFlag;
	private boolean existCardNo;
	
	private IGateCardService gateCardService;
	private ICellHouseholdInfoService cellHouseholdInfoService;
	private CellHouseholdInfoVo houseVO;
	
	public String getGateCardId() {
		return gateCardId;
	}
	public void setGateCardId(String gateCardId) {
		this.gateCardId = gateCardId;
	}
	
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerIdCard() {
		return ownerIdCard;
	}
	public void setOwnerIdCard(String ownerIdCard) {
		this.ownerIdCard = ownerIdCard;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean isPromptFlag() {
		return promptFlag;
	}
	public void setPromptFlag(boolean promptFlag) {
		this.promptFlag = promptFlag;
	}
	
	public boolean isExistCardNo() {
		return existCardNo;
	}
	public void setExistCardNo(boolean existCardNo) {
		this.existCardNo = existCardNo;
	}
	
	public GateCardVO getGateCard() {
		return gateCard;
	}
	public void setGateCard(GateCardVO gateCard) {
		this.gateCard = gateCard;
	}
	
	public List<GatePermissionsVO> getGatePermissions() {
		return gatePermissions;
	}
	public void setGatePermissions(List<GatePermissionsVO> gatePermissions) {
		this.gatePermissions = gatePermissions;
	}
	
	public DeviceVO getDevice() {
		return device;
	}
	public void setDevice(DeviceVO device) {
		this.device = device;
	}
	
	public IGateCardService getGateCardService() {
		return gateCardService;
	}
	public void setGateCardService(IGateCardService gateCardService) {
		this.gateCardService = gateCardService;
	}
	
	
	/**
	 * 查看指定门卡的详细信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String viewDetail() throws Exception {
		setGateCard(getGateCardService().getGateCardDetail(gateCardId));
		return SUCCESS;
	}
	
	/**
	 * 查询门卡列表。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String queryList() throws Exception {
		PagingVO<GateCardVO> page = getPage();
		if(page == null)
			page = new PagingVO<GateCardVO>();
		
		if(gateCard == null)
			gateCard = new GateCardVO();
		gateCard.setDistrictId(getDistrictId());
		
		page = getGateCardService().queryGateCardsForPaging(
				gateCard, page.getPageNum(), page.getPageSize());
		
		setPage(page);
		return SUCCESS;
	}
	
	/**
	 * 获取门卡信息进行更新。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String updateInput() throws Exception {
		setGateCard(getGateCardService().getGateCardDetail(gateCardId));
		return SUCCESS;
	}
	
	/**
	 * 更新门卡信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String update() throws Exception {
		gateCard.setUpdatedUser(getLoginName());
		gateCard.setUpdatedTime(new Date());
		gateCard.setGatePermissions(gatePermissions);		
		getGateCardService().updateGateCard(gateCard);
		gateCardId = gateCard.getGateCardId();
		gateCard = null;
		setPromptFlag(true);		
		return updateInput();
	}
	
	/**
	 * 新增门卡信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String addInput() throws Exception {	
		if(gateCard != null)
			houseVO = cellHouseholdInfoService.get(gateCard.getHouseId());
		return SUCCESS;
	}
	
	/**
	 * 保存新增门卡信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String save() throws Exception {
		gateCard.setCreatedUser(getLoginName());
		gateCard.setDistrictId(getDistrictId());
		gateCard.setGatePermissions(gatePermissions);
		getGateCardService().addGateCard(gateCard);
		gateCard = null;
		setPromptFlag(true);
		return SUCCESS;
	}
	
	/**
	 * 启用或禁用门卡。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String enableOrDisable() throws Exception {
		getGateCardService().updateGateCardStatus(
				gateCardId, status, getLoginName());
		gateCardId = null;
		status = null;
		setPromptFlag(true);
		return queryList();
	}
	
	/**
	 * 删除门卡信息。
	 * @return
	 * @throws Exception 当执行发生异常时
	 */
	public String remove() throws Exception {
		getGateCardService().removeGateCard(gateCardId);
		gateCardId = null;
		setPromptFlag(true);
		return queryList();
	}
	
	/**
	 * 判断卡号是否存在。
	 * @return
	 * @throws Exception
	 */
	public String existCardNo() throws Exception {
		GateCardVO gc = new GateCardVO();
		gc.setGateCardId(gateCardId);
		gc.setCardNo(cardNo);
		gc.setDistrictId(getDistrictId());
		setExistCardNo(getGateCardService().existCardNo(gc));
		return SUCCESS;
	}
	public ICellHouseholdInfoService getCellHouseholdInfoService() {
		return cellHouseholdInfoService;
	}
	public void setCellHouseholdInfoService(
			ICellHouseholdInfoService cellHouseholdInfoService) {
		this.cellHouseholdInfoService = cellHouseholdInfoService;
	}
	public CellHouseholdInfoVo getHouseVO() {
		return houseVO;
	}
	public void setHouseVO(CellHouseholdInfoVo houseVO) {
		this.houseVO = houseVO;
	}
	
}
