package com.biencloud.smarthome.web.qrcode.service;

import java.io.OutputStream;
import java.util.List;

import com.biencloud.smarthome.web.base.service.IBaseRestService;
import com.biencloud.smarthome.web.common.vo.ResultEntity;
import com.biencloud.smarthome.web.qrcode.vo.QrcodeVO;

public interface IQrcodeService extends IBaseRestService<QrcodeVO>{
	
	List<QrcodeVO> queryList(QrcodeVO qrcodeVO) throws Exception;
	
	ResultEntity<QrcodeVO> get(Integer id) throws Exception;
	
	ResultEntity<String> delete(Integer id) throws Exception;
	
	ResultEntity<String> update(QrcodeVO qrcodeVO) throws Exception;
	
	ResultEntity<String> add(QrcodeVO qrcodeVO) throws Exception;
	
	ResultEntity<String> delete(QrcodeVO qrcodeVO) throws Exception;
	
	OutputStream qrcodeOutputStream(QrcodeVO qrcodeVO,OutputStream os);

}
