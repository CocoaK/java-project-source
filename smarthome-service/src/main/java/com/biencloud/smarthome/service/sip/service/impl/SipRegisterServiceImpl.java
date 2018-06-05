package com.biencloud.smarthome.service.sip.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.common.utils.StringUtils;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.sip.mapper.SipRegisterMapper;
import com.biencloud.smarthome.service.sip.model.SipRegister;
import com.biencloud.smarthome.service.sip.service.ISipRegisterService;

@Service
public class SipRegisterServiceImpl extends BaseResService<SipRegister> implements ISipRegisterService{

	@Autowired
	private SipRegisterMapper sipRegisterMapper;
	
	@Override
	public ResultEntity<SipRegister> register(SipRegister sipRegister) {
		//默认类型type=0室内机,type=1门口机,type=2围墙机,type=3手机
		Integer type = 0;
		String username = null;
		String passwd = null;
		if(sipRegister != null && sipRegister.getType()!=null){
			//账号类型
			type = sipRegister.getType();
		}
		if(sipRegister.getUsername()==null || "".equals(sipRegister.getUsername())){
			//获取最sip用户名
			String maxUsername = sipRegisterMapper.getMaxUsername(type);
			//最大sip用户名+1
			username = ""+(Long.parseLong(maxUsername)+1);
		}else{
			username = sipRegister.getUsername();
		}
		if(sipRegister.getPassword()==null || "".equals(sipRegister.getPassword())){
			//6位随机密码
			passwd = StringUtils.randomNum(Constants.PASSWD_LENGTH);
		}else{
			passwd = sipRegister.getPassword();
		}
		//如果上传的密码不为空则保存上传的密码，否则生成随机密码
		if(sipRegister!=null && sipRegister.getPassword()!=null && !"".equals(sipRegister.getPassword())){
			passwd=sipRegister.getPassword();
		}
		SipRegister sr = new SipRegister(username,"",passwd,"",type);
		SipRegister sipReg = new SipRegister();
		sipReg.setUsername(username);
		sipReg.setPassword(passwd);
		List<SipRegister> sip = sipRegisterMapper.getList(sipReg);
			if(sip!=null && sip.size()>0){
				return super.proccessResultEntity(ResultEntity.SUCCESS,"",sip.get(0));
			}

		int i = sipRegisterMapper.insert(sr);
		ResultEntity<SipRegister> re = super.proccessResultEntity(i > 0 ? ResultEntity.SUCCESS : ResultEntity.FAILD,"",sr);
		return re;
	}

	@Override
	public BaseMapper<SipRegister> getBaseMapper() {
		return sipRegisterMapper;
	}

	@Override
	public ResultEntity<String> deleteByUsername(String username) {
		int i = sipRegisterMapper.deleteByUsername(username);
		return super.proccessResultEntity(i > 0 ? ResultEntity.SUCCESS : ResultEntity.FAILD,"",username);
	}

	@Override
	public ResultEntity<SipRegister> getByUsername(String username) {
		SipRegister sip = sipRegisterMapper.getByUsername(username);
		return super.proccessResultEntity(sip != null ?  ResultEntity.SUCCESS : ResultEntity.FAILD,"",sip);
	}

}
