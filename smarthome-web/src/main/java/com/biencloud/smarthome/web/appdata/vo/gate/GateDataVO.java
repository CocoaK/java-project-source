package com.biencloud.smarthome.web.appdata.vo.gate;

/**
 * 门禁数据值对象。
 * @author kouy
 * @since 1.0 2012-6-27
 */
public class GateDataVO {

	// 设备代码
	private String deviceNo;
	// 卡号
	private String cardNo;
	// 卡的有效状态
	private String status;
	// 卡的有效开始时间
	private long beginTime;
	// 卡的有效结束时间
	private long endTime;

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
}
