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
import com.biencloud.smarthome.service.common.utils.JsonUtil;
import com.biencloud.smarthome.service.rest.mapper.PositionLineMapper;
import com.biencloud.smarthome.service.rest.model.PositionLine;
import com.biencloud.smarthome.service.rest.service.IPositionLineService;

@Service
public class PositionLineService extends BaseResService<PositionLine> implements IPositionLineService{

	@Autowired
	private PositionLineMapper positionLineMapper;
	
	@Override
	public BaseMapper<PositionLine> getBaseMapper() {
		// TODO Auto-generated method stub
		return positionLineMapper;
	}

	@Override
	public List<PositionLine> queryList(PositionLine line) {
		// TODO Auto-generated method stub
		return positionLineMapper.getList(line);
	}

	@Override
	public ResultEntity<String> deleteBySipUid(String sipUid) {
		// TODO Auto-generated method stub
		return super.proccessResultEntity(positionLineMapper.deleteBySipUid(sipUid)>0?ResultEntity.SUCCESS:ResultEntity.FAILD, "", "");
	}

	@Override
	public ResultEntity<String> addList(String pos) {
		ResultEntity<String> re = new ResultEntity<String>(ResultEntity.FAILD, "", "");
		try {
			if (pos != null && pos.startsWith("{") && pos.endsWith("}")) {

				JSONObject jb = JsonUtil.jsonStringToJsonObject(pos);
				JSONArray jsonArray = jb.getJSONArray("position");
				List<Object> objs = new ArrayList<Object>();
				List<PositionLine> tracks = new ArrayList<PositionLine>();
				
				if (jsonArray != null) {
					objs = (List<Object>) JSONSerializer.toJava(jsonArray);
					for (Object obj : objs) {
						JSONObject jsonObject = JSONObject.fromObject(obj);
						Long createTime = Long.parseLong((String)jsonObject.get("createTime"));
						jsonObject.put("createTime", new Date(createTime));
						PositionLine p = (PositionLine) JSONObject.toBean(jsonObject,
								PositionLine.class);
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
