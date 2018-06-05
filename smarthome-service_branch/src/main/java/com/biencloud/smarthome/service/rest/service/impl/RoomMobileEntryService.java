package com.biencloud.smarthome.service.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.utils.JsonUtil;
import com.biencloud.smarthome.service.rest.mapper.RoomMobileEntryMapper;
import com.biencloud.smarthome.service.rest.model.RoomMobileEntry;
import com.biencloud.smarthome.service.rest.model.RoomMobileEntryJson;
import com.biencloud.smarthome.service.rest.service.IRoomMobileEntryService;

@Service
public class RoomMobileEntryService extends BaseResService<RoomMobileEntry> implements IRoomMobileEntryService {

	@Autowired
	private RoomMobileEntryMapper roomMobileEntryMapper;

	@Override
	public BaseMapper<RoomMobileEntry> getBaseMapper() {
		return roomMobileEntryMapper;
	}

	@Override
	public ResultEntity<List<RoomMobileEntry>> queryList(
			RoomMobileEntry roomMobileEntry) {
		List<RoomMobileEntry> list = roomMobileEntryMapper.getList(roomMobileEntry);
		return new ResultEntity<List<RoomMobileEntry>>(ResultEntity.SUCCESS,"success",list);
	}

	@Override
	public ResultEntity<List<RoomMobileEntryJson>> addList(String jsonString) {
		RoomMobileEntry rme = new RoomMobileEntry();
		List<RoomMobileEntry> list = new ArrayList<RoomMobileEntry>();
		ResultEntity<List<RoomMobileEntryJson>> re = new ResultEntity<List<RoomMobileEntryJson>>(
				ResultEntity.FAILD, "", null);
		try {
			if(jsonString == null){ 
				re.setMessage("value is null");
				return re;
			}
			if (!jsonString.startsWith("{") || !jsonString.endsWith("}")) {
				re.setMessage("not a json format");
				return re;
			}
			
			JSONObject jb = JsonUtil.jsonStringToJsonObject(jsonString);
			
			JSONArray jsonArray = jb.getJSONArray("entry");
			List<Object> objs = new ArrayList<Object>();
			List<RoomMobileEntry> entrys = new ArrayList<RoomMobileEntry>();

			if (jsonArray == null) {
				re.setMessage("json array is null");
				return re;
			}

			objs = (List<Object>) JSONSerializer.toJava(jsonArray);
			for (Object obj : objs) {
				JSONObject jsonObject = JSONObject.fromObject(obj);
				RoomMobileEntryJson roo = (RoomMobileEntryJson) JSONObject.toBean(jsonObject,
						RoomMobileEntryJson.class);
				RoomMobileEntry rm = new RoomMobileEntry();
				String roomNum = roo.getRoomNo();
				if(roomNum!=null && roomNum.length()==15){
					String districtNo = roomNum.substring(0, 4);
					String reginNo = roomNum.substring(4, 6);
					String buildingNo = roomNum.substring(6, 9);
					String unitNo = roomNum.substring(9, 11);
					String roomNo = roomNum.substring(11, 15);
					
					rm.setDistrictNo(districtNo);
					rm.setReginNo(reginNo);
					rm.setBuildingNo(buildingNo);
					rm.setUnitNo(unitNo);
					rm.setRoomNo(roomNo);
					rm.setTargetUid(roo.getTargetUid());
					rm.setType(roo.getType());
					rm.setIosToken(roo.getIosToken());
				entrys.add(rm);
				}
			}
			String room = jb.getString("room");
			//如果room为空，则执行上传操作
			if(StringUtils.isBlank(room)){
				for(RoomMobileEntry obj : entrys){
					List<RoomMobileEntry> li = roomMobileEntryMapper.getListForEntity(obj);
					for(RoomMobileEntry r : li)
					roomMobileEntryMapper.delete(r.getId());
				}
				this.addListForResultEntity(entrys);
				re.setCode(ResultEntity.SUCCESS);
				return re;
			}else{
				String districtNo = room.substring(0, 4);
				String reginNo = room.substring(4, 6);
				String buildingNo = room.substring(6, 9);
				String unitNo = room.substring(9, 11);
				//String roomNo = room.substring(11, 15);
				
				rme.setDistrictNo(districtNo);
				//如果区域，楼栋，为00则查询该小区的所有手机信息
				if(!"00".equals(reginNo)){
					rme.setReginNo(reginNo);
				}
				if(!"000".equals(buildingNo)){
					rme.setBuildingNo(buildingNo);
				}
				if(!"00".equals(unitNo)){
					rme.setUnitNo(unitNo);
				}
				//rme.setRoomNo(roomNo);
				list = roomMobileEntryMapper.getList(rme);
			}
						
			List<RoomMobileEntry> tempList = new ArrayList<RoomMobileEntry>();
			tempList.addAll(list);
			tempList.removeAll(entrys);//list去掉上传的重复数据,下载给门口机
			entrys.removeAll(list);//上传的数据去掉服务器获取的，上传到服务器
			this.addListForResultEntity(entrys);
			List<RoomMobileEntryJson> jsons = new ArrayList<RoomMobileEntryJson>();
			for(RoomMobileEntry ro:tempList){
				RoomMobileEntryJson json = new RoomMobileEntryJson();
				json.setRoomNo(ro.getDistrictNo()+ro.getReginNo()+ro.getBuildingNo()+ro.getUnitNo()+ro.getRoomNo());
				json.setTargetUid(ro.getTargetUid());
				json.setType(ro.getType());
				json.setIosToken(ro.getIosToken());
				jsons.add(json);
			}
			re.setData(jsons);
			re.setCode(ResultEntity.SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

}  
