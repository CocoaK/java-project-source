package com.biencloud.smarthome.web.qrcode.action;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.qrcode.service.IQrcodeCountService;
import com.biencloud.smarthome.web.qrcode.vo.QrcodeCountVO;
import com.biencloud.smarthome.web.user.service.IOwnerUserService;
import com.biencloud.smarthome.web.user.vo.OwnerUserVO;

@SuppressWarnings("serial")
public class QrcodeCountAction extends BaseAction<QrcodeCountAction>{
	private QrcodeCountVO qrcodeCountVO;
	private IQrcodeCountService qrcodeCountService;
	private IOwnerUserService ownerUserService;
	private OwnerUserVO user;
	private Integer houseId;
	private String userId;
	
	public String get() throws Exception{
		qrcodeCountVO = qrcodeCountService.getByHouseId(houseId);
		return SUCCESS;
	}
	
	public String detailInput() throws Exception{
		//user = getOwnerUserService().getOwnerUserDetail(userId);
		return SUCCESS;
	}
	
//	public String saveQrcodeCount() throws Exception{
//		qrcodeCountVO = qrcodeCountService.save();
//		return SUCCESS;
//	}

	public QrcodeCountVO getQrcodeCountVO() {
		return qrcodeCountVO;
	}

	public void setQrcodeCountVO(QrcodeCountVO qrcodeCountVO) {
		this.qrcodeCountVO = qrcodeCountVO;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public IQrcodeCountService getQrcodeCountService() {
		return qrcodeCountService;
	}

	public void setQrcodeCountService(IQrcodeCountService qrcodeCountService) {
		this.qrcodeCountService = qrcodeCountService;
	}

	public OwnerUserVO getUser() {
		return user;
	}

	public void setUser(OwnerUserVO user) {
		this.user = user;
	}

	public IOwnerUserService getOwnerUserService() {
		return ownerUserService;
	}

	public void setOwnerUserService(IOwnerUserService ownerUserService) {
		this.ownerUserService = ownerUserService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	};

}
