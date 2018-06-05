package com.biencloud.smarthome.cxfservice.cxfmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.biencloud.smarthome.cxfservice.cxfmap.MapConvertor.MapEntry;
import com.biencloud.smarthome.service.monitor.model.SceneDevice;
/**
 * 
 * 类名称：MapAdapter 
 * 类描述：Map类型适配器类 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-6-6 下午3:19:10
 */
public class MapAdapter extends XmlAdapter<MapConvertor, Map<String, List<SceneDevice>>> {
	@Override
	public MapConvertor marshal(Map<String, List<SceneDevice>> map) throws Exception {
		MapConvertor convertor = new MapConvertor();
		for (Map.Entry<String, List<SceneDevice>> entry : map.entrySet()) {
			MapConvertor.MapEntry e = new MapConvertor.MapEntry(entry);
			convertor.addEntry(e);
		}
		return convertor;
	}

	@Override
	public Map<String, List<SceneDevice>> unmarshal(MapConvertor map) throws Exception {
		Map<String, List<SceneDevice>> result = new HashMap<String, List<SceneDevice>>();
		for (MapConvertor.MapEntry e : map.getEntries()) {
			result.put(e.getKey(), e.getValue());
		}
		return result;
	}

}
