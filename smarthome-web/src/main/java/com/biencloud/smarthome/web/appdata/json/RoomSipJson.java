package com.biencloud.smarthome.web.appdata.json;

import java.util.List;
import com.biencloud.smarthome.web.appdata.vo.RoomSipVO;

/**
 * 
 * 类名称：RoomSipJson 
 * 类描述：呼叫管理机SIP账号
 * @author: ykou  
 * @version: 0.1
 * @date: 2015-3-15 下午2:34:53
 */
@SuppressWarnings("serial")
public class RoomSipJson extends Json{
	
	private List<RoomSipVO> roomSipList;

	public List<RoomSipVO> getRoomSipList() {
		return roomSipList;
	}

	public void setRoomSipList(List<RoomSipVO> roomSipList) {
		this.roomSipList = roomSipList;
	}


}
