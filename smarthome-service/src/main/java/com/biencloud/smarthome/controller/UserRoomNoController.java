package com.biencloud.smarthome.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.biencloud.smarthome.service.base.service.IBaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.model.UserRoomNo;
import com.biencloud.smarthome.service.rest.model.UserRoomNoVo;
import com.biencloud.smarthome.service.rest.service.IUserRoomNoService;

@Controller
@RequestMapping("/user/roomNo")
public class UserRoomNoController extends BaseResController<UserRoomNo>{
		
	@Autowired
	private IUserRoomNoService userRoomNoService;
	
	@Override
	public IBaseResService<UserRoomNo> getBaseResService() {
		return userRoomNoService;
	}

	@RequestMapping(value="/queryList", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<UserRoomNo> queryList(UserRoomNo record) {
		return userRoomNoService.getList(record);
	}
	
	@RequestMapping(value="/queryListBySip", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<UserRoomNoVo> queryListBySip(UserRoomNo record) {
		return userRoomNoService.getListMapBySip(record);
	}
	
	@RequestMapping(value="/queryByDeviceNo", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<UserRoomNo> queryList(String deviceNo) {
		return userRoomNoService.getListByDeviceNo(deviceNo);
	}
	
	@RequestMapping(value="/bound", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> bound(UserRoomNo record) {
		if(record==null){
			return new ResultEntity<String>();
		}
		return userRoomNoService.save(record);
	}
	
	@RequestMapping(value="/remove", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> unbound(UserRoomNo record) {
		if(record==null || record.getDeviceNo()==null || record.getRoomNo()==null){
			return new ResultEntity<String>();
		}
		return userRoomNoService.remove(record);
	}
	
	@RequestMapping(value="/updateStatusBySipid", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody ResultEntity<String> updateStatusBySipid(UserRoomNo record) {
		return userRoomNoService.updateStatusBySipid(record);
	}
	
	@RequestMapping(value="/getForOne", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody UserRoomNo getForOne(Long id) {
		return userRoomNoService.getForOne(id);
	}
	
	@RequestMapping(value="/queryBySipid", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody UserRoomNo queryBySipid(String sipid,String roomNo) {
		return userRoomNoService.queryBySipid(sipid,roomNo);
	}
	
	@RequestMapping(value="/showBoundSip", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<UserRoomNo> showBoundSip(String roomNo) {
		return userRoomNoService.showBoundSip(roomNo);
	}
	
	@RequestMapping(value="/showBoundSipByHouseId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<UserRoomNo> showBoundSipByHouseId(Long houseId) {
		return userRoomNoService.showBoundSipByHouseId(houseId);
	}
	
	@RequestMapping(value="/queryByHouseId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<UserRoomNo> queryByHouseId(UserRoomNo record) {
		return userRoomNoService.queryByHouseId(record);
	}
	
	@RequestMapping(value="/queryByHouseIdAndSipId", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<UserRoomNo> queryByHouseIdAndDeviceId(UserRoomNo record) {
		return userRoomNoService.queryByHouseIdAndSipId(record);
	}
}
