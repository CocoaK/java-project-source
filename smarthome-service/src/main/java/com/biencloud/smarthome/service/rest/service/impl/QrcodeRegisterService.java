package com.biencloud.smarthome.service.rest.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.mapper.QrcodeRegisterMapper;
import com.biencloud.smarthome.service.rest.model.QrcodeRegister;
import com.biencloud.smarthome.service.rest.service.IQrcodeRegisterService;

@Service
public class QrcodeRegisterService extends BaseResService<QrcodeRegister> implements IQrcodeRegisterService{
	
	@Autowired
	private QrcodeRegisterMapper qrcodeRegisterMapper;
	
	@Override
	public BaseMapper<QrcodeRegister> getBaseMapper() {
		return qrcodeRegisterMapper;
	}

	@Override
	public ResultEntity<String> addForResultEntity(QrcodeRegister qrcodeRegister){
		List<QrcodeRegister> qreg = qrcodeRegisterMapper.getList(qrcodeRegister);
		ResultEntity<String> re = new ResultEntity<String>();
		if(qrcodeRegister == null){
			return re;
		}
		if(qreg!=null && qreg.size()>0){
			re.setMessage("already exist");
			return re;
		}
		qrcodeRegister.setStatus(1);
		int i = super.add(qrcodeRegister);
		if(i>0){
			re.setCode(ResultEntity.SUCCESS);
			re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		}
		return re;
	}

	@Override
	public ResultEntity<String> delete(QrcodeRegister qrcodeRegister) {
		if(qrcodeRegister==null){
			return null;
		}
		int i = qrcodeRegisterMapper.delete(qrcodeRegister.getId());
		return super.proccessResultEntity(i > 0 ? ResultEntity.SUCCESS : ResultEntity.FAILD,"","");
	}

	@Override
	//返回值data: 0,不匹配;1,匹配;2,不存在此记录
	public ResultEntity<Integer> checkExist(
			QrcodeRegister qrcodeRegister) {
		ResultEntity<Integer> re = new ResultEntity<Integer>();
		if(qrcodeRegister==null)
			return re;
		if(StringUtils.isBlank(qrcodeRegister.getCode())){
			return re;
		}
				
		List<QrcodeRegister> list = qrcodeRegisterMapper.getList(qrcodeRegister);
		re.setCode(ResultEntity.SUCCESS);
		re.setMessage(ResultEntity.MESSAGE_SUCCESS);
		if(list!=null && list.size()>0){
			re.setData(1);			//匹配
		}else{
			re.setData(2);			//不存在
			QrcodeRegister reg = new QrcodeRegister();
			reg.setSipUid(qrcodeRegister.getSipUid());
			reg.setRoomNo(qrcodeRegister.getRoomNo());
			List<QrcodeRegister> qrNoCodes = qrcodeRegisterMapper.getList(reg);
			if(qrNoCodes!=null && qrNoCodes.size()>0){
				re.setData(0);		//不匹配
			}
		}
		return re;
	}
}
