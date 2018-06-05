package com.biencloud.smarthome.web.appdata.json;

import java.util.List;

import com.biencloud.smarthome.web.appdata.vo.RoomVO;

/**
 * 
 * 类名称：RoomJson 
 * 类描述： 户户通房号Json
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-21 下午2:34:53
 */
public class RoomJson extends Json{
	
	private List<RoomVO> roomList;

	public List<RoomVO> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<RoomVO> roomList) {
		this.roomList = roomList;
	}
	
	

}
