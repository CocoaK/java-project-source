package com.biencloud.smarthome.service.hdfs.service.impl;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.hdfs.model.LocalHdfs;
import com.biencloud.smarthome.service.hdfs.service.IHDFSFileService;

/**
 * 
 * 项目名称：smarthome-service426 类名称：HDFSFileServiceImpl 类描述： HDFS本地文件业务实现接口
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-4-26 下午5:18:29
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class HDFSFileServiceImpl extends BaseService<LocalHdfs, Long> implements IHDFSFileService {

	
	@Override
	public Paging<LocalHdfs> list(int pageNum, int pageSize,String condition,String orderString) {

		return super.queryForPagingByEntityNameAndCondition(pageNum, pageSize, " LocalHdfs",condition,orderString);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean saveOrUpdate(LocalHdfs localHdfs) {
		boolean isSuccess = false;
		if (localHdfs != null) {
			if (localHdfs.getId() == null) {
				super.save(localHdfs);
				isSuccess = true;
			} else {
				super.update(localHdfs);
				isSuccess = true;
			}
		}
		return isSuccess;
	}

	@Override
	public Paging<LocalHdfs> list(int pageNum, int pageSize, String condition, String orderString, Object... values) {
		
		return super.queryForPagingByEntityNameAndCondition(pageNum, pageSize, " LocalHdfs",condition,orderString,values);
	}

	

}
