package com.biencloud.smarthome.web.qrcode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestTemplate;
import com.biencloud.smarthome.web.base.service.BaseRestService;
import com.biencloud.smarthome.web.qrcode.service.IQrcodeCountService;
import com.biencloud.smarthome.web.qrcode.vo.QrcodeCountVO;

public class QrcodeCountServiceImpl extends BaseRestService<QrcodeCountVO> implements IQrcodeCountService{
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	@Override
	public QrcodeCountVO getByHouseId(Integer houseId) throws Exception {
		super.setQueryUrl("/qrcode/count/get");
		QrcodeCountVO vo = postForObject(restServiceUrl+queryUrl,houseId,new ParameterizedTypeReference<QrcodeCountVO>() {});
		return vo;
	} 
	
}
