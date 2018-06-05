package com.biencloud.smarthome.service.rest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.model.ResultEntity;
import com.biencloud.smarthome.service.rest.mapper.PositionMapper;
import com.biencloud.smarthome.service.rest.model.Position;
import com.biencloud.smarthome.service.rest.service.IPositionService;
import com.biencloud.smarthome.service.common.utils.JsonUtil;

@Service
public class PositionService extends BaseResService<Position> implements
		IPositionService {

	@Autowired
	private PositionMapper positionMapper;

	@Override
	public BaseMapper<Position> getBaseMapper() {
		return positionMapper;
	}

	@Override
	public List<Position> queryList(Position position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultEntity<String> delete(Position position) {
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
				List<Position> positions = new ArrayList<Position>();
				
				if (jsonArray != null) {
					objs = (List<Object>) JSONSerializer.toJava(jsonArray);
					for (Object obj : objs) {
						JSONObject jsonObject = JSONObject.fromObject(obj);
						Long createTime = Long.parseLong((String)jsonObject.get("createTime"));
						jsonObject.put("createTime", new Date(createTime));
						Position p = (Position) JSONObject.toBean(jsonObject,
								Position.class);
						positions.add(p);
					}
				}
				this.addListForResultEntity(positions);
				re.setCode(ResultEntity.SUCCESS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}
}  
