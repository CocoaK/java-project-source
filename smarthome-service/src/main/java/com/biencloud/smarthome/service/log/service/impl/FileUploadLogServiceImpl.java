package com.biencloud.smarthome.service.log.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biencloud.smarthome.service.base.service.impl.BaseService;
import com.biencloud.smarthome.service.charge.service.IChargeDataService;
import com.biencloud.smarthome.service.common.model.Paging;
import com.biencloud.smarthome.service.log.model.FileUploadLog;
import com.biencloud.smarthome.service.log.service.IFileUploadLogService;

/**
 * 文件上传日志领域服务实现类。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-15
 * @see IFileUploadLogService
 * @see BaseService
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FileUploadLogServiceImpl extends BaseService<FileUploadLog, Long> implements IFileUploadLogService {

	private IChargeDataService chargeDataService;

	@Override
	public Paging<FileUploadLog> queryFileUploadLogForPaging(FileUploadLog paramsOb, int pageNum, int pageSize) {
		try {
			StringBuilder hql = new StringBuilder("SELECT " + REPLACE_CHARS + " FROM FileUploadLog dn");
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(paramsOb.getSource()))
				appendCondition(hql, "dn.source like :source", "source", "%" + paramsOb.getSource().trim() + "%", params);
			if (StringUtils.isNotBlank(paramsOb.getName()))
				appendCondition(hql, "dn.name like :name", "name", "%" + paramsOb.getName().trim() + "%", params);
			if (StringUtils.isNotBlank(paramsOb.getFormat()))
				appendCondition(hql, "dn.format like :format", "format", "%" + paramsOb.getFormat().trim() + "%", params);
			if (paramsOb.getAddTime()!=null)
				appendCondition(hql, "dn.addTime like :addTime",
						"addTime", "%" + paramsOb.getAddTime()+ "%" , params);
			if (paramsOb.getAddStartTime()!=null){	
				appendCondition(hql, "dn.addTime >= :addStartTime",
						"addStartTime",paramsOb.getAddStartTime(), params);
			}
			if (paramsOb.getAddEndTime()!=null)
				appendCondition(hql, "dn.addTime <= :addEndTime",
						"addEndTime", paramsOb.getAddEndTime() , params);
			hql.append(" order by id desc");
			String queryString = hql.toString().replace(REPLACE_CHARS, "dn");
			String queryStringCount = hql.toString().replace(REPLACE_CHARS, "COUNT(*)");
			return queryByNamedParamsForPaging(pageNum, pageSize, queryString, queryStringCount, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<FileUploadLog> queryFileUploadLogForList(FileUploadLog paramsOb) {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateFileUploadLog(FileUploadLog entity) {
		super.update(entity);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveFileUploadLog(FileUploadLog entity) {
		entity.setAddTime(new Date(System.currentTimeMillis()));
		super.save(entity);
	}

	@Override
	public FileUploadLog getFileUploadLog(String entityId) {
		return super.get(new Long(entityId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void DelFileUploadLog(FileUploadLog entity) {
		super.remove(entity);
	}

	public IChargeDataService getChargeDataService() {
		return chargeDataService;
	}

	public void setChargeDataService(IChargeDataService chargeDataService) {
		this.chargeDataService = chargeDataService;
	}

}
