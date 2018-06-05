package com.biencloud.smarthome.web.hdfs.vo;

import java.util.Date;
import com.biencloud.smarthome.web.base.vo.BaseVO;

/**
 * 
 * 类名称：LocalHdfsVO 类描述：云存储文件类
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-5-11 下午3:18:38
 */
public class LocalHdfsVO extends BaseVO {

	// Fields
    //id,唯一标识
	private Long id;
	//本地路径
	private String localPath;
	//hadoop文件路径
	private String hdfsPath;
	//文件名称
	private String fileName;
	//文件大小
	private Long fileSize;
	//传输方式，分上传和下载两种方式
	private String transferWay;
	//添加时间
	private Date addTime;
	//文件格式
	private String fileFormat;
	//文件类型
	private String kind;
	//上传者类型，目前分web浏览器上传和app上传
	private String uploaderKind;
	
	
	public LocalHdfsVO() {
		super();
	}

	
	public LocalHdfsVO(Long id, String localPath, String hdfsPath, String fileName, Long fileSize, String transferWay, Date addTime,
			String fileFormat, String kind, String uploaderKind) {
		super();
		this.id = id;
		this.localPath = localPath;
		this.hdfsPath = hdfsPath;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.transferWay = transferWay;
		this.addTime = addTime;
		this.fileFormat = fileFormat;
		this.kind = kind;
		this.uploaderKind = uploaderKind;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getHdfsPath() {
		return hdfsPath;
	}

	public void setHdfsPath(String hdfsPath) {
		this.hdfsPath = hdfsPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getTransferWay() {
		return transferWay;
	}

	public void setTransferWay(String transferWay) {
		this.transferWay = transferWay;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
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