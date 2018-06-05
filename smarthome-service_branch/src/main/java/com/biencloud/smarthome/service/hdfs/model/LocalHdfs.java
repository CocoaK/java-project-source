package com.biencloud.smarthome.service.hdfs.model;


import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 项目名称：smarthome-service426 
 * 类名称：LocalHdfs 
 * 类描述： 本地文件实体
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-4-26 下午7:13:40
 */
@Entity
@Table(name = "t_local_hdfs", catalog = "smarthome")
public class LocalHdfs implements java.io.Serializable {

	// Fields
    //id
	private Long id;
	//服务器存放路径
	private String localPath;
	//hadoop路径
	private String hdfsPath;
	//文件名称
	private String fileName;
	//文件大小
	private Long fileSize;
	//传输方式，分上传和下载
	private String transferWay;
	//添加时间
	private Date addTime;
	//文件格式
	private String fileFormat;
	//文件类型
	private String kind;
	//上传者状态，分web和app
	private String uploaderKind;

	// Constructors

	/** default constructor */
	public LocalHdfs() {
	}

	/** full constructor */
	public LocalHdfs(Long id, String localPath, String hdfsPath, String fileName, Long fileSize, String transferWay, Date addTime) {
		this.id = id;
		this.localPath = localPath;
		this.hdfsPath = hdfsPath;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.transferWay = transferWay;
		this.addTime = addTime;
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

	@Column(name = "localPath", nullable = false)
	public String getLocalPath() {
		return this.localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	@Column(name = "hdfsPath", nullable = false)
	public String getHdfsPath() {
		return this.hdfsPath;
	}

	public void setHdfsPath(String hdfsPath) {
		this.hdfsPath = hdfsPath;
	}

	@Column(name = "fileName", nullable = false)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "fileSize", nullable = false)
	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name = "transferWay", nullable = false, length = 10)
	public String getTransferWay() {
		return this.transferWay;
	}

	public void setTransferWay(String transferWay) {
		this.transferWay = transferWay;
	}

	@Column(name = "addTime", nullable = false, length = 8)
	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	@Column(name = "fileFormat", nullable = false)
	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
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