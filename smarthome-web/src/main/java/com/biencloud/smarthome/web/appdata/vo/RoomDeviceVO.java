package com.biencloud.smarthome.web.appdata.vo;

import java.util.List;

import com.biencloud.smarthome.web.monitor.vo.SceneDeviceVo;

/**
 * 
 * 类名称：RoomInfoVO 类描述： 房间信息对象
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-7-3 下午2:11:19
 */
public class RoomDeviceVO {
	// 房间号
	private String room_id;
	// 房间名称
	private String room_name;
	// 房间图片url
	private String image_url;
	
	private List<SceneDeviceVo> deviceData;

	
	
	public RoomDeviceVO() {
		super();
	}

	public RoomDeviceVO(String room_id, String room_name, String image_url,List<SceneDeviceVo> deviceData) {
		super();
		this.room_id = room_id;
		this.room_name = room_name;
		this.image_url = image_url;
		this.deviceData = deviceData;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public List<SceneDeviceVo> getDeviceData() {
		return deviceData;
	}

	public void setDeviceData(List<SceneDeviceVo> deviceData) {
		this.deviceData = deviceData;
	}

}
