package com.biencloud.smarthome.service.rest.service;

import java.util.List;

import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.UserRoomNo;
import com.biencloud.smarthome.service.rest.model.UserRoomNoVo;

public interface IUserRoomNoService extends IBaseResService<UserRoomNo>{
	
	ResultEntity<String> save(UserRoomNo lfUserDevice);
	
	List<UserRoomNo> getList(UserRoomNo lfUserDevice);

	ResultEntity<String> remove(UserRoomNo record);

	/**
	 * @desc   @param deviceNo
	 * @desc   @return
	 * @return List<LfUserDevice>
	 * @param deviceNo
	 * @return
	 */
	List<UserRoomNo> getListByDeviceNo(String deviceNo);

	/**
	 * @desc   @param record
	 * @desc   @return
	 * @return List<Object>
	 * @param record
	 * @return
	 */
	List<Object> getListMap(UserRoomNo record);
	
	ResultEntity<String> updateStatusBySipid(UserRoomNo record);

	/**
	 * @desc   @param deviceId
	 * @return void
	 * @param deviceId
	 */
	void deleteByDeviceNo(String deviceNo);
	
	UserRoomNo getForOne(Long id);
	
	UserRoomNo queryBySipid(String sipid,String roomNo);
	
	List<UserRoomNo> showBoundSip(String roomNo);
	
	List<UserRoomNo> showBoundSipByHouseId(Long houseId);
	
	List<UserRoomNo> queryByHouseId(UserRoomNo record);
	
	List<UserRoomNo> queryByHouseIdAndSipId(UserRoomNo record);

	List<UserRoomNoVo> getListMapBySip(UserRoomNo record);
}
