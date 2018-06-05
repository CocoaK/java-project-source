package com.biencloud.smarthome.web.sip.action;

import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.ResultEntity;
import com.biencloud.smarthome.web.qrcode.service.IQrcodeService;
import com.biencloud.smarthome.web.qrcode.vo.QrcodeVO;
import com.biencloud.smarthome.web.sip.VO.SipRegister;
import com.biencloud.smarthome.web.sip.VO.UserRoomNoVo;
import com.biencloud.smarthome.web.sip.service.ISipRegisterService;
import com.biencloud.smarthome.web.sip.service.IUserRoomNoService;

@SuppressWarnings("serial")
public class SipBoundAction extends BaseAction<SipBoundAction>{
	
	private ResultEntity<String> re;
	private UserRoomNoVo userRoomNoVo;
	private IUserRoomNoService userRoomNoService;
	
	//绑定房号输入界面
	public String boundHouseInput() throws Exception{
		System.out.println("1111:"+userRoomNoVo);
		return SUCCESS;
	}
	//绑定房号
	public String boundHouse() throws Exception{
		if(userRoomNoVo==null){
			
		}
		return SUCCESS;
	}
	
	//绑定SIP账号
	/*public String boundSip() throws Exception{
		return SUCCESS;
	}*/
	
	//添加绑定sip账号
	public String add() throws Exception{
		if(userRoomNoVo!=null){
			ResultEntity<String> result = userRoomNoService.add(userRoomNoVo);
		}
//		if(result!=null && ResultEntity.SUCCESS == result.getCode()){
//			
//		}
		return SUCCESS;
	}
	
	//修改二维码
	public String update() throws Exception{
//		qrcodeVO.setStatus(Constants.QRCODE_STATUS_VALID);
//		re = qrcodeService.update(qrcodeVO);
		return SUCCESS;
	}
	
	//删除二维码
	public String delete() throws Exception{
//		re = qrcodeService.delete(qrcodeVO);
//		if(qrcodeVO!=null){
//			qrcodeVO.setSipUid(null);
//		}
//		return queryList();
		return SUCCESS;
	}
	
	public String isExist()throws Exception{

		return SUCCESS;
	}

	public ResultEntity<String> getRe() {
		return re;
	}

	public void setRe(ResultEntity<String> re) {
		this.re = re;
	}
	public UserRoomNoVo getUserRoomNoVo() {
		return userRoomNoVo;
	}
	public void setUserRoomNoVo(UserRoomNoVo userRoomNoVo) {
		this.userRoomNoVo = userRoomNoVo;
	}
	public IUserRoomNoService getUserRoomNoService() {
		return userRoomNoService;
	}
	public void setUserRoomNoService(IUserRoomNoService userRoomNoService) {
		this.userRoomNoService = userRoomNoService;
	}

}
