package com.biencloud.smarthome.web.appdata.json;

import java.util.List;
import com.biencloud.smarthome.web.appdata.vo.RoomDeviceVO;

/**
 * 
 * 类名称：RoomInfoJson 类描述： 场景设备json对象
 * 
 * @author: ky
 * @version: 0.1
 */
@SuppressWarnings("serial")
public class RoomDeviceJson extends Json {
	//场景设备数据集合
	private List<RoomDeviceVO> roomDeviceData;

	public List<RoomDeviceVO> getRoomDeviceData() {
		return roomDeviceData;
	}

	public void setRoomDeviceData(List<RoomDeviceVO> roomDeviceData) {
		this.roomDeviceData = roomDeviceData;
	}

}
