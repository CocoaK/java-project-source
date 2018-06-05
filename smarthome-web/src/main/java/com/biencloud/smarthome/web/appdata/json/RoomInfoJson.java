package com.biencloud.smarthome.web.appdata.json;

import java.util.List;

import com.biencloud.smarthome.web.appdata.vo.RoomInfoVO;

/**
 * 
 * 类名称：RoomInfoJson 类描述： 房号信息json对象
 * 
 * @author: kouy
 * @version: 0.1
 * @date: 2012-7-3 下午2:06:18
 */
public class RoomInfoJson extends Json {
	//房间数据集合
	private List<RoomInfoVO> roomData;

	public List<RoomInfoVO> getRoomData() {
		return roomData;
	}

	public void setRoomData(List<RoomInfoVO> roomData) {
		this.roomData = roomData;
	}

}
