package com.biencloud.smarthome.web.hdfstask.vo;

import java.util.Date;
import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 
 * 类名称：HdfsTaskVO 类描述： 云文件同步任务类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-11 下午3:17:06
 */
public class HdfsTaskVO extends BaseVO {

	// Fields
    //id,唯一标识
	private Long id;
	//上传路径
	private String uploadPath;
	//hadoop路径
	private String hdfsPath;
	//任务状态，该字段目前没有使用
	private Integer taskStatus;
	//添加时间
	private Date taskAddTime;
	//任务完成时间
	private Date taskFinishTime;
	//文件名称
	private String fileName;
	//文件格式
	private String fileFormat;
	//文件大小
	private Long fileSize;
	//文件根路径
	private String basepath;
	//文件类型
	private String kind;
	//上传者类型，分浏览器和app
	private String uploaderKind;

	
	public HdfsTaskVO() {
		super();
	}

	public HdfsTaskVO(Long id, String uploadPath, String hdfsPath, Integer taskStatus, Date taskAddTime, Date taskFinishTime,
			String fileName, String fileFormat, Long fileSize, String basepath, String kind, String uploaderKind) {
		super();
		this.id = id;
		this.uploadPath = uploadPath;
		this.hdfsPath = hdfsPath;
		this.taskStatus = taskStatus;
		this.taskAddTime = taskAddTime;
		this.taskFinishTime = taskFinishTime;
		this.fileName = fileName;
		this.fileFormat = fileFormat;
		this.fileSize = fileSize;
		this.basepath = basepath;
		this.kind = kind;
		this.uploaderKind = uploaderKind;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getHdfsPath() {
		return hdfsPath;
	}

	public void setHdfsPath(String hdfsPath) {
		this.hdfsPath = hdfsPath;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Date getTaskAddTime() {
		return taskAddTime;
	}

	public void setTaskAddTime(Date taskAddTime) {
		this.taskAddTime = taskAddTime;
	}

	public Date getTaskFinishTime() {
		return taskFinishTime;
	}

	public void setTaskFinishTime(Date taskFinishTime) {
		this.taskFinishTime = taskFinishTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getBasepath() {
		return basepath;
	}

	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getUploaderKind() {
		return uploaderKind;
	}

	public void setUploaderKind(String uploaderKind) {
		this.uploaderKind = uploaderKind;
	}
	

}