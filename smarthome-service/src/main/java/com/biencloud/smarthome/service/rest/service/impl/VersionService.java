package com.biencloud.smarthome.service.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biencloud.smarthome.service.base.mapper.BaseMapper;
import com.biencloud.smarthome.service.base.service.impl.BaseResService;
import com.biencloud.smarthome.service.common.constants.Constants;
import com.biencloud.smarthome.service.rest.mapper.VersionMapper;
import com.biencloud.smarthome.service.rest.model.Version;
import com.biencloud.smarthome.service.rest.service.IVersionService;

@Service
public class VersionService extends BaseResService<Version> implements
		IVersionService {

	@Autowired
	private VersionMapper versionMapper;

	@Override
	public BaseMapper<Version> getBaseMapper() {
		return versionMapper;
	}

	@Override
	public List<Version> queryList(Version version) {
		List<Version> list = versionMapper.queryList(version);
		//MCU版本>30(16进制)为新音乐IC版本
		//当MCU版本小于30的时候，是属于旧音乐IC的设备。此时如果查询到的版本高于或者等于30，则返回空列表，否则返回查询的数据。
		if(version!=null && version.getType().equals(Constants.VERSION_TYPE_MCU)){
			Integer curVersion = Integer.parseInt(version.getVersion(),16);
			if(curVersion<48){
				if(list!=null && list.size()>1){
					Map<String,String> map = (Map<String, String>) list.get(0);
					Integer queryVersion = Integer.parseInt(map.get("version"),16);
					if(queryVersion>=48){
						return new ArrayList<Version>();
					}
				}
			}
		}
		return list;
	}

}  
