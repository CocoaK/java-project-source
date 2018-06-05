package com.biencloud.smarthome.cxfservice.cxfmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import com.biencloud.smarthome.service.push.model.Push;
/**
 * 
 * 类名称：MapAdapter 
 * 类描述：Map类型适配器类 
 * @author: kouy  
 * @version: 0.1
 * @date: 2012-6-6 下午3:19:10
 */
public class PushMapAdapter extends XmlAdapter<PushMapConvertor, Map<String, List<Push>>> {
	@Override
	public PushMapConvertor marshal(Map<String, List<Push>> map) throws Exception {
		PushMapConvertor convertor = new PushMapConvertor();
		for (Map.Entry<String, List<Push>> entry : map.entrySet()) {
			PushMapConvertor.MapEntry e = new PushMapConvertor.MapEntry(entry);
			convertor.addEntry(e);
		}
		return convertor;
	}

	@Override
	public Map<String, List<Push>> unmarshal(PushMapConvertor map) throws Exception {
		Map<String, List<Push>> result = new HashMap<String, List<Push>>();
		for (PushMapConvertor.MapEntry e : map.getEntries()) {
			result.put(e.getKey(), e.getValue());
		}
		return result;
	}

}
