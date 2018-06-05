package com.biencloud.smarthome.service.log.dao.impl;

import com.biencloud.smarthome.service.base.dao.impl.BaseDao;
import com.biencloud.smarthome.service.log.dao.IFileUploadLogDao;
import com.biencloud.smarthome.service.log.model.FileUploadLog;

/**
 * 文件上传管理数据访问对象，基于JPA实现。
 * 
 * @author dehua ye
 * @since 1.0 2012-5-15
 * @see IFileUploadLogDao
 */
public class FileUploadLogDaoImpl extends BaseDao<FileUploadLog, String> implements IFileUploadLogDao {
}
