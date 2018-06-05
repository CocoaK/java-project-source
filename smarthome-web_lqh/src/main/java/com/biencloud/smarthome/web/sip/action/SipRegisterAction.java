package com.biencloud.smarthome.web.sip.action;

import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;

import com.biencloud.smarthome.web.base.action.BaseAction;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.vo.ResultEntity;
import com.biencloud.smarthome.web.qrcode.service.IQrcodeService;
import com.biencloud.smarthome.web.qrcode.vo.QrcodeVO;
import com.biencloud.smarthome.web.sip.VO.SipRegister;
import com.biencloud.smarthome.web.sip.service.ISipRegisterService;

@SuppressWarnings("serial")
public class SipRegisterAction extends BaseAction<SipRegisterAction>{
	
	private SipRegister sipRegister;
	private ISipRegisterService sipRegisterService;
	private IQrcodeService qrcodeService;
	private QrcodeVO qrcode;
	private OutputStream qrcodeOs;
	
	private ResultEntity<String> re;
	
	//添加二维码输入界面
	public String addInput() throws Exception{
		return SUCCESS;
	}
	
	//添加sip账号并打印
	public String add() throws Exception{
		SipRegister sip = null;
		if(qrcode != null && StringUtils.isNotBlank(qrcode.getPwd())){
			sipRegister.setPassword(qrcode.getPwd());
		}
		if(qrcode != null && StringUtils.isNotBlank(qrcode.getSipUid())){
			ResultEntity<SipRegister> result = sipRegisterService.getByUsername(qrcode.getSipUid());
			if(result != null){
				sip = result.getData();
			}
		}
		//如果sip账号未注册
		if(sip == null){
			ResultEntity<SipRegister> entity = sipRegisterService.create(sipRegister);
			SipRegister sr = entity.getData();
			if(sr!=null){
				qrcode.setSipUid(sr.getUsername());
				qrcode.setPwd(sr.getPassword());
			}
		}else{	//如果SIP已注册
			qrcode.setPwd(sip.getPassword());
		}
		qrcode.setType(Constants.QRCODE_TYPE_SYSTEM);
		qrcode.setStatus(Constants.QRCODE_STATUS_VALID);
		qrcodeService.add(qrcode);
		qrcodeService.qrcodeOutputStream(qrcode, super.getResponse().getOutputStream());
		return null;
	}
	
	//修改二维码
	public String update() throws Exception{
//		qrcodeVO.setStatus(Constants.QRCODE_STATUS_VALID);
//		re = qrcodeService.update(qrcodeVO);
		return SUCCESS;
	}
	
	//删除二维码
	public String deleteSip() throws Exception{
//		re = qrcodeService.delete(qrcodeVO);
//		if(qrcodeVO!=null){
//			qrcodeVO.setSipUid(null);
//		}
//		return queryList();
		return SUCCESS;
	}
	
	public String addAndPrint()throws Exception{
		
//		qrcodeOs = qrcodeService.qrcodeOutputStream(qrcodeVO,super.getResponse().getOutputStream());
		return null;
	}
	
	public String isExist()throws Exception{
		if(qrcode != null && StringUtils.isNotBlank(qrcode.getSipUid())){
			ResultEntity<SipRegister> result = sipRegisterService.getByUsername(qrcode.getSipUid());
			if(result == null || result.getData()==null){
				re = new ResultEntity<String>(0,"","");
			}else{
				re = new ResultEntity<String>(1,"","");
			}
		}
		return SUCCESS;
	}

	public SipRegister getSipRegister() {
		return sipRegister;
	}

	public void setSipRegister(SipRegister sipRegister) {
		this.sipRegister = sipRegister;
	}

	public ISipRegisterService getSipRegisterService() {
		return sipRegisterService;
	}

	public void setSipRegisterService(ISipRegisterService sipRegisterService) {
		this.sipRegisterService = sipRegisterService;
	}

	public ResultEntity<String> getRe() {
		return re;
	}

	public void setRe(ResultEntity<String> re) {
		this.re = re;
	}

	public IQrcodeService getQrcodeService() {
		return qrcodeService;
	}

	public void setQrcodeService(IQrcodeService qrcodeService) {
		this.qrcodeService = qrcodeService;
	}

	public QrcodeVO getQrcode() {
		return qrcode;
	}

	public void setQrcode(QrcodeVO qrcode) {
		this.qrcode = qrcode;
	}

	public OutputStream getQrcodeOs() {
		return qrcodeOs;
	}

	public void setQrcodeOs(OutputStream qrcodeOs) {
		this.qrcodeOs = qrcodeOs;
	}

}
