package com.biencloud.smarthome.web.appdata.service;

import com.biencloud.smarthome.web.appdata.json.RoomDeviceJson;
import com.biencloud.smarthome.web.appdata.json.RoomInfoJson;
import com.biencloud.smarthome.web.appdata.json.RoomJson;
import com.biencloud.smarthome.web.appdata.json.RoomSipJson;
import com.biencloud.smarthome.web.appdata.vo.RoomVO;

/**
 * 
 * 类名称：IRoomService 
 * 类描述： App户户通房号信息接口类
 * @author: ykou  
 * @version: 0.1
 * @date: 2012-6-21 下午2:29:10
 */
public interface IRoomService {
	
	/**
	 * 方法的描述: 根据设备编号查询户户通房间信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-21 上午10:41:27
	 * @param deviceCode
	 * @return
	 */
	public RoomJson queryRooms(String deviceCode);
	/**
	 * 
	 * 方法的描述:根据设备编号查询房间信息 
	 * @author: kouy  
	 * @version: 0.1
	 * @date: 2012-7-3 下午2:15:04
	 * @param deviceNo
	 * @return
	 */
	public RoomInfoJson queryRoomInfoByDeviceNo(String deviceNo);
	
	/**
	 * 方法的描述: 查询房间信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-21 上午10:41:08
	 * @param jsonString
	 * @return
	 */
	public RoomVO queryRoom(String jsonString);
	
	/**
	 * 方法的描述: 查询管理机信息
	 * @author: ykou  
	 * @version: 0.1
	 * @date: 2012-8-22 下午4:28:16
	 * @param deviceNo
	 * @return
	 */
	public RoomJson queryManageDevice(String deviceNo);
	
	/**
	 * 
	 * 方法的描述:根据设备编号查询房间信息和设备信息 
	 * @version: 0.1
	 * @param deviceNo
	 * @return
	 */
	public RoomDeviceJson queryRoomDevices(String deviceNo);
	
	/**
	 * 
	 * 方法的描述:根据完整房号查询房间信息和设备信息 
	 * @version: 0.1
	 * @param deviceNo
	 * @return
	 */
	public RoomSipJson queryManageDeviceByRoomNo(String roomNo);

}
