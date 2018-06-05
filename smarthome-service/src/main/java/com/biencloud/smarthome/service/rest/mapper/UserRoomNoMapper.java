package com.biencloud.smarthome.service.rest.mapper;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.rest.model.UserRoomNo;
import com.biencloud.smarthome.service.rest.model.UserRoomNoVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserRoomNoMapper extends BaseMapper<UserRoomNo>{
    int delete(Long id);

    int insert(UserRoomNo record);

    List<UserRoomNo> getForList();

    UserRoomNo getForOne(Long id);

    int updateOnActive(UserRoomNo record);

    int update(UserRoomNo record);

	/**
	 * @desc   @param deviceId
	 * @desc   @param roomNo
	 * @return void
	 * @param deviceId
	 * @param roomNo
	 */
	int remove(UserRoomNo record);
	
	List<Object> getListMap(UserRoomNo record);
	
	int updateStatusBySipid(UserRoomNo record);
	
	UserRoomNo queryBySipid(@Param(value = "sipid") String sipid,@Param(value="roomNo") String roomNo);
	
	List<UserRoomNo> showBoundSip(String roomNo);
	
	List<UserRoomNo> showBoundSipByHouseId(Long houseId);
	
	List<UserRoomNo> queryByHouseId(UserRoomNo record);
	
	List<UserRoomNo> queryByHouseIdAndSipId(UserRoomNo record);

	List<UserRoomNoVo> getListMapByEntity(UserRoomNo record);
}