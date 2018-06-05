package com.biencloud.smarthome.web.qrcode.service.impl;

import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;
import com.biencloud.smarthome.web.base.service.BaseRestService;
import com.biencloud.smarthome.web.common.util.QrcodeUtil;
import com.biencloud.smarthome.web.common.vo.ResultEntity;
import com.biencloud.smarthome.web.qrcode.service.IQrcodeService;
import com.biencloud.smarthome.web.qrcode.vo.QrcodeVO;

public class QrcodeService extends BaseRestService<QrcodeVO> implements IQrcodeService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}
	
	@Override
	public List<QrcodeVO> queryList(QrcodeVO qrcodeVO) throws Exception {
		super.setQueryUrl("/qrcode/queryList");
		List<QrcodeVO> list = super.postForObject(restServiceUrl+queryUrl, qrcodeVO, new ParameterizedTypeReference<List<QrcodeVO>>(){});
		return list;
	}

	@Override
	public ResultEntity<QrcodeVO> get(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntity<String> delete(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntity<String> update(QrcodeVO qrcodeVO) throws Exception {
		super.setUpdateUrl("/qrcode/update");
		ResultEntity<String> re = super.postForObject(restServiceUrl+updateUrl, qrcodeVO, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return re;
	}

	@Override
	public ResultEntity<String> add(QrcodeVO qrcodeVO) throws Exception {
		super.setQueryUrl("/qrcode/add");
		ResultEntity<String> re = super.postForObject(restServiceUrl+queryUrl, qrcodeVO, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return re;
	}

	@Override
	public OutputStream qrcodeOutputStream(QrcodeVO qrcodeVO,OutputStream os) {
		if(qrcodeVO==null){
			return null;
		}
		String roomNo = qrcodeVO.getRoomNo();
		String sipUid = qrcodeVO.getSipUid();
		String destUid = qrcodeVO.getDoorSipid();
		String pwd = qrcodeVO.getPwd();
		return QrcodeUtil.getQrcodeOutputStream(roomNo,sipUid,pwd,destUid,os);
	}

	@Override
	public ResultEntity<String> delete(QrcodeVO qrcodeVO) throws Exception {
		super.setQueryUrl("/qrcode/del");
		ResultEntity<String> re = super.postForObject(restServiceUrl+queryUrl, qrcodeVO, new ParameterizedTypeReference<ResultEntity<String>>(){});
		return re;
	}
}
