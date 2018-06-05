package com.biencloud.smarthome.service.rest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.utils.StringUtils;
import com.biencloud.smarthome.service.housemgr.service.ICellHouseholdInfoService;
import com.biencloud.smarthome.service.rest.mapper.QrcodeMapper;
import com.biencloud.smarthome.service.rest.model.Qrcode;
import com.biencloud.smarthome.service.rest.service.IQrcodeService;
import com.biencloud.smarthome.service.sip.model.SipRegister;
import com.biencloud.smarthome.service.sip.service.ISipRegisterService;

@Service
public class QrcodeService extends BaseResService<Qrcode> implements IQrcodeService{
	
	@Autowired
	private QrcodeMapper qrcodeMapper;
	
	@Autowired
	private ICellHouseholdInfoService cellHouseholdInfoService;
	
	@Autowired
	private ISipRegisterService sipRegisterService;

	@Override
	public BaseMapper<Qrcode> getBaseMapper() {
		return qrcodeMapper;
	}

	@Override
	public List<Qrcode> queryList(Qrcode qrcode) {
		List<Qrcode> qrcodeList =  qrcodeMapper.queryList(qrcode);
		if(qrcodeList!=null && qrcodeList.size()>0){
			for(Qrcode q:qrcodeList){
				String roomNo=cellHouseholdInfoService.getFullHouseNo(q.getHouseId().toString());
				String roomName= cellHouseholdInfoService.getFullHouseName(q.getHouseId().toString());
				String pwd = null;
				ResultEntity<SipRegister> re =  sipRegisterService.getByUsername(q.getSipUid());
				if(re!=null && re.getData()!=null){
					pwd = re.getData().getPassword();

				}
				q.setRoomNo(roomNo);
				q.setRoomName(roomName);
				q.setPwd(pwd);
			}
		}
		return qrcodeList;
	}

	@Override
	public Integer getQrcodeCount(Qrcode qrcode) {
		return qrcodeMapper.getQrcodeCount(qrcode);
	}

	@Override
	public ResultEntity<String> delete(Qrcode qrcode) {
		if(qrcode==null){
			return null;
		}
		int i = qrcodeMapper.delete(qrcode.getId());
		sipRegisterService.deleteByUsername(qrcode.getSipUid());
		return super.proccessResultEntity(i > 0 ? ResultEntity.SUCCESS : ResultEntity.FAILD,"","");
	}

	@Override
	public List<String> check(String str) {
		List<String> list = new ArrayList<String>();
		if(str!=null && !"".equals(str)){
			String[] qrcodes = str.split(",");
			if(qrcodes!=null && !"".equals(qrcodes)){
				for(int i=0;i<qrcodes.length;i++){
					String[] array = qrcodes[i].split("#");
					Qrcode qrcode = new Qrcode();
					qrcode.setRoomNo(array[0]);
					qrcode.setDoorSipid(array[1]);
					qrcode.setSipUid(array[2]);
					List<Qrcode> qrcodeList =  qrcodeMapper.queryList(qrcode);
					if(!qrcodeList.isEmpty()){
						list.add(qrcodes[i]);
					}
				}
			}
			
		}
		return list;
	}
	
	@Override
	public ResultEntity<String> addForResultEntity(Qrcode qrcode){
		String roomNo = null;
		if(qrcode==null)
			return null;
		if(qrcode.getHouseId()!=null){
			roomNo=cellHouseholdInfoService.getFullHouseNo(qrcode.getHouseId().toString());
			qrcode.setRoomNo(roomNo);
		}
		//检查二维码是否已经存在
		Qrcode q = new Qrcode();
		q.setSipUid(qrcode.getSipUid());
		q.setDoorSipid(qrcode.getDoorSipid());
		q.setRoomNo(qrcode.getRoomNo());
		if(q.getSipUid()!=null && q.getDoorSipid()!=null && q.getRoomNo()!=null){
			//查询sipuid,doorsipid,roomNo二维码
			List<Qrcode> list = this.queryList(q);
			//如果存在则返回
			if(list != null && list.size()>0){
				return new ResultEntity<String>(ResultEntity.FAILD,"already exist","");
			}
		}
		
		ResultEntity<String> re = proccessResultEntity(add(qrcode) > 0 ? ResultEntity.SUCCESS
				: ResultEntity.FAILD, "", "");
		String code = "AB"+StringUtils.toHex(qrcode.getId(),7);
		Qrcode qr = new Qrcode();
		qr.setId(qrcode.getId());
		qr.setCode(code);
		super.updateOnActiveById(qr);
		return re;
	}
}
