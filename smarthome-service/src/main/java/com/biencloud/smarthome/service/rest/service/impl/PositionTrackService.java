package com.biencloud.smarthome.service.rest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.common.utils.JsonUtil;
import com.biencloud.smarthome.service.rest.mapper.PositionTrackMapper;
import com.biencloud.smarthome.service.rest.model.PositionTrack;
import com.biencloud.smarthome.service.rest.service.IPositionTrackService;

@Service
public class PositionTrackService extends BaseResService<PositionTrack> implements IPositionTrackService{

	@Autowired
	private PositionTrackMapper positionTrackMapper;
	
	@Override
	public BaseMapper<PositionTrack> getBaseMapper() {
		// TODO Auto-generated method stub
		return positionTrackMapper;
	}

	@Override
	public List<PositionTrack> queryList(PositionTrack track, String beginTime,String endTime) {
		// TODO Auto-generated method stub
		if(StringUtils.isBlank(beginTime) || StringUtils.isBlank(endTime)){
			return null;
		}
		Date bTime = new Date(Long.parseLong(beginTime));
		Date eTime = new Date(Long.parseLong(endTime));
		return positionTrackMapper.getList(track,bTime,eTime);
	}

	@Override
	public ResultEntity<String> delete(PositionTrack track) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntity<String> addList(String pos) {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD, "", "");
		try {
			if (pos != null && pos.startsWith("{") && pos.endsWith("}")) {

				JSONObject jb = JsonUtil.jsonStringToJsonObject(pos);
				JSONArray jsonArray = jb.getJSONArray("position");
				List<Object> objs = new ArrayList<Object>();
				List<PositionTrack> tracks = new ArrayList<PositionTrack>();
				
				if (jsonArray != null) {
					objs = (List<Object>) JSONSerializer.toJava(jsonArray);
					for (Object obj : objs) {
						JSONObject jsonObject = JSONObject.fromObject(obj);
						Long createTime = Long.parseLong((String)jsonObject.get("createTime"));
						jsonObject.put("createTime", new Date(createTime));
						PositionTrack p = (PositionTrack) JSONObject.toBean(jsonObject,
								PositionTrack.class);
						tracks.add(p);
					}
				}
				this.addListForResultEntity(tracks);
				re.setCode(ResultEntity.SUCCESS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

}
