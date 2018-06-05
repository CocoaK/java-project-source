package com.biencloud.smarthome.web.sip.VO;

import com.biencloud.smarthome.web.base.vo.BaseVO;

@SuppressWarnings("serial")
public class UserRoomNoVo extends BaseVO{
	
	    private Integer id;

	    private String roomNo;
	    
	    private String sipid;

	    private String deviceId;

	    private String status;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getRoomNo() {
			return roomNo;
		}

		public void setRoomNo(String roomNo) {
			this.roomNo = roomNo;
		}

		public String getSipid() {
			return sipid;
		}

		public void setSipid(String sipid) {
			this.sipid = sipid;
		}

		public String getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
	    
}
