package com.biencloud.smarthome.cxfservice.service.impl;

import java.util.List;

import com.biencloud.smarthome.cxfservice.service.SmartHomeFileService;
import com.biencloud.smarthome.service.hdfs.model.LocalHdfs;
import com.biencloud.smarthome.service.hdfs.service.IHDFSFileService;
import com.biencloud.smarthome.service.hdfstask.model.HdfsTask;
import com.biencloud.smarthome.service.hdfstask.service.IHDFSTaskService;
import com.biencloud.smarthome.service.log.model.FileUploadLog;
import com.biencloud.smarthome.service.log.service.IFileUploadLogService;
import com.biencloud.smarthome.service.sysparam.service.ISysParamService;

/**
 * 
 * 类名称：SmartHomeFileServiceImpl 类描述： 针对文件服务器发布的服务实现
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-4-26 下午7:51:39
 */
public class SmartHomeFileServiceImpl implements SmartHomeFileService {

	private IHDFSFileService hdfsFileService;

	private IHDFSTaskService hdfsTaskService;
	private IFileUploadLogService fileUploadLogService;
	private ISysParamService sysParamService;
	@Override
	public List<HdfsTask> findTask(Integer taskStatus) {
		// TODO Auto-generated method stub
		return hdfsTaskService.findTask(taskStatus);
	}

	@Override
	public boolean saveOrUpdateHdfsTask(HdfsTask hdfsTask) {
		// TODO Auto-generated method stub
		return hdfsTaskService.saveOrUpdate(hdfsTask);
	}

	@Override
	public boolean saveOrUpdateLocalHdfs(LocalHdfs localHdfs) {
		// TODO Auto-generated method stub
		return hdfsFileService.saveOrUpdate(localHdfs);
	}

	@Override
	public HdfsTask findById(Long id) {
		return hdfsTaskService.findById(id);
	}

	@Override
	public void saveFileUploadLog(FileUploadLog entity) {
		fileUploadLogService.saveFileUploadLog(entity);
	}

	@Override
	public List<HdfsTask> findAllTask() {

		return hdfsTaskService.findTask();
	}

	@Override
	public boolean deleteTask(Long id) {

		return hdfsTaskService.deleteById(id);
	}

	public IHDFSFileService getHdfsFileService() {
		return hdfsFileService;
	}

	public void setHdfsFileService(IHDFSFileService hdfsFileService) {
		this.hdfsFileService = hdfsFileService;
	}

	public IHDFSTaskService getHdfsTaskService() {
		return hdfsTaskService;
	}

	public void setHdfsTaskService(IHDFSTaskService hdfsTaskService) {
		this.hdfsTaskService = hdfsTaskService;
	}

	public IFileUploadLogService getFileUploadLogService() {
		return fileUploadLogService;
	}

	public void setFileUploadLogService(IFileUploadLogService fileUploadLogService) {
		this.fileUploadLogService = fileUploadLogService;
	}

	@Override
	public String queryParamValueByParamCode(String paramCode) {		
		return sysParamService.queryParamValueByParamCode(paramCode);
	}

	public ISysParamService getSysParamService() {
		return sysParamService;
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}
    
}
