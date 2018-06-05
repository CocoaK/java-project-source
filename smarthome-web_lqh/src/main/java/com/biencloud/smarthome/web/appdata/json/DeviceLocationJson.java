package com.biencloud.smarthome.web.appdata.json;

/**
 * 设备位置信息。
 * @author kouy
 * @since 1.0 2012-11-9
 * @see Json
 */
@SuppressWarnings("serial")
public class DeviceLocationJson extends Json {

	String downloadPath;//小区平面图下载的相对地址
	String coordinate;//设备所在位置的坐标，用|分隔
	
	
	public String getDownloadPath() {
		return downloadPath;
	}
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}
	
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
}
