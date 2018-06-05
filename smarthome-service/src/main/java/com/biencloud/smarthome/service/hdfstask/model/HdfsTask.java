package com.biencloud.smarthome.service.hdfstask.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 类名称：HdfsTask 类描述： 本地文件定时上传任务
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-4-26 下午7:12:55
 */
@Entity
@Table(name = "t_hdfs_task", catalog = "smarthome")
public class HdfsTask implements java.io.Serializable {

	// Fields
    //id
	private Long id;
	//服务器存放路径
	private String uploadPath;
	//hadoop路径
	private String hdfsPath;
	//任务状态，0表示待上传到hadoop,1表示已经上传到hadoop
	private Integer taskStatus;
	//任务添加时间
	private Date taskAddTime;
	//任务完成时间
	private Date taskFinishTime;
	//文件名
	private String fileName;
	//文件格式
	private String fileFormat;
	//文件大小
	private Long fileSize;
	//服务器根路径
	private String basepath;
	//上传文件类型，包括：图片，视频，音频，apk
	private String kind;
	//上传者类型，分web上传和app上传
	private String uploaderKind;

	// Constructors

	/** default constructor */
	public HdfsTask() {
	}

	/** full constructor */
	public HdfsTask(Long id, String uploadPath, String hdfsPath, Integer taskStatus, Date taskAddTime, Date taskFinishTime, Long fileSize,
			String basePath) {
		this.id = id;
		this.uploadPath = uploadPath;
		this.hdfsPath = hdfsPath;
		this.taskStatus = taskStatus;
		this.taskAddTime = taskAddTime;
		this.taskFinishTime = taskFinishTime;
		this.fileSize = fileSize;
		this.basepath = basePath;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "uploadPath", nullable = false)
	public String getUploadPath() {
		return this.uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	@Column(name = "hdfsPath", nullable = false)
	public String getHdfsPath() {
		return this.hdfsPath;
	}

	public void setHdfsPath(String hdfsPath) {
		this.hdfsPath = hdfsPath;
	}

	@Column(name = "taskStatus", nullable = false)
	public Integer getTaskStatus() {
		return this.taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Column(name = "taskAddTime", nullable = false, length = 8)
	public Date getTaskAddTime() {
		return this.taskAddTime;
	}

	public void setTaskAddTime(Date taskAddTime) {
		this.taskAddTime = taskAddTime;
	}

	@Column(name = "taskFinishTime", length = 8)
	public Date getTaskFinishTime() {
		return this.taskFinishTime;
	}

	public void setTaskFinishTime(Date taskFinishTime) {
		this.taskFinishTime = taskFinishTime;
	}

	@Column(name = "fileName", nullable = false)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "fileFormat", nullable = false)
	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	@Column(name = "fileSize", nullable = false)
	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	@Column(name = "basePath", nullable = false)
	public String getBasepath() {
		return basepath;
	}

	public void setBasepath(String basepath) {
		this.basepath = basepath;
	}
	@Column(name = "kind", nullable = false)
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	@Column(name = "uploaderKind", nullable = false)
	public String getUploaderKind() {
		return uploaderKind;
	}

	public void setUploaderKind(String uploaderKind) {
		this.uploaderKind = uploaderKind;
	}
	
    
}